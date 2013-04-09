$(function() {
	$('.tips-helper').tooltip().show()
	$('#add-invite-item').click(function(){
		var $obj = $('<div class="invite-item more">'
				+ '<input type="email" class="invite-email" placeholder="例如：test@localhost.com" name="emails">'
				+ '<select class="invite-role" id="choose-role" name="roles">'
				+ '<option value="1">管理员</option>'
				+ '<option value="0" selected>成员</option>'
				+ '<option value="3">访客</option>'
				+ '</select>'
				+ '<a href="javascript:;" class="del-invite btn btn-x" data-toggle="button-delete">删除</a>'
				+ '</div>')
		$('#invite-container').append($obj)
	})
})
