package com.adoptme.model;

public abstract class Pet implements Comparable<Pet> {
	private int id;
	private String name;
	private String type;
	private String species;
	private int age;
	private boolean adopted;
	
	/**
	 * Constructor for Pet
	 * @param id The pet's ID
	 * @param name The pet's name
	 * @param type The pet's type (Dog, Cat, Rabbit)
	 * @param species The pet's specific species/breed
	 * @param age The pet's age
	 * @param adopted Whether the pet is adopted
	 */
	public Pet(int id, String name, String type, String species, int age, boolean adopted) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.species = species;
		this.age = age;
		this.adopted = adopted;
	}
	
	/**
     * Gets the pet's ID
     * @return the pet's ID
     */

	public int getId() {
		return id;
	}

	/**
     * Sets the pet's ID
     * @param id the ID to set
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Gets the pet's Name
     * @return the pet's Name
     */

	public String getName() {
		return name;
	}

	/**
     * Sets the pet's Name
     * @param name the Name to set
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets the pet's Type
     * @return the pet's Type
     */

	public String getType() {
		return type;
	}

	/**
     * Sets the pet's Type
     * @param type the Type to set
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets the pet's Species
     * @return the pet's Species
     */

	public String getSpecies() {
		return species;
	}

	/**
     * Sets the pet's Species
     * @param species the Species to set
     */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
     * Gets the pet's Age
     * @return the pet's Age
     */

	public int getAge() {
		return age;
	}

	/**
     * Sets the pet's Age
     * @param age the Age to set
     */
	public void setAge(int age) {
		this.age = age;
	}

	/**
     * Gets the pet's Adopted Status
     * @return the pet's Adopted Status
     */

	public boolean isAdopted() {
		return adopted;
	}

	/**
     * Sets the pet's Adopted Status
     * @param adopted the Adopted Status to set
     */
	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	/**
     * Compares this pet to another pet by name for natural ordering
     * @param other the pet to compare to
     * @return negative if this pet's name comes before, positive if after, 0 if equal
     */
	@Override
	public int compareTo(Pet other) {
		return this.name.compareTo(other.name);
	}
	
	/**
     * Returns a string representation of the pet
     * @return formatted string with pet details
     */
	@Override
	public String toString() {
		return String.format("%s - %s (%s), %d years old%s", 
	            name, type, species, age, adopted ? " (ADOPTED)" : "");
	}

	/**
     * Abstract method to get pet-specific details
     * @return String containing specific details about the pet
     */
	public abstract String getSpecificDetails();
}
