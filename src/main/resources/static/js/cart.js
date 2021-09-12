var colorIdAndName;
var sizeId;
var quantity;
var product_cart;

$(function(){
    $("#addCart").click(function (){
        colorIdAndName = $(".color").find(".current").attr("value");
        sizeId = $(".size").find(".current").attr("value");
        quantity = $("#buyNumber").val();
        product_cart = {
            id: colorIdAndName.substr(0,7) + sizeId,
            buyNumber: quantity
        };

        $.ajax({
            url: "/shop/cart",
            type: "post",
            data: {product:JSON.stringify(product_cart)},
            success: function (data){
                console.log("add success");
                console.log(data);
            },
            error: function (error){
                console.log(error);
            }
        });
    });
});