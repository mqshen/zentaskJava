!function(){

    "use strict"

    var Form = function(button, element, options) {
        this.$submitButton = $(button)
        this.$element = $(element)
        this.options = $.extend({}, $.fn.form.defaults, options)
    }

    Form.prototype = {
        constructor: Form,

        submit: function() {
		    this.oldText = this.$submitButton.text()
		    this.$submitButton.attr("disabled",true).text(this.$submitButton.attr("data-disable-with"))
            var $this = $(this) 
            var requestData = this.collectRequestData()
            if(this.$element.data("collectData")) {
                var specialData = this.$element.data("collectData")()
                $.extend(requestData, specialData)
            }
            var self = this
            function processResponse(reponseData) {
                if(self.$element.data("doResponse")) {
                   self.$element.data("doResponse")(reponseData) 
                }
                else {
                    document.location.href = reponseData.successUrl
                }
                self.resetForm()
            }
            $.lily.ajax({url: this.$element.attr("action"), 
                data: requestData, 
                dataType: 'json',
                type: 'POST',
                processResponse: processResponse
            })
        },

        resetForm: function() {
		    this.$submitButton.attr("disabled", false).text(this.oldText)
            this.$element[0].reset()
        },
        collectRequestData: function() {
            var orginRequestData = {}
            $('input' , this.$element).each(function() {
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
            
            $('textarea' , this.$element).each(function() {
                orginRequestData[this.name] = this.value
            })
            
            $('select' , this.$element).each(function() {
                orginRequestData[this.name] = this.value
            })
            return orginRequestData
        }

    }

    $.fn.form = function ( option ) {
       return this.each(function () {
           var $this = $(this), 
               data = $this.data('form'), 
               options = typeof option == 'object' && option;
           if (!data) {
               var form = $this.closest("form")
               $this.data('form', (data = new Form(this, form, options)));
           }
           if (option == 'submit') 
               data.submit();
       });
   }

   $.fn.form.defaults = {
       loadingText: 'loading...'
   }

   $.fn.form.Constructor = Form 

   $(document).on('click.form.data-api', '[data-toggle^=submit]', function (e) {
        var $btn = $(e.target)
        $btn.form("submit")

   })
}(window.jQuery)
