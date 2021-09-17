$(function (){
    $(".nav_top").find("li").click(function (){
        var index = $(this).index();
        console.log(index)
        $(this).addClass("current").siblings().removeClass("current");
        $(".nav_bottom").find("li").eq(index).addClass("current").siblings().removeClass("current");
    });

    $("input[type='submit']").click(function (){
        var account =  $("#account").val();
        var password =  $("#password").val();
        $(".msg").text('');
        if( account == null || account === ''){
            $(".msg").eq(0).text("Email / 手機號碼格式錯誤");
            return false;
        }

        if (password.length < 6 || password.length > 20){
            $(".msg").eq(1).text("密碼格式錯誤");
            return false;
        }
        return true;
    });

});