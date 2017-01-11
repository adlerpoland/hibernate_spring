package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Pojazd.all", query = "Select p from Pojazd p"),
	@NamedQuery(name = "Pojazd.byId", query = "Select p from Pojazd p where p.id = :id")
})
public class Pojazd {
	private Long id;
	private String marka;
	private String model;
	private int przebieg;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String txt) {
		this.marka = txt;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String txt) {
		this.model = txt;
	}
	public int getPrzebieg() {
		return przebieg;
	}
	public void setPrzebieg(int i) {
		this.przebieg = i;
	}
}
