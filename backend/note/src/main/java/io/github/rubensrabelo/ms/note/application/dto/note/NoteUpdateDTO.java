package io.github.rubensrabelo.ms.note.application.dto.note;

import jakarta.validation.constraints.Size;

public class NoteUpdateDTO {

    @Size(max = 50, message = "Title must be at most 50 characters")
    private String title;
    private String content;

    public NoteUpdateDTO() {
    }

    public NoteUpdateDTO(String title, String content) {
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
