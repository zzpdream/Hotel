layui.define(['jquery', 'form', 'layer', 'table'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;

    $(function () {
        //渲染表格
        table.render({
            elem: '#orders',
            url: '/hotel/order/findAll',
            page: false,
            cols: [[
                {type: 'checkbox'},
                {type: 'numbers', width : 100, sort: true, title: '编号'},
                // {field: 'id', minWidth : 280, sort: true, title: '角色名'},
                {field: 'orderName', minWidth : 180, sort: true, title: '姓名'},
                {field: 'orderPrice', minWidth : 100, sort: true, title: '价格'},
                {field: 'orderTel', minWidth : 180, sort: true, title: '客人电话'},
                {field: 'beginTime', minWidth : 180, sort: true, title: '开始时间'},
                {field: 'endTime', minWidth : 180, sort: true, title: '结束时间'},
                {field: 'orderRoomName', minWidth : 200, sort: true, title: '预定房间'}
            ]]
        });
    });



    //输出test接口
    exports('room', {});
});