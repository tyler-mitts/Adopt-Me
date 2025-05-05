package com.adoptme;

import com.adoptme.controller.AdoptablePetsController;
import com.adoptme.model.*;
import com.adoptme.view.PetsView;
import javax.swing.SwingUtilities;

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
    	
    	
    	SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				AdoptablePetsController controller = new AdoptablePetsController(shelter,new PetsView(shelter));
				
				controller.initiate();
			}
    		
    	});
    	
    };
    
}
