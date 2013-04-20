$(function() {
	function projectCollect() {
		var members = [];
		$('.member.selected').each(function(){
            var $this = $(this)
			members.push($this.attr("data-content"))
		});
		return {members: members};
	}

    	$("#form").each(function(){
            $(this).data('collectData', projectCollect);
        })
        $("#form").data("doResponse", function(){
        	document.location.href = $.lily.contextPath + '/project'
        })
})
