package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;



import com.example.demo.enums.Edition;
import com.example.demo.enums.Format;
import com.example.demo.enums.Rarity;
import com.example.demo.enums.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "carte")

public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", nullable = false, unique = false)
	private String nom;
	
	@Enumerated(EnumType.STRING)
	private Format format;
	
	@Column(name = "couleur", nullable = false, unique = false)
	private String couleur;
	
	@Column(name="cout_mana")
	private int coutMana;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
	private Rarity rarete;
	
	@Enumerated(EnumType.STRING)
	private Edition edition;
	
	@Column(name = "valeur (€)", unique = false)
	private float value;
	
	@Column(name = "image", unique = false)
	private String image;
	
	@ManyToMany(mappedBy = "cartes")
	private List<Deck> decks;

	public Card() {
		decks = new ArrayList<>();
	}	
	
	

}