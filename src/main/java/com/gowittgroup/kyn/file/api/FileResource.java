package com.gowittgroup.kyn.file.api;

import com.gowittgroup.kyn.file.models.File;
import com.gowittgroup.kyn.file.models.UploadFileRequest;
import com.gowittgroup.kyn.file.services.FileService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileResource {
    private final FileService fileService;

    FileResource(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        return ResponseEntity.ok(fileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFile(@PathVariable final UUID id) {
        return ResponseEntity.ok(fileService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestBody @Valid final UploadFileRequest request) {
        return new ResponseEntity<>(fileService.upload(request.getFileBase64(), request.getName()), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFile(@RequestBody String url) {
        fileService.delete(url);
        return ResponseEntity.noContent().build();
    }
}
