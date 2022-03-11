package controlleur;



	import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Traitement;
import model.Utilisateur;

	public class BarGestionUserController  implements Initializable{


	    @FXML
	    private BorderPane borderPane;

	    @FXML
	    private VBox barPane;

	    @FXML
	    private HBox searchBox;

	    @FXML
	    private TextField searchField;

	    @FXML
	    private HBox retourBox;

	    @FXML
	    private Label retourlLabel;

	    @FXML
	    private HBox editBox;

	    @FXML
	    private Label modifierLabel;

	    @FXML
	    private HBox deleteBox;

	    @FXML
	    private Label supprimerLabel;

	    @FXML
	    private BorderPane centerBorderPane;

	    @FXML
	    private TableView<Utilisateur> table;

	    @FXML
	    private TableColumn<Utilisateur, Integer> id;

	    @FXML
	    private TableColumn<Utilisateur, String> nom;

	    @FXML
	    private TableColumn<Utilisateur, String> prenom;

	    @FXML
	    private TableColumn<Utilisateur, String> email;

	    Parent root;
	    
	    @FXML
	    void mouseEnterBar(MouseEvent event) {
	    	new Thread(new Runnable() {

				@Override
				public void run() {
					for(int i=51;i<255;i+=25) {
						barPane.setPrefWidth(i);
						try {
							Thread.sleep(10);
							if(barPane.getPrefWidth()>180) {
								retourlLabel.setVisible(true);
								modifierLabel.setVisible(true);
								supprimerLabel.setVisible(true);
								
								searchField.setVisible(true);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
	    		
	    	}).start();

	    }

	    @FXML
	    void mouseExitBar(MouseEvent event) {
	    	
	    	new Thread(new Runnable() {

				@Override
				public void run() {
					for(int i=255;i>51;i-=25) {
						barPane.setPrefWidth(i);
						try {
							Thread.sleep(10);
							if(barPane.getPrefWidth()<120) {
								retourlLabel.setVisible(false);
								modifierLabel.setVisible(false);
								supprimerLabel.setVisible(false);
								
								
								searchField.setVisible(false);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
	    		
	    	}).start();
	    }
	    

	    @FXML
	    void mouseInDelete(MouseEvent event) {

	    	mouseInColor(deleteBox);
	    }

	    @FXML
	    void mouseInSearch(MouseEvent event) {

	    }

	
	    @FXML
	    void mouseInedit(MouseEvent event) {

	    	mouseInColor(editBox);
	    }

	    @FXML
	    void mouseOutDelete(MouseEvent event) {

	    	mouseOutColor(deleteBox);
	    }

	    @FXML
	    void mouseOutSearch(MouseEvent event) {

	    }

	

	    @FXML
	    void mouseOutedit(MouseEvent event) {

	    	mouseOutColor(editBox);
	    }

	    @FXML
	    void mouseinretour(MouseEvent event) {

	    	mouseInColor(retourBox);
	    }

	    @FXML
	    void mouseoutretour(MouseEvent event) {

	    	mouseOutColor(retourBox);
	    }

	 

	    @FXML
	    void ondelete(MouseEvent event) {


	    	Utilisateur user= table.getSelectionModel().getSelectedItem();
		     if(user!=null) {
	    	Traitement t=new Traitement();
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation de suppression");
	    	alert.setHeaderText("étes vous sùr de supprimer cet utilisateur");
	    	alert.setContentText("conirmez en cliquant sur oui");

	    	ButtonType buttonOui = new ButtonType("oui");
	    	ButtonType buttonNon = new ButtonType("non");
	    

	    	alert.getButtonTypes().setAll(buttonOui, buttonNon);

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonOui){
	    	    t.deleteUser(user);
	    	    Alert alert1 = new Alert(AlertType.INFORMATION);
		    	alert1.setTitle("Information ");
		    	alert1.setHeaderText(null);
		    	alert1.setContentText("L'utilisateur est bien supprimé");

		    	alert1.showAndWait();
	    	} 
	    	}else {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Information ");
		    	alert.setHeaderText(null);
		    	alert.setContentText("Vous devez selectionnez un livre");

		    	alert.showAndWait();
		    }
		     
	    }

	    @FXML
	    void onedit(MouseEvent event) {



	    	Utilisateur user= table.getSelectionModel().getSelectedItem();
		     if(user!=null) {
		     Traitement t=new Traitement();
		     FXMLLoader loader;
	    		
	    		loader=new FXMLLoader(getClass().getResource("/vue/GestionUser/EditUserPanel.fxml"));
				try {
					loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				EditUserController editUserController=loader.getController();
				editUserController.initData(user);
				root=loader.getRoot();
		     if(centerBorderPane.getLeft()!=null) centerBorderPane.setLeft(null);
			
			centerBorderPane.setLeft(root);
		    }else {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Information ");
		    	alert.setHeaderText(null);
		    	alert.setContentText("Vous devez selectionnez un utilisateur");

		    	alert.showAndWait();
		    }
		     
	    	
	    	
	    /*	try {
	    		if(centerBorderPane.getLeft()!=null) centerBorderPane.setLeft(null);
				root = FXMLLoader.load(getClass().getResource("/vue/GestionUser/EditUserPanel.fxml"));
				centerBorderPane.setLeft(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    }

	    @FXML
	    void onretour(MouseEvent event) {

	    	if(centerBorderPane.getLeft()!=null) centerBorderPane.setLeft(null);
	    	refreshTable();
	    	
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			ObservableList<Utilisateur> data = FXCollections.observableArrayList();
			model.Traitement t = new model.Traitement();

			for (Utilisateur a : t.getUsers()) {
				data.add(a);
				// System.out.println(a.getItem().getTitre()+"3");

			}

			id.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
			nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
			prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
			email.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		

			FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
					
					// 2. Set the filter Predicate whenever the filter changes.
					searchField.textProperty().addListener((observable, oldValue, newValue) -> {
						filteredData.setPredicate(utilisateur -> {
							// If filter text is empty, display all persons.
											
							if (newValue == null || newValue.isEmpty()) {
								table.refresh();
								return true;
							}
							
							// Compare first name and last name of every person with filter text.
							String lowerCaseFilter = newValue.toLowerCase();
							
							if (utilisateur.getNom().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches first name.
							} else if (utilisateur.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
							
							}else if (utilisateur.getEmail().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
								
							}else if ((""+utilisateur.getId()).contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
								
							}
							
							
							     else  {
							    	 table.refresh();
							    	 return false; // Does not match.
							     }
							    	 
						});
					});
					
					// 3. Wrap the FilteredList in a SortedList. 
					SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
					
					// 4. Bind the SortedList comparator to the TableView comparator.
					// 	  Otherwise, sorting the TableView would have no effect.
					sortedData.comparatorProperty().bind(table.comparatorProperty());
					
					// 5. Add sorted (and filtered) data to the table.
					table.setItems(sortedData);
			
		}
		public void mouseInColor(HBox box) {
			box.setBackground(new Background(new BackgroundFill(Color.web("#B5E0E7"), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		public void mouseOutColor(HBox box) {
			box.setBackground(new Background(new BackgroundFill(Color.web("#E9E9E9"), null,null)));
		}

		public void refreshTable() {
			ObservableList<Utilisateur> data = FXCollections.observableArrayList();
			model.Traitement t = new model.Traitement();

			for (Utilisateur a : t.getUsers()) {
				data.add(a);
				// System.out.println(a.getItem().getTitre()+"3");

			}

		

			FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
					
					// 2. Set the filter Predicate whenever the filter changes.
					searchField.textProperty().addListener((observable, oldValue, newValue) -> {
						filteredData.setPredicate(utilisateur -> {
							// If filter text is empty, display all persons.
											
							if (newValue == null || newValue.isEmpty()) {
								table.refresh();
								return true;
							}
							
							// Compare first name and last name of every person with filter text.
							String lowerCaseFilter = newValue.toLowerCase();
							
							if (utilisateur.getNom().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches first name.
							} else if (utilisateur.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
							
							}else if (utilisateur.getEmail().toLowerCase().contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
								
							}else if ((""+utilisateur.getId()).contains(lowerCaseFilter)) {
								table.refresh();
								return true; // Filter matches last name.
								
							}
							
							
							     else  {
							    	 table.refresh();
							    	 return false; // Does not match.
							     }
							    	 
						});
					});
					
					// 3. Wrap the FilteredList in a SortedList. 
					SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
					
					// 4. Bind the SortedList comparator to the TableView comparator.
					// 	  Otherwise, sorting the TableView would have no effect.
					sortedData.comparatorProperty().bind(table.comparatorProperty());
					
					// 5. Add sorted (and filtered) data to the table.
					table.setItems(sortedData);
		}
		
	}