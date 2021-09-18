$(function (){
    $(".color_select").change(function (){
        var $this = $(this);
        var colorId = $this.children("option:selected").val();
        $.ajax({
            url: "/user/keep/inventory/" + colorId +'',
            type : "get",
            success: function (data){
                var $dest = $this.siblings(".size_select");
                $dest.children().remove();
                for (const inventory of data) {
                    var id = inventory["id"];
                    var size = inventory["size"];
                    var $option = $("<option value='"+id+"'>" + size+"</option>");
                    $dest.append($option);
                }
                $this.siblings(".size_select").stop().change();
            },
        error: function (error){
            console.log(error);
        }});
    });

    $(".size_select").change(function (){
        var $this = $(this);
        var inventoryId = $this.children("option:selected").val();
        $.ajax({
            url: "/get/inventory",
            type: "post",
            data: {
                id: inventoryId
            }, success: function (data){
                var $dest = $this.parents("li").find(".quantity_select");
                $dest.children().remove();
                var max = data > 20 ? 20 : data;
                for (var i = 1; i <= max; i++){
                    var $option = $("<option value='"+i+"'>" + i+"</option>");
                    $dest.append($option);
                }
            },error : function (error){
                console.log(error);
            }
        });
    });

    $(".add_cart").click(function (){
        var $this = $(this).parents("li");
        if (!$this.hasClass("selected")){
            var buyNumber = $this.find(".quantity_select").children("option:selected").text();
            if (!isNaN(parseInt(buyNumber))){
                var inventoryId = $this.find(".size_select").val();
                addIntoSession(inventoryId, buyNumber);
                $this.addClass("selected");
            }
        } else {
            var inventoryId = $this.find(".size_select").val();
            removeFromSession(inventoryId);
            $this.removeClass("selected");
        }

    });

    // 添加到Session裡面
    function addIntoSession(inventoryId, quantity){
        var product_cart = {
            id: inventoryId,
            buyNumber: quantity +''
        };
        $.ajax({
            url: "/shop/cart",
            type: "post",
            data: {product:JSON.stringify(product_cart)},
            success: function (data){
            },
            error: function (error){
                console.log(error);
            }
        });
    }

    // 从session移除
    function removeFromSession(inventoryId){
        $.ajax({
            url: "/shop/cart/"+inventoryId,
            type: "DELETE",
            data: {inventoryId: inventoryId},
            contentType:"application/json",
            success: function (data){
            },
            error: function (error){
                console.log(error);
            }
        });
    }

    $(".deleteKeep").click(function (){
        var $this = $(this).parent("li");
        var productId = $this.attr("value");
        $.ajax({
            url: "/user/keep/" +productId,
            data: {productId: productId},
            type: "delete",
            success: function (data){
                if (data === "success")  $this.remove();
            }, error: function (error){
                console.log(error);
            }
        });
    });

});