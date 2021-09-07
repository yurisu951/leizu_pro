$(function(){

    var array = ["../images/uploads/index_box/52265_2PSALE_1180X630_210325_1P330_TW.jpeg", "../images/uploads/index_box/52397_1P299_1180X630_210607_299_TW.jpeg",
        "../images/uploads/index_box/52555_1P390_1180X630_210825_TW.jpeg", "../images/uploads/index_box/53403_2PSALE_1180X630_210824_TW.jpeg",
        "../images/uploads/index_box/54224_COTTON_1180X630_210817_TW.jpeg", "../images/uploads/index_box/54243_54860_DISNEY_1180X630_210810a_TW.jpeg",
        "../images/uploads/index_box/54294_2PSALE_1180X630_210824_TW.jpeg"]

    //  创建轮播图的li标签
    for (i = 0; i < array.length; i++) {
        var img = '<img src="' + array[i] + '" alt="">';
        var $li = '<li><a href="#">' + img + '</a></li>';
        $(".box_section").append($li);

        // 配置小圆点
        $li = "<li></li>";
        $(".box_dot").append($li);
    }

    var first = $(".box_section").children().eq(0).clone(true);
    $(".box_section").append(first);

    $(".box_dot").children().eq(0).addClass("current");
    $(".box_dot").delegate("li", "click", function(){
        index = $(this).index();
        changeImage();

    })


    var flag = true;

    var index = 0;
    var maxIndex = $(".box_section").children().length;
    var boxWidth = $(".top_box").outerWidth();

    // 设置ul宽度
    $(".box_section").width(maxIndex * boxWidth + 'px');


    function backImage() {
        if (index === 0) {
            index = maxIndex - 1;
            $(".box_section").css({
                left: -(boxWidth * index) + 'px'
            });

        }
        index--;
        changeImage();
    }

    function nextImage() {
        if (flag) {
            if (index >= maxIndex - 1) {
                index = 0;
                $(".box_section").css({
                    left: 0 + 'px'
                });
            }
            index++;
            changeImage();
        }
    }

    function changeImage(){
        $(".box_section").stop().animate({
                left: -(boxWidth * index) + 'px'
            });
            if (index >= maxIndex - 1) {
            $(".box_dot").children().removeClass("current").eq(0).addClass("current");
            } else {
                $(".box_dot").children().removeClass("current").eq(index).addClass("current");
            }
    }


    $(".box_back").click(function(){
        flag = true;
        backImage();
    });
    $(".box_next").click(function(){
        flag =true;
        nextImage();
    });

    var timer = setInterval(nextImage, 5000);



});