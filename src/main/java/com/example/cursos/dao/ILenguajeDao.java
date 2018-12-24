package com.example.cursos.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.cursos.entity.Lenguaje;


public interface ILenguajeDao extends CrudRepository<Lenguaje, Long> {

	
	@Query("select p from Lenguaje p where p.id=?1")
	public Lenguaje findByIdSQL(Long id);
}
