<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE>
<html style="height: 100%">
<head>
 <link rel="stylesheet" href="/fdoctor/statics/css/mainscreen/style.css"/>
<script type="text/javascript"
	src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/echarts.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=QaBCa02gdr5G3klaXIV5HlRKquut3SpH"></script>
<script type="text/javascript" src="/fdoctor/statics/js/bmap.min.js"></script>
<script type="text/javascript"
	src="/fdoctor/statics/js/areaRestriction.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	String webServerIp = PropertiesUtils.getValue("ftp_web_server_ip");
%>
<title>大屏终端展示</title>
</head>
<style>
	/*定义滚动条宽高及背景，宽高分别对应横竖滚动条的尺寸*/
	.scrollbar{
		max-height: 300px;
		overflow-y: auto;
	}
	.scrollbar::-webkit-scrollbar{
		width: 16px;
		height: 16px;
		/*background-color: #f5f5f5;*/
		background-color: transparent;
	}
	/*定义滚动条的轨道，内阴影及圆角*/
	.scrollbar::-webkit-scrollbar-track{
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
		border-radius: 10px;
		background-color: #f5f5f5;
	}
	/*定义滑块，内阴影及圆角*/
	.scrollbar::-webkit-scrollbar-thumb{
		/*width: 10px;*/
		height: 20px;
		border-radius: 10px;
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
		background-color: lightgray;
	}
	</style>
<body style="height: 100%; margin: 0">

	<div class="leftTop_docTeams" id="leftTopDiv" style="display: none;">
		<div class="top" id="topDivInLeftTop" style="cursor: pointer;">
			<div class="title tc">
				家庭医生团队
			</div>
			<div class="num tc" id="teamNumDiv">
				13
			</div>
		</div>
		<div class="bottom">
			<div class="address ecli">
				<img src="/fdoctor/statics/mainScreen/images/location.png"/> 
				<span id="regionNameSpan"></span>
			</div>
			<div class="team_name ecli" id="leaderNameDiv">
				<img src="/fdoctor/statics/mainScreen/images/person.png"/>
				<span id="leaderNameSpan">全部团队</span>
			</div>
		</div>
		<div class="team_select">
			<div class="trigle"></div>
			<ul class="team_items scrollbar" id="leaderListUl">
				
			</ul>
		</div>
	</div>

	<div id="show-data" style="display:none;position:absolute;background:#000000; opacity: 0.5; width:100%; height:100%; z-index:9999999;text-align:center;">
		<div style="padding-top:15%;color:azure;font-size:14px;">数据正在统计,请耐心等待……</div>
		<div><img src="/fdoctor/statics/mainScreen/images/loading.gif"></div>
	</div>

	<!--新增第一屏右边的栏目-->
	<div class="right_block">
		<img src="/fdoctor/statics/mainScreen/images/bg_right.png" alt="" class="bg"/>

		<div class="l">
			<div class="phy_test_num" >

			</div>
			<div class="heal_manage_num">

			</div>
		</div>
		<div class="r">
			<div style="padding-top:8px;font-weight:bold;color:#fff; text-align:center;line-height:20px; font-size: 16px;">慢病签约率</div>
			<div class="button_change">
				<%--<a href="javascript:;" class="high_a1 active">高血压</a>--%>
				<%--<a href="javascript:;" class="des_a2">糖尿病</a>--%>
			</div>
			<div class="wrap11">
				<div class="box">
					<div class="pie11"></div>
					<p>高血压签约率</p>
				</div>
				<div class="box">
					<div class="pie12"></div>
					<%--<p>高血压控制率</p>--%>
					<p class="">糖尿病签约率</p>
				</div>
			</div>
			<%--<div class="wrap11" style="opacity: 0; z-index: 1;">
				<div class="box">
					<div class="pie13"></div>
					<p class="">糖尿病管理率</p>
				</div>
				<div class="box">
					<div class="pie14"></div>
					<p class="">糖尿病控制率</p>
				</div>
			</div>--%>
		</div>

	</div>

	<input type="hidden" id="webServerIp" value="<%=webServerIp%>">
	<div id="container" style="height: 100%"></div>
	
	<div class="left_block left2" >
		<div class="logo lg2" id="lg2">
			<img src="/fdoctor/statics/mainScreen/images/logo.png" alt="" />
		</div>
		<div class="block_1">
			<img src="/fdoctor/statics/mainScreen/images/case1.png" alt="" class="bg"/>
			<div class="c">
				<ul>
					<li class="">
						<div class="msg">
							<div class="title blue_color">
								总人数
							</div>
							<div class="number">
								<span class="num">${basedata.personCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.personIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
					</li>
					<li class=""  style="width:50%">
						<div class="msg" style="display:inline-block">
							<div class="title blue_color">
								总签约人数
							</div>
							<div class="number">
								<span class="num">${basedata.signCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.signIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
                        <div style="display: inline-block;width:10px"></div>
                       <%-- <div class="msg" style="display:inline-block">
                            <div class="title blue_color">
                                总签约户数
                            </div>
                            <div class="number">
                                <span class="num">${basedata.familyCount}</span>
                                <c:choose>
                                    <c:when test="${incrementObject.signIncrement >= 0}">
                                        <span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>--%>
					</li>
					<li class="">
						<div class="msg">
							<div class="title blue_color">
								高血压人数
							</div>
							<div class="number">
								<span class="num">${basedata.hyperCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.hyperIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.hyperIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.hyperIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
					</li>
					<li class="">
						<div class="msg">
							<div class="title blue_color">
								糖尿病人数
							</div>
							<div class="number">
								<span class="num">${basedata.diabetesCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.diabetesIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.diabetesIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.diabetesIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
					</li>
					<li class="">
						<div class="msg">
							<div class="title blue_color">
								0-6岁儿童人数
							</div>
							<div class="number">
								<span class="num">${basedata.childrenCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.childrenIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.childrenIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.childrenIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
					</li>
					<li class="">
						<div class="msg">
							<div class="title blue_color">
								重性精神病人数
							</div>
							<div class="number">
								<span class="num">${basedata.majorPsychosisCount}</span>
								<%--<c:choose>
									<c:when test="${incrementObject.majorPsychosisIncrement >= 0}">
										<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.majorPsychosisIncrement}%</span>
									</c:when>
									<c:otherwise>
										<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.majorPsychosisIncrement}%</span>
									</c:otherwise>
								</c:choose>--%>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="block_2">
			<img src="/fdoctor/statics/mainScreen/images/case3.png" class="bg"/>
			<div class="h" id="townLable">
				
			</div>
			<div class="c">
				<table  cellspacing="" cellpadding="2" id="towmTable">
				</table>
			</div>
		</div>
	</div>
	
	
	<div class="left_block left1" >
		<div class="logo lg1">
			<img src="/fdoctor/statics/mainScreen/images/logo.png" alt="" />
		</div>
		<div class="block_3 ">
			<img src="/fdoctor/statics/mainScreen/images/case2.png" alt="" class="bg"/>
			<div class="h tc">
				地区人口情况
			</div>
			<div class="c">
				<!--左边-->
				<div class="l">
					<div class="img">
						<img src="/fdoctor/statics/mainScreen/images/personRB.png" alt="" />
					</div>
					<div class="contract_num">
						<p class="title">总人数</p>
						<div class="clear"></div>
						<p class="num">${basedata.personCount}</p>
						<p class="bottom_num">
							<%--<c:choose>
								<c:when test="${incrementObject.personIncrement >= 0}">
									<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
								</c:when>
								<c:otherwise>
									<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
								</c:otherwise>
							</c:choose>--%>
						</p>
					</div>
				</div>
				<!--右边-->
				<div class="r">
					<div class="pie_chart_areaPeople"></div>
                    <%--<div class="contract_num" style="margin:2% 1%">
                        <p class="title" style="font-size: 13px;">签约户数</p>
                        <div class="clear"></div>
                        <p class="num" style="font-size: 2.5rem;">${basedata.familyCount}</p>
                    </div>--%>
					<div class="contract_num" style="margin:4% 2%">
						<p class="title"><%--style="font-size: 13px;"--%>签约人数</p>
						<div class="clear"></div>
						<p class="num" ><%--style="font-size: 2.5rem;"--%>${basedata.signCount}
						</p>
						<p class="title">签约率</p>
						<div class="clear"></div>
						<p class="num" >${totalIncrement}%</p>
						<p class="bottom_num">
						<%--<c:choose>
								<c:when test="${incrementObject.signIncrement >= 0}">
									<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
								</c:when>
								<c:otherwise>
									<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.signIncrement}%</span>
								</c:otherwise>
							</c:choose>--%>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="block_4 clearfix ">
			<img src="/fdoctor/statics/mainScreen/images/case3.png" class="bg"/>
			<div class="h tc">
				医疗资源情况
			</div>
			<div class="c">
				<div class="l">
					<div class="img">
						<img src="/fdoctor/statics/mainScreen/images/medical_institution.png" alt="" />
					</div>
					<div class="contract_num">
						<p class="title">医疗机构总数</p>
						<p class="num">${basedata.orgCount}</p>
						<p class="bottom_num">
							<%--<c:choose>
								<c:when test="${incrementObject.orgIncrement >= 0}">
									<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.orgIncrement}%</span>
								</c:when>
								<c:otherwise>
									<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.orgIncrement}%</span>
								</c:otherwise>
							</c:choose>--%>
						</p>
					</div>
				</div>
                <div class="m">
                    <div class="img">
                        <img src="/fdoctor/statics/mainScreen/images/records_num.png" alt="" />
                    </div>
                    <div class="contract_num">
                        <p class="title">建档数量</p>
                        <p class="num">${basedata.personCount}</p>
                        <p class="bottom_num">
                           <%-- <c:choose>
                                <c:when test="${incrementObject.personIncrement >= 0}">
                                    <span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.personIncrement}%</span>
                                </c:otherwise>
                            </c:choose>--%>
                        </p>
                    </div>
                </div>
				<div class="r">
					<div class="img">
						<img src="/fdoctor/statics/mainScreen/images/medical_team.png" alt="" />
					</div>
					<div class="contract_num">
						<p class="title">医生团队总数</p>
						<p class="num">${basedata.teamCount}</p>
						<p class="bottom_num">
							<%--<c:choose>
								<c:when test="${incrementObject.teamIncrement >= 0}">
									<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>${incrementObject.teamIncrement}%</span>
								</c:when>
								<c:otherwise>
									<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>${incrementObject.teamIncrement}%</span>
								</c:otherwise>
							</c:choose>--%>
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="block_5 ">
			<img src="/fdoctor/statics/mainScreen/images/case_big.png" class="bg"/>
			<div class="h tc">居民健康概览</div>
			<div class="c">
				<div class="bar_graph2" id="totalGraph"></div>
			</div>
		</div>
	</div>
	
	
	<!--下面的模块--------------------------------------------------------------------->
	<div class="buttom_chunk" > 
		<img src="/fdoctor/statics/mainScreen/images/bottom_case.png" alt="" class="bg"/>
		<div class="nav">
			<ul>
				<li index="0">
					<img src="/fdoctor/statics/mainScreen/images/tab_active.png" alt="" class="bg_active"/>
					签约情况统计
				</li>
				<li index="1">
					<img src="/fdoctor/statics/mainScreen/images/tab.png" alt="" class="bg"/>
					服务量统计
				</li>
				<li index="2">
					<img src="/fdoctor/statics/mainScreen/images/tab.png" alt="" class="bg"/>
					服务质量统计
				</li>
<!-- 				<li> -->
<!-- 					<img src="/fdoctor/statics/mainScreen/images/tab.png" alt="" class="bg"/><span id="team0Name">团队一</span> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<img src="/fdoctor/statics/mainScreen/images/tab.png" alt="" class="bg"/><span id="team1Name">团队二</span> -->
<!-- 				</li> -->
<!-- 				<li> -->
					
<!-- 					<img src="/fdoctor/statics/mainScreen/images/tab.png" alt="" class="bg"/><span id="team2Name">团队三</span> -->
<!-- 				</li> -->
			</ul>
		</div>
		<!-- 全部统计部分 -->
		<div class="c clearfix ">
			<div class="left">
				<h1></h1>
				<div class="c1 clearfix">
					<img src="/fdoctor/statics/mainScreen/images/personRB.png" alt="" class="personRB"/>
					<div class="contract_num" ><%--style="margin: 0% 4%;"--%>
						<p class="title">签约人数</p>
						<p class="num" id="downSignNum" style="display: inline-block"></p>
						<p class="bottom_num" id="townSignIncrement" style="display: inline-block; margin-left: 15px"></p>
					</div>
                  <%--  <div class="contract_num" style="margin: -6% 4%;">
                        <p class="title">签约户数</p>
                        <p class="num" id="downFamilyCount"></p>
                    </div>--%>
				</div>
			</div>
			<div class="middle">
				<div class="pie_chart" id="townPieChart">

				</div>
				<div class="contract_num">
					<p class="title">签约人数占比</p>
					<p class="num" id="downSignPer"></p>
				</div>
			</div>
			<div class="right">
				<div class="bar_graph1" id="townBarGraph"></div>
			</div>
		</div>

		<div class="c clearfix ">
			<!--<h1>XXX机构-张丽医生团队</h1>-->
			<div class="left">
				<div class="bar_grap21" style="width:80%"></div>
			</div>
			<div class="middle">
				<div class="bar_grap22" style="width:80%"></div>
			</div>
			<div class="right">
				<div class="bar_grap23" style="width:80%"></div>
			</div>
		</div>

		<div class="c clearfix ">
			<div class="des_manage_per">
				<%--<h2>慢病管理率</h2>--%>
					<h2>高血压管理率</h2>
				<div class="box">
					<div class="pie_chart_down31" style="margin-left:50%"></div>
				<%--	<p>高血压管理率</p>--%>
				</div>
				<%--<div class="box">
					<div class="pie_chart_down32"></div>
					<p>糖尿病管理率</p>
					&lt;%&ndash;<span>ssdfsdfsdf</span>&ndash;%&gt;
				</div>--%>
			</div>
			<div class="des_control_per">
				<%--<h2>疾病控制率</h2>--%>
					<h2>糖尿病管理率</h2>
				<div class="box">
					<div class="pie_chart_down33"  style="margin-left:50%"></div>
					<%--<p>高血压控制率</p>--%>
					<%--<p>糖尿病管理率</p>--%>
				</div>
				<%--<div class="box">
					<div class="pie_chart_down34"></div>
					<p>糖尿病空置率</p>
					&lt;%&ndash;<span>ssdfsdfsdf</span>&ndash;%&gt;
				</div>--%>
			</div>
		</div>
		<!-- 团队一 -->
<!-- 		<div class="c clearfix "> -->
<!-- 			<div id="team0Div"> -->
<!-- 				<div class="left"> -->
<!-- 					<h1></h1> -->
<!-- 					<div class="c1 clearfix"> -->
<!-- 						<img src="/fdoctor/statics/mainScreen/images/personRB.png" alt="" class="personRB"/> -->
<!-- 						<div class="contract_num"> -->
<!-- 							<p class="title">签约人数</p> -->
<!-- 							<p class="num" id="team0SignNum"></p> -->
<!-- 							<p class="bottom_num" id="team0SignIncrement"></p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="middle"> -->
<!-- 					<div class="pie_chart" id="team0PieChart"> -->
						
<!-- 					</div> -->
<!-- 					<div class="contract_num"> -->
<!-- 						<p class="title">签约人数占比</p> -->
<!-- 						<p class="num" id="team0SignPer"></p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="right"> -->
<!-- 					<div class="bar_graph22" id="team0BarGraph"></div> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
<!-- 			<div class="center_nodata tc" id="team0DivMsg" style="display: none">未找到相关数据！</div> -->
			
<!-- 		</div> -->
<!-- 		<!-- 团队二 -->
<!-- 		<div class="c clearfix "> -->
<!-- 			<div id="team1Div"> -->
<!-- 				<div class="left"> -->
<!-- 					<h1 class="aa"></h1> -->
<!-- 					<div class="c1 clearfix"> -->
<!-- 						<img src="/fdoctor/statics/mainScreen/images/personRB.png" alt="" class="personRB"/> -->
<!-- 						<div class="contract_num"> -->
<!-- 							<p class="title">签约人数</p> -->
<!-- 							<p class="num" id="team1SignNum"></p> -->
<!-- 							<p class="bottom_num" id="team1SignIncrement"></p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="middle"> -->
<!-- 					<div class="pie_chart" id="team1PieChart"> -->
						
<!-- 					</div> -->
<!-- 					<div class="contract_num"> -->
<!-- 						<p class="title">签约人数占比</p> -->
<!-- 						<p class="num" id="team1SignPer"></p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="right"> -->
<!-- 					<div class="bar_graph3" id="team1BarGraph"></div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="center_nodata tc" id="team1DivMsg" style="display: none">未找到相关数据！</div> -->
<!-- 		</div> -->
		<!-- 团队三 -->
<!-- 		<div class="c clearfix "> -->
<!-- 			<div id="team2Div"> -->
<!-- 				<div class="left"> -->
<!-- 					<h1></h1> -->
<!-- 					<div class="c1 clearfix"> -->
<!-- 						<img src="/fdoctor/statics/mainScreen/images/personRB.png" alt="" class="personRB"/> -->
<!-- 						<div class="contract_num"> -->
<!-- 							<p class="title">签约人数</p> -->
<!-- 							<p class="num" id="team2SignNum"></p> -->
<!-- 							<p class="bottom_num" id="team2SignIncrement"></p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="middle"> -->
<!-- 					<div class="pie_chart" id="team2PieChart"> -->
						
<!-- 					</div> -->
<!-- 					<div class="contract_num"> -->
<!-- 						<p class="title">签约人数占比</p> -->
<!-- 						<p class="num" id="team2SignPer"></p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="right"> -->
<!-- 					<div class="bar_graph4" id="team2BarGraph"></div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="center_nodata tc" id="team2DivMsg" style="display: none">未找到相关数据！</div> -->
<!-- 		</div> -->
	</div>
	
	<script type="text/javascript">
		var bdary = new BMap.Boundary();
		var myGeo = new BMap.Geocoder();
		//var hospitals = JSON.parse('${hospJson}');
        var hospitals = [];
        var loadIndex = 0;

        //地址解析
        function geocAddr(data,item) {
            var geoc = new BMap.Geocoder();
            geoc.getPoint(item.address, function(point) {
                if (point) {
                    item.point = [point.lng,point.lat];
                } else {
                    console.log("您选择地址没有解析到结果!");
                }
                loadIndex++;
                if(loadIndex === data.length ){
                    loadBaiduMap();
				}
            }, "绵阳市游仙区");
        }

		//获取所有机构并解析地址
		$.ajax({
			url:"/fdoctor/mainScreen/getHospitalList",
			async:false,
			success:function(data){
                $.each(data,function(i,item){
                    geocAddr(data,item);
                });
                hospitals = data;
            }
        });
	function loadBaiduMap() {
        bdary.get("绵阳市游仙区", function (rs) { //获取行政区域
            //设置折线数据（区划边界线）
            var count = rs.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                alert('未能获取当前输入行政区域');
                return;
            }

            var szRoad = {
                success: true,
                errorCode: 0,
                errorMsg: "成功",
                data: [{
                    ROAD_LINE: rs.boundaries[0]
                }]
            }

            var busLines = [];
            var data = szRoad.data;
            var hStep = 300 / (data.length - 1);

            var i = 0;
            for (var x in data) {
                var line = data[x];
                var pointString = line.ROAD_LINE;
                var pointArr = pointString.split(';');
                var lnglats = [];
                for (var j in pointArr) {
                    lnglats.push(pointArr[j].split(','))
                }
                busLines.push({
                    coords: lnglats,
                    lineStyle: {
                        normal: {
                            color: echarts.color.modifyHSL('#5A94DF', Math
                                .round())
                        }
                    }
                })
            }

            //设置散点数据
            var pointNum = 0;

            var colorList = [
                '#5AB1EF', '#2EC7C9', '#B6A2DE', "#FFB980", '#ED868C'
            ];
            var labels = ["0个团队", "<5个团队", "5-9个团队", "10-15个团队", ">15个团队"];
            var points = {
                "0个团队": [],
                "<5个团队": [],
                "5-9个团队": [],
                "10-15个团队": [],
                ">15个团队": []
            }

            for (var j = 0; j < hospitals.length; j++) {
                if (!hospitals[j].point) continue;
                hospitals[j].point.push({
                    orgName: hospitals[j].orgName,
                    orgId: hospitals[j].id,
                    address: hospitals[j].address,
                    teamNum: hospitals[j].teamNum
                });
                if (hospitals[j].teamNum == 0) {
                    points['0个团队'].push(hospitals[j].point);
                } else if (hospitals[j].teamNum < 5) {
                    points['<5个团队'].push(hospitals[j].point);
                } else if (hospitals[j].teamNum >= 5 && hospitals[j].teamNum < 10) {
                    points['5-9个团队'].push(hospitals[j].point);
                } else if (hospitals[j].teamNum >= 10 && hospitals[j].teamNum <= 15) {
                    points['10-15个团队'].push(hospitals[j].point);
                } else {
                    points['>15个团队'].push(hospitals[j].point);
                }
            }

            option = {
                bmap: {
                    roam: true,
                    center: [104.9, 31.55],
                    zoom: 14,
                   /* mapStyle: {
					 'styleJson': [{
					 'featureType': 'water',
					 'elementType': 'all',
					 'stylers': {
					 'color': '#031628'
					 }
					 }, {
					 'featureType': 'land',
					 'elementType': 'geometry',
					 'stylers': {
					 'color': '#000102'
					 }
					 }, {
					 'featureType': 'highway',
					 'elementType': 'all',
					 'stylers': {
					 'visibility': 'on'
					 }
					 }, {
					 'featureType': 'arterial',
					 'elementType': 'geometry.fill',
					 'stylers': {
					 'color': '#000000'
					 }
					 }, {
					 'featureType': 'arterial',
					 'elementType': 'geometry.stroke',
					 'stylers': {
					 'color': '#0b3d51'
					 }
					 }, {
					 'featureType': 'local',
					 'elementType': 'geometry',
					 'stylers': {
					 'color': '#000000'
					 }
					 }, {
					 'featureType': 'railway',
					 'elementType': 'geometry.fill',
					 'stylers': {
					 'color': '#000000'
					 }
					 }, {
					 'featureType': 'railway',
					 'elementType': 'geometry.stroke',
					 'stylers': {
					 'color': '#08304b'
					 }
					 }, {
					 'featureType': 'subway',
					 'elementType': 'geometry',
					 'stylers': {
					 'lightness': -70
					 }
					 }, {
					 'featureType': 'building',
					 'elementType': 'geometry.fill',
					 'stylers': {
					 'color': '#000000'
					 }
					 }, {
					 'featureType': 'all',
					 'elementType': 'labels.text.fill',
					 'stylers': {
					 'color': '#857f7f'
					 }
					 }, {
					 'featureType': 'all',
					 'elementType': 'labels.text.stroke',
					 'stylers': {
					 'color': '#000000'
					 }
					 }, {
					 'featureType': 'building',
					 'elementType': 'geometry',
					 'stylers': {
					 'color': '#022338'
					 }
					 }, {
					 'featureType': 'green',
					 'elementType': 'geometry',
					 'stylers': {
					 'color': '#062032'
					 }
					 }, {
					 'featureType': 'boundary',
					 'elementType': 'all',
					 'stylers': {
					 'color': '#465b6c'
					 }
					 }, {
					 'featureType': 'manmade',
					 'elementType': 'all',
					 'stylers': {
					 'color': '#022338'
					 }
					 }, {
					 'featureType': 'label',
					 'elementType': 'all',
					 'stylers': {
					 'visibility': 'on'
					 }
					 }]
					 }*/
                },
                legend: {
                    orient: 'vertical',
                    bottom: '50',
                    left: "10",
                    textStyle: {
                        color: '#FFFFFF'
                    },
                    data: labels.reverse(),
                    formatter: function (name) {
                        return name;
                    }
                },
                tooltip: {
                    formatter: function (params, ticket, callback) {
                        var detail = params.data[2];
                        /*if (detail.teamNum != 0) {*/
                        if (detail.orgId) {
                            $.get('/fdoctor/mainScreen/getTeamInfoInHospital', {
                                orgId: detail.orgId
                            }, function (data) {
                                var total = 0;
                                var returnText = '<div class="popup"><div class="h">' + detail.orgName + '<br></div><div class="c"><table cellspacing="" cellpadding=""><tr>'
                                    + '<td>地址：</td><td>' + detail.address + '</td></tr><tr><td>团队数量：</td><td>' + detail.teamNum + '个团队</td></tr><tr><td>代表团队：</td>'
                                    + '<td>成员' + (data.data.docNum?data.data.docNum:0) + '人</td></tr></table><div class="teachers">';
                                for (var j = 0; j < data.data.docNum; j++) {
                                    if (total < 3) {
                                        if (data.data.docList[j].img != null && data.data.docList[j].img != '') {
                                            /*returnText += '<div class="t_box"><div class="thumbnails"><img src="' + $('#webServerIp').val() + '/' + data.data.docList[j].img + '" alt=""></div><div class="clear"></div> <span>' + data.data.docList[j].docName + '</span></div>'*/
                                            returnText += '<div class="t_box"><div class="thumbnails"><img src="/fdoctor/statics/image/defaultImg.png" alt=""></div><div class="clear"></div> <span>' + data.data.docList[j].docName + '</span></div>';
                                        } else {
                                            returnText += '<div class="t_box"><div class="thumbnails"><img src="/fdoctor/statics/image/defaultImg.png" alt=""></div><div class="clear"></div> <span>' + data.data.docList[j].docName + '</span></div>';
                                        }
                                        total++;
                                    }
                                }
                                returnText += '</div></div></div>';
                                callback(ticket, returnText);
                            })
                            return "加载中..";
                        } else {
                            var returnText = detail.orgName + '<br>' + '地址：' + detail.address + '<br>' + '团队数量：' + detail.teamNum;
                            return returnText;
                        }
                    },
                    trigger: 'item',
                    confine: true
                },
                series: [{
                    type: 'lines',
                    coordinateSystem: 'bmap',
                    polyline: true,
                    data: busLines,
                    silent: true,
                    lineStyle: {
                        normal: {
                            // color: '#c23531',
                            // color: 'rgb(200, 35, 45)',
                            opacity: 1,
                            width: 5
                        }
                    },
                    progressiveThreshold: 500,
                    progressive: 200
                }, {
                    type: 'lines',
                    coordinateSystem: 'bmap',
                    polyline: true,
                    data: busLines,
                    lineStyle: {
                        normal: {
                            width: 0
                        }
                    },
                    effect: {
                        constantSpeed: 20,
                        show: true,
                        trailLength: 1,
                        symbolSize:5
                    },
                    zlevel: 1
                }]
            };

            for (var j = 0; j < labels.length; j++) {
                var name = labels[j];
                var data = points[name];
                var color = colorList[j]
                var serie = {
                    name: name,
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: data,
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke',
                        scale: 4
                    },
                    itemStyle: {
                        normal: {
                            /*color: color,*/
                            color: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 0.5,
                                colorStops: [{
                                    offset: 0, color: 'transparent' // 0% 处的颜色
                                },{
                                    offset: 0.5, color: 'transparent' // 0% 处的颜色
                                }, {
                                    offset: 1, color: color // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                    hoverAnimation: true,
					symbol:'circle',
                    symbolSize: function (val) {
                        return 15;
                    },
                }
                option.series.push(serie);
            }

            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);

            myChart.setOption(option, true);

            // 添加百度地图插件
            var bmap = myChart.getModel().getComponent('bmap').getBMap();
            /*bmap.addControl(new BMap.MapTypeControl());
            bmap.setMapType(BMAP_SATELLITE_MAP);*/

            // 获取百度地图对象
            var ecModel = myChart._model;
            var map = null;
            ecModel.eachComponent('bmap', function (bmapModel) {
                if (map == null) {
                    map = bmapModel.__bmap;
                }
            });

            map.centerAndZoom(new BMap.Point(104.9, 31.55), 12);
            map.setCurrentCity("游仙区");


            //添加点击事件
            myChart.on('click', function (params) {
                if (params.seriesType == 'effectScatter') {
// 			    	myChart.showLoading({
// 						text: '加载中..',
// 						color: '#c23531',
// 						textColor: '#000',
// 						maskColor: '#969696',
// 						zlevel: 0
// 			    	});
                    option.bmap.center = [params.data[0], params.data[1]];
                    option.bmap.zoom = 13;
                    myChart.setOption(option, true);

                    var bmap = myChart.getModel().getComponent('bmap').getBMap();
                    /*bmap.addControl(new BMap.MapTypeControl());
                    bmap.setMapType(BMAP_SATELLITE_MAP);*/

                    //发送ajax请求、改变页面元素
                    loadTownData(params.data[2].orgId);

                    $(".nav li:eq(0)").click();
                }
            });

            $('#lg2').on('click', function () {
                //隐藏次级页面
                $('.left2').removeClass('fadeInDown').addClass("fadeOutUp").css("z-index", '888');
                setTimeout(function () {
                    $('.left1').removeClass('fadeOutUp').addClass("fadeInDown").css("z-index", '99999');
                    $('.right_block').removeClass('fadeOutUp').addClass("fadeInDown").css("z-index", '99999');
                    $('.buttom_chunk').animate({
                        bottom: "-38%"
                    }, 400, 'swing')
                }, 500)

                $('#leftTopDiv').attr("style", "display: none;");
                // echarts、地图重新加载

                option.bmap.center = [104.9, 31.55];
                option.bmap.zoom = 12
                myChart.setOption(option, true);

                var bmap = myChart.getModel().getComponent('bmap').getBMap();
                /*bmap.addControl(new BMap.MapTypeControl());
                bmap.setMapType(BMAP_SATELLITE_MAP);*/

                map.centerAndZoom(new BMap.Point(104.9, 31.55), 12);
                map.setCurrentCity("游仙区");
            })

            var currentLiIndex = -1;
            $(".nav li").on('click', function () {
                var index = $(this).index();
                currentLiIndex = index;
                $(".nav li>img").prop('src', '/fdoctor/statics/mainScreen/images/tab.png')
                $('img', this).prop('src', '/fdoctor/statics/mainScreen/images/tab_active.png').removeClass('bg').addClass('bg_active');
                $(".buttom_chunk .c").animate({
                    'opacity': '0',
                    'z-index': '1'
                }, 100).stop(true, true).eq(index).animate({
                    'opacity': '1',
                    'z-index': '999'
                })
            }).eq(0).click();

            //轮播
            setInterval(function(){
                currentLiIndex++;
                if(currentLiIndex > 2){
                    currentLiIndex = 0;
                }
                $(".buttom_chunk .nav li[index="+currentLiIndex+"]").click();

            },10000 * 6);

            //右下面 按钮切换高血压与糖尿病
           /* $('.button_change a').on('click', function () {
                var index = $(this).index();
                $('.button_change a').removeClass('active');
                $(this).addClass('active');
                $('.wrap11').animate({
                    'opacity': '0',
                    'z-index': '1'
                }, 100).stop(true, true).eq(index).animate({
                    'opacity': '1',
                    'z-index': '999'
                }, 100)
                return false

            });*/
            //定时切换高血压与糖尿病
            var locationIndex=0;
            setInterval(function(){
                if(locationIndex ==0){
                    $(".high_a1").click();
                    locationIndex=1;
                }else if(locationIndex==1){
                    $(".des_a2").click();
                    locationIndex=0;
                }
            },3000);

            //这段是控制屏幕大小变化时候html的根字体大小 百分比形势
            (function (doc, win) {
                var docEl = doc.documentElement,
                    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                    recalc = function () {
                        var clientWidth = docEl.clientWidth;
                        if (!clientWidth) return;
                        docEl.style.fontSize = 20 * (clientWidth / 3600) + 'px';
                    };

                if (!doc.addEventListener) return;
                win.addEventListener(resizeEvt, recalc, false);
                doc.addEventListener('DOMContentLoaded', recalc, false);
            })(document, window);
        });
    }
		function teamLiClick(teamId, personCount, orgName, liItem){
            $(".nav li:eq(0)").click();
			$(liItem).siblings().removeClass("li_active");
			$(liItem).addClass('li_active');
			$.get('/fdoctor/mainScreen/getTeamInfo',{
				teamId : teamId,
				personCount : personCount
			},function(data){
				if(data.code == 200){
					//拼接需要展示的数据
					var teamOrgStr = orgName + '-' + data.data.leaderName + '医生团队';
					$('#leaderNameSpan').html(data.data.leaderName + '医生团队');
					$('.left h1').html(teamOrgStr.replace('绵阳市','').replace('游仙区','').replace('游仙',''));
					$('#downSignNum').html(data.data.signCount);
				/*	$("#downFamilyCount").html(data.data.familyCount);*/
					$('#downSignPer').html(data.data.signPer + '%');
					/*if(data.data.signIncrement >= 0){
						$('#townSignIncrement').html('<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>'+data.data.signIncrement+'%</span>');
					}else{
						$('#townSignIncrement').html('<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>'+data.data.signIncrement+'%</span>');
					}*/
					
					//刷新饼图
					loadPieChart('townPieChart',data.data.signCount,personCount);

                   // loadSuiFang((data.data.hyperCount)*2,(data.data.diabetesCount)*2,(data.data.childrenCoun)*2,(data.data.majorPsychosisCount)*2);
					//加载城镇柱状图
					var totalArray = [];
					totalArray.push(data.data.hyperCount);
					totalArray.push(data.data.diabetesCount);
					totalArray.push(data.data.childrenCount);
					totalArray.push(data.data.majorPsychosisCount);
					
					var incrementArray = [];
					incrementArray.push(data.data.hyperMonthIncNum);
					incrementArray.push(data.data.diabetesMonthIncNum);
					incrementArray.push(data.data.childrenMonthIncNum);
					incrementArray.push(data.data.majorPsychosisMonthIncNum);
					
					loadBarGraph('townBarGraph',totalArray, incrementArray);
					loadDataByTeamId(teamId);
				}else{
					alert('加载团队数据失败！');
                   // loadSuiFang(0,0,0,0);
				}
			})
			
		}
        function showDataWindow() {
            $("#show-data").show();
        }
        function hideDataWindow() {
            $("#show-data").hide();
        }

		function loadTownData(orgId){
            showDataWindow();
			$.get('/fdoctor/mainScreen/getTownInfoByHospital',{
				orgId : orgId
			},function(data){
				if(data.code == 200){
                    hideDataWindow();
                    //显示对应镇下村的签约人数
					/*
						for(var i=0;i<data.data.childrenInfo.length;i++){
							console.log(data.data.childrenInfo[i].name+"  总人数:"+data.data.childrenInfo[i].personCount+"  签约人数："+data.data.childrenInfo[i].signCount);
						}
					*/
					$("#topDivInLeftTop").unbind();
					$("#leaderNameSpan").html('全部团队');
//						myChart.hideLoading();
					//拼接需要展示的数据
					$('#townLable').html(data.data.regionInfo.name + '情况');
					$('.left h1').html(data.data.hospitalInfo.orgName.replace('游仙区','').replace('游仙','').replace('绵阳市',''));
					$('#downSignNum').html(data.data.regionInfo.dataInfo.signCount);
                  /*  $("#downFamilyCount").html(data.data.regionInfo.dataInfo.familyCount);*/
					$('#downSignPer').html(data.data.regionInfo.dataInfo.signPer + '%');
				/*	if(data.data.regionInfo.dataInfo.signIncrement >= 0){
						$('#townSignIncrement').html('<span class="red_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_red.png" alt="" class="triangle"/>'+data.data.regionInfo.dataInfo.signIncrement+'%</span>');
					}else{
						$('#townSignIncrement').html('<span class="green_color"> <img src="/fdoctor/statics/mainScreen/images/triangle_green.png" alt="" class="triangle"/>'+data.data.regionInfo.dataInfo.signIncrement+'%</span>');
					}*/
					
					$('#towmTable').html('');
					var showCount = 0;
					var tableHtml = '<tr class="blue_color"><td>村组</td><td>总人数</td><td>签约人数</td><!--<td>签约户数</td>--><td>签约率</td></tr>';
					for(var j = 0; j < data.data.childrenInfo.length; j++){
						if(showCount < 4){
							tableHtml += '<tr style="font-size: 14px;"><td>' + data.data.childrenInfo[j].name + '</td><td>'
									+ data.data.childrenInfo[j].personCount + '人</td><td>' + data.data.childrenInfo[j].signCount + '人</td>' +
                               /* '<td>' + data.data.childrenInfo[j].familyCount + '户</td><td>'*/
                                '<td>' + data.data.childrenInfo[j].signPer + '%</td></tr>';
						}
						showCount++;
					}
					$('#towmTable').append(tableHtml);
					//刷新饼图
					loadPieChart('townPieChart',data.data.regionInfo.dataInfo.signCount,data.data.regionInfo.dataInfo.personCount);
					
					//加载城镇柱状图
					var totalArray = [];
					totalArray.push(data.data.regionInfo.dataInfo.hyperCount);
					totalArray.push(data.data.regionInfo.dataInfo.diabetesCount);
					totalArray.push(data.data.regionInfo.dataInfo.childrenCount);
					totalArray.push(data.data.regionInfo.dataInfo.majorPsychosisCount);
					
					var incrementArray = [];
					incrementArray.push(data.data.regionInfo.dataInfo.hyperMonthIncNum);
					incrementArray.push(data.data.regionInfo.dataInfo.diabetesMonthIncNum);
					incrementArray.push(data.data.regionInfo.dataInfo.childrenMonthIncNum);
					incrementArray.push(data.data.regionInfo.dataInfo.majorPsychosisMonthIncNum);
					
					loadBarGraph('townBarGraph',totalArray, incrementArray);
					
					//显示界面
					$('.left1').removeClass('fadeInDown').addClass("fadeOutUp").css("z-index",'888');
					setTimeout(function(){
						$('.left2').removeClass('fadeOutUp').addClass("fadeInDown").css("z-index",'99999');
                        $('.right_block').removeClass('fadeInDown').addClass("fadeOutUp").css("z-index",'888');
						$('.buttom_chunk').animate({
							bottom:"2%"
						},400,'swing');
					},500)
					
					//显示团队列表
					$('#teamNumDiv').html(data.data.leaderList.length);
					$('#regionNameSpan').html(data.data.regionInfo.name);
					var liHtml = '';
					for(var j = 0; j < data.data.leaderList.length; j++){
						liHtml += "<li style='cursor:pointer' onclick=\"teamLiClick('"+data.data.leaderList[j].teamId+"','"+data.data.regionInfo.dataInfo.personCount+"','"+data.data.hospitalInfo.orgName+"',this)\">"+data.data.leaderList[j].docName+"医生团队</li>"
					}
					$('#leaderListUl').html(liHtml);
					$('#leftTopDiv').removeAttr("style");
					
					//绑定leftTopDiv点击事件
					$('#topDivInLeftTop').one('click',function(){
						loadTownData(orgId);
					});
					
				}else{
					alert('加载乡镇数据失败！');
				}
			})

			//加载服务质量统计
			loadDataByOrgId(orgId);
		}
        function loadDataByTeamId(teamId){
            $.get('/fdoctor/mainScreen/getTeamInfoCount',{
                teamId : teamId,
                type:1
            },function(data) {
                //if (data) {
                    loadChartByData(data);
                    generateAllPie(data);
               // }
            });
        }

		function loadDataByOrgId(orgId){//
            $.get('/fdoctor/mainScreen/getTeamInfoCount',{
                orgId : orgId,
				type:1
            },function(data) {
               // if (data) {
					//管理率 chronicDiseaseManage
					//控制率 chronicDiseaseCtrl
                    loadChartByData(data);

                    generateAllPie(data);
               // }
            });
		}

		function generateAllPie(data){

            //高血压管理率
            generatePie(".pie_chart_down31","签约率",[{value:data?data.chronicDiseaseManage.hyperPersonDeseaseEstimatel - data.chronicDiseaseManage.hyperPersonDeseaseTotal:0,name:"未签约人数"},
               {value:data?data.chronicDiseaseManage.hyperPersonDeseaseTotal:0,name:"签约人数"}],["未签约人数","签约人数"]);

            //糖尿病管理率
          generatePie(".pie_chart_down33","签约率",[{value:data?data.chronicDiseaseManage.dabetesPersonDeseaseEstimatel - data.chronicDiseaseManage.dabetesPersonDeseaseTotal:0,name:"未签约人数"},
               {value:data?data.chronicDiseaseManage.dabetesPersonDeseaseTotal:0,name:"签约人数"}],["未签约人数","签约人数"]);

            //糖尿病控制率
          /*  if(data && !data.chronicDiseaseManage.dabetesPersonDeseaseTotal)
                data.chronicDiseaseManage.dabetesPersonDeseaseTotal = 0;
            var _d1 = Math.floor(data?data.chronicDiseaseManage.dabetesPersonDeseaseTotal / 2:0);
            generatePie(".pie_chart_down33","控制率",[{value:data?data.chronicDiseaseManage.dabetesPersonDeseaseTotal:0,name:"管理人数"},
                {value:_d1,name:"达标人数"}],["管理人数","达标人数"]);
				*/
            //高血压控制率
          /*  if(data && !data.chronicDiseaseManage.hyperPersonDeseaseTotal)
                data.chronicDiseaseManage.hyperPersonDeseaseTotal = 0;
            var _d2 = Math.floor(data?data.chronicDiseaseManage.hyperPersonDeseaseTotal / 2:0);
            generatePie(".pie_chart_down34","控制率",[{value:data?data.chronicDiseaseManage.hyperPersonDeseaseTotal:0,name:"管理人数"},
                {value:_d2,name:"达标人数"}],["管理人数","达标人数"]);*/
        }

		allPanel = {};
		function generatePie(el,name,data,legends){
            if(allPanel[el]) allPanel[el].dispose();
           var myPie1=echarts.init(document.querySelector(el));
            allPanel[el] = myPie1;
               var option = {
                color: ['#F25757','#0982FF'],
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'bottom',
                    data:legends
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: false},
                        dataView : {show: false, readOnly: false},
                        magicType : {
                            show: false,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'center',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: false},
                        saveAsImage : {show: false}
                    }
                },
                calculable : true,
                series : [{
                    type:'pie',
                    radius : ['50%', '70%'],
                    hoverAnimation:false,
					label: {
                        normal: {
                            show: false,
                            position: 'outside'
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle : {
                        normal : {
                            color:"#92918C"
                        },
                        emphasis: {
                            color:"#92918C"
                        }
                    },
					tooltip:{show:false,trigger:"none"},
                    data:[{value:1}]
				},
                    {
                        name:name,
                        type:'pie',
                        radius : ['50%', '70%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : false,
                                        formatter : function (value){
                                            return 100 - value + '%'
                                    }
                                },
                                labelLine : {
                                    show : false
                                }
                            },
                            emphasis : {
                                label : {
                                    show : false,
                                    position : 'center',
                                    textStyle : {
                                        fontSize : '30',
                                        fontWeight : 'bold'
                                    }
                                }
                            }
                        },
                        data:data
                    }
                ]
            };
            myPie1.setOption(option);
		}
	</script>
	
	<!-- 首页面板上的饼状图 -->
	<script type="text/javascript">
		//左边第一个的饼状图
		$(function(){
			var myPie=echarts.init(document.querySelector('.pie_chart_areaPeople'));
			var option = {
						color: ['#07d1e0','#366779'],
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: false},
			            dataView : {show: false, readOnly: false},
			            magicType : {
			                show: false, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '20%',
			                        width: '50%',
			                        funnelAlign: 'center',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: false},
			            saveAsImage : {show: false}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : ['50%', '80%'],
			            itemStyle : {
			                normal : {
			                    label : {
			                        show : false
			                    },
			                    labelLine : {
			                        show : false
			                    }
			                },
			                emphasis : {
			                    label : {
			                        show : false,
			                        position : 'center',
			                        textStyle : {
			                            fontSize : '30',
			                            fontWeight : 'bold'
			                        }
			                    }
			                }
			            },
			            data:[
			                {value:'${basedata.signCount}', name:'已签约人数'},
			                {value:'${basedata.personCount - basedata.signCount}', name:'未签约人数'}
			            ]
			        }
			    ]
			};
			myPie.setOption(option);
			
			
			 // 基于准备好的dom，初始化echarts实例
	        var myGraph = echarts.init(document.getElementById('totalGraph'));
	        
	        var incrementData = JSON.parse('${incrementData}');
	        var totalNum = [];
	        totalNum.push('${basedata.hyperCount}');
	        totalNum.push('${basedata.diabetesCount}');
	        totalNum.push('${basedata.childrenCount}');
	        totalNum.push('${basedata.majorPsychosisCount}');
	        var incrementNum = [];
	        incrementNum.push(incrementData.hyperMonthIncNum);
	        incrementNum.push(incrementData.diabetesMonthIncNum);
	        incrementNum.push(incrementData.childrenMonthIncNum);
	        incrementNum.push(incrementData.majorPsychosisMonthIncNum);
			 
	        graphOption = {
	        		color : ['#5793f3', '#d14a61'],
	        	    tooltip: { trigger: 'axis',
	        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        	        }},
	        	    legend: {
	        	        data:['总人数','月增长'],
	        	        textStyle: {
		        	        color: '#FFFFFF'
		        	    },
		        	    bottom : '5%'
	        	    },
	        	    xAxis: {
	        	        data: ["高血压","糖尿病","儿童","重性精神病"]
	        	    },
	        	    textStyle: {
	        	        color: '#FFFFFF'
	        	    },
	        	    yAxis: {},
	        	    series: [{
	        	        name: '总人数',
	        	        type: 'bar',
	        	        data: totalNum
	        	    },{
	        	        name: '月增长',
	        	        type: 'bar',
	        	        data: incrementNum
	        	    }]
	        };
	        
	        myGraph.setOption(graphOption);
		});
	</script>
	<script type="text/javascript">
		//获取数据
        $(function(){
            $.get('/fdoctor/mainScreen/getTeamInfoCount',{
                teamId : '',
                orgId:'',
                type : 0,
            },function(data) {
               // if (data) {
                    loadData(data)
               // }
            });
        })
		//显示图标
		function loadData(itemData){
          //  $.get('/fdoctor/mainScreen/getPhysicalExaminationFollowUpCount',{
          //  },function(data) {

            //体检随访人次 健康管理人数
            var myChart1 = echarts.init(document.querySelector(".phy_test_num"));
            var myChart2 = echarts.init(document.querySelector(".heal_manage_num"));
            var option1 = {
                title:{
                    text:'体检/随访人次',
                    x:'center',
                    textStyle:{
                        color:'#ffffff',
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                     type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        show:false,
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                grid:{
                    containLabel:true
                },
                yAxis : [
                    {
                        type : 'category',
                        data : ['高血压','糖尿病','儿童','重精','老年人体检'],
                        axisLabel : {
                            margin:8,
                            textStyle: {
                                color: '#fff',
                                fontSize:12
                            }
                        }
                    }
                ],
                series : [
                    {
                        type:'bar',
                        data:itemData?[itemData.followUpPeopleStatistics.hyperCount,itemData.followUpPeopleStatistics.dabetesCount,itemData.followUpPeopleStatistics.childCount,itemData.followUpPeopleStatistics.cjCount,itemData.eaminationStatistics.oldManCount]:[0,0,0,0,0],
                        //data:[data.hyperCount,data.dabetesCount,data.childCount,data.cjCount,data.oldManCount],
                        itemStyle:{
                            normal:{
                                color:'#0982FF',
                                label: {
                                    show: true,
                                    //position:'center',
									position:[40, 10],
                                    formatter: '{c}人次',
                                    textStyle: {
                                        color: '#fff',
                                        align:'center'
                                    }
                                }
                            }
                        }
                    }
                ]
            };


            var option2 = {
                title:{
                    text:'健康管理人次',
                    x:'center',
                    textStyle:{
                        color:'#ffffff'
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        show:false,
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                grid:{
                    containLabel:true
                },
                yAxis : [
                    {
                        type : 'category',
                        data : ['建档量','健康评估','问诊','健康教育'],
                        axisLabel : {
                            margin:8,
                            textStyle: {
                                color: '#fff',
                                fontSize:12
                            }
                        }
                    }
                ],
                series : [
                    {
                        type:'bar',
                        data:itemData?[itemData.servicedNumber.profileCount,itemData.servicedNumber.healthAsseCount,itemData.servicedNumber.interroCount,itemData.servicedNumber.healthEduCount]:[0,0,0,0],
                        itemStyle:{
                            normal:{
                                color:'#0982FF',
                                label: {
                                    show: true,
                                     position:[40, 10],
                                   // position:right,
                                    formatter: '{c}人次',
                                    textStyle: {
                                        color: '#fff',
                                       // align:'center',
                                       // baseline:'middle'
                                    }
                                }
                            }
                        }
                    }
                ]
            };
            myChart1.setOption(option1);
            myChart2.setOption(option2);
          //  })
            //整体的高血压 糖尿病率

            var myChart3 = echarts.init(document.querySelector(".pie11"));
            //var myChart4 = echarts.init(document.querySelector(".pie12"));
            var myChart5 = echarts.init(document.querySelector(".pie12"));
           /// var myChart6 = echarts.init(document.querySelector(".pie14"));
            var labelTop = {
                normal : {
                    label : {
                        show : true,
                        position : 'center',
                        formatter : '{b}',
                        textStyle: {
                            baseline : 'bottom'
                        }
                    },
                    labelLine : {
                        show : true
                    }
                }
            };
            var labelFromatter = {
                normal : {
                    show:false,
                    label : {
                        formatter : function (params){
                            return 100 - params.value + '%'
                        },
                        textStyle: {
                            baseline : 'top'
                        }
                    }
                },
            }

			var option3Params1=itemData?itemData.chronicDiseaseManage.hyperPersonDeseaseEstimatel:0;//未管理人数
			var option3Params2=itemData?itemData.chronicDiseaseManage.hyperPersonDeseaseTotal:0;//管理人数
            var option3 = {
                //color: ['#07d1e0','#366779'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                series : [
                    {
                        name:'高血压',
                        type : 'pie',
                        radius : ['50%', '70%'],
                        x: '0%', // for funnel
                        itemStyle :  {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return option3Params1 - params.value + '%'
                                    },
                                    textStyle: {
                                        baseline : 'top',
                                        color:'#fff',
                                        fontsize:'20rem'

                                    }
                                }
                            },
                        },
                        data : [
                            {name:'未签约人数', value:option3Params1-option3Params2,
                                itemStyle :{
                                    normal : {
                                        color: '#D14A61',
                                        label : {
                                            show : false,
                                            position : 'center'
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },
                                    emphasis: {
                                    }
                                }
                            },
                            {name:'签约人数', value:option3Params2,
                                itemStyle : {
                                    normal : {
                                        color:'#0982FF',
                                        label : {
                                            show : true,
                                            position : 'center',
                                            formatter : '{b}\n{d}%',
                                            textStyle: {
                                                baseline : 'bottom',
                                            }
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    }
                                }}
                        ]
                    }

                ]
            };
           /* var option4Params1=100;//未控人数
            var option4Params2=40;//控制人数
            var option4 = {
                color: ['#07d1e0','#366779'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                series : [
                    {
                        name:'高血压',
                        type : 'pie',
                        radius : ['50%', '70%'],
                        x: '0%', // for funnel
                        itemStyle :  {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return option4Params1 - params.value + '%'
                                    },
                                    textStyle: {
                                        baseline : 'top',
                                        color:'#fff'

                                    }
                                }
                            },
                        },
                        data : [
                            {name:'未控制人数', value:option4Params1-option4Params2,
                                itemStyle :{
                                    normal : {
                                        color: '#D14A61',
                                        label : {
                                            show : false,
                                            position : 'center'
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },
                                    emphasis: {
                                    }
                                }
                            },
                            {name:'控制人数', value:option4Params2,
                                itemStyle : {
                                normal : {
                                    color:'#0982FF',
                                    label : {
                                        show : true,
                                        position : 'center',
                                        formatter : '{b}\n{d}%',
                                        textStyle: {
                                            baseline : 'bottom'
                                        }
                                    },
                                    labelLine : {
                                        show : false
                                    }
                                }
                            }}
                        ]
                    }

                ]
            };*/
            var option5Params1=itemData?itemData.chronicDiseaseManage.dabetesPersonDeseaseEstimatel:0;//未管理人数
            var option5Params2=itemData?itemData.chronicDiseaseManage.dabetesPersonDeseaseTotal:0;//管理人数
            var option5 = {
                color: ['#07d1e0','#366779'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                series : [
                    {
                        name:'糖尿病',
                        type : 'pie',
                        radius : ['50%', '70%'],
                        x: '0%', // for funnel
                        itemStyle :  {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return option5Params1 - params.value + '%'
                                    },
                                    textStyle: {
                                        baseline : 'top',
                                        color:'#fff'

                                    }
                                }
                            },
                        },
                        data : [
                            {name:'未签约人数', value:option5Params1-option5Params2,
                                itemStyle :{
                                    normal : {
                                        color: '#D14A61',
                                        label : {
                                            show : false,
                                            position : 'center'
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },
                                    emphasis: {
                                    }
                                }
                            },
                            {name:'签约人数', value:option5Params2,
                                itemStyle : {
                                    normal : {
                                        color:'#0982FF',
                                        label : {
                                            show : true,
                                            position : 'center',
                                            formatter : '{b}\n{d}%',
                                            textStyle: {
                                                baseline : 'bottom'
                                            }
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    }
                                }}
                        ]
                    }
                ]
            };
           /* var option6Params1=100;//未控人数
            var option6Params2=60;//控制人数
            var option6 = {
                color: ['#07d1e0','#366779'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                series : [
                    {
                        name:'糖尿病',
                        type : 'pie',
                        radius : ['50%', '70%'],
                        x: '0%', // for funnel
                        itemStyle :  {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return option6Params1 - params.value + '%'
                                    },
                                    textStyle: {
                                        baseline : 'top',
                                        color:'#fff'

                                    }
                                }
                            },
                        },
                        data : [
                            {name:'未控制人数', value:option6Params1-option6Params2,
                                itemStyle :{
                                    normal : {
                                        color: '#D14A61',
                                        label : {
                                            show : false,
                                            position : 'center'
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },
                                    emphasis: {
                                    }
                                }
                            },
                            {name:'控制人数', value:option6Params2,itemStyle : {
                                normal : {
                                    color:'#0982FF',
                                    label : {
                                        show : true,
                                        position : 'center',
                                        formatter : '{b}\n{d}%',
                                        textStyle: {
                                            baseline : 'bottom'
                                        }
                                    },
                                    labelLine : {
                                        show : false
                                    }
                                }
                            }}
                        ]
                    }

                ]
            };*/
            myChart3.setOption(option3);
            //myChart4.setOption(option4);
            myChart5.setOption(option5);
            //myChart6.setOption(option6);
		}

        //第二页三个柱状图
        function loadChartByData(data){

//            {
//            "followUpPeopleStatistics": {
//                "hyperCount": null,
//                    "dabetesCount": null,
//                    "childCount": null,
//                    "cjCount": null
//            },
//            "eaminationStatistics": {
//                "otherExaminCount": 5,
//                    "oldManCount": 5
//            },
//            "servicedNumber": {
//                "profileCount": 252440,
//                    "healthAsseCount": 0,
//                    "interroCount": 0,
//                    "healthEduCount": null
//            },
//            "chronicDiseaseCtrl": {
//                "hyperPersonDeseaseCtrlRate": 0,
//                    "dabetesPersonDeseaseCtrlRate": 0
//            },
//            "chronicDiseaseManage": {
//                "hyperPersonDeseaseManageRate": null,
//                    "dabetesPersonDeseaseManageRate": null
//            }
//        }

//            {
//            "followUpPeopleStatistics": {
//                "hyperCount": null,
//                    "dabetesCount": null,
//                    "childCount": null,
//                    "cjCount": null
//            },
//            "eaminationStatistics": {
//                "otherExaminCount": 5,
//                    "oldManCount": 5
//            },
//            "servicedNumber": {
//                "profileCount": 252440,
//                    "healthAsseCount": 0,
//                    "interroCount": 0,
//                    "healthEduCount": null
//            },
//            "chronicDiseaseCtrl": {
//                "hyperPersonDeseaseCtrlRate": 0,
//                    "dabetesPersonDeseaseCtrlRate": 0
//            },
//            "chronicDiseaseManage": {
//                "hyperPersonDeseaseManageRate": null,
//                    "dabetesPersonDeseaseManageRate": null
//            }
//        }
                var myChart1 = echarts.init(document.querySelector(".bar_grap21"));
                var myChart2 = echarts.init(document.querySelector(".bar_grap22"));
                //var myChart3 = echarts.init(document.querySelector(".bar_grap23"));
                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    title: {
                        textStyle: {
                            color: '#fff',
                            fontStyle: 'normal',
                            fontWeight: 'bolder',
                            fontFamily: 'sans-serif',
                            fontSize: 18,
                        }
                    },
                    grid: {
                        left: '10%',
                        right: '10%',
                        bottom: '10%',
                        containLabel: true
                    },
                    color: ['#0977f1','#f25757'],
                    tooltip : {
                        trigger: 'axis'
                    },
                    calculable : true,
                };

                var option0 = {
                title:{
                    text:"随访人次"
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                xAxis: [
                    {
                        name: '类别',
                        nameGap: 25,
                        nameTextStyle: {
                            color: "#fff",
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: 'sans-serif',
                            fontSize: 12,
                        },
                        type: 'category',
                        data: ['高血压', '糖尿病', '儿童', '重度精神病'],
                        nameLocation: "middle",
                        axisTick: {
                            alignWithLabel: true
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#807c77',
                                width: 3,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'bold',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                    }
                ],
                yAxis: [
                    {
                        // name: "人次",
                        nameTextStyle: {
                            color: "#fff",
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: 'sans-serif',
                            padding: [0, 10, 0, 0],
                            fontSize: 12,
                        },
                        nameGap: 25,
                        type: 'value',
                        nameLocation: "middle",
                        nameRotate: Math.PI / 2,
                        // nameLocation:"end",
                        //分割线
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: ['#b7b5b3'],
                                //96938f
                                width: 2,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0
                            }
                        },
                        //刻度
                        axisLabel: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'bold',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                        //刻度线
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#807c77',
                                width: 3,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        }
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barWidth: '40%',
                        data: data?covertObjArray(data.followUpPeopleStatistics):[0,0,0,0],
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff',
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    fontFamily: 'sans-serif',
                                    fontSize: 12,
                                },
                            }
                        },
                    }
                ]
            };
            var option1 =  {
                title:{
                    text:"体检情况"
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['其他体检', '老人体检'],
                        nameLocation: "middle",
                        axisTick: {
                            alignWithLabel: true
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#7dae7f',
                                width: 1,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'normal',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        // nameLocation:"end",
                        //分割线
                        splitNumber: 10,
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: ['#7dae7f'],
                                //96938f
                                width: 1,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0
                            }
                        },
                        //刻度
                        axisLabel: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'normal',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                        //刻度线
                        axisTick: {
                            color: ['#7dae7f'],
                            show: true,
                            width: 1,
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#7dae7f',
                                width: 1,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        }
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barWidth: '40%',
                        data: data?covertObjArray(data.eaminationStatistics):[0,0],
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff',
                                    fontStyle: 'normal',
                                    fontWeight: 'normal',
                                    fontFamily: 'sans-serif',
                                    fontSize: 12,
                                },
                            }
                        },
                    }
                ]
            };

                function covertObjArray(obj) {
                    var values = [];
                    for (prop in obj) {
                        values.push(obj[prop]);
                    }
                    return values;
                }

              /*  var option2 ={
                    title:{
                        text:"服务人数情况"
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: [
                        {
                            name: '类别',
                            nameGap: 25,
                            nameTextStyle: {
                                color: "#fff",
                                fontStyle: 'normal',
                                fontWeight: 'normal',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                            type: 'category',
                            data: ['建档人数', '健康评估', '问诊人数', '健康教育'],
                            nameLocation: "middle",
                            axisTick: {
                                alignWithLabel: true
                            },
                            axisLine: {
                                show: true,
                                onZero: true,
                                lineStyle: {
                                    color: '#807c77',
                                    width: 3,
                                    type: 'solid',
                                    shadowOffsetX: 0,
                                    shadowOffsetY: 0,
                                },
                            },
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#fff',
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    fontFamily: 'sans-serif',
                                    fontSize: 12,
                                },
                            },
                        }
                    ],
                    yAxis: [
                        {
//                        name: "人数",
                            nameTextStyle: {
                                color: "#fff",
                                fontStyle: 'normal',
                                fontWeight: 'normal',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                            nameGap: 25,
                            type: 'value',
                            nameLocation: "middle",
                            nameRotate: Math.PI / 2,
                            // nameLocation:"end",
                            //分割线
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: ['#b7b5b3'],
                                    //96938f
                                    width: 2,
                                    type: 'solid',
                                    shadowOffsetX: 0,
                                    shadowOffsetY: 0
                                }
                            },
                            //刻度
                            axisLabel: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff',
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    fontFamily: 'sans-serif',
                                    fontSize: 12,
                                },
                            },
                            //刻度线
                            axisTick: {
                                show: false
                            },
                            axisLine: {
                                show: true,
                                onZero: true,
                                lineStyle: {
                                    color: '#807c77',
                                    width: 3,
                                    type: 'solid',
                                    shadowOffsetX: 0,
                                    shadowOffsetY: 0,
                                },
                            }
                        }
                    ],
                    series: [
                        {
                            type: 'bar',
                            barWidth: '40%',
                            data: data?covertObjArray(data.servicedNumber):[0,0,0,0],
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    textStyle: {
                                        color: '#fff',
                                        fontStyle: 'normal',
                                        fontWeight: 'bold',
                                        fontFamily: 'sans-serif',
                                        fontSize: 12,
                                    },
                                }
                            },
                        }
                    ]
                };*/
                myChart1.setOption(option);
                myChart2.setOption(option);
             //  myChart3.setOption(option);
                myChart1.setOption(option0);
                myChart2.setOption(option1);
                //myChart3.setOption(option2);
        }
     /*   function loadSuiFang(data1,data2,data3,dta4){
            alert(data1,data2,data3,dta4);
            var myChart1 = echarts.init(document.querySelector(".bar_grap21"));
            var option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                title: {
                    textStyle: {
                        color: '#fff',
                        fontStyle: 'normal',
                        fontWeight: 'bolder',
                        fontFamily: 'sans-serif',
                        fontSize: 18,
                    }
                },
                grid: {
                    left: '10%',
                    right: '10%',
                    bottom: '10%',
                    containLabel: true
                },
                color: ['#0977f1','#f25757'],
                tooltip : {
                    trigger: 'axis'
                },
                calculable : true,
            };
            var option0 = {
                title:{
                    text:"随访人次"
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                xAxis: [
                    {
                        name: '类别',
                        nameGap: 25,
                        nameTextStyle: {
                            color: "#fff",
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: 'sans-serif',
                            fontSize: 12,
                        },
                        type: 'category',
                        data: ['高血压', '糖尿病', '儿童', '重度精神病'],
                        nameLocation: "middle",
                        axisTick: {
                            alignWithLabel: true
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#807c77',
                                width: 3,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'bold',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                    }
                ],
                yAxis: [
                    {
                        // name: "人次",
                        nameTextStyle: {
                            color: "#fff",
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontFamily: 'sans-serif',
                            padding: [0, 10, 0, 0],
                            fontSize: 12,
                        },
                        nameGap: 25,
                        type: 'value',
                        nameLocation: "middle",
                        nameRotate: Math.PI / 2,
                        // nameLocation:"end",
                        //分割线
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: ['#b7b5b3'],
                                //96938f
                                width: 2,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0
                            }
                        },
                        //刻度
                        axisLabel: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: '#fff',
                                fontStyle: 'normal',
                                fontWeight: 'bold',
                                fontFamily: 'sans-serif',
                                fontSize: 12,
                            },
                        },
                        //刻度线
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: true,
                            onZero: true,
                            lineStyle: {
                                color: '#807c77',
                                width: 3,
                                type: 'solid',
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                            },
                        }
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barWidth: '40%',
                        data: [data1,data2,data3,dta4],
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff',
                                    fontStyle: 'normal',
                                    fontWeight: 'bold',
                                    fontFamily: 'sans-serif',
                                    fontSize: 12,
                                },
                            }
                        },
                    }
                ]
            };
            myChart1.setOption(option);
            myChart1.setOption(option0);
		}*/

	</script>
	<script type="text/javascript" src="/fdoctor/statics/js/screen/main.js"></script>
</body>
</html>