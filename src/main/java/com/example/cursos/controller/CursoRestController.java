package com.example.cursos.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursos.entity.Curso;
import com.example.cursos.entity.Profesor;
import com.example.cursos.service.ICursoService;

/*
 * Controller -> Indicamos que una clase es el controlador de
 * solicitudes web http.
 * En nuestro caso que estamos creando un Api Rest tendremos que untilizar
 * la etiqueta rescontroller.
 * Es decir es la clase que nos permite hacer nuestras solicitudes
 * para servicios web Restful.
 */

@RestController
@RequestMapping("/api") // Le decimos la url
public class CursoRestController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ICursoService cursoService;


	@GetMapping("/cursos") //Indicamos la url endpoint
	public ResponseEntity<?> listaCursos() {
		List<Curso> listaCursos = cursoService.findAll();
		if(listaCursos!=null) {
			if(listaCursos.size()!=0) {
				return new ResponseEntity<>(listaCursos, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/crear_curso")
	public ResponseEntity<?> agregarCurso(@RequestBody Curso curso) {
		cursoService.saveCurso(curso);
	 
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PostMapping("/cursosProfesor")
	public ResponseEntity<?> verCursosProfesor(@RequestBody Profesor profesor) {
		List<Curso> listaCursos = cursoService.getCursosProfesor(profesor.getId());
		if(listaCursos!=null) {
			if(listaCursos.size()!=0) {
				return new ResponseEntity<>(listaCursos, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
}
