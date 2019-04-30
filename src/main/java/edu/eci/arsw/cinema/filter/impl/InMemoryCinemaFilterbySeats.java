package edu.eci.arsw.cinema.filter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.FilterException;


//@Component("cfs")
public class InMemoryCinemaFilterbySeats implements CinemaFilter {
	

	

	@Override
	public List<CinemaFunction> getFunctionsbySeats(String cinName, int seats, String date,CinemaPersitence pelicula) throws  CinemaException {
		// TODO Auto-generated method stub
		List<CinemaFunction> cine = pelicula.getCinema(cinName).getFunctions();
		List<CinemaFunction> fun = new ArrayList<>();
		for (CinemaFunction funtion : cine) {
//			System.out.println(funtion.getNumSeats());
//			System.out.println(seats);
			if (funtion.getNumSeats() >= seats && funtion.getDate().equals(date)) {
				fun.add(funtion);

			}
		}
		return fun;
	}

	@Override
	public List<CinemaFunction> getFunctionsbyGen(String cinema, String gen, String date, CinemaPersitence cps)
			throws FilterException {
		// TODO Auto-generated method stub
		throw new FilterException("no es valido este filtro");
	}



}
