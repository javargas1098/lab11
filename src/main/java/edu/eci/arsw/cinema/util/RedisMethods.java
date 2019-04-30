package edu.eci.arsw.cinema.util;

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
				// Cerrar recurso jedis
				jedis.close();
			}
		}
		return content;
	}



	public static void main(String[] args) {
		// saveToREDIS("this is test","this is values of the test");
		System.out.println(getFromREDIS("this is test"));
		System.out.println("....//");
	}

}
