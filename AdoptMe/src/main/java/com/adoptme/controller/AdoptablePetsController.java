package com.adoptme.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import com.adoptme.model.Cat;
import com.adoptme.model.Dog;
import com.adoptme.model.Pet;
import com.adoptme.model.Rabbit;
import com.adoptme.model.Shelter;
import com.adoptme.view.NewPetView;
import com.adoptme.view.PetsView;

public class AdoptablePetsController {
	private Shelter<Pet> shelterModel;
	private PetsView petsView;
	private NewPetView newPetView;
	
	public AdoptablePetsController(Shelter<Pet> shelterModel, PetsView petsView) {
		this.shelterModel = shelterModel;
		this.petsView = petsView;
		this.newPetView = new NewPetView();
		
		//Adds listeners to the buttons
		this.petsView.addListenerToAddPetButton(new AddPetButtonActionListener());
		this.newPetView.addListenerToSubmitButton(new SubmitButtonActionListener());
		this.petsView.addListenerToDeletePetButton(new DeletePetButtonActionListener());
		
		//Updates list on startup
		updateShelter();
	}
	
	//function to start application
	public void initiate() {
		petsView.setVisible(true);
	}
	
	//returns shelter model
	public Shelter<Pet> getShelterModel() {
		return this.shelterModel;
	}
	
	//updates shelter object and JList for view(not working atm)
	public void updateShelter() {
		this.petsView.setShelter(getShelterModel());
		this.petsView.setList(new JList<>(getShelterModel().getAllPets().toArray()));
	}
	
	//Function for what happens when submit button is clicked
	private class SubmitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Pet p = null;
			String petType = newPetView.getPetType();
			
			//Finds what object type the pet is
			switch (petType.toLowerCase()) {
				case "dog":
					p = new Dog(newPetView.getId(),newPetView.getName(),newPetView.getSpecies(),newPetView.getAge(),false);
					break;
				case "cat":
					p = new Cat(newPetView.getId(),newPetView.getName(),newPetView.getSpecies(),newPetView.getAge(),false);
					break;
				case "rabbit":
					p = new Rabbit(newPetView.getId(),newPetView.getName(),newPetView.getSpecies(),newPetView.getAge(),false);
					break;
				default:
					//If type isn't apart of pet class then an error is thrown
					throw new IllegalArgumentException("Invalid Pet Type");
			}
			
			//adds pet to the model
			getShelterModel().addPet(p);
			System.out.println(getShelterModel().getAllPets());
			System.out.println(petsView.getShelter().getAllPets());
			//Switches to the list screen
			petsView.setVisible(true);
			//Updates view(not working atm)
			updateShelter();
		}
		
	}
	
	private class AddPetButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			newPetView.setVisible(true);
		}
		
	}
	
	private class DeletePetButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Pet p = getShelterModel().getAllPets().get(petsView.getSelectedPet()+1);
			getShelterModel().removePet(p);
			updateShelter();
			System.out.println(petsView.getShelter().getAllPets());
		}
		
	}
}
