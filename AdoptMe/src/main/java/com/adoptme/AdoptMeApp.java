package com.adoptme;

import com.adoptme.model.*;
import java.io.*;

public class AdoptMeApp {
	/**
     * Main method - application entry point
     * @param args Command line arguments
     */
    public static void main(String[] args) {
    	Shelter<Pet> shelter = new Shelter<Pet>();
    	shelter.addPet(new Dog(1, "Jake", "Pug", 0, false));
    	shelter.adoptPet("Jake");
    	System.out.println(shelter.getAllPets());
    };
}
