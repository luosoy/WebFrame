var ViewList = [];
var View = {
    render: $.noop,
    _cmp: {
        config: {
        },
        render: $.noop
    },
    _init: function () {
        if (!this.$el || !this.$el.length) {
            this.$el = $(this.el);
            if (!this.$el || !this.$el.length) {
                this.$el = $('body');
            }
        }
        this._initUI();
        this.render.apply(this);
        return this;
    },
    _initUI: function () {
        var base = this;
        base.uis = base.uis || {};
        $.each(base.uis, function (key, obj) {
            obj._id = key;
            obj.el = obj.el ? obj.el : '#' + key;
            obj.$el = obj.$el ? obj.$el : $(obj.el);
            var $ui = obj.$el;
            if (!$ui || !$ui.length) {
                return;
            }
            var id = $ui.attr('id'),
                    opts = $ui.data(),
                    data = $.extend({}, opts),
                    uis = base.uis,
                    type = obj.type,
                    cmp, eui, config;

            if ($ui.data('inited')) {
                return;
            }
            if (!opts.id) {
                if (id) {
                    opts.id = id;
                } else {
                    opts.id = opts.name;
                }
            }
            base.uis[key] = cmp = $.extend(true, {}, base._cmp, obj);
            config = $.extend(true, {
                required: opts.required,
                readonly: opts.readonly,
                value: opts.value
            }, cmp.config);
            if ($ui[type]) {
                cmp.eui = eui = $ui[type](config);
            } else {
                console.error("[", type, "] no this type for easyui");
                return;
            }

            cmp.uis = eui.uis = uis;
            cmp.view = eui.view = base;
            eui.ui = cmp;
            cmp.data = data;
            try {
                cmp.render.call(cmp, eui, opts);
            } catch (e) {
                console.error(eui, opts.id, ' render error! ', e);
            }
            $ui.data('inited', true);
        });
    },
    extend: function (config) {
        var ViewExtend = $.extend(true, {}, View, config);
        ViewList.push(ViewExtend);
        return ViewExtend;
    }
};


$.extend($.fn.datagrid.methods, {
    editCell: function (jq, param) {
        return jq.each(function () {
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] !== param.field) {
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            var ed = $(this).datagrid('getEditor', param);
            if (ed) {
                if ($(ed.target).hasClass('textbox-f')) {
                    $(ed.target).textbox('textbox').focus();
                } else {
                    $(ed.target).focus();
                }
            }
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    },
    enableCellEdit: function (jq) {
        return jq.each(function () {
            var dg = $(this);
            var opts = dg.datagrid('options');
            opts.oldOnClickCell = opts.onClickCell;
            opts.onClickCell = function (index, field) {
                if (opts.editIndex !== undefined) {
                    if (dg.datagrid('validateRow', opts.editIndex)) {
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                    } else {
                        return;
                    }
                }
                dg.datagrid('selectRow', index).datagrid('editCell', {
                    index: index,
                    field: field
                });
                opts.editIndex = index;
                opts.oldOnClickCell.call(this, index, field);
            };
        });
    }
});


$(function () {
    if (ViewList.length) {
        $.each(ViewList, function () {
            this._init();
        });
    } else {
        View._init();
    }
});