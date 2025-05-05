package com.adoptme.util;

import com.adoptme.model.Pet;
import java.util.Comparator;

/**
 *  Utility class providing comparator implementations for sorting by name, age, and species.
 */

public class PetComparators {

	/**
     * Comparator to sort pets by name (alphabetical) (Slightly redundant, replicates default behavior)
     */
	
	public static class NameComparator implements Comparator<Pet> {
		@Override
		public int compare(Pet p1, Pet p2) {
			String name1 = p1.getName();
			String name2 = p2.getName();

			if (name1 == null && name2 == null) {
				return 0;
			}
			if (name1 == null) {
				return 1;
			}
			if (name2 == null) {
				return -1;
			}

			return name1.compareToIgnoreCase(name2);
		}
	}
	
	/**
     * Comparator to sort pets by age (ascending)
     */

	public static class AgeComparator implements Comparator<Pet> {
		@Override
		public int compare(Pet p1, Pet p2) {
			return Integer.compare(p1.getAge(), p2.getAge());
		}
	}
	
	/**
     * Comparator to sort pets by species (alphabetically)
     */

	public static class SpeciesComparator implements Comparator<Pet> {
		@Override
		public int compare(Pet p1, Pet p2) {
			String species1 = p1.getSpecies();
			String species2 = p2.getSpecies();

			if (species1 == null && species2 == null) {
				return 0;
			}
			if (species1 == null) {
				return 1;
			}
			if (species2 == null) {
				return -1;
			}

			return species1.compareToIgnoreCase(species2);
		}
	}
	
	/**
     * Comparator to sort pets by type (alphabetically)
     */
    public static class TypeComparator implements Comparator<Pet> {
        @Override
        public int compare(Pet pet1, Pet pet2) {
        	String type1 = pet1.getType();
			String type2 = pet2.getType();

			if (type1 == null && type2 == null) {
				return 0;
			}
			if (type1 == null) {
				return 1;
			}
			if (type2 == null) {
				return -1;
			}

			return type1.compareToIgnoreCase(type2);
        }
    }
    
    /**
     * Comparator to sort pets by adoption status (available first)
     */
    public static class AdoptionStatusComparator implements Comparator<Pet> {
    	@Override
        public int compare(Pet pet1, Pet pet2) {
    		return Boolean.compare(pet1.isAdopted(), pet2.isAdopted());
        }
    }
    
    /**
     * Comparator to sort pets by type and then by name
     */
    public static class TypeThenNameComparator implements Comparator<Pet> {
        @Override
        public int compare(Pet pet1, Pet pet2) {
        	String type1 = pet1.getType();
			String type2 = pet2.getType();
            String name1 = pet1.getName();
			String name2 = pet2.getName();

			if (type1 == null && type2 == null) {
				return 0;
			}
			if (type1 == null) {
				return 1;
			}
			if (type2 == null) {
				return -1;
			}

			int typeCompare = type1.compareToIgnoreCase(type2);
            if (typeCompare != 0) {
                return typeCompare;
            }

			if (name1 == null && name2 == null) {
				return 0;
			}
			if (name1 == null) {
				return 1;
			}
			if (name2 == null) {
				return -1;
			}

			return name1.compareToIgnoreCase(name2);
        }
    }
}
