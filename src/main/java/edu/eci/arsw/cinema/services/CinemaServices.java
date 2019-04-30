/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.persistence.FilterException;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
	@Autowired
	CinemaPersitence cps;

	@Autowired
	CinemaFilter cfs;

	public void addNewCinema(Cinema c) throws CinemaPersistenceException {
		cps.saveCinema(c);
	}
	

	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
		return cps.getAllCinemas();
	}

	/**
	 * 
	 * @param name
	 *            cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 */
	public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException {
		try {
			return cps.getCinema(name);
		} catch (CinemaException  e) {
			throw new CinemaPersistenceException("The given name does not exit");
		}

	}


	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException,CinemaPersistenceException {
		cps.buyTicket(row, col, cinema, date, movieName);

	}

	public List<CinemaFunction> getFuntionbyGen(String cinema, String gen, String date)
			throws CinemaException, CinemaPersistenceException, FilterException {
		return cfs.getFunctionsbyGen(cinema, gen, date, cps);

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException  {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);

	}

	public List<CinemaFunction> getFuntionbyNumberofSeats(String cinName, int sillas, String date)
			throws CinemaException, CinemaPersistenceException, FilterException {
		// TODO Auto-generated method stub
		return cfs.getFunctionsbySeats(cinName, sillas, date, cps);
	}

	public CinemaFunction getFunctionsbyCinemaAndDateAndHour(String name, String date, String moviename) throws CinemaPersistenceException, CinemaException {
		// TODO Auto-generated method stub
		
		try {
			return cps.getFunctionsbyCinemaAndDateAndHour(name, date,  moviename);
		} catch (CinemaException e) {
			throw new CinemaException("The given name does not exit");}
	}

	public void addfuntion(String name, CinemaFunction funtion) throws CinemaPersistenceException{
		// TODO Auto-generated method stub
		cps.addfuntion(name,funtion);
	}
	public void UpdateFuntion(String name, CinemaFunction funtion) throws  CinemaException{
		// TODO Auto-generated method stub
		cps.UpdateFuntion(name,funtion);
	}
}
