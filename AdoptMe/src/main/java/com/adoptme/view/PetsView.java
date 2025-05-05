package com.adoptme.view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.adoptme.model.Dog;
import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;
import java.awt.Color;

public class PetsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton deletePetButton;
	private JList list;
	private JButton addPetButton;
	private Shelter<Pet> shelter;

	/**
	 * Create the frame.
	 */
	public PetsView(Shelter<Pet> shelter) {
		this.shelter = shelter;
		
		//Setting basics for application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		
		panel.setLayout(null);
		
		//Creating new JList for list of pets
		list = new JList<>(this.shelter.getAllPets().toArray());
		list.setForeground(new Color(128, 128, 128));
		list.setBounds(10, 10, 416, 177);
		
		panel.add(list);
		
		//Button to add new pets to list
		addPetButton = new JButton("Add Pet");
		addPetButton.setBounds(10, 232, 67, 21);
		panel.add(addPetButton);
		
		//Button to delete pets from list
		deletePetButton = new JButton("Delete Pet");
		deletePetButton.setBounds(347, 232, 79, 21);
		panel.add(deletePetButton);
	}
	
	//Functions to add listeners to buttons
	public void addListenerToDeletePetButton(ActionListener l) {
		deletePetButton.addActionListener(l);
	}
	
	public void addListenerToAddPetButton(ActionListener l) {
		addPetButton.addActionListener(l);
	}
	
	//Gets index of pet that is selected
	public int getSelectedPet() {
		return list.getSelectedIndex();
	}

	//Returns the shelter object from view
	public Shelter<Pet> getShelter() {
		return shelter;
	}
	
	//Sets JList list
	public void setList(JList list) {
		this.list = list;
	}

	//Sets shelter object
	public void setShelter(Shelter<Pet> shelter) {
		this.shelter = shelter;
	}
}
