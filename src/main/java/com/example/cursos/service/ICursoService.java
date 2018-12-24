package com.example.cursos.service;

import java.util.List;

import com.example.cursos.entity.Curso;

public interface ICursoService {
	
    public List<Curso> findAll();
	
	public void saveCurso(Curso curso);
	
	public List<Curso> getCursosProfesor(Long id);

}
