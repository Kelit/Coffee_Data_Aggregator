var reader = new FileReader();
define(['static/js/collections/categorys.js'], function(category, images) {
    return {
        rows:[
                    {
                        view:"form",
                        // width:600,
                        id:"form1",
                        elements:[
                                {
                                    cols: [
                                        {
                                            rows: [
                                                {view: "label", label: "Наименование товара"},
                                                {view: "text", name: "nameProduct", value: "Товар"},

                                                {view: "label", label: "Категория"},
                                                {view: "combo", name: "categoryProduct", options: category},


                                                {view: "label", label: "Цена"},
                                                {view: "text", name: "priceProduct", value: "0", format: "1.111,00"},

                                                {view: "label", label: "Дата создания"},
                                                {view: "text", name: "creatTimeProduct", value: "Дата создания"},
                                            ]
                                        },
                                        {
                                            rows: [
                                                {template: '<input type="file" onchange="encodeImageFileAsURL(this)" >'},
                                                {view: "label", label: "Описание"},
                                                {view: "text", id: "descriptionProduct", value: "Описание"},
                                            ]
                                        },
                                    ]
                                }
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
                                "<div class='webixlabel'>Цена: #productPrice#</div> " +
                                "<div class='webixlabel'>Описание: #productDescription#</div> " +
                                "<div class='webixlabel'>Товар добавлен: #createTime#</div> " +
                            "</div>"+
                            "<div class='webixtype_image' id='container'><img width='200' height='200'  src='#icon#'></div>" +
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
    }
})
function addData(){
    var values = $$("form1").getValues();
    $$("data").add({
        name:values.nameProduct,
        category:values.categoryProduct,
        productPrice:values.priceProduct,
        productDescription:values.descriptionProduct,
        createTime:values.creatTimeProduct,
        icon:reader.result
    }, 0);
}
function removeData(){
    if(!$$("data").getSelectedId()){
        webix.message("No item is selected!");
        return;
    }
    $$("data").remove($$("data").getSelectedId());
}
function encodeImageFileAsURL(element) {
    var file = element.files[0];
    reader.onloadend = function() {
        webix.alert("Картинка загруена" + reader.result)
    }
    reader.readAsDataURL(file);
}