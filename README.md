
# :ledger: Inventory-Management-WGU-Software-1 :technologist:

This application lets the user create Product and Part objects(OOP). Since every part needs to have certain variables, an abstract Part class was created. For that reason, the Part class was extended in the inHouse and Outsourced concrete classes, inheriting the methods from the Part class. The abstract Part class requires the id, name, price, stock, min, and max which are added in the constructor.

The application lets you add, modify or delete a part with certain restrictions. The part needs to have a unique identification and can't be repeated. The application also lets you add, modify, and delete a product with a unique identification. The product can not be deleted if a part is associated with the product.
 
![IMS(MainPanel)](https://user-images.githubusercontent.com/20764455/188293972-b3fcc527-446b-4e5d-8571-0d8fb3716414.png)

##  :trollface: Clone this project 

 git clone (copied linked)


## :warning: Java in VSCODE :warning: 

 Install Java for vs code if you don't already have it. 
 Use this link: https://code.visualstudio.com/docs/languages/java

 ## :warning: Important Dependencies! :warning:

 In order to run this program in VS Code, you need to have JavaFX installed.
 
 You could find your JAVAFX version in this link: https://gluonhq.com/products/javafx/
 
 JavaFX jars need to be added in the Java Projects/Referenced Libraries folder to run this program. 

 Once you installed JAVAFX, copy the path of the lib folder (from the JAVAFX that was installed). It should look something like this -->  C:/JavaOne/javafx-sdk-18.0.2/lib

 open the .vscode/lauch.json file and replace the current path with your own path.  It should look something like this -->  "vmArgs": "--module-path C:/JavaOne/javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml",


 ## :rocket:	Lets GO! :rocket:	

 Now open the src/Model/Inventory.java file  
 Click run on top of the main method 
    
     Run | Debug
     public static void main(String[] args) {

 ## VS CODE GUIDELINES 

Here is a guideline to help you get started to write Java code in Visual Studio Code.

### Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


 
