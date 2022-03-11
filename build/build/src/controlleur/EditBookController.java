package controlleur;

import java.io.ByteArrayInputStream;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import model.Livre;
import model.Traitement;

@SuppressWarnings("unused")
public class EditBookController implements Initializable {
	  @FXML
	    private CheckBox couvertureChek;

	    @FXML
	    private HBox covertureSection;
	    @FXML
	    private TextArea extraitArea;

	    @FXML
	    private ImageView coverImage;

	    @FXML
	    private Button selectionnerCovertureButton;

	    @FXML
	    private CheckBox informationsLivreChek;

	    @FXML
	    private GridPane informationLivreSection,informationLivreSection1;

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
	    private CheckBox contenuLivreChek;

	    @FXML
	    private GridPane contenuLivreSection;

	    @FXML
	    private Button selectionnerPdfButton;

	    @FXML
	    private Label selectionnerPdfLabel;

	    @FXML
	    private CheckBox audioLivreChek;

	    @FXML
	    private GridPane audioLivreSection;

	    @FXML
	    private Label selectionnerAudioLabel;

	    @FXML
	    private Button selectionnerAudioButton;


	@FXML
    void onModifierButton(MouseEvent eventt) throws ParseException {
    	try {
    	Traitement t=new Traitement();
    	if(informationsLivreChek.isSelected()) {
    	System.out.println("modifier");
    	addTextLimiter(titreField,50);
    	selectedLivre.setTitre(titreField.getText());
    	addTextLimiter(auteurField,50);
    	selectedLivre.setAuteur(auteurField.getText());
    	addTextLimiter(genreField,50);
    	selectedLivre.setGenre(genreField.getText());
    	
    	java.util.Date date1;

		java.util.Date dateM;

		dateM = new SimpleDateFormat("yyyy-MM-dd").parse(dateDeParitionField.getText());
		
    	selectedLivre.setDateDeParition(new java.sql.Date(dateM.getTime()));
    	selectedLivre.setNombreDePage(Integer.parseInt(nombreDePagesField.getText()));
    	addTextLimiter(extraitArea,999);
    	selectedLivre.setExtrait(extraitArea.getText());
    	t.updateinformationsLivre(selectedLivre);
    	}
    	if(couvertureChek.isSelected()) {
    	t.updatecouvertureLivre(selectedLivre);
    		
    	}
    	if(contenuLivreChek.isSelected()) {
        	t.updateContenuLivre(selectedLivre);
        		
        	}
    	if(audioLivreChek.isSelected()) {
        	t.updateAudioLivre(selectedLivre);
        		
        	}
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Succés");
		alert.setHeaderText(null);
		alert.setContentText("Le livre est bien modifié");

		alert.showAndWait();
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attetion");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
		
    }

    @FXML
    void onSelectionnerAudioButton(ActionEvent event) {

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
			selectedLivre.setAudio(fileContent); 
			selectionnerAudioLabel.setText(file.getAbsolutePath());
		
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attention");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
			e.printStackTrace();
		}
        
    }

    @FXML
    void onSelectionnerPdfButton(ActionEvent event) {

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
			selectedLivre.setpDF(fileContent);  
			selectionnerPdfLabel.setText(file.getAbsolutePath());
		
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attention");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
			e.printStackTrace();
		}
    }

    @FXML
    void onselectionnerCovertureButton(ActionEvent event) throws MalformedURLException {

    	FileChooser directoryChooser = new FileChooser();

	

		File file = directoryChooser.showOpenDialog(null);

		URL url;

		url = file.toURI().toURL();
		Image image = new Image(url.toExternalForm());
		coverImage.setImage(image);
		byte[] fileContent;
		try {
			fileContent = getByteArrayFromFile(file);
			selectedLivre.setCouverture(fileContent);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attention");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
			e.printStackTrace();
		}
	
    }
    
    @FXML
    void onAudioLivreChek(ActionEvent event) {

    	if(audioLivreChek.isSelected()) {
    		audioLivreSection.setDisable(false);
    	}else {
    		audioLivreSection.setDisable(true);
    	}
    }

    @FXML
    void onContenuLivreChek(ActionEvent event) {

    	if(contenuLivreChek.isSelected()) {
    		contenuLivreSection.setDisable(false);
    	}else {
    		contenuLivreSection.setDisable(true);
    	}
    }

    @FXML
    void onCouvertureChek(ActionEvent event) {

    	if(couvertureChek.isSelected()) {
    		covertureSection.setDisable(false);
    	}else {
    		covertureSection.setDisable(true);
    	}
    }

    @FXML
    void onInformationsLivreChek(ActionEvent event) {

    	if(informationsLivreChek.isSelected()) {
    		informationLivreSection.setDisable(false);
    		informationLivreSection1.setDisable(false);
    	}else {
    		informationLivreSection.setDisable(true);
    		informationLivreSection1.setDisable(true);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	Livre selectedLivre;
	public void initData(Livre livre) {
		selectedLivre=livre;
		  titreField.setText(selectedLivre.getTitre());
		    dateDeParitionField.setText(""+selectedLivre.getDateDeParition());
		    auteurField.setText(selectedLivre.getAuteur());
		    nombreDePagesField.setText(""+selectedLivre.getNombreDePage());
		    genreField.setText(selectedLivre.getGenre());
		    Image img = new Image(new ByteArrayInputStream(livre.getCouverture()));
		    coverImage.setImage(img);
		    extraitArea.setText(selectedLivre.getExtrait());
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
