
// var answerList={};
var qStatus={};
var sid = $.session.get('sid');
var testID = $.session.get('testID');
// var questions;

$(function(){

    // questions=[{"qid":18,'question':'设某无向图有n个顶点，则该无向图的邻接表中有（ ）个表头结点',
    //     'list':[{"selection":'2n',"optOrder":"A"},{"selection":'n',"optOrder":"B"},
    //         {"selection":'n/2',"optOrder":"C"}],'score':3},
    //     {"qid":21,'question':'设一组初始记录关键字序列为(13，18，24，35，47，50，62，83，90，115，134),则利用二分法查找关键字90需要比较的关键字个数为（ ）',
    //         'list':[{"selection":'1',"optOrder":"A"},{"selection":'2',"optOrder":"B"},
    //             {"selection":'3',"optOrder":"C"}],'score':4}];

    $.ajax({
        url: 'http://localhost:10005/getTest',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        dataType: "json",
        data: {studentID:sid,testID:parseInt(testID)},
        async:false,
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            // questions = data;
            initQuestions(data);
            initProgress(data.length);
            // alert(data);
        }
    });

    $.ajax({
        url: 'http://localhost:10006/duration',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        // dataType: "json",
        data: {testID:parseInt(testID)},
        async:false,
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            // alert(data);
            setColock(data);
        }
    });

    // setColock('2017/12/13 18:00:00');
    // initQuestions(questions);
    // initProgress(questions.length);

    // var slidesList = $("#quiz-container").find('.slide-container');
    // slidesList.hide().first().fadeIn(300);

    chooseSelection();
    progress_td_hover();

    next();
    prev();
    mark();

    $("#quiz-container").find('.final').click(function() {
        if(confirm('确定交卷?'))
        {
            submit();
            return true;
        }
        return false;
    });
});

function setColock(endtime) {
    $('#clock').countdown(endtime, function(event) {
        $(this).html("距离考试结束还有  "+event.strftime('%H:%M:%S'));

        if(event.strftime('%H:%M:%S')=='00:00:00'){
            submit();
        }
    });
}

function initQuestions(questions) {
    var content = "";

    for(var qindex = 0;qindex<questions.length;qindex++){
        content+="<div class='slide-container' data-qid='"+questions[qindex]['qid']+"'>" +
            "<div class='question_content'><a class='q_mark_a' data-qindex='"+qindex+"'><span class=\"glyphicon glyphicon-star-empty\" style='color: #FCDB65;'></span></a>"+
            (qindex+1)+". "+questions[qindex]['question']+ " ("+questions[qindex]['score']+"分)</div>" +
            "<div style='margin-top: 10px;margin-left: 52px;'>";

        var answers = questions[qindex]['list'];
        for(var aindex = 0;aindex<answers.length;aindex++){

            content+="<p class='selection_content'><input type=\"checkbox\" value='"+answers[aindex]['optOrder']
                +"' data-qid='"+qindex+"'/>"
                +" "+answers[aindex]['optOrder']+". "+answers[aindex]['selection']+"</p>";
        }

        content+= "</div><div style='margin-top: 5%;'>";

        if(qindex>0){
            content+="<button class=\"prev btn btn-primary\">上一题</button>";
        }

        if(qindex<questions.length-1){

            content+="<button class=\"next btn btn-primary\">下一题</button>";
            content+="</div>";
            content+="<button class=\"final btn btn-danger\" style='width:12%;margin-left: 88%;margin-top: 3%;'>交卷</button>";

        }else{

            content+="<button class=\"final btn btn-danger\" style='width:12%;float: right;'>交卷</button>";
            content+="</div>";
        }

        content+="</div>";
    }

    $("#quiz-container").html(content);

    var slidesList = $("#quiz-container").find('.slide-container');
    slidesList.hide().first().fadeIn(300);
}

function initProgress(qnum) {
    var content = "";

    for(var i = 0;i<qnum;i++){
        if(i%3==0){
            content+="<tr>";
        }

        content+="<td onclick='jumpToQuestion("+i+")' class='progress_td'>"+(i+1)+"</td>";

        if(i%3==2){
            content+="</tr>";
        }
    }

    $("#progress_table").html(content);
}

function next() {
    $("#quiz-container").find('.next').click(function() {

        $(this).parents('.slide-container').fadeOut(300,
            function() {
                $(this).next().fadeIn(300);
            });
        return false;
    });
}

function prev() {
    $("#quiz-container").find('.prev').click(function() {

        $(this).parents('.slide-container').fadeOut(300,
            function() {
                $(this).prev().fadeIn(300);
            });
        return false;
    });
}

function submit() {

    var data = {};
    var qList = $("#quiz-container").find(".slide-container");
    for(var i = 0;i<qList.length;i++){
        var qid = qList.eq(i).data("qid");
        var as = "";

        var sList = qList.eq(i).find(':checkbox:checked');
        for(var j = 0;j<sList.length;j++){
            as+=sList.eq(j).val();
        }
        data[parseInt(qid)] = as;
    }

    $.ajax({
        url: 'http://localhost:10007/submit',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        // dataType: "json",
        data: {testID:parseInt(testID),sid:sid,data:data},
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            sendEmail(data);
            window.location.href = "http://localhost:10009/submit";
        }
    });
}

function sendEmail(score) {
    $.ajax({
        url: 'http://localhost:10002/mail/score',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        // dataType: "json",
        data: {sid:sid,tid:parseInt(testID),score:score},
        async:false,
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
        }
    });
}

function chooseSelection() {
    var status_tList = $("#progress_table").find(".progress_td");

    $("#quiz-container").find(':checkbox').click(function() {
        var qindex = $(this).data("qid").toString();
        var tmpList = $(this).parents('.slide-container').find(':checkbox:checked');

        if(qStatus[qindex]!=2){
            if(tmpList.length==0){
                qStatus[qindex]=0;
                status_tList.eq(qindex).css('background-color', '#FFFFFF');

            }else{
                qStatus[qindex]=1;
                status_tList.eq(qindex).css('background-color', '#5bc0de');
            }
        }
    });
}

function mark() {

    $(".q_mark_a").click(function () {
        var qindex=$(this).data("qindex");
        var status_td = $("#progress_table").find(".progress_td").eq(qindex);

        if(qStatus[qindex]!=2){

            qStatus[qindex]=2;
            status_td.css('background-color', '#fff973');

            $(this).find('>span').removeClass("glyphicon-star-empty");
            $(this).find('>span').addClass("glyphicon-star");

        }else{

            if($(this).parents('.slide-container').find(':checkbox:checked').length==0){
                qStatus[qindex]=0;
                status_td.css('background-color', '#FFFFFF');

            }else{
                qStatus[qindex]=1;
                status_td.css('background-color', '#5bc0de');
            }

            $(this).find('>span').removeClass("glyphicon-star");
            $(this).find('>span').addClass("glyphicon-star-empty");
        }
    });
}

function jumpToQuestion(qid) {
    var slidesList = $("#quiz-container").find('.slide-container');

    slidesList.hide();
    slidesList.eq(qid).fadeIn(300);
}

function progress_td_hover() {

    $("#progress_table").find(".progress_td").hover(function() {
        var qindex = parseInt($(this).text())+parseInt(-1);

        if(qStatus[qindex]==1){

            $(this).css('background-color', '#2da7de');

        }else if(qStatus[qindex]==2){

            $(this).css({'background-color': '#ffea5a'});
        }else{
            $(this).css({'background-color': 'rgba(225,225,225,0.5)'});
        }
    }, function(){
        var qindex = parseInt($(this).text())+parseInt(-1);

        if(qStatus[qindex]==1){

            $(this).css('background-color', '#5bc0de');

        }else if(qStatus[qindex]==2){

            $(this).css('background-color', '#fff973');
        }else{
            $(this).css('background-color', '#FFFFFF');
        }
    });
}