/**
 * 基础设置.
 * 
 * @author luozp
 */
/*
 * 全局变量.
 */
var S = SYS = $.extend(SYS || {}, {});
var mini_debugger = false;
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
    SysUtils.mask($(settings.mask));
    settings.url = settings.url || '';
    
    var newst = $.extend(true, {}, settings, {
        url: (settings.url || '').indexOf('/') == 0 ? (SYS.ctx + settings.url) : (SYS.path + settings.url),
        type : settings.ajaxType || settings.type || "get",
        success: function (data) {
            var args = arguments, ndata = data, func;
            ndata = mini.decode(ndata);
            if (ndata.content && !settings.failed) {
                if ('SESSION_TIMEOUT' == ndata.content) {
                    window.location.reload();
                    return;
                } else if (!settings.nomsg) {
                    if (ndata.contentList && ndata.contentList.length) {
                        mini.alert(ndata.contentList.join('<br>'));
                    } else {
                        mini.alert(ndata.content);
                    }
                }
            }
            func = ndata.type == 'SUCCESS' ? settings.success : settings.failed;
            if (func) {
                func.call(this, settings.isMini ? ndata.data : ndata, args[1], args[2]);
            }
        },
        error: function (jqx, type, text) {
            var args = arguments;
            if (jqx.status != 0 && !settings.nomsg) {
                mini.alert('[' + jqx.status + '] ' + text, jqx.status);
            }
            settings.error && settings.error.call(this, jqx, type, text);
        },
        complete: function (jqx, text) {
            SysUtils.unmask($(settings.mask));
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
var DateUtils = SYS.DateUtils = {
    padLeft: function (s, l, c) {
        s = '' + s;
        if (l < s.length)
            return s;
        else
            return Array(l - s.length + 1).join(c || ' ') + s;
    },
    parseValue: function (val, fmt) {
        var today = new Date,
                params = val.split(','),
                d = {
                    oy: params[0] || 'y',
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
    var dt = {
        // 年份
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

$.fn.mask = function (opt) {
    return this.each(function () {
        var $el = $(this),
                dis = {
                    w: $el.outerWidth(),
                    h: $el.outerHeight()
                };
        if (opt === undefined) {
            opt = !$el.find('>.icon-spinner.icon-spin').length;
        }
        if (opt == true) {
            $el.find('.mask-loading').remove();
            $('<div class="mask-loading"></div>').appendTo($el).css({
                lineHeight: dis.h + 'px'
            });
        } else {
            $el.find('.mask-loading').remove();
        }
    });
};
$.submit = function (opts) {
    SysUtils.mask($(opts.mask));
    opts = opts || {};
    opts.url = opts.url || '';
    var $form = $('<form>').appendTo('body'), data = {};
    var attr = {
        action: opts.url.indexOf('/') == 0 ? (SYS.ctx + opts.url) : (SYS.path + opts.url),
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

var SysUtils = SYS.SysUtils = {
    mask: function ($els) {
        $els.each(function () {
            var $this = $(this),
                    $mui = $this.mini();
            if (mini.isControl($mui)) {
                $mui.disable();
            } else if ($this.is('body')) {
                mini.mask({
                    "iconCls": "mini-messagebox-waiting",
                    "message": "数据加载中..."
                });
            }
        });
    },
    unmask: function ($els) {
        $els.each(function () {
            var $this = $(this),
                    $mui = $this.mini();
            if (mini.isControl($mui)) {
                $mui.enable();
            } else if ($this.is('body')) {
                mini.unmask();
            }
        });
    },
    a2d: function (value, preKey) {
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
$.fn.j2m = function (data) {
    $.each(this, function () {
        new mini.Form(this).setData(data);
    });
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
$.fn.m2j = function (json, encode) {
    var arrs = $.map(this, function (dom) {
        return (new mini.Form(dom)).getData();
    });
    return arrs.length == 1 ? arrs[0] : arrs;
};
$.fn.miniAttr = function (el) {
    var ui = new name();
    return ui.getAttrs(el) || {};
};
$.fn.mini = function (name, opts) {
    var tmp;
    if (typeof name == 'string') {
        name = mini.getClassByUICls('mini-' + name);
    }
    if ($.isFunction(name)) {
        tmp = this.map(function () {
            var el = this, $el = $(el), ui = $el.data('mini'), cfg;
            if (ui)
                return this;
            ui = new name(); // 创建组件
            cfg = ui.getAttrs(el) || {};
            cfg.cls = cfg.cls ? cfg.cls.replace(/(?:ui-\w*|fn-hide)\s*/g, '') : '';
            opts = $.extend((cfg || {}), opts);
            if (opts.replace) {
                $el.replaceWith(ui.el);
                $el = $(ui.el);
            } else {
                $el.html(ui.el);
            }
            ui.set(opts || {});
            ui.$ctn = $el;
            $el.data('mini', ui);
            if (!opts.replace) {
                $(ui.el).attr('id', $el.attr('id') + '_mini');
            }
            return ui;
        });
    } else {
        tmp = this.map(function () {
            return $(this).data('mini');
        });
    }
    if (tmp && tmp.length === 1)
        return tmp.get(0);
};


mini._open = mini.open;
mini.open = function (opts) {
    opts.url = opts.url.indexOf('/') == 0 ? (SYS.ctx + opts.url) : (SYS.path + opts.url);
    return mini._open(opts);
};
var Global = {
    component: {
        combobox: {
            config: {
                autoLoad: true,
                textField: 'label',
                valueField: 'value',
                showNullItem: true,
                value: '',
                nullItemText: '请选择',
                width: '100%',
                cache: true,
                popupWidth: '100%'
            }
        },
        radiobuttonlist: {
            config: {
                textField: 'label',
                valueField: 'value'
            }
        },
        checkboxlist: {
            config: {
                textField: 'label',
                valueField: 'value'
            }
        },
        textbox: {
            config: {
                width: '100%'
            }
        },
        moneybox: {
            config: {
                width: '100%',
                minValue: 0,
                maxValue: 99999999999999.99
            }
        },
        datagrid: {
            config: {
                width: '100%'
            }
        },
        datepicker: {
            config: {
                width: '100%'
            }
        },
        monthpicker: {
            config: {
                width: '100%'
            }
        },
        yearpicker: {
            config: {
                width: '100%'
            }
        },
        button: {
            config: {
                replace: true
            }
        },
        treeselect: {
            config: {
                valueField: 'value',
                textField: 'label',
                parentField: 'parent',
                width: '100%',
                popupWidth: '100%',
                emptyText: '请选择'
            }
        },
        window: {
            config: {
                replace: true
            }
        },
        panel: {
            config: {
                width: '100%'
            }
        },
        tabs: {
            init: function (config) {
                $.each(config.tabs, function (idx, obj) {
                    obj.bodyParent = obj.bodyParent ? obj.bodyParent : $(obj.el).get(0)
                });
                return config;
            }
        }
    }
};
var ViewList = [];
var View = {
    autoRun: true,
    minis: {},
    _cmp: {
        config: {
            autoLoad: false
        },
        render: $.noop
    },
    _loading: 0,
    ui: {},
    render: $.noop,
    afterLoad: $.noop,
    extendView: {
        init: $.noop,
        afterRender: $.noop,
        ui: {}
    },
    _init: function () {
        if (!this.$el || !this.$el.length) {
            this.$el = $(this.el);
            if (!this.$el || !this.$el.length) {
                this.el = 'body';
                this.$el = $('body');
            }
        }
        this._initUI();
        this.render.apply(this);
        return this;
    },
    _initUI: function () {
        var base = this, map = {}, __length = 0;
        if (!this.data) {
            var elData = this.data = {};
            this.$el.find('input[type=hidden]').each(function () {
                elData[this.name] = this.value;
            });
            this.data = $.extend(elData, $('#' + this.el.replace('#', '') + '-data').data());
        }
        base.uis = base.uis || {};
        var tempuis = $.extend({}, base.uis);
        $.each(base.uis, function (key, obj) {
            obj._id = key;
            obj.el = obj.el ? obj.el : '#' + key;
            map[obj.el] = obj;
            obj.$el = obj.$el ? obj.$el : $(obj.el);
            obj.$el.attr("mini", true);
            __length++;
        });
        this.$el.find('[class^="ui-"], [class*=" ui-"]').each(function () {
            var $el = $(this),
                    data = $el.data(),
                    id = $el.attr('id') || data.name,
                    el = '#' + id,
                    uis = base.uis,
                    attr = $.grep($el.attr('class').split(' '), function (key) {
                        return key.indexOf('ui-') == 0;
                    }),
                    type = attr[0].replace('ui-', ''),
                    ui = map[el] || {el: el, _id: id};
            ui.type = type;
            ui.$el = $el;
            uis[ui._id] = ui;
        });

        while (__length != 0) {
            $.each(tempuis, function (key, obj) {
                var $mini = obj.$el,
                        innertmpuis = {};
                if ($mini.parents('[mini]').length) {
                    return;
                }
                __length--;
                delete tempuis[key];
                if (!$mini || !$mini.length) {
                    return;
                }
                obj.$el = $mini;
                var id = $mini.attr('id'),
                        opts = $mini.data(),
                        data = $.extend({}, opts),
                        uis = base.uis,
                        type = obj.type,
                        dCfg = Global.component[type] || {},
                        cmp, mini, config;
                if ($mini.data('inited')) {
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
                }, dCfg.config, cmp.config);
                // code存在，从后台获取数据
                opts.code = opts.code || config.code;
                opts.url = opts.url || config.url;
                delete config.url;
                delete config.code;
                if ($.isFunction(dCfg.init)) {
                    config = dCfg.init(config);
                }
                base.minis[opts.id] = cmp.mui = mui = $mini.mini(type, config);
                if (!mui) {
                    console.error('ui "' + opts.id + '" not exist.');
                    return;
                }
                if (config.replace) {
                    cmp.$el = $(mui.el);
                }
                cmp.uis = mui.uis = uis;
                cmp.view = mui.view = base;
                mui.ui = cmp;
                cmp.data = data;
                cmp.param = $.extend(base._generateDynParam(opts), cmp.param);
                data.param = cmp.param;
                if (opts.url) {
                    base._loading++;
                    mui.url = config.url || opts.url;
                    mui.load(mui.url, cmp.param);
                }

                try {
                    cmp.render.call(cmp, mui, opts);
                } catch (e) {
                    console.error(mui, opts.id, ' render error! ', e);
                }
                $mini.data('inited', true);
                $mini.removeAttr("mini");
            });
        }
    },
    _generateDynParam: function (opts) {
        var base = this,
                json = {};
        $.each(opts, function (key, value) {
            if (typeof value === 'string' || typeof value === 'number') {
                if (key.indexOf('param') === 0) {
                    json[base._replaceLetter(key.substring(5))] = value;
                }
            }
        });
        return json;
    },
    _replaceLetter: function (str) {
        return str.replace(/^[A-Z]/, function (letter) {
            return letter.toLowerCase();
        });
    },
    extend: function (config) {
        var ViewExtend = $.extend(true, {}, View, config);
        ViewList.push(ViewExtend);
        return ViewExtend;
    },
    require: function () {

    },
    get: function (id) {
        return this.minis[id];
    },
    init: function () {
        this._init();
        return this;
    },
    require: function () {

    }
};
$(function () {
    if (ViewList.length) {
        $.each(ViewList, function () {
            if (this.autoRun) {
                this._init();
            }
        });
    } else {
        View.autoRun && View._init();
    }
});