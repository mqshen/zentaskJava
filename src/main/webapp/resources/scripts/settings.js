$(function(){
    $('[type="file"]').bind("change", function() {
    	var url = $(this).attr("data-url")
        $.ajaxFileUpload({
            url: url,
            secureuri: false,
            fileElement: $('[type="file"]'),
            dataType: 'json',
            success: function (reponseData, status) {      
                $('#user-icon').attr("src", reponseData.contextPath + "/avatar/" + reponseData.avatarUrl)
            }
        })
    })
})
