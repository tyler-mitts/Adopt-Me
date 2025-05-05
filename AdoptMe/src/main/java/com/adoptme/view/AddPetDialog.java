package com.adoptme.view;

import com.adoptme.model.*;

import javax.swing.*;
import java.awt.*;

public class AddPetDialog extends JDialog {
	private Pet pet;
	
	private JTextField idField;
    private JTextField nameField;
    private JSpinner ageSpinner;
    private JComboBox<String> typeCombo;
    private JTextField speciesField;
	
	/**
     * Constructor for AddPetDialog
     * @param parent The parent frame
     */
	public AddPetDialog(JFrame parent) {
		super(parent, "Add New Pet", true);
		setSize(400, 400);
		setLocationRelativeTo(parent);
		initializeUI();
	}
	
	/**
     * Initialize the user interface
     */
	private void initializeUI() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
     // ID field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        formPanel.add(idField, gbc);
        
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        
        // Type combo
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Type:"), gbc);
        
        gbc.gridx = 1;
        typeCombo = new JComboBox<>(new String[]{"Dog", "Cat", "Rabbit"});
        formPanel.add(typeCombo, gbc);
        
        // Species field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Species:"), gbc);
        
        gbc.gridx = 1;
        speciesField = new JTextField(20);
        formPanel.add(speciesField, gbc);
        
        // Age spinner
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Age:"), gbc);
        
        gbc.gridx = 1;
        ageSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 30, 1));
        formPanel.add(ageSpinner, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Add Pet");
        
        cancelButton.addActionListener(e -> {
            pet = null;
            dispose();
        });
        
        addButton.addActionListener(e -> {
            if (validateInput()) {
                createPet();
                dispose();
            }
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
	}
	
	/**
     * Validate user input
     * @return true if input is valid, false otherwise
     */
	private boolean validateInput() {
		if (idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (speciesField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a species.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
	}
	
	/**
     * Create a pet based on user input
     */
    private void createPet() {
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();
        String species = speciesField.getText().trim();
        int age = (Integer) ageSpinner.getValue();
        
        switch (type) {
            case "Dog":
                pet = new Dog(id, name, species, age, false);
                break;
                
            case "Cat":
                pet = new Cat(id, name, species, age, false);
                break;
                
            case "Rabbit":
                pet = new Rabbit(id, name, species, age, false);
                break;
        }
    }
    
    /**
     * Get the created pet
     * @return The created pet, or null if cancelled
     */
    public Pet getPet() {
        return pet;
    }
}
