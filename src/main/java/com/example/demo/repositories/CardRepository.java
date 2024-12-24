package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumRarity;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	
	/*
	Optional<Card> findByName(String nom);
	List<Card> findByNameContaining(String nom);
	List<Card> findByFormats(Format formats);
	List<Card> findByManaCost (int manaCost);
	List<Card> findByColors (Color color);
	List<Card> findByType (CardType type);
	List<Card> findByRarity(Rarity rarete);
	List<Card> findByEdition (Edition edition);
	*/
	// Potentiellement ajouter un filtre pour la value
	
	
	@Query("SELECT c FROM Card c " +
				"LEFT JOIN c.colors Color " +
		       "LEFT JOIN c.formats Format " +
		       "WHERE (:name IS NULL OR c.name LIKE %:name%) " +
		       "AND (:manaCostMin IS NULL OR c.manaCost > :manaCostMin) " +
		       "AND (:manaCostMax IS NULL OR c.manaCost < :manaCostMax) " +
		       "AND (:valueMin IS NULL OR c.value > :valueMin) " +
		       "AND (:valueMax IS NULL OR c.value < :valueMax) " +
		       "AND (:types IS NULL OR c.type IN :types) " +
		       "AND (:rarities IS NULL OR c.rarity IN :rarities) " +
		       "AND (:editions IS NULL OR c.edition IN :editions) " +
		       "AND (:colors IS NULL OR Color IN :colors) " +
		       "AND (:formats IS NULL OR Format IN :formats) "
			)
		List<Card> findByOptionalAttribute(
				@Param("name") String name,
				@Param("manaCostMin") Long manaCostMin,
				@Param("manaCostMax") Long manaCostMax,
				@Param("valueMin") Float valueMin,
				@Param("valueMax") Float valueMax,
		        @Param("types") List<CardType> types,
		        @Param("rarities") List<EnumRarity> rarities,
		        @Param("editions") List<EnumEdition> editions,
		        @Param("colors") List<Color> colors,
		        @Param("formats") List<Format> formats
				);
	
	
	List<Card> findAll();
	
	List<Card> findByColorsIn(List<Color> colors);
	List<Card> findByFormatsIn(List<Format> formats);	

}
