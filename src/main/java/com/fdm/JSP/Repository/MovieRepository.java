package com.fdm.JSP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.JSP.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
