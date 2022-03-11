package controlleur;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.Livre;
import model.Traitement;

	public class AddBookController  implements Initializable {

		@FXML
	    private ImageView coverImage;

	    @FXML
	    private Button selectionnerCovertureButton;

	    @FXML
	    private TextField titreField;
	
	    @FXML
	    private TextField dateDeParitionField;

	    @FXML
	    private TextField auteurField;

	    @FXML
	    private TextField nombreDePagesField;

	    @FXML
	    private TextField genreField;

	    @FXML
	    private TextArea extraitArea;

	    @FXML
	    private Button selectionnerPdfButton;

	    @FXML
	    private Label selectionnerPdfLabel;

	    @FXML
	    private Label selectionnerAudioLabel;

	    @FXML
	    private Button selectionnerAudioButton;

	    Livre livre=new Livre();
	 

	 

	    @FXML
	    void onSelectionnerAudioButton(ActionEvent event) throws IOException {
	    	try {
				FileChooser directoryChooser = new FileChooser();

				/*
				 * directoryChooser.getExtensionFilters().addAll( new
				 * ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Image Files",
				 * "*.png", "*.jpg", "*.gif"), new ExtensionFilter("Audio Files", "*.wav",
				 * "*.mp3", "*.aac"), new ExtensionFilter("All Files", "*.*"));
				 */

				File file = directoryChooser.showOpenDialog(null);

				byte[] fileContent = getByteArrayFromFile(file);
				livre.setAudio(fileContent); 
				selectionnerAudioLabel.setText(file.getAbsolutePath());
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	    }

	    @FXML
	    void onSelectionnerPdfButton(ActionEvent event) throws IOException {
	    	try {
				FileChooser directoryChooser = new FileChooser();

				/*
				 * directoryChooser.getExtensionFilters().addAll( new
				 * ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Image Files",
				 * "*.png", "*.jpg", "*.gif"), new ExtensionFilter("Audio Files", "*.wav",
				 * "*.mp3", "*.aac"), new ExtensionFilter("All Files", "*.*"));
				 */

				File file = directoryChooser.showOpenDialog(null);

				
				byte[] fileContent = getByteArrayFromFile(file);
				livre.setpDF(fileContent);  
				selectionnerPdfLabel.setText(file.getAbsolutePath());
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                 
	    }

	    @FXML
		void onselectionnerCovertureButton(ActionEvent event) {
			try {
				FileChooser directoryChooser = new FileChooser();

				/*
				 * directoryChooser.getExtensionFilters().addAll( new
				 * ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Image Files",
				 * "*.png", "*.jpg", "*.gif"), new ExtensionFilter("Audio Files", "*.wav",
				 * "*.mp3", "*.aac"), new ExtensionFilter("All Files", "*.*"));
				 */

				File file = directoryChooser.showOpenDialog(null);

				URL url;

				url = file.toURI().toURL();
				Image image = new Image(url.toExternalForm());
				coverImage.setImage(image);
				byte[] fileContent = getByteArrayFromFile(file);
				livre.setCouverture(fileContent);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	               

	    }
	    
	    @FXML
	    void  onAjouterBookButton(MouseEvent event) throws ParseException {

	    	if(livre.getCouverture()==null) {
	    		Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attetion");
				alert.setHeaderText(null);
				alert.setContentText("veuillez selectionner une photo de couverture");

				alert.showAndWait();
	    	}else if(livre.getpDF()==null) {
	    		Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attetion");
				alert.setHeaderText(null);
				alert.setContentText("veuillez selectionner un pdf pour le livre");

				alert.showAndWait();
	    	}else {
	    		try {
	    		addTextLimiter(titreField,50);
	    		System.out.println(titreField.getLength());
	    	livre.setTitre(titreField.getText());
	    	addTextLimiter(auteurField,50);
	    	livre.setAuteur(auteurField.getText());
	    	addTextLimiter(genreField,50);
	    	livre.setGenre(genreField.getText());
	    	java.util.Date date1;

			java.util.Date dateM;

			dateM = new SimpleDateFormat("yyyy-MM-dd").parse(dateDeParitionField.getText());
			
	    	livre.setDateDeParition(new java.sql.Date(dateM.getTime()));
	    	livre.setNombreDePage(Integer.parseInt(nombreDePagesField.getText()));
	    	addTextLimiter(extraitArea,999);
	    	livre.setExtrait(extraitArea.getText());
	    	Traitement t=new Traitement();
	    	t.ahouterLivre(livre);
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Succés");
			alert.setHeaderText(null);
			alert.setContentText("Le livre est bien ajouté");

			alert.showAndWait();
			resetField();
	    		}catch(Exception e) {
	    			Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Attetion");
					alert.setHeaderText(null);
					alert.setContentText(e.getMessage());

					alert.showAndWait();
	    		}
	    	}
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		@SuppressWarnings("unused")
		private byte[] getByteArrayFromFile(File handledDocument) throws IOException {
		    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    final InputStream in = new FileInputStream(handledDocument);
		    final byte[] buffer = new byte[500];

		    int read = -1;
		    while ((read = in.read(buffer)) > 0) {
		        baos.write(buffer, 0, read);
		    }
		    in.close();

		    return baos.toByteArray();
		}

		public void resetField() {
		    titreField.setText("");
		    dateDeParitionField.setText("");
		    auteurField.setText("");
		    nombreDePagesField.setText("");
		    genreField.setText("");
		    extraitArea.setText("");
		    genreField.setText("");
		} 
		public static void addTextLimiter(final TextField tf, final int maxLength) {
		   
		            if (tf.getText().length() > maxLength) {
		            	System.out.println(tf.getText().length()+"avant");
		                String s = tf.getText().substring(0, maxLength);
		                tf.setText(s);
		                System.out.println(tf.getText().length()+"aapres");
		            }
		        
		    
		}
		public static void addTextLimiter(final TextArea tf, final int maxLength) {
		    
		            if (tf.getText().length() > maxLength) {
		                String s = tf.getText().substring(0, maxLength);
		                tf.setText(s);
		            }
		     
		}
	

}
