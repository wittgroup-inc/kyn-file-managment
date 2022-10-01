package com.wittgroup.kyn.file.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UploadFileRequest {
    @NotNull
    private String fileBase64;

    @NotNull
    private String name;
}
