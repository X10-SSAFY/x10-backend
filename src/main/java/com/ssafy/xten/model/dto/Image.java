package com.ssafy.xten.model.dto;

public class Image {
	private Integer imageSeq;
	private String mimetype;
	private String original_name;
	private byte[] data;
	private String created;
	public Image() {
	}
	public Image(Integer imageSeq, String mimetype, String original_name, byte[] data, String created) {
		super();
		this.imageSeq = imageSeq;
		this.mimetype = mimetype;
		this.original_name = original_name;
		this.data = data;
		this.created = created;
	}
	public Integer getImageSeq() {
		return imageSeq;
	}
	public void setImageSeq(Integer imageSeq) {
		this.imageSeq = imageSeq;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

}
