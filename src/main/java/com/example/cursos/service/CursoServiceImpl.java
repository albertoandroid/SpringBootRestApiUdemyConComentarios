package com.example.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cursos.dao.ICursoDao;
import com.example.cursos.entity.Curso;

@Service
public class CursoServiceImpl implements ICursoService {
	
	@Autowired
	private ICursoDao cursoDao;

	@Override
	@Transactional(readOnly = true) 
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	public void saveCurso(Curso curso) {
		cursoDao.save(curso);	
	}

	@Override
	public List<Curso> getCursosProfesor(Long id) {
		return (List<Curso>) cursoDao.findByProfesorId(id);
		
	}

}
