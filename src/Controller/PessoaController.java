package Controller;

import Model.Pessoa;
import com.sun.nio.sctp.Notification;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.Duration.*;

public class PessoaController implements Initializable{
    @FXML
    private TextField txtId;
    @FXML
    private TextField CadatroPessoaNome;
    @FXML
    private TextField CadastroPessoaSobrenome;
    @FXML
    private TextField CadastroPessoaCPF;
    @FXML
    private TextField CadastroPessoaTelefone;
    @FXML
    private TextField CadastroPessoaEndereco;
    @FXML
    private TextField CadastroPessoaCelular;
    @FXML
    private DatePicker CadastroPessoaDatNasc;
    @FXML
    private ComboBox<String> CadastroPessoaCidade;
    @FXML
    private TextField CadastroPessoaSexo;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFeminino;
    @FXML
    private Button CadastroPessoaBtnSalvar;
    @FXML
    private Button CadastroPessoaBtnEditar;
    @FXML
    private HBox CadastroPessoaHboxSexo;
    @FXML
    private ToggleGroup Sexo;
    @FXML
    private TableView<Pessoa> CadastroPessoaTableView;
    @FXML
    private TableColumn<Pessoa, String> CadastroPessoaTableViewID;
    @FXML
    private TableColumn<Pessoa, String> CadastroPessoaTableViewNome;
    @FXML
    private TableColumn<Pessoa, String> CadastroPessoaTableViewCPF;
    @FXML
    private TableColumn<Pessoa, String> CadastroPessoaTableViewCelular;
    @FXML
    private Pane panePrincipal;



    ObservableList<Pessoa> pessoasView;

    public void Salvar(){
        Pessoa pessoa = new Pessoa(); // cria objeto pessoa

        if(txtId.getText().equals("")){
            pessoa.setId(setarPessoaId());
        }
        //inicia a cptura das informações adicionadas nos campos
        pessoa.setNome(CadatroPessoaNome.getText());
        pessoa.setSobreNome(CadastroPessoaSobrenome.getText());
        pessoa.setCpf(CadastroPessoaCPF.getText());
        pessoa.setDataNascimento(CadastroPessoaDatNasc.getValue());
        pessoa.setCidade(CadastroPessoaCidade.getValue());
        pessoa.setCelular(CadastroPessoaCelular.getText());
        pessoa.setEndereco(CadastroPessoaEndereco.getText());
        pessoa.setTelefone(CadastroPessoaTelefone.getText());
        RadioButton rb = (RadioButton) Sexo.getSelectedToggle();
        pessoa.setSexo(rb.getText());

        pessoas.add(pessoa);
        populaView(pessoas);

        limparCampos();

        String Salvo = "Salvo Com Sucesso!";
        mostrarMsg(Salvo);
    }

    public void Editar(){

    }

    List<Pessoa> pessoas = new ArrayList<>();

    public void populaView(List<Pessoa> pessoas){

        CadastroPessoaTableViewID.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("id"));
        CadastroPessoaTableViewNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        CadastroPessoaTableViewCPF.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
        CadastroPessoaTableViewCelular.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("celular"));
        pessoasView = FXCollections.observableArrayList(pessoas);

        CadastroPessoaTableView.setItems(pessoasView);
    }

    public Integer setarPessoaId(){
        Integer id =0;
        int posicao = 0;

        Pessoa p = new Pessoa();
        if(pessoas.size() > 0){
            posicao = pessoas.size()-1;
            p = pessoas.get(posicao);
            id = p.getId() +1;
        }else{
            id =1;
        } return id;
    }

    public void limparCampos(){
        txtId.setText("");
        CadatroPessoaNome.setText("");
        CadastroPessoaSobrenome.setText("");
        CadastroPessoaCelular.setText("");
        CadastroPessoaCidade.setValue(null);
        CadastroPessoaDatNasc.setValue(null);
        CadastroPessoaCPF.setText("");
        CadastroPessoaTelefone.setText("");
        CadastroPessoaEndereco.setText("");
        CadastroPessoaBtnSalvar.setDisable(true);
        CadastroPessoaBtnEditar.setDisable(false);
    }

    public void mostrarMsg(String msg){
                 Notifications.create()
                .title("Mensagem")
                .text(msg)
                .owner(panePrincipal)
                .hideAfter(Duration.seconds(3))
                .darkStyle()
                .position(Pos.TOP_RIGHT)
                .showInformation();

    }

    public void preparaEdicao(){
        Pessoa p = (Pessoa) CadastroPessoaTableView.getSelectionModel().getSelectedItem();
        setarValores(p);
        CadastroPessoaBtnSalvar.setDisable(false);
        CadastroPessoaBtnEditar.setDisable(true);
        mostrarMsg("Carregado para Edição!");
    }

    public void setarValores(Pessoa p){

            Integer id = p.getId();
            txtId.setText(id.toString());
            CadatroPessoaNome.setText(p.getNome());
            CadastroPessoaSobrenome.setText(p.getSobreNome());
            CadastroPessoaCPF.setText(p.getCpf());
            CadastroPessoaDatNasc.setValue(p.getDataNascimento());
            CadastroPessoaTelefone.setText(p.getTelefone());
            CadastroPessoaCelular.setText(p.getCelular());
            CadastroPessoaEndereco.setText(p.getEndereco());
            CadastroPessoaCidade.setValue(p.getCidade());

            if (p.getSexo().equals(rbMasculino)){
                Sexo.selectToggle(rbMasculino);
            }else{
                Sexo.selectToggle(rbFeminino);
            }

    }

    public void editar(){
        List<Pessoa> pessoaAuxRemove = new ArrayList<Pessoa>();

        Pessoa p = new Pessoa();
        p.setId(Integer.parseInt(txtId.getText()));
        p.setNome(CadatroPessoaNome.getText());
        p.setSobreNome(CadastroPessoaSobrenome.getText());
        p.setCpf(CadastroPessoaCPF.getText());
        p.setDataNascimento(CadastroPessoaDatNasc.getValue());
        p.setTelefone(CadastroPessoaTelefone.getText());
        p.setCelular(CadastroPessoaCelular.getText());
        p.setEndereco(CadastroPessoaEndereco.getText());
        p.setCidade(CadastroPessoaCidade.getValue());
        RadioButton rb = (RadioButton) Sexo.getSelectedToggle();
        p.setSexo(rb.getText());

        for (Pessoa pessoa : pessoas){
            if (pessoa.getId() == p.getId()){
                pessoaAuxRemove.add(p);
            }//fim do if
        }//fim do for
        pessoas.removeAll(pessoaAuxRemove);
        pessoas.add(p);
        populaView(pessoas);
        String editado = "Salvo com Sucesso!";
        mostrarMsg(editado);
        limparCampos();

    }//fim do metodo

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        //Carregar o combobox com as cidades

            //Criar lista com todas as cidades
                List<String> cidades = new ArrayList<String>();
                    for (int i=0; i<=9; i++){
                        cidades.add("Cidade "+(i+1));
                    }
         CadastroPessoaCidade.getItems().addAll(cidades); // aqui chama o objeto e adiciona dentro dela a lista acima.

    }
}
