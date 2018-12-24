package com.example.cursos.service;

import java.util.List;

import com.example.cursos.entity.Lenguaje;

public interface ILenguajeService {
	
	public List<Lenguaje> findAll();
	
	public void saveLenguaje(Lenguaje lenguaje);
	
	public List<Lenguaje> getLenguajesProfesor(Long id);
	
	public Lenguaje findLenguajeByID(Long id);

}
