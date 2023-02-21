package com.idstar.lms.api.generic;

import java.util.List;

public interface GenericEntity<T> {

    void update(T source);

    Long getId();

    T createNewInstance();

}
