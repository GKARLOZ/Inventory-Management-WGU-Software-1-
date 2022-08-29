 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View_Controller.AddModifyProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gianm
 */


/** This class creates Products objects.
 Every time a new Product object is created, an observable list called AssociatedParts will be created.
     The observable list is unique to any other Product object and can be modified with the add and delete methods. 
      */
public class Product {
    
   
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max; 
    
    private ObservableList<Part> associatedParts;
    
    /** This constructor creates a new id,name,price,stock,min,max and associatedParts. */
     public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        
        associatedParts = FXCollections.observableArrayList();
        addAllAssociatedPart(AddModifyProductController.getTempList());
        
    }
/** @return returns an id of type int. */
    public int getId() {
        return id;
    }
/** @param id is the id of the product object. */
    public void setId(int id) {
        this.id = id;
    }
/** @return returns the name in type string. */
    public String getName() {
        return name;
    }
/** @param name is the name of the product object. */
    public void setName(String name) {
        this.name = name;
    }
/** @return returns the price in type double. */
    public double getPrice() {
        return price;
    }
/** @param price is the price of the Product object. */
    public void setPrice(double price) {
        this.price = price;
    }
/** @return returns the stock inventory of the object. */
    public int getStock() {
        return stock;
    }
/** @param stock is the stock available for the object. */
    public void setStock(int stock) {
        this.stock = stock;
    }
/** @return returns the minimum of the product. */
    public int getMin() {
        return min;
    }
/** @param min is the minimum in type int. */
    public void setMin(int min) {
        this.min = min;
    }
/** @return returns the maximum of the product. */
    public int getMax() {
        return max;
    }
/** @param max is the maximum of the product. */
    public void setMax(int max) {
        this.max = max;
    }

   /** This method adds a part to the associatedParts observableList. 
    @param part part is the Part object added. */ 
    public void addAssociatedPart(Part part)
    {
   
     associatedParts.add(part);
    
    }
     
     /** This method returns a observable list. 
      @return returns the getAllAssociatedParts observableList. */
     public ObservableList<Part> getAllAssociatedParts()
     {
     
     return associatedParts;
     
     }
     
     /** This method deletes a Part from the associatedParts Observable List. 
      @param selectedAssociatedPart is the Part object you desire to delete. 
      @return returns a boolean value true for deleted and false for not deleted. */
    public boolean deleteAssociatedPart (Part selectedAssociatedPart)
      {
        for(Part newPart : getAllAssociatedParts())
        {
            
        if(newPart == selectedAssociatedPart)
            return getAllAssociatedParts().remove(newPart);
   
        }
      
      return false;
      }
    /** This method adds all the Parts from allParts ObservableList with Part objects. 
     @param allParts allParts is the observableList that will be copied to the associatedParts observable list. 
     */
     public void addAllAssociatedPart(ObservableList<Part> allParts)
    {
        
      //Searches for an existing object 
       for(Part newPart: allParts)
       {
         
                 addAssociatedPart(newPart);
                 
       
       }
    }  
    
    
   
    
}
