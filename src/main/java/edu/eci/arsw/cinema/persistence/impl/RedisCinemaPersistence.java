package edu.eci.arsw.cinema.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import edu.eci.arsw.cinema.util.RedisMethods;

@Component("rcp")
public class RedisCinemaPersistence implements CinemaPersitence {
	
	public RedisCinemaPersistence() {
		String functionDate = "2018-12-18 15:30";
		String functionDate2 = "2018-12-18 15:30";
		List<CinemaFunction> functionsX = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		CinemaFunction funct3 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate2);
		CinemaFunction funct4 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"),functionDate);
		try {
		   //LOAD DATA FROM REDIS
		   funct1.setSeats(RedisMethods.getSeatsRedis("cinemaX",funct1));
		   funct2.setSeats(RedisMethods.getSeatsRedis("cinemaX",funct2));
		   funct3.setSeats(RedisMethods.getSeatsRedis("cinemaY",funct3));
		   funct4.setNumSeats(RedisMethods.getSeatsRedis("cinemaY",funct4));
		} catch (CinemaException ex) {
		   Logger.getLogger(RedisCinemaPersistence.class.getName()).log(Level.SEVERE, null, ex);
		}
		functionsX.add(funct1);
		functionsX.add(funct2);
		functionsX.add(funct3);
		functionsX.add(funct4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean buyTicket(int row, int col, String cinema, String date, String movieName)
			throws CinemaException, CinemaPersistenceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCinema(Cinema cinema) throws CinemaPersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cinema getCinema(String name) throws CinemaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CinemaFunction getFunctionsbyCinemaAndDateAndHour(String cinema, String date, String name)
			throws CinemaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addfuntion(String name, CinemaFunction funtion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateFuntion(String name, CinemaFunction funtion) throws CinemaException {
		// TODO Auto-generated method stub
		
	}

}
