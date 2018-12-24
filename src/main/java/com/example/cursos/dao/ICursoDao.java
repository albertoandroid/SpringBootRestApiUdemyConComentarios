package com.example.cursos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.cursos.entity.Curso;

public interface ICursoDao extends CrudRepository<Curso, Long> {
	
	public List<Curso> findByProfesorId(Long id);

}
