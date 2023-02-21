package com.idstar.lms.api.controller.master;

import com.idstar.lms.api.generic.GenericController;
import com.idstar.lms.api.model.master.Position;
import com.idstar.lms.api.repositories.master.PositionRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/position")
public class PositionController extends GenericController<Position> {

    public PositionController(PositionRepo s) {
        super(s);
    }
}