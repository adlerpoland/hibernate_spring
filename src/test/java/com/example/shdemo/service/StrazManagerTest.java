package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Pojazd;
import com.example.shdemo.domain.Interwencja;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional	
public class StrazManagerTest {
	
	//POJAZDY
	@Autowired
	StrazM strazM;	
	
	private final String MARKA = "Star";
	private final String MARKA_1 = "MAN";
	private final String MARKA_2 = "Mercedes";
	
	private final String MODEL = "155";
	private final String MODEL_1 = "FAST";
	private final String MODEL_2 = "Truck";
	
	private final int PRZEBIEG = 100000;
	private final int PRZEBIEG_1 = 55000;
	private final int PRZEBIEG_2 = 500000;

	//INTERWENCJE
	private final String DATA = "2015.01.01";
	private final String DATA_1 = "2015.01.05";
	
	private final String MIEJSCE = "Dluga";
	private final String MIEJSCE_1 = "Kartuska";
	
	
	@Before
	public void setup(){
		if(strazM.findInterwencjabyMiejsce(MIEJSCE) == null){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce(MIEJSCE);
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}
		
		Pojazd p1 = new Pojazd();
		p1.setMarka(MARKA_1);
		p1.setModel(MODEL_1);
		p1.setPrzebieg(PRZEBIEG_1);
		
		Interwencja i1 = new Interwencja();
		i1.setData(DATA_1);
		i1.setMiejsce(MIEJSCE);
		i1.getPojazdy().add(p1);
		
		strazM.addInterwencja(i1);
	}
	@Before
	public void setup1(){
		if(strazM.findInterwencjabyMiejsce(MIEJSCE) == null){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce(MIEJSCE);
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}
	}
	//Interwencja 
	@Test
	public void addInterwencja(){
		Interwencja i = new Interwencja();
		i.setMiejsce(MIEJSCE);
		i.setData(DATA);
		
		strazM.addInterwencja(i);
		Interwencja receivedInterwencja = strazM.findInterwencjabyMiejsce(MIEJSCE);
		assertEquals(MIEJSCE, receivedInterwencja.getMiejsce());
	}
	
	@Test
	public void editInterwencja(){
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE);
		i.setMiejsce("Armii Krajowej");
		i.setData("2015.05.05");
		Long InterwencjaId = i.getId();
		strazM.editInterwencja(i);
		
		Interwencja i2 = strazM.findInterwencjabyId(InterwencjaId);
		
		assertEquals("Armii Krajowej", i2.getMiejsce());
		assertEquals("2015.05.05", i2.getData());
	}
	@Test
	public void deleteInterwencja(){
		int InterwencjaCount = strazM.getAllInterwencja().size();
		
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE);
		Long InterwencjaId = i.getId();
		strazM.deleteInterwencja(i);
		
		assertEquals(null, strazM.findInterwencjabyId(InterwencjaId));
		assertEquals(InterwencjaCount - 1, strazM.getAllInterwencja().size());
	}
	@Test
	public void getInterwencjabyId(){
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE_1);
		
		Interwencja id = strazM.findInterwencjabyId(i.getId());
		
		assertEquals(i.getId(), id.getId());
	}
	@Test
	public void getPojazdyonInterwencja(){
		if(strazM.findInterwencjabyMiejsce("Dluga") == null){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce("Dluga");
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}
		
		Interwencja i = strazM.findInterwencjabyMiejsce("Dluga");
		List<Pojazd> pojazdOn = strazM.getPojazdInterwencja(i);
		
		assertEquals("MAN", pojazdOn.get(0).getMarka());
		assertEquals("FAST", pojazdOn.get(0).getModel());
		assertEquals(55000, pojazdOn.get(0).getPrzebieg());	
	}
	
	//Pojazd 
	
	@Test
	public void addPojazd(){
		Pojazd p = new Pojazd();
		p.setMarka("AUDI");
		p.setModel("A1");
		p.setPrzebieg(5000);
		
		Long pId = strazM.addNewPojazd(p);
		Pojazd pId2 = strazM.findPojazdById(pId);
		
		assertEquals("AUDI", pId2.getMarka());
		assertEquals("A1", pId2.getModel());
		assertEquals(5000, pId2.getPrzebieg());
	}
	@Test
	public void getPojazdId(){
		if(strazM.findInterwencjabyMiejsce(MIEJSCE) == null){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce(MIEJSCE);
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}
		
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE);
		
		if(i.getPojazdy().size() == 0){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			i.getPojazdy().add(p);
		}
		
		Pojazd p = i.getPojazdy().get(0);
		Pojazd pId = strazM.findPojazdById(i.getPojazdy().get(0).getId());
		
		assertEquals(p.getId(), pId.getId());
	}
	@Test
	public void editPojazd(){
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE_1);
		Pojazd p = i.getPojazdy().get(0);
		p.setMarka("Tata");
		p.setModel("Russia");
		p.setPrzebieg(250000);
		Long iId = i.getId();
		strazM.editPojazd(p);
		
		Interwencja iId2 = strazM.findInterwencjabyId(iId);
		
		assertEquals("Tata", iId2.getPojazdy().get(0).getMarka());
		assertEquals("Russia", iId2.getPojazdy().get(0).getModel());
		assertEquals(250000, iId2.getPojazdy().get(0).getPrzebieg());
	}
	@Test
	public void deletePojazd(){
		if(strazM.getAllInterwencja().size() == 0){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce(MIEJSCE);
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}	
		int InterwencjaCount = strazM.getAllInterwencja().size();
		
		if(strazM.findInterwencjabyMiejsce(MIEJSCE) == null){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			Interwencja interwencja = new Interwencja();
			interwencja.setData(DATA);
			interwencja.setMiejsce("Dluga");
			interwencja.getPojazdy().add(p);
			
			strazM.addInterwencja(interwencja);
		}
		Interwencja i = strazM.findInterwencjabyMiejsce(MIEJSCE);
		
		if(i.getPojazdy().size()==0){
			Pojazd p = new Pojazd();
			p.setMarka(MARKA);
			p.setModel(MODEL);
			p.setPrzebieg(PRZEBIEG);
			
			i.getPojazdy().add(p);
		}	
		int PojazdyOnInterwencja = i.getPojazdy().size();
		Pojazd p = i.getPojazdy().get(0);
		
		int PojazdCount = strazM.getAllPojazd().size();
		strazM.deletePojazd(p);
		
		i = strazM.findInterwencjabyMiejsce(MIEJSCE);
		
		assertEquals(InterwencjaCount, strazM.getAllInterwencja().size());
		assertEquals(PojazdCount - 1, strazM.getAllPojazd().size());
		assertEquals(PojazdyOnInterwencja - 1, i.getPojazdy().size());
	}
	
}
