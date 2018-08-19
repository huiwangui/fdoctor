package com.boco.modules.fdoc.api;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.ArticleEntity;
import com.boco.modules.fdoc.model.CommentEntity;
import com.boco.modules.fdoc.service.CircleService;
import com.boco.modules.fdoc.vo.ArticleVo;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping(value = "/circle", produces = "text/json;charset=UTF-8")
public class CircleApi {
	@Resource
	CircleService circleService;

	@RequestMapping(value = "/uploadArticle", method = RequestMethod.POST, produces = "multipart/form-data;charset=UTF-8")
	@ApiOperation(value = "上传帖子信息", notes = "{\"personId\":\"用户personId\",\"topic\":\"话题分类\",\"img\":\"报告图片对象(可以上传多个)\"}", response = BaseJsonVo.class)
	public String uploadUserReportByDoc(
			@RequestParam String topic,
			@RequestParam String content,
			@RequestParam String personId,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file[],
			HttpServletRequest request) {
		// 封装对象
		ArticleEntity entity = new ArticleEntity();
		entity.setPersonId(personId);
		entity.setTopic(topic);
		entity.setCreateTime(new Date());

		entity.setContent(content);
		StringBuilder addStrc = new StringBuilder();
		/**
		 * 上传图片开始
		 */
		if (file != null) {
			try {
				if (file.length > 0) {
					for (int i = 0; i < file.length; i++) {
						CommonsMultipartFile img = file[i];
						if (img != null
								&& img.getOriginalFilename() != null
								&& StringUtils.isNotEmpty(img
										.getOriginalFilename())) {
							InputStream is = img.getInputStream();
							byte[] bs = new byte[1024];
							int len;
							// 生成jpeg图片
							String headSuffix = StringUtils.getSuffix(img
									.getOriginalFilename()); // 获取后缀名
							String signImgHeadName = UUID.randomUUID()
									.toString().substring(0, 8)
									+ "." + headSuffix; // 重命名为8位随机数

							// 复制文件到指定路径
							File saveFile = new File(
									(request.getContextPath()
											+ "/upload/img/circle/" + signImgHeadName)
											.substring(9));
							FileUtils.copyInputStreamToFile(is, saveFile);
							// 上传文件到服务器
							FTPUtils.upload(saveFile, "/upload/img/circle/");
							addStrc.append(
									(request.getContextPath()
											+ "/upload/img/circle/" + signImgHeadName)
											.substring(9)).append(",");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (addStrc.length() > 0) {
				addStrc.deleteCharAt(addStrc.length() - 1);
			}

			/*
			 * 1.根据id查询出此人对应的上传图片记录 2.有,就添加此人的上传图片,没有此人的记录就增加一条记录
			 */

			entity.setImg(addStrc.toString());
		}
		Integer flag = 1;// 判断上传是否成功
		flag = circleService.insertOne(entity);

		if (flag == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.DEAL_FAIL.getKey(),
					ApiStatusEnum.DEAL_FAIL.getValue()));
		}

		return JsonUtils.getJson(BaseJsonVo.success(null));
	}

	/** 添加评论 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ApiOperation(value = "添加评论", notes = "{\"commenterId\":\"用户id\",\"articleId\":2,\"comment\":\"评论内容\"}", response = BaseJsonVo.class)
	public String add(@RequestBody String commentBody) throws Exception {

		CommentEntity entity = JsonUtils.getObject(commentBody,
				CommentEntity.class);
		System.out.println(entity);
		String articleId = entity.getArticleId();
		entity.setCommentTime(new Date());
		Integer flag = 0;// 判断上传是否成功
		flag = circleService.insertOneComment(entity);

		if (flag == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.DEAL_FAIL.getKey(),
					ApiStatusEnum.DEAL_FAIL.getValue()));
		}
		// 上传成功 评论数量加1
		circleService.updateNum(articleId);
		return JsonUtils.getJson(BaseJsonVo.success(entity));

	}

	/** 根据话题查询文章列表 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ApiOperation(value = "查询文章列表", notes = "{\"topic\":\"话题类别\",\"pageNo\":\"页码\",\"pageSize\":\"每页大小\","
			+ "\"nickname\":\"用户名\",\"userImg\":\"用户头像\",\"createTime\":\"文章发表时间\",\"commentTime\":\"评论时间\",\"id\":\"文章id\",\"commenNum\":\"评论数\"}", response = BaseJsonVo.class)
	public String findAll(@RequestParam String topic,
			@RequestParam int pageSize, @RequestParam int pageNo) {
		ArticleVo vo = new ArticleVo();
		vo.setTopic(topic);
		// 总页数
		int totalRecord = circleService.getArtCount(vo);

		if (pageNo != 0 && pageSize != 0) {
			Page<ArticleVo> page = new Page<ArticleVo>(pageNo, pageSize,
					Long.valueOf(totalRecord));
			vo.setPage(page);
		}
		List<ArticleVo> volist = circleService.findAll(vo);
		if (volist != null && volist.size() > 0) {
			for (ArticleVo articleVo : volist) {
				String id = articleVo.getPersonId();
				ArticleVo vo2 = circleService.selectNameImg(id);
				if (vo2 != null) {
					articleVo.setNickname(vo2.getNickname());
					articleVo.setUserImg(vo2.getUserImg());
				}
			}
		}

		return JsonUtils.getPageJsonWithTotal(totalRecord, pageSize, volist);
	}

	/** 根据文章查询评论列表 */
	@RequestMapping(value = "/findByArticleId", method = RequestMethod.GET)
	@ApiOperation(value = "查询评论列表", notes = "{\"topic\":\"话题类别\",\"pageSize\":\"每页大小\",\"pageNo\":\"页码\",\"nickname\":\"用户名\",\"userImg\":\"用户头像\",\"createTime\":\"文章发表时间\",\"commentTime\":\"评论时间\",\"id\":\"文章id\"}", response = BaseJsonVo.class)
	public String findByArticleId(@RequestParam int articleId,
			@RequestParam int pageSize, @RequestParam int pageNo) {
		ArticleVo vo = new ArticleVo();
		vo.setArticleId(articleId);
		// 总页数
		int totalRecord = circleService.getCommentCount(vo);

		if (pageNo != 0 && pageSize != 0) {
			Page<ArticleVo> page = new Page<ArticleVo>(pageNo, pageSize,
					totalRecord);
			vo.setPage(page);
		}
		
		List<ArticleVo> volist = circleService.selectCommentList(vo);
		if (volist != null && volist.size() > 0) {
			for (ArticleVo articleVo : volist) {
				String id = articleVo.getCommenterId();
				ArticleVo vo2 = circleService.selectNameImg(id);
				if (vo2 != null) {
					articleVo.setNickname(vo2.getNickname());
					articleVo.setUserImg(vo2.getUserImg());
				}
			}
		}

		return JsonUtils.getPageJsonWithTotal(totalRecord, pageSize, volist);
	}

	/** 我的帖子列表 */
	@RequestMapping(value = "/getMyArticles", method = RequestMethod.GET)
	@ApiOperation(value = "查询我的帖子列表", notes = "{\"topic\":\"话题类别\",\"pageSize\":\"每页大小\",\"pageNo\":\"页码\",\"nickname\":\"用户名\",\"userImg\":\"用户头像\",\"createTime\":\"文章发表时间\",\"commentTime\":\"评论时间\",\"id\":\"文章id\"}", response = BaseJsonVo.class)
	public String myArts(
			@RequestParam(value = "topic", required = false) String topic,
			@RequestParam String personId, @RequestParam int pageSize,
			@RequestParam int pageNo) {
		ArticleVo vo = new ArticleVo();
		vo.setPersonId(personId);
		if (topic != null) {
			vo.setTopic(topic);
		}

		// 总页数
		int totalRecord = circleService.getArtCount(vo);

		if (pageNo != 0 && pageSize != 0) {
			Page<ArticleVo> page = new Page<ArticleVo>(pageNo, pageSize,
					totalRecord);
			vo.setPage(page);
		}

		List<ArticleVo> volist = circleService.findAll(vo);
		if (volist != null && volist.size() > 0) {
			for (ArticleVo articleVo : volist) {
				ArticleVo vo2 = circleService.selectNameImg(personId);
				if (vo2 != null) {
					articleVo.setNickname(vo2.getNickname());
					articleVo.setUserImg(vo2.getUserImg());
				}
			}
		}

		return JsonUtils.getPageJsonWithTotal(totalRecord, pageSize, volist);
	}

	/** 删除我的帖子 */
	@RequestMapping(value = "/delMyArticle", method = RequestMethod.POST)
	@ApiOperation(value = "删除我的帖子", notes = "{\"id\":\"帖子ID\",\"personId\":\"个人ID\"}", response = BaseJsonVo.class)
	public String delete(@RequestParam int id, @RequestParam String personId) {
		ArticleVo vo = new ArticleVo();
		vo.setId(id);
		vo.setPersonId(personId);
		List<ArticleVo> volist = circleService.findAll(vo);
		if (volist == null || volist.size() < 1) {
			return JsonUtils.getJson(new BaseJsonVo(
					ApiStatusEnum.ART_NOT_EXISTS.getKey(),
					ApiStatusEnum.ART_NOT_EXISTS.getValue(), id));
		}
		int i = circleService.delArt(vo);
		if (i > 0) {
			return JsonUtils.getJson(BaseJsonVo.success(id));
		}
		return JsonUtils.getJson(BaseJsonVo.error(vo));

	}
}
