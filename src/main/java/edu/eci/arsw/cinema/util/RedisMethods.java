package edu.eci.arsw.cinema.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class RedisMethods {
	public static void saveToREDIS(String key, String data) {
		Jedis jedis = JedisUtil.getPool().getResource();
		jedis.watch(key);
		Transaction t1 = jedis.multi();
		t1.set(key, data);
		t1.exec();
		jedis.close();

	}

	public static String getFromREDIS(String key) {
		boolean intentar = true;
		String content = "";
		while (intentar) {
			Jedis jedis = JedisUtil.getPool().getResource();
			jedis.watch(key);
			Transaction t = jedis.multi();
			Response<String> data = t.get(key);
			List<Object> result = t.exec();
			if (result.size() > 0) {
				intentar = false;
				content = data.get();
				jedis.close();
			}
		}
		return content;
	}

	public static List<List<Boolean>> buyTicketRedis(int row, int col, String cinema, CinemaFunction cinemafun) {
		String key = new StringBuffer().append(cinema).append(cinemafun.getDate()).append(cinemafun.getMovie().getName()).toString();
		ObjectMapper mapper = new ObjectMapper();
		String JsonSeats = getFromREDIS(key);
		try {
			List<List<Boolean>> seats = mapper.readValue(JsonSeats,mapper.getTypeFactory().constructCollectionType(ArrayList.class, ArrayList.class));
			cinemafun.setSeats(seats);
			cinemafun.buyTicket(row,col);
			return seats;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<List<Boolean>> getSeatsRedis( String cinema, CinemaFunction cinemafun) {
		String key = new StringBuffer().append(cinema).append(cinemafun.getDate()).append(cinemafun.getMovie().getName()).toString();
		ObjectMapper mapper = new ObjectMapper();
		String JsonSeats = getFromREDIS(key);
		try {
			List<List<Boolean>> seats = mapper.readValue(JsonSeats,mapper.getTypeFactory().constructCollectionType(ArrayList.class, ArrayList.class));
			cinemafun.setSeats(seats);
			return cinemafun.getSeats();

			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// saveToREDIS("this is test","this is values of the test");
		String functionDate = "2018-12-18 15:30";
		CinemaFunction cf = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		buyTicketRedis(1, 1, "cinemaX", cf);
		System.out.println(buyTicketRedis(1, 1, "cinemaX", cf));
		System.out.println("....//");
	}

}
