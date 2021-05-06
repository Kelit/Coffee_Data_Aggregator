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
var menu_data_multi  = [
        buildButton('Home', ''),
        buildButton('Model', 'models'),
        buildButton('Cars', 'cars'),
        {id: "demo", icon: "mdi mdi-book", value:"Documentation"}
];
require(
    [
        'static/js/views/main.js',
        // 'static/js/views/product/productList.js',
        // 'static/js/views/category/categoryList.js',
        // 'util/resourceProxy',
    ],
    function(main, products, categorys,resourceProxy) {
        webix.ready(function(){
            webix.ui({
                rows: [
                    { view: "toolbar", padding:3, elements: [
                            {view: "button", type: "icon", icon: "mdi mdi-menu", width: 37, align: "left", css: "app_button",
                                click: function(){
                                    $$("$sidebar").toggle();
                                }
                            },
                            { view: "label", label: "Coffee Manager"},
                            {},
                        ]},
                    { cols:[
                            { view: "sidebar", width:300, data: [
                                    buildButton('Home', ''),
                                    // buildButton('Model', 'models'),
                                    //buildButton('Cars', 'cars'),
                                    {id: "demo", icon: "mdi mdi-book", value:"Documentation"}
                                ], on:{
                                    onAfterSelect: function(id){
                                        webix.message("Selected: "+this.getItem(id).value)
                                    }
                                }},
                            {
                                id : 'root'
                            }
                        ]},


                ]
            })
        })
        routie({
            '': buildRoute(main),
            // 'cars': buildRoute(products),
            // 'models': buildRoute(models),
            // 'marks': buildRoute(marks)
        })
    })
//     webix.ready(function() {
//         webix.ui({
//             container: 'app',
//             width: document.body.clientWidth,
//             height: document.body.clientHeight,
//             rows:[
//                 { view: "toolbar", padding:3, elements: [
//                         {view: "button", type: "icon", icon: "mdi mdi-menu", width: 37, align: "left", css: "app_button",
//                             click: function(){
//                                 $$("$sidebar1").toggle();
//                             }
//                         },
//                         { view: "label", label: "Coffee manager"},
//                         {cols:[
//                                 { view: "sidebar", width:300,
//                                     data: [          {value:"Dashboard",},
//                                         {value:"Users"},
//                                         {value:"Products"},
//                                         {value:"Location"}]}
//                                     ]}
//                     ]}
//                 // {view: "list",
//                 //     id:"mylist",
//                 //     scroll:false,
//                 //     select:true,
//                 //     width:200,
//                 //     css:"list_color",
//                 //     cols: [
//                 //                 buildButton('Home', ''),
//                 //                 buildButton('Model', 'models'),
//                 //                 buildButton('Cars', 'cars')
//                 //             ]
//                 // },
//                 // {view: "resizer"},
//                 // {template:"Table"},
//                 // {template:"Form"}
//             ]
//             // rows: [
//             //     {
//             //         view:"toolbar",
//             //         css:"webix_dark",
//             //         cols:[
//             //             { view:"label", label:"My app"},
//             //             {},
//             //             {height: 40, type:"icon", icon:"wxi-user",  view:"button", label:"Profile", width:100, css:"webix_transparent"}
//             //         ],
//             //         view: "list",
//             //         id:"mylist",
//             //         scroll:false,
//             //         select:true,
//             //         width:200,
//             //         css:"list_color",
//             //         data:[
//             //             {value:"Dashboard",},
//             //             {value:"Users"},
//             //             {value:"Products"},
//             //             {value:"Location"}
//             //         ],
//             //         cols: [
//             //             buildButton('Home', ''),
//             //             buildButton('Model', 'models'),
//             //             buildButton('Cars', 'cars')
//             //         ]
//             //     },
//             //     {
//             //         id: 'root'
//             //     }
//             // ]
//         })
//     })
//

// })
// webix.ready(function(){
//     webix.ui({
//         rows: [
//             { view: "toolbar", padding:3, elements: [
//                     {view: "button", type: "icon", icon: "mdi mdi-menu", width: 37, align: "left", css: "app_button",
//                         click: function(){
//                             $$("$sidebar").toggle();
//                         }
//                     },
//                     { view: "label", label: "Coffee Manager"},
//                     {},
//                 ]},
//             { cols:[
//                     { view: "sidebar", width:300, data: menu_data_multi, on:{
//                             onAfterSelect: function(id){
//                                 webix.message("Selected: "+this.getItem(id).value)
//                             }
//                         }},
//                     { template: "" }
//                 ]},
//             {
//                 id: 'root'
//             }
//
//         ]
//     });
//
//
// });