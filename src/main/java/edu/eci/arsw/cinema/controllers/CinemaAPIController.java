/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;

/**
 *
 * @author cristian
 */

@RestController
@RequestMapping(value = "/cinema")
public class CinemaAPIController {
	@Autowired
	CinemaServices cs;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() throws ResourceNotFoundException {
		try {
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(cs.getAllCinemas(), HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) throws CinemaPersistenceException, CinemaException {
		try {

			Cinema c = cs.getCinemaByName(name);
			//ArrayList<CinemaFunction> data = (ArrayList<CinemaFunction>) c.getFunctions();
			return new ResponseEntity<>(cs.getCinemaByName(name), HttpStatus.ACCEPTED);

		} catch (CinemaPersistenceException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No se encontró el recurso solicitado", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{name}/{date}")

	public ResponseEntity<?> getByNameAndDate(@PathVariable String name, @PathVariable String date)
			throws ResourceNotFoundException {
		try {
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name, date.replace("%20", " ")),
					HttpStatus.ACCEPTED);
		} catch (CinemaPersistenceException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error HTTP 404", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{name}/{date}/{moviename}")

	public ResponseEntity<?> getByNameAndDateAndName(@PathVariable String name, @PathVariable String date,
			@PathVariable String moviename) throws ResourceNotFoundException, CinemaException, CinemaPersistenceException {
		try {
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(
					cs.getFunctionsbyCinemaAndDateAndHour(name, date, moviename.replace("%20", " ")),
					HttpStatus.ACCEPTED);
		} catch (CinemaException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error HTTP 404", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/{name}")
	public ResponseEntity<?> Postbyfuntion(@PathVariable String name, @RequestBody CinemaFunction funtion) {
		try {
			cs.addfuntion(name, funtion);
			// registrar dato
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CinemaPersistenceException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error HTTP 404", HttpStatus.FORBIDDEN);
		}

	}

	@PutMapping("/{name}")
	public ResponseEntity<?> PostbyName(@PathVariable String name, @RequestBody CinemaFunction funtion)
			throws CinemaException {
		try {
			cs.UpdateFuntion(name, funtion);
			// registrar dato
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CinemaException ex) {
			Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error HTTP 404", HttpStatus.NOT_FOUND);
		}

	}
}
