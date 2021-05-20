var fileProd = {
    type: "",
    file: 1,
};
define(['static/js/collections/categorys.js', 'static/js/collections/images.js'], function(category, images) {
    return {
        rows:[
            // {cols:[
                    {
                        view:"form",
                        // width:600,
                        id:"form1",
                        elements:[
                            {cols:[
                                    {rows:[
                                            { view: "label", label: "Наименование товара"},
                                            { view:"text", name:"nameT", value:"Товар"},

                                            { view: "label", label: "Категория"},
                                            { view:"combo", name:"category", options: category},

                                            { view: "label", label: "Цена"},
                                            { view:"text", name:"priceP", value:"0", format:"1.111,00"},

                                            { view: "label", label: "В наличии"},
                                            { view:"text", name:"stockP", value:"В наличии"},

                                            { view: "label", label: "Дата создания"},
                                            { view:"text", name:"creatTimeP", value:"Дата создания"},

                                            { view: "label", label: "Дата последнего обновления"},
                                            { view:"text", name:"updateTimeP", value:"Дата последнего обновления"},
                                        ]},
                                    {rows:[
                                            // {
                                            //     view:"form",
                                            //     rows:[
                                                    {
                                                        view:"uploader",
                                                        id: "uploader1",
                                                        name:"uploadP",
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
                                                        id:"image",
                                                        rows:[]
                                                    },
                                                    {
                                                        view:"button",
                                                        value:"Отправить",
                                                        click:function(){
                                                            file = $$('uploader1').files.data.pull;;
                                                            $$('uploader1').send();
                                                            webix.ui([
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
                                                            ],$$("image"))
                                                        }
                                                    },

                                            //     ]
                                            // },
                                            { view: "label", label: "Описание"},
                                            { view:"text", id:"descriptionP", value:"Описание"},
                                        ]},
                                ]},
                            {cols:[
                                    { view:"button", value:"Add", click:addData},
                                    { view:"button", value:"Удалить выбраную позицию", click:removeData},
                                    { view:"button", value:"Remove all items", click:function(){
                                            $$('data').clearAll();
                                        }}
                                ]}
                        ]
                    },
            {
                id:"data",
                view:"dataview",
                container:"data",
                select:1,
                css: "movies",
                autowidth: true,
                editable:true,
                editor:"text",
                editValue:"item",
                type:{
                    width: 500,
                    height: 250,
                    template:"<style>" +
                                ".overall {" +
                                    "display: flex;" +
                                "}" +
                                ".inermod {"+
                                    "flex-direction: row;" +
                                "}"+
                                ".item {" +
                                    "flex: flex-end" +
                                "}"+
                                "#container {" +
                                    "flex-direction: row;" +
                                "}"+
                            "</style>" +
                            "<div class='overall'>" +
                                    "<div class='inermod'>"+
                                        "<div class='webixlabel item'>Продукт: #name#</div>" +
                                        "<div class='webixlabel'>Категория товара: #category.name#</div> " +
                                        "<div class='webixlabel'>В наличии: #productStock#</div>" +
                                        "<div class='webixlabel'>Цена: #productPrice#</div> " +
                                        "<div class='webixlabel'>Описание: #productDescription#</div> " +
                                        "<div class='webixlabel'>Товар добавлен: #createTime#</div> " +
                                        "<div class='webixlabel'>Дата последнего обновления: #updateTime#</div> " +
                                    "</div>"+
                                "<div class='webixtype_image' id='container'><img width='200' height='200'  src='data:#productIcon.type#;base64, #productIcon.file#'></div>" +
                             "</div>"

                },
                url:'resource->/api/product',
                save:'resource->/api/product',
                onClick:{
                    "wxi-trash":function(event, id, node){
                        this.remove(id)
                    }
                }
            },
        ]
            // }
            // ]
    }
})
function img(obj){
    return '<img style=height:80%  src="data:'+obj.type+';base64,'+obj.size+'">';
}
function addData(){
    var values = $$("form1").getValues();
    webix.ajax().get("/api/image/get/"+values.file).then(function(data){
        // // fileProd.type = data.file.type;
        // // fileProd.file = data.file.size;
        // webix.alert(file.type);
        // webix.alert(file.file);
    });
    $$("data").add({
        name:values.nameT,
        category:values.category,
        productPrice:values.priceP,
        productStock:values.stockP,
        productDescription:values.descriptionP,
        createTime:values.creatTimeP,
        updateTime:values.updateTimeP,
        productIcon:fileProd
    }, 0);
}

function removeData(){
    if(!$$("data").getSelectedId()){
        webix.message("No item is selected!");
        return;
    }
    $$("data").remove($$("data").getSelectedId());
}
