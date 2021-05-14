define(['static/js/collections/categorys.js', 'static/js/collections/images.js'], function(category,images) {
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
                                        value:"Добавить изображение",
                                        name:"file", accept:"image/png, image/gif, image/jpeg",
                                        link:"mylist",
                                        upload:'api/image/upload',
                                        autosend:false,
                                        datatype:"json"
                                    },
                                    {
                                        view:"list",
                                        id:"list1",
                                        type:"uploader",
                                        autoheight:true,
                                        borderless:true
                                    },
                                    {
                                        view:"button",
                                        value:"Отправить",
                                        click:function(){
                                            $$('uploader1').send();
                                        }
                                    }
                                ]
                            },
                            {

                                view: "dataview",
                                id: "imageList",
                                css: "nav_list",
                                yCount: 1,
                                select: true,
                                scroll: false,
                                type: {
                                    width: 100,
                                    height: 65
                                },
                                template: img,
                                data: images
                            },
                            // {
                            //     view:"toolbar", elements:[
                            //         { view:"richselect", value:1, height:180, width:300, options:{
                            //                 view:"datasuggest",
                            //                 template:function(obj){
                            //                     return '<img src="data:'+obj.type+';base64,' +obj.size+' style=margin:13px 5px">';
                            //                 },
                            //                 body:{
                            //                     template:function(obj){
                            //                         // return "<br><img style='height:80%' src='data:"+obj.type+";base64, "+obj.size+"'>";
                            //                         return '<img style=height:80% src="data:'+obj.type+';base64,' +obj.size+' style=margin:13px 5px">';
                            //                     },
                            //                     type:{
                            //                         width:255, height:180
                            //                     },
                            //                     data:images,
                            //                 }
                            //             }}, {}
                            //     ]
                            // },

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
function img(obj){
    return '<img style=height:80%  src="data:'+obj.type+';base64,'+obj.size+'">';
}