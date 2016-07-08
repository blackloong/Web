tt.vHandler = function(e) {
	this.h = function() {
		if (tt.Conf.errorStyle == 'alert') {
			return;
		}
		tt.validateElement(e);
	},
	this.talent_getId = function(){
		return tt.Conf.eventId;
	}
};

/**
 * @author Tanyaowu
 * @version 2.0.0
 * @date 2011-8-13
 * BaseValidator
 */
tt.BV = tt.C.ext({
	init:function() {
		this.fs = [];
		this.i18ps = [];
		this.isInFactory = false;// 本验证器是否已经在验证器工厂中了.false:不在工厂中;true:已在工厂中.
		this.clrSpace = tt.Conf.clrSpace;
		this.vType = 'local';
	},
	initI18n : function(m) {
		!this.i18n ? this.i18n = m : null;
		return this;
	},
	setI18 : function(m) {
		this.i18n = m;
		return this;
	},
	setClrSpace:function(c){
		this.clrSpace = c;
	},
	
	/**
	 * 
	 * @param {} s 需要被验证的串，根据配置此串有可能清除了两边的空格
	 * @param {} i index 当前元素序号，从0开始
	 * @param {} es elements
	 * @param {} f Field
	 * @return {Boolean} true:验证通过
	 */
	v : function(s, i, es, f) {
		return true;
	},
	/**
	 * 
	 * @param {} f Field
	 */
	doAfterAdd : function(f) {
	},
	/**
	 * 当移除后做些事情,子类视情况实现该函数,如Required验证器,需要去掉后面的红星号
	 */
	doBeforeRm : function(f) {
		this.clrFErr(f);
	},
	/**
	 * 
	 * @param {} fl filter
	 * @return {Boolean}
	 */
	vBf : function(fl) {
		var ret = true;
		if (this.vType == 'remote') {
			if (['a','f'].ttCons(tt.vf.vType)){
				this.v();
			}
			return;
		}
		
		for (var i = 0; i < this.fs.length; i++) {
			var es = this.fs[i].es;
			if (es) {
				for (j = 0; j < es.length; j++) {
					if (tt.vf.invalidEs.ttCons(es[j])){  //没有通过前面的验证器
						continue;
					}
					
					/** 不需要验证或者验证通过则继续下一个元素的处理 */
					if (fl && !fl.f(es[j])) {  //被过滤了，不需要验证
						if (tt.Conf.clearOtherError){
							this.clrEleErr(es[j]);
						}
						continue;
					}
					
					var types = tt.inputType(es[j]);
					
					var sv = es[j].value;
					
					if (this.clrSpace && (!types['isSelect'] && !types['isCheckbox'] && !types['isRadio'])) {
						sv = tt.trim(sv);
						if(!["e"].ttCons(tt.vf.vType)) {
							es[j].value = sv;
						}
					}
					
					if (types['isRadio'] || types['isCheckbox']){
						this.clrFErr(this.fs[i]);
					}else{
						this.clrEleErr(es[j]);
					}
					if(this.v(sv, j, es, this.fs[i])) {  //验证通过
						this.handTip(es[j], this.fs[i], sv, j);
						continue;
					} else {
						tt.vf.invalidEs.push(es[j]);
						this.handErr(es[j], this.fs[i], sv, j);
						ret = false;
					}
				}
			}
		}
		return ret;
	},

	/**
	 * 移除字段 用法:xx.rm("name1", "name2", "name3"...);
	 */
	rm : function() {
		return this._rm("name", arguments);
	},
	/**
	 * 移除字段 用法:xx.rmId("id1", "id2", "id3"...);
	 */
	rmId : function() {
		return this._rm("id", arguments);
	},
	_rm:function(type, args){
		for (var i = 0; i < args.length; i++) {
			for (var j = 0; j < this.fs.length; j++) {
				var f = false;
				if (typeof args[i] != "string"){
					f = (this.fs[j] && this.fs[j] == args[i]);
				} else {
					f = (this.fs[j] && this.fs[j][type] == args[i]);
				}
				
				if (f) {
					this.doBeforeRm(this.fs[j]);
					this.fs[j] = null;
				}
			}
		}
		this.fs = tt.rmNull(this.fs);
		return this;
	},
	
	/**
	 * 移除所有字段 用法:xxValidator.rmAll();
	 */
	rmAll : function() {
		for (var i = 0; i < this.fs.length; i++) {
			this.rm(this.fs[i]);
		}
		this.fs = [];
	},
	/**
	 * 将要验证的字段加到验证器中 用法:xx.add("name1", "name2", "name3"...);
	 */
	add : function() {
		return this._addF('name', arguments);
	},
	/**
	 * 将要验证的字段加到验证器中 用法:xx.addId("id1", "id2", "id3"...);
	 */
	addId : function() {
		return this._addF('id', arguments);
	},
	_addF : function(type, arg){
		for (var i = 0; i < arg.length; i++) {
			var f = null;
			if (type == 'id'){
				if (this._c('name',tt.getById(arg[i]).name) && this._c('id', arg[i])) {
					f = new tt.Field("", null, arg[i]);
				}
			} else {
				isStr = (typeof arg[i] == 'string');
				var fg = false;
				if (isStr){
					fg = this._c('name', arg[i]);
				}else{
					fg = (this._c('name', arg[i].name) && this._c('id', arg[i].id));
				}
				if (fg) {
					if (isStr) {
						f = new tt.Field("", arg[i]);
					} else {
						f = arg[i];
					}
				}
			}
			
			if (f != null){
				this.fs[this.fs.length] = f;
				for (var j = 0; j < f.es.length; j++) {
					if (f.es[j].tt_addedEvent){
						continue;
					}
					f.es[j].tt_addedEvent = true;
					this.attachE(f.es[j]);
				}
				
				if (!this.isInFactory)// 必要时添加验证器到工厂中
				{
					tt.vf.add(this);
					this.isInFactory = true;
				}
				this.doAfterAdd(f);
			}
		}
		return this;
	},
	/**
	 * 
	 * @param {} proName
	 * @param {} value
	 * @return {Boolean}
	 */
	_c:function(proName, value) {
		if (!value) {
			return true;
		}
		
		for (var i = 0; i < this.fs.length; i++) {
			if (proName){
				if (this.fs[i][proName] == value) {
					return false;
				}
			}else{
				if (this.fs[i] == value) {
					return false;
				}
			}
			
		}
		return true;
	},
	/**
	 * 处理没有验证通过的对象,例如对这个对象进行选中,将焦点转到该对象,修改该对象的样式等
	 */
	handErr : function(e, f, val, j) {
		var h = tt.instanceByClass("tt." + tt.Conf.errorStyle);
		h.setV(this).setE(e).setF(f).setVal(val).setIndex(j).h();
	},
	handTip : function(e, f, val, j) {
		var h = tt.instanceByClass("tt." + tt.Conf.tipStyle);
		h.setV(this).setE(e).setF(f).setVal(val).setIndex(j).h();
	},
	/**
	 * 验证不通过时，获取提示给用户的信息
	 * 
	 * @param label
	 */
	getI18 : function(label) {
		if (this.i18n){
			ret = tt.getI18S(this.i18n, [label], 0);
			return tt.getI18S(ret, this.i18ps, 1);
		}
		return null;
	},
	setTip : function(tip) {
		this.tip = tip;
		return this;
	},
	
	/**
	 * 
	 * @param {} e element
	 * @param {} f field
	 * @param {} v validator
	 * @param {} val
	 * @param {} index
	 * @return {}
	 */
	getTip : function(e,f,v,val,index) {
		return this.tip;
	},
	/**
	 * 清空field的错误
	 */
	clrFErr : function(f) {
		var es = f.es;
		for (i =0; i< es.length; i++) {
			this.clrEleErr(es[i]);
		}
	},
	clrEleErr:function(e){
		if (e){
			tt.rmCls(e, tt.Conf.errInputCls);
			tt.rmEle(tt.getById(e[tt.Conf.proNameOfMsgId]));
		}
		
	},
	/**
	 * 获取本验证器所验证的所有element
	 */
	getEs : function(){
		es = [];
		for (i =0; i< this.fs.length; i++) {
			for (var j=0;j<this.fs[i].es.length;j++){
				es = es.concat(this.fs[i].es[j]);
			}
		}
		return es;
	},
	/**
	 * 对html element作些额外的处理，如加验证事件
	 * @param {} e html element
	 */
	attachE:function(e){
		if (tt.Conf.errorStyle == 'alert') {
			return;
		}
		
		var types = tt.inputType(e);
		var hd = new tt.vHandler(e).h;
		for (var x in tt.Conf.validateOn) {
			var evt = tt.Conf.validateOn[x];
			if (types.isCheckbox || types.isRadio) {
				if (evt == 'change'){
					tt.Util.addEventHandler(e, evt, hd);
					break;
				}
				continue;
			}
			
			if (types.isSelect && e.multiple != true) {
				if (evt == 'change'){
					tt.Util.addEventHandler(e, 'blur', hd);
					break;
				}
				continue;
			}
			
			tt.Util.addEventHandler(e, evt, hd);
		}
	}
});