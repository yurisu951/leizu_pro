var productTitle;
var color;
var size;

$(function(){
    productTitle =$("#product_title").text();
    color = $(".color").find("li").eq(0).children("img").attr("title");
    size = $(".size").find("li").eq(0).text();

    $("#product_title").text(productTitle + '（' + color + ' -' + size +'）');
    $(".color").find("li").eq(0).addClass("current");
    $(".product_main_image").eq(0).addClass("current");


    $(".color").find("li").click(function(){
        var index = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".product_main_image").eq(index).addClass("current").siblings().removeClass("current");
        color = $(this).children("img").attr("title");
        $("#product_title").text(productTitle + '（' + color + ' -' + size +'）');
    });

    $(".size").find("li").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        size = $(this).text();
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


    if($(".activities")){
        $(".price .activities").append("<span> &gt; </span>");
        $(".price .activities").append("<span> &gt; </span>");
        var tempIndex = 0;
        var timer = setInterval(function (){
            tempIndex++;
            $(".price .activities").children().eq(tempIndex%2).css({color: "#fff"}).siblings().css({color: "#aaa"});
        }, 500);
    }

    if(colorName!= null){
        var $colorLi = $(".color").find("li");
        for (var i = 0; i < $colorLi.length; i++){
            var temp = $colorLi.eq(i).children().attr("title");
            if (temp === colorName){
                $colorLi.eq(i).addClass("current").siblings().removeClass("current");
                $(".product_main_image").eq(i).addClass("current").siblings().removeClass("current");
                color = colorName;
                $("#product_title").text(productTitle + '（' + color + ' -' + size +'）');
                break;
            }
        }
    }
});