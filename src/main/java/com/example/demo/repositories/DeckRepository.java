package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumFormat;

public interface DeckRepository extends JpaRepository<Deck, Long> {
	
	Optional<Deck> findByName(String name);
	List<Deck> findByNameContaining(String nom);
	List <Deck> findByDeckBuilder (DeckCreator deckBuilder);
	List<Deck> findByFormat(EnumFormat format);
	List<Deck> findByColors (EnumColor color);
	
	@Query("SELECT d FROM Deck d " +
		       "WHERE (:name IS NULL OR d.name LIKE %:name%) " +
		       "AND (:manaCostMin IS NULL OR d.manaCost > :manaCostMin) " +
		       "AND (:manaCostMax IS NULL OR d.manaCost < :manaCostMax) " +
		       "AND (:valueMin IS NULL OR d.value > :valueMin) " +
		       "AND (:valueMax IS NULL OR d.value < :valueMax) " +
		       "AND (:formats IS NULL OR d.format IN :formats) " +
		       "AND (:colors IS NULL OR d.colors IN :colors) " +
		       "AND (:isPublic IS NULL OR d.isPublic = :isPublic)")
				List<Deck> findByOptionalAttribute(
				@Param("name") String name,
				@Param("manaCostMin") Long manaCostMin,
				@Param("manaCostMax") Long manaCostMax,
				@Param("valueMin") Float valueMin,
				@Param("valueMax") Float valueMax,
				@Param("formats") List<EnumFormat> formats,
				@Param("colors") List<EnumColor> colors,
				@Param("isPublic") boolean isPublic
				);
	
	// Tentative de query avec des list à reproduire dans cardRepository si fonctionne



}
