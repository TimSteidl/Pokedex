package de.bredex.pokemon;

import de.bredex.enums.Type;
import de.bredex.pokedex.Pokedex;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pokemon {
	@Id
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type1;
	@Enumerated(EnumType.STRING)
	private Type type2;
	private int total;
	private int hp;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;

	@ManyToOne
	private Pokedex pokedex;
	
	public Pokemon() {
	}
	
	public Pokemon(int id, String name, Type type1, Type type2, int total, int hp, int attack, int defense,
			int specialAttack, int specialDefense, int speed, Pokedex pokedex) {
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.total = total;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.pokedex = pokedex;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Type getType1() {
		return this.type1;
	}
	
	public Type getType2() {
		return this.type2;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getAttack() {
		return this.attack;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public int getSpecialAttack() {
		return this.specialAttack;
	}
	
	public int getSpecialDefense() {
		return this.specialDefense;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("ID: ").append(getId());
		builder.append("\n");
		builder.append("Name: ").append(getName());
		builder.append("\n");
		builder.append("Type 1: ").append(getType1());
		builder.append("\n");
		builder.append("Type 2: ").append(getType2());
		builder.append("\n");
		builder.append("Attack: ").append(getAttack());
		builder.append("\n");
		builder.append("Special Attack: ").append(getSpecialAttack());
		builder.append("\n");
		builder.append("Special Defense: ").append(getSpecialDefense());
		builder.append("\n");
		builder.append("Speed: ").append(getSpeed());
		builder.append("\n");
		builder.append("Total: ").append(getTotal());

		return builder.toString();
	}
	
}
