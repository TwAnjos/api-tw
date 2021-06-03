package com.tw.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tw.model.Tw;
import com.tw.repository.ITwRepository;

@Component
public class DummyData {

	@Autowired
	ITwRepository iTwRepository;

	@PostConstruct
	public void StartDataInDB() {

		Tw tw1 = new Tw(null, "Thiago1", "Waltrick1", 34, LocalDate.now(), "campo de observação.", true);
		Tw tw2 = new Tw(null, "Thiago2", "Waltrick2", 342, LocalDate.now(), "campo de observação.", true);
		Tw tw3 = new Tw(null, "Thiago3", "Waltrick3", 343, LocalDate.now(), "campo de observação.", null);
		Tw tw4 = new Tw(null, "Thiago4", "Waltrick4", 344, LocalDate.now(), "campo de observação.", false);

//		Tw tw1 = new Tw();
//		tw1.setNome("Thiago");
//		tw1.setSobreNome("Waltrick");
//		tw1.setDataAtualizacao(LocalDate.now());
//		tw1.setIdade(34);
//		tw1.setObs("Campo de observações");
//		
//		i.save(tw1);

		List<Tw> twList = new ArrayList<Tw>();
		twList.addAll(Arrays.asList(tw1, tw2, tw3, tw4));
		iTwRepository.saveAll(twList);

		System.err.println("DummyData finished");

	}
}
