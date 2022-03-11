package controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Traitement;
import model.Utilisateur;

public class HomeAdminController  implements Initializable{

    @FXML
    public BorderPane borderPaneHome;
    
	@FXML
    private Button gestionLivreButton;

    @FXML
    private Button gestionUsersButton;

    @FXML
    private Button statistiqueButton;

    @FXML
    private Button accesDirectButton;

    @FXML
    private Button editAdminCompteButton;

    @FXML
    private Button fermerButton;
    Parent root=null;
    @FXML
    public VBox topHomeContent;

    @FXML
    public GridPane centerHomeContent;

    @FXML
    void onAccesDirect(ActionEvent event) {

    	try {
            FXMLLoader loader;
    		
    		loader=new FXMLLoader(getClass().getResource("/vue/slideBar.fxml"));
			loader.load();
			
			BarController barController=loader.getController();
			Traitement t=new Traitement();
			currentUser=t.connectUtilisateur(currentUser);
			barController.initData(currentUser);
			root=loader.getRoot();
			 Stage stg1 = new Stage();
				Scene scene = new Scene(root);
				stg1.setScene(scene);
				stg1.setResizable(true);
				stg1.centerOnScreen();
				stg1.setTitle("Bibliothéque des Livres");
				stg1.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));
				
					stg1.setWidth(1400);
					stg1.setHeight(800);
				
				stg1.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }

    @FXML
    void onEditAdminCompteButton(ActionEvent event) {

    	try {
			root = FXMLLoader.load(getClass().getResource("/vue/GestionAdmin/EditAdmin.fxml"));
			 Stage stg1 = new Stage();
				Scene scene = new Scene(root);
				stg1.setScene(scene);
				stg1.setResizable(true);
				stg1.centerOnScreen();
				stg1.setTitle("Modifier vos coordonnées");
				stg1.setResizable(false);		
				stg1.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));
				stg1.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onFermerButton(ActionEvent event) {

    	Stage stage=(Stage)borderPaneHome.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onGestionLivreButton(ActionEvent event) {

    	
    	try {
    		root = FXMLLoader.load(getClass().getResource("/vue/GestionBook/BarGestionBook.fxml"));
    		
			
			Stage getionBookStage = new Stage();
			getionBookStage.setWidth(1800);
			getionBookStage.setHeight(900);
		//	getionBookStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toString()))	;
			Scene scene = new Scene(root);
			getionBookStage.setScene(scene);
			getionBookStage.setResizable(true);
			getionBookStage.centerOnScreen();
			getionBookStage.setTitle("Gestion des livres");
			getionBookStage.initStyle(StageStyle.DECORATED);
			getionBookStage.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));
			getionBookStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }

    @FXML
    void onGestionUsersButton(ActionEvent event) {

    	try {
    		borderPaneHome.getChildren().removeAll();
			root = FXMLLoader.load(getClass().getResource("/vue/GestionUser/BarGestionUser.fxml"));
			Stage getionBookStage = new Stage();
			getionBookStage.setWidth(1500);
		//	getionBookStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toString()))	;
			Scene scene = new Scene(root);
			getionBookStage.setScene(scene);
			getionBookStage.setResizable(true);
			getionBookStage.centerOnScreen();
			getionBookStage.setTitle("Gestion des utilisateurs");
			getionBookStage.initStyle(StageStyle.DECORATED);
			getionBookStage.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));
			getionBookStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }

    @FXML
    void onStatistique(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("/vue/Statistique/homeStatistique.fxml"));
			 Stage stg1 = new Stage();
				Scene scene = new Scene(root);
				stg1.setScene(scene);
				stg1.setResizable(true);
				stg1.setWidth(1000);
				stg1.setHeight(850);
				stg1.centerOnScreen();
				stg1.setTitle("Statistiques");
				stg1.getIcons().add(new Image(getClass().getResource("/images/book.png").toString()));		
				stg1.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    
    public void resetContent() {
    
    	borderPaneHome.setTop(topHomeContent);
    	borderPaneHome.setCenter(centerHomeContent);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public Utilisateur currentUser;

	public void initData(Utilisateur user) {
		currentUser=user;
		
	}
   

}
