define(['static/js/component/dialogPage.js', 'static/js/collections/categorys.js'], function(dialogPage, categorys) {
    return dialogPage(
        'modelDialog',
        'resource->/api/category',
        [
            { id: 'name' },
            { id: 'category', options: categorys }
        ]
    )
})
