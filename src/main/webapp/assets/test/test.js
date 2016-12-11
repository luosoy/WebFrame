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
                columns: [
                    {field: 'name', header: '名称', name: 'name', width: 80},
                    {field: 'age', header: '年龄', name: 'age', width: 150}
                ]
//                onbeforepagechanged: function (e) {
//                    alert(1);
//                    this.ui.loadTable();
//                }
            },
            render: function () {
                this.loadTable();
            },
            loadTable: function () {
                var grid = this;
                $.send({
                    url: "test/findTest",
                    isjson: true,
                    ajaxType: "post",
                    success: function (data) {
                        grid.mui.setData(data.data);
                        grid.mui.setTotalCount(data.data.length);
                    }
                });
            }
        },
        cc: {
            el: '#cc',
            type: 'ComboBox',
            config: {
                url: "test/findTest",
                isjson: true,
                ajaxType: "post",
                textField: "name",
                valueField: "age"
            }
        }
    },
    render: function () {

    }
});

