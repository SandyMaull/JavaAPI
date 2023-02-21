package com.idstar.lms.api.controller.master;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.master.Company;
import com.idstar.lms.api.repositories.master.CompanyRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/company")
public class CompanyController extends GenericController<Company> {

    public CompanyController(CompanyRepo s) {
        super(s);
    }
}