package com.idstar.lms.api.generic;

import com.idstar.lms.api.exceptions.EmployeeNotExistsException;
import com.idstar.lms.api.exceptions.InvalidInputParameterExceptions;
import com.idstar.lms.api.model.employees.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;

public abstract class GenericService<T extends GenericEntity<T>> {

    private final GenericRepo<T> repository;

    public GenericService(GenericRepo<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T get(Long id){
        if (id != null) {
            Optional<T> response = repository.findById(id);
            if (response.isPresent()) {
                return response.get();
            } else {
                throw new EmployeeNotExistsException(
                        " id " + id + " doesn't exist");
            }
        } else {
            throw new InvalidInputParameterExceptions("invalid  id");
        }
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);

        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Long id){
        get(id);
        repository.deleteById(id);
    }
}