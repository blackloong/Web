<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>
<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<script type="text/javascript" >

	function saveUser () {
	 var mobile=$("#mobile_id").val();
	 var id="${data.id}";
	 if(id!=''){
	 $("#saveUser").submit();
	 }else{
	  if(mobile.length==11){
	 }else{
	 alert("请正确输入手机号码！");
	 return ;
	 }
	 $.post("ismobile","mobile="+mobile,function(data){
	if(data.boo){
	$("#saveUser").submit();
	}else{
	alert("账号已存在！");
	}
	}); 
	 }
	}	

</script>

</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="am-cf admin-main" style="margin-left: 5px;">
  <!-- sidebar start -->
  <%@include file = "../leftMenu.jsp"%>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">新增&编辑</strong> / <small>个人信息</small></div>
    </div>

    <div class="am-g">

    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      	<div class="">
          <div class="">
            <div class="am-g">
              <div class="am-u-md-4" id="imgDiv">
                  </div>
              <div class="am-u-md-8"><p> </p><div class="am-form-group"> <p class="am-form-help"></p></div>
              </div>
            </div>
          </div>
        </div>
      </div>     
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form class="am-form am-form-horizontal" action="saveUser" method="post" id="saveUser">
        	<input name="id" type="hidden" value="${data.id }" />
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">手机号码</label>
            <div class="am-u-sm-9" style="width: 75%">
              <input type="text"  value="${data.mobile }" name="mobile" id="mobile_id" placeholder="手机号码 " <c:if test="${data.id!=null }">readonly</c:if> >
            </div>
          </div>
		
		  <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">用户名</label>
            <div class="am-u-sm-9" style="width: 75%">
            <input type="text"  value= "${data.username }" name="username" id="user-phone" placeholder="用户名" <c:if test="${data.id!=null }">readonly</c:if> >
            </div>
          </div>
	   
         
          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">权限</label>
            <div class="am-u-sm-9" style="width: 75%">
              <select name="roleid">
             	<dic:loadSelectTag tableName="tb_role" key="id" text="role_name" id="${data.roleid}_id" where=""/>
            </select>
            </div>
          </div>
       <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">真实姓名</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="text"  value= "${data.name }" name="name" id="user-phone" placeholder="真实姓名" >
            </div>
          </div>
            <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">身份证号码</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="text"  value= "${data.code_no }" name="code_no" id="user-phone" placeholder="身份证号码" >
            </div>
          </div>
            <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="radio"  value= "0" name="gender" <c:if test="${data.gender!=1}">checked="checked"</c:if>  placeholder="">男
               <input type="radio"  value= "1" name="gender"  <c:if test="${data.gender==1}">checked="checked"</c:if> > 女
            </div>
          </div>
         <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">学历</label>
            <div class="am-u-sm-9" style="width: 75%">
            
             <select name="degree">
             	<dic:loadSelectTag tableName="tb_dictionary" key="id" text="value" id="${data.degree}_id" where=" and pid!=0 and type='degree' "/>
            </select>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">出生日期</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="text"  value= "${data.birthdate }" name="birthdate" class="am-form-field" data-am-datepicker placeholder="出生日期" readonly>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">现居住地址</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="text"  value= "${data.xjz_address }"  name="xjz_address"  placeholder="现居住地址">
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">户籍地址</label>
            <div class="am-u-sm-9" style="width: 75%">
               <input type="text"  value= "${data.fjxx_address }" name="fjxx_address"  placeholder="户籍地址">
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">简介</label>
            <div class="am-u-sm-9" style="width: 75%">
            
            <textarea rows="" cols="" name="profile">${data.profile }
            </textarea>
            
             
            </div>
          </div>
         
         
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <a href="javascript:saveUser();"  class="am-btn am-btn-primary">保存修改</a>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html>