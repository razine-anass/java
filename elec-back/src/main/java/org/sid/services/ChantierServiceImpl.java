package org.sid.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sid.entities.Chantier;
import org.sid.repositories.ChantierRepository;
import org.sid.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChantierServiceImpl extends AbstractService<Chantier> implements ChantierService{
	
	
	@PersistenceContext
	EntityManager entityManager;
	 
	@Autowired
	ChantierRepository chantierRepository;
	
	@Override
    protected JpaRepository<Chantier, Long> getDao() {
        return chantierRepository;
    }

}
