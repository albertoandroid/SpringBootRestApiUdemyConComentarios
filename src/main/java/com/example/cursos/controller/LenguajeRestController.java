package com.example.cursos.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursos.Model.ProfesorLenguaje;
import com.example.cursos.entity.Lenguaje;
import com.example.cursos.entity.Profesor;
import com.example.cursos.service.ILenguajeService;
import com.example.cursos.service.IProfesorService;

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
public class LenguajeRestController {
	
	@Autowired
	private ILenguajeService lenguajeService;
	
	@Autowired
	private IProfesorService profesorService;
	
	
	@GetMapping("/lenguajes") //Indicamos la url endpoint
	public ResponseEntity<?> listaLenguajes() {
		List<Lenguaje> listaLenguajes = lenguajeService.findAll();
		if(listaLenguajes != null) {
			if(listaLenguajes.size()!=0) {
				return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/crear_lenguaje") 
	public ResponseEntity<?> agregarLenguaje(@RequestBody Lenguaje lenguaje) {
		lenguajeService.saveLenguaje(lenguaje);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("/lenguajes_profesor") //Indicamos la url endpoint
	public ResponseEntity<?> listaLenguajesProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findById(profesor.getId());
		if(profesorDb != null) {
			Collection<Lenguaje> listaLenguajes = profesorDb.getLenguajes();
			if(listaLenguajes != null) {
				if(listaLenguajes.size()!=0) {
					return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/save_lenguajes_profesor") //Indicamos la url endpoint
	public ResponseEntity<?> saveLenguajeProfesor(@RequestBody ProfesorLenguaje profesorLenguaje) {
		Profesor profesorDb = profesorService.findById(profesorLenguaje.getProfesor().getId());
		if(profesorDb != null) {
			Lenguaje lenguajeDb = lenguajeService.findLenguajeByID(profesorLenguaje.getLenguaje().getId());
			profesorDb.addLenguaje(lenguajeDb);
			profesorService.save(profesorDb);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}


}
