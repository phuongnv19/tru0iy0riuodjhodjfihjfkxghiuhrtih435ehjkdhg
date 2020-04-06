package com.toprate.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "ATTACH_DOCUMENT")
@Data
public class AttachDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTACH_ID")
	private Long attachId;

	@Column(name = "DOCUMENT_PATH", length = 500)
	@NotNull
	private String documentPath;

	@Column(name = "SOURCE_ID")
	private Long sourceId;

	@Column(name = "DOCUMENT_NAME", length = 200)
	private String documentName;

	@Column(name = "TABLE_NAME", length = 100)
	private String tableName;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER_ID")
	private Long createUserId;

}
