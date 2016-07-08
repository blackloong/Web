/**
 * 信息提示框的关闭按钮动作处理类
 * 
 * @param {}
 *            obj
 * @param {}
 *            closeObj
 */
tt.closeHandler = function(obj, closeObj, e, eCls) {
	this.click = function() {
		tt.rmEle(obj);
		tt.rmCls(e, eCls + '_1');
	};
};
tt.msgHandler = function(e, cls) {
	var _cls = cls + "_1";
	this.mouseover = function() {
		tt.addCls(e, _cls);
	};
	this.mouseout = function() {
		tt.rmCls(e, _cls);
	};
	this.click = function() {
		try {
			e.focus(true);
		} catch (e) {}
	};
};

/**
 * baseHandler
 */
tt.bh = tt.C.ext({
	setV:function(v)
	{
		this.v = v;
		return this;
	},
	setE:function(e)
	{
		this.e = e;
		return this;
	},
	setF:function(f)
	{
		this.f = f;
		return this;
	},
	setVal:function(val)
	{
		this.val = val;
		return this;
	},
	setIndex:function(index)
	{
		this.index = index;
		return this;
	},
	needHandle:function()
	{
		return !(this.e.style.display == 'none' || this.e.disabled)//对于不可见的元素,不处理
	},
	render:function(cls, msg, closeCls, inputCls) {
			var e = this.e;
			var types = tt.inputType(e);
			var divWrap = document.createElement("div");
			var div = document.createElement("div");
			divWrap.appendChild(div);
			
			var msgId = this.f.getMsgId(e);
			if (msgId) {
				tt.getById(msgId).appendChild(divWrap);
			} else {
				if (types.isCheckbox || types.isRadio) {
					tt.moveToPos(divWrap, this.f.es[this.f.es.length - 1]);
				} else {
					tt.moveToPos(divWrap, e);
				}
				e.parentNode.insertBefore(divWrap, e);
				tt.vf.msgs.push({"msg":divWrap,"ele":e});
			}
			
			divWrap.id = e[tt.Conf.proNameOfMsgId];
			divWrap.className = cls;
			div.innerHTML = msg;
			div.style.display = "inline";
			
			var close = document.createElement("div");
			divWrap.appendChild(close);
			
			close.className = closeCls;
			close.innerHTML = "X";
			close.title = tt.close;
			
			tt.addCls(e, inputCls);
			
			tt.Util.addEventHandler(close, "click", new tt.closeHandler(divWrap, close, e, inputCls).click);
			
			tt.Util.addEventHandler(div, "click", new tt.msgHandler(e, inputCls)["click"]);
			tt.Util.addEventHandler(divWrap, "mouseover", new tt.msgHandler(e, inputCls)["mouseover"]);
			tt.Util.addEventHandler(divWrap, "mouseout", new tt.msgHandler(e, inputCls)["mouseout"]);
			
	}
});