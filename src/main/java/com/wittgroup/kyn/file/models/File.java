package com.wittgroup.kyn.file.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class File {

    private UUID id;

    @NotNull
    private String fileId;

    @NotNull
    private String name;

    @NotNull
    private String url;
}
