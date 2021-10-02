$(function (){
    $(".register").find("input[type=submit]").click(function (){
        if ($("#msg").text().length > 1){
            return false;
        }
        if ($("#uName").val() == null || $("#uName").val().trim() == '' ) {
            $("#msg").text("姓名不可為空").css({color: "brown"});
            return false;
        }
        if ($("#upwd").val() == null || $("#upwd").val().trim() == '') {
            $("#msg").text("密碼不可為空").css({color: "brown"});
            return false;
        }
        if ($("#uPhone").val() == null || $("#uPhone").val().trim() == '') {
            $("#msg").text("手機不可為空").css({color: "brown"});
            return false;
        }
        return true;
    });

    $("#upwd").blur(function (){
        var psd = $("#upwd").val().trim();
        if (psd.length < 6 || psd.length > 20){
            $("#msg").text("密碼長度必須介於6-20英數字").css({color: "brown"});
            return;
        } else {
            $("#msg").text(".").css({color: "white"});
        }

        let reg = /(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}/;
        if (!reg.test(psd)){
            $("#msg").text("密碼至少包含一位字母以及數字").css({color: "brown"});
        } else {
            $("#msg").text(".").css({color: "white"});
        }
    });

    $("#upwd_again").blur(function (){
        if ($("#upwd").val().trim() != $("#upwd_again").val().trim()){
            $("#upwd").text("");
            $("#upwd_again").text("");
            $("#msg").text("密碼不一致，請重新輸入").css({color: "brown"});
        }
    });
});