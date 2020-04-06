package com.toprate.erp.model;

import com.toprate.erp.dto.TemplateDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATE")
@Data
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID")
    private long templateId;

    @Column(name = "TEMPLATE_TYPE")
    private String templateType;

    @Column(name = "TEMPLATE_NAME")
    private String templateName;

    @Column(name = "TEMPLATE_PATH")
    private String templatePath;

    public TemplateDTO toDto(){
        TemplateDTO template = new TemplateDTO();
        template.setTemplateId(templateId);
        template.setTemplateType(templateType);
        template.setTemplateName(templateName);
        template.setTemplatePath(templatePath);
        return  template;
    }
}
