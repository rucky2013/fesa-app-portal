package cn.lyf.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userFeed", catalog = "public")
public class userFeed implements java.io.Serializable {
	
	private static final long serialVersionUID = 1218362413947828627L;
	private Integer Id;
	private Integer userId;
	private String speciesId;
	private baseFeed feedId;
	private Date addTime;
	private String remark; 
	
	public userFeed(){}

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
	public baseFeed getFeedId() {
		return feedId;
	}

	public void setFeedId(baseFeed feedId) {
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
