$(function (){
    var flagOk;

    $("input[type=password]").focus(function (){
        $(this).parent().children(".remind").css({
            display: "inline-block"
        });
    });

    $("input[type=password]").blur(function (){
        $(this).parent().children(".remind").css({
            display: "none"
        });

    });

    $("input[type=password]").eq(1).blur(function (){
        var $arr = $("input[type=password]");

        if ($arr.eq(0).val() === $arr.eq(1).val()){
            flagOk = true;
            return;
        }
        flagOk = false;
        $arr.eq(1).parent().find("strong").text("密碼確認有誤，請重新輸入");
        $arr.eq(1).parent().children(".remind").css({
            display: "inline-block"
        });
    });

    $("input[type=submit]").click(function (){
        if(flagOk != false){
            return true;
        }
        return false;
    });

});