package com.fdm.JSP.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdm.JSP.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>{
	
	List<Rental> findByUser_idEquals(int id);
}
