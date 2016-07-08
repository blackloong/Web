var defaultFormId = "ec";
_systemUI = {

	loadSelect: function(o,id,tablename,key,text,where,ss){
		var option = {};
		option.tablename = tablename;
		option.key = key;
		option.text = text;
		option.where = where+o.value;
		option.id = o.value;
		
		
		$.post("dicloadSelect",option,function(s){
		
			$("#"+id).html("");
			$("#"+id).append(s);
			$('select').comboSelect();
		})
	}
}
