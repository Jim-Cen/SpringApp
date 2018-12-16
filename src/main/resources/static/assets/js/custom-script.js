(function($){
    $.postForm = function(options){
        var headers = {};
        if (typeof options.verify === 'undefined' || options.verify) {
            var clientId = sessionStorage.getItem("clientId");
            if(clientId){
              var token = sessionStorage.getItem("token");
              var timestamp = new Date().getTime();
              var signture = md5(token + "WIN" + timestamp);
              headers.clientId = clientId;
              headers.timestamp = timestamp;
              headers.signture = signture;
            }
        }
        var data={};
        $("input[type=text],input[type=password],input[type=email],select,textarea").each(function(i,e){
            data[e.name || e.id] = e.value;
        });
  
        var cbParams={};
        $("input[type=checkbox],input[type=radio]").filter(":checked").each(function(i,e){
              var name = e.name || e.id;
              if(cbParams[name] != null){
                cbParams[name].push(e.value);
              }else{
                cbParams[name]=[e.value];
              }
        });
        Object.keys(cbParams).forEach(function(key){
          data[key] = cbParams[key].join(",");
        })
        options.data=data;
        options.headers = $.extend({},options.headers,headers);
        $.ajax(options);
      }

      //回车焦点定位
      $(document).keypress(function(e){
           e = event || e;
           var that = event.srcElement || event.target;
           var next = false;
          if( e.keyCode == 13 && 
              that.type !='button' &&  that.type !='submmit' &&
             (that.tagName == 'INPUT' || that.tagName == 'TEXTAREA' || that.tagName == 'BUTTON')
             ){
            $("input,textarea,button").each(function(i,el){
                  if(next){
                    el.focus();
                    return false;
                  }
                  next = (el == that);
            });
          }
      })
})(jQuery);
