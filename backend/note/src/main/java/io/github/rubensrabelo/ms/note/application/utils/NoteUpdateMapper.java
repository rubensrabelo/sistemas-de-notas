package io.github.rubensrabelo.ms.note.application.utils;

import io.github.rubensrabelo.ms.note.application.dto.note.NoteUpdateDTO;
import io.github.rubensrabelo.ms.note.domain.Note;

public class NoteUpdateMapper {
    private NoteUpdateMapper() {}


    public static void updateEntityFromDTO(Note entity, NoteUpdateDTO updateDTO) {
        entity.setTitle(updateDTO.getTitle());
        entity.setContent(updateDTO.getContent());
    }
}
