package com.example.demo.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.enums.CardType;
import com.example.demo.enums.Edition;
import com.example.demo.enums.Rarity;
import com.example.demo.services.IDeckBuilderService;
import com.example.demo.services.IDeckService;

@RestController
@RequestMapping("f_user")
public class UserController {
	
	// Ici les méthodes déstiné à l'user connecté
	// créer, modifier, rechercher des decks accessibles par les users auth
	
	@Autowired
	private IDeckBuilderService iDeckBuilderService;
	@Autowired
	private IDeckService iDeckService;
	
	
	@PutMapping("updateProfil")
	public DeckCreator updateProfil(@RequestParam String email, @RequestBody DeckCreator deckBuilder) {
		return iDeckBuilderService.updateProfil(email, deckBuilder);
	}
	
	@PostMapping("addDeck")
	public Deck addDeck(@RequestParam Long userId, @RequestBody Deck deck) {
		return iDeckService.addDeck(userId, deck);
	}
	
	@DeleteMapping("deleteDeck")
	public String deleteDeck(@RequestParam Long deckID) {
		return iDeckService.deleteDeck(deckID);
	}
	
	@PutMapping("updateDeck")
	public Deck updateDeck(@RequestParam Long deckID, @RequestBody Deck deckUpdate) {
		return iDeckService.updateDeck(deckID, deckUpdate);
	}

	@GetMapping("getCards")
	public List<Card> getCardsByFilterForDeck (@RequestParam Long deckId, @RequestParam(required = false) String name,
			@RequestParam(required = false) Long manaCost, @RequestParam(required = false) Float value, @RequestParam(required = false) CardType type, 
			@RequestParam(required = false) Rarity rarity, @RequestParam(required = false) Edition edition) {
		return iDeckService.getCardsByFilterForDeck(deckId, name, manaCost, value, type, rarity, edition);
	}
	
	@GetMapping("getCommander")
	public List<Card> getCommanderByFilterForDeck(@RequestParam Long deckId, @RequestParam(required = false) String name, @RequestParam(required = false) Long manaCost,
			@RequestParam(required = false) Float value, @RequestParam(required = false) Rarity rarity,@RequestParam(required = false) Edition edition) {
			return iDeckService.getCommanderByFilterForDeck(deckId, name, manaCost, value, rarity, edition);
	}

	
	
	@PostMapping("addCardOnDeck")
	public Deck addCardOnDeck(@RequestParam Long cardId, @RequestParam Long deckId) {
		return iDeckService.addCardOnDeck(cardId, deckId);
	}
	
	@PostMapping("addCommanderOnDeck")
	public Deck addCommanderOnDeck(Long cardId, Long deckId) {
		return iDeckService.addCommanderOnDeck(cardId, deckId);
	}
	
	@DeleteMapping("deleteCardOnDeck")
	public String deleteCardOnDeck(@RequestParam Long cardId, @RequestParam Long deckId) {
		return iDeckService.deleteCardOnDeck(cardId, deckId);
	}
	
	@PutMapping("deleteCommanderOnDeck")
	public String deleteCommanderOnDeck(Long deckID) {
		return iDeckService.deleteCommanderOnDeck(deckID);
	}
	
	@PutMapping("deckPublic")
	public String publishDeck(@RequestParam Long deckID) {
		return iDeckService.publishDeck(deckID);
	}
	
	@PutMapping("deckPrivate")
	public String privateDeck(Long deckID) {
		return iDeckService.privateDeck(deckID);
	}
	
	
	@GetMapping("getDeckByUser")
	public Set<Deck> getDeckByUser(Long dbID){
		return iDeckService.getDeckByUser(dbID);
	}

	
	@GetMapping("deckValue")
	public float getDeckValue(@RequestParam Long deckID) {
		return iDeckService.getDeckValue(deckID);
	}
	
	@GetMapping("deckCost")
	public float getDeckManaCost(@RequestParam Long deckID) {
		return iDeckService.getDeckManaCost(deckID);
	}
	
	
	
	// Toutes les méthodes sont la 


}
