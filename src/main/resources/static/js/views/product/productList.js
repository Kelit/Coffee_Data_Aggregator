define(['static/js/component/listPage.js'], function(listPage) {
    return listPage(
        'productList',
        'resource->/api/product',
        [
            { id: 'name', editor: 'text' },
            {
                id: 'category',
                // dialogUrl: 'views/model/modelDialog',
                dialogUrl: 'views/category/categoryDialog.js',
                template: function(row) {
                    return row.model && row.model.repr || ''
                }
            }
        ]
    )
})
