package com.adoptme.model;

/**
 * Represents a Dog in the adoption center.
 */
public class Dog extends Pet {

	/**
     * Constructor for Dog
     * @param id The dog's ID
     * @param name The dog's name
     * @param species The dog's breed/species
     * @param age The dog's age
     * @param adopted Whether the dog is adopted
     */
	public Dog(int id, String name, String species, int age, boolean adopted) {
		super(id, name, "Dog", species, age, adopted);
	}
	
	@Override
	public String getSpecificDetails() {
		return String.format("Dog Breed: %s", getSpecies());
	}

}
