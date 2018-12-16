$(document).ready(function () {
    $("#name").focus();
    $(".login__submit").click(function(e){
      $(this).addClass("processing");
       checkPassword();
    })
});

function checkPassword(){
    var clientId = $("#name").val();
    $.postForm({
        verify : false,
        url:"/apis/login/user",
        cache:false,
        async:true,
        type: "post",
        dataType: "json",
        beforeSend:function(XMLHttpRequest){},
        success:function(data, textStatus){
            sessionStorage.setItem("clientId",clientId);
            sessionStorage.setItem("token",data.token);
            $(".login__submit").addClass("success");
            setTimeout(function () {
                $(".login").addClass("inactive");
                setTimeout(function(){
                    $(".login__submit").removeClass("success processing");
                    setTimeout(function(){
                        location.replace("index.html");
                    },100);
                },100);
            });
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            setTimeout(function(){
                $(".login__submit").removeClass("processing");
                $(".modal").modal("show");
            },100);
        },
        complete:function(XMLHttpRequest, textStatus){},
    })
}