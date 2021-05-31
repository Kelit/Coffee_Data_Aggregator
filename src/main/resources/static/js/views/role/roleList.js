define(function() {
    return {
        rows:[
            {
                id:"tableID",
                view:"datatable",
                columns:[
                    {id:'username', editor:'text' },
                    {id:'role', editor:'text' },
                    { id:"trash", header:"", template:"{common.trashIcon()}"}
                ],
                url:'resource->roles',
                save:'resource->roles',
                height: 1024,
                // autoheight: true,
                width: 1024,
                // autowidth: true,
                editable: true,
                pager: "tableID" + 'Pager',
                datafetch: 15,
                onClick:{
                    "wxi-trash":function(event, id, node){
                        this.remove(id)
                    }
                }
            },
            {
                view: 'pager',
                id: "tableID" + 'Pager',
                size: 15,
                group: 15,
                template: '{common.first()}{common.prev()}{common.pages()}{common.next()}{common.last()}'
            }
        ]
    }
})
