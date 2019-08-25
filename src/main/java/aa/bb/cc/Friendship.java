package aa.bb.cc;

import java.io.Serializable;
import java.util.Date;

public class Friendship implements Serializable{

	private Integer friendshipId;

	private Date createTime;

	private Integer fromUserId;

	private Date upadteTime;

	private Date confirmTime;

	private Integer toUserId;

	private Integer status;

	public void setFriendshipId(){

	}

	public Integer getFriendshipId(){
		return this.friendshipId;
	}

	public void setCreateTime(){

	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setFromUserId(){

	}

	public Integer getFromUserId(){
		return this.fromUserId;
	}

	public void setUpadteTime(){

	}

	public Date getUpadteTime(){
		return this.upadteTime;
	}

	public void setConfirmTime(){

	}

	public Date getConfirmTime(){
		return this.confirmTime;
	}

	public void setToUserId(){

	}

	public Integer getToUserId(){
		return this.toUserId;
	}

	public void setStatus(){

	}

	public Integer getStatus(){
		return this.status;
	}

}