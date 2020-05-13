package org.sid.services.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author RAZINE Anass
 *
 * @param <T>
 */
@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	protected abstract JpaRepository<T, Long> getDao();

    @Override
    @Transactional(readOnly = true)
    public T findById(final long id) {
        return getDao().findById(id).orElse(null);
    }

    // read - all

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
//        return Lists.newArrayList(getDao().findAll());
        return (List<T>) getDao().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findPaginated(final int page, final int size) {
        return getDao().findAll(PageRequest.of(page, size));
    }

    // write
    
    @Override
    public T save(final T entity) {
        return getDao().save(entity);
    }
    
    @Override
    public T update(final T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        getDao().deleteById(entityId);
    }

}
