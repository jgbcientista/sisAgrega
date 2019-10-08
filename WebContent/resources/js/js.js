//Layout Fixes 
jQuery(function($) {
    $(".font-more").click(function () {
        var size = $("body").css('font-size');
        size = size.replace('px', '');
        size = parseInt(size) + 1.4;
        $("body").animate({'font-size' : size + 'px'});
    });

    $(".font-min").click(function () {
        var size = $("body").css('font-size');
        size = size.replace('px', '');
        size = parseInt(size) - 1.4;
        $("body").animate({'font-size' : size + '14px'});
    });
    $(".font-reset").click(function () {
        $("body").animate({'font-size' : 'inherit'});
    });
    
    $('input, textarea').addClass('input-block-level');
    
});