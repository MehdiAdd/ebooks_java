package controlleur;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Livre;
import model.LivreItem;
import model.Utilisateur;

public class TableFavorisController implements Initializable {

	@FXML
    private TableView<Livre> tableFavoris;

    @FXML
    private TableColumn<Livre, LivreItem> livresFavoris;

    @FXML
    private TableColumn<Livre, String> genreFavoris;

    @FXML
    private TableColumn<Livre, Date> datePublicationFavoris;

    @FXML
    private TableColumn<Livre, String> auteurFavoris;

    @FXML
    private TableColumn<Livre, Integer> noteFavoris;


	Utilisateur currentUser;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
			livresFavoris.setCellValueFactory(new PropertyValueFactory<Livre, LivreItem>("item"));
			noteFavoris.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("nombreEtoiles"));
			auteurFavoris.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
			genreFavoris.setCellValueFactory(new PropertyValueFactory<Livre, String>("genre"));
			datePublicationFavoris.setCellValueFactory(new PropertyValueFactory<Livre, Date>("dateDeParition"));
			livresFavoris.setCellFactory(new Callback<TableColumn<Livre,LivreItem>,TableCell<Livre,LivreItem>>(){        
	            @Override
	            public TableCell<Livre,LivreItem> call(TableColumn<Livre,LivreItem> param) {                
	                TableCell<Livre,LivreItem> cell = new TableCell<Livre,LivreItem>(){
	                    @Override
	                    public void updateItem(LivreItem item, boolean empty) {                        
	                        if(item!=null){                            
	                            HBox box= new HBox();
	                            box.setSpacing(10) ;
	                            VBox vbox = new VBox();
	                            vbox.getChildren().add(new Label(item.getTitre()));
	                             
	                            Image img = new Image(new ByteArrayInputStream(item.getCouverture()));
	                           
	                            ImageView imageview = new ImageView();
	                            imageview.setFitHeight(80);
	                            imageview.setFitWidth(60);
	                            imageview.setImage(img); 
	                            box.getChildren().addAll(imageview,vbox); 
	                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
	                            setGraphic(box);
	                        }
	                    }
	                };
	                System.out.println(cell.getIndex());               
	                return cell;
	            }
	            
	        }); 
			noteFavoris.setCellFactory(new Callback<TableColumn<Livre,Integer>,TableCell<Livre,Integer>>(){        
	            @Override
	            public TableCell<Livre,Integer> call(TableColumn<Livre,Integer> param) {                
	                TableCell<Livre,Integer> cell = new TableCell<Livre,Integer>(){
	                    @Override
	                    public void updateItem(Integer item, boolean empty) {                        
	                        if(item!=null){     
	                        	if(item<1)item=1;
	                            HBox box= new HBox();
	                            box.setSpacing(10) ;
	                            
	                            
	                            for(int i=1;i<=item;i++) {
	                            	ImageView imageview = new ImageView(getClass().getResource("/images/rates.png").toString());
		                            imageview.setFitHeight(25);
		                            imageview.setFitWidth(25);
	                            	box.getChildren().add(imageview);
	                            }
	                            
	                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
	                            setGraphic(box);
	                        }
	                    }
	                };
	                System.out.println(cell.getIndex());               
	                return cell;
	            }
	            
	        }); 
    	
    	tableFavoris.setRowFactory(tv -> {
            TableRow<Livre> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Livre rowData = row.getItem();
                    try {
                    	System.out.println(TableFavorisController.this.currentUser.getId_favoris());
                    	
						FXMLLoader loader=new FXMLLoader(getClass().getResource("/vue/Livrecract.fxml"));
	    				loader.load();
	    				LivreCaractController livreCaract=loader.getController();
	    				livreCaract.initData(rowData,this.currentUser);
	    				
	    				System.out.println("Double click on: "+rowData.getId());
	    				
	                    
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
                }
            });
            return row ;
        });
		
	}
	
	
	public void initData(Utilisateur user) {
		currentUser=user;
		ObservableList<Livre> data = FXCollections.observableArrayList();
		model.Traitement t=new model.Traitement();
	        
			for(Livre a:t.getFavorisLivres(currentUser)){
				data.add(a);
				//System.out.println(a.getItem().getTitre()+"3");
				
			}
			tableFavoris.setItems(data);
		
		
	}

}
