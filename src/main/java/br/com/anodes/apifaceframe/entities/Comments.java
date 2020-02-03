package br.com.anodes.apifaceframe.entities;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Table(name = "tb_comments")
public class Comments extends BaseEntity<Long> {

    private Post post;
    private String text;


    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Post getPost() {
        return post;
    }
}
