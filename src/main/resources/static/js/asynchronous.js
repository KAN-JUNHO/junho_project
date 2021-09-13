//클라이언트 쪽
$(function(){
    $('#plus').on('click',plus);
    var username={username}
})

function plus() {
    $.ajax({
        url:'plus'
        , method : 'POST'
        , contentType : "application/json"
        , data: JSON.stringify({username})
        , success :  function(resp){
            if(resp=="plus"){
                $('#req1').text("전송받은 데이터 : " + resp)
            }
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
        , success : function(resp){
            if(resp=="minus"){
                $('#req2').text("전송받은 데이터 : " + resp)
            }
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