var jsview = View.extend({
    uis: {
        magazineGrid: {
            el: '#magazineGrid',
            type: 'datagrid',
            config: {
                title: 'title',
                width: '700px',
                height: '250px',
                singleSelect: true,
                collapsible: true,
                url: SYS.stc + '/test/datagrid_data1.json',
                method: 'get',
                columns: [[
                        {field: 'itemid', title: 'Item ID', width: 80, editor: 'text'},
                        {field: 'productid', title: 'Product', width: 150, editor: 'text'},
                        {field: 'listprice', title: 'List Price', width: 100, align: 'right', editor: {type: 'numberbox', options: {precision: 2}}},
                        {field: 'unitcost', title: 'Unit Cost', width: 100, align: 'right', editor: {type: 'numberbox'}},
                        {field: 'attr1', title: 'Attribute', width: 100, editor: 'text'},
                        {field: 'status', title: 'Status', width: 100, align: 'center',
                            editor: {type: 'checkbox', options: {on: 'Y', off: 'N'}}
                        }
                    ]],
                tools: [{
                        iconCls: 'icon-add',
                        handler: function () {
                            alert('new')
                        }
                    }, {
                        iconCls: 'icon-save',
                        handler: function () {
                            alert('save')
                        }
                    }],
                onLoadSuccess: function (data) {
                    console.log($(this).datagrid('getData'));
                }
            },
            render: function () {
                this.eui.datagrid('enableCellEdit');
            }
        },
        cc: {
            el: '#cc',
            type: 'calendar',
            config: {
                width: '200px',
                height: '200px',
                current: new Date()
            },
            render: function () {
            }
        }
    },
    render: function () {

    }
});

