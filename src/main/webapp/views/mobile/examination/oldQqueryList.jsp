<%--
  Created by IntelliJ IDEA.
  User: mojo
  Date: 2017/2/27

  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>老年人体检</title>
    <link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
    <link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->
    <link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

    <script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
    <!--  <script type="text/javascript" src="/fdoctor/statics/js/examination/query.js"></script>-->

    <script type="text/javascript">

    </script>
</head>
<body>
<div class="main">
    <!--********************表单开始**************************-->
    <form  id="searchForm" action="" >
        <div class="search-form">
            <div class="form-group1">
                区划： <input name="regionCode" id="regionCode" class="easyui-combotree form-control" data-options="url:'/fdoctor/getorg',method:'get'" style="width: 20%;">
                <span class="seat"></span>
                体检日期
                <input type="date" name="FollowUpDateS" id="FollowUpDateS" value=""  class=""/>-
                <input type="date" name="FollowUpDateE" id="FollowUpDateE" value=""  class=""/>
                <span class="seat"></span>

            </div>
            <div class="form-group1 mt10" >
                <select name="KeyValueType" id="type">
                    <option value="1">姓名或拼音</option>
                    <option value="2">身份证</option>
                    <option value="3">档案号</option>
                </select>
                <input type="text" name="KeyValue" id="KeyValue" value=""  class="name"/>
                <span class="seat"></span>


                <span class="seat"></span>
                是否完善： <select name="IsPerfect" id="IsPerfect">
                <option value="" selected="selected">全部</option>
                <option value="0" >是</option>
                <option value="1">否</option>
            </select>

            </div>
        </div>
        <div class="search-btn">
            <a id="search" >查询居民</a>
            <input type="hidden" name="JsFromAd" id="JsFromAd" value="">
        </div>
    </form>
    <!--********************表单结束**************************-->

    <div class="clear"></div>
    <div class="operation">
        <a  id="edit"  href="javascript:void(0);" class="btg">编辑</a>
        <a id="delete"  href="javascript:void(0)" class="btg">删除</a>
        <a id="add"  href="javascript:void(0)" class="btg">添加体检</a>
        <a href="javascript:void(0)" class="btg">读健康卡</a>
    </div>
    <div class="table1">
        <table id="examTable" border="1" class="table table-striped table-bordered table-hover">
            <thead >
            <tr class="bgf1">
                <!--  <td>序号 </td>-->
                <td>成员姓名 </td>
                <td>性别 </td>
                <td>年龄 </td>
                <td>身份证 </td>
                <td>联系电话 </td>
                <td>健康指导 </td>
                <td>最后体检时间 </td>
                <td>是否完善 </td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">


    $(function(){
        var oTable=null;
        //$('select').prop('selectedIndex', 0);
        // 获取选中的某一行值
        $('#examTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                oTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

        //身份证回显安卓有传数据
   	 var androidDataJson=${androidDataMap}
   	         if(androidDataJson.personId=="123"){}else{
   			  oTable=$('#examTable').DataTable({
   	                            "sLoadingRecords":"载入中...",
   	                            "autoWidth":false,
   	                            //"stateSave": true,
   	                            "ordering":false,
   	                            "bPaginate" : true,// 分页按钮
   	                            "pagingType":   "full_numbers",//full_numbers  full simple
   	                            "bFilter" : false,// 搜索栏
   	                            "iDisplayLength" : 10,
   	                            "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
   	                            "serverSide": true,//打开后台分页
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
   	                             ajax:{
   	                                 url: '/fdoctor/mobile/examination/personExamination',
   	                                 data: {
   	                                 PersonID :androidDataJson.personId,
   	                                 doctorName:"${doctorName}"
   	                                 }
   	                             },
   	                            "columns": [
   	                                { "data": "NAME" },
   	                                { "data": "GENDER" },
   	                                { "data": "AGE" },
   	                                { "data": "CARD_ID" },
   	                                { "data": "PERSON_TEL" },
   	                                { "data": "GUIDANCE" },
   	                                { "data": "FOLLOW_UP_DATE" },
   	                                { "data": "SOPHISTICATION" }
   	                            ],
   	                            "filter": false,
   	                            "destroy": true,
   	
   	                        })
   	         } 
        //个人列表查询
        $('#serchPerson').on('click',function(){
            var PersonIDStro=oTable.row('.selected').data().ID;
            var PersonIDStr=JSON.stringify(PersonIDStro);
            console.log(PersonIDStr)
            if(PersonIDStr==''||PersonIDStr==null){
                alert("请先选择居民")
            } else{
                oTable=$('#examTable').DataTable({
                    "sLoadingRecords":"载入中...",
                    "autoWidth":false,
                    //"stateSave": true,
                    "ordering":false,
                    "bPaginate" : true,// 分页按钮
                    "pagingType":   "full_numbers",//full_numbers  full simple
                    "bFilter" : false,// 搜索栏
                    "iDisplayLength" : 10,
                    "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
                    "serverSide": true,//打开后台分页
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
                    ajax:{
                        url: '/fdoctor/mobile/examination/personExamination',
                        data: {
                            PersonID :oTable.row('.selected').data().ID,
                             doctorName:"${doctorName}"
                        }
                    },
                    "columns": [
                        { "data": "NAME" },
                        { "data": "GENDER" },
                        { "data": "AGE" },
                        { "data": "CARD_ID" },
                        { "data": "PERSON_TEL" },
                        { "data": "GUIDANCE" },
                        { "data": "FOLLOW_UP_DATE" },
                        { "data": "SOPHISTICATION" }
                    ],
                    "filter": false,
                    "destroy": true,

                })
            }
        })



        //编辑
        $('#edit').on('click', function () {

            var radte=oTable.row('.selected').data();
            /*for(i in androidDataJson){
             radte[i]=androidDataJson[i]
             } */
            radte=JSON.stringify(radte);
            if(radte==''||radte==null){
                alert("请先选择居民")
            } else{
                $('#edit').attr("href", "/fdoctor/mobile/examination/updateExaminationOld?radte="+radte+"&doctorName="+"${doctorName}");
            }
        } );
        //新增体检
        $('#add').on('click', function () {

            var radte=oTable.row('.selected').data();
            radte=JSON.stringify(radte);
            if(radte==''||radte==null){
                alert("请先选择居民")
            } else{
                $('#add').attr("href", "/fdoctor/mobile/examination/toAddExamination?radte="+radte+"&doctorName="+"${doctorName}");
            }
        } );
        //删除体检

        $('#delete').on('click', function () {
            var ID=oTable.row('.selected').data().ID;
            ID=JSON.stringify(ID);
            if(ID==''||ID==null){
                alert("请先选择居民")
            } else{
                if(window.confirm('是否删除？')){
                    $.ajax({
                        type: 'POST',
                        url: '/fdoctor/mobile/examination/deletExamination',
                        data: {
                            MtID :ID,
                            doctorName:"${doctorName}"
                        } ,
                        success: function(data){
                            if(data.code == '200'){
                                alert('删除成功！');

                            }else{
                                alert('删除失败！');
                            }
                        }
                    });
                    return true;
                }else{
                    return false;
                }

            }
        } );


        //区划获取
        $('#regionCode').combotree({
            onBeforeExpand:function(node) {
                $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;
            }
        });
        //	查询点击事件
        $("#search").click(function() {
            //datatables
            oTable=$('#examTable').DataTable({
                "sLoadingRecords":"载入中...",
                "autoWidth":false,
                //"stateSave": true,
                "ordering":false,
                "bPaginate" : true,// 分页按钮
                "pagingType":   "full_numbers",//full_numbers  full simple
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

                "processing":     "读取中...",

                "serverSide": true,//打开后台分页


                ajax: {

                    url: "/fdoctor/mobile/examination/OldTableList",
                    data:{
                        KeyValueType: $('select[name=KeyValueType]').val(),
                        KeyValue: $('#KeyValue').val(),
                        FollowUpDateS: $('#FollowUpDateS').val(),
                        FollowUpDateE:$('#FollowUpDateE').val(),
                        IsStandard: $('select[name=IsPerfect]').val(),
                        RegionCode:getRegionCode(),
                        doctorName:"${doctorName}"
                    }
                },
                "aoColumnDefs":[
    	                        {"aTargets":[7], "mRender":function (data, type, c, d) {
    	                        	
    	                        	if(data=="否"){
    	                        		
    	                        		 data ="<a href='#'><font color='blue'>未完善</font></a>";  
    	                        	}else{
    	                        		data="完善"
    	                        	}
    	                        	return   data;  
    	                        }}
                               
    	                        ],

                "columns": [


                    { "data": "NAME" },
                    { "data": "GENDER" },
                    { "data": "AGE" },
                    { "data": "CARD_ID" },
                    { "data": "TELPHONE" },
                    { "data": "GUIDANCE" },
                    { "data": "FOLLOW_UP_DATE" },
                    { "data": "PERFECT" }
                ],
                "filter": false,
                "destroy": true,

            });


        })

    });
    
    
    
    
    function setSelectByValue (selectId, value) {
        var select = document.getElementById(selectId);
        var opt;
        for (var i = 0, len = select.options.length; i < len; i++) {
            opt = select.options[i];
            if (opt.value == value) {
                opt.selected = true;
                break;
            }
        }
    }

    //获取选中区划节点的id
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
    
    
  //读取健康卡 方法

    function readHealthCard(){
     
    window.control.getInfo();

    }

    function result(idCard){
    	//setSelectByValue("type",2);
    	//$('#KeyValue').val("")
    	oTable=$('#examTable').DataTable({
    	    "sLoadingRecords":"载入中...",
    	    "autoWidth":false,
    	    //"stateSave": true,
    	    "ordering":false,
    	    "bPaginate" : true,// 分页按钮
    	    "pagingType":   "full_numbers",//full_numbers  full simple
    	    "bFilter" : false,// 搜索栏
    	    "iDisplayLength" : 10,
    	    "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
    	    "serverSide": true,//打开后台分页
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
    	     ajax:{
    	         url: '/fdoctor/mobile/examination/personExamination',
    	         data: {
    	         idCard :idCard,
    	         doctorName:"${doctorName}"
    	         }
    	     },
    	    "columns": [
    	        { "data": "NAME" },
    	        { "data": "GENDER" },
    	        { "data": "AGE" },
    	        { "data": "CARD_ID" },
    	        { "data": "PERSON_TEL" },
    	        { "data": "GUIDANCE" },
    	        { "data": "FOLLOW_UP_DATE" },
    	        { "data": "SOPHISTICATION" }
    	        
    	    ],
    	    "filter": false,
    	    "destroy": true,

    	})
    	
    }

</script>
</html>

