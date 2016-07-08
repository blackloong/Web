/**
 * 
 * @param {}
 *            target
 * @param {}
 *            type such as "click"
 * @param {}
 *            handler
 */
tt.Util.addEventHandler = function(o, type, handler) {
	f = arguments.length == 4;
	if (o.addEventListener) {
		if (f) {
			o.removeEventListener(type, handler, false);
		} else {
			o.addEventListener(type, handler, false);
		}
	} else if (o.attachEvent) {

		if (f) {
			o.detachEvent("on" + type, handler);
		} else {
			o.attachEvent("on" + type, handler);
		}
	} else {
		if (f) {
		} else {
			o["on" + type] = handler;

		}
	}
};

/**
 * 将htmlElement插入到srcElement元素后面
 * 
 * @param srcElement
 * @param htmlElement
 */
tt.insertAfter = function(src, e) {
	tt.insertHtml('afterend', src, e);
};

tt.insertHtml = function(where, el, html) {
	where = where.toLowerCase();
	if (el.insertAdjacentHTML) {
		switch (where) {
			case "afterend" :
				el.insertAdjacentHTML('AfterEnd', html);
				return el.nextSibling;
		}
	} else {
		var range = el.ownerDocument.createRange();
		var frag;
		switch (where) {
			case "afterend" :
				range.setStartAfter(el);
				frag = range.createContextualFragment(html);
				el.parentNode.insertBefore(frag, el.nextSibling);
				return el.nextSibling;
		}
	}
};
/**
 * 为element添加className样式
 * 
 * @param element
 *            被操作的元素
 * @param cls
 *            样式名
 * @return
 */
tt.addCls = function(element, cls) {
	if (cls && element){
		_cls = element.className;
		_cls = " " + _cls + " ";
		if (_cls.indexOf(" " + cls + " ") == -1) {
			element.className = _cls + cls;
		}
	}
	
};
/**
 * 为element删除className样式
 * 
 * @param element
 *            被操作的元素
 * @param cls
 *            样式名
 * @return
 */
tt.rmCls = function(element, cls) {
	if (!element || !element.className || !cls) {
		return;
	}
	var oClass = element.className;
	var reg = "/\\b" + cls + "\\b/g";
	element.className = oClass ? oClass.replace(eval(reg), '') : '';
};

/**
 * 删除某一个元素
 * 
 * @param element
 * @return
 */
tt.rmEle = function(e) {
	if (e && e.parentNode) {
		e.parentNode.removeChild(e);
	}
};

/**
 * 相当于string的trim
 * 
 * @param str
 * @return
 */
tt.trim = function(s, m) {
	if (!s) {
		return "";
	}
	r = /(^\s*)|(\s*$)/g;
	if (m) {
		if (m == "l") {
			r = /(^\s*)/g;
		} else if (m == "r") {
			r = /(\s*$)/g;
		}
	}
	return s.replace(r, "");
};

/**
 * 根据类名实例化js对象
 * 
 * @param {}
 *            clazz
 * @return {}
 */
tt.instanceByClass = function(c) {
	eval("var r = new " + c + "();");
	return r;
};

/**
 * 
 * @param {}
 *            v comparedValue
 * @param {}
 *            exp expression
 * @return {}
 */
tt.parRngExp = function(v, exp) {
	var map = {
		'(' : '>',
		'[' : '>='
	};
	var expArr = [];
	var m1 = {
		"{" : "(",
		"}" : ")",
		"|" : "||",
		"&" : "&&"
	};
	for (i = 0; i < exp.length; i++) {
		c = exp.charAt(i);

		if (c == '(' || c == '[') {
			compareOper1 = map[c];

			index1 = exp.indexOf(')');
			index2 = exp.indexOf(']');
			_index = index1;
			compareOper2 = '<';
			if (index1 == -1 && index2 == -1) {
				alert('expression is invalid, not found ] or )!');
				return null;
			} else if (index1 == -1 || (index1 > index2 && index2 != -1)) {
				_index = index2;
				compareOper2 = '<=';
			}
			var singleExp = exp.substring(i + 1, _index);

			var numArr = singleExp.split(',');
			numArr[0] = tt.trim(numArr[0]);

			if (numArr.length == 1) {
				numArr[1] = tt.trim(numArr[0]);
			} else if (numArr.length == 2) {
				numArr[1] = tt.trim(numArr[1]);
			} else {
				alert(singleExp + ' is error!');
				return null;
			}

			expArr.push("(");
			if (numArr[0] != '') {
				expArr.push(v);
				expArr.push(compareOper1);
				expArr.push(numArr[0]);
			}
			if (numArr[0] != '' && numArr[1] != '') {
				expArr.push(' && ');
			}
			if (numArr[1] != '') {
				expArr.push(v);
				expArr.push(compareOper2);
				expArr.push(numArr[1]);
			}

			expArr.push(")");

			exp = exp.substring(_index + 1, exp.length);
			i = 0;
			continue;
		} else if (m1[c]) {
			expArr.push(m1[c]);
		}
	}
	return expArr.join('');
};
/**
 * tt.getI18S("my name is {0}, your name is {1}",["kebo","smis"], 0);
 * tt.getI18S("my name is {1}, your name is {2}",["kebo","smis"], 1);
 */
tt.getI18S = function() {
	var ret = arguments[0];
	if (arguments.length > 1) {
		si = 0; // startIndex
		if (arguments.length == 3) {
			si = arguments[2];
		}
		for (i = 0; i < arguments[1].length; i++) {
			ret = ret.replace("{" + si + "}", arguments[1][i]);
			si++;
		}
	}
	return ret;
};

/**
 * 
 * @param {}
 *            e
 * @return {} true:包含
 */
Array.prototype.ttCons = function(e) {
	i = 0;
	for (; i < this.length && this[i] != e; i++);
	return !(i == this.length);
};

tt.getStrLen = function(s) {
	var len = 0;
	var c = -1;
	for (var i = 0; i < s.length; i++) {
		c = s.charCodeAt(i);
		if (c >= 0 && c <= 128)
			len += 1;
		else
			len += 2;
	}
	return len;
};
tt.getById = function(id) {
	return document.getElementById(id);
};

/**
 * 获取元素的位置信息
 * 
 * @param {}
 *            e
 * @return {} {"t":t,'l':l,"b":b,'r':r};
 */
tt.getPos = function(e) {
	var rect = e.getBoundingClientRect();
	var scrollTop = 0;
	var scrollLeft = 0;
	var temp = e;
	while (temp = temp.offsetParent) {
		scrollTop += temp.scrollTop;
		scrollLeft += temp.scrollLeft;
	}

	var t = rect.top + scrollTop;
	var l = rect.left + scrollLeft;
	var r = rect.right + scrollLeft;
	var b = rect.bottom + scrollTop;
	return {
		"t" : t,
		'l' : l,
		"b" : b,
		'r' : r
	};
};
/**
 * 将srcE移到targetE后面
 * 
 * @param {}
 *            srcE
 * @param {}
 *            targetE
 */
tt.moveToPos = function(srcE, targetE) {
	var targetpostion = tt.getPos(targetE);
	srcE.style.zIndex = 9;
	srcE.style.position = "absolute";
	srcE.style.top = targetpostion.t - 3;// -
											// srcE.currentStyle.borderTopWidth
											// - srcE.style.marginTop;
	srcE.style.left = targetpostion.r + 8;// - srcE.currentStyle.borderLeftWidth -
										// srcE.style.marginLeft;
};
tt.getSelectedCount = function(j, es) {
	if (!es) {
		return 0;
	}
	
	var types = tt.inputType(es[j]);
	var c = 0;

	if (types.isSelect) {
		for (var i = 0; i < es[j].options.length; i++) {
			if (es[j].options[i].selected) {
				c++;
			}
		}
		return c;
	} else {
		for (var i = 0; i < es.length; i++) {
			if (es[i].checked) {
				c++;
			}
		}
		return c;
	}
	return c;
};

/**
 * 
 * @param {}
 *            e element
 * @return {}
 */
tt.inputType = function(e) {
	return {
		'isSelect' : e.tagName == "SELECT",
		'isCheckbox' : e.tagName == "INPUT" && e.type == 'checkbox',
		'isRadio' : e.tagName == "INPUT" && e.type == 'radio'
	};
};

tt.setVfP = function(clrSpace) {
	tt.vf.clrSpace = clrSpace;
};

tt.rmNull = function(arr) {
	var temp = [];
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] != null ) {
			temp.push(arr[i]);
		}
	}
	return temp;
};

/**
 * 
 * @param {} formElement
 * @return {}
 */
tt.Util.formToQueryString = function(formElement) {
	var allStr = [""];
	if (formElement) {
		var formElements = formElement.elements;
		var inputObj;
		if (formElements) {
			for (var i = 0; i < formElements.length; i++) {
				inputObj = formElements[i];
				if (inputObj.name) {
					allStr.push(inputObj.name + "=" + encodeURIComponent(inputObj.value));
				}
			}
		}
	}
	return allStr.join("&");
};

/**
 * 获取浏览器信息
 * @return {String}
 */
tt.Util.getOs = function() {
   if(navigator.userAgent.indexOf("MSIE") > 0) {
        return "ie";
   }
   if(isFirefox=navigator.userAgent.indexOf("Firefox") > 0) {
        return "ff";
   }
   if(isSafari=navigator.userAgent.indexOf("Safari") > 0) {
        return "safari";
   }
   if(isCamino=navigator.userAgent.indexOf("Camino") > 0) {
        return "camino";
   }
   if(isMozilla=navigator.userAgent.indexOf("Gecko/") > 0) {
        return "gecko";
   }
};

/**
  eg:<br>
  var config = 
  {
	url:"xx.do",       //请求的url
	form: formElement, //要提交的form
	method:"post",     //post/get。默认为post
	async:false,       //true/false。默认为false(表示异步提交)
	data:[{name:"e", value:"ee"},{name:"w", value:"ww"}],
	queryStringData: "e=eee&a=ddd&c=kkkk",
    encoding:     'UTF-8',
    requestHeaders: {"",""},
    thisObj: null,                        //回调方法体内，this指示的对象
	success: function(xmlHttpRequest){}   //成功请求后的回调函数
  };
  new tt.Ajax().setConfig(config).submit();
 */
tt.Ajax = function() {
	this.config = null;
	this.setConfig = function(config) {
		this.config = config;
		return this;
	};
	/**
	 * 
	 */
	this.getXmlHttpRequest = function () {
		try {
			return new ActiveXObject("MSXML2.XMLHTTP");
		}
		catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e1) {}
		}
		if (typeof XMLHttpRequest != "undefined") {
			return new XMLHttpRequest();
		}
	};
	
	/**
	 * @param {} config 形如: <br>
	  {
  		url:"xx.do",       //请求的url
  		method:"post",    //post/get。默认为post
  		async:false,    //true/false。默认为true，true表示异步
  		
  		form: formElement, //要提交的form
  		data:[{name:"e", value:"ee"},{name:"w", value:"ww"}],
  		queryStringData: "e=eee&a=ddd&c=kkkk",
  		
        encoding:     'UTF-8',
        requestHeaders:{"Accept-Encodin":"x-compress; x-zip",
        				"User-Agent":"LII-Cello/1.0  libwww/2.5",
        				"Referer":"http://www.w3.org/hypertext/DataSources/Overview.html"},
        
        
  		success: function(xmlHttpResponse, callbackParams){},   //成功时的回调函数
  		callbackParams: {},                                     //回调函数的第二个参数
        thisObj: {},                                            //success方法体内，this代表的对象
  		
  		onSendError: function(){},   //当发送失败时需要调用的函数
  		thisObjForSendError: {}      //onSendError方法体内，this代表的对象
	  }
	 */
	this.submit = function () {
		var config = this.config;
		if (!config.url) {
			return;
		}
		
		var xmlHttpRequest = this.getXmlHttpRequest();
		var contentType = 'application/x-www-form-urlencoded';
		var method = config.method ? config.method.toUpperCase() : "POST";
		var async = config.async ? config.async : false;
		var encoding = config.encoding ? config.encoding : 'utf-8';
		var thisObj = config.thisObj ? config.thisObj : window;
		var thisObjForSendError = config.thisObjForSendError ? config.thisObjForSendError : window;
		
		var setRequestHeader = function(xmlHttpRequest, requestHeaders) {
			var buildinHeaders = 
			{
		      'X-Requested-With': 'XMLHttpRequest',
		      'Accept': 'text/javascript, text/html, application/xml, text/xml, */*'
		    };
		    if (method == "POST") {
				 buildinHeaders['Content-type'] = contentType + (encoding ? '; charset=' + encoding : '');
			}
			
			for (var name in buildinHeaders) {
				xmlHttpRequest.setRequestHeader(name, buildinHeaders[name]);
			}
			if (requestHeaders) {
				for (var name in requestHeaders) {
					xmlHttpRequest.setRequestHeader(name, requestHeaders[name]);
				}
			}
		};
		
		var url = config.url;
		
		var postData = [];
		
		//url += "&tt_ajax_time=" + new Date().getMilliseconds();
		postData.push("tt_ajax_time=" + new Date().getMilliseconds());
		
		if(config.formId) {
			config.form = tt.getById(config.formId);
		}
		if (config.form) {
			postData.push(tt.Util.formToQueryString(config.form));
		}
		if (config.queryStringData) {
			postData.push(encodeURI(config.queryStringData));
		}
		if (config.data) {
			var data;
			for (var i = 0; i < config.data.length; i++) {
				data = config.data[i];
				postData.push(data.name + "=" + encodeURIComponent(data.value));
			}
		}
		var postDataStr = postData.join("&");
		postDataStr = postDataStr.replace(/[&]{2,3}/g, "&");
		
		xmlHttpRequest.open(method, url, async);
		setRequestHeader(xmlHttpRequest, config.requestHeaders);//必须在open后面执行
		var t = function() {
			if (xmlHttpRequest.readyState == 4) {
				if (config.success) {
					thisObj = config.thisObj ? config.thisObj : config.success;
					config.success.call(thisObj, xmlHttpRequest, config.callbackParams);
				}
			}
		};
		
		try {
			if (tt.Util.getOs() == "ff") {
				xmlHttpRequest.send(postDataStr);
				xmlHttpRequest.onreadystatechange = t();
			} else {
				xmlHttpRequest.onreadystatechange = t;
				xmlHttpRequest.send(postDataStr);
			}
		} catch(e) {
			if (config.onSendError){
				config.onSendError.call(thisObjForSendError, config);
			}
		}
	};
};

tt.Ajax.submit = function(ajaxConfig) {
	new tt.Ajax().setConfig(ajaxConfig).submit();
};