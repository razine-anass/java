package org.sid.repositories;

import org.sid.entities.Customer;
import org.springframework.data.repository.CrudRepository;
 
public interface CustomerRepository extends CrudRepository<Customer, Long>{}