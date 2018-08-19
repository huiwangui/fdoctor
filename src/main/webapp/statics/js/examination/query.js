
$(function(){
	$('select').prop('selectedIndex', 0);

	$('#regionCode').combotree({                     
	    onBeforeExpand:function(node) {   
	     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
	    }  
	 });  
	 
     
      // 获取选中的某一个checkbox的值

     if ($(".checkchild:checked").length > 1) {   
    	 alert("一次只能修改一条数据");               
    	 return;           
    	 }

     var id = $(".checkchild:checked").val();

	$('#regionCode').combotree({                     
	    onBeforeExpand:function(node) {   
	     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
	    }  
	 }); 
	
	$("#search").click(function() {
		//datatables
		var oTable=$('#examTable').DataTable({
			  "sLoadingRecords":"载入中...",
              
			  "bPaginate" : true,// 分页按钮  
		      "pagingType":   "full_numbers",//full_numbers
		      "bFilter" : false,// 搜索栏  
		      "iDisplayLength" : 10,
		      "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表  
		       "oLanguage": {  //对表格国际化  
		         "sLengthMenu": "每页显示 _MENU_条",    
		         "sZeroRecords": "没有找到符合条件的数据",    
		         "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",    
		         "sInfoEmpty" : "共 _MAX_条", 
		         "sInfoFiltered": "",
		         'zeroRecords': '没有数据', 
		         'emptyTable': '没有数据',  
		         "oPaginate": {    
		         "sFirst": "首页",    
		         "sPrevious": "上页",    
		         "sNext": "下页",    
		         "sLast": "尾页"    
		          },     
		
		        },  
		    // "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json",
		     "processing":     "读取中...",
		    // "processing": true, //打开数据加载时的等待效果  
		    //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;", 
		    "serverSide": true,//打开后台分页 
			
		  
		    ajax: {  
		    	 url: "/fdoctor/mobile/examination/ExaminationTableList",   
	              data:{ 
	              KeyValueType: $('select[name=KeyValueType]').val(),
	   		      KeyValue: $('#KeyValue').val(),
			      FollowUpDateS: $('#FollowUpDateS').val(),
			      FollowUpDateE:$('#FollowUpDateE').val(),
			      IsStandard: $('select[name=IsStandard]').val(),
			      isExamed: $('select[name=isExamed]').val(),
			      isperson: $('select[name=isperson]').val(),
			      type:$('select[name=type]').val(),
			      RegionCode:getRegionCode()
	              }
	        }, 
		 
		    "columns": [
		   {
		       data:   "ID",
		       render: function ( data, type, row ) {
		           if ( type === 'display' ) {
		               return '<input type="checkbox" class="editor-active" value="' + data + '" />';
		           }
		           return data;
		       },
		       className: "dt-body-center"
		   },
		                
		                { "data": "Name" },
		                { "data": "GenderCode" },
		                { "data": "Age" },
		                { "data": "CardID" },
		                { "data": "Telphone" },
		                { "data": "jkzd" },
		                { "data": "Lasthldate" },
		                { "data": "Sophistication" }
		                ],
		    "filter": false,
		    "destroy": true,
		});
		
 })
		 
});

function getRegionCode(){
	try{
		var t = $("#regionCode").combotree('tree'); // 得到树对象  
		var n = t.tree('getSelected');
		if(n != null){
			return n.id;
		}else{
			return '51';
		}
	}catch(e){
		return '';
	}
}
