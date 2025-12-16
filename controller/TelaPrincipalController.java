/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: TrocarTelasController
* Descricao: Controller das telas de menu e sobre. Essa classe eh a responsavel por controlar os 
* botoes e controla as trocas de telas do programa
******************************************************************************************************* */
package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Consumidor;
import model.Produtor;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import model.Consumidor;
import model.Produtor;
import Utils.Buffer;

public class TelaPrincipalController {

  // tamanho do Buffer
  final static int tamanhoBuffer = 5;

  // semaforos
  public static Semaphore mutex = new Semaphore(1);
  public static Semaphore cheio = new Semaphore(0);
  public static Semaphore vazio = new Semaphore(tamanhoBuffer);

  // ImageViews do Produtor e consumidor
  @FXML ImageView imagem_consumidor;
  @FXML ImageView imagem_produtor;
  @FXML ImageView ConsumidorMorrendo;

  // relacionados a animacao dos buffers

  // buffer01
  @FXML ImageView pedestal01_01;
  @FXML ImageView pedestal01_02;
  @FXML ImageView pedestal01_03;
  @FXML ImageView pedestal01_04;
  @FXML ImageView pedestal01_05;
  @FXML ImageView produzindo01;
  @FXML ImageView item01;
  // buffer02
  @FXML ImageView pedestal02_01;
  @FXML ImageView pedestal02_02;
  @FXML ImageView pedestal02_03;
  @FXML ImageView pedestal02_04;
  @FXML ImageView pedestal02_05;
  @FXML ImageView produzindo02;
  @FXML ImageView item02;
  // buffer03
  @FXML ImageView pedestal03_01;
  @FXML ImageView pedestal03_02;
  @FXML ImageView pedestal03_03;
  @FXML ImageView pedestal03_04;
  @FXML ImageView pedestal03_05;
  @FXML ImageView produzindo03;
  @FXML ImageView item03;
  // buffer04
  @FXML ImageView pedestal04_01;
  @FXML ImageView pedestal04_02;
  @FXML ImageView pedestal04_03;
  @FXML ImageView pedestal04_04;
  @FXML ImageView pedestal04_05;
  @FXML ImageView produzindo04;
  @FXML ImageView item04;
  // buffer05
  @FXML ImageView pedestal05_01;
  @FXML ImageView pedestal05_02;
  @FXML ImageView pedestal05_03;
  @FXML ImageView pedestal05_04;
  @FXML ImageView pedestal05_05;
  @FXML ImageView produzindo05;
  @FXML ImageView item05;

  // Estados do Pentagrama
  @FXML ImageView pentagrama01;
  @FXML ImageView pentagrama02;
  @FXML ImageView pentagrama03;
  @FXML ImageView pentagrama04;
  @FXML ImageView pentagrama05;

  // Itens colocados no pentagrama
  @FXML ImageView vela_ritual01;
  @FXML ImageView vela_ritual02;
  @FXML ImageView vela_ritual03;
  @FXML ImageView vela_ritual04;
  @FXML ImageView vela_ritual05;
  @FXML ImageView boneco_voodoo;
  @FXML ImageView caveira_ritual01_01;
  @FXML ImageView caveira_ritual02_01;
  @FXML ImageView caveira_ritual03_01;
  @FXML ImageView caveira_ritual01_02;
  @FXML ImageView caveira_ritual02_02;
  @FXML ImageView caveira_ritual03_02;

  // ImageView dos botoes
  @FXML ImageView pause01Consumidor;
  @FXML ImageView pause02Consumidor;
  @FXML ImageView pause01Produtor;
  @FXML ImageView pause02Produtor;

  // sliders para controle de velocidade
  @FXML Slider sliderConsumidor;
  @FXML Slider sliderProdutor;

  // botoes
  @FXML Button botaoReset;
  @FXML Button botaoPauseConsumidor;
  @FXML Button botaoPauseProdutor;
  @FXML Button botaoVoltar;

  // Image do consumidor parado
  private Image imagem_consumidor_parado = new Image(getClass().getResource("../img/consumidor_parado.gif").toExternalForm());
      
  // Image dos itens invocados pelo produtor
  private Image imagem_vela = new Image(getClass().getResource("../img/vela01.png").toExternalForm());
  private Image imagem_caveira = new Image(getClass().getResource("../img/caveira01.png").toExternalForm());
  private Image imagem_voodoo = new Image(getClass().getResource("../img/boneco_voo_doo.png").toExternalForm());

  // Objetos Produtor Consumidor e Buffer
  Consumidor consumidor;
  Produtor produtor;
  Buffer buffer;

  // Variaveis de controle
  int ItensJaInvocados = 0;// controla qual item deve ser invocado

  int ID_ItemInvocado; // item invocado: 1 = velas, 2 = caveiras, 3 = velas para caveiras, 4 = boneco
                       // voodoo
  int posicaoBuffer; // buffer que sera invocado o item
  int ItemConsumido; // recebe o id do item consumido pelo consumidor

  // variaveis relacionadas ao controle do pentagrama
  int velas = 1;
  int caveiras = 1;
  int velasCaveiras = 1;
  int voodoo = 1;

  //variaveis para troca de telas
  private Stage janela;
  private Scene cena;

  /**************************************************************
   * Metodo: initialize
   * Funcao: metodo que eh inciado quando o arquivo fxml eh carregado e instancia os objetos e starta as threads
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  @FXML
  public void initialize() {

    buffer = new Buffer(tamanhoBuffer);
    buffer.inicializarVazioBuffer();

    consumidor = new Consumidor(this, imagem_consumidor, ConsumidorMorrendo, sliderConsumidor);
    produtor = new Produtor(this, sliderProdutor);

    consumidor.start();
    produtor.start();

  }

  // metodos

    /**************************************************************
   * Metodo: ProduzirItem
   * Funcao: metodo chamado pelo produtor para produzir e inserir itens no buffer
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void ProduzirItem() {

    this.posicaoBuffer = buffer.InserirBufferAleatorio();

    switch (posicaoBuffer) {

      case 0:
        ItemAserInvocado(item01);
        animarProducao(item01, produzindo01, pedestal01_01, pedestal01_02, pedestal01_03, pedestal01_04, pedestal01_05);

        break;
      case 1:
        ItemAserInvocado(item02);
        animarProducao(item02, produzindo02, pedestal02_01, pedestal02_02, pedestal02_03, pedestal02_04, pedestal02_05);

        break;
      case 2:
        ItemAserInvocado(item03);
        animarProducao(item03, produzindo03, pedestal03_01, pedestal03_02, pedestal03_03, pedestal03_04, pedestal03_05);

        break;
      case 3:
        ItemAserInvocado(item04);
        animarProducao(item04, produzindo04, pedestal04_01, pedestal04_02, pedestal04_03, pedestal04_04, pedestal04_05);

        break;
      case 4:
        ItemAserInvocado(item05);
        animarProducao(item05, produzindo05, pedestal05_01, pedestal05_02, pedestal05_03, pedestal05_04, pedestal05_05);

        break;
    }

    buffer.ArmazenarItemIDBuffer(posicaoBuffer, ID_ItemInvocado);
    buffer.adicionarFilaBuffer(posicaoBuffer);

  }

    /**************************************************************
   * Metodo: animarProducao
   * Funcao: metodo acessado pelo produtor que gerencia a animacao de invocacao de itens de acordo com a posicao do buffer escolhido
   * Parametros: ImageView(7) dos itens e pedestais
   * Retorno: void
   ***************************************************************/
  public void animarProducao(ImageView item, ImageView produzindo, ImageView pedestal01, ImageView pedestal02,
      ImageView pedestal03, ImageView pedestal04, ImageView pedestal05) {

    for (int i = 0; i < 9; i++) {

      switch (i) {

        case 0:
          Platform.runLater(() -> {
            item.setOpacity(0);
            item.setVisible(true);
            produzindo.setVisible(true);
            item.setOpacity(0.10);
          });
          break;

        case 2:
          Platform.runLater(() -> {
            item.setOpacity(0.20);
            pedestal02.setVisible(true);
            pedestal01.setVisible(false);
          });

          break;
        case 3:
          Platform.runLater(() -> {
            item.setOpacity(0.40);
            pedestal03.setVisible(true);
            pedestal02.setVisible(false);
          });
          break;
        case 4:
          Platform.runLater(() -> {
            item.setOpacity(0.60);
            pedestal04.setVisible(true);
            pedestal03.setVisible(false);
          });
          break;
        case 5:
          item.setOpacity(0.80);

          break;
        case 6:
          Platform.runLater(() -> {
            item.setOpacity(1);
            pedestal05.setVisible(true);
            pedestal04.setVisible(false);
          });
          break;
        case 7:
          break;
        case 8:
          Platform.runLater(() -> {
            produzindo.setVisible(false);
          });
          break;
      }

      produtor.sleepThread(sliderProdutor.getValue());
    }

  }

    /**************************************************************
   * Metodo: ItemAserInvocado
   * Funcao: metodo acessado pelo produtor que gerencia os IDs e itens que deverao ser invocados
   * Parametros: ImageView
   * Retorno: void
   ***************************************************************/
  public void ItemAserInvocado(ImageView item) {

    if (this.ItensJaInvocados <= 4) { // velas

      Platform.runLater(() -> item.setImage(imagem_vela));
      this.ID_ItemInvocado = 1;
      this.ItensJaInvocados++;

    } else if (this.ItensJaInvocados <= 7) { // caveiras

      Platform.runLater(() -> item.setImage(imagem_caveira));
      this.ID_ItemInvocado = 2;
      this.ItensJaInvocados++;

    } else if (this.ItensJaInvocados <= 10) { // velas das caveiras

      Platform.runLater(() -> item.setImage(imagem_vela));
      this.ID_ItemInvocado = 3;
      this.ItensJaInvocados++;

    } else if (this.ItensJaInvocados <= 11) { // voodoo

      Platform.runLater(() -> item.setImage(imagem_voodoo));
      this.ID_ItemInvocado = 4;
      this.ItensJaInvocados = 0;
    }

    produtor.sleepThread(sliderProdutor.getValue());

  }

    /**************************************************************
   * Metodo: escolherBuffer
   * Funcao: metodo acessado pelo consumidor para saber qual a ordem de buffers deve ser acessada
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public int escolherBuffer() {

    int bufferEscolhido = buffer.removerFilaBuffer();

    return bufferEscolhido;
  }

    /**************************************************************
   * Metodo: ConsumirItem
   * Funcao: metodo acessado pelo consumidor que recebe o ID do item consumido e retira ele do buffer
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void ConsumirItem(int bufferEscolhido) {

    this.ItemConsumido = buffer.RetirarItemIDBuffer(bufferEscolhido); // recebe o ID do item e atualiza o Buffer

    switch (bufferEscolhido) {

      case 0: // buffer 01
        item01.setVisible(false);
        pedestal01_01.setVisible(true);
        pedestal01_05.setVisible(false);
        break;
      case 1: // buffer 02
        item02.setVisible(false);
        pedestal02_01.setVisible(true);
        pedestal02_05.setVisible(false);
        break;
      case 2: // buffer 03
        item03.setVisible(false);
        pedestal03_01.setVisible(true);
        pedestal03_05.setVisible(false);
        break;
      case 3: // buffer 04
        item04.setVisible(false);
        pedestal04_01.setVisible(true);
        pedestal04_05.setVisible(false);
        break;
      case 4: // buffer 05
        item05.setVisible(false);
        pedestal05_01.setVisible(true);
        pedestal05_05.setVisible(false);
        break;
    }
  }

    /**************************************************************
   * Metodo: atualizarPentagrama
   * Funcao: metodo acessado pelo produtor para atualizar o pentagrama
   * Parametros: ImageView(7) dos itens e pedestais
   * Retorno: void
   ***************************************************************/
  public void atualizarPentagrama() {

    boolean ritualCompleto = false;

    switch (ItemConsumido) {

      case 1: // velas

        if (velas == 1) {
          Platform.runLater(() -> {
            vela_ritual01.setVisible(true);
            pentagrama02.setVisible(true);
            pentagrama01.setVisible(false);
        });
          
          velas++;

        } else if (velas == 2) {
            Platform.runLater(() -> {
              vela_ritual02.setVisible(true);
              pentagrama03.setVisible(true);
              pentagrama02.setVisible(false);
            });

            velas++;

        } else if (velas == 3) {
            Platform.runLater(() -> {
              vela_ritual03.setVisible(true);
              pentagrama04.setVisible(true);
              pentagrama03.setVisible(false);
            });

            velas++;

        } else if (velas == 4) {
          Platform.runLater(() -> {
            vela_ritual04.setVisible(true);
          });
            velas++;

        } else if (velas == 5) {
          Platform.runLater(() -> vela_ritual05.setVisible(true));
        }
        break;
      case 2: // Caveiras

        if (caveiras == 1) {
          Platform.runLater(() -> caveira_ritual01_01.setVisible(true));
          caveiras++;
        } else if (caveiras == 2) {
          Platform.runLater(() -> caveira_ritual02_01.setVisible(true));
          caveiras++;
        } else if (caveiras == 3) {
          Platform.runLater(() -> caveira_ritual03_01.setVisible(true));
        }
        break;
      case 3: // velas das caveiras

        if (velasCaveiras == 1) {
          Platform.runLater(() -> caveira_ritual01_02.setVisible(true));
          Platform.runLater(() -> caveira_ritual01_01.setVisible(false));
          velasCaveiras++;
        } else if (velasCaveiras == 2) {
          Platform.runLater(() -> caveira_ritual02_02.setVisible(true));
          Platform.runLater(() -> caveira_ritual02_01.setVisible(false));
          velasCaveiras++;
        } else if (velasCaveiras == 3) {
          Platform.runLater(() -> caveira_ritual03_02.setVisible(true));
          Platform.runLater(() -> caveira_ritual03_01.setVisible(false));
        }
        break;
      case 4: // voodoo

        if (voodoo == 1) {

          Platform.runLater(() -> {
            boneco_voodoo.setVisible(true);
            pentagrama05.setVisible(true);
            pentagrama04.setVisible(false);
          });

          ritualCompleto = true;

        }
      break;

    }
    consumidor.repousoConsumidor();

    if (!ritualCompleto) { // caso nao tenha completado o ritual ele volta para a posicao de espera
      consumidor.retornarPosicaoEspera();

    } else if (ritualCompleto) { // caso tenha completado o ritual ele da inicio a animacao
      consumidor.realizarRitual();
    }
  }

    /**************************************************************
   * Metodo: resetarElementos
   * Funcao: metodo para resetar todos os elementos e parametros da tela ao estado inicial
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void resetarElementos() {

    // para a thread do produtor e consumidor
    produtor.stop();
    consumidor.stop();

    // reseta os Buffers e a fila de Buffers
    buffer.inicializarVazioBuffer();
    buffer.resetarFilaBuffer();

    //reseta as variaveis de controle de itens
    this.ItensJaInvocados = 0;

    // reseta a posicao do consumidor
    this.imagem_consumidor.setLayoutX(611);
    this.imagem_consumidor.setLayoutY(51);
    this.imagem_consumidor.setScaleX(-1); 
    this.imagem_consumidor.setImage(imagem_consumidor_parado);

    // reseta os imageViews do Buffer e Ritual
    resetarRitual();
    resetarBuffer();

    // reseta os semaforos
    mutex = new Semaphore(1);
    cheio = new Semaphore(0);
    vazio = new Semaphore(tamanhoBuffer);

    // reseta os sliders
    sliderConsumidor.setValue(5);
    sliderProdutor.setValue(50);

    // reseta os Botoes
    pause01Consumidor.setVisible(true); // mostra o botao de pausa
    pause02Consumidor.setVisible(false); // esconde o botao de play

    pause01Produtor.setVisible(true); // mostra o botao de pausa
    pause02Produtor.setVisible(false); // esconde o botao de play

  }

    /**************************************************************
   * Metodo: resetarRitual
   * Funcao: metodo para resetar as Imagens do ritual
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void resetarRitual() {

    //reseta as variaveis de controle 
    this.velas = 1;
    this.caveiras = 1;
    this.velasCaveiras = 1;
    this.voodoo = 1;

    Platform.runLater(() -> {

      // reseta as velas
      this.vela_ritual01.setVisible(false);
      this.vela_ritual02.setVisible(false);
      this.vela_ritual03.setVisible(false);
      this.vela_ritual04.setVisible(false);
      this.vela_ritual05.setVisible(false);

      // reseta as caveiras
      this.caveira_ritual01_01.setVisible(false);
      this.caveira_ritual01_02.setVisible(false);
      this.caveira_ritual02_01.setVisible(false);
      this.caveira_ritual02_02.setVisible(false);
      this.caveira_ritual03_01.setVisible(false);
      this.caveira_ritual03_02.setVisible(false);

      // reseta o voodoo
      boneco_voodoo.setVisible(false);

      // reseta o pentagrama
      this.pentagrama01.setVisible(true);
      this.pentagrama02.setVisible(false);
      this.pentagrama03.setVisible(false);
      this.pentagrama04.setVisible(false);
      this.pentagrama05.setVisible(false);

    });
  }


    /**************************************************************
   * Metodo: resetarBuffer
   * Funcao: metodo para resetar as Imagens dos Buffers
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void resetarBuffer(){



    Platform.runLater(() -> {
    
    // reseta os Buffers

    // buffer 01
    this.pedestal01_01.setVisible(true);
    this.pedestal01_02.setVisible(false);
    this.pedestal01_03.setVisible(false);
    this.pedestal01_04.setVisible(false);
    this.pedestal01_05.setVisible(false);
    this.produzindo01.setVisible(false);
    this.item01.setVisible(false);
    // buffer 02
    this.pedestal02_01.setVisible(true);
    this.pedestal02_02.setVisible(false);
    this.pedestal02_03.setVisible(false);
    this.pedestal02_04.setVisible(false);
    this.pedestal02_05.setVisible(false);
    this.produzindo02.setVisible(false);
    this.item02.setVisible(false);
    // buffer 03
    this.pedestal03_01.setVisible(true);
    this.pedestal03_02.setVisible(false);
    this.pedestal03_03.setVisible(false);
    this.pedestal03_04.setVisible(false);
    this.pedestal03_05.setVisible(false);
    this.produzindo03.setVisible(false);
    this.item03.setVisible(false);
    // buffer 04
    this.pedestal04_01.setVisible(true);
    this.pedestal04_02.setVisible(false);
    this.pedestal04_03.setVisible(false);
    this.pedestal04_04.setVisible(false);
    this.pedestal04_05.setVisible(false);
    this.produzindo04.setVisible(false);
    this.item04.setVisible(false);
    // buffer 05
    this.pedestal05_01.setVisible(true);
    this.pedestal05_02.setVisible(false);
    this.pedestal05_03.setVisible(false);
    this.pedestal05_04.setVisible(false);
    this.pedestal05_05.setVisible(false);
    this.produzindo05.setVisible(false);
    this.item05.setVisible(false);
  });
  }
  // Fim dos metodos

  // Botoes

    /**************************************************************
   * Metodo: ActionBotaoReset
   * Funcao: metodo para resetar a simulacao
   * Parametros: event
   * Retorno: void
   ***************************************************************/
  @FXML
  void ActionBotaoReset(ActionEvent event) {

    // reseta todos os elementos presentes na tela para o estado inicial
    resetarElementos();

    // instancia novas threads para o produtor e consumidor
    produtor = new Produtor(this, sliderProdutor);
    consumidor = new Consumidor(this, imagem_consumidor, ConsumidorMorrendo, sliderConsumidor);

    // inicia as novas threads criadas
    produtor.start();
    consumidor.start();
  }

    /**************************************************************
   * Metodo: ActionBotaoPauseConsumidor
   * Funcao: metodo para pausar a thread do consumidor
   * Parametros: event
   * Retorno: void
   ***************************************************************/
  @FXML
  @SuppressWarnings("deprecated")
  void ActionBotaoPauseConsumidor(ActionEvent event) {
    if (pause01Consumidor.isVisible()) {
      consumidor.suspend(); // pausa a thread

      pause01Consumidor.setVisible(false); // esconde botao de pausa
      pause02Consumidor.setVisible(true); // mostra botao de play

    } else {
      consumidor.resume(); // retoma a thread

      pause01Consumidor.setVisible(true); // mostra o botao de pausa
      pause02Consumidor.setVisible(false); // esconde o botao de play
    }
  }

    /**************************************************************
   * Metodo: ActionBotaoPauseProdutor
   * Funcao: metodo para pausar a thread do produtor
   * Parametros: event
   * Retorno: void
   ***************************************************************/
  @FXML
  @SuppressWarnings("deprecated")
  void ActionBotaoPauseProdutor(ActionEvent event) {
    if (pause01Produtor.isVisible()) {
      produtor.suspend(); // pausa a thread

      pause01Produtor.setVisible(false); // esconde botao de pausa
      pause02Produtor.setVisible(true); // mostra botao de play

    } else {
      produtor.resume(); // retoma a thread

      pause01Produtor.setVisible(true); // mostra o botao de pausa
      pause02Produtor.setVisible(false); // esconde o botao de play
    }
  }

    /**************************************************************
   * Metodo: ActionBotaoVoltar
   * Funcao: volta para a tela inicial
   * Parametros: event
   * Retorno: void
   ***************************************************************/
  public void ActionBotaoVoltar(ActionEvent event) throws IOException{

    resetarElementos();

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();
  }
}
