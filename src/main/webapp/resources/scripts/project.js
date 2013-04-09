$(function() {
    function doReponse(responseData) {
        var contentStr = '<div class="message" style="display:none"><a href="/members/' + responseData.user.id 
        	+ '" ></a><img class="avatar" src="' + responseData.contextPath + '/avatar/' + responseData.user.avatarUrl 
        	+ '"><div class="name"><a href="/members/' + responseData.user.id + '">' 
        	+ responseData.user.name + '</a></div><a href="/' + responseData.teamId + '/project/messages/' 
        	+ responseData.message.id + '" class="message-link"><span class="message-title">' 
        	+ responseData.message.title + '</span><span class="message-content">' 
        	+ responseData.message.content + '</span></a><span class="time" title="2013-03-22">刚刚</span></div></div>'
        $(contentStr).prependTo('#messages').fadeIn()
        
        $('#no-discussion-div').remove()
        $('#new-discussion-form').toggle()
    }
    
    $('#txt-content').editor()
    /*
    function doReponseTodoItem(responseData) {
        var contentStr = '<li class="todo" ><div class="todo-actions actions"><div class="inr">' + 
            '<a href="' + responseData.teamId + '/project/' + responseData.projectId + '/list/' + responseData.listId + 'item/' + responseData.todoItem.id + '/running" class="run" title="标记成正在进行中">执行</a>' +
            '<a href="' + responseData.teamId + '/project/' + responseData.projectId + '/list/' + responseData.listId + 'item/' + responseData.todoItem.id + '/pause" class="pause" title="暂停">暂停</a>' +
            '<a href="' + responseData.teamId + '/project/' + responseData.projectId + '/list/' + responseData.listId + 'item/' + responseData.todoItem.id + '/edit" class="edit" title="编辑">编辑</a>' +
            '<a href="' + responseData.teamId + '/project/' + responseData.projectId + '/list/' + responseData.listId + 'item/' + responseData.todoItem.id + '/destroy" class="del" title="删除">删除</a>' +
		    '</div></div>' +
	        '<div class="todo-wrap">' +
	    	'<input type="checkbox" name="todo-done">' +
            '<span class="todo-content">' +
			'<span>' + responseData.todoItem.title + '</span>' +
            '<a href="' + responseData.teamId + '/project/' + responseData.projectId + '/list/' + responseData.listId + 'item/' + responseData.todoItem.id + '">' + responseData.todoItem.title + '</a>' +
		    '</span>' +
			'<a href="javascript:;" class="label todo-assign-due no-assign" >未指派</a>' +
	        '</div>' +
            '</li>'
        $(contentStr).appendTo('#todos-uncompleted-container-' + responseData.listId)
    }
    */

    function doReponseTodoList(responseData) {
        var contentStr = '<div class="todolist" data-guid="2b0083f159cd427badd67a9aa2b1f345" data-sort="0" data-project-guid="f1a6a280a67343fa827f5722e4c71f76">' + 
	        '<div class="title"><div class="todolist-actions actions"><div class="inr">' +
            '<a href="/project/' + responseData.projectId + '/list/' + responseData.todoList.id + '/stick" class="stick" ></a>' +
            '<a href="/project/' + responseData.projectId + '/list/' + responseData.todoList.id + '/edit" class="edit" title="编辑">编辑</a>' +
            '<a href="/project/' + responseData.projectId + '/list/' + responseData.todoList.id + '/destroy" class="del" title="删除任务列表">删除</a>' +
			'</div></div><h4><a href="/project/' + responseData.projectId+ '/list/' + responseData.todoList.id + '/show" data-stack="">' + responseData.todoList.title + '</a></h4></div>' +
	        '<ul class="todos todos-uncompleted ui-sortable" id="todos-uncompleted-container-' + responseData.todoList.id + '" ></ul>' +
            '<ul class="todo-new-wrap">' +
		    '<li class="todo-form" >' +
            '<button type="button" class="btn btn-mini btn-new-todo" data-toggle="append" data-content="#new-todo-form-container" data-url="' + responseData.todoList.id + '">添加新任务</button>' +
		    '</li>' +
            '</ul>' +
            '<ul class="todos todos-completed">' +
            '</ul>' +
            '</div>'
        var $todoList = $(contentStr)
        $todoList.prependTo('#todolists-container')
        //$('#new-todoItem-form' + responseData.todoList.id, $todoList).data('doResponse', doReponseTodoItem)
        $('#no-todoList-div').remove()
        $('#new-todoList-div').toggle()
        var hiddenButton = $('[data-toggle="append"]', $todoList)
        hiddenButton.click()
    }
    $(document).ready(function(){
    	$("#new-discussion-form").data('doResponse', doReponse)
    	$("#new-todoList-form").data('doResponse', doReponseTodoList)
        $('.datepicker').calendar()
    })
})
