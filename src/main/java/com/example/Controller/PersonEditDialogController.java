package com.example.Controller;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.example.Model.Person;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;


      @FXML
    private void initialize() {
    }


    public void setPerson(Person person){
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        cityField.setText(person.getCity());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
         if (person.getBirthday() != null) {
        birthdayField.setText(person.getBirthday().toString());
    } else {
        birthdayField.setText("");
    }
    }



    public boolean isOkClicked(){
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
}


    @FXML

    public void handleOK(){
       System.out.println("Pulsante OK premuto");    //per debug


    if (isInputValid()) {
        System.out.println("Input valido, aggiorno i dati...");    //per debug


        person.setFirstName(firstNameField.getText());
        person.setLastName(lastNameField.getText());
        person.setStreet(streetField.getText());
        person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
        person.setCity(cityField.getText());

        try {
            LocalDate date = LocalDate.parse(birthdayField.getText());
            person.setBirthday(date);
        } catch (DateTimeParseException e) {
            System.out.println("Errore parsing data: " + e.getMessage());
        }

        okClicked = true;
        dialogStage.close();
    } else {
        System.out.println("Input NON valido");    //per debug

    }
}





    
    private boolean isInputValid() {
        String errorMessage = "";


       if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
             errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } 

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }

    }

    @FXML
    private void handleCancel() {
    dialogStage.close();

    }
        
    
}


