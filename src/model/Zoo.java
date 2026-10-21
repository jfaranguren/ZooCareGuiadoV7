package model;

import model.activity.FeedingActivity;

/**
 * Represents a Zoo with a name, city, address, budget, and a collection of
 * habitats.
 */
public class Zoo {

    // Atributes
    private String name;
    private String city;
    private String address;
    private double budget;

    // Relations
    private Habitat[] myHabitats;
    private Map zooMap;

    // Constructor
    /**
     * Constructs a Zoo object with the specified name, city, address, and budget.
     * Initializes an array to store up to 33 habitats.
     *
     * @param name    The name of the zoo.
     * @param city    The city where the zoo is located.
     * @param address The address of the zoo.
     * @param budget  The initial budget of the zoo.
     */
    public Zoo(String name, String city, String address, double budget) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.budget = budget;

        myHabitats = new Habitat[33];
        zooMap = new Map();
        createVeterinaryClinic();

    }

    // Analyzers

    /**
     * Retrieves the general information of the zoo, including its name, city,
     * address, and budget.
     *
     * @return A string containing the general information of the zoo in the format:
     *         "name-city-address-budget".
     */
    public String getInformation() {
        return name + "-" + city + "-" + address + "-" + budget;
    }

    /**
     * Creates a veterinary clinic habitat and adds it to the zoo.
     *
     * Postconditions:
     * - A habitat named "Clinica Veterinaria" is added to the zoo
     */
    private void createVeterinaryClinic() {
        addHabitat("CV", "Clinica Veterinaria", 4, 400, 25);
    }

    /**
     * Adds a new habitat to the zoo. The habitat is created with the specified
     * name, environment, and area. The habitat is added to the first available
     * position in the habitats array.
     * 
     * Preconditions:
     * - The 'myHabitats' array must be initialized.
     *
     * Postconditions:
     * - If there is an available position in the `myHabitats` array, the new
     * habitat is added to the zoo.
     * 
     * @param code        The code of the habitat.
     * @param name        The name of the habitat.
     * @param environment The type of environment in the habitat (e.g., jungle,
     *                    desert).
     * @param area        The area of the habitat in square meters.
     * @param maxCapacity The maximum capacity of the habitat.
     * @return {@code true} if the habitat was successfully added; {@code false}
     *         if there is no available space in the habitats array.
     */
    public boolean addHabitat(String code, String name, int environment, double area, int maxCapacity) {

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] == null) {

                    Habitat myHabitat = new Habitat(code, name, calculateEnvironmentType(environment), area,
                            maxCapacity);

                    if (zooMap.addHabitat(myHabitat)) {
                        myHabitats[i] = myHabitat;
                        return true;
                    }
                }

            }
        }

        return false;

    }

    /**
     * Checks if there is at least one available habitat in the zoo.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     *
     * @return {@code true} if there is at least one habitat available;
     *         {@code false} otherwise.
     */
    public boolean hasAvailableHabitat() {

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Retrieves a list of all habitats in the zoo, including their names and
     * environments.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     * 
     * @return A string containing the list of habitats in the format:
     *         "name-environment", with each habitat on a new line.
     */
    public String getHabitatList() {

        String habitatList = "";

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null) {

                    habitatList += "\n" + myHabitats[i].getName() + "-" + myHabitats[i].getEnvironment().getTypeName();

                }
            }
        }
        return habitatList;

    }

    /**
     * Adds an animal to a specific habitat in the zoo.
     * 
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     * 
     * * Postconditions:
     * - If a habitat with the specified `habitatName` exists and has available
     * space, the animal is added to the habitat.
     * 
     * 
     * @param habitatName  The name of the habitat where the animal will be added.
     * @param name         The name of the animal.
     * @param weight       The weight of the animal in kilograms.
     * @param dietType     The type of diet the animal follows (e.g., herbivore,
     *                     carnivore, omnivore).
     * @param lifeStage    The life stage of the animal (e.g., juvenile, adult,
     *                     senior).
     * @param healthStatus The health status of the animal (e.g., healthy, sick,
     *                     injured).
     * @return {@code true} if the animal was successfully added to the habitat;
     *         {@code false} if the habitat
     *         does not exist or is full.
     */
    public boolean addAnimalInHabitat(String habitatName, String name, double weight, String dietType, String lifeStage,
            String healthStatus) {

        if (myHabitats != null) {

            for (int i = 0; i < myHabitats.length; i++) {
                if (myHabitats[i] != null && myHabitats[i].getName().equals(habitatName)) {

                    Animal myAnimal = new Animal(name, weight, dietType, lifeStage, healthStatus);

                    return myHabitats[i].addAnimal(myAnimal);

                }
            }
        }

        return false;
    }

    /**
     * Retrieves a list of all animals in all habitats in the zoo.
     *
     * Preconditions:
     * - The `myHabitats` array must be initialized.
     *
     * @return A string containing the list of all animals in all habitats,
     *         separated by newlines.
     */

    public String getAllAnimalList() {

        String allAnimalList = "";

        for (int i = 0; i < myHabitats.length; i++) {
            if (myHabitats[i] != null) {
                allAnimalList += myHabitats[i].getAnimalList() + "\n";

            }

        }
        return allAnimalList;
    }

    /**
     * Calculates the environment type based on the given environment option.
     *
     * Preconditions:
     * - The `environmentOption` parameter must be a valid index (1-based)
     * corresponding to an `EnvironmentType` value.
     * 
     * @param environmentOption The 1-based index of the environment type (e.g., 1
     *                          for LAND, 2 for WATER).
     * @return The `EnvironmentType` corresponding to the given `environmentOption`.
     */

    public EnvironmentType calculateEnvironmentType(int environmentOption) {

        // LAND [0], WATER [1], AVIARY [2], MEDICAL [3]

        EnvironmentType[] values = EnvironmentType.values();

        return values[environmentOption - 1];

    }

    /**
     * Retrieves a list of all environment types available in the zoo.
     *
     * @return A string containing the list of all environment types.
     */
    public String getEnvironmentTypeList() {

        String environmentList = "";

        EnvironmentType[] values = EnvironmentType.values();

        for (int i = 0; i < values.length; i++) {

            environmentList += (i + 1) + ". " + values[i].getTypeName() + "\n";

        }

        return environmentList;

    }

    /**
     * Retrieves the map of the zoo as a string.
     *
     * Preconditions:
     * - The `zooMap` object must be initialized (not null).
     *
     * @return A string containing the zoo map.
     */
    public String getZooMap() {

        return zooMap.getMapAsString();
    }

    // Modifiers
    /**
     * Sets the name of the zoo.
     *
     * @param name The new name of the zoo.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the city where the zoo is located.
     *
     * @param city The new city of the zoo.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the address of the zoo.
     *
     * @param address The new address of the zoo.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the budget of the zoo.
     *
     * @param budget The new budget of the zoo.
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

}
