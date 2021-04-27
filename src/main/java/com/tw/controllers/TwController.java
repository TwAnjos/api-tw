package com.tw.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * Swagger utiliza apenas esses retornos 
	 * 200 = OK 
	 * 201 = CREATED 
	 * 204 = NO_CONTENT
	 * 401 = UNAUTHORIZED 
	 * 403 = FORBIDDEN 404 = NOT_FOUND
	 */

	@Autowired
	iTwService iTwService;

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
