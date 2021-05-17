// define(['static/js/component/listPage.js', 'static/js/collections/categorys.js'], function(listPage, marks) {
//     return listPage(
//         'modelList',
//         'resource->/api/product',
//         [
//             { id: 'name', editor: 'text' },
//             { id: 'category', editor: 'combo', options: marks }
//         ]
//     )
// })
define(['static/js/collections/categorys.js'], function(categorys) {
        return {
            rows: [
                {
                    view: 'toolbar',
                    cols: [
                        {   view: 'button',
                            label: 'Добавить',
                            click: function() {
                                var List = $$(tableId)
                                var id = List.add({})
                                // List.editRow(id)
                            }
                        }
                    ]
                },
                {
                    id: 'tablCat',
                    view: 'datatable',
                    columns: [
                        { id: 'name' },
                        { id: 'category', options: categorys }],
                    url: 'resource->/api/category',
                    save: 'resource->/api/category',
                    autoheight: true,
                    autowidth: true,
                    editable: true,
                    pager: 'tablCat' + 'Pager',
                    datafetch: 10,
                    on: {
                        // onItemClick: function(id) {
                        //     var column = this.config.columns.find(function(col) {
                        //         return col.id === id.column
                        //     })
                        //     var parentTable = this
                        //
                        //     if (column.dialogUrl) {
                        //         require([column.dialogUrl], function(dialogPage) {
                        //             webix.ui({
                        //                 view: 'window',
                        //                 head: 'Choose an item',
                        //                 width: 400,
                        //                 position: 'center',
                        //                 modal: true,
                        //                 body: dialogPage,
                        //                 parentTable: parentTable,
                        //                 cell: id,
                        //             }).show()
                        //         })
                        //     }
                        // }
                    }

                },
                {
                    view: 'pager',
                    id: 'tablCat' + 'Pager',
                    size: 10,
                    group: 10,
                    template: '{common.first()}{common.prev()}{common.pages()}{common.next()}{common.last()}'
                }
            ]
        }
    }
)