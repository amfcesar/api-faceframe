package br.com.anodes.apifaceframe.entities;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Table(name = "tb_post")
public class Post extends BaseEntity<Long> {

    private String title;
    private String author;
    private String text;
    private String link;
    private User user;
    private List<Comments> comments;


    @Column(name = "title", nullable = false)
    public String getTitle() { return title; }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Comments> getComments() {
        return comments;
    }


}
