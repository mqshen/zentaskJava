$(function(){
	$('textarea').editor()
	
	
	$('[type="file"]').bind("change", function() {
		var $this = $(this)
        $.ajaxFileUpload({
            url: $.lily.contextPath + '/attachment',
            secureuri: false,
            fileElement: $this,
            dataType: 'json',
            success: function (reponseData, status) {      
            	fileUploadCallback(reponseData)
            }
        })
    })
    
    function fileUploadCallback(responseData) {
		if(responseData.file.imageFile) {
			var imageFile = $('<div class="file selected" data-toggle="select" data-content="'
				+ responseData.file.id + '" name="files"><div class="file-thumb"><a href="javascript:;" title="点击预览">'
				+ '<img class="image" alt="" src="' + $.lily.contextPath + '/attachment/' + responseData.file.url + '">'
				+ '</a></div>'
				+ '<a class="remove" data-toggle="remove" href="javascript:;">&nbsp;</a>'
				+ '</div>')
			var fileEditContainer = $('#file-container > .file-images')
			fileEditContainer.prepend(imageFile)
		}
		else {
			var otherFile = $('<div class="file selected" data-toggle="select" data-content="'
				+ responseData.file.id + '" name="files"><div class="file-thumb">'
				+ '<a href="' + $.lily.contextPath + '/attachment/' + responseData.file.url + '">'
				+ '<img alt="README" src="' + $.lily.contextPath + '/resources/images/file_extension_others.png"></a></div>'
				+ '<a class="remove" data-toggle="remove" href="javascript:;">&nbsp;</a>'
				+ '</div>')
			var fileEditContainer = $('#file-container > .file-others')
			fileEditContainer.prepend(otherFile)
		}
	}
})