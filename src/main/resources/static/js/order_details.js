$(function (){
    function sumPrice(){
        var $arrTr = $("tbody").find("tr");
        var priceTotal = 0;
        var ordertotal = 0;

        for (var i = 0; i<$arrTr.length; i++){
            var price = $arrTr.eq(i).children("td").eq(6).text().trim();
            if (isNaN(parseInt(price))){
                price = 0;
            }
            if ((price+'').includes(',')){
                price = price.replaceAll(',','');
            }

            priceTotal += parseInt(price);
        }
        ordertotal = priceTotal + 60;
        $("#priceTotal").text('NT$ '+priceTotal.toLocaleString());
        $("#ordertotal").text('NT$ '+ordertotal.toLocaleString());
    }
    sumPrice();

});