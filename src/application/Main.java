package application;
	
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ConnexionDB;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {

			try {
				ConnexionDB.getFirstconnection();
				 Statement st = (Statement) (ConnexionDB.cnx).createStatement();
				ResultSet res=st.executeQuery("select schema_name from information_schema.schemata where schema_name='ebook'");
				if(!res.next()) {
				int nombre =st.executeUpdate("create database if not exists ebook");
				System.out.println(nombre);
				ConnexionDB.cnx.close();
				
					ConnexionDB.getconnection();
					 st = (Statement) (ConnexionDB.cnx).createStatement();
					 st.executeUpdate("CREATE TABLE admin(\r\n"
					 		+ "        id         Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        nom        Varchar (30) NOT NULL ,\r\n"
					 		+ "        prenom     Varchar (30) NOT NULL ,\r\n"
					 		+ "        email      Varchar (50) NOT NULL ,\r\n"
					 		+ "        motDePasse Varchar (50) NOT NULL\r\n"
					 		+ "	,CONSTRAINT admim_PK PRIMARY KEY (id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE utilisateur(\r\n"
					 		+ "        id         Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        nom        Varchar (30) NOT NULL ,\r\n"
					 		+ "        prenom     Varchar (30) NOT NULL ,\r\n"
					 		+ "        email      Varchar (50) NOT NULL ,\r\n"
					 		+ "        motDePasse Varchar (50) NOT NULL \r\n"
					 		
					 		+ "	,CONSTRAINT utilisateur_PK PRIMARY KEY (id)\r\n"
					 		+ "\r\n"
					 		
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE auteur(\r\n"
					 		+ "        Id     Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        nom    Varchar (30) NOT NULL \r\n"
					 		//+ "        prenom Varchar (30) NOT NULL\r\n"
					 		+ "	,CONSTRAINT auteur_PK PRIMARY KEY (Id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE genre(\r\n"
					 		+ "        id    Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        genre Varchar (50) NOT NULL\r\n"
					 		+ "	,CONSTRAINT genre_PK PRIMARY KEY (id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE livre(\r\n"
					 		+ "        id             Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        titre          Varchar (50) NOT NULL ,\r\n"
					 		+ "        extrait          Varchar (500) NOT NULL ,\r\n"
					 		+ "        dateDeParition Date NOT NULL ,\r\n"
					 		+ "        nombrePage     Int NOT NULL ,\r\n"
					 		+ "        pdf            LongBlob NOT NULL ,\r\n"
					 		+ "        audio          LongBlob ,\r\n"
					 		+ "        couverture          LongBlob ,\r\n"
					 		+ "        id_genre       Int NOT NULL ,\r\n"
					 		+ "        Id_auteur      Int NOT NULL\r\n"
					 		+ "	,CONSTRAINT livre_PK PRIMARY KEY (id)\r\n"
					 		+ "\r\n"
					 		
					 		+ "	,CONSTRAINT livre_genre0_FK FOREIGN KEY (id_genre) REFERENCES genre(id)\r\n"
					 		+ "	,CONSTRAINT livre_auteur1_FK FOREIGN KEY (Id_auteur) REFERENCES auteur(Id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE note(\r\n"
					 		+ "        id             Int NOT NULL AUTO_INCREMENT ,\r\n"
					 		+ "        nombreEtoile   Int NOT NULL ,\r\n"
					 		+ "        id_livre       Int NOT NULL ,\r\n"
					 		+ "        id_utilisateur Int NOT NULL\r\n"
					 		+ "	,CONSTRAINT note_PK PRIMARY KEY (id)\r\n"
					 		+ "\r\n"
					 		+ "	,CONSTRAINT note_livre_FK FOREIGN KEY (id_livre) REFERENCES livre(id)\r\n"
					 		+ "	,CONSTRAINT note_utilisateur0_FK FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("CREATE TABLE favoris(\r\n"
					 		+ "        id Int NOT NULL AUTO_INCREMENT , \r\n"
					 		+"id_utilisateur Int NOT NULL\r\n"
					 		+ "	,CONSTRAINT favoris_PK PRIMARY KEY (id)\r\n"
					 		+ "	,CONSTRAINT favoris_utilisateur_FK FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("\r\n"
					 		+ "CREATE TABLE etre_constitue_de(\r\n"
					 		+ "        id         Int NOT NULL ,\r\n"
					 		+ "        id_favoris Int NOT NULL\r\n"
					 		+ "	,CONSTRAINT etre_constitue_de_PK PRIMARY KEY (id,id_favoris)\r\n"
					 		+ "\r\n"
					 		+ "	,CONSTRAINT etre_constitue_de_livre_FK FOREIGN KEY (id) REFERENCES livre(id)\r\n"
					 		+ "	,CONSTRAINT etre_constitue_de_favoris0_FK FOREIGN KEY (id_favoris) REFERENCES favoris(id)\r\n"
					 		+ ")\r\n"
					 		+ "");
					 st.executeUpdate("insert into admin(nom,prenom,email,motDePasse) values('admin','admin','admin','admin')");
					 st.executeUpdate("insert into utilisateur(nom,prenom,email,motDePasse) values('admin','admin','admin','admin')");
					 st.executeUpdate("insert into favoris(id_utilisateur) values(1)");
					 ConnexionDB.cnx.close();
					 
				}
			
			
			
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Alert");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	        Parent root = FXMLLoader.load(getClass().getResource("/vue/connect.fxml"));        
	        Scene scene = new Scene(root);
	        scene.setFill(Color.TRANSPARENT);
	        stage.setScene(scene);
	        stage.initStyle(StageStyle.TRANSPARENT);
	        stage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
