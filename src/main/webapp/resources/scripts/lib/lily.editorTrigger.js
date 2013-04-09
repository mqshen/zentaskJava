!function($) {

	"use strict"; // jshint ;_;

	var EditorTrigger = function(element, options) {
		this.$element = $(element)
		this.options = $.extend({}, $.fn.button.defaults, options)
		this.$target = $(this.$element.attr("data-target"))
	}

	EditorTrigger.prototype = {
		init: function() {
			this.$editorContainer = $('<div class="edit" style="display:none"><form class="form-edit-message" method="post" action="' + this.$element.attr("href") 
					+ '"</form></div>')
			var $form = $('form', this.$editorContainer)
			var self = this
			function doResponse(data) {
				$('.editable', self.$target).each(function(){
					var $this = $(this)
					$this.html(data.message[$this.attr("name")])
				})
				self.toggle()
			}
			$form.data('doResponse', doResponse);
			$('.editable', this.$target).each(function(){
				var $this = $(this)
				var $obj;
				if($this.attr("data-type") == "textarea") {
					$obj = $('<textarea class="content"></textarea>')
				}
				else {
					$obj = $('<input tabindex="1" type="text" >')
				}
				$obj.attr("name", $this.attr("name"))
				$obj.attr("id", $this.attr("id"))
				$obj.val($this.html())
				var $fieldContainer = $('<div class="form-field"></div>')
				$obj.appendTo($fieldContainer )
				var $itemContainer = $('<div class="form-item"></div>')
				$itemContainer.append($fieldContainer )
				$itemContainer.appendTo($form)
			})
			var $buttonObj = $('<div class="form-buttons"><button tabindex="1" class="btn btn-primary" id="btn-post" data-toggle="submit" data-disable-with="正在保存...">保存</button>' 
					+ '<a tabindex="2" href="javascript:;" class="btn btn-x" id="link-cancel-post">取消</a></div>')
			$buttonObj.appendTo($form)
			var self = this
			$('#link-cancel-post', this.$editorContainer).click(function(){
				self.toggle()
			})
			this.$editorContainer.insertAfter(this.$target)
			$('textarea', this.$editorContainer).editor({
				dataPost: $.parseJSON(this.$target.attr("data-post"))
			})
			this.initialized = true
		},
		
		
		toggle: function() {
			if(!this.initialized)
				this.init()
			this.$target.toggle()
			this.$editorContainer.toggle()
		}

	}

	$.fn.editorTrigger = function(option) {
		return this
				.each(function() {
					var $this = $(this), data = $this.data('editorTrigger'), options = typeof option == 'object' && option
					if (!data)
						$this.data('editorTrigger', (data = new EditorTrigger(this, options)))
					data.toggle()
				})
	}

	$.fn.editorTrigger.defaults = {
		loadingText : 'loading...'
	}

	$.fn.editorTrigger.Constructor = EditorTrigger

	$(document).on('click.button.data-api', '[data-toggle^=editorTrigger]',
			function(e) {
				var $btn = $(e.target)
				e.preventDefault()
				$btn.editorTrigger("submit")
			})

}(window.jQuery);
