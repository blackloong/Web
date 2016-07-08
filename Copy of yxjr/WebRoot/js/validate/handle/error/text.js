tt.text = tt.bh.ext({
	h:function()
	{
		var i18n = this.v.getI18(this.f.label);
		if (this.needHandle() && i18n) {
			this.render(tt.Conf.errCls, i18n, "talentClose", tt.Conf.errInputCls);
			
		}
	}
});