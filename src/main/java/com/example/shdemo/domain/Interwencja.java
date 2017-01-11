package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Interwencja.all", query = "Select i from Interwencja i"),
	@NamedQuery(name = "Interwencja.byId", query = "Select i from Interwencja i where i.id = :id"),
	@NamedQuery(name = "Interwencja.byMiejsce", query = "Select i from Interwencja i where i.miejsce = :miejsce")
})
public class Interwencja {
	private Long id;
	private String data = "";
	private String miejsce = "";
	private List<Pojazd> pojazdy = new ArrayList<Pojazd>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	public String getMiejsce() {
		return miejsce;
	}
	public void setMiejsce(String txt) {
		this.miejsce = txt;
	}
	public String getData() {
		return data;
	}
	public void setData(String txt) {
		this.data = txt;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Pojazd> getPojazdy() {
		return pojazdy;
	}
	public void setPojazdy(List<Pojazd> pojazdy) {
		this.pojazdy = pojazdy;
	}
}
