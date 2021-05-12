define(function() {
    return {
        rows:[
            {cols:[
                    {rows:[
                            { view: "label", label: "Имя"},
                            { view:"text", id:"name", value:"Имя"},
                            { view: "label", label: "Email"},
                            { view:"text", id:"email", value:"Почта"},
                            { view: "label", label: "Пароль"},
                            { view:"text", id:"password", value:"Пароль"},
                            { view: "label", label: "Телефон"},
                            { view:"text", id:"phone", value:"Телефон"},
                        ]},
                    {rows:[
                            { view: "label", label: "Учетка активна"},
                            {   view:"combo",
                                id:'active',
                                value:'true',
                                options:['true','false']
                            },
                            { view: "label", label: "Роль"},
                            { view:"text", id:'role', value:'ROLE_CUSTOMER'},

                        ]},

                ]},
            { view:"button", value:"Добавить пользователя" ,click:function()
                {
                    let data = {'name': $$('name').getValue(),
                        'email':$$('email').getValue(),
                        'password':$$('password').getValue(),
                        'phone':$$('phone').getValue(),
                        'active':$$('active').getValue(),
                        'role':$$('role').getValue()};
                    $$('tableID').add(data);
                }
            },
            {
                id:"tableID",
                view:"datatable",
                columns:[
                    {id:'name', editor:'text' },
                    {id:'email', editor:'text'},
                    {id:'password', editor:'text'},
                    {id:'phone', editor:'text'},
                    {id:'active',
                                editor:"select",
                                value:'true',
                                options:['true','false']
                            },
                    {id:'role', template:'ROLE_CUSTOMER'},
                    { id:"trash", header:"", template:"{common.trashIcon()}"}
                ],
                url:'resource->/api/user',
                save:'resource->/api/user',
                autoheight: true,
                autowidth: true,
                editable: true,
                pager: "tableID" + 'Pager',
                datafetch: 10,
                onClick:{
                    "wxi-trash":function(event, id, node){
                        this.remove(id)
                    }
                }
            },
            {
                view: 'pager',
                id: "tableID" + 'Pager',
                size: 10,
                group: 10,
                template: '{common.first()}{common.prev()}{common.pages()}{common.next()}{common.last()}'
            }
        ]
    }
})
