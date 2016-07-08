tt.VF = tt.C.ext({
	init : function() {
		this.vArr = [];  //validatorArray
		this.ip = new tt.RV().set(/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/).setI18(tt.i18Ip);
		this.email = new tt.RV().set(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).setI18(tt.i18Email);
		this.postcode = new tt.RV().set(/^[1-9]\d{5}(?!\d)$/).setI18(tt.i18Postcode);
		this.tel = new tt.RV().set(/^\d{3}-\d{8}$|^\d{4}-\d{7}$/).setI18(tt.i18Tel);
		this.idcard = new tt.RV().set(/^\d{15}$|^\d{17}[\d,x,X]{1}$/).setI18(tt.i18Idcard);
		this.req = new tt.ReqV();
		this.int = new tt.IV();  
		this.num = new tt.NV();
		
		this.msgs = [];
		this.seq = 0;            //序列号
	},
	rm: function(validatorArr){
		if(validatorArr) {
			for (var i = 0; i < this.vArr.length; i++){
				for (var j = 0; j < validatorArr.length; j++){
					if(validatorArr[j] == this.vArr[i]){
						this.vArr[i].rmAll();
						this.vArr[i] = null;
					}
				}
			}
		}
		this.vArr = tt.rmNull(this.vArr);
	},
	/**
	 * 移除所有验证器
	 */
	rmAll : function() {
		for ( var i = 0; i < this.vArr.length; i++) {
			this.vArr[i].isInFactory = false;
			this.vArr[i].rmAll();
		}
		this.vArr = [];
	},
	/**
	 * 用法:tt.vf.add(validator1, validator2,validator3... ...
	 * validatorx);
	 */
	add : function() {
		var startIndex = this.vArr.length;
		for ( var i = 0; i < arguments.length; i++) {
			this.vArr[i + startIndex] = arguments[i];
		}
	},
	vBf : function(f) {
		var ret = true;
		this.invalidEs = [];
		this.msgs = [];
		for (var i = 0; i < this.vArr.length; i++) {
			if (!this.vArr[i].vBf(f)) {
				ret = false;
			}
		}
		return ret;
	},
	resizeWindow : function() {
		for (var i = 0; i < tt.vf.msgs.length; i++) {
			tt.moveToPos(tt.vf.msgs[i].msg, tt.vf.msgs[i].ele);
		}
	}
});
tt.vf = new tt.VF();
tt.Util.addEventHandler(window, "resize", tt.vf.resizeWindow);