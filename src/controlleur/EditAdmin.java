package controlleur;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.Traitement;
import model.Utilisateur;

public class EditAdmin implements Initializable{

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField motDePasseField;

    @FXML
    void onModifier(MouseEvent event) {

    	Traitement t=new Traitement();
    	selectedUser.setNom(nomField.getText());
    	selectedUser.setPrenom(prenomField.getText());
    	selectedUser.setEmail(emailField.getText());
    	selectedUser.setMotDePasse(motDePasseField.getText());
    	t.updateAdmin(selectedUser);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Succés");
		alert.setHeaderText(null);
		alert.setContentText("L'utilisateur est bien modifié");

		alert.showAndWait();
    }

    Utilisateur selectedUser;
    public void initData(Utilisateur user) {
	
		   
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Traitement t=new Traitement();
		selectedUser=t.getAdmin();
		  nomField.setText(selectedUser.getNom());
		   prenomField.setText(selectedUser.getPrenom());
		    emailField.setText(selectedUser.getEmail());
		    motDePasseField.setText(selectedUser.getMotDePasse());
		
	}
}
