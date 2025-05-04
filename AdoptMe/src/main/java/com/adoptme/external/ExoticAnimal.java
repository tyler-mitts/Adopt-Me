package com.adoptme.external;

/**
 * Third-party ExoticAnimal class that doesn't follow Pet structure.
 * Represents the existing external system to adapt.
 */
public class ExoticAnimal {
	private String uniqueId;
    private String animalName;
    private String category;
    private String subSpecies;
    private int yearsOld;
    
    /**
     * Constructor for ExoticAnimal
     * @param uniqueId The unique identifier
     * @param animalName The animal's name
     * @param category The category of exotic animal (Bird, Reptile, etc.)
     * @param subSpecies The specific subspecies (Toucan, Python, etc.)
     * @param yearsOld The animal's age in years
     */
    public ExoticAnimal(String uniqueId, String animalName, String category, String subSpecies, int yearsOld) {
        this.uniqueId = uniqueId;
        this.animalName = animalName;
        this.category = category;
        this.subSpecies = subSpecies;
        this.yearsOld = yearsOld;
    }

    /**
     * Gets the animal's ID
     * @return the animal's ID
     */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
     * Gets the animal's Name
     * @return the animal's Name
     */
	public String getAnimalName() {
		return animalName;
	}

	/**
     * Gets the animal's Category
     * @return the animal's Category
     */
	public String getCategory() {
		return category;
	}

	/**
     * Gets the animal's SubSpecies
     * @return the animal's SubSpecies
     */
	public String getSubSpecies() {
		return subSpecies;
	}

	/**
     * Gets the animal's Years Old
     * @return the animal's Years Old
     */
	public int getYearsOld() {
		return yearsOld;
	}
}
