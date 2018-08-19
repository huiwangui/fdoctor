function loadBarGraph(chartId, totalArray, incrementArray) {
	var myGraph = echarts.init(document.getElementById(chartId));
	graphOption = {
		color : [ '#5793f3', '#d14a61' ],
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : [ '总人数', '月增长' ],
			textStyle : {
				color : '#FFFFFF'
			},
			bottom : '5%'
		},
		xAxis : {
			data : [ "高血压", "糖尿病", "儿童", "重性精神病" ]
		},
		textStyle : {
			color : '#FFFFFF'
		},
		yAxis : {},
		series : [ {
			name : '总人数',
			type : 'bar',
			data : totalArray
		}, {
			name : '月增长',
			type : 'bar',
			data : incrementArray
		} ]
	};

	myGraph.setOption(graphOption);
}

function loadPieChart(chartId, signCount, personCount) {
	var myPie = echarts.init(document.getElementById(chartId));
	pieOption = {
		color : [ '#07d1e0', '#366779' ],
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		toolbox : {
			show : false,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : false,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '20%',
							width : '50%',
							funnelAlign : 'center',
							max : 1548
						}
					}
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : false
				}
			}
		},
		calculable : true,
		series : [ {
			name : '签约比例',
			type : 'pie',
			radius : [ '50%', '80%' ],
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
			data : [ {
				value : signCount,
				name : '已签约人数'
			}, {
				value : personCount - signCount,
				name : '未签约人数'
			} ]
		} ]
	};
	myPie.setOption(pieOption);
}

(function(doc, win) {
	var docEl = doc.documentElement, resizeEvt = 'orientationchange' in window ? 'orientationchange'
			: 'resize', recalc = function() {
		var clientWidth = docEl.clientWidth;
		if (!clientWidth)
			return;
		docEl.style.fontSize = 20 * (clientWidth / 3600) + 'px';
	};

	if (!doc.addEventListener)
		return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
