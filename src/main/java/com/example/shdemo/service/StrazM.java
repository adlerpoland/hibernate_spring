package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.Interwencja;
import com.example.shdemo.domain.Pojazd;

public interface StrazM {
	//Interwencja
	void addInterwencja(Interwencja interwencja);
	List<Interwencja> getAllInterwencja();
	void deleteInterwencja(Interwencja interwencja);
	Interwencja findInterwencjabyId(Long id);
	public Interwencja findInterwencjabyMiejsce(String miejsce);
	public boolean editInterwencja(Interwencja interwencja);
	
	//Film
	Long addNewPojazd(Pojazd pojazd);
	List<Pojazd> getAllPojazd();
	void deletePojazd(Pojazd pojazd);
	Pojazd findPojazdById(Long id);
	public boolean editPojazd(Pojazd pojazd);
	
	List<Pojazd> getPojazdInterwencja(Interwencja interwencja);
	void dodajPojazdDoInterwencji(Long interwencjaId, Long pojazdId);
}
