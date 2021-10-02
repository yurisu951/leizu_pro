$(function (){
    var verifyCode = '000000';
    var userEmail = '';

    $(".nav_top").find("li").click(function (){
        var index = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $(".nav_bottom").find("li").eq(index).addClass("current").siblings().removeClass("current");
    });

    $(".loginSec").find("input[type='submit']").click(function (){
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
        var url = document.referrer;
        url = url.substring(url.indexOf("/", 7));
        $("input[type=hidden]").val(url);
        return true;
    });

    $(".registerSec").find("input[type=submit]").click(function (){
        var uemail = $("#userEmail").val();

        $.ajax({
            url: "/registered/email_check?userEmail=" + uemail,
            method: "GET",
            dataType: "text",
            success: function (data){
                //  信箱已经注册过
                if (data === "registered"){
                    $(".registerSec").find("p").eq(1).text("此信箱已被註冊過").css({color:"brown"});
                }

                if (data === "no") {
                    $(".mask").css({
                        display: "inline-block"
                    });
                    var $this = $(this);
                    verifyCode = ranStr();
                    userEmail = $("#userEmail").val();
                    emailTo();
                }
            },
            error: function (error){
                console.log(error);
            }
        });

        return false;
    });

    $(".verifySec").find("input[type=submit]").click(function (){
        var inputVerify = $("#verify").val();
        if (inputVerify === verifyCode){
            return true;
        }
        $(this).siblings(".warn").css({
            display: "block"
        });
        return false;
    });



    function ranStr(){
        let temp = '0123456789QAZXSWEDCVFRTGBNHYUJMKIOLP';
        let result = '';
        for(let i = 0; i <6; i++){
            let index = Math.random()* (temp.length);
            result += temp.charAt(index);
        }
        return result;
    }


    function emailTo(){
        emailjs.init("user_bIfDwaLRbFfQmf0h2jZsH");

        let templateParams = {
            "userMail": userEmail,
            "content": "<div>" +
                "<h2 style='text-align: center;'> Leizu國際服飾 - 電子信箱驗證信</h2>" +
                "<h4>歡迎註冊 Leizu國際服飾 會員</h4>" +
                "<hr>" +
                "<p>認證e-mail："+userEmail+"</p>" +
                "<p>註冊驗證碼："+verifyCode+"</p>" +
                "<hr>" +
                "<p style='color: brown;'>請在1小時內回填驗證碼進行註冊；本郵件由系統自動發出，請勿回覆</p>" +
                "</div>"
        }

        let service_id = "service_51zrmog";
        let template_id = "template_ocqsc0d";
        let userID = "user_bIfDwaLRbFfQmf0h2jZsH"
        emailjs.send(service_id, template_id, templateParams,userID)
            .then((response) => {
                alert("驗證信已發送，請至信箱查收");
                $(".verifySec").addClass("current").find(".userEmail").val(userEmail).attr("value", userEmail);
                $(".verifySec").siblings().removeClass("current");
                $(".mask").css({
                    display: "none"
                });
            })
            .catch((error) => {
                console.log('FAILED...', error);
                alert("網路延遲，請重新發送");
                $(".mask").css({
                    display: "none"
                });
                // $(".registerSec").find("input[type=submit]").prop("disable", false);
            })
    }

});