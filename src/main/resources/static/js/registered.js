$(function (){
    var index = 5;
    var timer = setInterval(function (){
        index--;
        if (index > -1){
            $(".registered").find("span").text(index);
        } else {
            clearInterval(timer);
            window.location.replace("/index");
        }
    },1000);
});