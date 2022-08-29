/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;


import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author gianm
 */

/** This class controls the the inventory screen. */
public class InventoryController implements Initializable {
    
     /**
     * @param args the command line arguments
     */

    @FXML
    private   TableView<Part> partTableView;
    @FXML
    private   TableView<Product> productTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private javafx.scene.control.Button modifyButton;
    @FXML
    private TableColumn<Product,Integer> ProductIdCol;
    @FXML
    private TableColumn<Product,String> ProductNameCol;
    @FXML
    private TableColumn<Product,Integer> ProductInvenCol;
    @FXML
    private TableColumn<Product,Double> ProductPriceCol;
    @FXML
    private TextField searchProductTextField;
    @FXML
    private TextField SeachPartTextField;
    @FXML
    private javafx.scene.control.Button deleteButton;
    @FXML
    private javafx.scene.control.Button searchProductButton;  
    @FXML
    private javafx.scene.control.Button modifyProductButton;
    @FXML
    private javafx.scene.control.Button DeleteProductButton;
    @FXML
    private javafx.scene.control.Button addPartButton;
    @FXML
    private javafx.scene.control.Button SearchPartButton;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
       //the setCellValueFactory() method is called by a 
        //Table Column (i.e. partIdCol) to assign its cell a value from 
        // the the Object within the ObservableList. 
       
       //This statment lets you view all the objects in the table view.
          partTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
         productTableView.setItems(Inventory.getAllProducts());
        ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInvenCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
      
    }  
  /** This method will close the program when the exit button in selected.
   @param  event the event being fired which caused your function to be executed. */
   @FXML
   private void onActionExit(ActionEvent event) 
    {
    
     System.exit(0);
    
    }
    
   /** This method will delete when the button selected.
    @param event the event being fired which caused the function to be executed. */
   @FXML
   private void onActionDelete(ActionEvent event)
    {     try{
          Alert alert; 
          //This line will create an array called result of type Optional<ButtonType>
        Optional<ButtonType> result;
        if(partTableView.getSelectionModel().isEmpty())
        {
            throw new Exception();
               
        
        }//if the result button is there (isPresent()) and it is pressed (.get()) does it equal ok? 
        //if yes, the do the follow
        
        alert =  new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this part?");
        result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
           Inventory.deletePart(partTableView.getSelectionModel().getSelectedItem().getId()); 
        }
    }
    catch (Exception e){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Part to Delete. ");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
    
    
    }
    }
   
    /** This method will delete a product when selected.
     @param event the event being fired which caused the function to be executed. */
   @FXML
   void onActionDeleteProduct(ActionEvent event) throws IOException 
    {
        
         Alert alert; 
         
         try
         {
       if(productTableView.getSelectionModel().isEmpty())// if a product is not select, it throws an exception  
              throw new Exception();
       
       if((productTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() == 0)){
                  
            System.out.println("size " + productTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().size());
           alert =  new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete the product?");
            Optional<ButtonType> result = alert.showAndWait();
           
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
        
             deleteProduct(productTableView.getSelectionModel().getSelectedItem().getId());        
        }
       }
       else{
           alert = new Alert(Alert.AlertType.WARNING,"Product can't be deleted until all the associated parts are removed from this Product");
            //Optional<ButtonType>  result2 = alert.showAndWait();
       }
       }
   
    //end of try
   catch( Exception e ){

             alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Product to Delete. ");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );


   }
}
   
 
    
/** This method will filter your search with the text entered in the textfield. 
 @param event the event being fired which caused the function to be executed.*/
   @FXML
   private void onActionSearch(ActionEvent event) 
    {    
        //This method works but it wont search for a name now cus of my int
      
    
     try{
            
         int id = Integer.parseInt(SeachPartTextField.getText());
        partTableView.getSelectionModel().select(Inventory.lookupPart(id));//the id is put in the loopkup method 
        
          if(!(Inventory.getAllParts().contains(Inventory.lookupPart(id)))){//if the id is not in the observable list it throws an exception
              throw new NumberFormatException (" part id dosent exist ");
          
          }
         
     }
       
     catch( NumberFormatException e ) 
        {
          try{
             partTableView.setItems(Inventory.lookupPart(SeachPartTextField.getText()));
             
             if(Inventory.getAllFilteredParts().isEmpty()){ 
             throw new NumberFormatException(" No part contains that character or name. ");
             }
          }
          
          catch( NumberFormatException ex){
          
         //Need to specifiy what you want to happen when the exception is caught
            
            //tHESE LINES ARE FOR CREATEING A DIALOG BOX THAT WILL DISPLAY WITH A BUTTON 
            // tHE PROGRAM WONT CONTINUE UNTIL THE DIALOG BOX IS FINISHED
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please try a different id or name");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
                  
            }
          
          }
             
        }
        
             
    
          
    
   /** This method will search a product when button is selected.
    @param event the event being fired which caused the function to be executed. */
   @FXML
   void onActionSearchProduct(ActionEvent event) 
    {
    
     try{
            
          int id = Integer.parseInt(searchProductTextField.getText());
            productTableView.getSelectionModel().select(Inventory.lookupProduct(id));
        
          if(!(Inventory.getAllProducts().contains(Inventory.lookupProduct(id)))){
              throw new NumberFormatException (" part id dosent exist ");
          
          }
         
     }
       
     catch( NumberFormatException e ) 
        {
          try{
             String name =  searchProductTextField.getText();
             productTableView.setItems(Inventory.lookupProduct(name));
             
             if(Inventory.getAllFilteredProducts().isEmpty()){ 
             throw new NumberFormatException(" No part contains that character or name. ");
             }
          }
          
          catch( NumberFormatException ex){
          
         //Need to specifiy what you want to happen when the exception is caught
            
            //tHESE LINES ARE FOR CREATEING A DIALOG BOX THAT WILL DISPLAY WITH A BUTTON 
            // tHE PROGRAM WONT CONTINUE UNTIL THE DIALOG BOX IS FINISHED
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please try a different id or name");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
                  
            }
          
          }
             
    
    }

   /** This method will display a modify screen when button is selected. 
    @param  event the event being fired which caused the function to be executed. */
   @FXML
   private void onActionDisplayModify(ActionEvent event) throws IOException {
       
       
       try{
         
        if(partTableView.getSelectionModel().isEmpty())
          {
            throw new Exception();
           
          }
         
        //creating an object
        FXMLLoader loader = new FXMLLoader();
        //which fxml file to load
        
        loader.setLocation(getClass().getResource("/View_Controller/AddModifyPart.fxml"));
        //call the load method
        loader.load();
        
        AddModifyPartController MController = loader.getController();
      
        MController.displayPart(partTableView.getSelectionModel().getSelectedItem());
          MController.ChangeSpecialLabel(true);
        Parent AddMenuParent = loader.getRoot();
        Scene AddMenuScene = new Scene(AddMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();  
        window.setScene(AddMenuScene);
        window.show();
      
         }
    catch (Exception e){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Part to Modify. ");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
    
    
    }
    }
    /** THis method will change screens when the modify button is selected.
     @param event the event being fired which caused the function to be executed. */
   @FXML
   void onActionModifyProduct(ActionEvent event) throws IOException 
   {
     
     try{
         
        if(productTableView.getSelectionModel().isEmpty())
          {
            throw new Exception();
           
          }
         
        //creating an object
        FXMLLoader loader = new FXMLLoader();
        //which fxml file to load
        
        loader.setLocation(getClass().getResource("/View_Controller/AddModifyProduct.fxml"));
        //call the load method
        loader.load();
        
        AddModifyProductController MController = loader.getController();
      
        MController.displayProduct(productTableView.getSelectionModel().getSelectedItem());
        MController.ChangeSpecialLabel(true);
        Parent AddMenuParent = loader.getRoot();
        Scene AddMenuScene = new Scene(AddMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();  
        window.setScene(AddMenuScene);
        window.show();
      
         }
    catch (Exception e){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Product to Modify. ");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
    
    
    }
    }
    
   
   @FXML
   /** This method will change screens to the AddModify screen. 
     @param event the event being fired which caused the function to be executed. */
   private void onActionDisplayAdd(ActionEvent event) throws IOException 
    { 
       
        ChangeAddScreens(event,"AddModifyPart");
    }
   
     /** This method will change screens to the AddModify screen. 
     @param event the event being fired which caused the function to be executed. */
   @FXML
   void onActionAddProduct(ActionEvent event) throws IOException {
       
       ChangeAddScreens(event,"AddModifyProduct");
          
    }
   
   /** This method will change screens depending what is entered as "changeScreen" parameter. 
    @param event 
    @param  changeScreen changeScreen is the name of the file the you wish to load.
     * @throws java.io.IOException */
   public  void ChangeAddScreens(ActionEvent event,String changeScreen) throws IOException
   {
   
        Parent AddMenuParent = FXMLLoader.load(getClass().getResource("/View_Controller/"+ changeScreen +".fxml"));
        Scene AddMenuScene = new Scene(AddMenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(AddMenuScene);
        window.show();
       
   } 
   
   /** This method deletes Products from the products observable list.
    @param selectedProduct is the product the user wants to delete. 
    @return returns a true or false value. true meaning the product was deleted. */
     public static boolean deleteProduct (int selectedProduct)
      {
        for(Product newProduct: Inventory.getAllProducts())
        {
        if(newProduct.getId() == selectedProduct)
            return Inventory.getAllProducts().remove(newProduct);
   
        }
      
           return false;
      }
   
   
   
    
 //end of class   
}


