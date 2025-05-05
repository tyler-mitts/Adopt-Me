package com.adoptme.service;

import com.adoptme.external.ExoticAnimal;
import com.adoptme.model.*;
import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling Pet persistence and JSON operations.
 * Demonstrates Single Responsibility Principle.
 */
public class PetService {
	private static final String RESOURCES_PATH = "src/main/resources/";
	private static final String PETS_FILE = "pets.json";
    private static final String EXOTIC_PETS_FILE = "exotic_pets.json";
    
    private final Gson gson;
    
    public PetService() {
    	this.gson = new GsonBuilder()
    			.registerTypeAdapter(Pet.class, new PetDeserializer())
    			.setPrettyPrinting()
    			.create();
    }
    
    /**
     * Load regular pets from JSON file
     * @return List of pets
     * @throws IOException if file cannot be read
     */
    public List<Pet> loadPets() throws IOException {
    	File file = new File(RESOURCES_PATH + PETS_FILE);
    	if (!file.exists()) {
    		return new ArrayList<>();
    	}
    	
    	try (Reader reader = new FileReader(file)) {
    		Type listType = new com.google.gson.reflect.TypeToken<List<Pet>>(){}.getType();
    		List<Pet> pets = gson.fromJson(reader, listType);
    		return pets != null ? pets : new ArrayList<>();
    	}
    }
    
    /**
     * Save pets to JSON file with timestamp
     * @param pets List of pets to save
     * @throws IOException if file cannot be written
     */
    public void savePets(List<? extends Pet> pets) throws IOException{
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    	String filename = now.format(formatter) + "_pets.json";
    	
    	File file = new File(RESOURCES_PATH + filename);
    	file.getParentFile().mkdirs();
    	
    	try (Writer writer = new FileWriter(file)) {
    		gson.toJson(pets, writer);
    	}
    }
    
    /**
     * Custom deserializer for Pet interface
     */
    private static class PetDeserializer implements JsonDeserializer<Pet>{
    	@Override
    	public Pet deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) 
    			throws JsonParseException {
    		JsonObject jsonObject = json.getAsJsonObject();
    		String species = jsonObject.get("species").getAsString();
    		
    		switch (species.toLowerCase()) {
    			case "dog":
    				return context.deserialize(json, Dog.class);
    			case "cat":
    				return context.deserialize(json, Cat.class);
    			case "rabbit":
    				return context.deserialize(json, Rabbit.class);
    			default:
    				if (species.toLowerCase().startsWith("exotic")) {
                        ExoticAnimal exotic = context.deserialize(json, ExoticAnimal.class);
                        return new ExoticAnimalAdapter(exotic);
                    }
                    throw new JsonParseException("Unknown species: " + species);
    		}
    	}
    }
}
