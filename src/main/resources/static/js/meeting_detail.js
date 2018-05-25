layui.define(['jquery', 'form', 'layer', 'table','laydate'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;
    var laydate = layui.laydate;
    var meetId;

    $(function () {
        meetId=getQueryString("id");
        console.log(meetId)
        //渲染表格
        table.render({
            elem: '#test',
            url: '/agenda/findById/'+meetId,
            page: false,
            cols: [[
                {type: 'checkbox'},
                {type: 'numbers', width: 100, sort: true, title: '编号'},
                // {field: 'id', minWidth : 280, sort: true, title: '角色名'},
                {
                    field: 'content', minWidth: 280, sort: true, title: '议程内容'
                },
                {field: 'beginTime', minWidth: 280, sort: true, title: '议程时间'},
                {field: 'files', minWidth: 280, sort: true, title: '议程附件'},
                {field: 'remark', minWidth: 280, sort: true, title: '备注'}
            ]]
        });

    });

    //获取iframe 地址
    function getIframeByElement(element){
        var iframe;
        $("iframe", window.parent.document).each(function(){
            if(element.ownerDocument === this.contentWindow.document) {
                iframe = this;
            }
            return !iframe;
        });
        return iframe;
    };

    //通过正则表达式获取？号之后的参数
    function getQueryString(name){
        var iframe=getIframeByElement(document.body);
        var iframeObj=$(iframe);
        var address=iframeObj.context.src.split("?");

        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = address[1].match(reg);
        if (r!=null) return r[2]; return '';
    };

    //添加按钮点击事件
    $("#addBtn").click(function () {
        showEditModel(null);
    });

    //添加按钮点击事件
    $("#delete").click(function () {
        var checkStatus = table.checkStatus('test')
            , data = checkStatus.data;
        layer.confirm('确认要删除吗？', function () {
            //捉到所有被选中的，发异步进行删除
            $.post({
                url: '/agenda/delete',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        layer.alert("删除成功", {icon: 6});
                        layer.closeAll('page');
                        layui.table.reload('test', {});
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
        layer.open({
            type: 1,
            title: data == null ? "添加议程" : "修改议程",
            area: '450px',
            offset: '120px',
            content: $("#addModel").html()
        });
        $("#editForm")[0].reset();
        $("#editForm").attr("method", "POST");
        $("#editForm input[name=meetId]").val(meetId);

        $("#btnCancel").click(function () {
            layer.closeAll('page');
        });
        laydate.render({
            elem: '#beginTime' //指定元素
        });
    };

    //表单提交事件
    layui.form.on('submit(add)', function (data) {
        // data.field.token = getToken();
        data.field._method = $("#editForm").attr("method");
        layer.load(1);
        $.post("/agenda/add", data.field, function (data) {
            layer.closeAll('loading');
            if (data.code == 0) {
                layer.alert("增加成功", {icon: 6});
                layer.closeAll('page');
                layui.table.reload('test', {});
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }, "JSON");
        return false;
    });


    //输出test接口
    exports('meet_detail', {});
});