package TestProject;

/**
 * Created by Admin on 22.08.2016.
 */
public class searchObject {
    private String model = null;
    private int minCost = 0;
    private int maxCost = 0;
    private String windowTinting = null;
    private String alloyWheels = null;
    private String immobiliser = null;
    private String radioEquipment = null;
    private String cruiseControl = null;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public String getWindowTinting() {
        return windowTinting;
    }

    public void setWindowTinting(String windowTinting) {
        this.windowTinting = windowTinting;
    }

    public String getAlloyWheels() {
        return alloyWheels;
    }

    public void setAlloyWheels(String alloyWheels) {
        this.alloyWheels = alloyWheels;
    }

    public String getImmobiliser() {
        return immobiliser;
    }

    public void setImmobiliser(String immobiliser) {
        this.immobiliser = immobiliser;
    }

    public String getRadioEquipment() {
        return radioEquipment;
    }

    public void setRadioEquipment(String radioEquipment) {
        this.radioEquipment = radioEquipment;
    }

    public String getCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(String cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public String getDescription (){
        StringBuffer temp = new StringBuffer(0);
        if(getWindowTinting() != null)
            temp.append("windowTinting-" + getWindowTinting() + " % ");
        if(getAlloyWheels() != null)
            temp.append(" % " + "alloyWheels-" + getAlloyWheels() + " % ");
        if(getImmobiliser() != null)
            temp.append(" % " + "immobiliser-" + getImmobiliser() + " % ");
        if(getRadioEquipment() != null)
            temp.append(" % " + "radioEquipment-" + getRadioEquipment() + " % ");
        if(getCruiseControl() != null)
            temp.append(" % " + "cruiseControl-" + getCruiseControl());
        if(temp.length() == 0)
            temp.append("%");
        return temp.toString();
    }
}
