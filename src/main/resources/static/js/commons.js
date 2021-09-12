

$(function (){
    $("#cart_info").mouseenter(function (){
        var $table_structure = $("<table></table>");
        var $table_head = $("<tr><th>商品名稱</th><th>顏色</th><th>尺寸</th><th>數量</th></tr>");
        var $table_end = $('<tr class="last_row"><td colspan="4"><a href="#">前往結帳</a></td></tr>');

        $.ajax({
            url: "/shop/getCart",
            success: function (data){
                if ($.isEmptyObject(data)){
                    var $table_body = $('<tr class="nothing_row"><td colspan="4">購物車裡面什麼都沒有</td></tr>');
                    $table_structure.append($table_head);
                    $table_structure.append($table_body);
                    $("#cart_list").html($table_structure);
                    return;
                }
                var productList = data["productList"];
                $table_structure.append($table_head);

                for (const index in productList) {
                    var product = productList[index];
                    var productName = product["productName"];
                    var quantity = product["buyNumber"];
                    var color = product["thisProductInfo"]["colorName"];
                    var size = product["thisProductInfo"]["size"];

                    var $table_body_li = $('<tr><td>' + productName +'</td><td>'+ color +
                        '</td><td>'+ size+'</td><td>' + quantity +'</td></tr>');

                    $table_structure.append($table_body_li);
                }
                $table_structure.append($table_end);
                $("#cart_list").html($table_structure);
            },
            error:function (error){

                console.log(error);
            }
        });
    });


    $(".toUp").click(function(){
        $("html, body").animate({scrollTop:0}, 500, function(){
            $(".toUp").fadeOut();
        });
    });

    $(window).scroll(function(){
        var top = $(this).scrollTop();
        if(top > 600){
            $(".toUp").fadeIn();
        }
    });

    $(".sub_nav").find("li").hover(function (){
        $(this).find(".sub_sub_nav").stop().fadeToggle(300);
    });
});