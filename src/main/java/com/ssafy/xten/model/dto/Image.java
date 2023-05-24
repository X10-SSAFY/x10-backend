package com.ssafy.xten.model.dto;


public class Image {
	private int imageSeq;
	private String imageType;
	private String imageName;
	private byte[] imageData;
	private String uploadDate;
	private int userSeq;

	public Image() {
	}

	public Image(int imageSeq, String imageType, String imageName, byte[] imageData, String uploadDate, int userSeq) {
		super();
		this.imageSeq = imageSeq;
		this.imageType = imageType;
		this.imageName = imageName;
		this.imageData = imageData;
		this.uploadDate = uploadDate;
		this.userSeq = userSeq;
	}

	public int getImageSeq() {
		return imageSeq;
	}

	public void setImageSeq(int imageSeq) {
		this.imageSeq = imageSeq;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}


}
