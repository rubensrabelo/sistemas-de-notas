package io.github.rubensrabelo.ms.note.application.service;

import io.github.rubensrabelo.ms.note.application.dto.note.NoteCreateDTO;
import io.github.rubensrabelo.ms.note.application.dto.note.NoteResponseDTO;
import io.github.rubensrabelo.ms.note.application.dto.note.NoteUpdateDTO;
import io.github.rubensrabelo.ms.note.application.exceptions.domain.DatabaseException;
import io.github.rubensrabelo.ms.note.application.exceptions.domain.ResourceNotFoundException;
import io.github.rubensrabelo.ms.note.domain.Note;
import io.github.rubensrabelo.ms.note.infra.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.rubensrabelo.ms.note.application.utils.NoteUpdateMapper.updateEntityFromDTO;

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

    @Transactional
    public NoteResponseDTO create(NoteCreateDTO createDTO) {
        Note entity = modelMapper.map(createDTO, Note.class);
        entity = repository.save(entity);
        return modelMapper.map(entity, NoteResponseDTO.class);
    }

    @Transactional
    public NoteResponseDTO update(Long id, NoteUpdateDTO updateDTO) {
        Note entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id = " + id + " not found."));
        updateEntityFromDTO(entity, updateDTO);
        entity = repository.save(entity);
        return modelMapper.map(entity, NoteResponseDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Task with id = " + id + " not found.");
        }  catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
