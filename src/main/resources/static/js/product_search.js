$(function (){
    var index = 0
    var $dest =  $(".product_section > ul");
    $(window).scroll(function (){
        if (list != null){
            if (maxPage > 0){
                if ($(document).scrollTop() >= $(document).height() - $(window).height()){
                    index++;
                    $.ajax({
                        url: "/load/search",
                        type: "post",
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify({
                            "productList" : list,
                            "maxPage" :maxPage,
                            "index": index
                        }),
                        success: function (data){
                            for (const product of data) {
                                var gender = ((product["productInfo"]["category"] +'').substring(0,1) === '1')? 'men':'women';
                                var productId = product["productId"];
                                var color = product["productColor"];
                                var img = product["productImage"]
                                var productName = product["productInfo"]["productName"];
                                var productPrice = product["productInfo"]["price"];
                                var productPromo = product["productInfo"]["promoPrice"];


                                var $li = $("<li></li>");
                                var $a = $('<a href="/'+gender + '/details/'+ productId +'/' +color +'"></a>')
                                var $img = $('<img src="'+img+'" title="'+ productName +'">');
                                var $pSize = $('<p><span>S</span><span>M</span><span>L</span></p>');
                                var $h3 = $('<h3>'+productName+'</h3>');
                                var $p = '';
                                if (productPromo != null){
                                    $p = $('<p>NT$'+productPrice+' 活動價 NT$'+productPromo+'</p>');
                                } else {
                                    $p = $('<p>NT$'+productPrice+'</p>');
                                }
                                $a.append($img).append($pSize);
                                $li.append($a).append($h3).append($p);
                                $dest.append($li);
                            }
                        },
                        error: function (error){
                            console.log(error);
                        }
                    });
                }
            }

        } else {
            if (keyWords === null || keyWords ===''){
                if ($(document).scrollTop() >= $(document).height() - $(window).height()){
                    index++;
                    $.ajax({
                        url: "/load/search/r",
                        type: "post",
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify({
                            "gender" :gender,
                        }),
                        success: function (data){
                            for (const product of data) {
                                var gender = ((product["productInfo"]["category"] +'').substring(0,1) === '1')? 'men':'women';
                                var productId = product["productId"];
                                var color = product["productColor"];
                                var img = product["productImage"]
                                var productName = product["productInfo"]["productName"];
                                var productPrice = product["productInfo"]["price"];
                                var productPromo = product["productInfo"]["promoPrice"];


                                var $li = $("<li></li>");
                                var $a = $('<a href="/'+gender + '/details/'+ productId +'/' +color +'"></a>')
                                var $img = $('<img src="'+img+'" title="'+ productName +'">');
                                var $pSize = $('<p><span>S</span><span>M</span><span>L</span></p>');
                                var $h3 = $('<h3>'+productName+'</h3>');
                                var $p = '';
                                if (productPromo != null){
                                    $p = $('<p>NT$'+productPrice+' 活動價 NT$'+productPromo+'</p>');
                                } else {
                                    $p = $('<p>NT$'+productPrice+'</p>');
                                }
                                $a.append($img).append($pSize);
                                $li.append($a).append($h3).append($p);
                                $dest.append($li);
                            }
                        },
                        error: function (error){
                            console.log(error);
                        }
                    });
                }

            }
        }
    });
});