define(['static/js/component/listPage.js', 'static/js/collections/categorys.js'], function(listPage, marks) {
    return listPage(
        'modelList',
        'resource->/api/product',
        [
            { id: 'name', editor: 'text' },
            { id: 'category', editor: 'combo', options: marks }
        ]
    )
})
