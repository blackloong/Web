
function onsub_money(){
	
	var q = $("#mmoeny_id").val();
	if (q!=""&&isNaN(q)) {
		alert("请输入正确金额！");
		return ;
	} 
	var xz=document.getElementById("agreement_check_id"); 
	if(!xz.checked){
		alert("未同意《借款协议》");
		return ;
	} 
	var loanid=$("#bid_loan_id").val();
	$.post("public_bid_info_save", "loanid=" + loanid + "&money=" + q, function(data){
		alert(data.msg);
		 location.reload();
	/*	window.location.href=document.referrer;*/
	});
}

function onsub_project_money(){
	var q = $("#mmoeny_project_id").val();
	if (q!=""&&isNaN(q)) {
		alert("请输入正确金额！");
		return ;
	} 
	var xz=document.getElementById("agreement_check_id"); 
	if(!xz.checked){
		alert("未同意《借款协议》");
		return ;
	} 
	var projectid=$("#bid_project_id").val();
  $.post("public_project_info_save", "projectid=" + projectid + "&money=" + q, function(data){
		alert(data.msg);
		 location.reload();
	});
}