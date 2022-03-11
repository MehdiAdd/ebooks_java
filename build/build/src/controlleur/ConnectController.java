package controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Traitement;
import model.Utilisateur;

public class ConnectController implements Initializable {

	@FXML
	private AnchorPane generalPane;

	@FXML
	private AnchorPane connectPane;

	@FXML
	private TextField emailFieldConnect;

	@FXML
	private Button connectButtonConnect;

	@FXML
	private Button sinscrireButtonConnect;

	@FXML
	private PasswordField passFieldConnect;

	@FXML
	private AnchorPane inscriptionPane;

	@FXML
	private TextField emailFieldInscription;

	@FXML
	private TextField nomUserFieldInscription;

	@FXML
	private Button connectButtonInscription;

	@FXML
	private Button sinscrireButtonInscription;

	@FXML
	private PasswordField passFieldInscription;

	@FXML
	private TextField prenomUserFieldInscription;
	@FXML
	private Label erreurLabel;
	FXMLLoader loader;


	@FXML
	void onConnectButtonConnect(ActionEvent event) {

		Parent root = null;
		Traitement t = new Traitement();
		Utilisateur user = new Utilisateur();
		String titre = null;
	
		boolean show = false;
		try {
			System.out.println("ok1");
			user.setEmail(emailFieldConnect.getText());
			user.setMotDePasse(passFieldConnect.getText());
			Utilisateur simple=t.connectUtilisateur(user);
			Utilisateur admin=t.connectAdmin(user);
			if ( admin != null) {
				
				loader=new FXMLLoader(getClass().getResource("/vue/HomeAdmin.fxml"));
				loader.load();
				HomeAdminController homeAdmin=loader.getController();
				homeAdmin.initData(admin);
				root=loader.getRoot();
				titre="Administration de la Bibliothéque";
				show = true;
			} else if (simple != null) {
				
				
				loader=new FXMLLoader(getClass().getResource("/vue/slideBar.fxml"));
				loader.load();
				
				BarController barController=loader.getController();
				barController.initData(simple);
				root=loader.getRoot();
				titre="E-Book";
				show = true;
			} else {
				new Thread(new Runnable() {

					@Override
					public void run() {
						erreurLabel.setVisible(true);
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						erreurLabel.setVisible(false);

					}

				}).start();;
			}
			if (show) {
				
				Stage stg1 = new Stage();
				Stage stage= (Stage) generalPane.getScene().getWindow();
				Scene scene = new Scene(root);
				stg1.setScene(scene);
				stg1.setResizable(true);
				stg1.centerOnScreen();
				stg1.setTitle(titre);
				stg1.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));
				if(titre.equals("E-Book")) {
					stg1.setWidth(1400);
					stg1.setHeight(800);
				}
				stage.close();
				stg1.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onConnectButtonInscription(ActionEvent event) {

		TranslateTransition translateTransition = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition.setNode(connectPane);

		// Setting the value of the transition along the x axis.
		translateTransition.setFromX(inscriptionPane.getLayoutX());
		translateTransition.setFromY(inscriptionPane.getHeight());
		translateTransition.setToY(0);
		connectPane.setVisible(true);

		// Setting the cycle count for the transition
		translateTransition.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition.setAutoReverse(false);

		// Playing the animation
		translateTransition.play();
		translateTransition.setOnFinished((e) -> inscriptionPane.setVisible(false));
		TranslateTransition translateTransition1 = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition1.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition1.setNode(inscriptionPane);

		// Setting the value of the transition along the x axis.
		translateTransition1.setFromY(0);
		translateTransition1.setToY(-generalPane.getHeight());

		// Setting the cycle count for the transition
		translateTransition1.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition1.setAutoReverse(false);

		// Playing the animation
		translateTransition1.play();
		// translateTransition.setOnFinished((e) -> inscriptionPane.setVisible(false));

	}

	@FXML
	void onSinscrireButtonConnect(ActionEvent event) {

		TranslateTransition translateTransition = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition.setNode(inscriptionPane);

		// Setting the value of the transition along the x axis.
		translateTransition.setFromX(connectPane.getLayoutX());
		translateTransition.setFromY(generalPane.getHeight());
		translateTransition.setToY(0);
		inscriptionPane.setVisible(true);

		// Setting the cycle count for the transition
		translateTransition.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition.setAutoReverse(false);

		// Playing the animation
		translateTransition.play();
		translateTransition.setOnFinished((e) -> connectPane.setVisible(false));
		TranslateTransition translateTransition1 = new TranslateTransition();

		// Setting the duration of the transition
		translateTransition1.setDuration(Duration.millis(1000));

		// Setting the node for the transition
		translateTransition1.setNode(connectPane);

		// Setting the value of the transition along the x axis.
		translateTransition1.setFromY(0);
		translateTransition1.setToY(-generalPane.getHeight());

		// Setting the cycle count for the transition
		translateTransition1.setCycleCount(1);

		// Setting auto reverse value to false
		translateTransition1.setAutoReverse(false);

		// Playing the animation
		translateTransition1.play();
		// translateTransition.setOnFinished((e) -> inscriptionPane.setVisible(false));

	}

	@FXML
	void onSinscrireButtonInscription(ActionEvent event) {

		Traitement t=new Traitement();
		Utilisateur user=new Utilisateur();
		if(nomUserFieldInscription.getText().equals("") || prenomUserFieldInscription.getText().equals("") || 
				emailFieldInscription.getText().equals("") ||
				passFieldInscription.getText().equals("")) {
			Alert confirm = new Alert(AlertType.WARNING);
			confirm.setTitle("Information ");
			confirm.setHeaderText(null);
			confirm.setContentText("Vous devez remplir tous les champs");

			confirm.showAndWait();
		}
		else {
			user.setNom(nomUserFieldInscription.getText());
			user.setPrenom(prenomUserFieldInscription.getText());
			user.setEmail(emailFieldInscription.getText());
			user.setMotDePasse(passFieldInscription.getText());
			t.inscription(user);
			Alert confirm = new Alert(AlertType.INFORMATION);
			confirm.setTitle("Information ");
			confirm.setHeaderText(null);
			confirm.setContentText("Vous avez bien inscrit");

			confirm.showAndWait();
		}
	}
	@FXML
	public void onClose(MouseEvent event) {
		Stage stage= (Stage) generalPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	Label fermer;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fermer.setGraphic(new ImageView(getClass().getResource("/images/fermer.png").toString()));

	}

}
