$(function () {

    $.ajax({
        url: 'http://localhost:10010/course/getAllCourses',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        dataType: "json",
        data: {},
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            showCourses(data);
        }
    });

    searchCourses();
    addCourse();
});

function searchCourses() {

    $("#searchForm").submit(function(){
        $(this).ajaxSubmit({
            url: 'http://localhost:10010/course/searchCourses',
            method: 'POST',     // 请求类型，常用的有 GET 和 POST
            dataType: "json",
            success: function(data) { // 接口调用成功回调函数
                // data 为服务器返回的数据
                showCourses(data);
            }
        });
        return false;
    });

}

function showCourses(data) {
    var content = "";

    for(var i = 0;i < data.length;i++){

        var introduction = data[i]["introduction"];
        if(introduction==null||introduction.length==0){
            introduction = "无.";
        }

        var marginLeft = "";
        if(i%3!=0){
            marginLeft = "margin-left: 5%;";
        }

        content+="<a class='course_a' href=\"http://localhost:10009/courseDetail?courseID="+data[i]["id"]
            +"\"><div class=\"card course_card\" style=\"width:302px;margin-top:5%;display: inline-block;"+marginLeft+"\">\n" +
            "        <img class=\"card-img-top\" src=\"img/41.jpg\" alt=\"Card image cap\" width=\"300\" height=\"170\"/>\n" +
            "        <div class=\"card-block\">\n" +
            "            <h4 class=\"card-title\">"+data[i]["name"]+"</h4>\n" +
            "            <p class=\"card-text\">简介："+introduction+"</p>\n" +
            "            <a href=\"http://localhost:10009/courseDetail?courseID="+data[i]["id"]+"\" class=\"btn btn-primary\">查看详情</a>\n" +
            "            <a class=\"btn btn-danger\" onclick='deteleCourse("+data[i]["id"]+")'>删除</a>\n" +
            "        </div>" +
            "    </div></a>";
    }

    content+="<a title='添加课程' class=\"card course_card\" onclick='showBox()' " +
        "style=\"width:300px;height:300px;margin-left: 5%;margin-top:5%;display: inline-block;\">\n" +
        "        <img src=\"img/add_ico.png\" width=\"80\" height=\"80\" style='margin-left:105px;margin-top:110px;'/>\n" +
        "    </a>";

    document.getElementById("coursesPanel").innerHTML = content;
}

function deteleCourse(courseID) {

    $.ajax({
        url: 'http://localhost:10010/course/deleteCourse',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        // dataType: "json",
        data: {courseID:courseID},
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            alert("删除成功");
            location.reload();
        }
    });
}

function addCourse() {

    $("#addCourseForm").submit(function(){
        $(this).ajaxSubmit({
            url: 'http://localhost:10010/course/addCourse',
            method: 'POST',     // 请求类型，常用的有 GET 和 POST
            // dataType: "json",
            async:false,
            beforeSubmit:checkCourseName,
            success: function(data) { // 接口调用成功回调函数
                // data 为服务器返回的数据
                alert("添加成功！");
                location.reload();
            }
        });
        return false;
    });
}

function checkCourseName() {
    var coursename = $("#addcoursename").val();
    var isExist;

    $.ajax({
        url: 'http://localhost:10010/course/isCourseNameExist',
        type: 'POST',     // 请求类型，常用的有 GET 和 POST
        // dataType: "json",
        data: {coursename:coursename},
        async:false,
        success: function(data) { // 接口调用成功回调函数
            // data 为服务器返回的数据
            isExist = data;

        }
    });

    if(isExist == true){
        document.getElementById("addcourse_error").style.display = "block";
        return false;
    }else{
        document.getElementById("addcourse_error").style.display = "none";
        return true;
    }
}

function removeError() {
    document.getElementById("addcourse_error").style.display = "none";
}