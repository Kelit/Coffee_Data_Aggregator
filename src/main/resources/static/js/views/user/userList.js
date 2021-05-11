define(['static/js/component/listPage.js'], function (listPage){
    return listPage(
            'userList',
            'resource->/api/user',
            [
                {id:'name', editor:'text'},
                {id:'email', editor:'text'},
                {id:'password', editor:'text'},
                {id:'phone', editor:'text'},
                {id:'active',
                            editor:"select",
                            template: true,
                            options:[true, false]
                        },
                {id:'role', template:'ROLE_CUSTOMER'}
            ]
    )
})