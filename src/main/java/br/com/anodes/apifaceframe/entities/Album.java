package br.com.anodes.apifaceframe.entities;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Table(name = "tb_album")
public class Album extends BaseEntity<Long> {

    private User user;
    private List<Photo> photos;


    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Photo> getPhotos() {
        return photos;
    }

}
