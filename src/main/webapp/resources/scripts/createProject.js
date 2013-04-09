$(function() {
	function projectCollect() {
		var members = [];
		$('.member.selected').each(function(){
            var $this = $(this)
			members.push($this.attr("data-content"))
		});
		return {members: members};
	}

    $(document).ready(function(){
    	$("#projectCreateForm").each(function(){
            $(this).data('collectData', projectCollect);
        })
    
    })
})
