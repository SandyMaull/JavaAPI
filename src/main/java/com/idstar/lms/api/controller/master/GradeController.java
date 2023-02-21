package com.idstar.lms.api.controller.master;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.master.Grade;
import com.idstar.lms.api.repositories.master.GradeRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/grade")
public class GradeController extends GenericController<Grade> {

    public GradeController(GradeRepo s) {
        super(s);
    }
}