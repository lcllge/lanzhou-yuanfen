$(function () {
    loadDataAnimate();

    function loadDataAnimate() {
        var html = '';
        for (let i = 0; i < 30; i++) {
            html += '<div class="card">' +
                '<div class="content outer shiny-shadow-blue">' +
                '<div class="inner">' +
                '<img class="show-img" src="../authentic/images/toilet.jpg"/>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        $(".container .stage").html(html);
    }



    /*为元素添加hover事件*/
    $('.container .stage .card').on({
        mouseenter:function(){
            $(this).find(".outer").removeClass("shiny-shadow-blue").find(".inner").addClass("blue-bypass scale-percent");
        },
        mouseleave:function(){
            $(this).find(".outer").addClass("shiny-shadow-blue").find(".inner").removeClass("blue-bypass scale-percent");
        }
    });

});