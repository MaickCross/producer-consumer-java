/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 25/05/2025
* Ultima alteracao: 17/06/2025 
* Nome: TelaInicialController
* Descricao: Controller da tela inicial. Essa classe eh a responsavel por controlar os 
* botoes e controla as trocas de telas do programa
******************************************************************************************************* */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;


public class TelaInicialController implements Initializable{
  
  @FXML Button botaoIniciar;
  @FXML ImageView icone;

  private Stage janela;
  private Scene cena;



  /****************************************************************
  * Metodo: initialize
  * Funcao: responsavel pela inicializacao e configuracao dos elementos da GUI
  * Parametros: URL url, ResourceBundle rb
  * Retorno: void
  ****************************************************************/
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    icone.setVisible(false);
    botaoIniciar.setOnMouseEntered(e -> {
    icone.setVisible(true);
    });
    botaoIniciar.setOnMouseExited(e -> {
    icone.setVisible(false);
    });
  }

  // Metodos relacionados ao botao jogar:

  /**************************************************************
   * Metodo: Iniciar
   * Funcao: metodo ligado ao botao "botaoIniciarr". Troca para a tela de simulacao,
   * Carrega o FXML
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Iniciar(ActionEvent event) throws IOException {
    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaPrincipal.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();

  }


}



