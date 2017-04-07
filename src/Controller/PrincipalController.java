package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Notifications;
import org.controlsfx.tools.Platform;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable{

    @FXML
    private MenuItem AbrirCadastroPessoa; // liink com menu item que está sendo criado para abrir a visão Pessoa

    @FXML
    private Pane paneCenterPrincipal; // link do objeto principal Pane do centro para abrir a classes que forem chamadas

    // Criou um metodo para abrir a view Cadastro de Pessoa
    public  void  AbrirCadastroPessoa() throws IOException {

        URL arquivoFxml = getClass().getResource("/View/Pessoa.fxml");// aqui informa qual é a visão que vai abrir
        Parent fxmParent = (Parent) FXMLLoader.load(arquivoFxml); // carregamento da vião com filho
        paneCenterPrincipal.getChildren().add(fxmParent); // aqui tem a varivél fx:id do pane principal aonde abre o view pessoa
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    public void onExit(ActionEvent actionEvent) {

        //Platform.exit();
    }
}
