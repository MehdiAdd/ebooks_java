package controlleur;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Livre;
import model.LivreItem;
import model.Utilisateur;

public class BarController implements Initializable {

	public Utilisateur currentUser = new Utilisateur();
	@FXML
	private VBox barPane;

	@FXML
	private BorderPane borderPane, borderPaneTable, tablePane, containerPane;
	@FXML
	private Label loginLabel;

	@FXML
	private Label logoutLabel;

	@FXML
	private TextField searchField;
	@FXML
	private Label listeLabel, labelTable;

	@FXML
	private Label favorisLabel;
	@FXML
	private TableView<Livre> table;
	@SuppressWarnings("unused")
	private TableView<Livre> CurrentTable;

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
	private HBox loginBox;

	@FXML
	private HBox searchBox;

	@FXML
	private HBox listBox;

	@FXML
	private HBox favorisBox;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private HBox logoutBox;
	@FXML
	private ImageView login, retourButton;
	@FXML
    private StackPane parentPane;

	@FXML
	void mouseEnterBar(MouseEvent event) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 51; i < 255; i += 25) {
					barPane.setPrefWidth(i);
					try {
						Thread.sleep(10);
						if (barPane.getPrefWidth() > 180) {
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
				for (int i = 255; i > 51; i -= 25) {
					barPane.setPrefWidth(i);
					try {
						Thread.sleep(10);
						if (barPane.getPrefWidth() < 120) {
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
	void mouseInFavoris(MouseEvent event) {

		mouseInColor(favorisBox);
	}

	@FXML
	void mouseInList(MouseEvent event) {
		mouseInColor(listBox);
	}

	@FXML
	void mouseInLogout(MouseEvent event) {

		mouseInColor(logoutBox);
	}

	@FXML
	void mouseInSearch(MouseEvent event) {

		// mouseInColor(searchBox);
	}

	@FXML
	void mouseOutFavoris(MouseEvent event) {

		mouseOutColor(favorisBox);
	}

	@FXML
	void mouseOutList(MouseEvent event) {

		mouseOutColor(listBox);
	}

	@FXML
	void mouseOutLogout(MouseEvent event) {

		mouseOutColor(logoutBox);
	}

	@FXML
	void mouseOutSearch(MouseEvent event) {

		// mouseOutColor(searchBox);
	}

	@FXML
	void onFavoris() {
		/*
		 * FXMLLoader loader; Parent root = null; Traitement t = new Traitement();
		 * Utilisateur user = new Utilisateur(); try {
		 * 
		 * loader=new FXMLLoader(getClass().getResource("/vue/tablefavoris.fxml"));
		 * loader.load(); TableFavorisController tableFavoris=loader.getController();
		 * System.out.println(currentUser.getId()+"iddddddddddd");
		 * tableFavoris.initData(currentUser); root=loader.getRoot(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * containerPane.setCenter(root);
		 */
		ObservableList<Livre> data = FXCollections.observableArrayList();
		model.Traitement t = new model.Traitement();
		table.refresh();
		labelTable.setText("Livres Favoris");
		for (Livre a : t.getFavorisLivres(currentUser)) {
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

	@FXML
	void onListLivres() {
		ObservableList<Livre> data = FXCollections.observableArrayList();
		model.Traitement t = new model.Traitement();
		table.refresh();
		labelTable.setText("Bibliothèque des livres");
		for (Livre a : t.getLivres()) {
			data.add(a);
			// System.out.println(a.getItem().getTitre()+"3");

		}
		
FilteredList<Livre> filteredData = new FilteredList<>(data, b -> true);
		
		 
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livre -> {
			
								
				if (newValue == null || newValue.isEmpty()) {
					table.refresh();
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (livre.getTitre().toLowerCase().contains(lowerCaseFilter)) {
					table.refresh();
					return true; 
				} else if (livre.getAuteur().toLowerCase().contains(lowerCaseFilter)) {
					table.refresh();
					return true; 
				
				}else if (livre.getGenre().toLowerCase().contains(lowerCaseFilter)) {
					table.refresh();
					return true; 
					
				}else if ((""+livre.getDateDeParition()).contains(lowerCaseFilter)) {
					table.refresh();
					return true; 
					
				}
				
				
				     else  {
				    	 table.refresh();
				    	 return false; // Does not match.
				     }
				    	 
			});
		});
		
		
		SortedList<Livre> sortedData = new SortedList<>(filteredData);
		
	
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
	
		table.setItems(sortedData);

		// containerPane.setCenter(tablePane);
	}

	@FXML
	void onRetourButton(MouseEvent event) {
		retourButton.setVisible(false);
		setVisible();
	}

	BorderPane root;
	@FXML
	void onLogout(MouseEvent event) throws IOException {
		Stage stagetable=(Stage)parentPane.getScene().getWindow();
    	stagetable.close();
    	 Parent root = FXMLLoader.load(getClass().getResource("/vue/connect.fxml"));   
    	 Stage stage=new Stage();
	        Scene scene = new Scene(root);
	        scene.setFill(Color.TRANSPARENT);
	        stage.initStyle(StageStyle.TRANSPARENT);
	        stage.setScene(scene);

	        stage.show();
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

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livre -> {
			
				if (newValue == null || newValue.isEmpty()) {
					table.refresh();
					return true;
				}
				
		
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (livre.getTitre().toLowerCase().contains(lowerCaseFilter)) {
					table.refresh();
					return true; 
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
		
		
		SortedList<Livre> sortedData = new SortedList<>(filteredData);
		
	
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
		table.setRowFactory(tv -> {
			TableRow<Livre> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Livre rowData = row.getItem();
					try {
						System.out.println(BarController.this.currentUser.getId_favoris());

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Livrecract.fxml"));
						loader.load();
						LivreCaractController livreCaract = loader.getController();
						livreCaract.initData(rowData, this.currentUser);
					
						System.out.println("Double click on: " + rowData.getNombreEtoiles());
						root = loader.getRoot();
						containerPane.setCenter(root);
						borderPane.setLeft(null);
						retourButton.setVisible(true);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			return row;
		});

	}

	public void mouseInColor(HBox box) {
		box.setBackground(new Background(new BackgroundFill(Color.web("#B5E0E7"), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void mouseOutColor(HBox box) {
		box.setBackground(new Background(new BackgroundFill(Color.web("#E9E9E9"), null, null)));
	}

	public void initData(Utilisateur user) {
		currentUser = user;

		loginLabel.setText(currentUser.getNom() + " " + currentUser.getPrenom());
	}

	public void setVisible() {

		containerPane.setCenter(tablePane);
		borderPane.setLeft(barPane);
	}

}
