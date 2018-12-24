package com.example.cursos.service;

import java.util.List;
import java.util.Optional;

import com.example.cursos.entity.Profesor;

/*
 * Los servicios nos proporcionan la funcionalidad de negocio de nuestra App.
 */

public interface IProfesorService {

public List<Profesor> findAll();
	
	public void save(Profesor profesor);
	
	public Profesor findProfessor(Profesor profesor);
	
	public Profesor checkProfesorLogin(Profesor profesor);
	
	public void deleteProfesor(Profesor profesor);

	void delete(Profesor profesor);
	
	public Profesor updateProfesor(Profesor profesor);
	
	public Optional<Profesor> findProfesorById(Long profesor_id);
	
	public void deleteProfesor(Long id);
	
	public Profesor findById(Long id);
	
	public Profesor findByIdSQL(Long id);
	
	public void updateProfesor(Long id);
}
