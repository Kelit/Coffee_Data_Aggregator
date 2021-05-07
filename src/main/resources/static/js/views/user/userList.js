define(['static/js/component/listPage.js'], function (listPage){
    return listPage(
            'userList',
            'resource->/api/user',
            [
                {id:'name', editor:'text'},
                {id:'email', editor:'text'},
                {id:'password', editor:'text'},
                {id:'phone', editor:'text'},
                // {id:'active', editor:'boolean'},
                {
                    id: 'active',
                    dialogUrl: 'views/user/userDialogActive.js',
                    template: function(row) {
                        return row.model && row.model.repr || ''
                    }
                }
            ]
    )
})