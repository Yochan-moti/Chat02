package model;

import java.io.Serializable;

public class PostKoukan implements Serializable{
	private String userName;
	private String item;
	private String epi;
	
	public PostKoukan() {}
	
	public PostKoukan(String userName, String item, String epi) {
		this.userName = userName;
		this.item = item;
		this.epi = epi;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getItem() {
		return item;
	}
	
	public String getEpi() {
		
		return epi;
	}
}
