package com.adoptme.view;

import com.adoptme.controller.PetController;
import com.adoptme.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * View class for the Pet Adoption Center application.
 * Implements the View part of MVC pattern using Swing.
 */
public class PetView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final PetController controller;
	private JTable petTable;
	private DefaultTableModel tableModel;
	private JComboBox<String> sortComboBox;
	private JComboBox<String> filterComboBox;
	private JButton addButton, adoptButton, removeButton, viewDetailsButton, saveButton;
	
	/**
     * Constructor for PetView
     * @param controller The PetController instance
     */
	public PetView(PetController controller) {
		this.controller = controller;
		controller.setView(this);
		initializeUI();
	}
	
	/**
     * Initialize the user interface
     */
	private void initializeUI() {
		setTitle("Adopt Me! - Pet Adoption Center");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		String[] columnNames = {"ID", "Name", "Type", "Species", "Age", "Status"};
		tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		petTable = new JTable(tableModel);
		petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(petTable);
		
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel sortLabel = new JLabel("Sort by:");
		sortComboBox = new JComboBox<>(new String[]{"Name", "Age", "Species"});
		sortComboBox.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                controller.sortPets((String) sortComboBox.getSelectedItem());
            }
		});
		
		JLabel filterLabel = new JLabel("Show:");
        filterComboBox = new JComboBox<>(new String[]{"All Pets", "Available Pets", "Adopted Pets"});
        filterComboBox.addActionListener(e -> {
            String selected = (String) filterComboBox.getSelectedItem();
            if (selected != null) {
                updateFilteredPetList(selected);
            }
        });
		
		controlPanel.add(sortLabel);
		controlPanel.add(sortComboBox);
		controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(filterLabel);
        controlPanel.add(filterComboBox);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		addButton = new JButton("Add Pet");
        adoptButton = new JButton("Adopt");
        removeButton = new JButton("Remove");
        viewDetailsButton = new JButton("View Details");
        saveButton = new JButton("Save");
        
        addButton.addActionListener(e -> showAddPetDialog());
        adoptButton.addActionListener(e -> adoptSelectedPet());
        removeButton.addActionListener(e -> removeSelectedPet());
        viewDetailsButton.addActionListener(e -> viewSelectedPetDetails());
        saveButton.addActionListener(e -> controller.savePets());
        
        buttonPanel.add(addButton);
        buttonPanel.add(adoptButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(saveButton);

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
	}
	
	/**
	 * Update the pet list in the table
	 * @param pets List of pets to display
	 */
	public void updatePetList(List<Pet> pets) {
		tableModel.setRowCount(0);
		for (Pet pet : pets) {
			Object[] row = {
					pet.getId(),
					pet.getName(),
					pet.getType(),
					pet.getSpecies(),
					pet.getAge(),
					pet.isAdopted() ? "Adopted" : "Available"
			};
			tableModel.addRow(row);
		}
	}
	
	/**
     * Update the pet list based on filter selection
     * @param filter The filter to apply ("All Pets", "Available Pets", "Adopted Pets")
     */
    private void updateFilteredPetList(String filter) {
        List<Pet> filteredPets;
        
        switch (filter) {
            case "Available Pets":
                filteredPets = controller.getAvailablePets();
                break;
            case "Adopted Pets":
                filteredPets = controller.getAdoptedPets();
                break;
            case "All Pets":
            default:
                filteredPets = controller.getAllPets();
                break;
        }
        
        updatePetList(filteredPets);
    }
	
    /**
     * Show dialog to add a new pet
     */
    private void showAddPetDialog() {
        AddPetDialog dialog = new AddPetDialog(this);
        dialog.setVisible(true);
        
        Pet newPet = dialog.getPet();
        if (newPet != null) {
            controller.addPet(newPet);
        }
    }
    
    /**
     * Adopt the selected pet
     */
    private void adoptSelectedPet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow >= 0) {
            String petName = (String) tableModel.getValueAt(selectedRow, 1);
            controller.adoptPet(petName);
        } else {
            showError("Please select a pet to adopt.");
        }
    }
    
    /**
     * Remove the selected pet
     */
    private void removeSelectedPet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow >= 0) {
            String petName = (String) tableModel.getValueAt(selectedRow, 1);
            Pet pet = controller.getPetDetails(petName);
            if (pet != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove " + petName + "?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.removePet(pet);
                }
            }
        } else {
            showError("Please select a pet to remove.");
        }
    }
    
    /**
     * View details of the selected pet
     */
    private void viewSelectedPetDetails() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow >= 0) {
            String petName = (String) tableModel.getValueAt(selectedRow, 1);
            Pet pet = controller.getPetDetails(petName);
            if (pet != null) {
                showPetDetails(pet);
            }
        } else {
            showError("Please select a pet to view details.");
        }
    }
    
    /**
     * Show pet details in a dialog
     * @param pet The pet to show details for
     */
    private void showPetDetails(Pet pet) {
        String details = String.format(
            "ID: %d\nName: %s\nType: %s\nSpecies: %s\nAge: %d\nStatus: %s\n\n%s",
            pet.getId(),
            pet.getName(),
            pet.getType(),
            pet.getSpecies(),
            pet.getAge(),
            pet.isAdopted() ? "Adopted" : "Available",
            pet.getSpecificDetails()
        );
        
        JOptionPane.showMessageDialog(this, details, "Pet Details", JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
     * Show an error message
     * @param message The error message
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show an information message
     * @param message The information message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
