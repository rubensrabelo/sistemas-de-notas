package io.github.rubensrabelo.ms.note.application.dto.note;

import jakarta.validation.constraints.Size;

import java.time.Instant;

public class NoteCreateDTO {

    @Size(max = 50, message = "Title must be at most 50 characters")
    private String title;
    private String content;

    public NoteCreateDTO() {
    }

    public NoteCreateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
