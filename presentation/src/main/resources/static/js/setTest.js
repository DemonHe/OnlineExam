$(function () {

    $.ajax({
        url: 'http://localhost:10003/test/getCourseNames',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        dataType: "json",
        data: {},
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            var content = "";
            var i;
            for(i = 0;i < data.length;i++){
                content+="<option>"+data[i]+"</option>";
            }
            document.getElementById("coursename").innerHTML = content;
        }
    });
    addTest();
});

function generateScoreSet() {

    var qnum = document.getElementById("qnum").value;

    if(qnum>0){

        var content = "";

        for (var i = 1;i<=qnum;i++){

            content+="<div class=\"form-group\" style=\"width: 70%;\">" +
                        "<label class=\"col-sm-2 control-label testSet_label\">第"+i+"题：</label>\n" +
                        "<div class=\"col-sm-7\">" +
                            "<input type='number' class=\"form-control\" name='scoreof"+i +
                            "' id='scoreof"+i+"' required/>" +
                        "</div>" +
                    "</div>";

        }

        document.getElementById("detailscore").innerHTML = content;

    }else{

        document.getElementById("detailscore").innerHTML = "";

    }

}

function addTest(){

    $("#testSet").submit(function(){
        $(this).ajaxSubmit({
            url: 'http://localhost:10003/test/setTest',
            method: 'POST',     // 请求类型，常用的有 GET 和 POST
            //dataType: "jsonp",
            data: $("#testSet").serialize(),
            async: false,
            success: function(data) { // 接口调用成功回调函数
                // data 为服务器返回的数据
                if(parseInt(data)==-1){
                    alert('试题数量不足！');
                }else
                    uploadStudentsList(data);
            }
        });
        return false;
    });

    // $.ajax({
    //     url: 'http://localhost:10003/test/setTest',
    //     method: 'POST',     // 请求类型，常用的有 GET 和 POST
    //     //dataType: "jsonp",
    //     data: $("#testSet").serialize(),
    //     xhrFields: {
    //         withCredentials: true
    //     },
    //     crossDomain: true,
    //     async: false,
    //     success: function(data) { // 接口调用成功回调函数
    //         // data 为服务器返回的数据
    //         alert(data);
    //        // uploadStudentsList(data);
    //     }
    // });

}

function uploadStudentsList(testID) {
    var formData = new FormData();
    formData.append('file', $('#slist')[0].files[0]);
    formData.append("testID",testID);

    $.ajax({
        url: 'http://localhost:10004/excel/importStudents',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,

        success: function(data) {

            alert("设置成功");
            window.location.href = "http://localhost:10009/addTest";

        }
    });

}