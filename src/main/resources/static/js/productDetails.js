$(function(){
    var color = $(".color").find("li").eq(0).attr("value");
    var size = $(".size").find("li").eq(0).attr("value");
    const productTitle =$("#product_title").text();
    $(".color").find("li").eq(0).addClass("current");
    $(".product_main_image").eq(0).addClass("current");

    $(".color").find("li").click(function(){
        var index = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".product_main_image").eq(index).addClass("current").siblings().removeClass("current");
        color = $(this).attr("value");
        $("#product_title").text(productTitle + '（' + color + ' -' + size +'）');
    });

    $(".size").find("li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        size = $(this).attr("value");
        $("#product_title").text(productTitle + '（' + color + ' -' + size +'）');
    });

    $(".minus").click(function(){
        var value = $("#buyNumber").val() - 1;
        if(value <= 0) value = 0;
        $("#buyNumber").val(value);
    });

    $(".plus").click(function(){
        var value = parseInt($("#buyNumber").val()) + 1;
        if(value >= 99) value = 99;
        $("#buyNumber").val(value);
    });

    $(".toUp").click(function(){
        $("html, body").animate({scrollTop:0}, 500, function(){
            $(".toUp").fadeOut();
        });
    });

    $(window).scroll(function(){
        var top = $(this).scrollTop();
        if(top > 600){
            $(".toUp").fadeIn();
        }
    });

    if($(".activities")){
        $(".price .activities").append("<span> &gt; </span>");
        $(".price .activities").append("<span> &gt; </span>");
        var tempIndex = 0;
        var timer = setInterval(function (){
            tempIndex++;
            $(".price .activities").children().eq(tempIndex%2).css({color: "#fff"}).siblings().css({color: "#aaa"});
        }, 500);
    }



});