/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.eci.arsw.cinema.controllers.CinemaAPIController;
import edu.eci.arsw.cinema.services.CinemaServices;

/**
 *
 * @author JAvier gas Sebastian Goenaga
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CinemaAPIController.class)
public class ApplicationServicesTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getAllCinemasTest() throws Exception {

		mvc.perform(get("/cinema")).andExpect(status().isAccepted());

	}

	@Test
	public void getByNameTest() throws Exception {

		mvc.perform(get("/cinema/cinemaY")).andExpect(status().isAccepted());
		mvc.perform(get("/cinema/cinemaZ")).andExpect(status().isNotFound());

	}

	@Test
	public void getByDateTest() throws Exception {

		mvc.perform(get("/cinema/cinemaX/2018-12-18")).andExpect(status().isAccepted());

	}

	@Test
	public void getByDateAndNameTest() throws Exception {

		mvc.perform(get("/cinema/cinemaX/2018-12-18 15:30/The Night")).andExpect(status().isAccepted());
		mvc.perform(get("/cinema/cinemaZ/2018-12-18 15:30/The Night")).andExpect(status().isNotFound());

	}

	@Test
	public void postFunctionTest() throws Exception {

		mvc.perform(post("/cinema/cinemaX").contentType(MediaType.APPLICATION_JSON).content(
				"{\"movie\":{\"name\":\"NOMBRE PRUEBA\",\"seats\":0,\"genre\":\"Horror\"},\"seats\":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],\"date\":\"2018-12-18\",\"hour\":\"15:30\",\"numSeats\":84,\"gen\":\"Horror\"}"))
				.andExpect(status().isCreated());

	}

	@Test
	public void updateFunctionTest() throws Exception {

		mvc.perform(put("/cinema/cinemaX").contentType(MediaType.APPLICATION_JSON).content(
				"{\"movie\":{\"name\":\"The Night\",\"seats\":0,\"genre\":\"DEBERIA ACTUALIZAR EL GENERO\"},\"seats\":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],\"date\":\"2018-12-18\",\"hour\":\"15:30\",\"numSeats\":84,\"gen\":\"Horror\"}"))
				.andExpect(status().isCreated());

		mvc.perform(put("/cinema/cinemaX").contentType(MediaType.APPLICATION_JSON).content(
				"{\"movie\":{\"name\":\"NO DEBERIA ENCONTRAR ESTE NOMBRE\",\"seats\":0,\"genre\":\"Horror\"},\"seats\":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],\"date\":\"2018-12-18\",\"hour\":\"15:30\",\"numSeats\":84,\"gen\":\"Horror\"}"))
				.andExpect(status().isNotFound());

	}

}