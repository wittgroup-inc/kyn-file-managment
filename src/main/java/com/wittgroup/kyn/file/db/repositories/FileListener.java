package com.wittgroup.kyn.file.db.repositories;

import com.wittgroup.kyn.file.db.entities.FileEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileListener extends AbstractMongoEventListener<FileEntity> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<FileEntity> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }
}
