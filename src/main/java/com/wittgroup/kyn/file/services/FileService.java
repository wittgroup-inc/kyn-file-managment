package com.wittgroup.kyn.file.services;

import com.wittgroup.kyn.file.db.entities.FileEntity;
import com.wittgroup.kyn.file.db.repositories.FileRepository;
import com.wittgroup.kyn.file.models.File;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private FileRepository fileRepository;
    private ImageKit imageKit;

    FileService(final FileRepository fileRepository, final ImageKit imageKit) {
        this.fileRepository = fileRepository;
        this.imageKit = imageKit;
    }

    public String upload(final String base64, final String name) {
        FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, name);
        Result result;
        try {
            result = imageKit.upload(fileCreateRequest);
            if (result != null) {
                fileRepository.save(mapResultToFileEntity(result, new FileEntity()));
            }
        } catch (InternalServerException | UnknownException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (TooManyRequestsException e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        } catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return result.getUrl();
    }

    public boolean delete(String url) {
        FileEntity file = fileRepository.findAll().stream()
                .filter(fileEntity -> fileEntity.getUrl().equals(url))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            Result result = imageKit.deleteFile(file.getFileId());
            if (result != null) {
                fileRepository.deleteById(file.getId());
                return true;
            }
        } catch (InternalServerException | UnknownException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (TooManyRequestsException e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        } catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return false;
    }

    public List<File> findAll() {
        return fileRepository.findAll().stream().map(this::mapFile).toList();
    }

    public File get(UUID id) {
        return fileRepository.findById(id).map(this::mapFile).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    private FileEntity mapResultToFileEntity(Result result, FileEntity entity) {
        entity.setFileId(result.getFileId());
        entity.setName(result.getName());
        entity.setUrl(result.getUrl());
        return entity;
    }

    private File mapFile(FileEntity entity) {
        File file = new File();
        file.setId(entity.getId());
        file.setFileId(entity.getFileId());
        file.setName(entity.getName());
        file.setUrl(entity.getUrl());
        return file;
    }
}
