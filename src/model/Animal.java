package model;

/**
 * Represents an Animal with specific attributes such as name, weight, diet
 * type, life stage, and health status.
 */
public class Animal {

    private String name;
    private double weight;
    private String dietType;
    private String lifeStage;
    private String healthStatus;

    /**
     * Constructs an Animal object with the specified attributes.
     *
     * @param name         The name of the animal.
     * @param weight       The weight of the animal in kilograms.
     * @param dietType     The type of diet the animal follows (e.g., herbivore,
     *                     carnivore, omnivore).
     * @param lifeStage    The life stage of the animal (e.g., juvenile, adult,
     *                     senior).
     * @param healthStatus The health status of the animal (e.g., healthy, sick,
     *                     injured).
     */
    public Animal(String name, double weight, String dietType, String lifeStage, String healthStatus) {
        this.name = name;
        this.weight = weight;
        this.dietType = dietType;
        this.lifeStage = lifeStage;
        this.healthStatus = healthStatus;
    }

    public double calculateMonthlyRationCost() {

        double rationFactor = 0.0;
        double foodUnitCost = 0.0;
        double incrementPercentage = 0.0;

        switch (dietType) {
            case "carnivoro":
                rationFactor = 0.03;
                foodUnitCost = 25500;
                break;
            case "herbivoro":
                rationFactor = 0.04;
                foodUnitCost = 8800;
                break;

            case "omnivoro":
                rationFactor = 0.035;
                foodUnitCost = 15200;
                break;

            case "insectivoro":
                rationFactor = 0.025;
                foodUnitCost = 18300;
                break;

        }

        if (lifeStage.equals("juvenil")) {
            incrementPercentage = 0.2;
        } else if (healthStatus.equals("cuarentena") || healthStatus.equals("recuperacion")) {
            incrementPercentage = 0.15;
        } else if (healthStatus.equals("observacion")) {
            incrementPercentage = 0.1;
        }

        return (weight * rationFactor) * (1 + incrementPercentage) * foodUnitCost * 30;
    }

    public String getName() {

        return name;

    }

    public DietType getDietType(){
        //TODO
        return null;
    }
}
