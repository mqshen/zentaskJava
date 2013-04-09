$(function() {
    function doReponse(reponseData) {
        var contentStr = '<div class="comment" id="" style=""><a class="avatar-wrap" href="/member/' 
        	+ reponseData.user.id + '/" data-stack="" data-stack-root=""><img class="avatar" src="' + reponseData.contextPath + '/avatar/' 
        	+ reponseData.user.avatarUrl + '" width="50" height="50"></a><div class="comment-actions actions"><a href="" class="edit" style="display: inline;">编辑</a><a href="/comment/' 
        	+ reponseData.comment.id + '/destroy" class="del" data-remote="true" data-method="post" data-confirm="确定要删除这条回复吗？" data-visible-to="creator,admin" style="display: inline;">删除</a></div><div class="comment-main"><div class="info"><a class="author" href="/member/' 
        	+ reponseData.user.id + '/" >' + reponseData.user.name + '</a><span class="create-time" >刚刚</span></div><div class="comment-content editor-style">' 
        	+ reponseData.comment.content + '</div></div></div>'
        $(contentStr).appendTo('#comment-container')
        $('#no-discussion-div').remove()
        $('#new-discussion-form').toggle()
    }
    
    $(document).ready(function(){
    	$("#new-comment-form").data('doResponse', doReponse);
    })
})
