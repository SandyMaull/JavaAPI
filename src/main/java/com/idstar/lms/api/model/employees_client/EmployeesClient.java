package com.idstar.lms.api.model.employees_client;

import com.idstar.lms.api.generic.GenericEntity;
import com.idstar.lms.api.model.employees.Employees;
import com.idstar.lms.api.model.master.Client;
import com.idstar.lms.api.model.master.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "karyawan_client")
public class EmployeesClient implements Serializable, GenericEntity<EmployeesClient> {
    public EmployeesClient(Status ps_status, Employees sales, Client placement, Client client, String project_name, String pic_timesheet_d2d, String pic_contact_hp, String pic_email, String cutoff_timesheet, String timesheet_note, Date start_contract, Date end_contract, long idle_alert, boolean perpanjangan, Client status_project, long durasi_perpanjangan, Employees karyawan) {
        this.ps_status = ps_status;
        this.sales = sales;
        this.placement = placement;
        this.client = client;
        this.project_name = project_name;
        this.pic_timesheet_d2d = pic_timesheet_d2d;
        this.pic_contact_hp = pic_contact_hp;
        this.pic_email = pic_email;
        this.cutoff_timesheet = cutoff_timesheet;
        this.timesheet_note = timesheet_note;
        this.start_contract = start_contract;
        this.end_contract = end_contract;
        this.idle_alert = idle_alert;
        this.perpanjangan = perpanjangan;
        this.status_project = status_project;
        this.durasi_perpanjangan = durasi_perpanjangan;
        this.karyawan = karyawan;
    }

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status ps_status;

    @OneToOne
    @JoinColumn(name = "sales_id", referencedColumnName = "id")
    private Employees sales;

    @OneToOne
    @JoinColumn(name = "placement_id", referencedColumnName = "id")
    private Client placement;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private String project_name;
    private String pic_timesheet_d2d;

    private String pic_contact_hp;
    private String pic_email;

    private String cutoff_timesheet;
    private String timesheet_note;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date start_contract;

    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date end_contract;

    private long idle_alert;

    private boolean perpanjangan;

    @OneToOne
    @JoinColumn(name = "status_project_id", referencedColumnName = "id")
    private Client status_project;

    private long durasi_perpanjangan;

    @OneToOne
    @JoinColumn(name = "karyawan_id", referencedColumnName = "id")
    private Employees karyawan;

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    public void update(EmployeesClient s) {
        this.ps_status = s.getPs_status();
        this.sales = s.getSales();
        this.placement = s.getPlacement();
        this.client = s.getClient();
        this.project_name = s.getProject_name();
        this.pic_timesheet_d2d = s.getPic_timesheet_d2d();
        this.pic_contact_hp = s.getPic_contact_hp();
        this.pic_email = s.getPic_email();
        this.cutoff_timesheet = s.getCutoff_timesheet();
        this.timesheet_note = s.getTimesheet_note();
        this.start_contract = s.getStart_contract();
        this.end_contract = s.getEnd_contract();
        this.idle_alert = s.getIdle_alert();
        this.perpanjangan = s.isPerpanjangan();
        this.status_project = s.getStatus_project();
        this.durasi_perpanjangan = s.getDurasi_perpanjangan();
        this.karyawan = s.getKaryawan();
    }

    @Override
    public EmployeesClient createNewInstance() {
        EmployeesClient newInstance = new EmployeesClient();
        newInstance.update(this);

        return newInstance;
    }


}
