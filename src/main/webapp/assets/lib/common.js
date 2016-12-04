/*
 * 全局变量.
 */
var S = SYS = $.extend(SYS || {}, {});
try {
    console.log;
} catch (e) {
    console = {
        log: $.noop,
        info: $.noop,
        error: $.noop
    };
}

console = console || {
    log: $.noop,
    info: $.noop,
    error: $.noop
};
/**
 * ajax全局设置.
 */
$.ajaxSetup({
    beforeSend: function (jqx, st) {
    },
    ajaxStart: function () {
    },
    ajaxSend: function () {
    },
    type: 'post'
});
$.jqAjax = $.ajax;
$.ajax = $.send = function (settings) {
    settings.url = settings.url || '';
    var newst = $.extend(true, {}, settings, {
        url: (settings.url || '').indexOf('/') === 0 ? (SYS.ctx + settings.url) : (SYS.path + settings.url),
        success: function (data) {
            var args = arguments, ndata = data, func;
            if (ndata.content) {
                if ('SESSION_TIMEOUT' === ndata.content) {
                    window.location.reload();
                    return;
                } else if (!settings.nomsg) {
                    if (ndata.contentList && ndata.contentList.length) {
                        $.messager.alert(ndata.contentList.join('<br>'));
                    } else {
                        $.messager.alert(ndata.content);
                    }
                }
            }
            func = ndata.type === 'SUCCESS' ? settings.success : settings.failed;
            if (func) {
                func.call(this, settings.isMini ? ndata.data : ndata, args[1], args[2]);
            }
        },
        error: function (jqx, type, text) {
            var args = arguments;
            if (jqx.status !== 0 && !settings.nomsg) {
                $.messager.alert('[' + jqx.status + '] ' + text, jqx.status);
            }
            settings.error && settings.error.call(this, jqx, type, text);
        },
        complete: function (jqx, text) {
            settings.complete && settings.complete.call(this, jqx, text);
        }
    });
    if (newst.needpid !== false) {
        if (newst.data) {
            if (!$.isPlainObject(newst.data)) {
                newst.data = $.parseJSON(newst.data);
            }
            newst.data.partition = SYS.pid;
        } else {
            newst.data = {
                partition: SYS.pid
            };
        }
    }
    if (newst.isjson
            || (newst.contentType || jQuery.ajaxSettings.contentType)
            .indexOf('json') > -1 && $.isPlainObject(newst.data)) {
        newst.contentType = 'application/json;charset=UTF-8';
        newst.data = JSON.stringify(newst.data);
    }
    return $.jqAjax(newst);
};

var DateUtils = SYS.DateUtils = {padLeft: function (s, l, c) {
        s = '' + s;
        if (l < s.length)
            return s;
        else
            return Array(l - s.length + 1).join(c || ' ') + s;
    },
    parseValue: function (val, fmt) {
        var today = new Date,
                params = val.split(','),
                d = {oy: params[0] || 'y',
                    oM: params[1] || 'M',
                    od: params[2] || 'd'
                };
        d.y = eval(d.oy.replace('y', today.getFullYear()));
        today.setYear(d.y);
        d.M = eval(d.oM.replace('M', today.getMonth() + 1));
        while (d.M < 1) {
            d.M = d.M + 12;
            d.y--;
        }
        while (d.M > 12) {
            d.M = d.M - 12;
            d.y++;
        }
        today.setYear(d.y);
        if (d.od.indexOf('ld') > -1) {
            today.setMonth(d.M);
            today.setDate(0);
            d.d = eval(d.od.replace('ld', today.getDate()));
        } else {
            d.d = eval(d.od.replace('d', today.getDate()));
        }
        today.setMonth(d.M - 1);
        today.setDate(d.d);
        d.y = today.getFullYear();
        d.M = today.getMonth() + 1;
        d.d = today.getDate();
        d.date = $.formatDate(d.y + '/' + d.M + '/' + d.d, fmt);
        return d;
    },
    formatValue: function (val, fmt) {
        return DateUtils.parseValue(val, fmt).date;
    }
};
$.formatDate = function (date, ptn) {
    if (typeof date !== Date) {
        date = new Date(date);
    }
    ptn = ptn || "yyyy-MM-dd";
    date = date || new Date();
    var dt = {// 年份
        "yyyy": date.getFullYear(),
        // 月份
        "MM": date.getMonth() + 1,
        // 日
        "dd": date.getDate(),
        // 小时
        "hh": date.getHours(),
        // 分
        "mm": date.getMinutes(),
        // 秒
        "ss": date.getSeconds(),
        // 季度
        "q": Math.floor((date.getMonth() + 3) / 3),
        // 毫秒
        "SSS": date.getMilliseconds()
    };
    dt.MM = DateUtils.padLeft(dt.MM, 2, '0');
    dt.dd = DateUtils.padLeft(dt.dd, 2, '0');
    dt.hh = DateUtils.padLeft(dt.hh, 2, '0');
    dt.mm = DateUtils.padLeft(dt.mm, 2, '0');
    dt.ss = DateUtils.padLeft(dt.ss, 2, '0');
    for (var key in dt) {
        ptn = ptn.replace(key, dt[key]);
    }
    return ptn;
};

$.submit = function (opts) {
    opts = opts || {};
    opts.url = opts.url || '';
    var $form = $('<form>').appendTo('body'), data = {};
    var attr = {action: opts.url.indexOf('/') === 0 ? (SYS.ctx + opts.url) : (SYS.path + opts.url),
        method: opts.type || opts.method || 'POST',
        target: opts.target
    };
    if (opts.target) {
        attr.target = opts.target;
    }
    $form.attr(attr);
    if (opts.data) {
        data = SysUtils.j2d(opts.data);
    }
    data.partition = SYS.pid;
    for (var key in data) {
        $form.append($('<input>').attr({
            type: 'hidden',
            name: key,
            value: data[key]
        }));
    }
    $form.submit();
};

var SysUtils = SYS.SysUtils = {a2d: function (value, preKey) {
        var data = {}, key, val;
        preKey = preKey + '[_IDX_]';
        for (var i = 0; i < value.length; i++) {
            val = value[i];
            key = preKey.replace('_IDX_', i);
            if ($.isPlainObject(val)) {
                $.extend(data, this.j2d(val, key, key.indexOf('Map', key.length - 3) !== -1));
            } else if ($.isArray(val)) {
                $.extend(data, this.a2d(val, key));
            } else {
                data[key] = val;
            }
        }
        return data;
    },
    j2d: function (value, preKey, isMap) {
        var data = {}, key, val;
        if (preKey) {
            preKey = preKey + (isMap ? '[_KEY_]' : '._KEY_');
        } else {
            preKey = '_KEY_';
        }
        for (var ckey in value) {
            val = value[ckey];
            key = preKey.replace('_KEY_', ckey);
            if ($.isPlainObject(val)) {
                $.extend(data, this.j2d(val, key, ckey.indexOf('Map', ckey.length - 3) !== -1));
            } else if ($.isArray(val)) {
                $.extend(data, this.a2d(val, key));
            } else {
                data[key] = val;
            }
        }
        return data;
    },
    j2fArray: function ($fm, preifx, json, opts) {
        var keypre = prefix + key, val = json[key];
        for (var i = 0; i < val.length; i++) {
            var obj = val[i];
            keypre = keypre + '[' + i + ']';
            if ($.isPlainObject(val)) {
                this.j2f($fm, keypre + '.', obj, opts);
            } else if ($.isArray(val)) {
                this.j2fArray($fm, keypre, obj, opts);
            } else {
                var $fd = $fm.find('[name="' + keypre + '"]');
                if ($fd.is('input, select, textarea')) {
                    $fd.val(val);
                } else {
                    if (opts) {
                        $fd.text(val);
                    } else {
                        $fd.html(val);
                    }
                }
                $fd.attr('title', val);
            }
        }
    },
    v2f: function ($fm, key, val, opts) {
        var $fd = $fm.find('[name="' + key + '"]');
        if ($fd.is('input, select, textarea')) {
            $fd.val(val);
        } else {
            if (opts.encode) {
                $fd.text(val);
            } else {
                $fd.html(val);
            }
        }
        $fd.attr('title', val);
    },
    j2f: function ($fm, prefix, json, opts) {
        for (var key in json) {
            var keypre = prefix + key, val = json[key];
            if ($.isPlainObject(val)) {
                this.j2f($fm, keypre + '.', val, opts);
            } else if ($.isArray(val)) {
                this.j2fArray($fm, keypre, val, opts);
            } else {
                this.v2f($fm, keypre, val, opts);
            }
        }
    },
    REG: /(.*?)\[(\d+)+\]/,
    f2j: function (keys, value, json) {
        var key = keys[0],
                regs = this.REG.exec(key),
                isArray = regs && regs.length > 0,
                key = isArray ? regs[1] : key;
        data = json[key];
        keys = keys.slice(1);
        if (!data) {
            data = isArray ? [] : {};
            json[key] = data;
        }
        if (!keys || !keys.length) {
            if (isArray) {
                var tdata = data;
                for (var i = 3; i < regs.length - 1; i++) {
                    data[regs[i - 1]] = tdata = data[regs[i - 1]] || [];
                }
                tdata[regs[regs.length - 1]] = value;
            } else {
                json[key] = value;
            }
        } else {
            json[key] = this.f2j(keys, value, data);
        }
        return json;
    }
};

$.fn.j2f = function (json, opts) {
    opts = opts || {};
    var type = typeof opts == 'string';
    if (type == 'string' || type == 'boolean') {
        opts.encode = (opts == true);
    }
    SysUtils.j2f($(this), "", json, opts);
};

$.fn.f2j = function (encode) {
    var json = {}, data;
    $(this).find('[name]').each(function () {
        var $el = $(this),
                key = $el.attr('name'),
                k = key.split('.'),
                val;
        if ($el.is('select, textarea, input')) {
            val = $.trim($el.val());
        } else {
            val = $.trim($el.text());
        }
        json = SysUtils.f2j(k, val, json);
    });
    return json;
};

var ViewList = [];
var View = {render: $.noop,
    _cmp: {config: {},
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
            config = $.extend(true, {required: opts.required,
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


$.extend($.fn.datagrid.methods,
        {
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
                        dg.datagrid('selectRow', index).datagrid('editCell', {index: index,
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