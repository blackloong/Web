tt.CV = tt.BV.ext({
	/**
	 * @param cmpType
	 *            比较类型 'n':数字比较; 'v':字符串比较
	 * @param oper
	 *            比较符,可以为'<','<=','==','!=','>','>='
	 * @param fOrV
	 *            被比较的字段或值
	 * @param showCmpVal
	 *            是否显示被比较的值 举例: var field1 = new tt.Field("用户名", "loginName"); new
	 *            tt.CV().set('n','>',field1);//要求添加此验证器的字段必须大于field1的值
	 */
	set : function(cmpType, oper, fOrV, showCmpVal) {
		this.cmpType = cmpType;
		this.oper = oper;
		this.cmpF = null;   //comparedField
		this.cmpV = null;
		this.i18ps[0] = tt.operMap[this.oper];
		this.showCmpVal = true;  //默认为true
		if (arguments.length == 4) {
			this.showCmpVal = showCmpVal;
		}
		if (["string",'number'].ttCons(typeof fOrV)) {
			this.cmpV = fOrV;
			if (typeof fOrV == 'number'){
				this.cmpType = 'n';
			}else if (typeof fOrV == "string" ){
				this.cmpType = 'v';
			}
		} else {
			this.cmpF = fOrV;
			this.i18ps[1] = fOrV.label;
		}
		
		if (!this.showCmpVal) {
			this.i18n = tt.i18Compare;
		} else if (cmpType == 'n' && this.cmpF) {
			this.i18n = tt.i18NumCompare;
		} else if (cmpType == 'v' && this.cmpF) {
			this.i18n = tt.i18StrCompare;
		} else if (cmpType == 'n' && this.cmpV) {
			this.i18n = tt.i18NumValueCompare;
		} else if (cmpType == 'v' && this.cmpV) {
			this.i18n = tt.i18StrValueCompare;
		} else {
			alert("error occured:talent-validate'tt.CV not support the compare type '" + cmpType + "'");
		}
		return this;
	},
	v : function(str, index) {
		if (!str) {
			return true;
		}

		var cmpV;
		if (this.cmpV) {
			cmpV = this.cmpV;
			if (this.showCmpVal) {
				this.i18ps[1] = "<span class='talentComparedValue'>"
						+ cmpV + "</span>";
			} else {
				this.i18ps[2] = "";
			}
		} else {
			var es = this.cmpF.es;
			if (es.length == 0){
				return true;
			}
			
			cmpV = (es[index])
					? es[index].value
					: es[es.length - 1].value;
			if (cmpV == null || cmpV == "") {
				return true;
			}
		
			if (this.showCmpVal) {
				this.i18ps[2] = "<span class='talentComparedValue'>" + cmpV + "</span>";
			} else {
				this.i18ps[2] = "";
			}
		}

		var s;
		if (this.cmpType == "n")// 是数字比较
		{
			var numV = tt.vf.num;
			if ((!numV.v(str)) || (!numV.v(cmpV+"")))// 不是数字
			{
				return true;  //不是数字则留给数字验证器去验证
			}

			s = str + this.oper + cmpV;
		} else if (this.cmpType == "v")// 是字符串比较
		{
			s = "\"" + str + "\"" + this.oper + "\"" + cmpV + "\"";
		}
		return eval(s) == true;
	}
});