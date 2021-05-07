requirejs.config({
    baseUrl: 'js'
})

function buildRoute(view) {
    return function() {
        webix.ui({
            id: 'root',
            rows: [
                view
            ]
        }, $$('root'))
    }
}

function buildButton(label, route) {
    return {
        view: 'button',
        value: label,
        width: 100,
        align: 'center',
        content: route
    }
}
var menu_button  = [
        buildButton('Home', ''),
        buildButton('Товары', 'products'),
        buildButton('Категории', 'category'),
        {id: "demo", icon: "mdi mdi-book", value:"Documentation"}
];

var menu ={
        view: "menu", id: "m1",
        layout: "y", width: 250,
        select: true,
        data: menu_button,
        on: {
            onMenuItemClick: function (id) {
                routie(this.getMenuItem(id).content);
            }
        }
}


require(
    [
        'static/js/views/main.js',
        'static/js/views/product/productList.js',
        'static/js/views/category/categoryList.js',
        // 'util/resourceProxy',
    ],
    function(main, products, categories,resourceProxy) {
        webix.ready(function(){
            webix.ui({
                type:"wide",
                rows:[
                    {view: "toolbar", padding:3, elements: [{ view: "label", label: "Coffee Manager"},{},],},
                    {cols:[ menu, { id:"root", template:" " }]}],
            })
        })
        routie({
            '': buildRoute(main),
            'products': buildRoute(products),
            'category': buildRoute(categories),
            // 'marks': buildRoute(marks)
        })
})
