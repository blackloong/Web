
tt.RemoteV = tt.BV.ext({
	init:function(){
		this._super();
		this.vType = 'remote';
		this.rmId = this.rm;
		//this.vs = [];
		tt.vf.add(this);
	},
	v:function()
	{
		new tt.Ajax().setConfig(this.ajaxConf).submit();
		return this.result;
	},
	rm:function() {
		tt.vf.rm([this]);
		tt.vf.rm(this.vs);
	},
	add:function() {
		tt.vf.add(this);
	},
	/**
	 * 
	 * @param {} ajaxConf 形如：
	 * {
  		url:"xx.do",       //请求的url
  		form: formElement, //要提交的form
  		method:"post",    //post/get。默认为post
  		async:false,    //true/false。默认为true
  		data:[{name:"e", value:"ee"},{name:"w", value:"ww"}],
  		queryStringData: "e=eee&a=ddd&c=kkkk",
        encoding:     'UTF-8',
        requestHeaders: {"name1":"value1","":""},
        callbackParams: {},  //回调方法的第二个参数
	  }
	 */
	set:function(ajaxConf)
	{
		this.ajaxConf = ajaxConf;
		this.ajaxConf.async = false;
		this.ajaxConf.thisObj = this;
		this.ajaxConf.thisObjForSendError = this;
		this.ajaxConf.onSendError = function() {
			this.result = false;
			tt.vf.rm(this.vs);
			alert(tt.ajaxError); //"向服务器发送请求时出现异常，请检查本机与服务器的网络是否通畅!"
		};
		this.ajaxConf.success = function(xmlHttpResponse,
				callbackParams) {
			tt.vf.rm(this.vs);
			var responseObj;
			if (!xmlHttpResponse.responseText){
				this.result = false;
				this.setI18(tt.ajaxError1); //"从服务器端获取数据失败!";
				return;
			}
			try {
				responseObj = eval("(" + xmlHttpResponse.responseText + ")");
			} catch(e){
				this.result = false;
				this.setI18(tt.ajaxError2); //"服务器异常!";
				return;
			}
			
			/**
			 * {
			 * "name1":{'result':true, 'msg':'验证成功'},
			 * "name2":{'result':false, 'msg':'验证失败'}
			 * }
			 */
			this.vs = [];
			for (var item in responseObj) {
				var onlyShow = new tt.OnlyShow();
				this.vs.push(onlyShow);
				onlyShow.result = responseObj[item].result;
				onlyShow.msg = responseObj[item].msg;
				var msgId = responseObj[item].msgId;
				
				var f1;
				if (item.indexOf("id:") != -1) {
					f1 = new tt.Field("", "", item.substring(3)).setMsgId(msgId);
					onlyShow.add(f1);
				}
				else {
					f1 = new tt.Field("", item).setMsgId(msgId);
				}
				onlyShow.add(f1);
			}
		};
		return this;
	},
	attachE:function(e){}//服务端验证，不允许添加onchange等事件，以影响性能。
});