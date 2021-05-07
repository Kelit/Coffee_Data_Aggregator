define(['static/js/component/dialogPage.js', 'static/js/collections/activity.js'], function (dialogPage, data){
    return dialogPage(
        'modelDialog',
        '',
        [
            { id: 'name' },
            { id: 'active', options: data }
        ]
    )
})