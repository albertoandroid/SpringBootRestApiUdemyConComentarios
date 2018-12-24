package com.example.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursos.dao.IProfesorDao;
import com.example.cursos.entity.Profesor;

/*
 * Los servicios nos proporcionan la funcionalidad de negocio de nuestra App.
 */

@Service
public class ProfesorServiceImpl implements IProfesorService {
	
	@Autowired
	private IProfesorDao profesorDao;

	@Override
	@Transactional(readOnly = true)  //Manejar transacción en este caso solo lectura
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);
		
	}
	
	@Override
	@Transactional(readOnly = true)  //Manejar transacción en este caso solo lectura
	public Profesor findProfessor(Profesor profesor) {
		return (Profesor) profesorDao.findByEmail(profesor.getEmail());
	}
	
	@Override
	@Transactional(readOnly = true)  //Manejar transacción en este caso solo lectura
	public Profesor checkProfesorLogin(Profesor profesor) {
		return (Profesor) profesorDao.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
	}
	
	@Override
	@Transactional
	public void delete(Profesor profesor) {
		profesorDao.delete(profesor);
	}
	
	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return (Profesor) profesorDao.save(profesor);
	}

	@Override
	public void deleteProfesor(Profesor profesor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)  //Manejar transacción en este caso solo lectura
	public Optional<Profesor> findProfesorById(Long id) {
		// TODO Auto-generated method stub
		return (Optional<Profesor>) profesorDao.findById(id);
	}
	
	@Override
	@Transactional
	public void deleteProfesor(Long id) {
		profesorDao.deleteById(id);
	}

	@Override
	public Profesor findById(Long id) {
		return profesorDao.findById(id).orElse(null);
		
	}

	@Override
	public Profesor findByIdSQL(Long id) {
		return profesorDao.findByIdSQL(id);
	}

	@Override
	public void updateProfesor(Long id) {
		//profesorDao.save(entity);
		
	}

}
