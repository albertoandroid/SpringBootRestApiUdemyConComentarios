package com.example.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursos.dao.ILenguajeDao;
import com.example.cursos.entity.Lenguaje;

@Service
public class LenguajeService implements ILenguajeService{
	
	@Autowired
	private ILenguajeDao lenguajeDao;

	@Override
	@Transactional(readOnly = true) 
	public List<Lenguaje> findAll() {
		return (List<Lenguaje>) lenguajeDao.findAll(); 
	}
	

	@Override
	public void saveLenguaje(Lenguaje lenguaje) {
		lenguajeDao.save(lenguaje);
		
	}


	@Override
	public List<Lenguaje> getLenguajesProfesor(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Lenguaje findLenguajeByID(Long id) {
		return lenguajeDao.findByIdSQL(id);
	}
	
	
}