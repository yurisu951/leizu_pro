

$(function (){
    var $dest =  $(".product_section > ul");
    $(window).scroll(function (){
        if ($(document).scrollTop() >= $(document).height() - $(window).height()){
            $.ajax({
                url: "/load/" + gender,
                type: "post",
                success: function (data){
                    for (const index in data) {
                        var product = data[index];
                        var $li = $('<li></li>');
                        var url = `/${gender}/details/${product.id}`;
                        var $a = $('<a href="'+ url+'"></a>');
                        var $img = $('<img src="'+ product["image"]+'" alt="'+ product["productName"] +'" title="'+ product["productName"] +'">');
                        var $h3 = $('<h3>'+ product["productName"]  +'</h3>');
                        var $p;
                        if (product["promo"] == null){
                            $p = $('<p>NT$ '+ product["price"] +'</p>');
                        } else {
                            $p = $('<p><del>NT$'+ product["price"] +'</del><span>活動價NT$ '+ product["promoPrice"] +'</span></p>');
                        }
                        $a.append($img);
                        $li.append($a).append($h3).append($p);
                        $dest.append($li);
                    }
                    height = $("html body").innerHeight() - 450;
                },
                error: function (error){
                    console.log(error);
                }

            });
        }
    });
});