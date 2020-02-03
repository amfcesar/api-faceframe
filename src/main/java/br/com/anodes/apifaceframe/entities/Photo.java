package br.com.anodes.apifaceframe.entities;

import lombok.Builder;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Builder
@Table(name = "tb_photo")
public class Photo extends BaseEntity<Long> {

    @URL
    @NotEmpty
    private String image;
    private String name;
    private String description;

    private Album album;


    @Column(name = "title", nullable = false)
    public String getImage() { return image; }

    @ManyToOne(fetch = FetchType.EAGER)
    public Album getAlbum() {
        return album;
    }
}
