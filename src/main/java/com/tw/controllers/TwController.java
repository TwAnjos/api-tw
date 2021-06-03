package com.tw.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.model.Tw;
import com.tw.services.interfaces.iTwService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tw")
public class TwController {

	/*
	 * Swagger utiliza apenas esses retornos 200 = OK 201 = CREATED 204 = NO_CONTENT
	 * 401 = UNAUTHORIZED 403 = FORBIDDEN 404 = NOT_FOUND
	 */

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${tw.rds1}")
	private String rds1;
	
	@Value("${tw.rds2}")
	private String rds2;
	
	@Value("${tw.rds3}")
	private String rds3;

	@Value("${tw.rds4}")
	private String rds4;

	@Value("${tw.rds5}")
	private String rds5;

	@Value("${tw.rds6}")
	private String rds6;

	@Value("${tw.oquevemdaqui1}")
	private String oquevemdaqui1;

	@Value("${tw.oquevemdaqui2}")
	private String oquevemdaqui2;

	@Value("${tw.oquevemdaqui3}")
	private String oquevemdaqui3;

	@Autowired
	iTwService iTwService;

	@GetMapping(value = "/VariaveisDeAmbiente", produces = "application/json")
	public List<String> VariaveisDeAmbiente() {

		List<String> ListVariaveis = new ArrayList<String>();

		ListVariaveis.add("JAVA_HOME = " + System.getenv("JAVA_HOME"));
		ListVariaveis.add("RDS_DB_NAME = " + System.getenv("RDS_DB_NAME"));
		ListVariaveis.add("RDS_USERNAME = " + System.getenv("RDS_USERNAME"));
		ListVariaveis.add("RDS_PASSWORD = " + System.getenv("RDS_PASSWORD"));

		ListVariaveis.add("SERVER_PORT = " + System.getenv("SERVER_PORT"));
		ListVariaveis.add("Propriedades de ambiente = " + System.getenv("Propriedades de ambiente"));
		ListVariaveis.add("Propriedades.de.ambiente = " + System.getenv("Propriedades.de.ambiente"));
		ListVariaveis.add("tw = " + System.getenv("tw"));

		ListVariaveis.add("Configure.stack.options = " + System.getenv("Configure.stack.options"));

		ListVariaveis.add("rds.password = ${rds.password}");
		ListVariaveis.add("url = " + url);
		ListVariaveis.add("username = " + username);
		ListVariaveis.add("password = " + password);

		ListVariaveis.add("rds.hostname = " + rds1);
		ListVariaveis.add("rds.username = " + rds2);
		ListVariaveis.add("rds.password = " + rds3);
		ListVariaveis.add("rds = " + rds4);
		ListVariaveis.add("rds.db.name = " + rds5);
		ListVariaveis.add("rds.port = " + rds6);

		ListVariaveis.add("oquevemdaqui1 = " + oquevemdaqui1);
		ListVariaveis.add("oquevemdaqui2 = " + oquevemdaqui2);
		ListVariaveis.add("oquevemdaqui3 = " + oquevemdaqui3);
		
		ListVariaveis.add("todas ----- = " + System.getenv());
		

		return ListVariaveis;
	}

	@ApiOperation(value = "Retorna uma lista de tws")
	@GetMapping(value = "/getListTw", produces = "application/json")
	public ResponseEntity<List<Tw>> getListTw() {
		List<Tw> twlist = new ArrayList<Tw>();
		twlist.addAll(iTwService.GetAll());
		return ResponseEntity.ok(twlist);
	}

	@GetMapping(value = "/getTwById/{id}", produces = "application/json")
	public ResponseEntity<Tw> getTwById(@PathVariable("id") int id) {
		return ResponseEntity.ok(iTwService.GetById(id));
	}

	@PostMapping(value = "/saveTw", produces = "application/json")
	public ResponseEntity<Tw> saveTw(@RequestBody Tw tw) {
		tw.setId(null);
		tw = iTwService.Save(tw);
		return new ResponseEntity<Tw>(tw, HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateTw", produces = "application/json")
	public ResponseEntity<Tw> updateTw(@RequestBody Tw newTw) {
		Tw tw = iTwService.GetById(newTw.getId());
		tw = newTw;
		return ResponseEntity.ok(iTwService.Save(tw));
	}

	@DeleteMapping(value = "/delTwById/{id}", produces = "application/json")
	public ResponseEntity<String> delTwById(@PathVariable("id") int id) {
		return new ResponseEntity<String>(iTwService.DelleteById(id), HttpStatus.OK);
	}

}
