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
        content: route,
        click: function() {
            routie(route)
        }
    }
}
var menu_button  = [
        buildButton('Home', ''),
        buildButton('Пользователи', 'users'),
        buildButton('Роли', 'role'),
        buildButton('Товары', 'product'),
        buildButton('Категории', 'category'),
        {id: "demo", icon: "mdi mdi-book", value:"Documentation"}
];

var menu ={
        view: "menu", id: "m1",
        layout: "y", width: 350,
        select: true,
        data: menu_button,
        on: {
            onMenuItemClick: function (id) {
                routie(this.getMenuItem(id).content);
                // $$('root').setHTML(routie(this.getMenuItem(id).content));
            }
        }
}


require(
    [
        'static/js/views/main.js',
        'static/js/views/user/userList.js',
        'static/js/views/role/roleList.js',
        'static/js/views/product/productList.js',
        // 'static/js/views/category/categoryList.js',
        'util/resourceProxy',
    ],
    function(main,users,role, product,resourceProxy) {
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
            'users': buildRoute(users),
            'role': buildRoute(role),
            'product': buildRoute(product),
        })
})
