package com.idstar.lms.api.model.master;

import com.idstar.lms.api.generic.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "status")
public class Status implements Serializable, GenericEntity<Status> {
    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    private String name;

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public void update(Status s) {
        this.name = s.getName();
    }

    @Override
    public Status createNewInstance() {
        Status newInstance = new Status();
        newInstance.update(this);

        return newInstance;
    }
}
