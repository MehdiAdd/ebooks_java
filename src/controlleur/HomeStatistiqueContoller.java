package controlleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Livre;
import model.Traitement;

public class HomeStatistiqueContoller implements Initializable{
	
	@FXML
    private BarChart<String, Number> livreChart;

    @FXML
    private CategoryAxis xAxe;

    @FXML
    private NumberAxis yAxe;

    @FXML
    private TextField topField;
    
    
	@FXML
    void onAuteurs(ActionEvent event) {

    	setStatistiqueAuteur(5);
    }

  
	@FXML
    void onGenres(ActionEvent event) {

    	setStatistiqueGenre(5);
    	
    }

    @FXML
    void onLivre(ActionEvent event) {

    	setStatistiqueLivre(5);
    }

    private String type="livre";
    @FXML
    void onTopField(ActionEvent event) {

    	try {
    	switch(type) {
    	case "livre":setStatistiqueLivre(Integer.parseInt(topField.getText()));break;
    	case "auteur":setStatistiqueAuteur(Integer.parseInt(topField.getText()));break;
    	case "genre":setStatistiqueGenre(Integer.parseInt(topField.getText()));break;
    	}
    	}catch(Exception e){
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attetion");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
    	}
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Traitement t=new Traitement();
		ObservableList<XYChart.Data<String, Number>> data = getItemsGraphicsStatisticsLivre(t.getStatistiqueByLivres(5));
        XYChart.Series<String, Number> series = new XYChart.Series<>("Note", data);

        livreChart.getData().setAll(series);
       // graphHandler.getChildren().add(barchart);
        setColor();
		
		
		}
		
	public ObservableList<XYChart.Data<String, Number>> getItemsGraphicsStatisticsLivre(ArrayList<Livre> list){
	    ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();


	    for (Livre a : list) {
	    	
			data.add(new XYChart.Data<>(a.getTitre(),a.getNombreEtoiles()));
			// System.out.println(a.getItem().getTitre()+"3");

		}
	            

	    
	    return data;
	}
	public ObservableList<XYChart.Data<String, Number>> getItemsGraphicsStatisticsAuteur(ArrayList<Livre> list){
	    ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();


	    for (Livre a : list) {
	    	
			data.add(new XYChart.Data<>(a.getAuteur(),a.getNombreEtoiles()));
			// System.out.println(a.getItem().getTitre()+"3");

		}
	            

	    
	    return data;
	}

	public ObservableList<XYChart.Data<String, Number>> getItemsGraphicsStatisticsGenre(ArrayList<Livre> list){
	    ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();


	    for (Livre a : list) {
	    	
			data.add(new XYChart.Data<>(a.getGenre(),a.getNombreEtoiles()));
			// System.out.println(a.getItem().getTitre()+"3");

		}
	            

	    
	    return data;
	}
	@SuppressWarnings("unchecked")
	public void setStatistiqueLivre(int number) {

    	Traitement t=new Traitement();
		ObservableList<XYChart.Data<String, Number>> data = getItemsGraphicsStatisticsLivre(t.getStatistiqueByLivres(number));
        XYChart.Series<String, Number> series = new XYChart.Series<>("Note", data);
        type="livre";
        livreChart.getData().clear();
        livreChart.getData().setAll(series);
        livreChart.setTitle("Top "+number+" Livres");
        xAxe.setLabel("Livres");
        setColor();
	}
	@SuppressWarnings("unchecked")
	public void setStatistiqueAuteur(int number) {
		
		Traitement t=new Traitement();
		ObservableList<XYChart.Data<String, Number>> data = getItemsGraphicsStatisticsAuteur(t.getStatistiqueByAuteur(number));
		 type="auteur";
		XYChart.Series<String, Number> series2 = new XYChart.Series<>("Note", data);
        livreChart.getData().clear();
        livreChart.getData().setAll(series2);
        livreChart.setTitle("Top "+number+" Auteurs");
        xAxe.setLabel("Auteurs");
        setColor();
	}
	@SuppressWarnings("unchecked")
	public void setStatistiqueGenre(int number) {
		Traitement t=new Traitement();
		ObservableList<XYChart.Data<String, Number>> data = getItemsGraphicsStatisticsGenre(t.getStatistiqueByGenre(number));
        XYChart.Series<String, Number> series1 = new XYChart.Series<>("Note", data);
        type="genre";
        livreChart.getData().clear();
        livreChart.getData().setAll(series1);
        livreChart.setTitle("Top "+number+" Genres");
        xAxe.setLabel("Genres");
        setColor();
	}
	public void setColor() {
		for(Node n:livreChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #A0CFD9;");
        }
		  Legend legend = (Legend)livreChart.lookup(".chart-legend");
		 /* Color clr=new Color(160, 207, 216, 0);
		    Legend.LegendItem li1=new Legend.LegendItem("Over 8", new Rectangle(10,4,clr));
		    legend.getItems().setAll(li1);*/
		  legend.getItems().get(0).getSymbol().setStyle("-fx-bar-fill: #A0CFD9;");
	}


}


