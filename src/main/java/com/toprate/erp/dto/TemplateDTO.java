package com.toprate.erp.dto;

import com.toprate.erp.base.anotation.AdjHistory;
import com.toprate.erp.model.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateDTO {
    private long templateId;
    @AdjHistory(field = "TEMPLATE_TYPE")
    private String templateType;

    @AdjHistory(field = "TEMPLATE_NAME")
    private String templateName;

    @AdjHistory(field = "TEMPLATE_PATH")
    private String templatePath;
    public Template toModel(){
        Template template = new Template();
        template.setTemplateId(templateId);
        template.setTemplateType(templateType);
        template.setTemplateName(templateName);
        template.setTemplatePath(templatePath);
        return  template;
    }
}
