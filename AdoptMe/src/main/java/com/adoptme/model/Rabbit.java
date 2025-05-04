package com.adoptme.model;

/**
 * Represents a Rabbit in the adoption center.
 */
public class Rabbit extends Pet {
    
    /**
     * Constructor for Rabbit
     * @param id The rabbit's ID
     * @param name The rabbit's name
     * @param species The rabbit's breed/species
     * @param age The rabbit's age
     * @param adopted Whether the rabbit is adopted
     */
    public Rabbit(int id, String name, String species, int age, boolean adopted) {
        super(id, name, "Rabbit", species, age, adopted);
    }
    
    @Override
    public String getSpecificDetails() {
        return String.format("Rabbit Breed: %s", getSpecies());
    }
}