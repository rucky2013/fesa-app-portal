package com.fs.app.portal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userRead", schema = "public")
public class UserReadPojo implements java.io.Serializable {
	
	private static final long serialVersionUID = -3298680872588545805L;
	private Integer Id;
	private Integer userId;
	private String speciesId;
	private Integer feedId;
	private String feedName;
	private String feedAddress;
	private Integer detailId;
	private Integer readCount;
	private Date addTime;
	private Date updateTime;
	private String remark; 
	
	public UserReadPojo(){}

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

	@Column(name = "speciesId",length=100)
	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}
	@Column(name = "feedId")
	public Integer getFeedId() {
		return feedId;
	}

	public void setFeedId(Integer feedId) {
		this.feedId = feedId;
	}
	@Column(name = "feedName",length=100)
	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	@Column(name = "feedAddress",length=100)
	public String getFeedAddress() {
		return feedAddress;
	}

	public void setFeedAddress(String feedAddress) {
		this.feedAddress = feedAddress;
	}
	@Column(name = "detailId")
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	@Column(name = "readCount")
	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	@Column(name = "addTime")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "remark",length=100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
