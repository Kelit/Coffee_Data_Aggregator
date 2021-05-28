define(['static/js/collections/roles.js'], function(roles) {
    return {
        rows:[
            {cols:[
                    {rows:[
                            { view: "label", label: "Учетка"},
                            { view:"text", id:"username", value:"Имя"},

                            { view: "label", label: "Имя"},
                            { view:"text", id:"name", value:"Имя"},

                            { view: "label", label: "Фамилия"},
                            { view:"text", id:"lastname", value:"Фамилия"},
                        ]},
                    {rows:[
                            { view: "label", label: "Активность учетки"},
                            {   view:"combo",
                                id:'active',
                                value:'true',
                                options:['true','false']
                            },
                            { view: "label", label: "Email"},
                            { view:"text", id:"email", value:"Почта"},

                            { view: "label", label: "Пароль"},
                            { view:"text", id:"password", value:"Пароль"},

                            { view: "label", label: "Роль"},
                            { view:"combo", name:"category", options: roles},

                            // { view: "label", label: "Роль"},
                            // { view:"text", id:'role', value:'ROLE_CUSTOMER'},

                        ]},

                ]},
            { view: "label", label: "Телефон"},
            { view:"text", id:"phone", value:"Телефон", format:{
                    parse: function(a){ return a.replace(/[^0-9]*/g,""); },
                    edit: function(a){
                        function chunk(a, n){
                            return a.length > n ? (a.substr(0,n) + "-" + chunk(a.substr(n), n)): a;
                        }
                        return (a.length ? "+": "") + chunk(a, 4);
                    },
                }},
            { view:"button", value:"Добавить пользователя" ,click:function()
                {
                    let data = {
                        'username': $$('username').getValue(),
                        'name': $$('name').getValue(),
                        'lastname': $$('lastname').getValue(),
                        'email':$$('email').getValue(),
                        'password':$$('password').getValue(),
                        'phone':$$('phone').getValue(),
                        'active':$$('active').getValue(),
                        // 'role':$$('role').getValue()
                    };
                    $$('tableID').add(data);
                }
            },
            {
                id:"tableID",
                view:"datatable",
                columns:[
                    {id:'username', editor:'text' },
                    {id:'name', editor:'text' },
                    {id:'lastname', editor:'text' },
                    {id:'email', editor:'text'},
                    {id:'password', editor:'text'},
                    {id:'phone', editor:'text'},
                    {id:'active',
                                editor:"select",
                                value:'true',
                                options:['true','false']
                            },
                    // {id:'role', template:'ROLE_CUSTOMER'},
                    { id:'role', editor:"select",
                                 value:'CUSTOMER',
                                 options: roles},
                    { id:"trash", header:"", template:"{common.trashIcon()}"}
                ],
                url:'resource->/api/user',
                save:'resource->/api/user',
                height: 1024,
                // autoheight: true,
                width: 1024,
                // autowidth: true,
                editable: true,
                pager: "tableID" + 'Pager',
                datafetch: 15,
                onClick:{
                    "wxi-trash":function(event, id, node){
                        this.remove(id)
                    }
                }
            },
            {
                view: 'pager',
                id: "tableID" + 'Pager',
                size: 15,
                group: 15,
                template: '{common.first()}{common.prev()}{common.pages()}{common.next()}{common.last()}'
            }
        ]
    }
})
