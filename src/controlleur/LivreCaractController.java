package controlleur;


	import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import model.Livre;
import model.Traitement;
import model.Utilisateur;

	public class LivreCaractController implements Initializable{

	   public Livre currentLivre;
	   public Utilisateur currentUser;
	   int noteUser;
	   
	   Traitement t;
	   
		@FXML
	    private ImageView iconLivre;

	    @FXML
	    private Label titreLivre;

	    @FXML
	    private FlowPane flowPaneNoteLivre;

	    @FXML
	    private ImageView noteLivre;

	    @FXML
	    private Label auteur;

	    @FXML
	    private Label genre;

	    @FXML
	    private Label dateParition;

	    @FXML
	    private Label nombreDePages,retourButton;

	    @FXML
	    private Label titreLivre2;

	    @FXML
	    private TextArea extraitLivre;

	    @FXML
	    private FlowPane flowPaneNoteUser;

	    @FXML
	    private ImageView note1user;

	    @FXML
	    private ImageView note2user;

	    @FXML
	    private ImageView note3user;

	    @FXML
	    private ImageView note4user;

	    @FXML
	    private ImageView note5user;

	    @FXML
	    private ImageView iconFavoris;

	    @FXML
	    private Button lireButton;

	    @FXML
	    private Button telechargerPdfButton;

	    @FXML
	    private Button ecouterButton;

	    @FXML
	    private Button telechargerAudioButton;
	    @FXML
	    void onIconFavoris(MouseEvent event) {
	    	Traitement t=new Traitement();
	    	int resultat=t.ajouterFavoris(currentUser, currentLivre);
	    	if(resultat==1)
	    	 iconFavoris.setImage(new Image(getClass().getResource("/images/favorated.png").toString()));
	    	else if(resultat==0)
	    		 iconFavoris.setImage(new Image(getClass().getResource("/images/nofavorated.png").toString()));
	    }
	    
	    @FXML
	    void note1userMouseIn(MouseEvent event) {
               note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
               note2user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
               note3user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
               note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
               note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
	    }

	    @FXML
	    void note2userMouseIn(MouseEvent event) {

	    	 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note3user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
	    }

	    @FXML
	    void note2userMouseout(MouseEvent event) {

	    	note1userMouseout(event);
	    }

	    @FXML
	    void note3userMouseIn(MouseEvent event) {

	    	 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
	    }

	    @FXML
	    void note3userMouseout(MouseEvent event) {

	    	note1userMouseout(event);
	    }

	    @FXML
	    void note4userMouseIn(MouseEvent event) {

	    	 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note4user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
	    }

	    @FXML
	    void note4userMouseout(MouseEvent event) {

	    	note1userMouseout(event);
	    }

	    @FXML
	    void note5userMouseIn(MouseEvent event) {
	    	 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note4user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
             note5user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	    }

	    @FXML
	    void note5userMouseout(MouseEvent event) {

	    	note1userMouseout(event);
	    }
	    @FXML
	    void note1userMouseout(MouseEvent event) {

	    	 setNote();
	    }
	    
	    @FXML
	    void onnote1user(MouseEvent event) {

	    	noteUser=1;
	    	t=new Traitement();
	    	t.ajouterNote(currentUser, currentLivre, noteUser);
	    	setNote();
	    }

	    @FXML
	    void onnote2user(MouseEvent event) {

	    	noteUser=2;
	    	t=new Traitement();
	    	t.ajouterNote(currentUser, currentLivre, noteUser);
	    	setNote();
	    }

	    @FXML
	    void onnote3user(MouseEvent event) {

	    	noteUser=3;
	    	t=new Traitement();
	    	t.ajouterNote(currentUser, currentLivre, noteUser);
	    	setNote();
	    }

	    @FXML
	    void onnote4user(MouseEvent event) {

	    	noteUser=4;
	    	t=new Traitement();
	    	t.ajouterNote(currentUser, currentLivre, noteUser);
	    	setNote();
	    }

	    @FXML
	    void onnote5user(MouseEvent event) {

	    	noteUser=5;
	    	t=new Traitement();
	    	t.ajouterNote(currentUser, currentLivre, noteUser);
	    	setNote();
	    }

	    @FXML
	    void onecouterButton(ActionEvent event) throws IOException {
	    	Traitement t=new Traitement();
			Livre livre=t.getAudio(currentLivre);
			
			if(livre.getAudio()!=null) 
			{ 
				currentLivre=livre;
	    	AudioInputStream oAIS = null;
	    	  //System.out.println(currentLivre.getAudio().length);

			  //ByteArrayInputStream oInstream = new ByteArrayInputStream(currentLivre.getAudio());
			  // myInputStream = new ByteArrayInputStream(currentLivre.getAudio()); 
			 // fileInputStream = AudioSystem.getAudioInputStream(oInstream);
  	//	AudioFormat format=oAIS.getFormat();
			 
			   /* DataLine.Info info = new DataLine.Info(SourceDataLine.class,
			                format);
			    SourceDataLine line = null;
			    line.open(format);
			    line = (SourceDataLine) AudioSystem.getLine(info);
			    line.start();*/
			   /* Clip clip = AudioSystem.getClip();
			  
			    clip.open(oAIS);
			    
			      clip.start();*/
			    // playSound(oAIS);
			   
			   
			   
			   
			   new Thread(new Runnable() {

				@Override
				public  void run() {
		               try {
		            	  
		 					  File tempMp3 = File.createTempFile("Ebook", ".mp3", null); //, getCacheDir()
		 		               tempMp3.deleteOnExit();
		 		               FileOutputStream fos = new FileOutputStream(tempMp3);
						fos.write(currentLivre.getAudio());
						 fos.close();
						 
						 Desktop.getDesktop().open(tempMp3);
			               

			              /* final Media media = new Media(tempMp3.toURI().toURL().toString());
			               final MediaPlayer mediaPlayer = new MediaPlayer(media);
			               mediaPlayer.play();*/
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		              
					
				}

             
			   }).start();;
			   
			   
			 /*  FileChooser directoryChooser = new FileChooser();

				 file1 = directoryChooser.showOpenDialog(null);
			 playThread=new Thread(runnablePlay);
			    resumeThread=new Thread(runnableResume);
			    playThread.start();*/
	    }else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("La version audio pour ce livre n'existe pas");

			alert.showAndWait();
		}
	    }

	    @FXML
	    void onlireButton(ActionEvent event) {

	    	Traitement t=new Traitement();
			Livre livre=t.getlivre(currentLivre);
			if(livre.getpDF()!=null) 
			{currentLivre=livre;
			OutputStream out;
			
		try {
				File file = new File("Ebook.pdf");
				boolean result = Files.deleteIfExists(file.toPath());
				out = new FileOutputStream("Ebook.pdf");
				out.write(currentLivre.getpDF());
				out.close();
				Desktop.getDesktop().open(new File("Ebook.pdf"));
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Important!");
				alert.setHeaderText("Fermeture du livre précédent est obligatoire");
				alert.setContentText("Veuillez fermer le precedent livre ouvert pour ouvrir un nouveau!");

				alert.showAndWait();
				e.printStackTrace();
			}
			
			
			
			
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("La version PDF pour ce livre n'existe pas");

				alert.showAndWait();
			}
	    }

	    @FXML
	    void telechargerAudioButton(ActionEvent event) {

	    	Traitement t=new Traitement();
			Livre livre=t.getAudio(currentLivre);
			if(livre.getAudio()!=null) 
			{currentLivre=livre;
	    	DirectoryChooser directoryChooser = new DirectoryChooser();

			File file = directoryChooser.showDialog(null);
			if(file!=null) {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Nom du livre");
				dialog.setHeaderText("La saisie le nom du audio du livre");
				
				dialog.setContentText("Veuillez saisir le nom d'audio à créer:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					
					OutputStream out;
					try {


						 File tempMp3 = new File(file.getAbsolutePath()+"/"+result.get()+".mp3"); 
						 System.out.println(file.getAbsolutePath()+"/"+result.get());
	 		               tempMp3.deleteOnExit();
	 		               tempMp3.createNewFile();
	 		               FileOutputStream fos = new FileOutputStream(tempMp3);
					fos.write(currentLivre.getAudio());
					 fos.close();
						
						
						/*InputStream b_in = new ByteArrayInputStream(livre.getAudio());
						
						  ByteArrayInputStream oInstream = new ByteArrayInputStream(currentLivre.getAudio());
						  AudioInputStream stream = AudioSystem.getAudioInputStream(oInstream);

						       // AudioFormat format = new AudioFormat(8000f, 16, 1, true, false);
						     //   AudioInputStream stream = new AudioInputStream(b_in);/*, format,
						               // livre.getAudio().length);*/
						       /* File file1 = new File(file.getAbsolutePath()+"/"+result.get()+".wav");
						        AudioSystem.write(stream, Type.WAVE, file1);
						       // Logger.info("File saved: " + file1.getName() + ", bytes: "
						           //     + livre.getAudio().length);*/
					
						
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Succés");
						alert.setHeaderText(null);
						alert.setContentText("l'audio di livre est bien télèchargé");

						alert.showAndWait();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Echec");
						alert.setHeaderText(null);
						alert.setContentText("le livre n'est pas télèchargé");
						e.printStackTrace();
					}
					
					}
				  
				}
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("La version audio pour ce livre n'existe pas");

				alert.showAndWait();
			}

	    }

	    @FXML
	    void telechargerPdfButton(ActionEvent event) {

	    	DirectoryChooser directoryChooser = new DirectoryChooser();

			File file = directoryChooser.showDialog(null);
			if(file!=null) {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Nom du livre");
				dialog.setHeaderText("La saisie le nom de PDF");
				
				dialog.setContentText("Veuillez saisir le nom de livre à créer:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					Traitement t=new Traitement();
					Livre livre=t.getlivre(currentLivre);
					if(livre!=null) 
					{currentLivre=livre;
					OutputStream out;
					try {
						out = new FileOutputStream(file.getAbsolutePath()+"/"+result.get()+".pdf");
						out.write(currentLivre.getpDF());
						out.close();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Succés");
						alert.setHeaderText(null);
						alert.setContentText("le livre est bien télèchargé");

						alert.showAndWait();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Echec");
						alert.setHeaderText(null);
						alert.setContentText("le livre n'est pas télèchargé");
						e.printStackTrace();
					}
					
					}
				    System.out.println("Your name: " + result.get());
				}
			}

		 
	    }
	    
	   
	
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			System.out.println("initialize");
			
		}
		
		public void initData(Livre Livre,Utilisateur user) {
			   currentLivre=Livre;
			   System.out.println();
			   currentUser=user;
			   t=new Traitement();
			   currentLivre=t.getLivre(Livre);
			  
			   System.out.println(currentLivre.getTitre());
	           titreLivre.setText(currentLivre.getTitre());
			   titreLivre2.setText(currentLivre.getTitre());
			   auteur.setText(currentLivre.getAuteur());
			   dateParition.setText(currentLivre.getDateDeParition().toString());
			   nombreDePages.setText(""+currentLivre.getNombreDePage());
			   genre.setText(currentLivre.getGenre());
			   ImageView imageview = new ImageView(getClass().getResource("/images/rates.png").toString());
	           imageview.setFitHeight(25);
	           imageview.setFitWidth(25);
	           flowPaneNoteLivre.getChildren().clear();
	           if(currentLivre.getNombreEtoiles()<1) flowPaneNoteLivre.getChildren().add(imageview);
	          
	           else{for(int i=1;i<=currentLivre.getNombreEtoiles();i++) {
	        	   System.out.println(i);
	        	   ImageView imageview1 = new ImageView(getClass().getResource("/images/rates.png").toString());
		           imageview1.setFitHeight(25);
		           imageview1.setFitWidth(25);
	           	flowPaneNoteLivre.getChildren().add(imageview1);
	           }
	           System.out.println(currentLivre.getNombreEtoiles());
	           }
	           Image img = new Image(new ByteArrayInputStream(currentLivre.getItem().getCouverture()));
	           iconLivre.setImage(img);
	           extraitLivre.setText(currentLivre.getExtrait());
	           if(t.isFavoris(currentUser, currentLivre)) 
	        	   iconFavoris.setImage(new Image(getClass().getResource("/images/favorated.png").toString()));
	           else iconFavoris.setImage(new Image(getClass().getResource("/images/nofavorated.png").toString()));
	           noteUser=t.getUserNote(user, Livre);
	           setNote();
	        
			  
		   }
		
		
			public void setNote() {
				switch(noteUser) {
				case 0:
					 note1user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note2user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note3user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             break;
				case 1:
					 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note2user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note3user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             break;
				case 2:
					 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note3user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             break;
				case 3:
					 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note4user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             break;
				case 4:
					 note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note4user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
		             note5user.setImage(new Image(getClass().getResource("/images/favorisadd.png").toString()));
		             break;
				case 5: note1user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	             note2user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	             note3user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	             note4user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	             note5user.setImage(new Image(getClass().getResource("/images/favorisadded.png").toString()));
	             break;
				}
			}

			
			
			
			
			
			
			
			
}
