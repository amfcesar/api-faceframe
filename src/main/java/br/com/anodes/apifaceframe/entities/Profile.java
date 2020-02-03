package br.com.anodes.apifaceframe.entities;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Setter
@Table(name = "tb_profile")
public class Profile extends BaseEntity<Long>   implements GrantedAuthority

    {

    private String name;


    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Override
    @Transient
    public String getAuthority() {
        return name;
    }
}
