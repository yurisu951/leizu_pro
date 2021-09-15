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

        // 全載入=checkbox，個體 null
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
            var $itemArr = $("tbody").children("tr");
            for (var i = 0; i < $itemArr.length; i++){
                if ($itemArr.eq(i).find("input:checkbox").prop("checked")){
                    $item = $itemArr.eq(i);
                    var priceArr = $item.find("h5").attr("value").split(",");
                    var number = $item.find(".buyNumber").val();

                    originalTotalPrice += parseInt(priceArr[0])*number;
                    if (!isNaN(priceArr[1])){
                        discount -= (priceArr[0] - priceArr[1])*number;
                    }
                }
            }
            totalPrice = originalTotalPrice + discount + 60;
        }

            $("#product_price").text('NT$ '+ originalTotalPrice);
            $("#discount_price").text('NT$ ' + discount);
            $("#total_price").text('NT$ ' + totalPrice);
        }

    // 添加、減少購買數量
    $(".minus").click(function(){
        var $dest = $(this).parent("span").children(".buyNumber");
        var value = $dest.val() - 1;
        if(value <= 0) {
            var title = $(this).parents("tr").find("h5").text();
            var tempCheck = confirm("是否刪除商品 " + title);
            if (tempCheck){
                alert("已刪除商品");
            }
        } else {
            $dest.val(value);
        }

    //    處理金額
        var $item = $(this).parents("tr");
        $item.find("input:checkbox").prop("checked", true);
        countPrice(false, null);
    });

    $(".plus").click(function(){
        var $dest = $(this).parent("span").children(".buyNumber");
        var value = parseInt($dest.val()) + 1;
        var maxNum = $dest.attr("max");
        if(value >= maxNum) value = maxNum;
        $dest.val(value);

        //    處理金額
        var $item = $(this).parents("tr");
        $item.find("input:checkbox").prop("checked", true);
        countPrice(false, null);
    });

    $(".removeItem").click(function (){
        var inventoryId= $(this).parents("tr").find("input:checkbox").attr("value");
        removefromSession(parseInt(inventoryId));
        $(this).parents("tr").detach();
        countPrice(false,null);
        console.log($("tbody").children("tr").length);
        console.log($("tbody").children("tr"))
        if ($("tbody").children("tr").length == 3){
            location.reload();
        }
    });

    function removefromSession(inventoryId){
        $.ajax({
            url: "/shop/cart/"+inventoryId,
            data: {
                inventoryId: inventoryId
            },
            type: "delete",
            success: function (){},
            error: function (error){
                console.log(error);
            }
        });
    }

    $("#addAll").click();

});