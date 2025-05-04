package com.adoptme.util;

import com.adoptme.model.Pet;
import java.util.Comparator;

/* Utility class providing comparator implementations for sorting by name, age, and species. */

public class PetComparators {

	/* Compares Names: */
	/* Handles all null values */
	
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
	
	/* Compares Age: */

	public static class AgeComparator implements Comparator<Pet> {
		@Override
		public int compare(Pet p1, Pet p2) {
			return Integer.compare(p1.getAge(), p2.getAge());
		}
	}
	
	/* Compares Species: */
	/* Handles all null values */

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
}
