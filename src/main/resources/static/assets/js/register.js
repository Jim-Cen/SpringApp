$(document).ready(function(){
    // var steps = $('.step-bar ul li');
    // // First step
    // $('.firstNext').on('click', function(e) {
    //   setTimeout(function() {
    //     $(steps[1]).find('.number').addClass('active');
    //   }, 1000);
    
    //   $(steps[1]).find('.line').addClass('line-active');
    //   $('.account-setup').css('left', '-4000px');
    //   $('.user-details').css('left', 'calc(50% - 175px)');
    // });
    
    // // Second step
    // $('.secondNext').on('click', function(e) {
    //   setTimeout(function() {
    //     $(steps[2]).find('.number').addClass('active');
    //   }, 1000);
    
    //   $(steps[2]).find('.line').addClass('line-active');
    //   $('.user-details').css('left', '-4000px');
    //   $('.finish-step').css('left', 'calc(50% - 175px)');
    // });
    
    // $('.firstPrev').on('click', function(e) {
    //   $(steps[1]).find('.number').removeClass('active');
    //   $(steps[1]).find('.line').removeClass('line-active');
    //   $('.user-details').css('left', '4000px');
    //   $('.account-setup').css('left', 'calc(50% - 175px)');
    // });
    
    // // Last step
    // $('.secondPrev').on('click', function(e) {
    //   $(steps[2]).find('.number').removeClass('active');
    //   $(steps[2]).find('.line').removeClass('line-active');
    //   $('.finish-step').css('left', '4000px');
    //   $('.user-details').css('left', 'calc(50% - 175px)');
    // });

    $("#regBtn").click(function(e){
        $(".error-text").html("*必须填写");
        if($("#password").val().trim() !=$("#confirmPassword").val().trim()){
            $(".error-text").html("两次密码输入不一致");
            return;
        }
        if($("#name").val().trim() == ""){
            $(".error-text").html("必须填账号");
            $("#name").focus();
            return;
        }
        if($("#password").val().trim() == ""){
            $(".error-text").html("必须填密码");
            $("#password").focus();
            return;
        }

        $.postForm({
            url:"/apis/login/user",
            cache:false,
            async:true,
            type: "post",
            dataType: "json",
            beforeSend:function(XMLHttpRequest){
                $(".loading").show();
            },
            success:function(data, textStatus){
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            complete:function(XMLHttpRequest, textStatus){
                $(".loading").hide();
            },
        })
    })
    
})
