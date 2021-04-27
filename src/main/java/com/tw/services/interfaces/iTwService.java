package com.tw.services.interfaces;

import java.util.List;

import com.tw.model.Tw;

public interface iTwService {

	// GET
	List<Tw> GetAll();
	Tw GetById(long id);

	// POST
	Tw Save(Tw tw);

	// PUT
	Tw Update(Tw tw);

	// DELLETE
	String DelleteById(long id);

}
