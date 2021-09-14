$(function (){
    $("#addAll").click(function (){
        var $boxArr = $("tbody").find("input:checkbox").prop("checked", $(this).prop("checked"));
        countPrice(true, $(this));
    });

    $("tbody").find("input:checkbox").click(function (){
        if ($("#addAll").prop("checked") == true && $(this).prop("checked") == false){
            $("#addAll").prop("checked", false);
        }
        countPrice(false, $(this).parents("tr"));
    });

    function countPrice(all, $item){
        var originalTotalPrice = 0;
        var discount = 0;
        var totalPrice = 0;

        if(all) {
            if ($item.prop("checked")) {
                var $h5Arr = $("tbody").find("h5");
                var $numArr = $("tbody").find(".buyNumber");
                for (var i = 0; i < $h5Arr.length; i++) {
                    var priceArr = $h5Arr.eq(i).attr("value").split(",");
                    var number = $numArr.eq(i).val();

                    originalTotalPrice += priceArr[0] * number;

                    if (!isNaN(parseInt(priceArr[1]))) {
                        // 有優惠情形下
                        discount += -(priceArr[0] - priceArr[1]) * number;
                    }
                    totalPrice = originalTotalPrice + discount + 60;
                }
            }
        } else {
            originalTotalPrice = parseInt($("#product_price").text().substring(3).trim());
            discount = parseInt($("#discount_price").text().substring(3).trim());
            totalPrice = parseInt($("#total_price").text().substring(3).trim());
            var priceArr = $item.find("h5").attr("value").split(",");
            var number = $item.find(".buyNumber").val();

            if ($item.find("input:checkbox").prop("checked")){
                originalTotalPrice += parseInt(priceArr[0])*number;
                if (!isNaN(priceArr[1])){
                    discount -= (priceArr[0] - priceArr[1])*number;
                }
            } else {
                originalTotalPrice -= parseInt(priceArr[0])*number;
                if (!isNaN(priceArr[1])){
                    discount += (priceArr[0] - priceArr[1])*number;
                }
            }
            totalPrice = originalTotalPrice + discount + 60;

        }

            $("#product_price").text('NT$ '+ originalTotalPrice);
            $("#discount_price").text('NT$ ' + discount);
            $("#total_price").text('NT$ ' + totalPrice);
        }



});