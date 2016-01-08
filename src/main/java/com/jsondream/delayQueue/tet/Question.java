package com.jsondream.delayQueue.tet;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Question extends QQ{

	private String id ;
	private String outId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Question(String id,String outId){
		this.outId =outId;
		this.id = id;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", outId=" + outId + "]";
	}

	@Override
    public int hashCode(){
        return id.hashCode()*33;
    }
	
	
}
