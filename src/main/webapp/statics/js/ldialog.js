var iframeWin;
var pageWin;
var ff;
//增加字段，用于存储执行弹出框的页面window对象，如弹出页需要使用，请在页面中增加openWin全局变量，js将默认赋值(注：
//现在无法在页面ready方法执行之前注入成功，如果需要在ready中调用，请使用定时器，每个一段时间去判断一次是否赋值，如果赋值再执行相应的代码
//可以参考 loadDevicePort.jsp页面的openWin调用方式)
var openWin;
var target;
var options;
var refDocHandle;

(function($){
	openWin = window;
	try{
		var obj = top.$("#iframe iframe");
		ff = obj[0].contentWindow;
		if(!ff){
			ff = window;
		}
	}catch(e){
		ff = window;
	}
	//直接在最外层窗口显示
	pageWin = ff;
	iframeWin = ff;
	function createLdiglog(target)
	{	
		var state = $.data(target,'ldialog');
		var opt = state.options;
		if(opt.showOutSide){
			iframeWin = top;	//最外层弹出
			if(opt.onClose){
				opt.onCloseByNew=true,
				opt.target = iframeWin.document	.body;
			}
		}
		var ldialogs=iframeWin.$("#ldialogs_"+opt.idFled);
		var isNewWin = true;
		if(ldialogs[0]){
			isNewWin = false;
		}
		iframeWin.options = opt;
		if(opt.url!=""){
			if(isNewWin){
				$(iframeWin.document.body).append("<div id='ldialogs_"+opt.idFled+"'></div>");
				var div_frist="<div id=\"s_"+opt.idFled+"\" name=\"dialog_thisDialog\" style=\"overflow:hidden\"" 
				+"\"><iframe frameborder='0' id='dialog_showWindow_"+opt.idFled+"' src='' ></iframe></div>";
				iframeWin.$("#ldialogs_"+opt.idFled).append(div_frist);
				iframeWin.$("#s_"+opt.idFled).window(opt);
				var obj=iframeWin.$("#dialog_showWindow_"+opt.idFled);
				obj.width(obj.parent().width());
				obj.height(obj.parent().height());
				obj.attr("src",opt.url);
				if(!window.attachEvent){
					iframeWin.document.getElementById("dialog_showWindow_"+opt.idFled).addEventListener("load", function(){
						hiddenAppend(opt.idFled);
						this.removeEventListener("load", arguments.call, false);
					}, false);
				}
			}else{
				iframeWin.$("#dialog_showWindow_"+opt.idFled).attr("src","about:blank");
				setTimeout(function(){refWindowUrl("dialog_showWindow_"+opt.idFled,opt.url);},50);
			}
			iframeWin.$("#s_"+opt.idFled).window(opt);
			iframeWin.$("#s_"+opt.idFled).window("open");
			refDocHandle = window.setInterval('setOpenWin("'+opt.idFled+'")',500);
		}else{
			iframeWin.$("#"+opt.idFled).append("<input type='hidden' name='dialog_thisDialog' value='"+ opt.idFled +"' />");
			iframeWin.$("#"+opt.idFled).window(opt);
			iframeWin.$("#"+opt.idFled).window("open");
		}
	}
	$.fn.ldialog=function(options,param){
		options = options || {};
		if(!$(this)[0]){
			//直接调用方法
			if(options.showOutSide){
				iframeWin = top;
			}
			target = $('<div></div>').appendTo(iframeWin.document.body);
				var state = $.data(this, 'ldialog');
				if (state){
					$.extend(this, options);
				} else {
					$.data(this, 'ldialog', {
						options: $.extend({}, $.fn.ldialog.defaults, options)
					});
				}
				createLdiglog(target);
		}else{
			return this.each(function(){
				var state = $.data(this, 'ldialog');
				if (state){
					$.extend(state.options, options);
				} else {
					$.data(this, 'ldialog', {
						options: $.extend({}, $.fn.ldialog.defaults, options)
					});
				}
				createLdiglog(this);
			});
		}
	};
	$.fn.ldialog.defaults = {
		idFled:'default1',
		width:100,
		height:160,
		url:'',
		showOutSide:false,
		title:'',
		iconCls:'icon-tip',
		collapsible:false,
		resizable:false,
		draggable:false,
		minimizable:false,
		maximizable:false,
		modal:true,
		closed:true
	};
	function refWindowUrl(divId,url){
		if(options&&options.showOutSide){
			iframeWin = top;
		}
		iframeWin.$("#"+divId).attr("src",url);
	}
})(jQuery);

//其他浏览器出入ID
function hiddenAppend(id)
{	
	if(options&&options.showOutSide){
		iframeWin = top;
	}
	try{
		var s_body=$(iframeWin.document.getElementById("dialog_showWindow_"+id).contentWindow.document.body);
		s_body.append("<input type='hidden' name='dialog_thisDialog' value='s_"+ id +"' />");
	}catch (e) {
	}
}  
function dialogOpen(divId){
	if(options&&options.showOutSide){
		iframeWin = top;
	}
	iframeWin.$("#s_"+divId).window("open");
}
function dialogClose(){
	var ids=document.getElementsByName("dialog_thisDialog");
	var id="";
	if(ids){
		id=ids[0].value;
	}
	parent.$("#"+id).window("close");
}
function changeTitle(divId,titleStr){
	iframeWin.$("#s_"+divId).window("setTitle",titleStr);
}
var setTime = 0;
function setOpenWin(div){
	setTime++;
	if(setTime==10){
		clearInterval(refDocHandle);
	}
	iframeWin.openWin = openWin;
	var iframeWindow ;
	try{
		iframeWindow=iframeWin.$("#dialog_showWindow_"+div)[0].contentWindow;
		if(!("openWin" in iframeWindow)){
			iframeWindow = undefined;
			return;
		}else{
		}
	}catch(e){
		iframeWindow = undefined;
		return;   
	}
	if(iframeWindow){
		iframeWindow.openWin = openWin;
		clearInterval(refDocHandle);
	}
}
