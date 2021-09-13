$(function (){
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
        var $item_li = $(this).parents("li");
        $item_li.css({
            borderColor: "#C3A789"
        });
        $item_li.find(".add_cart").css({
            background: "#C3A789",
            color : "#fff"
        });
        var id = $item_li.attr("value");
        var img_src = $item_li.find("img").attr("src");
        var color = $item_li.find(".size_select").children("option:selected").text();
        var number = $item_li.find(".quantity_select").children("option:selected").val();

        var $dest = $(".cart_bottom").children("ul");
        if (number != '數量'){
            var $li = $("<li class='cart_list'></li>");
            var $img = $("<img src='"+img_src +"'>");
            var $p = $("<p>" +color +" -"+ number +"件</p>");
            $li.append($img).append($p);
            $dest.append($li);
        }

    });

    $(".cart_bottom").children("ul").delegate("p", "click",
        function (){
            $(this).parents("li").remove();
        });
});