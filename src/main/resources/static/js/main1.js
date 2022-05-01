$(function () {

    $('.slider__recommended,.slider__new,.slider__popular').slick({
        infinite: true,
        dots: false,
        arrow: false,
        slidesToShow: 5,
        slidesToScroll: 3,
        autoplay: true,
        autoplaySpeed: 10000,
        speed: 1000,
        // appendArrows: $('.slider__recommended__arrow'),
        responsive: [
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 2,
                }
            },
            {
                breakpoint: 660,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                }
            }
        ],
        // prevArrow: "<button type='button' class='slider__recommended slick-prev slick-arrow '><i class='fa-solid fa-angle-left'></i></button>",
        // nextArrow: "<button type='button' class='slider__recommended slick-next slick-arrow '><i class='fa-solid fa-angle-right '></i></button>",
    });

    // for Recommended bloc
    $('.slider__recommended__arrows>.btn__l').click(function (event) {
        $('.slider__recommended').slick('slickPrev');
    })
    $('.slider__recommended__arrows>.btn__r').click(function (event) {
        $('.slider__recommended').slick('slickNext');
    })

    // for New bloc
    $('.slider__new__arrows>.btn__l').click(function (event) {
        $('.slider__new').slick('slickPrev');
    })
    $('.slider__new__arrows>.btn__r').click(function (event) {
        $('.slider__new').slick('slickNext');
    })


    // for Popular bloc
    $('.slider__popular__arrows>.btn__l').click(function (event) {
        $('.slider__popular').slick('slickPrev');
    })
    $('.slider__popular__arrows>.btn__r').click(function (event) {
        $('.slider__popular').slick('slickNext');
    })

})


/*-------------------
 for change language
--------------------*/
$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption !== '') {
            window.location.replace('?lang=' + selectedOption);
        }
    });
});


/*---------------
 for select card
----------------*/

$(function () {
    $('.click').on('click', function (e) {
        if (e.target.tagName == 'A') return true;
        else if (e.target.tagName == 'BUTTON') return true;
        else if (e.target.tagName == 'I') return true;
        else {
            window.location.href = $(e.currentTarget).attr('data-url');
        }
        return false;
    });
});

