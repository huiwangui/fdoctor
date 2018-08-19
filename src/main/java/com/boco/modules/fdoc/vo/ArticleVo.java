package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Arrays;
import java.util.Date;

public class ArticleVo {


	public CommonsMultipartFile[] getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile[] file) {
		this.file = file;
	}
	

	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(String commenterId) {
		this.commenterId = commenterId;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    private Integer id;

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ArticleVo{" +
				"id=" + id +
				", articleId=" + articleId +
				", commenterId='" + commenterId + '\'' +
				", commentTime=" + commentTime +
				", comment='" + comment + '\'' +
				", content='" + content + '\'' +
				", personId='" + personId + '\'' +
				", topic='" + topic + '\'' +
				", title='" + title + '\'' +
				", img='" + img + '\'' +
				", createTime=" + createTime +
				", commentNum=" + commentNum +
				", userImg='" + userImg + '\'' +
				", docImg='" + docImg + '\'' +
				", docName='" + docName + '\'' +
				", nickname='" + nickname + '\'' +
				", commentId='" + commentId + '\'' +
				", file=" + Arrays.toString(file) +
				", page=" + page +
				'}';
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer articleId;
	private String commenterId;
	private Date commentTime;
	private String comment;
	private String content;
	private String personId;
	private String topic;
	private String title;
	private String img;
	private Date createTime;
	private int commentNum;
	private String userImg;
	private String docImg;
	private String docName;
	private String nickname;
	private String commentId;
	private CommonsMultipartFile  file[];
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	private Page<ArticleVo> page;	//分页对象
	
	public Page<ArticleVo> getPage() {
		return page;
	}
	public void setPage(Page<ArticleVo> page) {
		this.page = page;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getDocImg() {
		return docImg;
	}
	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

}
