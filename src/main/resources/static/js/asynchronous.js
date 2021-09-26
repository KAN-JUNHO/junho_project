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
            $('#first').append("<li> intput = " +data.cnt +"  "+ data.content +"  "+ data.sender +"  "+ "</li>" )

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
            console.log(data)
            $('#second').append("<li> intput = " +data.cnt +"  "+ data.content +"  "+ data.sender + "</li>" )

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

    })
}


$(function(){
    $("#stop").on('click',stop);
})

function stop(){
    $.ajax({
        url:'thread/all'
        , method : 'POST'
    })
}

$(function(){
    $("#view").on('click',view);
})
function view(){
    $.ajax({
        url:'view'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        , success : function(data){
            console.log(data)
            $('#show').append("<li> " +data + "</li>" )

        }
    })
}
