package com.domain.POST;

public class Post {
    private Long id;
    private String title;
    private String description;

    // 생성자
    public Post(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
