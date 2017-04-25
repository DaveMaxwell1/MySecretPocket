package ie.aib.mysecretpocket;

public class Goal {
    private String name;
    private double totalToSave;
    private double currentlySaved;

    public Goal() {
    }

    public Goal(String name, double totalToSave, double currentlySaved) {
        this.name = name;
        this.totalToSave = totalToSave;
        this.currentlySaved = currentlySaved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalToSave() {
        return totalToSave;
    }

    public void setTotalToSave(double totalToSave) {
        this.totalToSave = totalToSave;
    }

    public double getCurrentlySaved() {
        return currentlySaved;
    }

    public void setCurrentlySaved(double currentlySaved) {
        this.currentlySaved = currentlySaved;
    }
}
