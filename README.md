
 ## Clone this project 

 git clone (copied linked)


 ## Java in VSCODE 

 Install Java for vs code if you don't already have it. 
 Use this link: https://code.visualstudio.com/docs/languages/java

 ## Important Dependencies!

 In order to run this program in VS Code, you need to have JavaFX installed.
 
 You could find your JAVAFX version in this link: https://gluonhq.com/products/javafx/
 
 JavaFX jars need to be added in the Java Projects/Referenced Libraries folder to run this program. 

 Once you installed JAVAFX, copy the path of the lib folder (from the JAVAFX that was installed). It should look something like this -->  C:/JavaOne/javafx-sdk-18.0.2/lib

 open the .vscode/lauch.json file and replace the current path with your own path.  It should look something like this -->  "vmArgs": "--module-path C:/JavaOne/javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml",


 ## Lets GO!

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


 
