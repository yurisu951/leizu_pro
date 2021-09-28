$(function (){
    $('input').keyup(function(e){
        if($(this).val().length==$(this).attr('maxlength'))
            $(this).next(':input').focus();
    });




    init();
    function init(){
        for (var i =2;i < 13;i++){
            var strNum = (i<10) ? '0'+i : i;
            var $monthOption = $("<option value='"+i+"'>"+ strNum +"</option>")
            $(".expired_month").append($monthOption);
        }

        for (var i =22;i < 28;i++){
            var $yearOption = $("<option value='"+i+"'>"+ i +"</option>")
            $(".expired_year").append($yearOption);
        }

    }




});