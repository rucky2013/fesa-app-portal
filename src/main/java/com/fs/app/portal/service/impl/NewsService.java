package com.fs.app.portal.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.fs.app.portal.entity.RenderData;
import com.fs.app.portal.pojo.UserCommentPojo;
import com.fs.app.portal.pojo.UserReadPojo;
import com.fs.app.portal.repository.IUserCommentRepository;
import com.fs.app.portal.repository.IUserReadRepository;
import com.fs.app.portal.service.INewsService;

public class NewsService implements INewsService{

	@Autowired
	private IUserReadRepository userReadRepository;
	@Autowired
	private IUserCommentRepository userCommentRepository;
	
	@Override
	public RenderData updateNewsRead(Integer userid, Integer newsid, Integer type) {
		UserReadPojo readPojo=new UserReadPojo();
		readPojo.setActionType(type);
		readPojo.setAddTime((new Date()));
		readPojo.setNewsId(newsid);
		readPojo.setUserId(userid);
		Boolean updateresult= userReadRepository.updateUserRead(readPojo);
		RenderData result=new RenderData(updateresult);
		return result;
	}

	@Override
	public RenderData updateNewComment(Integer userid, Integer newsid, String commentInfo) {
		UserCommentPojo commentPojo=new UserCommentPojo();
		commentPojo.setAddTime(new Date());
		commentPojo.setCommentInfo(commentInfo);
		commentPojo.setNewsId(newsid);
		commentPojo.setStatus(1);
		commentPojo.setUserId(userid);
		Boolean updateresult= userCommentRepository.updateUserComment(commentPojo);
		RenderData result=new RenderData(updateresult);
		return result;
	}

	@Override
	public RenderData getReadByNewId(Integer newsid, Integer type) {
		List<UserReadPojo> userRead=userReadRepository.getUserReads(newsid, type);
		RenderData result=new RenderData(userRead);
		return result;
	}

	@Override
	public RenderData getCommentByNewId(Integer newsid) {
		List<UserCommentPojo> commentPojo=userCommentRepository.getUserComments(newsid);
		RenderData result=new RenderData(commentPojo);
		return result;
	}
}
