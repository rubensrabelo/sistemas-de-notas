package io.github.rubensrabelo.ms.note.application.dto.note;

public class NoteUpdateDTO {

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
