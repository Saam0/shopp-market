document.addEventListener('DOMContentLoaded', function () {

// -----------------------------------Menu-----------------------------------
    function toggleMenu() {
        $('.menu-toggle').toggleClass('menu-toggle_active')
        $('.top-menu').toggleClass('top-menu_active')
        $('.language__container').toggleClass('language__container_active')
    }

    $('.menu-toggle').click(function () {
        toggleMenu()
    })

    function closeMenu() {
        $('.menu-toggle').removeClass('menu-toggle_active')
        $('.top-menu').removeClass('top-menu_active')
    }

    $(document).click(function(e) {
        if ($(e.target).closest('.menu-container').length) return
        closeMenu()
    })

})

$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption !== ''){
            window.location.replace('?lang=' + selectedOption);
        }
    });
});

//Humberger Menu
$(".humberger__open").on('click', function () {
    $(".humberger__menu__wrapper").addClass("show__humberger__menu__wrapper");
    $(".humberger__menu__overlay").addClass("active");
    $("body").addClass("over_hid");
});

$(".humberger__menu__overlay").on('click', function () {
    $(".humberger__menu__wrapper").removeClass("show__humberger__menu__wrapper");
    $(".humberger__menu__overlay").removeClass("active");
    $("body").removeClass("over_hid");
});
