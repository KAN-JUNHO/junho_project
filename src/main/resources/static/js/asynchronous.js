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

        , success :  function(data){
            console.log(data)
            $('#show').append("<li> 전송받은 데이터 : " +data.cnt +"  "+ data.content +"  "+ data.sender +"  "+ data.type  + "</li>" )

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
            $('#show').append("<li> 전송받은 데이터 : " +data.cnt +"  "+ data.content +"  "+ data.sender +"  "+ data.type  + "</li>" )

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
        , success : function(data){

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
        , success : function(data){

        }
    })
}


$(function(){
    $("#view").on('click',view);
})

function view(){
    $.ajax({
        url:'view'
        , method : 'POST'
        // , contentType : "application/json"
        // , dataType:"json"
        // , data: JSON.stringify({})
        , success : function(data){

            console.log(data)
            $('#show').append("<li> 전송받은 데이터 : " + "</li>" )

        }
    })
}
