package com.example.cursos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursos.Mapper.Mapper;
import com.example.cursos.Model.MProfesor;
import com.example.cursos.entity.Profesor;
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
public class ProfesorRestController {
	
	
	@Autowired
	private IProfesorService profesorService;
	

	@GetMapping("/profesores") //Indicamos la url endpoint
	@ResponseStatus(HttpStatus.OK) //Cambiamos el codigo a 200 que es ok
	public List<Profesor> getProfesores() {
		return profesorService.findAll();
	}
	
	@PostMapping("/sing_up")
	public ResponseEntity<Void> addProfesor(@RequestBody Profesor profesor) {
		if(profesorService.findProfessor(profesor)==null) {
			profesorService.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.checkProfesorLogin(profesor);
		if(profesorDb!=null) {
			List<Profesor> profesores = new ArrayList<>();
			profesores.add(profesorDb);
			List<MProfesor> mProfesores = new ArrayList<>();
			mProfesores = Mapper.convertirLista(profesores);
			return new ResponseEntity<>(mProfesores, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Void> deleteProfesor(@RequestBody Profesor profesor) {
		//Si profesor no Existe
		if(profesorService.findProfessor(profesor)!=null) {
			//Borramos profesor
			profesorService.delete(profesor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfesor(@PathVariable("id") long id) {
		
		profesorService.deleteProfesor(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findById(profesor.getId());
		if(profesorDb != null) {
			profesor.setPassword(profesorDb.getPassword());
			profesor.setCreateAt(profesorDb.getCreateAt());
			this.profesorService.save(profesor);
			List<Profesor> profesores = new ArrayList<>();
			profesores.add(profesorDb);
			return new ResponseEntity<>(profesor, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update_sql")
	public ResponseEntity<?> updateProfesorSql(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findByIdSQL(profesor.getId());
		if(profesorDb != null) {
			profesor.setPassword(profesorDb.getPassword());
			profesor.setCreateAt(profesorDb.getCreateAt());
			this.profesorService.save(profesor);
			List<Profesor> profesores = new ArrayList<>();
			profesores.add(profesorDb);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/find_professor")
	public ResponseEntity<?> findProfesor(@RequestBody Profesor profesor) {
		Profesor profesorDb = profesorService.findProfessor(profesor);
		if(profesorDb != null) {
			return new ResponseEntity<>(profesor, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProfesor(@PathVariable(value="id") Long id, @RequestBody Profesor profesor){
		Profesor profesorDb = null;
		profesorDb = profesorService.findById(id);
		if(profesorDb != null) {
			profesorDb.setEmail(profesor.getEmail());
			profesorDb.setNombre(profesor.getNombre());
			this.profesorService.updateProfesor(profesorDb);
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
