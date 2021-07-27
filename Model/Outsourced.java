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
/** This class creates Outsourced parts objects. */
public class Outsourced extends Part{
    
    
     private String companyName;
    
     public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
         
        super(id, name, price, stock, min, max);
        this.companyName = companyName;    
     
     
     }
/**This method gets the Company Name of the Part.  
 * @return returns the companyName type string. */
    public String getCompanyName() {
        return companyName;
    }
/** This method sets a Company Name for the Part.  
 * @param companyName is the string or name of the company for that Part. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
   
    
    
   
    
    
    
}
