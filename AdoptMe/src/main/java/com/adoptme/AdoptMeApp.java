package com.adoptme;

import com.adoptme.controller.PetController;
import com.adoptme.view.PetView;

import javax.swing.*;

/**
 * Main application class for the Adopt Me! Pet Adoption Center.
 * Entry point for the application.
 */
public class AdoptMeApp {
	/**
     * Main method - application entry point
     * @param args Command line arguments
     */
    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	SwingUtilities.invokeLater(() -> {
    		try {
    			PetController controller = new PetController();
    			
    			PetView view = new PetView(controller);
    			
    			controller.initialize();
    			
    			view.setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    			JOptionPane.showMessageDialog(null,
    					"Error starting application: " + e.getMessage(),
    					"Application Error",
    					JOptionPane.ERROR_MESSAGE);
    			System.exit(1);
    		}
    	});
    };
}
