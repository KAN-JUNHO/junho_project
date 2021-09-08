$("#plus").click(function(){
    // ajax 통신
    var Count = ${"form"}.serialize();

    $.ajax({
        type : "POST",            // HTTP method type(GET, POST) 형식이다.
        url : "./",      // 컨트롤러에서 대기중인 URL 주소이다.
        data : Count,            // Json 형식의 데이터이다.
        success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
            alert(res.code);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.")
        }
    });
});

//function up(){
//    var inputed = $('#plus').val();
//    $.ajax({
//        url : "./index.html"
//        , type : "POST"
//        , dataType : "json"
//        , data : {
//            plus : inputed
//        }
//        , success : function(data) {
//
//        }
//        , error : function() {
//            alert('서버 통신 실패');
//        }
//    })
//}
//function up(var cnt){
//    $.ajax({
//          url: './index.html',
//          method: 'POST',
//          contentType: "application/json",
//          dataType: "application/json",
//          data: JSON.serialize({cnt})
//          success: function(data){
//                $('#message').append(data);
//                alert("sucess");
//          },
//          error: function(response){
//            alert("string err");
//            console.error(response);
//          }
//      })
//}