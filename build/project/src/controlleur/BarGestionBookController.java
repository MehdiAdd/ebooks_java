package controlleur;



	import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Livre;
import model.LivreItem;
import model.Traitement;

	public class BarGestionBookController  implements Initializable{

	    @FXML
	    private BorderPane borderPane;

	    @FXML
	    private VBox barPane;

	    @FXML
	    private HBox retourBox;

	    @FXML
	    private Label loginLabel;

	    @FXML
	    private HBox searchBox;

	    @FXML
	    private TextField searchField;

	    @FXML
	    private HBox addBox;

	    @FXML
	    private Label listeLabel;

	    @FXML
	    private HBox editBox;

	    @FXML
	    private Label favorisLabel;

	    @FXML
	    private HBox deleteBox;

	    @FXML
	    private Label logoutLabel;

	    @FXML
		private TableView<Livre> table;
	    @FXML
		private TableColumn<Livre, LivreItem> livres;

		@FXML
		private TableColumn<Livre, String> genre;
		@FXML
		private TableColumn<Livre, Integer> note;

		@FXML
		private TableColumn<Livre, Date> datePublication;

		@FXML
		private TableColumn<Livre, String> auteur;

	    @FXML
	    private BorderPane centerBorderPane;
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
								loginLabel.setVisible(true);
								listeLabel.setVisible(true);
								favorisLabel.setVisible(true);
								logoutLabel.setVisible(true);
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
								loginLabel.setVisible(false);
								listeLabel.setVisible(false);
								favorisLabel.setVisible(false);
								logoutLabel.setVisible(false);
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
	    void mouseInadd(MouseEvent event) {

	    	mouseInColor(addBox);
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
	    void mouseOutadd(MouseEvent event) {

	    	mouseOutColor(addBox);
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
	    void onadd(MouseEvent event) {

	    	try {
	    		if(root!=null) centerBorderPane.getChildren().remove(root);
				root = FXMLLoader.load(getClass().getResource("/vue/GestionBook/AddBookPanel.fxml"));
				centerBorderPane.setLeft(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void ondelete(MouseEvent event) {

	    	Livre livre= table.getSelectionModel().getSelectedItem();
		     if(livre!=null) {
	    	Traitement t=new Traitement();
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation de suppression");
	    	alert.setHeaderText("étes vous sùr de supprimer ce livre");
	    	alert.setContentText("conirmez en cliquant sur oui");

	    	ButtonType buttonOui = new ButtonType("oui");
	    	ButtonType buttonNon = new ButtonType("non");
	    

	    	alert.getButtonTypes().setAll(buttonOui, buttonNon);

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonOui){
	    	    t.deleteLivreLivre(livre);
	    	    Alert alert1 = new Alert(AlertType.INFORMATION);
		    	alert1.setTitle("Information ");
		    	alert1.setHeaderText(null);
		    	alert1.setContentText("Le livre est bien supprimé");

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
	    void onedit(MouseEvent event) throws IOException {

	    	Livre livre= table.getSelectionModel().getSelectedItem();
		     if(livre!=null) {
		     Traitement t=new Traitement();
		     FXMLLoader loader;
	    		
	    		loader=new FXMLLoader(getClass().getResource("/vue/GestionBook/EditBookPanel.fxml"));
				loader.load();
				
				EditBookController editBookController=loader.getController();
				editBookController.initData(livre);
				root=loader.getRoot();
		     if(centerBorderPane.getLeft()!=null) centerBorderPane.setLeft(null);
			
			centerBorderPane.setLeft(root);
		    }else {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Information ");
		    	alert.setHeaderText(null);
		    	alert.setContentText("Vous devez selectionnez un livre");

		    	alert.showAndWait();
		    }
		     

	    	
	    }

	    @FXML
	    void onretour(MouseEvent event) {

	    	if(centerBorderPane.getLeft()!=null) centerBorderPane.setLeft(null);
	    	refreshTable();
	    	
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {

			ObservableList<Livre> data = FXCollections.observableArrayList();
			model.Traitement t = new model.Traitement();

			for (Livre a : t.getLivres()) {
				data.add(a);
				// System.out.println(a.getItem().getTitre()+"3");

			}

			livres.setCellValueFactory(new PropertyValueFactory<Livre, LivreItem>("item"));
			note.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("nombreEtoiles"));
			auteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
			genre.setCellValueFactory(new PropertyValueFactory<Livre, String>("genre"));
			datePublication.setCellValueFactory(new PropertyValueFactory<Livre, Date>("dateDeParition"));
			livres.setCellFactory(new Callback<TableColumn<Livre, LivreItem>, TableCell<Livre, LivreItem>>() {
				@Override
				public TableCell<Livre, LivreItem> call(TableColumn<Livre, LivreItem> param) {
					TableCell<Livre, LivreItem> cell = new TableCell<Livre, LivreItem>() {
						@Override
						public void updateItem(LivreItem item, boolean empty) {
							if (item != null) {
								HBox box = new HBox();
								box.setSpacing(10);
								VBox vbox = new VBox();
								Label titre=new Label(item.getTitre());
								titre.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
								vbox.getChildren().add(titre);

								Image img = new Image(new ByteArrayInputStream(item.getCouverture()));

								ImageView imageview = new ImageView();
								imageview.setFitHeight(80);
								imageview.setFitWidth(60);
								imageview.setImage(img);
								box.getChildren().addAll(imageview, vbox);
								// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
								setGraphic(box);
							}
						}
					};
					System.out.println(cell.getIndex());
					return cell;
				}

			});
			note.setCellFactory(new Callback<TableColumn<Livre, Integer>, TableCell<Livre, Integer>>() {
				@Override
				public TableCell<Livre, Integer> call(TableColumn<Livre, Integer> param) {
					TableCell<Livre, Integer> cell = new TableCell<Livre, Integer>() {
						@Override
						public void updateItem(Integer item, boolean empty) {
							if (item != null) {
								if (item < 1)
									item = 1;
								HBox box = new HBox();
								box.setSpacing(10);

								for (int i = 1; i <= item; i++) {
									ImageView imageview = new ImageView(
											getClass().getResource("/images/rates.png").toString());
									imageview.setFitHeight(25);
									imageview.setFitWidth(25);
									box.getChildren().add(imageview);
								}

								// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
								setGraphic(box);
							}
						}
					};
					System.out.println(cell.getIndex());
					return cell;
				}

			});

	FilteredList<Livre> filteredData = new FilteredList<>(data, b -> true);
			
			// 2. Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(livre -> {
					// If filter text is empty, display all persons.
									
					if (newValue == null || newValue.isEmpty()) {
						table.refresh();
						return true;
					}
					
					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (livre.getTitre().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches first name.
					} else if (livre.getAuteur().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches last name.
					
					}else if (livre.getGenre().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches last name.
						
					}else if ((""+livre.getDateDeParition()).contains(lowerCaseFilter)) {
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
			SortedList<Livre> sortedData = new SortedList<>(filteredData);
			
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
			ObservableList<Livre> data = FXCollections.observableArrayList();
			model.Traitement t = new model.Traitement();
			table.refresh();
			
			for (Livre a : t.getLivres()) {
				data.add(a);
				// System.out.println(a.getItem().getTitre()+"3");

			}
			
	FilteredList<Livre> filteredData = new FilteredList<>(data, b -> true);
			
			// 2. Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(livre -> {
					// If filter text is empty, display all persons.
									
					if (newValue == null || newValue.isEmpty()) {
						table.refresh();
						return true;
					}
					
					// Compare first name and last name of every person with filter text.
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (livre.getTitre().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches first name.
					} else if (livre.getAuteur().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches last name.
					
					}else if (livre.getGenre().toLowerCase().contains(lowerCaseFilter)) {
						table.refresh();
						return true; // Filter matches last name.
						
					}else if ((""+livre.getDateDeParition()).contains(lowerCaseFilter)) {
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
			SortedList<Livre> sortedData = new SortedList<>(filteredData);
			
			// 4. Bind the SortedList comparator to the TableView comparator.
			// 	  Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(table.comparatorProperty());
			
			// 5. Add sorted (and filtered) data to the table.
			table.setItems(sortedData);

		}


		
	}