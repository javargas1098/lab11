/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author cristian
 */
public class Cinema {
	private AtomicReference<String> name = new AtomicReference<String>();
	private List<CinemaFunction> functions = Collections.synchronizedList(new ArrayList<CinemaFunction>());

	public Cinema() {
	}

	public Cinema(String name, List<CinemaFunction> functions) {
		this.setName(name);
		this.functions = functions;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void Addfuntion(CinemaFunction funtion) {
		functions.add(funtion);

	}

	public boolean Updatefuntion(CinemaFunction funtion) {
		for (CinemaFunction cinemaFunction : functions) {
			if (cinemaFunction.getMovie().getName().equals(funtion.getMovie().getName())) {
				cinemaFunction.setFunction(funtion);
				return true;
			}
		}
		return false;

	}

	public List<CinemaFunction> getFunctions() {
		return this.functions;
	}

	public void setSchedule(List<CinemaFunction> functions) {
		this.functions = functions;
	}
}
