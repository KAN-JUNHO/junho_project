//클라이언트 쪽
$(function(){
    $('#plus').on('click',plus);
})

function plus() {
    $.ajax({
        url:'plus'
        , method : 'POST'
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