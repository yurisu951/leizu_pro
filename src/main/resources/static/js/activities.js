$(function (){
    var promo_total = 0;
    var ori_total = 0;
    var total = 0;
    $(".size_select").change(function (){
        var $item = $(this).children("option:selected");

        $.ajax({
            url: "/get/inventory",
            type: "post",
            data: {
                id: $item.val()
            },
            success: function (data){
                var maxNum = (data < 20) ? data : 20;
                var $select = $item.parents("li").find(".quantity_select");
                $select.children("option").detach();
                for(var i = 1; i <= maxNum; i++){
                    var $option = $("<option value='"+ i +"'>"+ i +"</option>");
                    $select.append($option);
                }
            },
            error: function (error){
                console.log(error);
            }
        });
    });

    $(".add_cart").click(function (){
        var $dest = $(".cart_bottom").children("ul");
        var dest_num = $dest.children().length;
        var $item_li = $(this).parents("li");
        var inven_id = $item_li.find(".size_select").children("option:selected").val();
        var img_src = $item_li.find("img").attr("src");
        var color = $item_li.find(".size_select").children("option:selected").text();
        var number = parseInt($item_li.find(".quantity_select").children("option:selected").val());
        var ori_price = parseInt($item_li.find("del").text().substring(3).trim());
        var promo_price = parseInt($item_li.children("p").children("span").text().substring(6).trim());

        if(dest_num > 0){
            var $array = $dest.children("li");
            for(var i = 0; i< $array.length;i++){
                //  重复的inventory， 购物车重新赋值数量
                if (inven_id === $array.eq(i).attr("value")){
                    var $li_before_change = $array.eq(i);
                    var before_num = $li_before_change.find("p").text().split("-")[2].trim();
                    before_num = parseInt(before_num.substring(0,before_num.indexOf("件")));
                    var changeNum = number - before_num;

                    changeCartNumber(changeNum, ori_price, promo_price);

                    removeFromSession(inven_id);
                    addIntoSession(inven_id, number);

                    $li_before_change.find("p").text(color +" -"+ number +"件");
                    return;
                }
            }
        }


            $item_li.addClass("select");
            if (number != '數量'){
                var $li = $("<li class='cart_list'></li>");
                var $img = $("<img src='"+img_src +"'>");
                var $p = $("<p value='"+ori_price + "," + promo_price +"'>" +color +" -"+ number +"件</p>");
                $li.attr("value", inven_id);
                $li.append($img).append($p);
                $dest.append($li);

                changeCartNumber(number, ori_price, promo_price);
                addIntoSession(inven_id, number);
            }

    });

    function changeCartNumber(number, ori_price,  promo_price){
        // 算錢區
        promo_total += promo_price * number;
        ori_total += ori_price * number;
        total += number;
        $("#cart_origin_price").text(ori_total);
        $("#cart_discount_price").text(promo_total);
        $("#cart_selected_number").text(total);
    }

    function addIntoSession(inventoryId, quantity){
        // 添加到Session裡面
        var product_cart = {
            id: inventoryId,
            buyNumber: quantity +''
        };
        $.ajax({
            url: "/shop/cart",
            type: "post",
            data: {product:JSON.stringify(product_cart)},
            success: function (data){
                console.log("add success");
                // console.log(data);
            },
            error: function (error){
                console.log(error);
            }
        });
    }

    function removeFromSession(inventoryId){
        $.ajax({
            url: "/shop/cart/"+inventoryId,
            type: "DELETE",
            data: {inventoryId: inventoryId},
            contentType:"application/json",
            success: function (data){
                console.log("delete success");
                console.log(data);
            },
            error: function (error){
                console.log(error);
            }
        });
    }



    $(".cart_bottom").children("ul").delegate("p", "click",
        function (){
            var $item_remove = $(this).parents("li");
            var id = $item_remove.attr("value");
            var $arr = $(".product_section").find("li[class*=select]");
            for (var i = 0; i < $arr.length; i++){
                if($arr.eq(i).attr("value") === id ){
                    $arr.eq(i).removeClass("select");
                }
            }

            // 算錢區
            var array_info_num = $item_remove.find("p").text().split("-")[2];
            var array_price = $item_remove.find("p").attr("value").split(",");
            var num = parseInt(array_info_num.substring(0, array_info_num.indexOf("件")));
            var ori_price = parseInt(array_price[0]);
            var promo_price = parseInt(array_price[1]);
            changeCartNumber(-num, ori_price, promo_price);

            $(this).parents("li").remove();
        });
});