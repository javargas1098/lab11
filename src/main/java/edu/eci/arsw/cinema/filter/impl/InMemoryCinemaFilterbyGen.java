package edu.eci.arsw.cinema.filter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.FilterException;

@Component("cfs")
public class InMemoryCinemaFilterbyGen implements CinemaFilter {

	@Override
	public List<CinemaFunction> getFunctionsbyGen(String cinema, String gen, String date, CinemaPersitence cps)
			throws CinemaException, FilterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CinemaFunction> getFunctionsbySeats(String cinName, int seats, String date, CinemaPersitence cps)
			throws CinemaException, FilterException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<CinemaFunction> getFunctionsbyGen(String cinema, String gen, String date,CinemaPersitence pelicula) throws CinemaException {
//		List<CinemaFunction> cine = pelicula.getCinema(cinema).getFunctions();
//		List<CinemaFunction> fun = new ArrayList<>();
//		for (CinemaFunction cin : cine) {
//			if (cin.getGen().equals(gen) && cin.getDate().equals(date)) {
//				fun.add(cin);
//
//			}
//		}
//		return fun;
//	}
//
//	@Override
//	public List<CinemaFunction> getFunctionsbySeats(String cinName, int seats, String date, CinemaPersitence cps)
//			throws FilterException {
//		// TODO Auto-generated method stubs
//		
//		
//		throw new FilterException("no es valido este filtro");
//	}

	

}
