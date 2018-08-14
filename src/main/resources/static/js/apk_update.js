layui.define(['jquery', 'form', 'layer', 'table', 'upload'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        upload = layui.upload;

    $(function () {
        //渲染表格
        table.render({
            elem: '#apkUpdateList',
            url: '/apk/findAll',
            page: false,
            cols: [[
                {type: 'checkbox'},
                {type: 'numbers', width: 100, sort: true, title: '编号'},
                {field: 'name', minWidth: 280, sort: true, title: '文件名',
                    templet: '<div><a href="{{d.url}} " target="_blank" class="layui-table-link">{{d.name}}</a></div>'},
                {
                    field: 'versionCode', minWidth: 280, sort: true, title: 'VerisonCode'
                }
            ]]
        });
    });



    //添加按钮点击事件
    $("#addBtn").click(function () {
        showEditModel(null);
    });

    //添加按钮点击事件
    $("#delete").click(function () {
        var checkStatus = table.checkStatus('apkUpdateList')
            , data = checkStatus.data;
        layer.confirm('确认要删除吗？', function () {
            //捉到所有被选中的，发异步进行删除
            $.post({
                url: '/apk/delete',
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        layer.alert("删除成功", {icon: 6});
                        layer.closeAll('page');
                        layui.table.reload('apkUpdateList', {});
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
            title: data == null ? "添加apk" : "修改议程",
            area: '450px',
            offset: '120px',
            content: $("#addModel").html()
        });

        upload.render({
            elem: '#addfile'
            ,url: '/apk/uploadapk/',
            field: 'file'
            ,done: function(res, index, upload){ //上传后的回调
                if(res.code==0){
                    layer.msg("上传成功");
                    $("#pdfFileList").append('<div><a class="filename" data-title=' + res.name
                        + ' data-url=' + res.url + ' href=' + res.url + '>' + res.name
                        + '</a>&nbsp;<a class="btn red del_file">删除</a>&nbsp;&nbsp;</div>');
                    $("#url").val(res.url);
                    $("#name").val(res.name);
                }else{
                    layer.msg(res.msg);
                }

            }
            ,accept: 'file' //允许上传的文件类型
            //,size: 50 //最大允许上传的文件大小
            //,……
        });

        $("#editForm")[0].reset();
        $("#editForm").attr("method", "POST");

        $("#btnCancel").click(function () {
            layer.closeAll('page');
        });
    };

    //表单提交事件
    layui.form.on('submit(add)', function (data) {
        // data.field.token = getToken();
        data.field._method = $("#editForm").attr("method");
        layer.load(1);
        console.log(data.field);
        $.post("/apk/add", data.field, function (data) {
            layer.closeAll('loading');
            if (data.code == 0) {
                layer.alert("增加成功", {icon: 6});
                layer.closeAll('page');
                layui.table.reload('apkUpdateList', {});
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }, "JSON");
        return false;
    });


    //输出test接口
    exports('meet_detail', {});
});