package com.adoptme.model;

/**
 * Represents a Cat in the adoption center.
 */
public class Cat extends Pet {

	/**
     * Constructor for Cat
     * @param id The cat's ID
     * @param name The cat's name
     * @param species The cat's breed/species
     * @param age The cat's age
     * @param adopted Whether the cat is adopted
     */
    public Cat(int id, String name, String species, int age, boolean adopted) {
        super(id, name, "Cat", species, age, adopted);
    }

	@Override
	public String getSpecificDetails() {
		return String.format("Cat Breed: %s", getSpecies());
	}

}
