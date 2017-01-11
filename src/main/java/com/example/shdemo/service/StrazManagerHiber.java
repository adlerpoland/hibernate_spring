package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Interwencja;
import com.example.shdemo.domain.Pojazd;


@Component
@Transactional
public class StrazManagerHiber implements StrazM {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory _sessionFactory){
		this.sessionFactory = _sessionFactory;
	}

	
	public void addInterwencja(Interwencja interwencja) {
		interwencja.setId(null);
		sessionFactory.getCurrentSession().persist(interwencja);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Interwencja> getAllInterwencja() {
		return sessionFactory.getCurrentSession().getNamedQuery("Interwencja.all").list();
	}

	
	public void deleteInterwencja(Interwencja interwencja) {
		interwencja = (Interwencja) sessionFactory.getCurrentSession().get(Interwencja.class, interwencja.getId());
		for(Pojazd pojazd : interwencja.getPojazdy()){
			sessionFactory.getCurrentSession().delete(pojazd);
		}
		sessionFactory.getCurrentSession().delete(interwencja);	
	}

	
	public Interwencja findInterwencjabyId(Long id) {
		return (Interwencja) sessionFactory.getCurrentSession().get(Interwencja.class, id);
	}

	
	public Interwencja findInterwencjabyMiejsce(String miejsce) {
		List<Interwencja> interwencje =  sessionFactory.getCurrentSession().getNamedQuery("Interwencja.byMiejsce").setString("miejsce", miejsce).list();
		if(interwencje.size() == 0){
			return null;
		}else{
			return interwencje.get(0);
		}
	}
	
	public boolean editInterwencja(Interwencja interwencja) {
		try{
			sessionFactory.getCurrentSession().update(interwencja);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	
	public Long addNewPojazd(Pojazd pojazd) {
		pojazd.setId(null);
		return (Long)sessionFactory.getCurrentSession().save(pojazd);
	}

	
	@SuppressWarnings("unchecked")
	public List<Pojazd> getAllPojazd() {
		return sessionFactory.getCurrentSession().getNamedQuery("Pojazd.all").list();
	}

	
	public void deletePojazd(Pojazd pojazd) {
		Pojazd _pojazd = (Pojazd) sessionFactory.getCurrentSession().get(Pojazd.class, pojazd.getId());
		
		List<Interwencja> interwencja = getAllInterwencja();
		for(Interwencja k : interwencja){
			for(Pojazd f : k.getPojazdy()){
				if(f.getId() == _pojazd.getId()){
					k.getPojazdy().remove(f);
					sessionFactory.getCurrentSession().update(k);
					break;
				}
			}
		}
		sessionFactory.getCurrentSession().delete(_pojazd);
	}

	
	public Pojazd findPojazdById(Long id) {
		return (Pojazd) sessionFactory.getCurrentSession().get(Pojazd.class, id);
	}

	
	public boolean editPojazd(Pojazd pojazd) {
		try{
			sessionFactory.getCurrentSession().update(pojazd);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	
	public List<Pojazd> getPojazdInterwencja(Interwencja interwencja) {
		interwencja = (Interwencja) sessionFactory.getCurrentSession().get(Interwencja.class, interwencja.getId());
		List<Pojazd> pojazd = new ArrayList<Pojazd>(interwencja.getPojazdy());
		return pojazd;
	}

	
	public void dodajPojazdDoInterwencji(Long interwencjaId, Long pojazdId) {
		Interwencja interwencja = (Interwencja) sessionFactory.getCurrentSession().get(Interwencja.class, interwencjaId);
		Pojazd pojazd = (Pojazd) sessionFactory.getCurrentSession().get(Pojazd.class, pojazdId);
		interwencja.getPojazdy().add(pojazd);
	}
	
}

