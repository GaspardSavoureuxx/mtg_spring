package com.example.demo.entities;


import java.io.Serializable;
import java.util.List;

import com.example.demo.enums.EnumFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "format")
@Builder
public class Format  {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", unique=true)
	@Enumerated(EnumType.STRING)
	private EnumFormat name;
	
	@ManyToMany(mappedBy = "formats", fetch = FetchType.LAZY)
	private List<Card> cards;

}
