define(['static/js/component/listPage.js'], function(listPage) {
    return listPage(
        'productList',
        'resource->/api/product',
        [grida = webix.ui({
            container:"testA",
            view:"datatable",
            columns:[
                { id: 'name', editor: 'text' }
            ],

            autowidth:true,

            url:"/api/product/list"
        })


        ]
    )
})
