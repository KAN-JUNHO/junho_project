//클라이언트 쪽
$(function(){
    $('#reqBtn1').on('click',plus);}
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