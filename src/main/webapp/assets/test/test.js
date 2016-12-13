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
                    {type: 'indexcolumn', header: '序号'},
                    {field: 'name', header: '名称', name: 'name', width: 80},
                    {field: 'age', header: '年龄', name: 'age', width: 150}
                ],
                onpagechanged: function (e) {
                    this.ui.loadTable({
                        pageIndex: e.pageIndex,
                        pageSize: e.pageSize,
                        sortField: 'age',
                        sortOrder: 'ASC'
                    });
                }
            },
            render: function () {
                this.loadTable({
                    pageIndex: this.mui.pageIndex,
                    pageSize: this.mui.pageSize,
                    sortField: 'age',
                    sortOrder: 'ASC'
                });
            },
            loadTable: function (page) {
                var grid = this;
                $.send({
                    url: "test/findTest",
                    data: page,
                    isjson: true,
                    ajaxType: "post",
                    success: function (data) {
                        grid.mui.setData(data.data.data);
                        grid.mui.setTotalCount(data.data.totalCount);
                    }
                });
            }
        }
    },
    render: function () {

    }
});



