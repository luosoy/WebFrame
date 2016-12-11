var view = View.extend({
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
                url: '/assets/test/datagrid_data1.json',
                method: 'get',
                columns: [
                    {field: 'itemid', header: 'Item ID', name: 'Item ID', width: 80},
                    {field: 'productid', header: 'Product', width: 150},
                    {field: 'listprice', header: 'List Price', width: 100, align: 'right'},
                    {field: 'unitcost', header: 'Unit Cost', width: 100, align: 'right'},
                    {field: 'attr1', header: 'Attribute', width: 100},
                    {field: 'status', header: 'Status', width: 100}
                ]
            },
            render: function () {
            }
        },
        cc: {
            el: '#cc',
            type: 'calendar',
            config: {

            }
        }
    },
    render: function () {

    }
});

