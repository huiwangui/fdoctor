package com.boco.modules.fdoc.web.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;

@Controller
@RequestMapping(value = "/mobile/record", produces = "application/json;charset=UTF-8")
public class RecordbrowseController {
    /*
     * 档案浏览 初始化体检信息
     * @author jomo
     * @param  personId 需要从安卓端传来个人ID 和体检日期默认最后一次
     */
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public String toExamnationList(HttpServletRequest request, Model model) {
		String androidData = request.getParameter("androidData");
		if (androidData != null) {
			Map androidDataMap = JsonUtils.getSingleJsonMap(androidData);
			if (androidDataMap.get("personId") == null || "".equals(androidDataMap.get("personId"))) {
				androidDataMap.put("personId", "");
				model.addAttribute("androidData", androidDataMap);
			
			String PersonID=(String) androidDataMap.get("personId");
			String ExamDate=(String) androidDataMap.get("ExamDate");
		

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// Map remap = JsonUtils.getSingleJsonMap(radte);
		// System.out.println("------&---(--哈哈哈哈---)----------" + radte);
		// String PersonID = (String) remap.get("ID");
		// String PersonID=request.getParameter("ID");
		//String PersonID = "CD8B44CC9AB7411BA693C4EE8524D39F";
		model.addAttribute("PersonID", PersonID);
		// 查体检日期 "CD8B44CC9AB7411BA693C4EE8524D39F"
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		paramMap.put("PersonID", PersonID);
		// paramMap.put("PersonID", "CD8B44CC9AB7411BA693C4EE8524D39F");
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List listExamDate = new ArrayList<>();
		List<Map<String, Object>> listMiIdAndExamDate = new ArrayList<Map<String, Object>>();
		if (responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			listMiIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			if (listMiIdAndExamDate.size() > 0 && listMiIdAndExamDate != null) {
				for (Map<String, Object> map : listMiIdAndExamDate) {
					String examDate = (String) map.get("ExamDate");
					listExamDate.add(examDate);
				}
			}
			System.out.println("+++++++++++++++++++++++++++" + listMiIdAndExamDate);

			// 通过PersonID日期获取最后一次体检信息
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ProductCode", PropertiesUtils.getValue("produce_code"));
			paramMap2.put("PersonID", PersonID);
			// paramMap2.put("PersonID", "CD8B44CC9AB7411BA693C4EE8524D39F");
			if (listExamDate.size() > 0 && listExamDate != null) {
				paramMap2.put("ExamDate", listExamDate.get(0));
			}
			if(ExamDate!=null){
				paramMap2.put("ExamDate", ExamDate);
			}
			String response2 = RestfulUtils.getPublicData("56-7", paramMap2);
			Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2);
			System.out.println("+++++++++最后一次体检信息responseMap2++++++++++++" + responseMap2);
			Map<String, Object> mapLastExamination = new HashMap<String, Object>();
			if (responseMap2.get("Result") != null && (Integer) responseMap2.get("Result") != 0) {

				mapLastExamination = (Map<String, Object>) responseMap2.get("Msg");
			}
			model.addAttribute("lastExamination", mapLastExamination);
			model.addAttribute("lastExaminationJson", JsonUtils.getJsonFormat(mapLastExamination));
			System.out.println("+++++++++++++++最后一次体检信息++++++++++++" + mapLastExamination);

			// 通过随访id 56-4 查询个人健康体检记录
			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("ProductCode", PropertiesUtils.getValue("produce_code"));
			// paramMap3.put("MtID", MtID);listMiIdAndExamDate
			paramMap3.put("MtID", listMiIdAndExamDate.get(0).get("MtId"));

			String response3 = RestfulUtils.getPublicData("56-4", paramMap3);
			Map<String, Object> responseMap3 = JsonUtils.getObjectJsonMap(response3);

			if (responseMap3.get("Result") != null || (Integer) responseMap3.get("Result") != 0) {

				Map<String, Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) responseMap3.get("Msg");

				// 获取随访时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式

				if (((Map<String, Object>) map.get("master")).get("ExamDate") != null
						&& !"".equals(((Map<String, Object>) map.get("master")).get("ExamDate"))) {
					String adate = (String) (((Map<String, Object>) map.get("master")).get("ExamDate"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(adate);
					Long ExamDateLong = Long.parseLong(m.replaceAll("").trim());
					String examDate = format.format(new Date(ExamDateLong));
					map.put("ExamDate", examDate);
					((Map<String, Object>) map.get("master")).put("ExamDate", examDate);
				}

				// 去除时间
				if (((List<Map>) map.get("vaccList")) != null && !"".equals(((List<Map>) map.get("vaccList")))) {
					for (int i = 0; i < ((List<Map>) map.get("vaccList")).size(); i++) {
						if (((List<Map>) map.get("vaccList")).get(i).get("VaccDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("VaccDate");
						}
						if (((List<Map>) map.get("vaccList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("ExamDate");
						}
					}
				}
				//
				if (((List<Map>) map.get("drugUseList")) != null && !"".equals(((List<Map>) map.get("drugUseList")))) {
					for (int i = 0; i < ((List<Map>) map.get("drugUseList")).size(); i++) {
						if (((List<Map>) map.get("drugUseList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("drugUseList")).get(i).remove("ExamDate");
						}
					}
				}

				//
				if (((List<Map>) map.get("hosList")) != null && !"".equals(((List<Map>) map.get("hosList")))) {
					for (int i = 0; i < ((List<Map>) map.get("hosList")).size(); i++) {
						if (((List<Map>) map.get("hosList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("ExamDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("InDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("InDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("OutDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("OutDate");
						}
					}
				}
				// 主表浏览回显字段处理
				Map master = (Map) map.get("master");
				if (map.get("master") != null) {
					int Guidance = (int) master.get("Guidance");
					switch (Guidance) {
					case 1:
						model.addAttribute("Guidance", "纳入慢性病患者健康管理");
						break;
					case 2:
						model.addAttribute("Guidance", "建议复查 ");
						break;
					case 3:
						model.addAttribute("Guidance", "建议转诊 ");
						break;
					default:
						model.addAttribute("Guidance", " ");
						break;
					}
					// RiskCrtl
					int RiskCrtl = (int) master.get("RiskCrtl");
					switch (RiskCrtl) {
					case 1:
						model.addAttribute("RiskCrtl", "戒烟 ");
						break;
					case 2:
						model.addAttribute("RiskCrtl", " 健康饮酒 ");
						break;
					case 3:
						model.addAttribute("RiskCrtl", "饮食 ");
						break;
					case 4:
						model.addAttribute("RiskCrtl", "锻炼 ");
						break;
					case 5:
						model.addAttribute("RiskCrtl", "减体重 ");
						break;
					case 6:
						model.addAttribute("RiskCrtl", "建议接种疫苗 ");
						break;
					default:
						model.addAttribute("RiskCrtl", " ");
						break;
					}
					// 健康评价是否异常：1 体检无异常 2 有异常
					int Assessment = (int) master.get("Assessment");
					switch (Assessment) {
					case 1:
						model.addAttribute("Assessment", "体检无异常");
						break;
					case 2:
						model.addAttribute("Assessment", "有异常");
						break;
					default:
						model.addAttribute("Assessment", " ");
						break;
					}

				}

				// 分隔符处理 脏器功能回显 BreathSounds

				Map organ = (Map) map.get("organ");
				if (map.get("organ") != null) {
					String Fundus = (String) organ.get("Fundus");
					if (Fundus != null && !"".equals(Fundus)) {
						String[] FundusArr = Fundus.split("\\|");
						if (FundusArr.length == 1) {
							organ.put("Fundus", FundusArr[0]);
						} else if (FundusArr.length > 1) {
							organ.put("FundusReason", FundusArr[1]);
						}
					}

					String BreathSounds = (String) organ.get("BreathSounds");
					if (BreathSounds != null && !"".equals(BreathSounds)) {
						String[] BreathSoundsArr = BreathSounds.split("\\|");
						if (BreathSoundsArr.length == 1) {
							organ.put("BreathSounds", BreathSoundsArr[0]);
						} else if (BreathSoundsArr.length > 1) {
							organ.put("BreathSoundsReason", BreathSoundsArr[1]);
						}
					}

					String HeartMurmur = (String) organ.get("HeartMurmur");
					if (HeartMurmur != null && !"".equals(HeartMurmur)) {
						String[] HeartMurmurArr = HeartMurmur.split("\\|");
						if (HeartMurmurArr.length == 1) {
							organ.put("HeartMurmur", HeartMurmurArr[0]);
						} else if (HeartMurmurArr.length > 1) {
							organ.put("HeartMurmurReason", HeartMurmurArr[1]);
						}
					}

					String AbdominalTenderness = (String) organ.get("AbdominalTenderness");
					if (AbdominalTenderness != null && !"".equals(AbdominalTenderness)) {
						String[] AbdominalTendernessArr = AbdominalTenderness.split("\\|");
						if (AbdominalTendernessArr.length == 1) {
							organ.put("AbdominalTenderness", AbdominalTendernessArr[0]);
						} else if (AbdominalTendernessArr.length > 1) {
							organ.put("AbdominalTendernessReason", AbdominalTendernessArr[1]);
						}
					}

					String AbdominalMass = (String) organ.get("AbdominalMass");
					if (AbdominalMass != null && !"".equals(AbdominalMass)) {
						String[] AbdominalMassArr = AbdominalMass.split("\\|");
						if (AbdominalMassArr.length == 1) {
							organ.put("AbdominalMass", AbdominalMassArr[0]);
						} else if (AbdominalMassArr.length > 1) {
							organ.put("AbdominalMassReason", AbdominalMassArr[1]);
						}
					}

					String TheAbdomenLiver = (String) organ.get("TheAbdomenLiver");
					if (TheAbdomenLiver != null && !"".equals(TheAbdomenLiver)) {
						String[] TheAbdomenLiverArr = TheAbdomenLiver.split("\\|");
						if (TheAbdomenLiverArr.length == 1) {
							organ.put("TheAbdomenLiver", TheAbdomenLiverArr[0]);
						} else if (TheAbdomenLiverArr.length > 1) {
							organ.put("TheAbdomenLiverReason", TheAbdomenLiverArr[1]);
						}
					}

					String Splenomegaly = (String) organ.get("Splenomegaly");
					if (Splenomegaly != null && !"".equals(Splenomegaly)) {
						String[] SplenomegalyArr = Splenomegaly.split("\\|");
						if (SplenomegalyArr.length == 1) {
							organ.put("Splenomegaly", SplenomegalyArr[0]);
						} else if (SplenomegalyArr.length > 1) {
							organ.put("TheAbdomenLiverReason", SplenomegalyArr[1]);
						}
					}

					String ShiftingDullness = (String) organ.get("ShiftingDullness");
					if (ShiftingDullness != null && !"".equals(ShiftingDullness)) {
						String[] ShiftingDullnessArr = ShiftingDullness.split("\\|");
						if (ShiftingDullnessArr.length == 1) {
							organ.put("ShiftingDullness", ShiftingDullnessArr[0]);
						} else if (ShiftingDullnessArr.length > 1) {
							organ.put("ShiftingDullnessReason", ShiftingDullnessArr[1]);
						}
					}

					// 健康浏览器回显 把数字转换成汉字
					// 口唇 1 红润2苍白3 发绀 4 皲裂 5 疱疹
					int Lips = (int) organ.get("Lips");
					System.out.println("Lips:" + Lips);
					switch (Lips) {
					case 1:
						model.addAttribute("Lips", " 红润");
						break;
					case 2:
						model.addAttribute("Lips", "苍白");
						break;
					case 3:
						model.addAttribute("Lips", "发绀 ");
						break;
					case 4:
						model.addAttribute("Lips", " 皲裂");
						break;
					case 5:
						model.addAttribute("Lips", " 疱疹");
						break;
					default:
						model.addAttribute("Lips", " 无");
						break;
					}

					// Throat number 咽部：1 无充血 2 充血 3 淋巴滤泡增生

					int Throat = (int) organ.get("Throat");
					System.out.println("Throat:" + Throat);
					switch (Throat) {
					case 1:
						model.addAttribute("Throat", "无充血");
						break;
					case 2:
						model.addAttribute("Throat", "充血");
						break;
					case 3:
						model.addAttribute("Throat", "淋巴滤泡增生 ");
						break;

					default:
						model.addAttribute("Throat", " ");
						break;
					}

					// Hearing number 听力：1 听见 2 听不清或无法听见

					int Hearing = (int) organ.get("Hearing");
					System.out.println("Hearing:" + Hearing);
					switch (Hearing) {
					case 1:
						model.addAttribute("Hearing", "听见");
						break;
					case 2:
						model.addAttribute("Hearing", "听不清或无法听见");
						break;
					default:
						model.addAttribute("Hearing", " ");
						break;
					}

					// MotorFunction number 听力：1 看见 2 看不清或无法看见
					int MotorFunction = (int) organ.get("MotorFunction");
					System.out.println("MotorFunction:" + MotorFunction);
					switch (MotorFunction) {
					case 1:
						model.addAttribute("MotorFunction", "看见");
						break;
					case 2:
						model.addAttribute("MotorFunction", "看不清或无法看见");
						break;
					default:
						model.addAttribute("MotorFunction", " ");
						break;
					}
					// Skin number 皮肤：1 正常 2 潮红 3 苍白 4 发绀 5 黄染 6 色素沉着 64 其他
					int Skin = (int) organ.get("Skin");
					System.out.println("Skin:" + Skin);
					switch (Skin) {
					case 1:
						model.addAttribute("Skin", "正常");
						break;
					case 2:
						model.addAttribute("Skin", "潮红");
						break;
					case 3:
						model.addAttribute("Skin", "苍白");
						break;
					case 4:
						model.addAttribute("Skin", "发绀");
						break;
					case 5:
						model.addAttribute("Skin", "黄染");
						break;
					case 6:
						model.addAttribute("Skin", " 色素沉着");
						break;
					case 7:
						model.addAttribute("Skin", "其他");
						break;
					default:
						model.addAttribute("Skin", " ");
						break;
					}

					// Sclera number 巩膜：1 正常 2 黄染 3 充血 4 其他（内容放【Other】里面）
					int Sclera = (int) organ.get("Sclera");
					System.out.println("Sclera:" + Sclera);
					switch (Sclera) {
					case 1:
						model.addAttribute("Sclera", "正常");
						break;
					case 2:
						model.addAttribute("Sclera", "黄染");
						break;
					case 3:
						model.addAttribute("Sclera", " 充血 ");
						break;
					case 4:
						model.addAttribute("Sclera", " 其他");
						break;
					default:
						model.addAttribute("Sclera", " ");
						break;
					}

					// LymphNodes number 淋巴结：1 未触及 2 锁骨上 3 腋窝 4 其他（内容放【Other】里面）
					int LymphNodes = (int) organ.get("LymphNodes");
					System.out.println("LymphNodes:" + LymphNodes);
					switch (LymphNodes) {
					case 1:
						model.addAttribute("LymphNodes", "未触及 ");
						break;
					case 2:
						model.addAttribute("LymphNodes", " 锁骨上");
						break;
					case 3:
						model.addAttribute("LymphNodes", "腋窝 ");
						break;
					case 4:
						model.addAttribute("LymphNodes", " 其他");
						break;
					default:
						model.addAttribute("LymphNodes", " ");
						break;
					}
					// BarrelChest number 肺 桶状胸：1 否 2 是
					int BarrelChest = (int) organ.get("BarrelChest");
					System.out.println("BarrelChest:" + BarrelChest);
					switch (BarrelChest) {
					case 1:
						model.addAttribute("BarrelChest", "否");
						break;
					case 2:
						model.addAttribute("BarrelChest", "是");
						break;
					default:
						model.addAttribute("BarrelChest", " ");
						break;
					}
					// BreathSounds string 肺 呼吸音：1正常2异常
					String BreathSounds2 = (String) organ.get("BreathSounds");
					System.out.println("BreathSounds2:" + BreathSounds2);
					switch (BreathSounds) {
					case "1":
						model.addAttribute("BreathSounds", "正常");
						break;
					case "2":
						model.addAttribute("BreathSounds", "异常");
						break;
					default:
						model.addAttribute("BreathSounds", " ");
						break;
					}
					// Rale number 肺 罗音：1 无 2 干罗音 3 湿罗音 4 其他
					int Rale = (int) organ.get("Rale");
					System.out.println("Rale:" + Rale);
					switch (Rale) {
					case 1:
						model.addAttribute("Rale", "无");
						break;
					case 2:
						model.addAttribute("Rale", "干罗音");
						break;
					case 3:
						model.addAttribute("Rale", " 湿罗音 ");
						break;
					case 4:
						model.addAttribute("Rale", " 其他");
						break;
					default:
						model.addAttribute("Rale", " ");
						break;
					}

					// Rhythm number 心脏心律：1 齐 2 不齐 3 绝对不齐
					int Rhythm = (int) organ.get("Rhythm");
					System.out.println("Rhythm:" + Rhythm);
					switch (Rale) {
					case 1:
						model.addAttribute("Rhythm", " 齐");
						break;
					case 2:
						model.addAttribute("Rhythm", "不齐");
						break;
					case 3:
						model.addAttribute("Rhythm", "绝对不齐 ");
						break;
					default:
						model.addAttribute("Rhythm", " ");
						break;
					}
					// HeartMurmur string 心脏杂音：1 无 2 有 格式形如
					String HeartMurmur2 = (String) organ.get("HeartMurmur");
					System.out.println("HeartMurmur2:" + HeartMurmur2);
					switch (HeartMurmur) {
					case "1":
						model.addAttribute("HeartMurmur", "无");
						break;
					case "2":
						model.addAttribute("HeartMurmur", "有");
						break;
					default:
						model.addAttribute("HeartMurmur", " ");
						break;
					}

					// AbdominalTenderness string 腹部 压痛：1 无 2 有 格式：1|”

					String AbdominalTenderness2 = (String) organ.get("AbdominalTenderness");
					if (AbdominalTenderness.equals("1")) {
						model.addAttribute("AbdominalTenderness", "无");
					}
					if (AbdominalTenderness.equals("2")) {
						model.addAttribute("AbdominalTenderness", AbdominalTenderness2.replace("2|", ""));
					}
					// AbdominalMass string 腹部 包块 1无 2有 格式：2|包块内容
					String AbdominalMass2 = (String) organ.get("AbdominalMass");
					if (AbdominalMass2.equals("1")) {
						model.addAttribute("AbdominalMass", "无");
					}
					if (AbdominalMass2.equals("2")) {
						model.addAttribute("AbdominalMass", AbdominalMass2);
					}
					// TheAbdomenLiver string 腹部 肝大 1无 2有 格式1|
					String TheAbdomenLiver2 = (String) organ.get("AbdominalMass");
					if (TheAbdomenLiver2.equals("1")) {
						model.addAttribute("TheAbdomenLiver", "无");
					}
					if (TheAbdomenLiver2.equals("2")) {
						model.addAttribute("TheAbdomenLiver", AbdominalMass2);
					}
					// Splenomegaly string 腹部 脾大 1无 2有 格式：1|
					String Splenomegaly2 = (String) organ.get("Splenomegaly");
					if (Splenomegaly2.equals("1")) {
						model.addAttribute("Splenomegaly", "无");
					}
					if (Splenomegaly2.equals("2")) {
						model.addAttribute("Splenomegaly", Splenomegaly2);
					}
					// ShiftingDullness string 腹部 移动性浊音 1无 2有 格式：”1|
					String ShiftingDullness2 = (String) organ.get("ShiftingDullness");
					if (ShiftingDullness2.equals("1")) {
						model.addAttribute("ShiftingDullness", "无");
					}
					if (ShiftingDullness2.equals("2")) {
						model.addAttribute("ShiftingDullness", ShiftingDullness2);
					}

				}
				System.out.println("===RRRRRRR==" + map.get("organ"));
				//// 分隔符处理 妇科
				Map women = (Map) map.get("women");
				if (women != null && "".equals(women)) {

					String Vulva = (String) women.get("Vulva");
					if (Vulva != null && !"".equals(Vulva)) {
						String[] VulvaArr = Vulva.split("\\|");
						if (VulvaArr.length == 1) {
							women.put("Vulva", VulvaArr[0]);
						} else if (VulvaArr.length > 1) {
							women.put("VulvaReason", VulvaArr[1]);
						}
					}

					String Vaginal = (String) women.get("Vulva");
					if (Vaginal != null && !"".equals(Vaginal)) {
						String[] VaginalArr = Vaginal.split("\\|");
						if (VaginalArr.length == 1) {
							women.put("Vaginal", VaginalArr[0]);
						} else if (VaginalArr.length > 1) {
							women.put("VaginalReason", VaginalArr[1]);
						}
					}

					String Cervix = (String) women.get("Cervix");
					if (Cervix != null && !"".equals(Cervix)) {
						String[] CervixArr = Cervix.split("\\|");
						if (CervixArr.length == 1) {
							women.put("Cervix", CervixArr[0]);
						} else if (CervixArr.length > 1) {
							women.put("CervixReason", CervixArr[1]);
						}
					}

					String Palace = (String) women.get("Palace");
					if (Palace != null && !"".equals(Palace)) {
						String[] PalaceArr = Palace.split("\\|");
						if (PalaceArr.length == 1) {
							women.put("Vulva", PalaceArr[0]);
						} else if (PalaceArr.length > 1) {
							women.put("VulvaReason", PalaceArr[1]);
						}
					}

					String UterineAdnexa = (String) women.get("UterineAdnexa");
					if (UterineAdnexa != null && !"".equals(UterineAdnexa)) {
						String[] UterineAdnexaArr = UterineAdnexa.split("\\|");
						if (UterineAdnexaArr.length == 1) {
							women.put("Vulva", UterineAdnexaArr[0]);
						} else if (UterineAdnexaArr.length > 1) {
							women.put("UterineAdnexaReason", UterineAdnexaArr[1]);
						}
					}

				}

				// 心电图Ecg string 心电图: 1 正常 2 异常 格式：1|
				Map labora = (Map) map.get("labora");
				if (labora != null && !"".equals(labora)) {

					String Ecg = (String) labora.get("Ecg");
					if (Ecg.equals("1")) {
						model.addAttribute("Ecg", "正常 ");
					}
					if (Ecg.equals("2")) {
						model.addAttribute("Ecg", "异常 ");
					}
					// ChestXRay string 胸部X线片 1正常2异常 格式：2|胸部太大

					String ChestXRay = (String) labora.get("ChestXRay");
					if (Ecg.equals("1")) {
						model.addAttribute("ChestXRay", "正常 ");
					}
					if (Ecg.equals("2")) {
						model.addAttribute("ChestXRay", "异常 ");
					}

					// BUltrasonicWave string B超1正常2异常 格式：1|

					String BUltrasonicWave = (String) labora.get("BUltrasonicWave");
					if (Ecg.equals("1")) {
						model.addAttribute("BUltrasonicWave", "正常 ");
					}
					if (Ecg.equals("2")) {
						model.addAttribute("BUltrasonicWave", "异常 ");
					}
					// CervicalSmear string 宫颈涂片0未选择1正常2异常 格式： 0|

					String CervicalSmear = (String) labora.get("CervicalSmear");
					if (Ecg.equals("1")) {
						model.addAttribute("CervicalSmear", "正常 ");
					}
					if (Ecg.equals("2")) {
						model.addAttribute("CervicalSmear", "异常 ");
					}
					// HepatitisBSurfaceAntigen string 乙型肝炎表面抗原

					String HepatitisBSurfaceAntigen = (String) labora.get("HepatitisBSurfaceAntigen");
					if (Ecg.equals("1")) {
						model.addAttribute("HepatitisBSurfaceAntigen", "正常 ");
					}
					if (Ecg.equals("2")) {
						model.addAttribute("HepatitisBSurfaceAntigen", "异常 ");
					}

					// FecalOccultBlood number 大便潜血
					int FecalOccultBlood = (int) organ.get("Rale");
					System.out.println("FecalOccultBlood:" + FecalOccultBlood);
					switch (FecalOccultBlood) {
					case 1:
						model.addAttribute("FecalOccultBlood", "无");
						break;
					case 2:
						model.addAttribute("FecalOccultBlood", "有");
						break;
					default:
						model.addAttribute("FecalOccultBlood", " ");
						break;
					}

				}

				// 获取症状信息，把2的幂次表示法分割为逗号隔开的数组 VaccDate
				if (((Map) map.get("master")).get("Symptom") != null) {
					Integer symptomInt = ((Integer) ((Map) map.get("master")).get("Symptom"));
					System.out.println("JSJSJSSSSSSSSMSSSSSSSSSSS" + symptomInt);
					// ((Map) map.get("master")).put("Symptom",
					// NumberUtils.bitand(symptomInt));
					System.out.println("===============================" + NumberUtils.bitand(symptomInt));

					((Map) map.get("master")).put("Symptom", NumberUtils.bitand(symptomInt));
					System.out.println("===============================" + NumberUtils.bitand(symptomInt));
					model.addAttribute("Symptom", ExamnationInfoController.getSymptom(NumberUtils.bitand(symptomInt)));
				}
				// 体检时间
				// model.addAttribute("listExamDate",
				// JsonUtils.getJsonFormat(listExamDate));
				// model.addAttribute("remap", remap);

				// 体检时间和随访ID ProductCode
				model.addAttribute("ProductCode", "8CBE1F526BE144419083D25720106D0E");
				model.addAttribute("listMtIdAndExamDate", listMiIdAndExamDate);
				model.addAttribute("listMtIdAndExamDateJson", JsonUtils.getJsonFormat(listMiIdAndExamDate));
				model.addAttribute("RecordOneJson", map);

				System.out.println(JsonUtils.getJsonFormat(map));
			}
		 }
	  }
	}
		return "/mobile/healthRecord/personRecordbrowse";

	}

	@ResponseBody
	@RequestMapping(value = "/browseExamination", method = RequestMethod.GET)
	public String browseExamination(HttpServletRequest request, Model model, String radte) {
		return radte;
	}

}
