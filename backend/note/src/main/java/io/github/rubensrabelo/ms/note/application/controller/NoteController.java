package io.github.rubensrabelo.ms.note.application.controller;

import io.github.rubensrabelo.ms.note.application.dto.note.NoteCreateDTO;
import io.github.rubensrabelo.ms.note.application.dto.note.NoteResponseDTO;
import io.github.rubensrabelo.ms.note.application.dto.note.NoteUpdateDTO;
import io.github.rubensrabelo.ms.note.application.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<NoteResponseDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        Page<NoteResponseDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NoteResponseDTO> findById(
            @PathVariable Long id
    ) {
        NoteResponseDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<NoteResponseDTO> create(
            @Valid @RequestBody NoteCreateDTO taskCreateDTO
    ) {
        NoteResponseDTO dto = service.create(taskCreateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<NoteResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody NoteUpdateDTO taskUpdateDTO
    ) {
        NoteResponseDTO dto = service.update(id, taskUpdateDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}