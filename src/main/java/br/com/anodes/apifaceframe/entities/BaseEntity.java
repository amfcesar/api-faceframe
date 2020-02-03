package br.com.anodes.apifaceframe.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Setter
public abstract class BaseEntity<ID extends Serializable> implements Serializable {


    private static final long serialVersionUID = 1L;

    private ID id;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public ID getId() {
        return id;
    }

    @Column(name = "data_criacao", nullable = false)
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Column(name = "data_atualizacao", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    @PrePersist
    public void prePersist() {
        final LocalDate atual = LocalDate.now();
        dataAtualizacao = atual;
        dataCriacao = atual;
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDate.now();
    }
}
