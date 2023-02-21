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
@Table(name = "position")
public class Position implements Serializable, GenericEntity<Position> {
    public Position(Long id, String name) {
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
    public void update(Position s) {
        this.name = s.getName();
    }

    @Override
    public Position createNewInstance() {
        Position newInstance = new Position();
        newInstance.update(this);

        return newInstance;
    }
}
