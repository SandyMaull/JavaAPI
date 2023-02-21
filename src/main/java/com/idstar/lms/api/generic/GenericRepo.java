package com.idstar.lms.api.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepo<T extends GenericEntity<T>> extends JpaRepository<T, Long> {
}