!function ($) {

  "use strict"; // jshint ;_;


 /* PUBLIC CLASS DEFINITION
  * ============================== */

  var Todo= function ($todoElement, options) {
    this.$element = $todoElement
    this.options = $.extend({}, $.fn.todo.defaults, options)
  }

  Todo.prototype = {

    constructor: Todo,

    process: function(button) {
        var $button = $(button)
        var url = $button.attr("href")
        var that = this
        var img = $('<img src="' + $.lily.contextPath + '/resources/images/empty.gif" class="tiny-load">')
        function processResponse(responseData) {
            if(url.match(/running$/)) {
                $('.label', that.$element).text(responseData.user.name)
                var workerStr = '<span class="runner on" title="' + responseData.user.name + '正在做这条任务">' + 
                    '<img alt="' + responseData.user.name + '" class="avatar" src="' + responseData.contextPath + '/avatar/' + responseData.user.avatarUrl + '"></span>'
                $(workerStr).insertBefore($('.todo-content', that.$element))
                img.remove()
                that.$element.addClass("running")
                $button.css("display", "")
            }
            else if(url.match(/pause$/)) {
                $('.label', that.$element).text(responseData.user.name)
                $('.runner', that.$element).remove()
                img.remove()
                that.$element.addClass("running")
                $button.css("display", "")
                that.$element.removeClass("running")
            }
            else if(url.match(/\/done$/)) {
                if(!that.$completeContainer) {
                    var test = that.$element.closest('ul')
                    that.$completeContainer = test.parent().find(".todos-completed")
                }
                $button.attr("href", url.substring(0, url.length - 4) + "undone")
                that.$element.addClass("completed").appendTo(that.$completeContainer)
            }
            else if(url.match(/\/undone$/)) {
                if(!that.$uncompleteContainer) {
                    var test = that.$element.closest('ul')
                    that.$uncompleteContainer = test.parent().find(".todos-uncompleted")
                }
                $button.attr("href", url.substring(0, url.length - 6) + "done")
                that.$element.removeClass("completed").appendTo(that.$uncompleteContainer)
            }
        }
        $button.css("display", "none")
        img.insertAfter($button)
        $.lily.ajax({url: url,
            dataType: 'json',
            type: 'POST',
            processResponse: processResponse
        })
        
    }
  }


 /* PLUGIN DEFINITION
  * ======================== */

  var old = $.fn.todo

  $.fn.todo= function (option) {
    return this.each(function () {
      var $this = $(this)
        , options = typeof option == 'object' && option
        , $todoElement = $this.closest('li')
        , data = $todoElement.data('todo')
      if (!data) $todoElement.data('todo', (data = new Todo($todoElement, options)))
      data.process(this)
    })
  }

  $.fn.todo.defaults = {
    loadingText: 'loading...'
  }

  $.fn.todo.Constructor = Todo


 /* NO CONFLICT
  * ================== */

  $.fn.todo.noConflict = function () {
    $.fn.todo= old
    return this
  }


 /* DATA-API
  * =============== */

  $(document).on('click.todo.data-api', '[data-toggle^=remote]', function (e) {
    var $btn = $(e.target)
    console.log($btn[0].nodeName)
    if($btn[0].nodeName.toLowerCase() == 'a')
        e.preventDefault()
    $btn.todo()
  })

}(window.jQuery);
