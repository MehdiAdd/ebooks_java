package controlleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableController implements Initializable {

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> livres;

    @FXML
    private TableColumn<?, ?> genre;

    @FXML
    private TableColumn<?, ?> datePublication;

    @FXML
    private TableColumn<?, ?> auteur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
