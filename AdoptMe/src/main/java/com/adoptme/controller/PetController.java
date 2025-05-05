package com.adoptme.controller;

import com.adoptme.external.ExoticAnimal;
import com.adoptme.model.*;
import com.adoptme.service.PetService;
import com.adoptme.util.PetComparators;
import com.adoptme.view.PetView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Pet Adoption Center application.
 * Implements the Controller part of MVC pattern.
 */
public class PetController {
	private final Shelter<Pet> shelter;
	private final PetService petService;
	private PetView view;
	
	/**
     * Constructor for PetController
     */
	public PetController() {
		this.shelter = new Shelter<>();
		this.petService = new PetService();
	}
	
	/**
	 * Set the view for this controller
	 * @param view The PetView instance
	 */
	public void setView(PetView view) {
		this.view = view;
	}
	
	/**
     * Initialize the application by loading pets from JSON files
     */
	public void initialize() {
		try {
			List<Pet> pets = petService.loadPets();
			for (Pet pet : pets) {
				shelter.addPet(pet);
			}
			
			List<ExoticAnimal> exoticAnimals = petService.loadExoticPets();
			for (ExoticAnimal exotic : exoticAnimals) {
				shelter.addPet(new ExoticAnimalAdapter(exotic));
			}
			
			if (view != null) {
				view.updatePetList(shelter.getAllPets());
			}
		} catch (IOException e) {
			showError("Error loading pets: " + e.getMessage());
		}
	}
	
	/**
	 * Add a new pet to the shelter
	 * @param pet The pet to add
	 */
	public void addPet(Pet pet) {
		if (pet != null) {
			shelter.addPet(pet);
			view.updatePetList(shelter.getAllPets());
		}
	}
	
	/**
	 * Remove a pet from the shelter
	 * @param pet The pet to remove
	 */
	public void removePet(Pet pet) {
		if (pet != null && shelter.removePet(pet)) {
			view.updatePetList(shelter.getAllPets());
		}
	}
	
	/**
	 * Adopt a pet
	 * @param petName The name of the pet to adopt
	 */
	public void adoptPet(String petName) {
		try {
			Pet adoptedPet = shelter.adoptPet(petName);
			if (adoptedPet != null) {
				view.updatePetList(shelter.getAllPets());
                showMessage("Successfully adopted " + petName + "!");
			} else {
				showError("Pet not found: " + petName);
			}
		} catch (IllegalStateException e) {
			showError(e.getMessage());
		}
	}
	
	/**
     * Sort pets by the selected criteria
     * @param sortBy The sorting criteria
     */
	public void sortPets(String sortBy) {
		switch (sortBy.toLowerCase()) {
			case "name":
				shelter.sortByName();
				break;
			case "age":
                shelter.sortPets(new PetComparators.AgeComparator());
                break;
            case "species":
                shelter.sortPets(new PetComparators.SpeciesComparator());
                break;
            default:
                return;
		}
		view.updatePetList(shelter.getAllPets());
	}
	
	/**
	 * Save current pet list to JSON file
	 */
	public void savePets() {
		try {
			petService.savePets(shelter.getAllPets());
			showMessage("Pets saved successfully!");
		} catch (IOException e) {
			showError("Error saving pets: " + e.getMessage());
		}
	}
	
	/**
	 * Get details for a specific pet
	 * @param petName The name of the pet
	 * @return Pet details or null if not found
	 */
	public Pet getPetDetails(String petName) {
		return shelter.findPetByName(petName);
	}
	
	/**
     * Get all pets in the shelter
     * @return List of all pets
     */
    public List<Pet> getAllPets() {
        return shelter.getAllPets();
    }
    
    /**
     * Get available (not adopted) pets
     * @return List of available pets
     */
    public List<Pet> getAvailablePets() {
        return shelter.getAvailablePets();
    }
    
    /**
     * Get adopted pets
     * @return List of adopted pets
     */
    public List<Pet> getAdoptedPets() {
        return shelter.getAdoptedPets();
    }
	
	/**
     * Show an error message
     * @param message The error message
     */
	private void showError(String message) {
		if (view != null) {
			view.showError(message);
		} else {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Show an information message
     * @param message The information message
     */
	private void showMessage(String message) {
		if (view != null) {
			view.showMessage(message);
		} else {
			JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
