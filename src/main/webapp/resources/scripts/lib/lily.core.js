/**
 * jQuery core - v1.0
 * auth: shenmq
 * E-mail: mqshen@126.com
 * website: shenmq.github.com
 */

(function( $, undefined ) {

// prevent duplicate loading
// this is only a problem because we proxy existing functions
// and we don't want to double proxy them
$.lily = $.lily || {};
if ( $.lily.version ) {
	return;
}

jQuery.fn.extend({
	bind: function( types, data, fn ) {
		this.off( types, null, fn );
		return this.on( types, null, data, fn );
	}
});

$.extend( $.lily, {
	minInterval: 1000,
    browser: function(browser) {
		var ua = navigator.userAgent.toLowerCase();
		var match = /(chrome)[ \/]([\w.]+)/.exec(ua) || /(webkit)[ \/]([\w.]+)/.exec(ua) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua) || /(msie) ([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || [];

		if (browser == 'version')
		{
			return match[2];
		}

		if (browser == 'webkit')
		{
			return (match[1] == 'chrome' || match[1] == 'webkit');
		}
		return match[1] == browser;
	},
	ajax: function(options) {
		//console.log(options);
		//try{initAutoOutTimer();}catch(e){};
		var startTime = (new Date()).getTime()
		var option = $.extend(options, {cache:false, dataType:'json'});
		if(option.data) {
			$.extend(option.data, $.lily.collectCsrfData())
		}
		else {
			option.data =  $.lily.collectCsrfData()
		}

		function doResponse(data) {

			if(data.returnCode != '0' && data.returnCode != '000000') {
				if(options.transactionFailed) {
					alert(data.returnCode);
				}
			}
			else {
				var currentTime = (new Date()).getTime();
				var timeInterval = currentTime - startTime ;
				if(timeInterval < $.lily.minInterval) {
					setTimeout(function() {options.processResponse(data) }, $.lily.minInterval - timeInterval);
				}
				else
					options.processResponse(data)
                
			}
		}
	    if(options.processResponse) {	
    		$.extend(options, {success: doResponse})
        }
		
		return $.ajax(option);
	},
	
	collectCsrfData: function() {
		var data = {}
        $('#csrfForm > input').each(function(){
        	data[this.name] = this.value
        });
		return data; 
	},
    generateUID : function() {
        var guid = "";
        for (var i = 1; i <= 32; i++){
            var n = Math.floor(Math.random()*16.0).toString(16);
            guid += n;
            if((i==8)||(i==12)||(i==16)||(i==20))
                guid += "-";
        }
        return guid;
    }
});
})( jQuery ); 
