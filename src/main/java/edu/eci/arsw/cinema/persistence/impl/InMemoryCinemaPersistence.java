/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.internal.util.privilegedactions.GetMethodFromPropertyName;
import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component("cps")
public class InMemoryCinemaPersistence implements CinemaPersitence {

	private final Map<String, Cinema> cinemas = new HashMap<>();

	public InMemoryCinemaPersistence() {
		// load stub data
		String functionDate = "2018-12-18 15:30";
		String functionDate2 = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("cinemaX", functions);
		cinemas.put("cinemaX", c);
		functions = new ArrayList<>();
		CinemaFunction funct3 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate2);
		CinemaFunction funct4 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate2);
		functions.add(funct3);
		functions.add(funct4);
		Cinema c1 = new Cinema("cinemaY", functions);
		cinemas.put("cinemaY", c1);
	}

	@Override
	public boolean buyTicket(int row, int col, String cinema, String date, String movieName)
			throws CinemaException, CinemaPersistenceException {
		Cinema cine = cinemas.get(cinema);
		for (CinemaFunction funtion : cine.getFunctions()) {

			if (funtion.getMovie().getName().equals(movieName) && funtion.getDate().equals(date)) {

				try {
					funtion.buyTicket(row, col);
					return true;
				} catch (CinemaException e) {
					throw new CinemaPersistenceException(e.getMessage());
				}

			}

		}
		return false;

	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		Cinema cine = cinemas.get(cinema);
		List<CinemaFunction> fun = new ArrayList<>();
		for (CinemaFunction funtion : cine.getFunctions()) {
			if (funtion.getDate().equals(date)) {
				fun.add(funtion);

			}
		}
		return fun;

	}

	@Override
	public CinemaFunction getFunctionsbyCinemaAndDateAndHour(String cinema, String date, String nombre) throws CinemaException {
		Cinema cine = cinemas.get(cinema);
		if (cine != null) {
			for (CinemaFunction funtion : cine.getFunctions()) {
				if (funtion.getDate().equals(date.split(" ")[0]) && funtion.getHour().equals(date.split(" ")[1])
						&& funtion.getMovie().getName().equals(nombre)) {
					return funtion;

				}
			}
		}
		throw new CinemaException("The given function to update does not exit");

	}



	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		try {
			if (cinemas.containsKey(c.getName())) {
				throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
			} else {
				cinemas.put(c.getName(), c);
			}
		} catch (NullPointerException e) {
			throw new CinemaPersistenceException("The given name does not exit");

		}
	}
	@Override
	public Cinema getCinema(String name) throws CinemaException {
		if ( cinemas.get(name) == null) {
			throw new CinemaException("The given function to update does not exit");
		}else {
			return cinemas.get(name);
		}
	}

	@Override
	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
		// TODO Auto-generated method stub
		Set<Cinema> allCinemas = new HashSet<Cinema>();
		for (Map.Entry<String, Cinema> entry : cinemas.entrySet()) {
			allCinemas.add(entry.getValue());
		}
		return allCinemas;
	}

	@Override
	public void addfuntion(String name, CinemaFunction funtion) {
		// TODO Auto-generated method stub
		Cinema cine = cinemas.get(name);
		cine.Addfuntion(funtion);

	}

	@Override
	public void UpdateFuntion(String name, CinemaFunction funtion) throws CinemaException {
		// TODO Auto-generated method stub
		Cinema cine = cinemas.get(name);
		if (!cine.Updatefuntion(funtion)) {
			throw new CinemaException("No se puede actualizar");

		}

	}

}
