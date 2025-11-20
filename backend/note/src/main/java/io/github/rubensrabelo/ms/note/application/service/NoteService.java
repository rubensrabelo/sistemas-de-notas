package io.github.rubensrabelo.ms.note.application.service;

import io.github.rubensrabelo.ms.note.application.dto.note.NoteResponseDTO;
import io.github.rubensrabelo.ms.note.application.exceptions.domain.ResourceNotFoundException;
import io.github.rubensrabelo.ms.note.domain.Note;
import io.github.rubensrabelo.ms.note.infra.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final ModelMapper modelMapper;

    public NoteService(NoteRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Page<NoteResponseDTO> findAll(Pageable pageable) {
        Page<Note> entities = repository.findAll(pageable);
        return entities.map(
                entity -> modelMapper.map(entity, NoteResponseDTO.class)
        );
    }

    public NoteResponseDTO findById(Long id) {
        Note entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id = " + id + " not found."));
        return modelMapper.map(entity, NoteResponseDTO.class);
    }
}
