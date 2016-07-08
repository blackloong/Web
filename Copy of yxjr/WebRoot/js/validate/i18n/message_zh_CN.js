var tt = {};
tt.Util = {};
tt.f={};

tt.i18Req = "{0}为必输项，请输入!";
tt.i18Req1 = "请选择{0}!";
tt.i18Email = "{0}不是一个合法的邮箱!";
tt.i18Int = "{0}必须为整数!";
tt.i18Datetime = "{0}不是一个合法的日期时间! 正确的格式为{1}";

//  num range start
tt.i18NumRange = "{0}必须在{1}和{2}之间!";
tt.i18NumRangeMin = "{0}必须小于等于{1}!";
tt.i18NumRangeMax = "{0}必须大于等于{1}!";
tt.i18NumRangeExp = "{0}合法范围：{1}!";
//  num range end

tt.i18LenMin = "{0}最多只能输入{1}个字符，您已超出{2}个字符!";
tt.i18Len = "{0}最少要输入{1}个字符，你还需要输入{2}个字符!";//{0}的长度不应大于3! //{0}的长度不应小于3!
tt.i18LenExp = "{0}长度合法范围：{1}!";
tt.i18LenTip = "您已输入{0}个字符，还可以输入{1}个字符!";

tt.i18Num = "{0}必须为数字!";
tt.i18Regex = "{0}{1}";
tt.i18Ip = "{0}不是一个合法的IP!";
tt.i18Postcode = "{0}不是一个合法的邮政编码!";
tt.i18Tel = "{0}不是一个合法的电话号码!<br>合法的格式形如：0734-1234567或 021-12345678";
tt.i18Idcard = "{0}不是一个合法的身份证号码!";

tt.i18SelectCountMin = "{0}最多只能选中{1}项，您已多选{2}项!";
tt.i18SelectCount = "{0}最少要选中{1}项，你还需要选择{2}项!";
tt.i18SelectCount_1 = "{0}最少要选中{1}项!";
tt.i18SelectCountExp = "{0}选中个数的合法范围：{1}!";

tt.ajaxError="向服务器发送请求时出现异常，请检查本机与服务器的网络是否通畅!";
tt.ajaxError1="从服务器端获取数据失败!";
tt.ajaxError2="服务器异常!";

tt.i18DftOk = null;//'OK!';

//-----  compare validator
tt.i18Compare = "{0}不正确!";
tt.i18StrCompare = "{0}应该{1}{2}{3}!";
tt.i18NumCompare = "{0}应该{1}{2}{3}!";
tt.i18StrValueCompare = "{0}必须{1}{2}!";
tt.i18NumValueCompare = "{0}必须{1}{2}!";
tt.operMap = [];
tt.operMap["<"] = "小于";
tt.operMap["<="] = "小于或等于";
tt.operMap["=="] = "等于";
tt.operMap["!="] = "不等于";
tt.operMap[">"] = "大于";
tt.operMap[">="] = "大于或等于";

tt.close = "关闭";