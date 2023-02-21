package com.idstar.lms.api.model.employees;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idstar.lms.api.generic.GenericEntity;
import com.idstar.lms.api.model.user.Users;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.idstar.lms.api.model.master.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "karyawan")
public class Employees implements Serializable, GenericEntity<Employees> {
    public Employees(Company talent_company, String nik, String name, String contact_email, Position position, Users tl, Status status, Date first_join_date, Date start_contract, Date end_contract) {
        this.talent_company = talent_company;
        this.nik = nik;
        this.name = name;
        this.contact_email = contact_email;
        this.position = position;
        this.tl = tl;
        this.status = status;
        this.first_join_date = first_join_date;
        this.start_contract = start_contract;
        this.end_contract = end_contract;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company talent_company;

    private String nik;
    private String name;
    private String contact_email;

    @OneToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @OneToOne
    @JoinColumn(name = "tl_id", referencedColumnName = "id")
    private Users tl;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date first_join_date;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date start_contract;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date end_contract;

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public void update(Employees s) {
        this.talent_company = s.getTalent_company();
        this.nik = s.getNik();
        this.name = s.getName();
        this.contact_email = s.getContact_email();
        this.position = s.getPosition();
        this.tl = s.getTl();
        this.status = s.getStatus();
        this.first_join_date = s.getFirst_join_date();
        this.start_contract = s.getStart_contract();
        this.end_contract = s.getEnd_contract();
    }

    @Override
    public Employees createNewInstance() {
        Employees newInstance = new Employees();
        newInstance.update(this);

        return newInstance;
    }


}

