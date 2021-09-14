//클라이언트 쪽
$(function(){
    $('#plus').on('click',plus);
})
function plus() {
    $.ajax({
        url:'plus'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        // , data:JSON.stringify(username)
        , success :  function(data){
            $('#req1').text("전송받은 데이터 : " + data)

        }
    })
}

$(function(){
    $("#minus").on('click',minus);
})

function minus(){
    $.ajax({
        url:'minus'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        // , data:JSON.stringify(username)
        , success :  function(data){
            $('#req2').text("전송받은 데이터 : " + data)

        }
    })
}

$(function(){
    $("#create").on('click',create);
})

function create(){
    $.ajax({
        url:'thread/create'
        , method : 'POST'
        , success : function(resp){
            if(resp=="create"){
                $('#req3').text("전송받은 데이터 : " + resp)
            }
        }
    })
}


$(function(){
    $("#all").on('click',all);
})

function all(){
    $.ajax({
        url:'thread/all'
        , method : 'POST'
        , success : function(resp){
            if(resp=="all"){
                $('#req4').text("전송받은 데이터 : " + resp)
            }
        }
    })
}