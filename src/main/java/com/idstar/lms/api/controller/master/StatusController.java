package com.idstar.lms.api.controller.master;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.master.Status;
import com.idstar.lms.api.repositories.master.StatusRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/status")
public class StatusController extends GenericController<Status> {

    public StatusController(StatusRepo s) {
        super(s);
    }
}