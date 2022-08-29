/*
 * Giancarlo Bustos
 * This application is an Inventory that will add,modifie and deletes information 
 * Software1 
*/
package Model;

import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gianm
 */

/** This class creates an app that displays messages. */
public class Inventory extends Application {

    /**
     * @param args the command line arguments
     */
    
    private static ObservableList<Part>       allParts         = FXCollections.observableArrayList();
    private static ObservableList<Part>       filteredParts    = FXCollections.observableArrayList();
    private static ObservableList<Product>    allProducts      = FXCollections.observableArrayList();
    private static ObservableList<Product>   filteredProducts  = FXCollections.observableArrayList();
 
    /** *  This is the main method.This is the first method that gets called when you run your java program. launch will show your scene,keep in mind you must put your declarations before the launch or it wont appear
     * @param args
     */
    public static void main(String[] args) {
       
        InHousePart inHousePart99 = new InHousePart(1,"Max needs to be fixed",22.50,44,42,20,771);
        InHousePart inHousePart77 = new InHousePart(2,"Min needs to be fixed",77.8,474,800,950,6899);
        Outsourced outsourced55 = new Outsourced(3,"Inventory needs to be fixed",44.4,78,60,20,"Batman");
        
        addPart(inHousePart99);
        addPart(inHousePart77);
        addPart(outsourced55);
        
        Product ProductOne = new Product(1,"Max and Inv needs to be fixed ",250.50,255,25,5);
        Product ProductTwo = new Product(2,"The min needs to be fixed ",250.50,25,255,25);
          
          addProduct(ProductOne);
          addProduct(ProductTwo);
       
         launch(args);
    }
    
    /**This method will create a scene. 
     @param stage stage is an object of Stage. */
    @Override
    public void start(Stage stage) throws Exception {
    
    Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/InventoryView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.setTitle("My javafx menu");
        stage.show();
        //System.out.print("Hello, Just testing ");
 
    }
    
    /** This method will add a Part to the allParts observable list.
     @param newPart Part object added to the allParts observable list*/
     public static void addPart(Part newPart)
    {
        
        allParts.add(newPart);
            
    }
    
     
     /** This method will return a ObservableList of Part Objects. 
      @return Returns an ObservableList of Parts.*/
    public static ObservableList<Part> getAllParts()
    {
        
        return allParts;
    }
    
    /** This method will return a ObservableList of Part Objects. 
      @return Returns an ObservableList of Filtered Parts.*/
    public static ObservableList<Part> getAllFilteredParts()
    {
        
        return filteredParts;
    }
    
    
    /** This method updates any selected Part from the Table View. You need to 
     select a part or it wont work 
     @param index Index is used to find the exact Part from AllParts
     @param  selectedPart SelectedPart is the object selected from the table view. */
     public  static void updatePart(int index,Part selectedPart)
    {
      
      
      int index2 = -1;
      //Searches for an existing object 
       for(Part newPart: getAllParts())
       {
          index2++;
          if(newPart.getId() == index) 
            getAllParts().set(index2, selectedPart);
          
       }
       
          
    }
     /** This method will delete a selected Part from the Part Table View
      @param selectedPart SelectedPart is the id of the object selected
      @return  Returns true if the part is deleted. */
      public static boolean deletePart (int selectedPart)
      {
        for(Part newPart : getAllParts())
        {
            
        if(newPart.getId() == selectedPart)
            return getAllParts().remove(newPart);
   
        }
      
      return false;
      }
      
      
      /** This method searches a String typed in the search box. 
       * this method will filter the list as long as the name is true.
       * @param partName partName is the string typed in the search box
       * @return Returns an ObservableList of Part objects. */
    public static ObservableList<Part> lookupPart(String partName)
    {
        //IF NOT EMPTY CLEAR THE LIST
        if(!(getAllFilteredParts().isEmpty()))
            getAllFilteredParts().clear();
                    
                    
        for(Part newPart : getAllParts())
        {
            if(newPart.getName().contains(partName))
            getAllFilteredParts().add(newPart);
        }
            if(getAllFilteredParts().isEmpty())
               return getAllParts();
           else
               return getAllFilteredParts();
    }
     
    /** This method will highlight the Id location in the Part table view.
     @param partId partId is the Parts id 
     @return Returns an object of type Part. */
    public  static Part lookupPart(int partId)
    {
      //IF NOT EMPTY CLEAR THE LIST
       for(Part searchId : getAllParts())
       {
              
        if(searchId.getId() == partId)
        {
         
         return searchId;
        }
       } 
         return null;
    }
    //==========================Products=========================================
    
    /** This method will add a Product the the allProducts observable list. 
     @param  newProduct newProduct is an object of Product.
     */
    public static void addProduct(Product newProduct)
    {
        
        allProducts.add(newProduct);
            
    }
    /** This method will return the observable list of allProducts.
     @return Returns the observable list*/
     public static ObservableList<Product> getAllProducts()
     {
        
        return allProducts;
    }
    /** This method will return a filtered observable list of Products. 
     @return Returns the observableList of Products*/
     public static ObservableList<Product> getAllFilteredProducts()
     {
        
        return filteredProducts;
    }
    /** This method updates the selected product from the product table view.
     @param index Index is the integer to find the Product.
     @param newProduct is the replacement of the index. */
    public  static void updateProduct(int index,Product newProduct)
    {
      
      
      
      int index2 = -1;
      //Searches for an existing object 
       for(Product product: getAllProducts())
       {
          index2++;
          if(product.getId() == index) 
            getAllProducts().set(index2, newProduct);   
       }
          
    }
    
  /** This method will delete a selected Product from the table view. 
   @param selectedProduct is the id of the selected product.
   @return  Returns a boolean value, true if deleted and false if not. */
     public static boolean deleteProduct (int selectedProduct)
      {
        for(Product newProduct: getAllProducts())
        {
        if(newProduct.getId() == selectedProduct)
            return getAllProducts().remove(newProduct);
   
        }
      
           return false;
      }
     
      /** This method will search any string input in the search box. 
       @param productName is the name of the product to search.
       @return returns an observable list of an matches. */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        //IF NOT EMPTY CLEAR THE LIST
        if(!(getAllFilteredProducts().isEmpty()))
            getAllFilteredProducts().clear();
                    
                    
        for(Product newProduct : getAllProducts())
         {
           if(newProduct.getName().contains(productName))
           getAllFilteredProducts().add(newProduct);
         }
           if(getAllFilteredProducts().isEmpty())
            return getAllProducts();
           else
            return getAllFilteredProducts();
    }
     
    /** This method will search for any id and highlight any results. 
     @param productId is the id of the product. 
     @return returns an object of Product. */
     public  static Product lookupProduct(int productId)
    {
      //IF NOT EMPTY CLEAR THE LIST
       for(Product searchId : getAllProducts())
       {
              
        if(searchId.getId() == productId)
        {
        
         return searchId;
        }
       } 
         return null;
    }
     
    
//endof class   
    
}