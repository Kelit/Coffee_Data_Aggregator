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
        click: function() {
            routie(route)
        }
    }
}

require(
    [
        'static/js/views/main.js',
        'static/js/views/product/productList.js',
        'static/js/views/category/categoryList.js',
        'static/js/views/category/categoryList.js',
        'util/resourceProxy',
    ],
    function(main, products, categorys,resourceProxy) {
    webix.ready(function() {
        webix.ui({
            container: 'app',
            width: document.body.clientWidth,
            height: document.body.clientHeight,
            rows: [
                {
                    view: 'toolbar',
                    cols: [
                        buildButton('Home', ''),
                        buildButton('Model', 'models'),
                        buildButton('Cars', 'cars')
                    ]
                },
                {
                    id: 'root'
                }
            ]
        })
    })

    routie({
        '': buildRoute(main),
        'cars': buildRoute(products),
        'models': buildRoute(categorys)
    })
})
