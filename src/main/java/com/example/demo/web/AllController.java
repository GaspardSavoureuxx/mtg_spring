package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Card;
import com.example.demo.entities.Color;
import com.example.demo.entities.Deck;
import com.example.demo.entities.DeckCreator;
import com.example.demo.entities.Format;
import com.example.demo.enums.CardType;
import com.example.demo.enums.EnumColor;
import com.example.demo.enums.EnumEdition;
import com.example.demo.enums.EnumFormat;
import com.example.demo.enums.EnumRarity;
import com.example.demo.register.GetCard;
import com.example.demo.register.GetDeck;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.DeckBuilderRepository;
import com.example.demo.repositories.DeckRepository;
import com.example.demo.services.CardService;
import com.example.demo.services.ColorService;
import com.example.demo.services.DeckBuilderService;
import com.example.demo.services.DeckService;
import com.example.demo.services.EnumService;
import com.example.demo.services.IAuthenticationService;

@RestController
@RequestMapping("f_all")
public class AllController {
	
	// Contient les requetes nécessaires pour s'atuthentifier
	// Accessibles à tous
	
	
	@Autowired
	private IAuthenticationService iAuthenticationService;
	
	@Autowired
	private DeckBuilderService deckBuilderService;
	
	@Autowired
	private DeckService deckService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private EnumService enumService;
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	DeckRepository deckRepository;
	
	@Autowired
	DeckBuilderRepository userRepository;
	
	
	@PostMapping("likeCard")
	public ResponseEntity likeCard (@RequestParam Long userId, @RequestParam Long cardId) {
		
		Optional<DeckCreator> user = userRepository.findById(userId);
		if(user.isPresent()) {
			deckBuilderService.likeCard(user.get(), cardId);
			return ResponseEntity.ok("Carte ajoutée");
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
	}
	
	@DeleteMapping("dislikeCard")
	public ResponseEntity deleteCard (@RequestParam Long userId, @RequestParam Long cardId) {
		
		Optional<DeckCreator> user = userRepository.findById(userId);
		if(user.isPresent()) {
			deckBuilderService.dislikeCard(user.get(), cardId);
			return ResponseEntity.ok("Carte retirée");
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
		
	}
	
	@GetMapping("GetCardLiked")
	public ResponseEntity getCardLiked (@RequestParam Long userId) {
		Optional<DeckCreator> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(deckBuilderService.getCardLiked(user.get()));
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
	}
	
	
	
	
	
	@PostMapping("likeDeck")
	public ResponseEntity likeDeck (@RequestParam Long userId, @RequestParam Long deckId) {
		
		Optional<DeckCreator> user = userRepository.findById(userId);
		if(user.isPresent()) {
			deckBuilderService.likeDeck(user.get(), deckId);
			return ResponseEntity.ok("Deck ajouté");
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
	}
	
	@DeleteMapping("dislikeDeck")
	public ResponseEntity deleteDeck (@RequestParam Long userId, @RequestParam Long deckId) {
		
		Optional<DeckCreator> user = userRepository.findById(userId);
		if(user.isPresent()) {
			deckBuilderService.dislikeDeck(user.get(), deckId);
			return ResponseEntity.ok("Deck retiré");
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
		
	}
	
	@GetMapping("getDeckLiked")
	public ResponseEntity getDeckLiked (@RequestParam Long userId) {
		Optional<DeckCreator> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(deckBuilderService.getDeckLiked(user.get()));
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Echec de l'authentification");
	}

	
	@PostMapping("inscription")
	public DeckCreator inscription(@RequestBody DeckCreator db) {
		return iAuthenticationService.inscription(db);
		
	}
	
	
	@PostMapping("connexion")
	public String connexion(@RequestBody Map<String, String> request) {
		return iAuthenticationService.connexion(request);
	}
	
	@GetMapping("getDecks")
	List<GetDeck> getDecksByFilter( @RequestParam(required = false) String name,
	@RequestParam(required = false) Long manaCostMin, @RequestParam(required = false) Long manaCostMax,
	@RequestParam(required = false) Float valueMin, @RequestParam(required = false) Float valueMax,
	@RequestParam(required = false) List<EnumFormat> formats, @RequestParam(required = false) List<EnumColor> colors) {
		return deckService.getDecksByFilter(name, manaCostMin, manaCostMax, valueMin, valueMax, formats, colors);
	}
	
	
	@GetMapping("getCards")
	public List<GetCard> getCardsByFilter (@RequestParam(required = false) String name, 
			@RequestParam(required = false) Long manaCostMin, @RequestParam(required = false) Long manaCostMax,
			@RequestParam(required = false) Float valueMin, @RequestParam(required = false) Float valueMax,
			@RequestParam(required = false) List<EnumFormat> formats,
			@RequestParam(required = false)List<EnumColor> colors, @RequestParam(required = false) List<CardType> types,
			@RequestParam(required = false) String legendary,
			@RequestParam(required = false)List<EnumRarity> rarities, @RequestParam(required = false) List<EnumEdition> editions) {
		return cardService.getCardsByFilter(name, manaCostMin, manaCostMax, valueMin, valueMax, 
				formats, colors, types, legendary, rarities, editions);
	}
	
	@GetMapping("getDeckID")
	public ResponseEntity getDeckById(@RequestParam Long deckID) {
		return ResponseEntity.ok(deckService.getDeckById(deckID));

	}
	
	@GetMapping("getCardDeckID")
	public ResponseEntity getCardsOnDeckById(@RequestParam Long deckID) {
		return ResponseEntity.ok(deckService.getCardsOnDeckById(deckID));
	}
	
	/*
	
	@GetMapping("getCardsByColors")
	public List<Card> getCardsByColors (@RequestParam List<EnumColor> colors) {
		return cardService.findByColors(colors);
	}
	
	@GetMapping("getCardsByFormats")
	public List<Card> getCardsByFormats (@RequestParam List<EnumFormat> formats) {
		return cardService.findByFormats(formats);
	}
	
	@GetMapping("testDecks")
	public List<GetDeck> getDecks () {
		List<Deck> decks = deckRepository.findAll();
		List<GetDeck> decksReturn = new ArrayList<>();
		
		for (Deck deck : decks) {
			GetDeck testDeck = new GetDeck();
			testDeck.setId(deck.getId());
			testDeck.setName(deck.getName());
			testDeck.setImage(deck.getImage());
			testDeck.setFormat(deck.getFormat());
			testDeck.setDeckBuilder(deck.getDeckBuilder());
			testDeck.setDeckBuilderName(deck.getDeckBuilder().getPseudo());
			
			for (Color color : deck.getColors()) {
				testDeck.getColors().add(color.getName());
			}	
			decksReturn.add(testDeck);

		}
		
		return decksReturn;
	}
	*/
	
	@GetMapping("testCards")
	public List<GetCard> getCards () {
		List<Card> cards = cardRepository.findAll();
		List<GetCard> cardsReturn = new ArrayList<>();
		
		for (Card card : cards) {
			GetCard testCard = new GetCard();
			testCard.setId(card.getId());
			testCard.setName(card.getName());
			testCard.setImage(card.getImage());
			testCard.setText(card.getText());
			testCard.setType(card.getType());
			
			for (Color color : card.getColors()) {
				testCard.getColors().add(color.getName());
			}	
			for (Format format : card.getFormats()) {
				testCard.getFormats().add(format.getName());
			}	
			cardsReturn.add(testCard);
		}
		
		return cardsReturn;
	}
	
	@GetMapping("testUsers")
	public List<DeckCreator> getUsers () {
		return userRepository.findAll();
	}
	
	@GetMapping("getColors")
	public List<EnumColor> getColorsNames() {
		return enumService.getColorsNames();
	}
	
	@GetMapping("getFormats")
	public List<EnumFormat> getFormatsNames() {
		return enumService.getFormatsNames();
	}
	
	@GetMapping("getRarities")
	public List<EnumRarity> getRarities() {
		return enumService.getRarities();
	}
	
	@GetMapping("getEditions")
	public List<EnumEdition> getEditions() {
		return enumService.getEditions();
	}
	
	@GetMapping("getTypes")
	public List<CardType> getTypes() {
		return enumService.getTypes();
	}

}
