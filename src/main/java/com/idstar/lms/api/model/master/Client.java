package com.idstar.lms.api.model.master;

import com.idstar.lms.api.generic.GenericEntity;
import com.idstar.lms.api.model.employees_client.EmployeesClient;
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
@Table(name = "client")
public class Client implements Serializable, GenericEntity<Client> {
    public Client(Long id, String name) {
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
    public void update(Client s) {
        this.name = s.getName();
    }

    @Override
    public Client createNewInstance() {
        Client newInstance = new Client();
        newInstance.update(this);

        return newInstance;
    }
}
