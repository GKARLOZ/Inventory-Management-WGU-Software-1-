/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


/**
 *
 * @author gianm
 */

/** This class create an app that displays an add or modify screen. */
public class AddModifyPartController {

    @FXML
    private RadioButton partInHouse;
    @FXML
    private RadioButton partOutsourced;
    @FXML
    private TextField InHousePartId ;
    @FXML
    private TextField InHousePartName;
    @FXML
    private TextField InHousePartInv;
    @FXML
    private TextField InHousePartPrice;
    @FXML
    private TextField InHousePartMax;
    @FXML
    private TextField InHousePartMin;
    @FXML
    private ToggleGroup inHouseOrOutsourced;
    @FXML
      Label SpecialLabel;
    @FXML
    private TextField SpecialLabelText;
    @FXML
    private Label SpecialTitle;
    @FXML
    private Label IdNumber;
    
    //=========================Button Methods=================================
    
   /** This method take the user back to the inventory screen. 
    This method will show a confirmation dialog box to confirm.
    @param  event */
    @FXML
    private void onActionGoToMainMenu(ActionEvent event) throws IOException {
        
        Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Do you want to cancel? ");
        //This line will create an array called result of type Optional<ButtonType>
        Optional<ButtonType> result = alert.showAndWait();
        
        //if the result button is there (isPresent()) and it is pressed (.get()) does it equal ok? 
        //if yes, the do the follow
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
        
        //THESE lines of code will display the intended scene depending on the .getResource address. 
        Parent AddMenuParent = FXMLLoader.load(getClass().getResource("/View_Controller/InventoryView.fxml"));
        Scene AddMenuScene = new Scene(AddMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();       
        window.setScene(AddMenuScene);
        window.show();
        }
        
        
        
        }
      /** This method saves a new part to Parts observable list.
       Depending if the user is modifing or just adding a new part the method will 
       update the part or add. The method checks if the part is inHouse or Outsourced. 
       @param event.  */ 
    @FXML
    private void onActionSaveInfo(ActionEvent event) throws IOException {
        
       
        try{
          
       
        String name      = InHousePartName.getText();
        int    stock       = Integer.parseInt(InHousePartInv.getText());
        double price     = Double.parseDouble(InHousePartPrice.getText());
        int    max       = Integer.parseInt(InHousePartMax.getText());
        int    min       = Integer.parseInt(InHousePartMin.getText());
        
     
       if(!(Math.max(max, min) == max))
               throw new NumberFormatException("Max need to be bigger than Min");
       if(stock > max || stock < min)
              throw new NumberFormatException("Stock needs a value between Max and Min.");
          
      //The if statements will convert the specialLabel text into an int or string
      //depending on the radiobutton selected
      if(SpecialTitle.getText() == "Modify Part")
    {
        int id = Integer.parseInt(InHousePartId.getText());
         //InventoryController.getAllProducts().indexOf( partTableView.getSelectionModel().getSelectedItem());

        if(inHouseOrOutsourced.getSelectedToggle().equals(this.partInHouse)){
            System.out.println("The inHouse button was selected Save button");
             int machineId = Integer.parseInt(SpecialLabelText.getText()); 
             Inventory.updatePart(id, new InHousePart (id,name,price,stock,max,min, machineId));
        }
        if (inHouseOrOutsourced.getSelectedToggle().equals(this.partOutsourced)){
            System.out.println("The outsourced button was selected Save button");
            String companyName = SpecialLabelText.getText();
            Inventory.updatePart(id, new Outsourced (id,name,price,stock,min,max,companyName));
        }
        
    }
     
      
      else{
                int id = setId();
        if (inHouseOrOutsourced.getSelectedToggle().equals(this.partInHouse)){
            
            int machineId = Integer.parseInt(SpecialLabelText.getText());
            Inventory.addPart(new InHousePart( id, name,  price,  stock, max, min, machineId ));
            System.out.println("Added as inHouse");

        }
           
        if (inHouseOrOutsourced.getSelectedToggle().equals(this.partOutsourced))
        {
               String companyName      = SpecialLabelText.getText();
            Inventory.addPart(new Outsourced(id,name,price,stock,min,max,companyName));
            System.out.println("\n Added as Outsourced");
        }
      
      }
      
        //These lines will let you see it on the display of the main menu
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

   /** This method change the label to Machine if the part is in house. 
    @param event. */
    @FXML
    private void onActionChangeLabelIn(ActionEvent event) {
        
         // if(inHouseOrOutsourced.getSelectedToggle().equals(this.partInHouse));
        SpecialLabel.setText("Machine Id");
        
    }

    /** This method will change the label to Company Name if the part is Outsourced.
     @param event. */
    @FXML
    private void onActionChangeLabelOut(ActionEvent event) {
         
        //if(inHouseOrOutsourced.getSelectedToggle().equals(this.partOutsourced));
        SpecialLabel.setText("Company Name");
    }
    
   //============================================================================            
    
    /** This method displaies the values of the Part the user wants to modify. 
     It will also change the Machine Id to Company Name if the user want to change one 
     from the other. 
     @param part part is the Part object that will display. */
    public void displayPart (Part part)
    {
        //string.valueof(part.getid()) converted the integer into a string
    InHousePartId.setText(String.valueOf(part.getId()));
    InHousePartName.setText(part.getName());
    InHousePartInv.setText(String.valueOf(part.getStock()));
    InHousePartPrice.setText(String.valueOf(part.getPrice()));
    InHousePartMax.setText(String.valueOf(part.getMax()));
    InHousePartMin.setText(String.valueOf(part.getMin()));
    
    inHouseOrOutsourced.getSelectedToggle();
   
      
      if(part instanceof InHousePart){ 
          
                  SpecialLabel.setText("Machine Id");
                  partInHouse.setSelected(true);       
                  SpecialLabelText.setText(String.valueOf(((InHousePart) part).getMachineId()));
               
      }
      
       if(part instanceof Outsourced ){
          
                
                 SpecialLabel.setText("Company Name");
                 partOutsourced.setSelected(true);
                 SpecialLabelText.setText(((Outsourced) part).getCompanyName());
      }
     
    } 
    /** The method will change title to Modify Part if the user want to modify a Part.
     @param what is true if a Part is modified. */
    public void ChangeSpecialLabel(boolean what){
             if(what == true)
             SpecialTitle.setText("Modify Part");
        
     }
/** This method sets a new id to a new Part.
 @return returns a id type int. */
      public static int setId()
    {     
      
       
        int newId;
        
              int lastPart = Inventory.getAllParts().size();
             System.out.println("GetAllParts size of " + lastPart );
             if(lastPart == 0)
             {
                        newId = 1;
             }
              else{
             
                     int lastPartId = Inventory.getAllParts().get(lastPart-1).getId();
                     System.out.println("New id is " + lastPartId );

             newId = ++lastPartId;
                  }
        return newId;   
    }
     
   
     //end of class
    }
    
   

