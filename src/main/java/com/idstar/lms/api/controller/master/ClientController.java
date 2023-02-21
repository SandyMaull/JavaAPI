package com.idstar.lms.api.controller.master;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.master.Client;
import com.idstar.lms.api.repositories.master.ClientRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("master/client")
public class ClientController extends GenericController<Client> {

    public ClientController(ClientRepo s) {
        super(s);
    }
}