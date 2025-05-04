package com.adoptme.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic Shelter class to manage pets.
 * Demonstrates the use of Java Generics.
 * @param <T> Type parameter that must extend Pet
 */
public class Shelter<T extends Pet> {
	private List<T> pets;
	
	 /**
     * Constructor for Shelter
     */
	public Shelter() {
		this.pets = new ArrayList<>();
	}
	
	/**
     * Add a pet to the shelter
     * @param pet The pet to add
     */
    public void addPet(T pet) {
        if (pet != null) {
            pets.add(pet);
        }
    }
    
    /**
     * Remove a pet from the shelter
     * @param pet The pet to remove
     * @return true if pet was removed, false otherwise
     */
    public boolean removePet(T pet) {
        return pets.remove(pet);
    }
    
    /**
     * Get all pets in the shelter
     * @return List of all pets
     */
    public List<T> getAllPets() {
        return new ArrayList<>(pets);
    }
    
    /**
     * Get only available (not adopted) pets
     * @return List of available pets
     */
    public List<T> getAvailablePets() {
        return pets.stream()
                   .filter(pet -> !pet.isAdopted())
                   .collect(Collectors.toList());
    }
    
    /**
     * Get only adopted pets
     * @return List of adopted pets
     */
    public List<T> getAdoptedPets() {
        return pets.stream()
                   .filter(Pet::isAdopted)
                   .collect(Collectors.toList());
    }
    
    /**
     * Adopt a pet by name
     * @param name The name of the pet to adopt
     * @return The adopted pet, or null if not found or already adopted
     * @throws IllegalStateException if pet is already adopted
     */
    public T adoptPet(String name) throws IllegalStateException {
        for (T pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                if (pet.isAdopted()) {
                    throw new IllegalStateException("Pet " + name + " is already adopted!");
                }
                pet.setAdopted(true);
                return pet;
            }
        }
        return null;
    }
    
    /**
     * Find a pet by name
     * @param name The name to search for
     * @return The pet if found, null otherwise
     */
    public T findPetByName(String name) {
        for (T pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }
    
    /**
     * Sort pets by natural order (name)
     */
    public void sortByName() {
        Collections.sort(pets);
    }
    
    /**
     * Sort pets by a custom comparator
     * @param comparator The comparator to use for sorting
     */
    public void sortPets(Comparator<T> comparator) {
        pets.sort(comparator);
    }
    
    /**
     * Get the number of pets in the shelter
     * @return The count of pets
     */
    public int getPetCount() {
        return pets.size();
    }
    
    /**
     * Clear all pets from the shelter
     */
    public void clearShelter() {
        pets.clear();
    }
}
