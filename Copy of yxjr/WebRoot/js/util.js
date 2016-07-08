/**
 * 获取选中数据
 * @param checkName
 * @returns
 */
function getSelectArray(checkName){
	var obj=document.getElementsByName(checkName); 
	var array=[];
	for(var i=0; i<obj.length; i++){
	if(obj[i].checked && obj[i].value!=undefined) {
		alert(obj[i].value);
		array[i]=obj[i].value;
	} 
	}
	return array;
}