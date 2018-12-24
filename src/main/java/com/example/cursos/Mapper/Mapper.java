package com.example.cursos.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cursos.Model.MProfesor;
import com.example.cursos.entity.Profesor;

//Convertimos una Lista de Nota en un a Lista de MNotas
@Component("mapper")
public class Mapper {
	
	
	public static List<MProfesor> convertirLista(List<Profesor> profesores){
		List<MProfesor> mProfesores = new ArrayList<>();
		
		for(Profesor profesor: profesores) {
			mProfesores.add(new MProfesor(profesor));
		}
	
		return mProfesores;
	}
	

}

