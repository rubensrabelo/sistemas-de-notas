package io.github.rubensrabelo.ms.note.infra.repository;

import io.github.rubensrabelo.ms.note.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
