layui.define(['jquery', 'form', 'layer', 'table','laydate'], function (exports) {
    var $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;
    var laydate = layui.laydate;

    $(function () {
        var iframe=getIframeByElement(document.body);
        var iframeObj=$(iframe);
        console.log(iframeObj);
       alert(iframeObj.context.src);

    });

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


    //输出test接口
    exports('meet_detail', {});
});