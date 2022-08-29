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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gianm
 */

/** This class controls the Add and Modify screen. */
public class AddModifyProductController implements Initializable {

    @FXML
    private TextField ProductNameTF;
    @FXML
    private TextField ProductInvTF;
    @FXML
    private TextField ProductPriceTF;
    @FXML
    private TextField ProductMinTF;
    @FXML
    private TextField ProductMaxTF;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part,Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part,Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private Button addProduct;
    @FXML
    private TextField ProductIdTF;
    @FXML
     private TableView<Part> associatedPartTableView;
    @FXML
    private TableColumn<Part,Integer> ProductIdCol;
    @FXML
    private TableColumn<Part,String> ProductNameCol;
    @FXML
    private TableColumn<Part,Integer> ProductInvCol;
    @FXML
    private TableColumn<Part, Double> ProductPriceCol;
    @FXML
    private Button saveProductButton;
    @FXML
    private Button RemoveAP;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Label SpecialLabel;
    
    //private String AddLabel = "Add Product";
    
    private static ObservableList<Part> TempList = FXCollections.observableArrayList();

    /** This method initializes the controller class. 
     @param url
     @param rb */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            partTableView.setItems(Inventory.getAllParts());
        
       //This line of code will let me see all the part in the tableview 
       partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
             associatedPartTableView.setItems(getTempList());
      
        //These lines are for the associated part table 
         ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // TODO
    }    

    //============================================================================
    //===============================Methods=======================================
    
    
  /** This method adds a newPart to the TempList for it to appear in the table view. 
   @param newPart is a Part object that will be added. */
     public void addPartToAPartTable(Part newPart)
      {
     
       TempList.add(newPart);
     
      }
     
     /** This method returns an observableList. 
      @return returns the TempList. */
     public static ObservableList<Part> getTempList()
     {
     
      return TempList;
     
     }
     
    /** This method will remove ALL the pars in the TempList.
     */
     public static void deleteTempList()
     {
         
        getTempList().removeAll(TempList);
        
     }
             
     
     /** This method will delete a selected Product from the table view. 
   @param selectedProduct is the id of the selected product.
   @return  Returns a boolean value, true if deleted and false if not. */
     public static boolean deletePartTempList (int selectedProduct)
      {
        for(Part part: getTempList())
        {
        if(part.getId() == selectedProduct)
            return getTempList().remove(part);
   
        }
      
           return false;
      }
     
     
    /** This method will display a product info in the text fields.
     * This method will get all the associated parts from the products observable 
     * list and add them to the TempList to display on the table view.
     @param product is the Product that will be displayed. */
     public void displayProduct (Product product)
    {
          //InventoryController.DisplayArray(product.getId());  
        //string.valueof(part.getid()) converted the integer into a string
    ProductIdTF.setText(String.valueOf(product.getId()));
    ProductNameTF.setText(product.getName());
    ProductInvTF.setText(String.valueOf(product.getStock()));
    ProductPriceTF.setText(String.valueOf(product.getPrice()));
    ProductMaxTF.setText(String.valueOf(product.getMax()));
    ProductMinTF.setText(String.valueOf(product.getMin()));
     
     associatedPartTableView.setItems(product.getAllAssociatedParts());
     
     for(Part newPart : product.getAllAssociatedParts())
        {
     
          addPartToAPartTable(newPart);
         
        }
     
     associatedPartTableView.setItems(getTempList());
     
    }
     
     
     /**This method will change the add label to modify label.
      * This method changes the label to show on the screen.
      @param what what is true or false.*/
     public void ChangeSpecialLabel(boolean what)
     {
         if(what == true)
             SpecialLabel.setText("Modify Product");
        
     }
    /** This method sets a new Id to a new Product.
     @return returns the int id. */
   public static int setId()
    {          
        int newId;
        
            int lastPart = Inventory.getAllProducts().size();
                if(lastPart == 0)
             {
                        newId = 1;
             }
              else{
            int lastPartId = Inventory.getAllProducts().get(lastPart-1).getId();
             
             newId = ++lastPartId;
                }
        return newId;     
    } 
 
   
   //============================================================================ 
                                 //Action Methods
   
   //===========================================================================
   
   /** This button method will add a Part from the top table view to the bottom table view. 
    This method adds a part to the TempList and show it on the bottom table view. 
    @param event. */
    @FXML
    private void onActionAddProduct(ActionEvent event) throws IOException 
     {
       addPartToAPartTable( partTableView.getSelectionModel().getSelectedItem());
     }

    /** This method saves the Product and/or the associated parts. 
     The method could save a new Product or save a modified product.
     After saving the product or the associated parts it will delete 
     all the parts in the TempList.
     The TempList need to empty for the next associated parts to show.
     
    @param event. */
    @FXML
    private void onActionSaveProduct(ActionEvent event) throws IOException {
        
        
        try{
            
        int  id;
        String name      = ProductNameTF.getText();
        int    stock     = Integer.parseInt(ProductInvTF.getText());
        double price     = Double.parseDouble(ProductPriceTF.getText());
        int    max       = Integer.parseInt(ProductMaxTF.getText());
        int    min       = Integer.parseInt(ProductMinTF.getText());
        
        
    //The if statements will check if max is bigger than min and stock in between. 
         if(!(Math.max(max, min) == max))
               throw new NumberFormatException("Max need to be bigger than Min");
       if(stock > max || stock < min)
              throw new NumberFormatException("Stock needs a value between Max and Min.");
          
        //The if statements will modify or add depending on the SpecialLabel text.
        if(SpecialLabel.getText() == "Modify Product"){ 
                 System.out.print("Modify text is read");
                 id = Integer.parseInt(ProductIdTF.getText());
                 Product newProduct = new Product(id,name,price,stock,min,max);
                Inventory.updateProduct(id,newProduct);
          
          }
          
          else{  
              id = setId();
              Product newProduct = new Product(id,name,price,stock,min,max);
               Inventory.addProduct(newProduct);
        System.out.println(SpecialLabel.getText()+ "\nThe index of the new Product is ");
        System.out.println(Inventory.getAllProducts().indexOf(newProduct));
          
               }
          
          
              //tHIS METHOD WILL ERASE THE ASSOCIATED TABLE VIEW TO START FROM ZERO
              deleteTempList();     
               
               
       
       // Takes the user back to the Inventory screen after saving 
        Parent AddMenuParent = FXMLLoader.load(getClass().getResource("/View_Controller/InventoryView.fxml"));
        Scene AddMenuScene = new Scene(AddMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(AddMenuScene);
        window.show();
 
            
        }
        
        catch( NumberFormatException e ) 
        {
        //Need to specifiy what you want to happen when the exception is caught
            
            //tHESE LINES ARE FOR CREATEING A DIALOG BOX THAT WILL DISPLAY WITH A BUTTON 
            // tHE PROGRAM WONT CONTINUE UNTIL THE DIALOG BOX IS FINISHED
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter valid values for each text field. Min should be less than Max; and Inv should be between those two values.");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " + e);
            System.out.println("Exception " + e.getMessage());
               
  
        }
        
        
        
    }
    /** This method will remove an associated part from the bottom table view. 
     This method will show a confirmation to ensure if the user wants to delete the part. 
     @param event */
    @FXML
    private void onActionDeleteFromAssPar(ActionEvent event) {
        
     
       try{
          Alert alert; 
          //This line will create an array called result of type Optional<ButtonType>
        Optional<ButtonType> result;
        if(associatedPartTableView.getSelectionModel().isEmpty())
        {
            throw new Exception();
               
        
        }//if the result button is there (isPresent()) and it is pressed (.get()) does it equal ok? 
        //if yes, the do the follow
        
        alert =  new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete this part?");
        result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
           deletePartTempList(associatedPartTableView.getSelectionModel().getSelectedItem().getId()); 
        }
    }
    catch (Exception e){
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a Part to remove. ");
            alert.showAndWait();
            
            // THE E VARIABLE CAN BE USED FOR MESSAGE LIKE THE FOLLOWING 
            //System.out.println("Please enter valid values in the text fields");
            System.out.println("Exception " );
      
        }
    }

    /** This method searches the id or name entered in the text field. 
     This method check for the id or a similar character in the name of a part.
     @param event */
    @FXML
    private void onActionSearch(ActionEvent event) {
        
       
     try{
            
         int id = Integer.parseInt(searchTF.getText());
        partTableView.getSelectionModel().select(Inventory.lookupPart(id));
        
          if(!(Inventory.getAllParts().contains(Inventory.lookupPart(id)))){
              throw new NumberFormatException (" part id dosent exist ");
          
          }
         
     }
       
     catch( NumberFormatException e ) 
        {
          try{
             partTableView.setItems(Inventory.lookupPart(searchTF.getText()));
           if(Inventory.getAllFilteredParts().isEmpty())
           { 
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

    
    /** This method will take the user back to the inventory screen.
     The user will need to answer the  confirmation dialog box.
     * The method will also delete the parts in the TempList.
     @param  event */
    @FXML
    private void onActionGoToMainMenu(ActionEvent event) throws IOException {
        
        Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Do you want to cancel. ");
        //This line will create an array called result of type Optional<ButtonType>
        Optional<ButtonType> result = alert.showAndWait();
        
        //if the result button is there (isPresent()) and it is pressed (.get()) does it equal ok? 
        //if yes, the do the follow
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
          deleteTempList();
        //THESE lines of code will display the intended scene depending on the .getResource address. 
        Parent AddMenuParent = FXMLLoader.load(getClass().getResource("/View_Controller/InventoryView.fxml"));
        Scene AddMenuScene = new Scene(AddMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(AddMenuScene);
        window.show();
        }
        
    }
   
   
//end of class
}
