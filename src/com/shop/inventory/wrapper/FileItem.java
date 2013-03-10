package com.shop.inventory.wrapper;

import java.io.File;

public class FileItem {
	
	private String name;
	private String mime;
	private int sizeBytes;
	private long length;
	private byte[] data;
	private File fileTmp;

	private String productRefCode;
	private String attachType;
	private String uploadFileName;
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}

	public File getFileTmp() {
		return fileTmp;
	}

	public void setFileTmp(File fileTmp) {
		this.fileTmp = fileTmp;
	}

	public int getSizeBytes() {
		return sizeBytes;
	}

	public void setSizeBytes(int sizeBytes) {
		this.sizeBytes = sizeBytes;
	}
	
	public String getProductRefCode() {
		return productRefCode;
	}

	public void setProductRefCode(String productRefCode) {
		this.productRefCode = productRefCode;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
}
