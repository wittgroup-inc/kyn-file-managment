package com.gowittgroup.kyn.file.db.repositories;

import com.gowittgroup.kyn.file.db.entities.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FileRepository extends MongoRepository<FileEntity, UUID> {
}
