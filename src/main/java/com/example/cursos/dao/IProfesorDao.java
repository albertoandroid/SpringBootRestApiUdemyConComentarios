package com.example.cursos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.cursos.entity.Profesor;

/*
 * DAO -> Data Access Object. Objeto de acceso a datos.
 * 
 * El patrón Dao nos permite abstraer y emcapsular el acceso a los datos
 * de una base de datos. Independientemente de como hayamos guardado esos
 * datos. Es decir pueden ser en mySql, noSQL. etc.
 * 
 * El patrón DAO maneja la conesión con la fuente de datos para obtener
 * y guardar los datos.
 */

public interface IProfesorDao extends CrudRepository<Profesor, Long>{
	
	public Profesor findByEmail(String email);
	
	public Profesor findByEmailAndPassword(String email, String password);
	
	public Optional<Profesor> findById(Long id);
	
	@Query("select p from Profesor p where p.id=?1")
	public Profesor findByIdSQL(Long id);


}