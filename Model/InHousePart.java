/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gianm
 */
/** This class creates InHouse Parts objects. */
public class InHousePart extends Part {
    
    private int machineId;
/** This constructor obtains the requirements for a InHouse part object.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param max
     * @param min
     * @param machineId */
    public InHousePart(int id, String name, double price, int stock, int max, int min,int machineId) {
        super(id, name, price, stock, min, max);
        
        
        this.machineId = machineId;
        
        
    }

    

   
/** This method will get the machine Id of an object. 
 @return returns an int. */
    public int getMachineId() {
        return machineId;
    }
/** This method sets the machine Id for an In house object. 
 @param machineId is the id. */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
