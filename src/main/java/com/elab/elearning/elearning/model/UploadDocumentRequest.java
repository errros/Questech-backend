package com.elab.elearning.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadDocumentRequest {
    @NotNull
    private String title;
    @NotNull
    private DocumentType documentType;
    @NotNull
    private String moduleCode;





}
