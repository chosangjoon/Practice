package com.domain.POST;

import javax.persistence.Entity;
import javax.persistence.CreatedDate;
import javax.persistence.LastModifiedDate;
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity //엔티티 클래스임을 나타냄
public class Post {
    @Id  // 기본키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 전략 사용
    private Long id;
    private String title;
    private String description;

    @CreatedDate //시간을 저장하는 어노테이션
    private LocalDateTime createdAt;

    @LastModifiedDate//엔티티가 수정될때마다 시간을 자동으로 업데이트
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "post_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

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
