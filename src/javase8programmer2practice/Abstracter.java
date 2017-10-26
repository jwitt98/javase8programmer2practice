//Develop code that uses abstract classes and methods
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public abstract class Abstracter {
    public static void main(String[] args){
        MotorVehicle pickupTruck = new Truck2("Pickup", 4500);
        pickupTruck.startEngine();
        pickupTruck.moveForward(45);
        MotorVehicle dumpTruck = new Truck2("Dump truck", 9400);
        dumpTruck.startEngine();
        dumpTruck.moveForward(37);
    }
}

abstract class MotorVehicle{
    
    private int numWheels;
    private String bodyType;
    private int ccs;
    
    public abstract void startEngine();
    public abstract void moveForward(int speed);
    
    public int getNumWheels(){
        return numWheels;
    }
    public String getBodyType(){
        return bodyType;
    }
    public int getCcs(){
        return ccs;
    }
    public void setNumWheels(int numWheels){
        this.numWheels = numWheels;
    }
    public void setBodyType(String bodyType){
        this.bodyType = bodyType;
    }
    public void setCcs(int ccs){
        this.ccs = ccs;
    }
}

class Truck2 extends MotorVehicle{
    
    public Truck2(String bodyType, int ccs){
        setNumWheels(4);
        setBodyType(bodyType);
        setCcs(ccs);
    }
    @Override
    public void startEngine(){
        System.out.println(getCcs() + " CC engine starting...");
    }
    public void moveForward(int speed){
        System.out.println(getBodyType() + " moving forward at " + speed + " mph...");
    }
}
