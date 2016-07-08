tt.Field = tt.C.ext({
	init : function(label, name, id, e) {
		this.label = label;
		this.name = name == '' ? null : name;
		this.id = null;
		this.msgId = null;
		es = [];
		
		if(e){
			es.push(e);
		} 
		if (name) {
			es = document.getElementsByName(name);
		}
		if (id) {
			es = [];
			this.id = id;
			e = tt.getById(id);
			if (e) {
				es.push(e);
			}
		}
		for (var i=0;i<es.length;i++) {
			 seq = tt.vf.seq++;
			 !es[i][tt.Conf.proNameOfMsgId] ? es[i][tt.Conf.proNameOfMsgId] = tt.Conf.proNameOfMsgId + seq : null;
			 !es[i][tt.Conf.proNameOfReqStarId] ? es[i][tt.Conf.proNameOfReqStarId] = tt.Conf.proNameOfReqStarId + seq : null;
		}
		this.es = es;
		
	},
	setMsgId:function(id) {
		this.msgId = id;
		return this;
	},
	getMsgId:function(e) {
		return this.msgId;
	}
});