package com.cgsinc.producer.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("Resources")

public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private UUID id;
	@Column
	private String uri;
	@Column
	private String fileType;
	@Column
	private String fileName;
	@Column
	private String content;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", uri=" + uri + ", fileType=" + fileType
				+ ", fileName=" + fileName + "]";
	}
	
	
	

}
