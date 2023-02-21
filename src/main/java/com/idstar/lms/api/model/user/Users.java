package com.idstar.lms.api.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idstar.lms.api.generic.GenericEntity;
import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.model.master.Client;
import com.idstar.lms.api.model.master.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class Users implements Serializable, GenericEntity<Users> {
    public Users(String name, String username, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(targetEntity = Employees.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_to_employee",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    List<Employees> employees;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public void update(Users s) {
        this.name = s.getName();
        this.username = s.getUsername();
        this.password = s.getPassword();
        this.roles = s.getRoles();
    }

    @Override
    public Users createNewInstance() {
        Users newInstance = new Users();
        newInstance.update(this);

        return newInstance;
    }
}
