package com.adoptme.model;

import com.adoptme.external.ExoticAnimal;

/**
 * Adapter class to make ExoticAnimal compatible with our Pet hierarchy.
 * Implements the Adapter design pattern.
 */
public class ExoticAnimalAdapter extends Pet {
	private ExoticAnimal exoticAnimal;

	/**
	 * Constructor for ExoticAnimalAdapter
	 * @param exoticAnimal The ExoticAnimal to adapt
	 */
	public ExoticAnimalAdapter(ExoticAnimal exoticAnimal) {
		// Using uniqueId hashcode as numeric ID (in real app, might need better ID generation)
        super(Math.abs(exoticAnimal.getUniqueId().hashCode() % 10000), 
              exoticAnimal.getAnimalName(), 
              "Exotic",
              exoticAnimal.getSubSpecies(), 
              exoticAnimal.getYearsOld(), 
              false); // Assume not adopted by default
        this.exoticAnimal = exoticAnimal;
    }
	
    @Override
    public String getSpecificDetails() {
        return String.format("Exotic Category: %s, Subspecies: %s", 
            exoticAnimal.getCategory(), 
            exoticAnimal.getSubSpecies());
    }

    /**
     * Get the underlying ExoticAnimal
     * @return The adapted ExoticAnimal
     */
    public ExoticAnimal getExoticAnimal() {
        return exoticAnimal;
    }
}
