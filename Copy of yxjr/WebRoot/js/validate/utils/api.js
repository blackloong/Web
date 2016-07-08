/**
 * validate all
 */
tt.validate = function() {
	tt.setVfP(true);
	tt.vf.vType = "a";
	return tt.vf.vBf();
};
/**
 * validate form eg:tt.validateForm('formname1', 'formname2', ... );
 */
tt.validateForm = function () {
	tt.setVfP(true);
	tt.vf.vType = "f";
	return tt.vf.vBf(new tt.f.Form(arguments));
};
/**
 * validate element eg:tt.validateElement(element1, element2, ... );
 */
tt.validateElement = function() {
	tt.setVfP(false);
	tt.vf.vType = "e";
	return tt.vf.vBf(new tt.f.Ele(arguments));
};
/**
 * validate id eg:tt.validateId('id1', 'id2', ... );
 */
tt.validateId = function() {
	tt.setVfP(true);
	tt.vf.vType = "i";
	return tt.vf.vBf(new tt.f.Id(arguments));
};
/**
 * validate name eg:tt.validateName('name1', 'name2', ... );
 */
tt.validateName = function() {
	tt.setVfP(true);
	tt.vf.vType = "n";
	return tt.vf.vBf(new tt.f.Name(arguments));
};
/**
 * remove all validator form validatorFactory
 */
tt.removeAll = function() {
	tt.vf.rmAll();
};