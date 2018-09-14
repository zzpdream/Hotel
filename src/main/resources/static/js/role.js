layui.define(['jquery', 'form', 'layer', 'table'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;

    $(function () {
        //渲染表格
        table.render({
            elem: '#test',
            url: '/hotel/role/findAll',
            page: false,
            cols: [[
                {type: 'checkbox'},
                {type: 'numbers', width : 100, sort: true, title: '编号'},
                // {field: 'id', minWidth : 280, sort: true, title: '角色名'},
                {field: 'name', minWidth : 280, sort: true, title: '角色名'}
            ]]
        });
    });

    //添加按钮点击事件
    $("#addBtn").click(function(){
        showEditModel(null);
    });

    //添加按钮点击事件
    $("#delete").click(function(){
        var checkStatus = table.checkStatus('test')
            ,data = checkStatus.data;
        layer.confirm('确认要删除吗？', function () {
            //捉到所有被选中的，发异步进行删除
            $.post({
                url: '/hotel/role/delete_role',
                data:JSON.stringify(data),
                contentType:"application/json",
                dataType:"json",
                success: function(data) {
                    if (data.code == 0) {
                        layer.alert("删除成功", {icon: 6});
                        layer.closeAll('page');
                        layui.table.reload('test', {});
                    } else {
                        layer.msg(data.msg,{icon: 2});
                    }
                }
            });
            // $(".layui-form-checked").not('.header').parents('tr').remove();
        });


    });

    //显示表单弹窗
    function showEditModel(data) {
        layer.open({
            type: 1,
            title: data == null ? "添加角色" : "修改角色",
            area: '450px',
            offset: '120px',
            content: $("#addModel").html()
        });
        $("#editForm")[0].reset();
        $("#editForm").attr("method", "POST");
        if (data != null) {
            $("#editForm input[name=roleId]").val(data.roleId);
            $("#editForm input[name=roleName]").val(data.roleName);
            $("#editForm textarea[name=comments]").val(data.comments);
            $("#editForm").attr("method", "PUT");
        }
        $("#btnCancel").click(function () {
            layer.closeAll('page');
        });
    };

    //表单提交事件
    layui.form.on('submit(add)', function(data){
        // data.field.token = getToken();
        data.field._method = $("#editForm").attr("method");
        layer.load(1);
        $.post("/role/add_role", data.field, function(data){
            layer.closeAll('loading');
            if(data.code==0){
                layer.alert("增加成功", {icon: 6});
                layer.closeAll('page');
                layui.table.reload('test', {});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }, "JSON");
        return false;
    });

    //输出test接口
    exports('role', {});
});