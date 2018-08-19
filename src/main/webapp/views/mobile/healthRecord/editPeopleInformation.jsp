<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
		 
	</head>
	<body>
		<div class="main">
			<div class="Edit_navbar ">
				<ul>
					<li>
						<a href="javascript:void(0)" class="active_li">封面 <span>(必填)</span> </a>
					</li>
					<li>
						<a href="javascript:void(0)">基本信息 <span>(必填)</span> </a>
					</li>
					<li>
						<a href="javascript:void(0)">扩展信息 <span>(选填)</span> </a>
					</li>
					<li>
						<a href="javascript:void(0)">体检 <span></span> </a>
					</li>
					<li>
						<a href="javascript:void(0)">档案信息卡 <span></span> </a>
					</li>
					<li>
					    <a   id="save" href="javascript:void(0)">保存</a>			 
					</li>
				</ul>
			</div>
			<!------------------------------------封面---------------------------->
			<div class="content_div">
				<div class="personNumber">
					<table>
						<tr>
							<td class="tr">个人编号：</td>
							<td><div name="person_code_1"></div></td>
							 
						</tr>
					</table>
				</div>
				
				<h1 class="tc">居民健康档案</h1>
				<table class="health_table1">
					<tr>
						<td class="tr">自定编号：</td>
						<td> <input type="text" name="CustomNumber" id="CustomNumber" value="${data.CustomNumber }"  class="borderb"/></td>
					</tr>
					<tr>
						<td class="tr">姓 名：</td>
						<td> <input type="text" name="Name" id="Name" value="${data.Name }" onkeyup='$("#tab2_name").text($("#Name").val())' class="borderb"/></td>
					</tr>
					<tr>
						<td class="tr">身份证号：</td>
						<td>
							<input type="text" name="CardID" id="CardID" value="${data.CardID }" class="borderb"/>
							<!-- <input type="button" name="" id="" value="验证身份证" class=""/> -->
							<a href="javascript:void(0)" class="sibtn_small">验证身份证</a>
							<label>
								<input type="radio" name="idCard" id="idCard_1"   /> 无
							</label>
							<label>
								<input type="radio" name="idCard" id="idCard_2"  /> 不详
							</label>
						</td>
					</tr>
					<tr>
						<td class="tr">现 住 址：</td>
						<td> 
							<input type="text" name="CurrentAddress" id="CurrentAddress" value="${data.CurrentAddress }" class="borderb"/>
							<!-- <input type="button" name="" id="masterAdd" value="当前户主地址" class="sibtn_small"/> 
							<input type="button" name="" id="currentAdd" value="使用当前组" class=""/>  -->
							<a href="javascript:void(0)" class="sibtn_small" id="masterAdd">当前户主地址</a>
							<a href="javascript:void(0)" class="sibtn_small" id="masterAdd">使用当前组</a>
						</td>
					</tr>
					<tr>
						<td class="tr">户籍地址：</td>
						<td>
							<input type="text" name="ResidenceAddress" id="ResidenceAddress" value="${data.ResidenceAddress }"  class="borderb" style="width:200px"/> 
							<!-- <input type="button" name="" id="fAdd" value="使用现住址" class=""/>  -->
							<a href="javascript:void(0)" class="sibtn_small" id="fAdd">使用现住址</a>
						</td>
					</tr>
					<tr>
						<td class="tr">本人电话：</td>
						<td>
							<input type="text" name="PersonTel" id="PersonTel" value="${data.PersonTel }"  class="borderb" />
							<label>
								<input type="radio" name="ptelephone" id="ptelephone1" value="" /> 无
							</label>
							<label>
								<input type="radio" name="ptelephone" id="ptelephone2" value="" /> 不详
							</label>
							
							<span>
								贫困人口： 
								<select id="IsPoor" name="">
									<option value="2">是</option>
									<option value="1">否</option>
								</select>
							</span>
						</td>
					</tr>
					<tr>
						<td class="tr">乡镇(街道)名称：</td>
						<td>
							<input type="text" name="xiangzhen=" id="xiangzhen=" disabled value="${data.xiangzhen }" class="borderb"/>
						</td>
					</tr>
					<tr>
						<td class="tr">村(居)委会名称</td>
						<td>
							<input type="text" name="cun" id="cun" value="${data.cun }" disabled class="borderb"/>
						</td>
					</tr>
					<tr>
						<td class="tr"><span class="redcolor">*</span>与户主关系：</td>
						<td>
							<select id="HouseholderRelationship" name="">
								<option value="1">本人</option>
								<option value="2">配偶</option>
								<option value="3">子</option>
								<option value="4">女婿</option>
								<option value="5">女</option>
								<option value="6">儿媳</option>
								<option value="7">父亲</option>
								<option value="8">母亲</option>
								<option value="9">孙子</option>
								<option value="10">孙女</option>
								<option value="11">外孙子</option>
								<option value="12">外孙女</option>
								<option value="13">爷爷</option>
								<option value="14">奶奶</option>
								<option value="15">外公</option>
								<option value="16">外婆</option>
								<option value="17">兄弟姐妹</option>
								<option value="18">孙媳妇或外孙媳妇</option>
								<option value="19">孙女婿或外孙女婿</option>
								<option value="20">曾孙子或外曾孙子</option>
								<option value="21">曾孙女或外曾孙女</option>
								<option value="22">公公</option>
								<option value="23">婆婆</option>
								<option value="24">岳父</option>
								<option value="25">岳母</option>
								<option value="26">其他</option>
								<option value="27">不详</option>
							</select>
							<span class="redcolor">*</span>户口类型：
							<input type="hidden" name="ResType" id="ResType" />
							<label >
								<input type="radio" name="agriculture" id="agriculture1" value="1" /> 农业
							</label>
							<label >
								<input type="radio" name="agriculture" id="agriculture2" value="2" />非 农业
							</label>
							<label >
								<input type="checkbox" name="IsFlowing" id="IsFlowing"   /> 流动人口
							</label>
						</td>
					</tr>
					<tr>
						<td class="tr">建档单位：</td>
						<td>
							<input type="text" name="BuildOrgName" id="BuildOrgName" value="${data.BuildOrgName }" readOnly="readOnly"  class="borderb"/>
						</td>
					</tr>
					<tr>
						<td class="tr">建档人：</td>
						<td>
							<a href="javascript:void(0)"><input type="text" name="BuildEmployeeName" id="BuildEmployeeName" value="${data.BuildEmployeeName }"  class="borderb"/></a>
						</td>
					</tr>
					<tr>
						<td class="tr">责任医生或者护士</td>
						<td>
							<a href="javascript:void(0)"><input type="text" name="ResponsibilityDoctor" id="ResponsibilityDoctor" value="${data.ResponsibilityDoctor }"  class="borderb"/> </a>
						</td>
					</tr>
					<tr>
						<td class="tr">建档日期：</td>
						<td>
							<input type="date"  name="BuildDate" id="BuildDate"   class="borderb"/>
						</td>
					</tr>
				</table>
			</div>
			<!------------------------------------基本信息---------------------------->
			<div class="content_div" hidden="">
				<h1>基本信息</h1>
				<div class="clearfix">
					<div class="fl">
						姓名：<span id="tab2_name">${data.Name }</span>
					</div>
					<div class="fr">个人编号：<span>${data.PersonCode }</span></div>
				</div>
				<table border="1">
					<tr>
						<td>性别</td>
						<td colspan="3">					 
							<a onClick="Gendering(this)" value="0" class="option">0  未知的性别    </a>
							<a onClick="Gendering(this)" value="1" class="option">1  男性    </a> 
							<a onClick="Gendering(this)" value="2" class="option">2  女性 </a> 
							<a onClick="Gendering(this)" value="9" class="option">9  未说明的性别 </a> 
							<span class="fr"><input type="number" name="GenderCode" id="GenderCode" value="${data.GenderCode }"  class="w20"/> </span>
						</td>
						<td>出生日期</td>
						<td>
							<input type="date" name="BirthDay" id="BirthDay" value="${BirthDay }"  class="borderb"/>
						</td>
					</tr>
					<tr>
						<td>身份证号</td>
						<td colspan="3"><input type="text" name="CardID" id="tab_CardID" value="${data.CardID  }" disabled="disabled" class="borderb"/></td>
						<td>工作单位</td>
						<td colspan="3"><input type="text" name="WorkOrgName" id="WorkOrgName" value="${data.WorkOrgName }"  class="borderb"/></td>
					</tr>
					<tr>
						<td>本人电话</td>
						<td>
							<input type="text" name="PersonTel" id="PersonTel" value="${data.PersonTel }"  class="borderb"/>手机或固话
						</td>
						<td>联系人姓名</td>
						<td><input type="text" name="ContactPerson" id="ContactPerson" value="${data.ContactPerson }"  class="borderb"/></td>
						<td>联系人电话</td>
						<td>
							<input type="text" name="ContactTel" id="ContactTel" value="${data.ContactTel }"  class="borderb"/> 手机或固话
						</td>
					</tr>
					<tr>
						<td>常住类型</td>
						<td colspan="3">
							<a onClick="HukouInding(this)" value="1" class="option">1 户籍       </a>
							<a onClick="HukouInding(this)" value="2" class="option">2 非户籍    </a> 
							<span class="fr"><input type="number" name="HukouInd" id="HukouInd" value="${data.HukouInd }"  class="w20"/> </span>
						</td>
						<td>民族</td>
						<td>
					 
							  <select name="NationCode" id="NationCode">
								<option title="汉族" value="01">汉族</option>
								<option title="蒙古族" value="02">蒙古族</option>
								<option title="回族" value="03">回族</option>
								<option title="藏族" value="04">藏族</option>
								<option title="维吾尔族" value="05">维吾尔族</option>
								<option title="苗族" value="06">苗族</option>
								<option title="彝族" value="07">彝族</option>
								<option title="壮族" value="08">壮族</option>
								<option title="布依族" value="09">布依族</option>
								<option title="朝鲜族" value="10">朝鲜族</option>
								<option title="满族" value="11">满族</option>
								<option title="侗族" value="12">侗族</option>
								<option title="瑶族" value="13">瑶族</option>
								<option title="白族" value="14">白族</option>
								<option title="土家族" value="15">土家族</option>
								<option title="哈尼族" value="16">哈尼族</option>
								<option title="哈萨克族" value="17">哈萨克族</option>								
								<option title="傣族" value="18">傣族</option>
								<option title="黎族" value="19">黎族</option>
								<option title="傈僳族" value="20">傈僳族</option>
								<option title="佤族" value="21">佤族</option>
								<option title="畲族" value="22">畲族</option>
								<option title="高山族" value="23">高山族</option>
								<option title="拉祜族" value="24">拉祜族</option>
								<option title="水族" value="25">水族</option>
								<option title="东乡族" value="26">东乡族</option>
								<option title="纳西族" value="27">纳西族</option>
								<option title="景颇族" value="28">景颇族</option>
								<option title="柯尔克孜族" value="29">柯尔克孜族</option>
								<option title="土族" value="30">土族</option>
								<option title="达斡尔族" value="31">达斡尔族</option>
								<option title="仫佬族" value="32">仫佬族</option>
								<option title="羌族" value="33">羌族</option>
								<option title="布朗族" value="34">布朗族</option>
								<option title="撒拉族" value="35">撒拉族</option>
								<option title="毛南族" value="36">毛南族</option>
								<option title="仡佬族" value="37">仡佬族</option>
								<option title="锡伯族" value="38">锡伯族</option>
								<option title="阿昌族" value="39">阿昌族</option>
								<option title="普米族" value="40">普米族</option>
								<option title="塔吉克族" value="41">塔吉克族</option>
								<option title="怒族" value="42">怒族</option>
								<option title="乌孜别克族" value="43">乌孜别克族</option>
								<option title="俄罗斯族" value="44">俄罗斯族</option>
								<option title="鄂温克族" value="45">鄂温克族</option>
								<option title="德昂族" value="46">德昂族</option>
								<option title="保安族" value="47">保安族</option>
								<option title="裕固族" value="48">裕固族</option>
								<option title="京族" value="49">京族</option>
								<option title="塔塔尔族" value="50">塔塔尔族</option>
								<option title="独龙族" value="51">独龙族</option>
								<option title="鄂伦春族" value="52">鄂伦春族</option>
								<option title="赫哲族" value="53">赫哲族</option>
								<option title="门巴族" value="54">门巴族</option>
								<option title="珞巴族" value="55">珞巴族</option>
								<option title="基诺族" value="56">基诺族</option>
								<option title="外国血统" value="98">外国血统</option>
								<option title="其他 " value="99">其他 </option>
								 
							</select>  
						</td>
					</tr>
					<tr>
						<td>血型</td>
						<td colspan="5">	
						 <span>
						 	<a value="1" onClick="BloodTypeing(this)" class="option">1 A型  </a>
							<a value="2" onClick="BloodTypeing(this)" class="option">2 B型  </a>
							<a value="3" onClick="BloodTypeing(this)" class="option">3 O型  </a>
							<a value="4" onClick="BloodTypeing(this)" class="option">4 AB型  </a>
							<a value="5" onClick="BloodTypeing(this)" class="option">5 不详  </a>
						 </span>				 
							/RH阴性：	
							<span>
								<a value="1" onClick="RhBlooding(this)" class="option">1 否  </a>
								<a value="2" onClick="RhBlooding(this)" class="option">2 是  </a>
								<a value="3" onClick="RhBlooding(this)" class="option">3 不详  </a>
							</span>					 
							<span class="fr"> <input type="number" name="BloodType" id="BloodType" value="${data.BloodType}" class="w20"/>/<input type="number" name="RhBlood" id="RhBlood" value="${data.RhBlood }" class="w20"/></span>
						</td>
					</tr>
					<tr>
						<td>文化程度</td>
						<td colspan="5">
							<a value="1" onClick="EducationCodeing(this)" class="option">1 文盲或半文盲  </a>
							<a value="2" onClick="EducationCodeing(this)" class="option">2 小学  </a>
							<a value="3" onClick="EducationCodeing(this)" class="option">3 初中  </a>
							<a value="4" onClick="EducationCodeing(this)" class="option">4 高中/技校/中专  </a>
							<a value="5" onClick="EducationCodeing(this)" class="option">5 大学专科及以上  </a>
							<a value="6" onClick="EducationCodeing(this)" class="option">6 不详  </a>
							<span class="fr"> <input type="number" name="EducationCode" id="EducationCode" value="${data.EducationCode}" class="w20"/></span>
						</td>
					</tr>
					<tr>
						<td>职业</td>
						<td colspan="5">						 
							<a value="1" onClick="JobCodeing(this)" class="option">1 国家机关、党群组织、企业、事业单位负责人     </a>
							<a value="2" onClick="JobCodeing(this)" class="option">2 专业技术人员     </a>
							<a value="3" onClick="JobCodeing(this)" class="option">3 办事人员和有关人员     </a>
							<a value="4" onClick="JobCodeing(this)" class="option">4 商业、服务业人员     </a>
							<a value="5" onClick="JobCodeing(this)" class="option">5 农、林、牧、渔、水利业生产人员     </a>
							<a value="6" onClick="JobCodeing(this)" class="option">6 生产、运输设备操作人员及有关人员      </a>
							<a value="7" onClick="JobCodeing(this)" class="option">7 军人     </a>
							<a value="8" onClick="JobCodeing(this)" class="option">8 不便分类的其他从业人员  </a>
							<select> <!-- 这里还有待处理 -->
								<option>学龄前</option>
								<option>学生</option>
								<option>农民</option>
							</select>
							<span class="fr"> <input type="number" name="JobCode" id="JobCode" value="${data.JobCode}" class="w20"/></span>
						</td>
					</tr>
					<tr>
						<td>婚姻状况</td>
						<td colspan="5">					
							<a value="1" onClick="MarryStatusing(this)" class="option">1 未婚     </a>
							<a value="2" onClick="MarryStatusing(this)" class="option">2 已婚     </a>
							<a value="3" onClick="MarryStatusing(this)" class="option">3 丧偶     </a>
							<a value="4" onClick="MarryStatusing(this)" class="option">4 离婚     </a>
							<a value="5" onClick="MarryStatusing(this)" class="option">5 未说明的婚姻情况     </a>
							<span class="fr"> <input type="number" name="MarryStatus" id="MarryStatus" value="${data.MarryStatus}" class="w20"/></span>
						</td>
					</tr>
					<tr>
						<td>医疗费用支付方式</td>
						<td colspan="5">
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="1" value="1" class="optionMore">1 城镇职工基本医疗保险   </a>      
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="2" value="2" class="optionMore">2 城镇居民基本医疗保险   </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="3" value="4" class="optionMore">3 新型农村合作医疗   </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="4" value="8" class="optionMore">4 贫困救助    </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="5" value="16" class="optionMore">5 商业医疗保险   </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="6" value="32" class="optionMore">6 全公费   </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="7" value="64" class="optionMore">7 全自费   </a>
							<a onClick="PaymentWay(this)" href="javascript:void(0)" id="8" value="128" class="optionMore">8 其他  </a>
							<input type="text" name="OtherPaymentWaystring" id="OtherPaymentWaystring" value="${data.OtherPaymentWaystring }" class="borderb"/></span>
							<span class="fr">						 
								<input type="number" name="PaymentWay1" id="PaymentWay1" value="" class="w20"/> 
								<input type="number" name="PaymentWay2" id="PaymentWay2" value="" class="w20"/>
								<input type="number" name="PaymentWay3" id="PaymentWay3" value="" class="w20"/> 
								<input type="number" name="PaymentWay4" id="PaymentWay4" value="" class="w20"/> 
								<input type="number" name="PaymentWay5" id="PaymentWay5" value="" class="w20"/> 
								<input type="number" name="PaymentWay6" id="PaymentWay6" value="" class="w20"/> 
								<input type="number" name="PaymentWay7" id="PaymentWay7" value="" class="w20"/> 
								<input type="number" name="PaymentWay8" id="PaymentWay8" value="" class="w20"/>   
							</span>
						</td>
					</tr>
					<tr>
						<td>药物过敏</td>
						<td colspan="5">
							<a value="1" id="1" onClick="DrugAllergyHistorying(this)" class="optionMore">1 无    </a>
							<a value="2" id="2" onClick="DrugAllergyHistorying(this)" class="optionMore">2 青霉素     </a>
							<a value="4" id="3" onClick="DrugAllergyHistorying(this)" class="optionMore">3 磺胺     </a>
							<a value="8" id="4" onClick="DrugAllergyHistorying(this)" class="optionMore">4 链霉素     </a>
							<a value="16" id="5" onClick="DrugAllergyHistorying(this)"  class="optionMore">5 其他    </a>
							<input type="text" name="OtherDrugAllergyHistory" id="OtherDrugAllergyHistory" value="${data.OtherDrugAllergyHistory }" class="borderb"/>
							<span class="fr">						 
								<input type="number" name="DrugAllergyHistory1" id="DrugAllergyHistory1" value="" class="w20"/> 
								<input type="number" name="DrugAllergyHistory2" id="DrugAllergyHistory2" value="" class="w20"/> 
								<input type="number" name="DrugAllergyHistory3" id="DrugAllergyHistory3" value="" class="w20"/> 
								<input type="number" name="DrugAllergyHistory4" id="DrugAllergyHistory4" value="" class="w20"/> 
								<input type="number" name="DrugAllergyHistory5" id="DrugAllergyHistory5" value="" class="w20"/> 
							</span>
						</td>
					</tr>
					<tr>
						<td>暴露史</td>
						<td colspan="5">						 
						    <a value="1" id="1" onClick="ExposureHistorying(this)"  class="optionMore">1 无     </a>
							<a value="2" id="2" onClick="ExposureHistorying(this)" class="optionMore">2 化学品     </a>
							<a value="4" id="3" onClick="ExposureHistorying(this)" class="optionMore">3 毒物     </a>
							<a value="8" id="4" onClick="ExposureHistorying(this)" class="optionMore">4 射线    </a>
							<span class="fr">						 
								<input type="number" name="ExposureHistory1" id="ExposureHistory1" value="" class="w20"/> 
								<input type="number" name="ExposureHistory2" id="ExposureHistory2" value="" class="w20"/> 
								<input type="number" name="ExposureHistory3" id="ExposureHistory3" value="" class="w20"/> 
								<input type="number" name="ExposureHistory4" id="ExposureHistory4" value="" class="w20"/> 
  
							</span>
						</td>
					</tr>
					<tr>
						<td rowspan="4" class="jiwangshi">既往史</td>
						<td rowspan="">疾病</td>
						<td colspan="4" class="diseases_td">
							<span class="Noption chosed">1 无</span>
							<span class="optionMore ">2 高血压</span>
							<span class="optionMore">3 糖尿病</span>
							<span class="optionMore">4 冠心病</span>
							<span class="optionMore">5 慢性阻塞性肺疾病</span>
							<span class="optionMore">6 恶性肿瘤</span>
							<span class="optionMore">7 脑卒中</span>
							<span class="optionMore">8 重性精神疾病</span>
							<span class="optionMore">9 结核病</span>
							<span class="optionMore">10 肝炎</span>
							<span class="optionMore">11 其它法定传染病</span>
							<span class="optionMore">12 职业病</span>
							<span class="optionMore">13 其它 </span>
						
							
							<div class="diseaseBox">
								<span class="box1">
									<span class="codeCon1 chosed">2</span>
									<span>确诊时间 <input   name="" id="highBloodPressure" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">3</span>
									<span>确诊时间 <input   name="" id="diabetes" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">4</span>
									<span>确诊时间 <input   name="" id="coronaryHeartdDisease" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">5</span>
									<span>确诊时间 <input   name="" id="chronicObstructivePulmonaryDisease" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">6</span>
									<span>确诊时间 <input   name="" id="cancer" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">7</span>
									<span>确诊时间 <input   name="" id="stroke" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">8</span>
									<span>确诊时间 <input   name="" id="severeMentalIllness" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">9</span>
									<span>确诊时间 <input   name="" id="tuberculosis" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">10</span>
									<span>确诊时间 <input   name="" id="hepatitis" value="" class="borderb"/></span>
								</span>
								<span class="box1">
									<span class="codeCon1 chosed">11</span>
									<span>确诊时间 <input   name="" id="notifiableDisease" value="" class="borderb"/></span>
								</span>
								
								<span class="box1">
									<span class="codeCon1 chosed">12</span>
									<span>确诊时间 <input   name="" id="industrialDisease" value="" class="borderb"/></span>
								</span>
								<div class="box1">
									<span>其它疾病13.1</span>
									<input type="text" name="" id="" value="" class="borderb"/>
									<span>确诊时间</span>
									<input type="date" name="" id="" value="" class="borderb"/>
									<a href="javascript:void(0)" class="add_other_disase">+</a>
								</div>
								
								
							</div>
						</td>
					</tr>
					
					<tr>
						<td>手术</td>
						<td>
							<span class="option no_illness chosed" >1 无</span>
							<span class="option has_illness" >2 有</span>
						</td>
						<td colspan="3" id="operation" class="add_illness_td">
							<div class="add_illness" hidden="">
								名称： <input type="text" name="operationName" id="" value="" class="borderb"/> 时间： <input type="date" name="operationtime" id="" value="" class="borderb"/>  
								<a href="javascript:void(0)" class="add">+</a>
							</div>
						</td>
					</tr>
					<tr>
						<td>外伤</td>
						<td>
							<span class="option no_illness chosed" >1 无</span>
							<span class="option has_illness">2 有</span>
						</td>
						<td colspan="3" id="trauma" class="add_illness_td">
							<div class="add_illness" hidden="">
								名称： <input type="text" name="traumaName" id="" value="" class="borderb"/> 时间： <input type="date" name="traumatime" id="" value="" class="borderb"/>  
								<a href="javascript:void(0)" class="add">+</a>
							</div>
						</td>
					</tr>
					<tr>
						<td>输血</td>
						<td>
							<span class="option no_illness chosed">1 无</span>
							<span class="option has_illness">2 有</span>
						</td>
						<td colspan="3" id="transfusion" class="add_illness_td">
							<div class="add_illness" hidden="">
								名称： <input type="text" name="transfusionName" id="" value="" class="borderb"/> 时间： <input type="date" name="transfusiontime" id="" value="" class="borderb"/>  
								<a href="javascript:void(0)" class="add">+</a>
							</div>
						</td>
					</tr>
					<tr>
						<td rowspan="2">家族史</td>
						<td colspan="5" class="family_disase_td">
							<span class="option1 chosed " style="cursor: pointer;">1 父亲</span>
							<input type="number" name="f1" id="f1" value=""  class="w20"/>
							<input type="number" name="f2" id="f2" value="" class="w20"/> 
							<input type="number" name="f3" id="f3" value="" class="w20"/> 
							<input type="number" name="f4" id="f4" value="" class="w20"/>
							<input type="number" name="f5" id="f5" value="" class="w20"/>
							<input type="number" name="f6" id="f6" value="" class="w20"/> 
							<input type="number" name="f7" id="f7" value="" class="w20"/> 
							<input type="number" name="f8" id="f8" value="" class="w20"/> 
							<input type="number" name="f9" id="f9" value="" class="w20"/> 
							<input type="number" name="f10" id="f10" value="" class="w20"/> 
							<input type="number" name="f11" id="f11" value="" class="w20"/> 
							<input type="number" name="f12" id="f12" value="" class="w20"/> <input type="text" name="" id="f_1" value=""  class="borderb"/>
							</br>
							<span class="option2" style="cursor: pointer;">2 母亲</span>
							<input type="number" name="m1" id="m1" value=""  class="w20"/>
							<input type="number" name="m2" id="m2" value="" class="w20"/> 
							<input type="number" name="m3" id="m3" value="" class="w20"/>
							<input type="number" name="m4" id="m4" value="" class="w20"/>
							<input type="number" name="m5" id="m5" value="" class="w20"/> 
							<input type="number" name="m6" id="m6" value="" class="w20"/> 
							<input type="number" name="m7" id="m7" value="" class="w20"/> 
							<input type="number" name="m8" id="m8" value="" class="w20"/> 
							<input type="number" name="f9" id="m9" value="" class="w20"/> 
							<input type="number" name="m10" id="m10" value="" class="w20"/> 
							<input type="number" name="m11" id="m11" value="" class="w20"/> 
							<input type="number" name="m12" id="m12" value="" class="w20"/> <input type="text" name="" id="m_1" value=""  class="borderb"/>
							</br>
							<span class="option3" style="cursor: pointer;">3 兄弟姐妹</span>
							<input type="number" name="b1" id="b1" value=""  class="w20"/>
							<input type="number" name="b2" id="b2" value="" class="w20"/> 
							<input type="number" name="b3" id="b3" value="" class="w20"/> 
							<input type="number" name="b4" id="b4" value="" class="w20"/> 
							<input type="number" name="b5" id="b5" value="" class="w20"/> 
							<input type="number" name="b6" id="b6" value="" class="w20"/>
							<input type="number" name="b7" id="b7" value="" class="w20"/> 
							<input type="number" name="b8" id="b8" value="" class="w20"/>
							<input type="number" name="b9" id="b9" value="" class="w20"/> 
							<input type="number" name="b10" id="b10" value="" class="w20"/> 
							<input type="number" name="b11" id="b11" value="" class="w20"/> 
							<input type="number" name="b12" id="b12" value="" class="w20"/> <input type="text" name="" id="b_1" value=""  class="borderb"/>
							</br>
							<span class="option4" style="cursor: pointer;">4 子女</span>
							<input type="number" name="s1" id="s1" value=""  class="w20"/>
							<input type="number" name="s2" id="s2" value="" class="w20"/> 
							<input type="number" name="s3" id="s3" value="" class="w20"/> 
							<input type="number" name="s4" id="s4" value="" class="w20"/> 
							<input type="number" name="s5" id="s5" value="" class="w20"/>
							<input type="number" name="s6" id="s6" value="" class="w20"/> 
							<input type="number" name="s7" id="s7" value="" class="w20"/> 
							<input type="number" name="s8" id="s8" value="" class="w20"/> 
							<input type="number" name="s9" id="s9" value="" class="w20"/> 
							<input type="number" name="s10" id="s10" value="" class="w20"/> 
							<input type="number" name="s11" id="s11" value="" class="w20"/> 
							<input type="number" name="s12" id="s12" value="" class="w20"/> <input type="text" name="" id="s_1" value=""  class="borderb"/>
						</td>
					</tr>
					<tr>
						<td colspan="5" class="family_disase_conent">
							<a onClick="fillness(this)" href="javascript:void(0)" id="father1" value="1" class="Noption">1 无</a>      
							<a onClick="fillness(this)" href="javascript:void(0)" id="father2" value="2" class="optionMore" >2 高血压 </a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father3" value="4" class="optionMore">3 糖尿病</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father4" value="8" class="optionMore">4 冠心病 </a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father5" value="16" class="optionMore">5 慢性阻塞性肺疾病 </a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father6" value="32" class="optionMore">6 恶性肿瘤 </a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father7" value="64" class="optionMore">7 脑卒中</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father8" value="128" class="optionMore">8四肢发麻</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father9" value="256" class="optionMore">9 结核病</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father10" value="512" class="optionMore">10 肝炎</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father11" value="1024" class="optionMore">11 先天畸形</a>
							<a onClick="fillness(this)" href="javascript:void(0)" id="father12" value="2048" class="optionMore">12 其它</a> 
						</td>
						<td colspan="5"  hidden  class="family_disase_conent">		 
							<a onClick="millness(this)" href="javascript:void(0)" id="mather1" value="1" class="Noption">1 无</a>      
							<a onClick="millness(this)" href="javascript:void(0)" id="mather2" value="2" class="optionMore">2 高血压 </a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather3" value="4" class="optionMore">3 糖尿病</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather4" value="8" class="optionMore">4 冠心病 </a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather5" value="16" class="optionMore">5 慢性阻塞性肺疾病 </a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather6" value="32" class="optionMore">6 恶性肿瘤 </a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather7" value="64" class="optionMore">7 脑卒中</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather8" value="128" class="optionMore">8四肢发麻</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather9" value="256" class="optionMore">9 结核病</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather10" value="512" class="optionMore">10 肝炎</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather11" value="1024" class="optionMore">11 先天畸形</a>
							<a onClick="millness(this)" href="javascript:void(0)" id="mather12" value="2048" class="optionMore">12 其它</a> 
						</td>
						<td colspan="5" hidden   class="family_disase_conent">
						    <a onClick="billness(this)" href="javascript:void(0)" id="brather1" value="1" class="Noption">1 无</a>      
							<a onClick="billness(this)" href="javascript:void(0)" id="brather2" value="2" class="optionMore">2 高血压 </a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather3" value="4" class="optionMore">3 糖尿病</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather4" value="8" class="optionMore">4 冠心病 </a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather5" value="16" class="optionMore">5 慢性阻塞性肺疾病 </a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather6" value="32" class="optionMore">6 恶性肿瘤 </a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather7" value="64" class="optionMore">7 脑卒中</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather8" value="128" class="optionMore">8四肢发麻</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather9" value="256" class="optionMore">9 结核病</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather10" value="512" class="optionMore">10 肝炎</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather11" value="1024" class="optionMore">11 先天畸形</a>
							<a onClick="billness(this)" href="javascript:void(0)" id="brather12" value="2048" class="optionMore">12 其它</a> 
						</td>
						<td colspan="5" hidden  class="family_disase_conent"> 
							<a onClick="sillness(this)" href="javascript:void(0)" id="son1" value="1" class="Noption">1 无</a>      
							<a onClick="sillness(this)" href="javascript:void(0)" id="son2" value="2" class="optionMore">2 高血压 </a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son3" value="4" class="optionMore">3 糖尿病</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son4" value="8" class="optionMore">4 冠心病 </a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son5" value="16" class="optionMore">5 慢性阻塞性肺疾病 </a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son6" value="32" class="optionMore">6 恶性肿瘤 </a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son7" value="64" class="optionMore">7 脑卒中</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son8" value="128" class="optionMore">8四肢发麻</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son9" value="256" class="optionMore">9 结核病</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son10" value="512" class="optionMore">10 肝炎</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son11" value="1024" class="optionMore">11 先天畸形</a>
							<a onClick="sillness(this)" href="javascript:void(0)" id="son12" value="2048" class="optionMore">12 其它</a> 
						</td>
					</tr>
					<tr>
						<td>遗传病史</td>
						<td class="ycb">
							<span class="Noption chosed">1 无</span>
							<span class="option">2 有</span>
						</td>
						<td colspan="4" id="inheritance" class="ycb_content_td">
							<div class="box2" hidden="">
								名称： <input type="text" name="inheritanceName" id="" value="" class="borderb"/> 时间： <input type="date" name="inheritancetime" id="" value="" class="borderb"/>  
								<a href="javascript:void(0)" class="add_box2">+</a>
							</div>
						</td>
					</tr>
					<tr>
						<td>残疾情况</td>
						<td colspan="5">						 
							<a onClick="Disabilitying(this)"  id="1" value="1" class="Noption">1 无残疾   </a>      
							<a onClick="Disabilitying(this)"  id="2" value="2" class="optionMore">2 视力残疾   </a>
							<a onClick="Disabilitying(this)"  id="3" value="4" class="optionMore">3 听力残疾   </a>
							<a onClick="Disabilitying(this)"  id="4" value="8" class="optionMore">4 言语残疾   </a>
							<a onClick="Disabilitying(this)"  id="5" value="16" class="optionMore">5 肢体残疾   </a>
							<a onClick="Disabilitying(this)"  id="6" value="32" class="optionMore">6 智力残疾   </a>
							<a onClick="Disabilitying(this)"  id="7" value="64" class="optionMore">7 精神残疾   </a>
							<a onClick="Disabilitying(this)"  id="8" value="128" class="optionMore">8 其它残疾   </a>
							<input type="text" name="OtherDisability" id="OtherDisability" value="${data.OtherDisability }" />
							<span>
								残疾证：<input type="text" name="DisabilityNumber" id="DisabilityNumber" value="${data.DisabilityNumber }"  class="borderb"/>
							</span>
							<span class="fr">
								<input type="number" name="Disability1" id="Disability1" value="" class="w20"/> 
								<input type="number" name="Disability2" id="Disability2" value="" class="w20"/> 
								<input type="number" name="Disability3" id="Disability3" value="" class="w20"/> 
								<input type="number" name="Disability4" id="Disability4" value="" class="w20"/> 
								<input type="number" name="Disability5" id="Disability5" value="" class="w20"/> 
								<input type="number" name="Disability6" id="Disability6" value="" class="w20"/> 
								<input type="number" name="Disability7" id="Disability7" value="" class="w20"/> 
								<input type="number" name="Disability8" id="Disability8" value="" class="w20"/> 
 
								 
							</span>
						</td>
					</tr>
					<tr>
						<td rowspan="5">生活环境</td>
						<td>厨房排风设施</td>
						<td colspan="4">
							<a onClick="KitchenExhausting(this)"  id="1" value="1" class="option">1 无   </a>      
							<a onClick="KitchenExhausting(this)"  id="2" value="2" class="option">2 油烟机   </a>
							<a onClick="KitchenExhausting(this)"  id="3" value="4" class="option">3 换气扇   </a>
							<a onClick="KitchenExhausting(this)"  id="4" value="8" class="option">4 烟囱   </a>
							<span class="fr"><input type="number" name="KitchenExhaust" id="KitchenExhaust"    class="w20"/> </span>
						</td>
					</tr>
					<tr>
						<td>燃料类型</td>
						<td colspan="4">
							<a onClick="FuelTypeing(this)"  id="1" value="1" class="option">1 液化气    </a>      
							<a onClick="FuelTypeing(this)"  id="2" value="2" class="option">2 煤    </a>
							<a onClick="FuelTypeing(this)"  id="3" value="4" class="option">3 天然气    </a>
							<a onClick="FuelTypeing(this)"  id="4" value="8" class="option">4 沼气    </a>
							<a onClick="FuelTypeing(this)"  id="5" value="16" class="option">5 柴火    </a>
							<a onClick="FuelTypeing(this)"  id="6" value="32" class="option">6 其他  </a>
							<span class="fr"><input type="number" name="FuelType" id="FuelType"    class="w20"/> </span>
						</td>
					</tr>
					<tr>
						<td>饮水</td>
						<td colspan="4">
						    <a onClick="Drinkingwatering(this)"  id="1" value="1" class="option">1 自来水    </a>      
							<a onClick="Drinkingwatering(this)"  id="2" value="2" class="option">2 经净化过滤的水    </a>
							<a onClick="Drinkingwatering(this)"  id="3" value="4" class="option">3 井水    </a>
							<a onClick="Drinkingwatering(this)"  id="4" value="8" class="option">4 河湖水    </a>
							<a onClick="Drinkingwatering(this)"  id="5" value="16" class="option">5 塘水    </a>
							<a onClick="Drinkingwatering(this)"  id="6" value="32" class="option">6 其他  </a>
							<span class="fr"><input type="number" name="Drinkingwater" id="Drinkingwater"    class="w20"/> </span>
						</td>
					</tr>
					<tr>
						<td>厕所</td>
						<td colspan="4"> 
							<a onClick="Toileting(this)"  id="1" value="1" class="option">1 卫生厕所    </a>      
							<a onClick="Toileting(this)"  id="2" value="2" class="option">2 一格或二格粪池式    </a>
							<a onClick="Toileting(this)"  id="3" value="4" class="option">3 马桶    </a>
							<a onClick="Toileting(this)"  id="4" value="8" class="option">4 露天粪坑    </a>
							<a onClick="Toileting(this)"  id="5" value="16" class="option">5 简易棚厕    </a>						 
							<span class="fr"><input type="number" name="Toilet" id="Toilet"    class="w20"/> </span>
						</td>
					</tr>
					<tr>
						<td>禽兽栏</td>
						<td colspan="4">							
							<a onClick="LivestockColumning(this)"  id="1" value="1" class="option">1 单设    </a>      
							<a onClick="LivestockColumning(this)"  id="2" value="2" class="option">2 室内    </a>
							<a onClick="LivestockColumning(this)"  id="3" value="4" class="option">3 室外    </a>
							 						 
							<span class="fr"><input type="number" name="LivestockColumn" id="LivestockColumn"  class="w20"/> </span>
						</td>
					</tr>
				</table>
			</div>
			<!------------------------------------个人扩展信息---------------------------->
			<div class="content_div" hidden="">
				<h1 class="tc">个人扩展信息</h1>
				<div class="pMsg_box clearfix">
					<div class="pic">
						<div class="thumbnails">
							<img src="" alt="" />
						</div>

						<p>尺寸(cm):113*137</p>
						<p class="tc mt10">
							<a href="javascript:void(0)" class="sibtn">浏览</a>
						</p>
					</div>
					<div class="msg_table">
						<table cellspacing="5">
							<tr>
								<td>经济类型：</td>
								<td>
									<select name="IncomeType" id="IncomeType" >
										<option value="0">无</option>
										<option value="1">低保</option>
										<option value="2">五保</option>
										<option value="3">优抚</option>
									</select>
								</td>
								<td>参加工作时间：</td>
								<td>
									<input type="date" name="WorkDate" id="WorkDate" value="${WorkDate }" class="borderb"/>
								</td>
							</tr>
							<tr>
								<td>备注信息：</td>
								<td colspan="3"> 
									<textarea name="Remark" id="Remark" rows="" cols=""></textarea>
								</td>
							</tr>
							<tr>
								<td>状态：</td>
								<td>
									<select name="HrStatus" id="HrStatus">
										<option value="0">正常</option>
										<option value="1">迁出</option>
										<option value="2">是我</option>
										<option value="99">已删除</option>
										<option value="3">其他</option>
									</select>
								</td>
								<td>状态变更日期：</td>
								<td>
									<input type="date" name="HrStatusDate" id="HrStatusDate" value="${HrStatusDate }" class="borderb"/>
								</td>
							</tr>
							<tr>
								<td>状态变更说明：</td>
								<td colspan="3">
									<textarea name="HrStatusRemark" id="HrStatusRemark" rows="" cols=""> </textarea>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			<div class="content_div" hidden="">
				
				体检的
				
			</div>
<!------------------------------------档案信息卡---------------------------->
			<div class="content_div" hidden="">
				<h1 class="tc">居民健康档案信息卡</h1>
				<p class="tc mt20">(正面)</p>
				<div class="msg_card_table">
					<table border="1">
						<tr>
							<td>姓名</td>
							<td>${data.Name }</td>
							<td>性别</td>
							<td>${Gender }</td>
							<td>出生日期</td>
							<td>${BirthDay }</td>
						</tr>
						<tr>
							<td colspan="2">健康档案编号</td>
							<td colspan="4">
								<div name="person_code_1"></div>
							</td>
						</tr>
						<tr>
							<td>血型</td>
							<td colspan="2">
								<label >
									<input type="radio" name="BloodTypeing" id="1"   />A
								</label>
								<label >
									<input type="radio" name="BloodTypeing" id="2"     />B
								</label>
								<label >
									<input type="radio" name="BloodTypeing" id="3"     />O
								</label>
								<label >
									<input type="radio" name="BloodTypeing" id="4"  />AB
								</label>
								<label >
									<input type="radio" name="BloodTypeing" id="5"  />不详
								</label>
							</td>
							<td>RH阴性</td>
							<td colspan="2">
								<label >
									<input type="radio" name="RhBlooding" id="2"   />是
								</label>
								<label >
									<input type="radio" name="RhBlooding" id="1"  />否
								</label>
								<label >
									<input type="radio" name="RhBlooding" id="3"  />不详
								</label>							
							</td>
						</tr>
						<tr>
							<td colspan="6">慢性病患者：${cmData }</td>
						</tr>
						<tr>
							<td colspan="6">
								过敏史：${DrugAllergyHistorystr }
							</td>
						</tr>
					</table>
					
					<p class="tc mt20">(反面)</p>
					<table border="1" >
						<tr>
							<td>户籍地址</td>
							<td>${data.ResidenceAddress }</td>
							<td>家庭电话</td>
							<td>未知</td>
						</tr>
						<tr>
							<td>联系人姓名</td>
							<td>${data.ContactPerson }</td>
							<td>联系人电话</td>
							<td>${data.ContactTel }</td>
						</tr>
						<tr>
							<td>建档机构名称</td>
							<td>${data.BuildOrgName }</td>
							<td>责任医生</td>
							<td>${data.ResponsibilityDoctor }</td>
						</tr>
						<tr>
							<td colspan="4">
								其他说明： 无
							</td>
						</tr>
					</table>
				
				</div>
			</div>
			
			
		</div>
	</body>
	<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>		
	<script type="text/javascript">
	//性别处理
	function Gendering(tr){
		var value=tr.getAttribute('value');
		$("#GenderCode").val(value)
	}
	//常住类型
	function HukouInding(tr){
		var value=tr.getAttribute('value');
		$("#HukouInd").val(value)
	}
 	//血型处理
	function BloodTypeing(tr){
		var value=tr.getAttribute('value');
		$("#BloodType").val(value)
	}
	//RH阴性
	function RhBlooding(tr){
		var value=tr.getAttribute('value');
		$("#RhBlood").val(value)
	}
	//文化程度
	function EducationCodeing(tr){
		var value=tr.getAttribute('value');
		$("#EducationCode").val(value)
	}
	//职业
	function JobCodeing(tr){
		var value=tr.getAttribute('value');
		$("#JobCode").val(value)
	} 
	//婚姻状况
	function MarryStatusing(tr){
		var value=tr.getAttribute('value');
		$("#MarryStatus").val(value)
	} 
	
	//医疗费用支付方式
	function PaymentWay(tr){		 
		var value=tr.getAttribute('value');
		//先判断先前是否选中
		var id =tr.getAttribute('id');
		 
		if(!($("#"+id).data('abc'))){
			if(value==64){
				$("#"+id).data('abc','7')			 
				$("#PaymentWay"+7).val(7)
				$("#PaymentWay"+1).val("")
				$("#PaymentWay"+2).val("")
				$("#PaymentWay"+3).val("")
				$("#PaymentWay"+4).val("")
				$("#PaymentWay"+5).val("")
				$("#PaymentWay"+6).val("")				
				$("#PaymentWay"+8).val("")	 
			}else if(value==1){
				$("#"+id).data('abc','1')
				$("#PaymentWay"+1).val(1)
				$("#PaymentWay"+7).val("")
			}else if(value==2){
				$("#"+id).data('abc','2')
				$("#PaymentWay"+2).val(2)
				$("#PaymentWay"+7).val("")
			}else if(value==4){
				$("#"+id).data('abc','3')
				$("#PaymentWay"+3).val(3)
				$("#PaymentWay"+7).val("")
			}else if(value==8){
				$("#"+id).data('abc','4')
				$("#PaymentWay"+4).val(4)
				$("#PaymentWay"+7).val("")
			}else if(value==16){
				$("#"+id).data('abc','5')
				$("#PaymentWay"+5).val(5)
				$("#PaymentWay"+7).val("")
			}else if(value==32){
				$("#"+id).data('abc','6')
				$("#PaymentWay"+6).val(6)
				$("#PaymentWay"+7).val("")
			}else if(value==128){
				$("#"+id).data('abc','8')
				$("#PaymentWay"+8).val(8)
				$("#PaymentWay"+7).val("")
			} 
		}else{
			if(value==1){
				$("#"+id).data('abc','')			 
				$("#PaymentWay"+1).val("")
			}else if(value==2){
				$("#"+id).data('abc','')
				$("#PaymentWay"+2).val("")
			}else if(value==4){
				$("#"+id).data('abc','')
				$("#PaymentWay"+3).val("")
			}else if(value==8){
				$("#"+id).data('abc','')
				$("#PaymentWay"+4).val("")
			}else if(value==16){
				$("#"+id).data('abc','')
				$("#PaymentWay"+5).val("")
			}else if(value==32){
				$("#"+id).data('abc','')
				$("#PaymentWay"+6).val("")
			}else if(value==64){
				$("#"+id).data('abc','')
				$("#PaymentWay"+7).val("")
			}else if(value==128){
				$("#"+id).data('abc','')
				$("#PaymentWay"+8).val("")
			} 
		} 
	}
	
	//医疗费用支付方式回显
	var PaymentWaystr ='${PaymentWaystring}';
	var PaymentWayList = new Array();
	PaymentWayList=PaymentWaystr.split(",");
	for(var i=0;i<PaymentWayList.length;i++){
		if(1==PaymentWayList[i]){
			$("#PaymentWay1").val(1)
		}else if(2==PaymentWayList[i]){
			$("#PaymentWay2").val(2)
		}else if(4==PaymentWayList[i]){
			$("#PaymentWay3").val(3)
		}else if(8==PaymentWayList[i]){
			$("#PaymentWay4").val(4)
		}else if(16==PaymentWayList[i]){
			$("#PaymentWay5").val(5)
		}else if(32==PaymentWayList[i]){
			$("#PaymentWay6").val(6)
		}else if(64==PaymentWayList[i]){
			$("#PaymentWay7").val(7)
		}else if(128==PaymentWayList[i]){
			$("#PaymentWay8").val(8)
		} 
	}
	
	//药物过敏
	function DrugAllergyHistorying(tr){		 
		var value=tr.getAttribute('value');
		//先判断先前是否选中
		var id =tr.getAttribute('id');
		 
		if(!($("#"+id).data('abc'))){
			if(value==1){		 
				$("#"+id).data('abc','1')		  
				$("#DrugAllergyHistory1").val(1)
				$("#DrugAllergyHistory2").val("")
				$("#DrugAllergyHistory3").val("")
				$("#DrugAllergyHistory4").val("")
				$("#DrugAllergyHistory5").val("") 
			}else if(value==2){
				$("#"+id).data('abc','2')
				$("#DrugAllergyHistory2").val(2)
				$("#DrugAllergyHistory1").val("")
			}else if(value==4){
				$("#"+id).data('abc','3')
				$("#DrugAllergyHistory3").val(3)
				$("#DrugAllergyHistory1").val("")
			}else if(value==8){
				$("#"+id).data('abc','4')
				$("#DrugAllergyHistory4").val(4)
			    $("#DrugAllergyHistory1").val("")
			}else if(value==16){
				$("#"+id).data('abc','5')
				$("#DrugAllergyHistory5").val(5)
				$("#DrugAllergyHistory1").val("")
			} 
		}else{
			if(value==1){
				$("#"+id).data('abc','')			 
				$("#DrugAllergyHistory1").val("")
			}else if(value==2){
				$("#"+id).data('abc','')
				$("#DrugAllergyHistory2").val("")
			}else if(value==4){
				$("#"+id).data('abc','')
				$("#DrugAllergyHistory3").val("")
			}else if(value==8){
				$("#"+id).data('abc','')
				$("#DrugAllergyHistory4").val("")
			}else if(value==16){
				$("#"+id).data('abc','')
				$("#DrugAllergyHistory5").val("")
			} 
		} 
	}
	
	//药物过敏回显
	var DrugAllergyHistorystr ='${DrugAllergyHistory}';
	var DrugAllergyHistoryList = new Array();
	DrugAllergyHistoryList=DrugAllergyHistorystr.split(",");
	for(var i=0;i<DrugAllergyHistoryList.length;i++){
		if(1==DrugAllergyHistoryList[i]){
			$("#DrugAllergyHistory1").val(1)
		}else if(2==PaymentWayList[i]){
			$("#DrugAllergyHistory2").val(2)
		}else if(4==PaymentWayList[i]){
			$("#DrugAllergyHistory3").val(3)
		}else if(8==PaymentWayList[i]){
			$("#DrugAllergyHistory4").val(4)
		}else if(16==PaymentWayList[i]){
			$("#DrugAllergyHistory5").val(5)
		} 
	}
	//暴露史
	function ExposureHistorying(tr){		 
		var value=tr.getAttribute('value');
		//先判断先前是否选中
		var id =tr.getAttribute('id');
		 
		if(!($("#"+id).data('abc'))){
			if(value==1){		 
				$("#"+id).data('abc','1')		  
				$("#ExposureHistory1").val(1)
				$("#ExposureHistory2").val("")
				$("#ExposureHistory3").val("")
				$("#ExposureHistory4").val("")
			 
			}else if(value==2){
				$("#"+id).data('abc','2')
				$("#ExposureHistory2").val(2)
				$("#ExposureHistory1").val("")
			}else if(value==4){
				$("#"+id).data('abc','3')
				$("#ExposureHistory3").val(3)
				$("#ExposureHistory1").val("")
			}else if(value==8){
				$("#"+id).data('abc','4')
				$("#ExposureHistory4").val(4)
			    $("#ExposureHistory1").val("")
			} 
		}else{
			if(value==1){
				$("#"+id).data('abc','')			 
				$("#ExposureHistory1").val("")
			}else if(value==2){
				$("#"+id).data('abc','')
				$("#ExposureHistory2").val("")
			}else if(value==4){
				$("#"+id).data('abc','')
				$("#ExposureHistory3").val("")
			}else if(value==8){
				$("#"+id).data('abc','')
				$("#ExposureHistory4").val("")
			} 
		} 
	}
	
	//暴露史回显
	var ExposureHistorystr ='${DrugAllergyHistory}';
	var ExposureHistoryList = new Array();
	ExposureHistoryList=ExposureHistorystr.split(",");
	for(var i=0;i<ExposureHistoryList.length;i++){
		if(1==ExposureHistoryList[i]){
			$("#ExposureHistory1").val(1)
		}else if(2==ExposureHistoryList[i]){
			$("#ExposureHistory2").val(2)
		}else if(4==ExposureHistoryList[i]){
			$("#ExposureHistory3").val(3)
		}else if(8==ExposureHistoryList[i]){
			$("#ExposureHistory4").val(4)
		} 
	}
	//疾病回显
	var CmDataList ='${CmDataList}';
	var CdList=JSON.parse(CmDataList); 
	if(CdList!=null&&CdList!=""){
		for(var i=0;i<CdList.length;i++){
			var cid=CdList[i].DiseaseKindID;
			var time =CdList[i].DiagnosisDate;
			var times = time.split("T");
			cid=cid.replace(/-/g,"").toUpperCase();
			if("52A0328740914BCE86ED10A4D2521816"==cid){
				//高血压
				$("#highBloodPressure").val(times[0])
			}else if("7AA0AFF1DDA040F18510513DE3753547"==cid){
				//糖尿病
				$("#diabetes").val(times[0])
			}else if("708DE71884C645D0AEF802E06C6B4B12"==cid){
				//冠心病
				$("#coronaryHeartdDisease").val(times[0])
			}else if("C2B2CB0803EC46A684A00AE350A34251"==cid){
				//慢性阻塞性肺疾病
				$("#chronicObstructivePulmonaryDisease").val(times[0])
			}else if("E63F8CF300D44FECBD6FF96B9FCAF39C"==cid){
				//恶性肿瘤
				$("#cancer").val(times[0])
			}else if("37A51584E2EF43D09CEF1CE8D69BDEB8"==cid){
				//脑卒中
				$("#stroke").val(times[0])
			}else if("FC6FF7E27F064D9C98DCD5C83E70F119"==cid){
				//重性精神疾病
				$("#severeMentalIllness").val(times[0])
			}else if("52A0328740914BCE86ED10A4D2521814"==cid){
				//结核病
				$("#tuberculosis").val(times[0])
			}else if("AB9E834EDC1B4FE68CD41C94767AFD9A"==cid){
				//肝炎
				$("#hepatitis").val(times[0])
			}else if("DE414310BBCA47299208FD524D3B617A"==cid){
				//其他法定传染病
				$("#notifiableDisease").val(times[0])
			}else if("930FAEAF1DA34246A2CBDDA86EE480CD"==cid){
				//职业病
				$("#industrialDisease").val(times[0])
			}
		}
	}
	//经济类型回显
	$("#IncomeType").val("${data.IncomeType}");
	//状态回显
	$("#HrStatus").val("${data.HrStatus}");
	//备注信息回显
	$("#Remark").text("${data.Remark}");
 
	//状态变更说明回显
	$("#HrStatusRemark").text("${data.HrStatusRemark}");
	 
	//手术回显
	var HealthHistoryList ='${flist}';
	var flist=JSON.parse(HealthHistoryList);  
	if(flist!=null&&flist!=""){
		for(var i=0;i<flist.length;i++){
			var time=flist[i].OccurrenceDate;
			var timestrs =time.split("T");
			if(flist[i].RecordType==3){
				//输血
				var transfusionoption= '<div class="add_illness" >名称： <input type="text" name="transfusionName"  value='+flist[i].Name+' class="borderb"/> 时间： <input type="text" name="transfusiontime" id="transfusion" value='+ timestrs[0]+' class="borderb"/>'+'<a href="javascript:void(0)" class="add">+</a></div>';
				$("#transfusion").append(transfusionoption);
			}else if(flist[i].RecordType==2){
				//外伤
				var traumaoption ='<div class="add_illness" >名称： <input type="text" name="traumaName"   value='+flist[i].Name+' class="borderb"/> 时间： <input type="text" name="traumatime"   value='+ timestrs[0]+' class="borderb"/>'+'<a href="javascript:void(0)" class="add">+</a></div>';
				$("#trauma").append(traumaoption);
			}else if(flist[i].RecordType==1){
				//手术
				var operationoption='<div class="add_illness" >名称： <input type="text" name="operationName"   value='+flist[i].Name+' class="borderb"/> 时间： <input type="text" name="operationtime"  value='+ timestrs[0]+' class="borderb"/>'+'<a href="javascript:void(0)" class="add">+</a></div>';
				$("#operation").append(operationoption);
			}else if(flist[i].RecordType==4){
				//遗传病史
				var inheritanceoption='<div class="box2" >名称： <input type="text" name="inheritanceName"   value='+flist[i].Name+' class="borderb"/> 时间： <input type="text" name="inheritancetime"   value='+ timestrs[0]+' class="borderb"/>'+'<a href="javascript:void(0)" class="add_box2">+</a></div>';
				$("#inheritance").append(inheritanceoption);
			}
	
		}
	
	}
	//家族史回显
	//父亲回显
	var fillnessstr ='${fillness}';
	var fillnessList = new Array();
	fillnessList=fillnessstr.split(",");
	for(var i=0;i<fillnessList.length;i++){
		if(1==fillnessList[i]){
			$("#f1").val(1)
		}else if(2==fillnessList[i]){
			$("#f2").val(2)
		}else if(4==fillnessList[i]){
			$("#f3").val(3)
		}else if(8==fillnessList[i]){
			$("#f4").val(4)
		}else if(16==fillnessList[i]){
			$("#f5").val(5)
		}else if(32==fillnessList[i]){
			$("#f6").val(6)
		}else if(64==fillnessList[i]){
			$("#f7").val(7)
		}else if(128==fillnessList[i]){
			$("#f8").val(8)
		}else if(256==fillnessList[i]){
			$("#f9").val(9)
		}else if(512==fillnessList[i]){
			$("#f10").val(10)
		}else if(1024==fillnessList[i]){
			$("#f11").val(11)
		}else if(2048==fillnessList[i]){
			$("#f12").val(12)
		} 
	}
	//母亲回显
	var millnessstr ='${millness}';
	var millnessList = new Array();
	millnessList=millnessstr.split(",");
	for(var i=0;i<millnessList.length;i++){
		if(1==millnessList[i]){
			$("#m1").val(1)
		}else if(2==millnessList[i]){
			$("#m2").val(2)
		}else if(4==millnessList[i]){
			$("#m3").val(3)
		}else if(8==millnessList[i]){
			$("#m4").val(4)
		}else if(16==millnessList[i]){
			$("#m5").val(5)
		}else if(32==millnessList[i]){
			$("#m6").val(6)
		}else if(64==millnessList[i]){
			$("#m7").val(7)
		}else if(128==millnessList[i]){
			$("#m8").val(8)
		}else if(256==millnessList[i]){
			$("#m9").val(9)
		}else if(512==millnessList[i]){
			$("#m10").val(10)
		}else if(1024==millnessList[i]){
			$("#m11").val(11)
		}else if(2048==millnessList[i]){
			$("#m12").val(12)
		} 
	}
	//兄弟姐妹回显
	var billnessstr ='${billness}';
	var billnessList = new Array();
	billnessList=billnessstr.split(",");
	for(var i=0;i<billnessList.length;i++){
		if(1==billnessList[i]){
			$("#b1").val(1)
		}else if(2==billnessList[i]){
			$("#b2").val(2)
		}else if(4==billnessList[i]){
			$("#b3").val(3)
		}else if(8==billnessList[i]){
			$("#b4").val(4)
		}else if(16==billnessList[i]){
			$("#b5").val(5)
		}else if(32==billnessList[i]){
			$("#b6").val(6)
		}else if(64==billnessList[i]){
			$("#b7").val(7)
		}else if(128==billnessList[i]){
			$("#b8").val(8)
		}else if(256==billnessList[i]){
			$("#b9").val(9)
		}else if(512==billnessList[i]){
			$("#b10").val(10)
		}else if(1024==billnessList[i]){
			$("#b11").val(11)
		}else if(2048==billnessList[i]){
			$("#b12").val(12)
		} 
	}
	//子女回显
	var sillnessstr ='${sillness}';
	var sillnessList = new Array();
	sillnessList=sillnessstr.split(",");
	for(var i=0;i<sillnessList.length;i++){
		if(1==sillnessList[i]){
			$("#s1").val(1)
		}else if(2==sillnessList[i]){
			$("#s2").val(2)
		}else if(4==sillnessList[i]){
			$("#s3").val(3)
		}else if(8==sillnessList[i]){
			$("#s4").val(4)
		}else if(16==sillnessList[i]){
			$("#s5").val(5)
		}else if(32==sillnessList[i]){
			$("#s6").val(6)
		}else if(64==sillnessList[i]){
			$("#s7").val(7)
		}else if(128==sillnessList[i]){
			$("#s8").val(8)
		}else if(256==sillnessList[i]){
			$("#s9").val(9)
		}else if(512==sillnessList[i]){
			$("#s10").val(10)
		}else if(1024==sillnessList[i]){
			$("#s11").val(11)
		}else if(2048==sillnessList[i]){
			$("#s12").val(12)
		} 
	}
	
	//家族史
	//父亲
	function fillness(tr){		 
			var value=tr.getAttribute('value');
			//先判断先前是否选中
			var id =tr.getAttribute('id');		 
			if(!($("#"+id).data('abc'))){
				if(value==1){
					$("#"+id).data('abc','1')
					$("#f1").val(1)
					$("#f2").val("")
					$("#f3").val("")
					$("#f4").val("")
					$("#f5").val("")
					$("#f6").val("")
					$("#f7").val("")
					$("#f8").val("")
					$("#f9").val("")
					$("#f10").val("")
					$("#f11").val("")
					$("#f12").val("")
					 
				}else if(value==2){
					$("#"+id).data('abc','2')
					$("#f2").val(2)
					$("#f1").val("")
				}else if(value==4){
					$("#"+id).data('abc','3')
					$("#f3").val(3)
					$("#f1").val("")
				}else if(value==8){
					$("#"+id).data('abc','4')
					$("#f4").val(4)
					$("#f1").val("")
				}else if(value==16){
					$("#"+id).data('abc','5')
					$("#f5").val(5)
						$("#f1").val("")
				}else if(value==32){
					$("#"+id).data('abc','6')
					$("#f6").val(6)
					$("#f1").val("")
				}else if(value==64){
					$("#"+id).data('abc','7')
					$("#f7").val(7)
					$("#f1").val("")
				}else if(value==128){
					$("#"+id).data('abc','8')
					$("#f8").val(8)
					$("#f1").val("")
				}else if(value==256){
					$("#"+id).data('abc','9')
					$("#f9").val(9)
					$("#f1").val("")
				}else if(value==512){
					$("#"+id).data('abc','10')
					$("#f10").val(10)
					$("#f1").val("")
				}else if(value==1024){
					$("#"+id).data('abc','10')
					$("#f11").val(11)
					$("#f1").val("")
				}else if(value==2048){
					$("#"+id).data('abc','10')
					$("#f12").val(12)
					$("#f1").val("")
				}    
			}else{
				if(value==1){ 
					$("#"+id).data('abc','')
					 
					$("#f1").val("")
					 
					 
				}else if(value==2){
					$("#"+id).data('abc','')
					$("#f2").val("")
				}else if(value==4){
					$("#"+id).data('abc','')
					$("#f3").val("")
				}else if(value==8){
					$("#"+id).data('abc','')
					$("#f4").val("")
				}else if(value==16){
					$("#"+id).data('abc','')
					$("#f5").val("")
				}else if(value==32){
					$("#"+id).data('abc','')
					$("#f6").val("")
				}else if(value==64){
					$("#"+id).data('abc','')
					$("#f7").val("")
				}else if(value==128){
					$("#"+id).data('abc','')
					$("#f8").val("")
				}else if(value==256){
					$("#"+id).data('abc','')
					$("#f9").val("")
				}else if(value==512){
					$("#"+id).data('abc','')
					$("#f10").val("")
				}else if(value==1024){
					$("#"+id).data('abc','')
					$("#f11").val("")
				}else if(value==2048){
					$("#"+id).data('abc','')
					$("#f12").val("")
				}  
			} 
		}
	 
	//母亲
	function millness(tr){		 
			var value=tr.getAttribute('value');
			//先判断先前是否选中
			var id =tr.getAttribute('id');		 
			if(!($("#"+id).data('abc'))){
				if(value==1){
					$("#"+id).data('abc','1')
					$("#m1").val(1)
					$("#m2").val("")
					$("#m3").val("")
					$("#m4").val("")
					$("#m5").val("")
					$("#m6").val("")
					$("#m7").val("")
					$("#m8").val("")
					$("#m9").val("")
					$("#m10").val("")
					$("#m11").val("")
					$("#m12").val("")
					 
				}else if(value==2){
					$("#"+id).data('abc','2')
					$("#m2").val(2)
					$("#m1").val("")
				}else if(value==4){
					$("#"+id).data('abc','3')
					$("#m3").val(3)
					$("#m1").val("")
				}else if(value==8){
					$("#"+id).data('abc','4')
					$("#m4").val(4)
					$("#m1").val("")
				}else if(value==16){
					$("#"+id).data('abc','5')
					$("#m5").val(5)
					$("#m1").val("")
				}else if(value==32){
					$("#"+id).data('abc','6')
					$("#m6").val(6)
					$("#m1").val("")
				}else if(value==64){
					$("#"+id).data('abc','7')
					$("#m7").val(7)
					$("#m1").val("")
				}else if(value==128){
					$("#"+id).data('abc','8')
					$("#m8").val(8)
					$("#m1").val("")
				}else if(value==256){
					$("#"+id).data('abc','9')
					$("#m9").val(9)
					$("#m1").val("")
				}else if(value==512){
					$("#"+id).data('abc','10')
					$("#m10").val(10)
					$("#m1").val("")
				}else if(value==1024){
					$("#"+id).data('abc','10')
					$("#m11").val(11)
					$("#m1").val("")
				}else if(value==2048){
					$("#"+id).data('abc','10')
					$("#m12").val(12)
					$("#m1").val("")
				}    
			}else{
				if(value==1){ 
					$("#"+id).data('abc','')				 
					$("#m1").val("")								 
				}else if(value==2){
					$("#"+id).data('abc','')
					$("#m2").val("")
				}else if(value==4){
					$("#"+id).data('abc','')
					$("#m3").val("")
				}else if(value==8){
					$("#"+id).data('abc','')
					$("#m4").val("")
				}else if(value==16){
					$("#"+id).data('abc','')
					$("#m5").val("")
				}else if(value==32){
					$("#"+id).data('abc','')
					$("#m6").val("")
				}else if(value==64){
					$("#"+id).data('abc','')
					$("#m7").val("")
				}else if(value==128){
					$("#"+id).data('abc','')
					$("#m8").val("")
				}else if(value==256){
					$("#"+id).data('abc','')
					$("#m9").val("")
				}else if(value==512){
					$("#"+id).data('abc','')
					$("#m10").val("")
				}else if(value==1024){
					$("#"+id).data('abc','')
					$("#m11").val("")
				}else if(value==2048){
					$("#"+id).data('abc','')
					$("#m12").val("")
				}  
			} 
		}
	//兄弟姐妹
	function billness(tr){		 
			var value=tr.getAttribute('value');
			//先判断先前是否选中
			var id =tr.getAttribute('id');		 
			if(!($("#"+id).data('abc'))){
				if(value==1){
					$("#"+id).data('abc','1')
					$("#b1").val(1)
					$("#b2").val("")
					$("#b3").val("")
					$("#b4").val("")
					$("#b5").val("")
					$("#b6").val("")
					$("#b7").val("")
					$("#b8").val("")
					$("#b9").val("")
					$("#b10").val("")
					$("#b11").val("")
					$("#b12").val("")
					 
				}else if(value==2){
					$("#"+id).data('abc','2')
					$("#b2").val(2)
					$("#b1").val("")
				}else if(value==4){
					$("#"+id).data('abc','3')
					$("#b3").val(3)
					$("#b1").val("")
				}else if(value==8){
					$("#"+id).data('abc','4')
					$("#b4").val(4)
					$("#b1").val("")
				}else if(value==16){
					$("#"+id).data('abc','5')
					$("#b5").val(5)
					$("#b1").val("")
				}else if(value==32){
					$("#"+id).data('abc','6')
					$("#b6").val(6)
					$("#m1").val("")
				}else if(value==64){
					$("#"+id).data('abc','7')
					$("#b7").val(7)
					$("#b1").val("")
				}else if(value==128){
					$("#"+id).data('abc','8')
					$("#b8").val(8)
					$("#b1").val("")
				}else if(value==256){
					$("#"+id).data('abc','9')
					$("#b9").val(9)
					$("#b1").val("")
				}else if(value==512){
					$("#"+id).data('abc','10')
					$("#b10").val(10)
					$("#b1").val("")
				}else if(value==1024){
					$("#"+id).data('abc','10')
					$("#b11").val(11)
					$("#b1").val("")
				}else if(value==2048){
					$("#"+id).data('abc','10')
					$("#b12").val(12)
					$("#b1").val("")
				}    
			}else{
				if(value==1){ 
					$("#"+id).data('abc','')				 
					$("#b1").val("")								 
				}else if(value==2){
					$("#"+id).data('abc','')
					$("#b2").val("")
				}else if(value==4){
					$("#"+id).data('abc','')
					$("#b3").val("")
				}else if(value==8){
					$("#"+id).data('abc','')
					$("#b4").val("")
				}else if(value==16){
					$("#"+id).data('abc','')
					$("#b5").val("")
				}else if(value==32){
					$("#"+id).data('abc','')
					$("#b6").val("")
				}else if(value==64){
					$("#"+id).data('abc','')
					$("#b7").val("")
				}else if(value==128){
					$("#"+id).data('abc','')
					$("#b8").val("")
				}else if(value==256){
					$("#"+id).data('abc','')
					$("#b9").val("")
				}else if(value==512){
					$("#"+id).data('abc','')
					$("#b10").val("")
				}else if(value==1024){
					$("#"+id).data('abc','')
					$("#b11").val("")
				}else if(value==2048){
					$("#"+id).data('abc','')
					$("#b12").val("")
				}  
			} 
		}
	//子女
	function sillness(tr){		 
			var value=tr.getAttribute('value');
			//先判断先前是否选中
			var id =tr.getAttribute('id');		 
			if(!($("#"+id).data('abc'))){
				if(value==1){
					$("#"+id).data('abc','1')
					$("#s1").val(1)
					$("#s2").val("")
					$("#s3").val("")
					$("#s4").val("")
					$("#s5").val("")
					$("#s6").val("")
					$("#s7").val("")
					$("#s8").val("")
					$("#s9").val("")
					$("#s10").val("")
					$("#s11").val("")
					$("#s12").val("")
					 
				}else if(value==2){
					$("#"+id).data('abc','2')
					$("#s2").val(2)
					$("#s1").val("")
				}else if(value==4){
					$("#"+id).data('abc','3')
					$("#s3").val(3)
					$("#s1").val("")
				}else if(value==8){
					$("#"+id).data('abc','4')
					$("#s4").val(4)
					$("#s1").val("")
				}else if(value==16){
					$("#"+id).data('abc','5')
					$("#s5").val(5)
					$("#s1").val("")
				}else if(value==32){
					$("#"+id).data('abc','6')
					$("#s6").val(6)
					$("#s1").val("")
				}else if(value==64){
					$("#"+id).data('abc','7')
					$("#s7").val(7)
					$("#s1").val("")
				}else if(value==128){
					$("#"+id).data('abc','8')
					$("#s8").val(8)
					$("#s1").val("")
				}else if(value==256){
					$("#"+id).data('abc','9')
					$("#s9").val(9)
					$("#s1").val("")
				}else if(value==512){
					$("#"+id).data('abc','10')
					$("#s10").val(10)
					$("#s1").val("")
				}else if(value==1024){
					$("#"+id).data('abc','10')
					$("#s11").val(11)
					$("#s1").val("")
				}else if(value==2048){
					$("#"+id).data('abc','10')
					$("#s12").val(12)
					$("#s1").val("")
				}    
			}else{
				if(value==1){ 
					$("#"+id).data('abc','')				 
					$("#s1").val("")								 
				}else if(value==2){
					$("#"+id).data('abc','')
					$("#s2").val("")
				}else if(value==4){
					$("#"+id).data('abc','')
					$("#s3").val("")
				}else if(value==8){
					$("#"+id).data('abc','')
					$("#s4").val("")
				}else if(value==16){
					$("#"+id).data('abc','')
					$("#s5").val("")
				}else if(value==32){
					$("#"+id).data('abc','')
					$("#s6").val("")
				}else if(value==64){
					$("#"+id).data('abc','')
					$("#s7").val("")
				}else if(value==128){
					$("#"+id).data('abc','')
					$("#s8").val("")
				}else if(value==256){
					$("#"+id).data('abc','')
					$("#s9").val("")
				}else if(value==512){
					$("#"+id).data('abc','')
					$("#s10").val("")
				}else if(value==1024){
					$("#"+id).data('abc','')
					$("#s11").val("")
				}else if(value==2048){
					$("#"+id).data('abc','')
					$("#s12").val("")
				}  
			} 
		}
	
	//残疾情况
	function Disabilitying(tr){
		var value=tr.getAttribute('value');
		//先判断先前是否选中
		var id =tr.getAttribute('id');
		 
		if(!($("#"+id).data('abc'))){
			if(value==1){
				$("#"+id).data('abc','1')			 
				$("#Disability"+1).val(1)
				$("#Disability"+2).val("")
				$("#Disability"+3).val("")
				$("#Disability"+4).val("")
				$("#Disability"+5).val("")
				$("#Disability"+6).val("")
				$("#Disability"+7).val("")
				$("#Disability"+8).val("")	 
			}else if(value==2){
				$("#"+id).data('abc','2')
				$("#Disability"+2).val(2)
				$("#Disability"+1).val("")
			}else if(value==4){
				$("#"+id).data('abc','3')
				$("#Disability"+3).val(3)
				$("#Disability"+1).val("")
			}else if(value==8){
				$("#"+id).data('abc','4')
				$("#Disability"+4).val(4)
				$("#Disability"+1).val("")
			}else if(value==16){
				$("#"+id).data('abc','5')
				$("#Disability"+5).val(5)
				$("#Disability"+1).val("")
			}else if(value==32){
				$("#"+id).data('abc','6')
				$("#Disability"+6).val(6)
				$("#Disability"+1).val("")
			}else if(value==64){
				$("#"+id).data('abc','7')
				$("#Disability"+7).val(7)
				$("#Disability"+1).val("")
			}else if(value==128){
				$("#"+id).data('abc','8')
				$("#Disability"+8).val(8)
				$("#Disability"+1).val("")
			} 
		}else{
			if(value==1){	 
				$("#"+id).data('abc','')			 
				$("#Disability"+1).val("")	 
			}else if(value==2){
				$("#"+id).data('abc','')
				$("#Disability"+2).val("")
			}else if(value==4){
				$("#"+id).data('abc','')
				$("#Disability"+3).val("")
			}else if(value==8){
				$("#"+id).data('abc','')
				$("#Disability"+4).val("")
			}else if(value==16){
				$("#"+id).data('abc','')
				$("#Disability"+5).val("")
			}else if(value==32){
				$("#"+id).data('abc','')
				$("#Disability"+6).val("")
			}else if(value==64){
				$("#"+id).data('abc','')
				$("#Disability"+7).val("")
			}else if(value==128){
				$("#"+id).data('abc','')
				$("#Disability"+8).val("")
			} 
		} 
		
	}
	//残疾情况回显
	var Disabilitystr ='${Disability}';
	var DisabilityList = new Array();
	DisabilityList=Disabilitystr.split(",");
	for(var i=0;i<DisabilityList.length;i++){
		if(1==DisabilityList[i]){
			$("#Disability1").val(1)
		}else if(2==DisabilityList[i]){
			$("#Disability2").val(2)
		}else if(4==DisabilityList[i]){
			$("#Disability3").val(3)
		}else if(8==DisabilityList[i]){
			$("#Disability4").val(4)
		}else if(16==DisabilityList[i]){
			$("#Disability5").val(5)
		}else if(32==DisabilityList[i]){
			$("#Disability6").val(6)
		}else if(64==DisabilityList[i]){
			$("#Disability7").val(7)
		}else if(128==DisabilityList[i]){
			$("#Disability8").val(8)
		}
	}
	//厨房排风设施
    function KitchenExhausting(tr){
    	var id=tr.getAttribute('id');
		//var value=tr.getAttribute('value');
		$("#KitchenExhaust").val(id);
		//$("#KitchenExhaust").data("val",value);
	} 
	//燃料类型
	function FuelTypeing(tr){
		var id=tr.getAttribute('id');
		//var value=tr.getAttribute('value');
		$("#FuelType").val(id);
		//$("#FuelType").data("val",value);
	} 
	//饮水
	function Drinkingwatering(tr){
		var id=tr.getAttribute('id');
		//var value=tr.getAttribute('value');
		$("#Drinkingwater").val(id)
	//$("#Drinkingwater").data("val",value);
	} 
	//厕所
	function Toileting(tr){
		var id=tr.getAttribute('id');
		//var value=tr.getAttribute('value');
		$("#Toilet").val(id);
		//$("#Toilet").data("val",value);
	} 
	//禽畜栏
	function LivestockColumning(tr){
		var id=tr.getAttribute('id');
		//var value=tr.getAttribute('value');
		$("#LivestockColumn").val(id);
	    //$("#LivestockColumn").data("val",value);
	} 
			$(function(){
				
				//身份证号处理
				$("#idCard_1").on('click',function(){
					$("#CardID").val("无")
				})
				$("#idCard_2").on('click',function(){
					$("#CardID").val("不详")
				})
				
				//本人电话处理
				$("#ptelephone1").on('click',function(){
					$("#PersonTel").val("无")
				})
				$("#ptelephone2").on('click',function(){
					$("#PersonTel").val("不详")
				})
				 
				//现住址处理
				$("#masterAdd").on('click',function(){
					$("#CurrentAddress").val("${add}")
				})
				$("#currentAdd").on('click',function(){
					$("#CurrentAddress").val("${data.CurrentAddress}")
				})
				//处理户籍地址
				$("#fAdd").on('click',function(){
					$("#ResidenceAddress").val("${data.CurrentAddress}")
				})
				//建档日期回显
				var buildDate ="${data.BuildDate }";
				var buildDateList =buildDate.split("T");
				$("#BuildDate").val(buildDateList[0]);
				//流动人口回显
				var IsFlowing ="${data.IsFlowing }";
				if(IsFlowing=="1"){ 
					$("#IsFlowing").prop("selected","true");
				}
				//户口类型回显
				var ResType="${data.ResType}"
				if(ResType=="1"){
					$("#agriculture1").prop("checked","true")
				}else if(ResType=="2"){
					$("#agriculture2").prop("checked","true")
				}
				//与户主关系回显
				$("#HouseholderRelationship").val("${data.HouseholderRelationship }")
				//贫困人口回显
				$("#IsPoor").val("${data.IsPoor }");
				//个人编码回显
				$("div[name='person_code_1']").html("");
				var CodeList = "${data.PersonCode}";
				for(var i=1;i<=18;i++){
					var v="";
					if(i<=CodeList.length){
						v=CodeList.substr(i-1,1);
					}
					$("div[name='person_code_1']").append("<div class='codeCon' id='pCode_'" + i + " style='color:Gray'>" + v + "</div>");//"<div class='codeCon' id='pCode_'" + i + " style='color:Gray'>" + v + "</div>"
					/* if (i % 6 == 0 && i + 1 <= 18){
						$("#family_code_1").append("<div style='float:left;padding:4px;'>-</div>");//"<div style='float:left;padding:4px;'>-</div>"
					} */
						
				}
				
				//基本信息回显
				//厨房排风设施回显
				var KitchenExhaust ="${data.KitchenExhaust}";
				if(KitchenExhaust=="1"){
					$("#KitchenExhaust").val(1)
				}else if(KitchenExhaust=="2"){
					$("#KitchenExhaust").val(2)
				}else if(KitchenExhaust=="4"){
					$("#KitchenExhaust").val(3)
				}else if(KitchenExhaust=="8"){
					$("#KitchenExhaust").val(4)
				}
				//燃料类型回显
				var FuelType ="${data.FuelType}";
				if(FuelType=="1"){
					$("#FuelType").val(1)
				}else if(FuelType=="2"){
					$("#FuelType").val(2)
				}else if(FuelType=="4"){
					$("#FuelType").val(3)
				}else if(FuelType=="8"){
					$("#FuelType").val(4)
				}else if(FuelType=="16"){
					$("#FuelType").val(5)
				}else if(FuelType=="32"){
					$("#FuelType").val(6)
				}
				//饮水回显
				var Drinkingwater ="${data.Drinkingwater}";
				if(Drinkingwater=="1"){
					$("#Drinkingwater").val(1)
				}else if(Drinkingwater=="2"){
					$("#Drinkingwater").val(2)
				}else if(Drinkingwater=="4"){
					$("#Drinkingwater").val(3)
				}else if(Drinkingwater=="8"){
					$("#Drinkingwater").val(4)
				}else if(Drinkingwater=="16"){
					$("#Drinkingwater").val(5)
				}else if(Drinkingwater=="32"){
					$("#Drinkingwater").val(6)
				}
				//厕所回显
				var Toilet ="${data.Toilet}";
				if(Toilet=="1"){
					$("#Toilet").val(1)
				}else if(Toilet=="2"){
					$("#Toilet").val(2)
				}else if(Toilet=="4"){
					$("#Toilet").val(3)
				}else if(Toilet=="8"){
					$("#Toilet").val(4)
				}else if(Toilet=="16"){
					$("#Toilet").val(5)
				}
				//禽畜栏
				var LivestockColumn ="${data.LivestockColumn}";
				if(LivestockColumn=="1"){
					$("#LivestockColumn").val(1)
				}else if(LivestockColumn=="2"){
					$("#LivestockColumn").val(2)
				}else if(LivestockColumn=="4"){
					$("#LivestockColumn").val(3)
				}
				
				//档案信息卡
				//血型
				$("input[name='BloodTypeing']").each(function(i){
				   var BloodType=${data.BloodType}
				   if(this.id==BloodType){
					  this.checked="true" ;
				   } 
				});
				//RH阴性
				$("input[name='RhBlooding']").each(function(i){
				   var RhBlood=${data.RhBlood}
				   if(this.id==RhBlood){
					  this.checked="true" ;
				   } 
				});
				$(".content_div").eq(2).show().siblings('.content_div').hide();
				//内容选项卡；
				$(".Edit_navbar li a").on("click",function(){
					var index= $(this).parent().index();
					$(".Edit_navbar li a").removeClass("active_li");
					$(this).addClass('active_li');
					$(".content_div").eq(index).show().siblings('.content_div').hide();
					return false;
				});
				/* 	//民族下拉列表；
		    var national = [
		            "汉族", "壮族", "满族", "回族", "苗族", "维吾尔族", "土家族", "彝族", "蒙古族", "藏族", "布依族", "侗族", "瑶族", "朝鲜族", "白族", "哈尼族",
		            "哈萨克族", "黎族", "傣族", "畲族", "傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族", "羌族", "土族", "仫佬族", "锡伯族",
		            "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "布朗族", "塔吉克族", "阿昌族", "普米族", "鄂温克族", "怒族", "京族", "基诺族", "德昂族", "保安族",
		            "俄罗斯族", "裕固族", "乌孜别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族"
		    ];
		        var nat = document.getElementById ("national");
		        for ( var i = 0; i < national.length; i++)
		        {
		            var option = document.createElement ('option');
		            option.value = i;
		            var txt = document.createTextNode (national[i]);
		            option.appendChild (txt);
		            nat.appendChild (option);
		        } */
				});
			$("#save").on("click",function(){
				var saveParam = {};
				saveParam.ProductCode = "${ProductCode}";				 
				var Person = {};//居民基本信息
				var FamilyHistoryList = [];//家庭既往史
				var PersonHistoryList = [];//个人既往史
				var CmDataList = [];//个人疾病既往史
				//封面
				Person["ID"]="${data.ID}";
				Person["RegionCode"]="${data.RegionCode}";//区划编码    必需
				Person["FamilyID"]="${data.FamilyID}";//家庭ID    必需
				Person["CustomNumber"]=$("#CustomNumber").val();//自定义编码
				Person["Name"]=$("#Name").val();//姓名
				Person["CardID"]=$("#tab_CardID").val();//身份证号 使用基本信息中
				Person["CurrentAddress"]=$("#CurrentAddress").val();//现住址
				Person["ResidenceAddress"]=$("#ResidenceAddress").val();//户籍地址
				Person["PersonTel"]=$("#PersonTel").val();//本人电话
				Person["IsPoor"]=$("#IsPoor").val();//贫困人口
				Person["HouseholderRelationship"]=$("#HouseholderRelationship").val();//与户主关系
				Person["ResType"]=$("#ResType").val();//户口类型
				Person["IsFlowing"]=$("#IsFlowing").val();//流动人口
				Person["BuildEmployeeName"]=$("#BuildEmployeeName").val();//建档人
				Person["ResponsibilityDoctor"]=$("#ResponsibilityDoctor").val();//责任医生
				Person["BuildDate"]=$("#BuildDate").val();//建档日期			 
				//基本信息
				Person["GenderCode"]=$("#GenderCode").val();//性别
				Person["BirthDay"]=$("#BirthDay").val();//出生日期
				
				Person["WorkOrgName"]=$("#WorkOrgName").val();//工作单位
				
				Person["ContactPerson"]=$("#ContactPerson").val();//联系人姓名
				/* Person["ContacetTel"]=$("#ContactTel").val();//联系人电话 */   
				Person["ContaceTel"]=$("#ContactTel").val();//联系人电话   
				Person["HukouFlag"]=$("#HukouInd").val();//常住类型
				//Person["HukouInd"]=$("#HukouInd").val();//常住类型
				Person["NationCode"]=$("#NationCode").val();//民族
				Person["BloodType"]=$("#BloodType").val();//血型
				Person["RhBlood"]=$("#RhBlood").val();//RH阴性
				Person["EducationCode"]=$("#EducationCode").val();//文化程度
				Person["JobCode"]=$("#JobCode").val();//职业
				Person["MarryStatusCode"]=$("#MarryStatus").val();//婚姻状况
				var PaymentWaySum=0;
				var s0=$("#PaymentWay1").val()
				if(s0!=null&&s0!=""){
					PaymentWaySum+=1;
				}
				var s1=$("#PaymentWay2").val()
				if(s1!=null&&s1!=""){
					PaymentWaySum+=2;
				}
				var s2=$("#PaymentWay3").val()
				if(s2!=null&&s2!=""){
					PaymentWaySum+=4;
				}
				var s3=$("#PaymentWay4").val()
				if(s3!=null&&s3!=""){
					PaymentWaySum+=8;
				}
				var s4=$("#PaymentWay5").val()
				if(s4!=null&&s4!=""){
					PaymentWaySum+=16;
				}
				var s5=$("#PaymentWay6").val()
				if(s5!=null&&s5!=""){
					PaymentWaySum+=32;
				}
				
				var s6=$("#PaymentWay7").val()
				if(s6!=null&&s6!=""){
					PaymentWaySum+=64;
				}
				var s7=$("#PaymentWay8").val()
				if(s7!=null&&s7!=""){
					PaymentWaySum+=128;
				} 
				Person["PaymentWaystring"]=PaymentWaySum;//医疗费用支付方式
				Person["OtherPaymentWaystring"]=$("#OtherPaymentWaystring").val();//医疗费用其他
				
				var DrugAllergyHistorySum=0;
				var s0=$("#DrugAllergyHistory1").val()
				if(s0!=null&&s0!=""){
					DrugAllergyHistorySum+=1;
				}
				var s1=$("#DrugAllergyHistory2").val()
				if(s1!=null&&s1!=""){
					DrugAllergyHistorySum+=2;
				}
				var s2=$("#DrugAllergyHistory3").val()
				if(s2!=null&&s2!=""){
					DrugAllergyHistorySum+=4;
				}
				var s3=$("#DrugAllergyHistory4").val()
				if(s3!=null&&s3!=""){
					DrugAllergyHistorySum+=8;
				}
				var s4=$("#DrugAllergyHistory5").val()
				if(s4!=null&&s4!=""){
					DrugAllergyHistorySum+=16;
				}
				
				Person["DrugAllergyHistory"]=DrugAllergyHistorySum ;//药物过敏史
				Person["OtherDrugAllergyHistory"]=$("#OtherDrugAllergyHistory").val();//其他药物过敏史
				var ExposureHistorySum=0;
				var s0=$("#ExposureHistory1").val()
				if(s0!=null&&s0!=""){
					ExposureHistorySum+=1;
				}
				var s1=$("#ExposureHistory2").val()
				if(s1!=null&&s1!=""){
					ExposureHistorySum+=2;
				}
				var s2=$("#ExposureHistory3").val()
				if(s2!=null&&s2!=""){
					ExposureHistorySum+=4;
				}
				var s3=$("#ExposureHistory4").val()
				if(s3!=null&&s3!=""){
					ExposureHistorySum+=8;
				}
				Person["ExposureHistory"]= ExposureHistorySum;//暴露史
				var CmDataList =[];
				//当确诊时间有值就将其保存
				var highBloodPressure =$("#highBloodPressure").val();
				if(highBloodPressure!=null&&highBloodPressure!=""){
					var CmData ={};
					CmData.DiseaseKindID="52A0328740914BCE86ED10A4D2521816";
					CmData.DiagnosisDate=highBloodPressure;
					CmDataList.push(CmData)
				}
				var diabetes =$("#diabetes").val();
				if(diabetes!=null&&diabetes!=""){
					var CmData ={};
					CmData.DiseaseKindID="7AA0AFF1DDA040F18510513DE3753547";
					CmData.DiagnosisDate=diabetes;
					CmDataList.push(CmData)
				}
				var coronaryHeartdDisease =$("#coronaryHeartdDisease").val();
				if(coronaryHeartdDisease!=null&&coronaryHeartdDisease!=""){
					var CmData ={};
					CmData.DiseaseKindID="708DE71884C645D0AEF802E06C6B4B12";
					CmData.DiagnosisDate=coronaryHeartdDisease;
					CmDataList.push(CmData)
				}
				var chronicObstructivePulmonaryDisease =$("#chronicObstructivePulmonaryDisease").val();
				if(chronicObstructivePulmonaryDisease!=null&&chronicObstructivePulmonaryDisease!=""){
					var CmData ={};
					CmData.DiseaseKindID="C2B2CB0803EC46A684A00AE350A34251";
					CmData.DiagnosisDate=chronicObstructivePulmonaryDisease;
					CmDataList.push(CmData)
				}
				var cancer =$("#cancer").val();
				if(cancer!=null&&cancer!=""){
					var CmData ={};
					CmData.DiseaseKindID="E63F8CF300D44FECBD6FF96B9FCAF39C";
					CmData.DiagnosisDate=cancer;
					CmDataList.push(CmData)
				}
				var stroke =$("#stroke").val();
				if(stroke!=null&&stroke!=""){
					var CmData ={};
					CmData.DiseaseKindID="37A51584E2EF43D09CEF1CE8D69BDEB8";
					CmData.DiagnosisDate=stroke;
					CmDataList.push(CmData)
				}
				var severeMentalIllness =$("#severeMentalIllness").val();
				if(severeMentalIllness!=null&&severeMentalIllness!=""){
					var CmData ={};
					CmData.DiseaseKindID="FC6FF7E27F064D9C98DCD5C83E70F119";
					CmData.DiagnosisDate=severeMentalIllness;
					CmDataList.push(CmData)
				}
				var tuberculosis =$("#tuberculosis").val();
				if(tuberculosis!=null&&tuberculosis!=""){
					var CmData ={};
					CmData.DiseaseKindID="52A0328740914BCE86ED10A4D2521814";
					CmData.DiagnosisDate=tuberculosis;
					CmDataList.push(CmData)
				}
				var hepatitis =$("#hepatitis").val();
				if(hepatitis!=null&&hepatitis!=""){
					var CmData ={};
					CmData.DiseaseKindID="AB9E834EDC1B4FE68CD41C94767AFD9A";
					CmData.DiagnosisDate=hepatitis;
					CmDataList.push(CmData)
				}
				var notifiableDisease =$("#notifiableDisease").val();
				if(notifiableDisease!=null&&notifiableDisease!=""){
					var CmData ={};
					CmData.DiseaseKindID="DE414310BBCA47299208FD524D3B617A";
					CmData.DiagnosisDate=notifiableDisease;
					CmDataList.push(CmData)
				}
				var industrialDisease =$("#industrialDisease").val();
				if(industrialDisease!=null&&industrialDisease!=""){
					var CmData ={};
					CmData.DiseaseKindID="930FAEAF1DA34246A2CBDDA86EE480CD";
					CmData.DiagnosisDate=industrialDisease;
					CmDataList.push(CmData)
				}
				//手术处理
			   var PersonHistoryList =[]
			   var operationName  = $("input[name='operationName']");
               var operationtime = $("input[name='operationtime']");
			   if(operationtime.length>0){
				   for(var i=0;i<operationtime.length;i++){
				    	var operation ={};
				    	operation.RecordType="1";
				    	var value=operationName[i].value;
				    	if(value!=null&&value!=""){
				    		operation.Name=operationName[i].value;
					    	operation.OccurrenceDate=operationtime[i].value
					    	PersonHistoryList.push(operation);
				    	}
				    	
				   }
			   }
			   //外伤处理
			   var traumaName  = $("input[name='traumaName']");
               var traumatime = $("input[name='traumatime']");
			   if(traumatime.length>0){
				   for(var i=0;i<traumatime.length;i++){
				    	var trauma ={};
				    	trauma.RecordType="2";
				    	var value=traumaName[i].value;
				    	if(value!=null&&value!=""){
				    		trauma.Name=traumaName[i].value;
					    	trauma.OccurrenceDate=traumatime[i].value;
					    	PersonHistoryList.push(trauma)	
				    	}
				    	
				   }
			   }
			   //输血处理
			   var transfusionName  = $("input[name='transfusionName']");
               var transfusiontime = $("input[name='transfusiontime']");
			   if(transfusiontime.length>0){
				   for(var i=0;i<transfusiontime.length;i++){
				    	var transfusion ={};
				    	transfusion.RecordType="3";
				    	var value=transfusionName[i].value;
				    	if(value!=null&&value!=""){
				    		transfusion.Name=transfusionName[i].value;
					    	transfusion.OccurrenceDate=transfusiontime[i].value;
					    	PersonHistoryList.push(transfusion);
				    	}
				    	
				   }
			   }
			   //遗传病史处理
			   var inheritanceName  = $("input[name='inheritanceName']");
               var inheritancetime = $("input[name='inheritancetime']");
			   if(inheritancetime.length>0){
				   for(var i=0;i<inheritancetime.length;i++){
				    	var inheritance ={};
				    	inheritance.RecordType="4";
				    	var value=inheritanceName[i].value;
				    	if(value!=null&&value!=""){
				    		inheritance.Name=inheritanceName[i].value;
					    	inheritance.OccurrenceDate=inheritancetime[i].value;
					    	PersonHistoryList.push(inheritance);
				    	}
				    
				   }
			   }
			   
			   //家族史处理
			   var FamilyHistoryList = [];
				//保存父亲数据
			    var fSum=0;
				var f1=$("#f1").val()
				if(f1!=null&&f1!=""){
					fSum+=1;
				}
				var f2=$("#f2").val()
				if(f2!=null&&f2!=""){
					fSum+=2;
				}
				var f3=$("#f3").val()
				if(f3!=null&&f3!=""){
					fSum+=4;
				}
				var f4=$("#f4").val()
				if(f4!=null&&f4!=""){
					fSum+=8;
				}
				var f5=$("#f5").val()
				if(f5!=null&&f5!=""){
					fSum+=16;
				}
				var f6=$("#f6").val()
				if(f6!=null&&f6!=""){
					fSum+=32;
				}
				var f7=$("#f7").val()
				if(f7!=null&&f7!=""){
					fSum+=64;
				}
				var f8=$("#f8").val()
				if(f8!=null&&f8!=""){
					fSum+=128;
				}
				var f9=$("#f9").val()
				if(f9!=null&&f9!=""){
					fSum+=256;
				}
				var f10=$("#f10").val()
				if(f10!=null&&f10!=""){
					fSum+=512;
				}
				var f11=$("#f11").val()
				if(f11!=null&&f11!=""){
					fSum+=1024;
				}
				var f12=$("#f12").val()
				if(f12!=null&&f12!=""){
					fSum+=2048;
				}
				var father ={}
				father.RelationshipType="1";
				father.Disease=fSum;
				 
				father.Remark=$("#f_1").val();
				FamilyHistoryList.push(father)
				//保存母亲数据
			    var mSum=0;
				var m1=$("#m1").val()
				if(m1!=null&&m1!=""){
					mSum+=1;
				}
				var m2=$("#m2").val()
				if(m2!=null&&m2!=""){
					mSum+=2;
				}
				var m3=$("#m3").val()
				if(m3!=null&&m3!=""){
					mSum+=4;
				}
				var m4=$("#m4").val()
				if(m4!=null&&m4!=""){
					mSum+=8;
				}
				var m5=$("#m5").val()
				if(m5!=null&&m5!=""){
					mSum+=16;
				}
				var m6=$("#m6").val()
				if(m6!=null&&m6!=""){
					mSum+=32;
				}
				var m7=$("#m7").val()
				if(m7!=null&&m7!=""){
					mSum+=64;
				}
				var m8=$("#m8").val()
				if(m8!=null&&m8!=""){
					mSum+=128;
				}
				var m9=$("#m9").val()
				if(m9!=null&&m9!=""){
					mSum+=256;
				}
				var m10=$("#m10").val()
				if(m10!=null&&m10!=""){
					mSum+=512;
				}
				var m11=$("#m11").val()
				if(m11!=null&&m11!=""){
					mSum+=1024;
				}
				var m12=$("#m12").val()
				if(m12!=null&&m12!=""){
					mSum+=2048;
				}
				var mather ={}
				mather.RelationshipType="2";
				mather.Disease=mSum;
				mather.Remark=$("#m_1").val();
				FamilyHistoryList.push(mather)
				//保存兄弟姐妹数据
			    var bSum=0;
				var b1=$("#b1").val()
				if(b1!=null&&m1!=""){
					bSum+=1;
				}
				var b2=$("#b2").val()
				if(b2!=null&&b2!=""){
					bSum+=2;
				}
				var b3=$("#b3").val()
				if(b3!=null&&b3!=""){
					bSum+=4;
				}
				var b4=$("#b4").val()
				if(b4!=null&&b4!=""){
					bSum+=8;
				}
				var b5=$("#b5").val()
				if(b5!=null&&b5!=""){
					bSum+=16;
				}
				var b6=$("#b6").val()
				if(b6!=null&&b6!=""){
					bSum+=32;
				}
				var b7=$("#b7").val()
				if(b7!=null&&b7!=""){
					bSum+=64;
				}
				var b8=$("#b8").val()
				if(b8!=null&&b8!=""){
					bSum+=128;
				}
				var b9=$("#b9").val()
				if(b9!=null&&b9!=""){
					bSum+=256;
				}
				var b10=$("#b10").val()
				if(b10!=null&&b10!=""){
					bSum+=512;
				}
				var b11=$("#b11").val()
				if(b11!=null&&b11!=""){
					bSum+=1024;
				}
				var b12=$("#b12").val()
				if(b12!=null&&b12!=""){
					bSum+=2048;
				}
				var brother ={}
				brother.RelationshipType="3";
				brother.Disease=bSum;
				brother.Remark=$("#b_1").val();
				FamilyHistoryList.push(brother)
				//保存子女数据
			    var sSum=0;
				var s1=$("#s1").val()
				if(s1!=null&&s1!=""){
					sSum+=1;
				}
				var s2=$("#b2").val()
				if(s2!=null&&s2!=""){
					sSum+=2;
				}
				var s3=$("#s3").val()
				if(s3!=null&&s3!=""){
					sSum+=4;
				}
				var s4=$("#s4").val()
				if(s4!=null&&s4!=""){
					sSum+=8;
				}
				var s5=$("#s5").val()
				if(s5!=null&&s5!=""){
					sSum+=16;
				}
				var s6=$("#s6").val()
				if(s6!=null&&s6!=""){
					sSum+=32;
				}
				var s7=$("#s7").val()
				if(s7!=null&&s7!=""){
					sSum+=64;
				}
				var s8=$("#s8").val()
				if(s8!=null&&s8!=""){
					sSum+=128;
				}
				var s9=$("#s9").val()
				if(s9!=null&&s9!=""){
					sSum+=256;
				}
				var s10=$("#b10").val()
				if(s10!=null&&s10!=""){
					sSum+=512;
				}
				var s11=$("#s11").val()
				if(s11!=null&&s11!=""){
					sSum+=1024;
				}
				var s12=$("#s12").val()
				if(s12!=null&&s12!=""){
					sSum+=2048;
				}
				var srother ={}
				srother.RelationshipType="4";
				srother.Disease=sSum;
				srother.Remark=$("#s_1").val();
				FamilyHistoryList.push(srother)
				//残疾情况处理 
				var DisabilitySum=0;
				var d1=$("#Disability1").val()
				if(d1!=null&&d1!=""){
					DisabilitySum+=1;
				}
				var d2=$("#Disability2").val()
				if(d2!=null&&d2!=""){
					DisabilitySum+=2;
				}
				var d3=$("#Disability3").val()
				if(d3!=null&&d3!=""){
					DisabilitySum+=4;
				}
				var d4=$("#Disability4").val()
				if(d4!=null&&d4!=""){
					DisabilitySum+=8;
				}
				var d5=$("#Disability5").val()
				if(d5!=null&&d5!=""){
					DisabilitySum+=16;
				}
				var d6=$("#Disability6").val()
				if(d6!=null&&d6!=""){
					DisabilitySum+=32;
				}
				var d7=$("#Disability7").val()
				if(d7!=null&&d7!=""){
					DisabilitySum+=64;
				}
				var d8=$("#Disability8").val()
				if(s8!=null&&d8!=""){
					DisabilitySum+=128;
				}
				Person["Disability"] =DisabilitySum;
				Person["OtherDisability"] =$("#OtherDisability").val();
				Person["DisabilityNumber"] =$("#DisabilityNumber").val();
				//Person["KitchenExhaust"] =$("#KitchenExhaust").val();
				var KitchenExhaust =$("#KitchenExhaust").val();
				var KitchenExhaustSum =0;
				if(KitchenExhaust==1){
					KitchenExhaustSum=1;
				}else if(KitchenExhaust==2){
					KitchenExhaustSum=2;
				}else if(KitchenExhaust==3){
					KitchenExhaustSum=4;
				}else if(KitchenExhaust==4){
					KitchenExhaustSum=8;
				}
				Person["KitchenExhaust"]=KitchenExhaustSum;
				//Person["FuelType"] =$("#FuelType").val();
				var FuelType =$("#FuelType").val();
				var FuelTypeSum=0;
				if(FuelType==1){
					 FuelTypeSum=1;
				}else if(FuelType==2){
					FuelTypeSum=2;
				}else if(FuelType==3){
					FuelTypeSum=4;
				}else if(FuelType==4){
					FuelTypeSum=8;
				}else if(FuelType==5){
					FuelTypeSum=16;
				}else if(FuelType==6){
					FuelTypeSum=32;
				}
				 Person["FuelType"] =FuelTypeSum;
				//Person["Drinkingwater"] =$("#Drinkingwater").val();
			    var Drinkingwater = $("#Drinkingwater").val()
			    var DrinkingwaterSum=0;
				if(Drinkingwater==1){
					DrinkingwaterSum=1;
				}else if(Drinkingwater==2){
					DrinkingwaterSum=2;
				}else if(Drinkingwater==3){
					DrinkingwaterSum=4;
				}else if(Drinkingwater==4){
					DrinkingwaterSum=8;
				}else if(Drinkingwater==5){
					DrinkingwaterSum=16;
				}else if(Drinkingwater==6){
					DrinkingwaterSum=32;
				}
				Person["Drinkingwater"]=DrinkingwaterSum;
				//Person["Toilet"] =$("#Toilet").val();
				var Toilet=$("#Toilet").val();
				var ToiletSum=0;
				if(Toilet==1){
					ToiletSum=1;
				}else if(Toilet==2){
					ToiletSum=2;
				}else if(Toilet==3){
					ToiletSum=4;
				}else if(Toilet==4){
					ToiletSum=8;
				}else if(Toilet==5){
					ToiletSum=16;
				}
				Person["Toilet"]=ToiletSum;
				//Person["LivestockColumn"] =$("#LivestockColumn").val();
				var LivestockColumn = $("#LivestockColumn").val();
				var LivestockColumnSum=0;
				if(LivestockColumn==1){
					LivestockColumnSum=1;
				}else if(LivestockColumn==2){
					LivestockColumnSum=2;
				}else if(LivestockColumn==3){
					LivestockColumnSum=4;
				}
				Person["LivestockColumn"]=LivestockColumnSum;
				//扩展信息
				Person["IncomeType"] =$("#IncomeType").val();
				Person["WorkDate"] =$("#WorkDate").val();
		        Person["Remark"] =$("#Remark").val();
				
				Person["HrStatus"] =$("#HrStatus").val();
				Person["HrStatusDate"] =$("#HrStatusDate").val();
				Person["HrStatusRemark"] =$("#HrStatusRemark").val();
				 

				
				
				saveParam["Person"]=Person;
				saveParam["FamilyHistoryList"]=FamilyHistoryList;
				saveParam["PersonHistoryList"]=PersonHistoryList;
				saveParam["CmDataList"]=CmDataList;
				if(window.confirm('确定保存？')){
						console.debug(saveParam);
						$.ajax({
						    type: 'POST',
						    url: '/fdoctor/mobile/healthRecord/updateResidentHealthRecord' ,
						    data: {
						    	saveParam : JSON.stringify(saveParam),
						    } ,
						    success: function(data){
						    	if(data.code == '200'){
						    		alert('修改成功！');
						    		
						    	}else{
						    		alert('修改失败！');
						    	}
						    }
						});
				        return true;
				     }else{
				        return false;
			} 
			})
			
			
			//点击添加 手术，外伤，输血
		$('.no_illness').on('click',function(){
			$(this).addClass('chosed').siblings().removeClass('chosed')
			$(this).parent().siblings('.add_illness_td').find('.add_illness').hide();
			
			
		})
		$('.has_illness').on('click',function(){
			$(this).addClass('chosed').siblings().removeClass('chosed')
			$(this).parent().siblings('.add_illness_td').find('.add_illness').show();
		})
		
		
		
		
		
		
		//点击添加 手术，外伤，输血
		$(".add_illness .add").on('click',function(){
			var add_div='<div class="add_illness">'+
					'	名称： <input type="text" name="" id="" value="" class="borderb"/> 时间： <input type="date" name="" id="" value="" class="borderb"/>  '+
					'	<a href="javascript:void(0)" class="delete" onclick="delete_this(this)">X</a>'+
					'</div>';
			$(this).parents('.add_illness_td').append(add_div);
		});
		
		//点击删除;
		
		function delete_this(x){
			$(x).parent().remove();
		};
		//点击删除;
		
		//点击添加疾病的确诊时间;让他们显示
			$('.diseases_td .optionMore').on('click',function(){
			// 点击增加删除样式;
				$('.diseases_td .Noption').removeClass('chosed');
				$(this).toggleClass('chosed')
				var index=$(this).index();
				$('.box1').eq(index-1).toggle();
			});
			
			$('.diseases_td .Noption').on('click',function(){
				$('.diseases_td .optionMore').removeClass('chosed');
				$(this).addClass('chosed');
				$('.box1').hide();
			})
			
			
			//其他疾病的添加与删除
			var idx=1
			function other_delete(x){
				$(x).parent().remove();
				idx--;
				
			}
			$('.add_other_disase').on('click',function(){
				idx++;
				var other_disase='<div class=""  style="margin: 5px;">'+
								'	<span>其它疾病13. '+idx+'</span>'+
								'	<input type="text" name="" id="" value="" class="borderb"/>'+
								'	<span>确诊时间</span>'+
								'	<input type="date" name="" id="" value="" class="borderb"/>'+
								'	<a href="javascript:void(0)" onclick="other_delete(this)">X</a>'+
								'</div>';
				$(this).parents('.diseaseBox').append(other_disase);
			});
									
			
			
			//家族史疾病;
			
			$('.family_disase_td .option1').on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$(".family_disase_conent").eq(0).show().siblings().hide();
			});
			$('.family_disase_td .option2').on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$(".family_disase_conent").eq(1).show().siblings().hide();
			});
			$('.family_disase_td .option3').on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$(".family_disase_conent").eq(2).show().siblings().hide();
			});
			$('.family_disase_td .option4').on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$(".family_disase_conent").eq(3).show().siblings().hide();
			});
			
			$(".family_disase_conent .option").on('click',function(){
				$(this).toggleClass('chosed');
				$(this).siblings('.Noption').removeClass('chosed');
			})
			
			$(".family_disase_conent .Noption").on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed')
			
			})
			//遗传病;
			
			$(".ycb .option").on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$('.box2').show();
			});
			
			$(".ycb .Noption").on('click',function(){
				$(this).addClass('chosed').siblings().removeClass('chosed');
				$('.box2').hide();
			});					
			
			
			
			var add_box2='<div class="box2">'+
						'	名称： <input type="text" name="inheritanceName" id="" value="" class="borderb"/> 时间： <input type="date" name="inheritancetime" id="" value="" class="borderb"/>  '+
						'	<a href="javascript:void(0)" onclick="delete_this(this)">X</a>'+
						'</div>';
						
			$(".add_box2").on('click',function(){
				$('.ycb_content_td').append(add_box2);
			});
			
			//多选加样式
			$(".optionMore").on('click',function(){
				$(this).toggleClass("bluecolor");
				$(this).siblings('.Noption').removeClass('bluecolor')
			});
			// 多选全取消 加样式
			$(".Noption").on('click',function(){
				$(this).addClass('bluecolor').siblings('.optionMore').removeClass('bluecolor')
			})
			
			//单选加样式
			$(".option").on('click',function(){
				$(this).addClass('bluecolor').siblings('.option').removeClass('bluecolor');
			})
	</script>
</html>
