layui.define(['jquery', 'form', 'layer', 'table'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;

    $(function () {
        //渲染表格
        table.render({
            elem: '#users',
            url: '/user/findAll',
            page: false,
            cols: [[
                {type: 'checkbox'},
                {field: 'id', minWidth: 100, sort: true, title: 'ID'},
                {field: 'name', minWidth: 280, sort: true, title: '用户名'},
                {field: 'tel', minWidth: 280, sort: true, title: '电话'},
                {field: 'rolename', minWidth: 280, sort: true, title: '角色'}
            ]]
        });
    });

    //添加按钮点击事件
    $("#addUserBtn").click(function () {
        showEditModel(null);
    });

    $("#updateUserBtn").click(function () {
        var checkStatus = table.checkStatus('users')
            , data = checkStatus.data;
        showEditModel(data);
    });

    //删除
    $("#deleteBtn").click(function () {
        var checkStatus = table.checkStatus('users')
            , data = checkStatus.data;
        layer.confirm('确认要删除吗？', function () {
            //捉到所有被选中的，发异步进行删除
            $.post({
                url: '/user/delete_user',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        layer.alert("删除成功", {icon: 6});
                        layer.closeAll('page');
                        layui.table.reload('users', {});
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
            // $(".layui-form-checked").not('.header').parents('tr').remove();
        });


    });

    //显示表单弹窗
    function showEditModel(data) {
        if (data != null && data.length != 1) {
            layer.msg("请选择一条数据！");
            return;
        }
        layer.open({
            type: 1,
            area: [($(window).width() * 0.9), ($(window).height() - 50)],
            title: data == null ? "添加用户" : "修改用户",
            content: $("#addModel").html()
        });
        $("#user_form")[0].reset();
        $("#user_form").attr("method", "POST");
        if (data != null) {
            console.log(data[0]);
            $("#user_form input[name=name]").val(data[0].name);
            $("#user_form input[name=tel]").val(data[0].tel);
            // $("#role-select").val(data[0].);
            // $("#user_form textarea[name=comments]").val(data.comments);
            $("#user_form").attr("method", "PUT");
        }
        $("#btnCancel").click(function () {
            layer.closeAll('page');
        });
        getRoles();

        //自定义验证规则
        form.verify({
            name: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , repass: function (value) {
                if ($('#password').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });
    };

    //添加按钮点击事件
    $("#deleteBtn").click(function () {
        var checkStatus = table.checkStatus('users')
            , data = checkStatus.data;
        layer.confirm('确认要删除吗？', function () {
            //捉到所有被选中的，发异步进行删除
            $.post({
                url: '/user/delete_user',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        layer.alert("删除成功", {icon: 6});
                        layer.closeAll('page');
                        layui.table.reload('users', {});
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
            // $(".layui-form-checked").not('.header').parents('tr').remove();
        });


    });

    //表单提交事件
    layui.form.on('submit(add)', function (data) {
        // data.field.token = getToken();
        data.field._method = $("#editForm").attr("method");
        layer.load(1);
        $.post("/user/add_user", data.field, function (data) {
            layer.closeAll('loading');
            if (data.code == 0) {
                layer.alert("增加成功", {icon: 6});
                layer.closeAll('page');
                layui.table.reload('users', {});
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }, "JSON");
        return false;
    });


    //获取所有角色
    var roles = null;

    function getRoles() {
        if (roles != null) {
            layui.laytpl(rolesSelect.innerHTML).render(roles, function (html) {
                $("#role-select").html(html);

                layui.form.render('select');
                layer.closeAll('loading');
            });
        } else {
            layer.load(1);
            $.get("/role/findAll", function (data) {
                if (0 == data.code) {
                    roles = data.data;
                    getRoles();
                }
            });
        }
    }

    //输出test接口
    exports('role', {});
});