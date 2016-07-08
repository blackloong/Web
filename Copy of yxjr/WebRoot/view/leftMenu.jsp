<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- sidebar start -->

<div class="admin-sidebar am-offcanvas" id="admin-offcanvas" style="height: 100%;">
	<div class="am-offcanvas-bar admin-offcanvas-bar" style="height: 800px;">
		<ul class="am-list admin-sidebar-list">
			<c:forEach items="${program.teams }" var="team">
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav${team.teamid }'}"><span
						class="am-icon-file"></span> ${team.team_name } <span
						class="am-icon-angle-right am-fr am-margin-right"></span>
				</a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in"
						id="collapse-nav${team.teamid }">
						<c:forEach items="${program.items }" var="item">
							<c:if test="${item.teamid == team.teamid }">
								<li><a href="<%=basePath %>${item.item_url }"
									class="am-cf"><span class="am-icon-check"></span>
										${item.item_name} </a>
								</li>
							</c:if>
						</c:forEach>
					</ul></li>
			</c:forEach>

		</ul>

		<!-- <div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-bookmark"></span> 公告
				</p>
				<p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
			</div>
		</div>

		<div class="am-panel am-panel-default admin-sidebar-panel">
			<div class="am-panel-bd">
				<p>
					<span class="am-icon-tag"></span> wiki
				</p>
				<p>Welcome to the Amaze UI wiki!</p>
			</div>
		</div> -->
	</div>
</div>




<%--   
  <div class="admin-sidebar" style="height: 780px;">
   <ul class="am-list admin-sidebar-list" style="height: 480px;" >
	  	<c:forEach items="${program.teams }" var="team">
	  		 <li class="admin-parent">
		        <a class="am-cf" data-am-collapse="{target: '#collapse-nav${team.teamid }'}"><span class="am-icon-file"></span> ${team.team_name } <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
		        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav${team.teamid }"   >
		          <c:forEach items = "${program.items }" var = "item">
      		       			<c:if test="${item.teamid == team.teamid }">
      		       				<li style="margin-left: 23px;"><a href="<%=basePath %>${item.item_url }" class="am-cf"><span class="am-icon-check"></span> ${item.item_name} </a></li>
      		       			</c:if>
      		       	</c:forEach>
		        </ul>
		      </li>
	  	</c:forEach>
    </ul>

 </div>

 --%>















<!--  <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同。—— Amaze</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>Welcome to the Amaze</p>
      </div>
    </div> -->

<!-- sidebar end -->