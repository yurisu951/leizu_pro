$(function (){
    let userEmail = '';
    let userName = '';
    let userPassword = '';
    $(".verifySec").find("input[type=submit]").click(function (){
        userEmail = $(this).siblings(".userEmail").val().trim();
        if (userEmail != null && userEmail != ""){
            $.ajax({
                url: "/registered/email_check?userEmail=" + userEmail,
                type: "GET",
                success: function (data){
                    if (data === "no"){
                        $(".failed").addClass("current").siblings().removeClass("current");
                    }
                    if (data === "registered"){
                        $(".mask").css({display: "block"});
                        userPassword = ranStr();
                        changePwd();
                    }
                }, error: function (error){
                    console.log(error);
                }
            });
        }
        return false;
    });

    function ranStr(){
        let temp = '0123456789QAZXSWEDCVFRTGBNHYUJMKIOLPzaqwsxcderfvbgtyhnmjuiklop';
        let result = '';
        for(let i = 0; i < 10; i++){
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
                "<h2 style='text-align: center;'> Leizu國際服飾 - 要求重設密碼</h2>" +
                "<hr>" +
                "<p>親愛的"+ userName+ "：</p>" +
                "<p>您的密碼已經變更為 <strong style='font-size: 14px;'>"+ userPassword +"</strong></p>" +
                "<hr>" +
                "<p style='color: brown;'>本郵件由系統自動發出，請勿回覆</p>" +
                "</div>"
        }

        let service_id = "service_51zrmog";
        let template_id = "template_xf9pjir";
        let userID = "user_bIfDwaLRbFfQmf0h2jZsH"
        emailjs.send(service_id, template_id, templateParams,userID)
            .then((response) => {
                $(".emailed").addClass("current").siblings().removeClass("current");
                $(".mask").css({
                    display: "none"
                });
            })
            .catch((error) => {
                console.log('FAILED...', error);
                alert("網路發生問題，請重新發送");
                $(".mask").css({
                    display: "none"
                });
            })
    }

    function changePwd(){
        $.ajax({
            url: "/user/change_password",
            type: "PUT",
            data: {
                userEmail: userEmail,
                userPassword : userPassword
            }, success: function (data){
                if (data == null || data === "null"){
                    alert("網路發生問題，請重新發送");
                    $(".mask").css({
                        display: "none"
                    });
                } else {
                    userName = data;
                    emailTo();
                }
            }, error: function (error){
                console.log(error);
            }
        });
    }


});