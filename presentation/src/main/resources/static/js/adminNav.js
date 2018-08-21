function chooseActive(element){
    $(element).children().css("color","#fff");
    $('#selectNav').children().click(function () {
        $(this).parent().children().children().css("color","#ccc");
        $(this).children().css("color","#fff");
    });
}