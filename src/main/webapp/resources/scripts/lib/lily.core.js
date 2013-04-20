/**
 * jQuery core - v1.0
 * auth: shenmq
 * E-mail: mqshen@126.com
 * website: shenmq.github.com
 */

(function( $, undefined ) {
	
	var matched, browser;

	// Use of jQuery.browser is frowned upon.
	// More details: http://api.jquery.com/jQuery.browser
	// jQuery.uaMatch maintained for back-compat
	jQuery.uaMatch = function( ua ) {
	    ua = ua.toLowerCase();

	    var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
	        /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
	        /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
	        /(msie) ([\w.]+)/.exec( ua ) ||
	        ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
	        [];

	    return {
	        browser: match[ 1 ] || "",
	        version: match[ 2 ] || "0"
	    };
	};

	matched = jQuery.uaMatch( navigator.userAgent );
	browser = {};

	if ( matched.browser ) {
	    browser[ matched.browser ] = true;
	    browser.version = matched.version;
	}

	// Chrome is Webkit, but Webkit is also Safari.
	if ( browser.chrome ) {
	    browser.webkit = true;
	} else if ( browser.webkit ) {
	    browser.safari = true;
	}

	jQuery.browser = browser;

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
	
	formatPostData: function(data) {
		$.extend(data, $.lily.collectCsrfData())
	},
	
	collectCsrfData: function() {
		var data = {}
        $('#csrfForm > input').each(function(){
        	data[this.name] = this.value
        });
		return data; 
	},
	collectCsrfDataStr: function() {
		var data = ""
        $('#csrfForm > input').each(function(){
        	data += '' + this.name + '=' + this.value +''
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
    },
    collectRequestData: function(sourceElement) {
        var orginRequestData = {}
        $('input' , sourceElement).each(function() {
            // 待用统一方法完善
            if (this.type == 'checkbox') {
                if (this.checked) {
                    orginRequestData[this.name] = '1'
                } 
                else {
                    orginRequestData[this.name] = '0'
                }
            } 
            else if (this.type == 'radio') {
                if (this.checked) {
                    orginRequestData[this.name] = this.value
                }
            }
            else {
                var $this = $(this)
                /*
                var dataValidate = $this.attr('data-validate')
                if(dataValidate) {
                    var config = $.parseJSON(dataValidate)
                    if(config.type && config.type == 'currency') {
                        orginRequestData[this.name] = $.lily.format.removeComma(this.value)
                        return
                    }
                    else {
                        orginRequestData[this.name] = this.value
                        return
                    }
                }
                */
                orginRequestData[this.name] = this.value
            }
        })
        
        $('textarea' , sourceElement).each(function() {
            orginRequestData[this.name] = this.value
        })
        
        $('select' , sourceElement).each(function() {
            orginRequestData[this.name] = this.value
        })
        
        $('[data-toggle="select"]', sourceElement).each(function() {
        	var $this = $(this)
        	var orginStatues = $(this).attr("data-orgin-statues")
        	var selected = false;
        	if(orginStatues == "selected") {
        		if($this.hasClass("selected"))
        			return
        		selected = false;
        	}
        	else {
        		if(!$this.hasClass("selected"))
        			return
        		selected = true;
        	}
        	var contentValue = $this.attr("data-content")
        	if(orginRequestData[$this.attr("name")]) {
            	orginRequestData[$this.attr("name")].push({"content": contentValue, "selected": selected})
        	}
        	else {
        		orginRequestData[$this.attr("name")] = []
        		orginRequestData[$this.attr("name")].push({"content": contentValue, "selected": selected})
        	}
        })
        return orginRequestData
    },
    showWait : function(target) {
    	var waitObj = $('<img src="' + $.lily.contextPath + '/resources/images/empty.gif" class="tiny-load">');
    	waitObj.css({
    		width: target.width(),
    		height: target.height(),
    		padding: target.css("padding"),
    		float: target.css("float")
    	})
    	target.hide();
    	waitObj.insertAfter(target);
    },
    hideWait: function(target) {
    	target.next('img').remove();
    	target.css("display", "")
    }
});
})( jQuery ); 
