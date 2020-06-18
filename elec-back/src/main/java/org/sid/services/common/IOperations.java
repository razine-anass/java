package org.sid.services.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IOperations<T extends Serializable> {

    // read - one

    T findById(final long id) throws Exception;

    // read - all

    List<T> findAll() throws Exception;

    Page<T> findPaginated(int page, int size);

    // write

    T save(final T entity) throws Exception;

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId) throws Exception;
}