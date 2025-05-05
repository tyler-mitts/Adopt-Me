package com.adoptme.view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class NewPetView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField typeField;
	private JTextField speciesField;
	private JTextField ageField;
	private JButton submitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPetView frame = new NewPetView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewPetView() {
		//basic setup for screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Labels and text inputs for adding a new pet
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(138, 13, 9, 13);
		contentPane.add(lblNewLabel);
		
		idField = new JTextField();
		idField.setBounds(152, 10, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(114, 42, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		nameField = new JTextField();
		nameField.setBounds(152, 39, 96, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		typeField = new JTextField();
		typeField.setBounds(152, 71, 96, 19);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		speciesField = new JTextField();
		speciesField.setBounds(152, 109, 96, 19);
		contentPane.add(speciesField);
		speciesField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setBounds(152, 138, 96, 19);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(102, 74, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Species");
		lblNewLabel_3.setBounds(102, 112, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setBounds(102, 141, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(152, 190, 85, 21);
		contentPane.add(submitButton);
	}
	
	//Function for adding listener to the submit button
	public void addListenerToSubmitButton(ActionListener l) {
		submitButton.addActionListener(l);
	}
	
	//Getters for the text field inputs by the user
	public Integer getId() {
		return Integer.parseInt(idField.getText());
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public String getPetType() {
		return typeField.getText();
	}
	
	public String getSpecies() {
		return speciesField.getText();
	}
	
	public Integer getAge() {
		return Integer.parseInt(ageField.getText());
	}
	
	
}
