define(['static/js/collections/categorys.js'], function(category) {
    return {
        rows:[
            {cols:[
                    {rows:[
                            { view: "label", label: "Наименование товара"},
                            { view:"text", id:"name", value:"Товар"},

                            { view: "label", label: "Категория"},
                            { view:"combo", id:"category", options: category},

                            { view: "label", label: "Цена"},
                            { view:"text", id:"price", value:"0", format:"1.111,00"},

                            { view: "label", label: "В наличии"},
                            { view:"text", id:"stock", value:"В наличии"},

                        ]},
                    {rows:[
                            { view: "label", label: "Описание"},
                            { view:"text", id:"description", value:"Описание"},

                            {
                                view:"form",
                                rows:[
                                    {
                                        view:"uploader",
                                        id: "uploader1",
                                        value:"file",
                                        link:"file",
                                        upload:webix.proxy().post('/api/image/upload',),
                                        datatype:"json"
                                    },
                                    {
                                        view:"list",
                                        id:"list1",
                                        type:"uploader",
                                        autoheight:true,
                                        borderless:true
                                    }
                                ]
                            },
                            // { view: "label", label: "Фото"},
                            // { view:"text", id:"icon", value:"Фото"},

                            { view: "label", label: "Дата создания"},
                            { view:"text", id:"creatTime", value:"Дата создания"},

                            { view: "label", label: "Дата последнего обновления"},
                            { view:"text", id:"updateTime", value:"Дата последнего обновления"},
                        ]},

                ]},
            { view:"button", value:"Добавить позицию" , click:function()
                {
                    let data = {'name': $$('name').getValue(),
                        'category':$$('category').getValue(),
                        'productPrice':$$('price').getValue(),
                        'productStock':$$('stock').getValue(),
                        'productDescription':$$('description').getValue(),
                        'productIcon':$$('icon').getValue(),
                        'createTime':$$('creatTime').getValue(),
                        'updateTime':$$('updateTime').getValue()
                    };
                    $$('tableID').add(data);
                }
            },
            {
                id:"tableID",
                view:"datatable",
                columns:[
                    {id:'name', editor:'text' },
                    {id:'category',
                        editor:"select",
                        value:'true',
                        options:['true','false']
                    },
                    {id:'productPrice', editor:'text'},
                    {id:'productStock', editor:'text'},
                    {id:'productDescription', editor:'text'},
                    {id:'productIcon', editor:'text'},
                    {id:'createTime', editor:'text'},
                    {id:'updateTime', editor:'text'},
                    { id:"trash", header:"", template:"{common.trashIcon()}"}
                ],
                url:'resource->/api/product',
                save:'resource->/api/product',
                autoheight: true,
                autowidth: true,
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