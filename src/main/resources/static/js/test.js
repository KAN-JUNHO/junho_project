
// $(function(){
//     $('#plus').on('click',plus);
// })
function plus() {
    $.ajax({
        url:'/plus'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        , success :  function(data){
            $('#first').append("<li> intput = " +data.cnt +"  "+ data.content +"  "+ data.sender +"  "+ "</li>" )

        }
    })

}

// $(function(){
//     $("#minus").on('click',minus);
// })

function minus(){
    $.ajax({
        url:'/minus'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        , success :  function(data){
            $('#first').append("<li> intput = " +data.cnt +"  "+ data.content +"  "+ data.sender + "</li>" )

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
        url:'/sseview'
        , method : 'POST'
        , contentType : "application/json"
        , dataType:"json"
        , data: JSON.stringify({username})
        , success : function(data){
            $('#show').append("<li> " + data[0].value + "</li>" )

        }
    })
}

let number = document.querySelector('#number')
function pluscnt(){
    if (number.value>0) {
        for (let i = 0; i < number.value; i++) {
            plus()
        }
    }
    else {
        alert("0보다 큰숫자를 입력해주세요")
    }

}
function minuscnt(){
    if (number.value>0) {
        for (let i = 0; i < number.value; i++) {
            minus()
        }
    }
    else {
        alert("0보다 큰숫자를 입력해주세요")
    }

}

