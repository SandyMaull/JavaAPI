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
@Table(name = "grade")
public class Grade implements Serializable, GenericEntity<Grade> {
    public Grade(Long id, String name) {
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
    public void update(Grade s) {
        this.name = s.getName();
    }

    @Override
    public Grade createNewInstance() {
        Grade newInstance = new Grade();
        newInstance.update(this);

        return newInstance;
    }
}
