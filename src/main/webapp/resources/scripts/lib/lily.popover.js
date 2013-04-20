!function ($) {

  "use strict"; // jshint ;_;


 /* POPOVER PUBLIC CLASS DEFINITION
  * =============================== */

    var Popover = function (element, options) {
        this.$element = $(element)
        this.options = $.extend({}, $.fn.popover.defaults, options)
        this.mainOffset = $('#main-workspace').offset()
        var $content = $(this.$element.attr("data-conent"))
        this.$content = $content.clone();
        this.$content.insertAfter($content)
        
    }


    /* NOTE: POPOVER EXTENDS BOOTSTRAP-TOOLTIP.js
     ========================================== */
    
    Popover.prototype = {
    
        constructor: Popover,

        toggle: function () {
            return this[!this.isShown ? 'show' : 'hide']()
        },
        
        show: function() {
            if (this.isShown )
                return
            this.isShown = true

            var that = this
            this.backdrop(function () {
                var offset = that.$element.offset()
                var shownbottom = $(window).height() - offset.top + $(window).scrollTop()
                if(shownbottom > that.$content.height()) {
                    that.$content.css({
                        top: offset.top - that.mainOffset.top - that.options.gap ,//- that.$element.height()/2,
                        left: offset.left - that.mainOffset.left + that.$element.width() + that.options.horizontalGap
                    })
                    that.$content.removeClass("direction-right-top")
                    that.$content.addClass("direction-right-bottom")
                }
                else {
                    that.$content.css({
                        top: offset.top - that.mainOffset.top - that.$content.height() + that.options.gap + that.$element.height(),
                        left: offset.left - that.mainOffset.left + that.$element.width() + that.options.horizontalGap
                    })
                    that.$content.removeClass("direction-right-bottom")
                    that.$content.addClass("direction-right-top")
                }
                that.$content.fadeOut()

                //that.$element.show()
                
                that.$content.fadeIn()
                function processResponse(data) {
                	var obj = '<span class="assignee">' + data.todoItem.worker.name  + '&nbsp;</span>'
                	 + '<span class="due">' + $.lily.format.formatDate(data.todoItem.deadLine, 'yyyy-mm-dd') + '</span>';
                	that.$element.html(obj);
                	$.lily.hideWait(that.$element);
                }
                function dateSelectCallback(date) {
                	if(that.$element.attr("data-remote") == "true") {
                		that.hide()
                		var url = that.$element.attr("href");
                		var requestData = $.lily.collectRequestData(that.$content);
                		requestData[that.$element.attr("data-date-name")] = $.lily.format.formatDate(date, 'yyyy-mm-dd 23:59:59')
                		$.lily.showWait(that.$element);
                		$.lily.ajax({url: url,
                			dataType: 'json',
                			data: requestData,
                	        type: 'POST',
                	        processResponse: processResponse
                	    })
                	}
                	else {
                    	that.$element.next('input').val($.lily.format.formatDate(date, 'yyyy-mm-dd 23:59:59'));
                    	that.$element.text($.lily.format.formatDate(date))
                    	that.hide();
                	}
                }
                $('.datepicker', that.$content).calendar({
			    	selectFun: dateSelectCallback,
			    	target: that.$element
			    })
                
            })
        },

        hide: function(e) {
            var that = this

            if (!this.isShown )
                return
            this.isShown = false
            this.$content.fadeOut()
            this.removeBackdrop()
        },

        removeBackdrop: function () {
            this.$backdrop && this.$backdrop.remove()
            this.$backdrop = null
        },

        backdrop: function (callback) {
            var that = this
            if (this.isShown && this.options.backdrop) {
                this.$backdrop = $('<div class="backdrop" />')
                    .appendTo(document.body)
                this.$backdrop.click(function() {
                    that.hide()
                })
                this.$backdrop.addClass('in')
                if (!callback) return
                callback()
            } 
            else if (!this.isShown && this.$backdrop) {
                this.$backdrop.removeClass('in')
                callback()
            } 
            else if (callback) {
                callback()
            }
        }
    }
    
    
     /* POPOVER PLUGIN DEFINITION
      * ======================= */
    
    
    $.fn.popover = function (option) {
        return this.each(function () {
            var $this = $(this)
                , data = $this.data('popover')
                , options = typeof option == 'object' && option
            if (!data) $this.data('popover', (data = new Popover(this, options)))
            if (typeof option == 'string') data[option]()
        })
    }
    
    $.fn.popover.Constructor = Popover
    
    $.fn.popover.defaults = {
        placement: 'right', 
        trigger: 'click', 
        content: '', 
        backdrop: true,
        gap: 15,
        horizontalGap: 25,
        template: '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
    }
    

 /* POPOVER NO CONFLICT
  * =================== */

    $.fn.popover.noConflict = function () {
        $.fn.popover = old
        return this
    }

    $(document).on('click.popover.data-api', '[data-toggle^=popover]', function (e) {
        var $btn = $(e.target)
        if (!$btn.hasClass('label')) $btn = $btn.closest('.label')
        $btn.popover('show')
        e.preventDefault()
    })

}(window.jQuery);
