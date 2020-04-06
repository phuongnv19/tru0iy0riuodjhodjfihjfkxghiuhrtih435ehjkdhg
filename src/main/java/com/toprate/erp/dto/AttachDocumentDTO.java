package com.toprate.erp.dto;

import java.util.Date;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.model.AttachDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AttachDocumentDTO  {

	private Long attachId;

	@AdjHistory(field = "DOCUMENT_PATH")
	private String documentPath;

	@AdjHistory(field = "SOURCE_ID")
	private Long sourceId;

	@AdjHistory(field = "DOCUMENT_NAME")
	private String documentName;

	@AdjHistory(field = "TABLE_NAME")
	private String tableName;

	@AdjHistory(field = "CREATE_DATE")
	private Date createDate;

	@AdjHistory(field = "CREATE_USER_ID")
	private Long createUserId;

	public AttachDocument toModel() {
		AttachDocument attachDocument = new AttachDocument();
		attachDocument.setAttachId(attachId);
		attachDocument.setCreateDate(createDate);
		attachDocument.setCreateUserId(createUserId);
		attachDocument.setDocumentName(documentName);
		attachDocument.setDocumentPath(documentPath);
		attachDocument.setSourceId(sourceId);
		attachDocument.setTableName(tableName);
		return attachDocument;

	}
}
