package com.tw.services.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.model.Tw;
import com.tw.repository.ITwRepository;
import com.tw.services.interfaces.iTwService;

@Service
public class TwService implements iTwService {

	@Autowired
	ITwRepository itw;

	@Override
	public List<Tw> GetAll() {
		return itw.findAll();
	}

	@Override
	public Tw GetById(long id) {
		return itw.findById(id).get();
	}

	@Override
	public Tw Save(Tw tw) {
		return itw.save(tw);
	}

	@Override
	public Tw Update(Tw newtw) {
		Tw tw = new Tw();
		tw = itw.findById(newtw.getId()).get();
		tw = newtw;
		return itw.save(tw);
	}

	@Override
	public String DelleteById(long id) {
		itw.deleteById(id);
		return "Deletado!";
	}

}
