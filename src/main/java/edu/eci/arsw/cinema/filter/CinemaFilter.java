package edu.eci.arsw.cinema.filter;

import java.util.List;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.FilterException;

public interface CinemaFilter {

	public List<CinemaFunction> getFunctionsbyGen(String cinema, String gen, String date, CinemaPersitence cps)
			throws CinemaException, FilterException;

	public List<CinemaFunction> getFunctionsbySeats(String cinName, int seats, String date, CinemaPersitence cps)
			throws CinemaException, FilterException;

}
