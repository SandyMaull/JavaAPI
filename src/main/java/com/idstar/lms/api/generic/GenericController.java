package com.idstar.lms.api.generic;

import com.idstar.lms.api.constants.ResponseMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<T extends GenericEntity<T>> {

    private final GenericService<T> service;

    public GenericController(GenericRepo<T> repository) {
        this.service = new GenericService<T>(repository) {};
    }

    @GetMapping("")
    @ApiOperation("Get Pagination from this Service")
    public ResponseEntity<ResponseMapper> getPage(Pageable pageable){
        return ResponseEntity.ok().body(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", service.getPage(pageable)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Show Data by ID from this Service")
    public ResponseEntity<ResponseMapper> getOne(@PathVariable Long id){
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", service.get(id)));
    }

    @PutMapping("")
    @ApiOperation("Update data by ID from this Service")
    public ResponseEntity<ResponseMapper> update(@RequestBody T updated){
        return ResponseEntity.ok(new ResponseMapper(HttpStatus.OK.value(),
                "SUCCESS", service.update(updated)));
    }

    @PostMapping("")
    @ApiOperation("Add data to this Service")
    public ResponseEntity<ResponseMapper> create(@RequestBody T created){
        return ResponseEntity.ok().body(
                new ResponseMapper(HttpStatus.CREATED.value(),
                        "SUCCESS",
                        service.create(created)
                )
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete data by ID from this Service")
    public ResponseEntity<ResponseMapper> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().body(
                new ResponseMapper(HttpStatus.OK.value(),
                        "SUCCESS",
                        "Data Successfully Deleted"
                )
        );
    }
}