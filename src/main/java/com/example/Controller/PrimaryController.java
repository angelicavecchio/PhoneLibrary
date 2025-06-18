package com.example.Controller;

import java.io.IOException;

import com.example.Model.Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

     @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField birthdayField;


    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
    this.primaryStage = stage;
}


   
    @FXML
     private void initialize() {
    firstNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getFirstName()));
    
    lastNameColumn.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getLastName()));

  
     //quando inserisco una persona nella tabella devo vedere i suoi dati 
    personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue)
    );
     }
    


   public void showPersonDetails(Person p){
 
    if(p!=null){

      firstNameField.setText(p.getFirstName());
      lastNameField.setText(p.getLastName());
      cityField.setText(p.getCity());
      streetField.setText(p.getStreet());
      postalCodeField.setText(Integer.toString(p.getPostalCode()));
      birthdayField.setText(p.getBirthday().toString());
    }else{

        firstNameField.clear();
        lastNameField.clear();
        streetField.clear();
        cityField.clear();
        postalCodeField.clear();
        birthdayField.clear();
    }

   }


       @FXML
     private void handleNewPerson() {

        Person person = new Person();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/PersonEditDialog.fxml"));
            VBox root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Add Person");


            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

             dialogStage.showAndWait();

        if (controller.isOkClicked()) {
            personTable.getItems().add(person);
        }





        }catch(IOException e){
            e.printStackTrace();
        }




     }







        @FXML
     private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();

        if(selectedPerson!=null){
            try{
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/PersonEditDialog.fxml"));
               VBox root = loader.load();
               Stage dialogStage = new Stage();
               dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
               Scene scene = new Scene(root);
               dialogStage.setTitle("Edit Person");
               dialogStage.setScene(scene);
             
               
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(selectedPerson);

            // Mostra il dialog e aspetta finché non è chiuso
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                // Refresh o aggiornamenti
                showPersonDetails(selectedPerson);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Nessuna persona selezionata
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");
        alert.showAndWait();
    }




        }



        @FXML
     private void handleDeletePerson() {

        int selectedIndex= personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            personTable.getItems().remove(selectedIndex);
            
        }else{
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();

        }


     }

    



    }