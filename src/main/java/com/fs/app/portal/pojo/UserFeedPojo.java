package com.fs.app.portal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userFeed", schema = "public")
public class UserFeedPojo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1218362413947828627L;
	private Integer Id;
	private Integer userId;
	private String speciesId;
	private BaseFeedPojo feedId;
	private Date addTime;
	private String remark; 
	
	public UserFeedPojo(){}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "speciesId", length = 500)
	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}
	@Column(name = "feedId")
	public BaseFeedPojo getFeedId() {
		return feedId;
	}

	public void setFeedId(BaseFeedPojo feedId) {
		this.feedId = feedId;
	}
	@Column(name = "addTime")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "remark", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
