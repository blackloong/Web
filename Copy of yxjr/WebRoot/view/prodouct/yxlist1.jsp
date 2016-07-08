<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/dic" prefix="dic" %>
<%@taglib prefix="FQ" uri="/fq" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  
  
  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">

<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
  <script type="text/javascript"  src="<%=basePath%>assets/js/layer.js"></script>
<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>
<script src="<%=basePath%>assets/js/common.js"></script>

      <br>
   <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main"   border="1"  width="100%" >
            <thead>
              <tr> 
			  <th class="table-title">还款时间</th>
			  <th class="table-title">期数</th>			  		  
			  <th class="table-title">总金额</th>
              <th class="table-title">本金</th>
              <th class="table-title">利息</th>
              <th class="table-type">服务费</th>
               <th class="table-type">提前结清总额</th>
              <th class="table-type">提前结清本息</th>
              <th class="table-type">提前结</th>
               </tr>
          </thead>
          <tbody>
          <c:forEach items="${data.Data.DongZhiExportRepaymentPlan}" var="item">
          	<tr>
              <td >
                 ${item.RepaymentTime } </td>
              <td>${item.Dual}</td>
              <td>${item.AllMoney}</td>
              <td>
                ${item.BenJin}
                 </td>              
              <td>			   
                 ${item.LiXi}
                
                </td>
              <td>${item.FuWuFei }</td>
              <td>${item.AllTiQianJieQing}
                 
              </td>
              <td>${item.TiQianJieQingBenXi}</td>
              <td>${item.TiQianJieQingShouXuFei}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
         </form>
      </div>

    </div>
   
</html>