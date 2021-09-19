$(function (){
   $("#check").click(function (){
       $(".block_check").css({
           display: "block"
       });
   });

   $("#cancel").click(function (){
       $(".block_check").css({
           display: "none"
       });
   });


   $(".block_check").find("input[type=submit]").click(function (){
       var pwd = $(this).siblings("input[type=password]").val().trim();
       if (pwd != null && pwd != ''){
           $.ajax({
               url: "/check_again",
               type: "post",
               data: JSON.stringify({password: pwd}),
               contentType: "application/json",
               success: function (data){
                   if (data == 'true'){
                       window.location.assign("/user/profile");
                       return;
                   } else {
                       alert("密碼輸入錯誤");
                       $(".block_check").find("input[type=password]").val("");
                   }

               }, error: function (error){
                   console.log(error);
                   $("#cancel").click();
               }
           });
       }


   });

});

