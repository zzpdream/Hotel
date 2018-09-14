layui.define(['jquery', 'form', 'layer', 'table'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;

    $(function () {
        //渲染表格
        table.render({
            elem: '#rooms',
            url: '/hotel/room/findAll',
            page: false,
            cols: [[
                {type: 'checkbox'},
                {type: 'numbers', width : 100, sort: true, title: '编号'},
                // {field: 'id', minWidth : 280, sort: true, title: '角色名'},
                {field: 'name', minWidth : 280, sort: true, title: '房间名'},
                {field: 'info', minWidth : 280, sort: true, title: '房描述'},
                {field: 'price', minWidth : 280, sort: true, title: '价格'},
                {field: 'img', minWidth : 280, sort: true, title: '图片'}
            ]]
        });
    });



    //输出test接口
    exports('room', {});
});