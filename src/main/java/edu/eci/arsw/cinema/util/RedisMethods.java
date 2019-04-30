package edu.eci.arsw.cinema.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.eci.arsw.cinema.model.CinemaFunction;
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

	public static List<List<Boolean>> buyTicketRedis(String key) {
		String tickets = getFromREDIS(key);
		String str[] = tickets.split("],");
		List<String> al = new ArrayList<String>();
		al = Arrays.asList(str);
		System.out.println(al);
		return null;

	}

	public static void main(String[] args) {
		// saveToREDIS("this is test","this is values of the test");
		System.out.println(buyTicketRedis("cinemaY2018-12-18 15:30The Enigma"));
		System.out.println("....//");
	}

}
