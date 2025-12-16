/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 25/05/2025
* Ultima alteracao: 17/06/2025 
* Nome: Consumidor
* Descricao: Essa classe gerencia o objeto consumidor e controla suas animacoes e logicas, alem de garantir a exclusao mutua ao Buffer atraves de semaforos
********************************************************************************************************/
package model;

import controller.TelaPrincipalController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Consumidor extends Thread {

  //controller
  private TelaPrincipalController controller = new TelaPrincipalController();

  //ImageView do consumidor
  private ImageView imagem_consumidor;
  //ImageView do consumidor Morrendo no Ritual
  private ImageView consumidorMorrendo;

  //Imagens do consumidor andando/parado
  private Image imagem_consumidor_andando = new Image(getClass().getResource("../img/consumidor_andando.gif").toExternalForm());
  private Image imagem_consumidor_parado = new Image(getClass().getResource("../img/consumidor_parado.gif").toExternalForm());

  //imagens relacionadas a realizacao do ritual
  private Image ritual01 = new Image(getClass().getResource("../img/consumidor_morrendo/morendo01.png").toExternalForm());
  private Image ritual02 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo02.png").toExternalForm());
  private Image ritual03 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo03.png").toExternalForm());
  private Image ritual04 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo04.png").toExternalForm());
  private Image ritual05 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo05.png").toExternalForm());
  private Image ritual06 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo06.png").toExternalForm());
  private Image ritual07 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo07.png").toExternalForm());
  private Image ritual08 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo08.png").toExternalForm());
  private Image ritual09 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo09.png").toExternalForm());
  private Image ritual10 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo10.png").toExternalForm());
  private Image ritual11 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo11.png").toExternalForm());
  private Image ritual12 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo12.png").toExternalForm());
  private Image ritual13 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo13.png").toExternalForm());
  private Image ritual14 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo14.png").toExternalForm());
  private Image ritual15 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo15.png").toExternalForm());
  private Image ritual16 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo16.png").toExternalForm());
  private Image ritual17 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo17.png").toExternalForm());
  private Image ritual18 = new Image(getClass().getResource("../img/consumidor_morrendo/morrendo18.png").toExternalForm());

  //slider de velocidade do consumidor
  private Slider sliderConsumidor;

  //armazena o buffer a ser acessado pelo consumidor
  private int bufferEscolhido;

  //variavel para conferir se a thread esta pausada ou nao
  private boolean pausado = false;

  //variavel para resetar a thread
  private boolean reset = false;


    /**************************************************************
   * Metodo: Construtor da Classe Consumidor
   * Funcao: inicializar o objeto consumidor
   * Parametros: controller, ImageView, ImageView, SLider
   * Retorno: void
   ***************************************************************/
  public Consumidor(TelaPrincipalController controller, ImageView imagem_consumidor, ImageView consumidor_morrendo, Slider sliderConsumidor) {

    this.controller = controller;
    this.imagem_consumidor = imagem_consumidor;
    this.sliderConsumidor = sliderConsumidor;
    this.consumidorMorrendo = consumidor_morrendo;

  }

    /**************************************************************
   * Metodo: run
   * Funcao: metodo run que inicia a thread
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  @Override
  public void run() {
    while (true) {

      try {

        //pause da thread
        while(getPausado()){
          Thread.sleep(100);
          if(reset) break;
        }

        TelaPrincipalController.cheio.acquire();

        TelaPrincipalController.mutex.acquire();

        bufferEscolhido = controller.escolherBuffer(); //escolhe um buffer para acessar

        caminharBuffer(bufferEscolhido); //caminha ate o buffer escolhido

        controller.ConsumirItem(bufferEscolhido); //consome o item do buffer

        TelaPrincipalController.mutex.release();

        TelaPrincipalController.vazio.release();

        retornarPentagrama(bufferEscolhido); //retorna ate o pentagrama

        sleep(1000); //controle de velocidade da thread
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  //=================================== METODOS ===================================


  // ====================== METODOS RELACIONADOS A MOVIMENTACAO DO CONSUMIDOR ======================

    /**************************************************************
   * Metodo: caminharBuffer
   * Funcao: Responsavel por gerenciar as opcoes de animacao para caminhar ate o buffer escolhido
   * Parametros: int 
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer(int opcaoBuffer){

    switch(opcaoBuffer){

      case 0: //buffer 01
        caminharBuffer01();
      break;
      case 1: //buffer 02
        caminharBuffer02();
      break;
      case 2: //buffer 03
        caminharBuffer03();
      break;
      case 3: //buffer 04
        caminharBuffer04();
      break;
      case 4: //buffer 05
        caminharBuffer05();
      break;
    }
  }

    /**************************************************************
   * Metodo: retornarPentagrama
   * Funcao: Responsavel por gerenciar as opcoes de animacao para retornar ate o pentagrama de acordo com o bufferescolhido
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void retornarPentagrama(int opcaoBuffer){

    switch(opcaoBuffer){

      case 0: //Buffer 01
        voltarParaPentagrama01();
      break;
      case 1: //Buffer 02
        voltarParaPentagrama02();
      break;
      case 2: //Buffer 03
        voltarParaPentagrama03();
      break;
      case 3: //Buffer 04
        voltarParaPentagrama04();
      break;
      case 4: //Buffer 05
        voltarParaPentagrama05();
      break;
    }
  }
  
  /**************************************************************
   * Metodo: caminharBuffer01
   * Funcao: Responsavel pela movimentação do consumidor ate o buffer 01
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer01() {
    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() > 260) {

      if (this.imagem_consumidor.getLayoutX() > 500) { // Horizontal

        Platform.runLater(() ->  this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      } else if (this.imagem_consumidor.getLayoutX() > 400) { //Diagonal

        Platform.runLater(() -> { 
          this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5);
          this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 7);
        });

      } else if (this.imagem_consumidor.getLayoutX() > 260) { //Horizontal

        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5)); 
      }

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: caminharBuffer02
   * Funcao: Responsavel pela movimentação do consumidor ate o buffer 02
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer02() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() > 180) {

      if (this.imagem_consumidor.getLayoutX() > 500) { //Horizontal
        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      } else if (this.imagem_consumidor.getLayoutX() > 440) { //Diagonal
        Platform.runLater(() -> {
          this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5);
          this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 5);
        });

      } else if (this.imagem_consumidor.getLayoutX() > 180) { //Horizontal
        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      }
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: caminharBuffer03
   * Funcao: Responsavel pela movimentação do consumidor ate o buffer 03
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer03() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() > 235) { //Horizonal
      Platform.runLater(() -> {
        this.imagem_consumidor.setScaleX(-1);
        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5);

      });

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: caminharBuffer04
   * Funcao: Responsavel pela movimentação do consumidor ate o buffer 04
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer04() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() > 253) { 

      if ((this.imagem_consumidor.getLayoutY() < 190) && (this.imagem_consumidor.getLayoutX() > 253)) { //Vertical
        Platform.runLater(() -> this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() + 5));

      } else if ((this.imagem_consumidor.getLayoutY() == 191) && (this.imagem_consumidor.getLayoutX() > 253)) { //Horizontal
        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      }

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: caminharBuffer05
   * Funcao: Responsavel pela movimentação do consumidor ate o buffer 05
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void caminharBuffer05() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() > 253) {

      if ((this.imagem_consumidor.getLayoutY() < 270) && (this.imagem_consumidor.getLayoutX() > 253)) { //Vertical
        Platform.runLater(() -> this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() + 5));

      } else if ((this.imagem_consumidor.getLayoutY() == 271) && (this.imagem_consumidor.getLayoutX() > 253)) { //Horizontal
        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      }

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: voltarParaPentagrama01
   * Funcao: Responsavel pela movimentação do consumidor ate o pentagrama a partir
   * do buffer01
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void voltarParaPentagrama01() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() < 680) { //Horizontal

      Platform.runLater(() -> {
        this.imagem_consumidor.setScaleX(1);
        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);

      });

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

    controller.atualizarPentagrama(); //atualiza o pentagrama

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: voltarParaPentagrama02
   * Funcao: Responsavel pela movimentação do consumidor ate o pentagrama a partir
   * do buffer02
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void voltarParaPentagrama02() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando)); //muda a imagem do consumidor para andando

    while (this.imagem_consumidor.getLayoutX() < 680) { //Horizontal

      Platform.runLater(() -> {
        this.imagem_consumidor.setScaleX(1);
        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);

      });

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

    controller.atualizarPentagrama(); //atualiza o pentagrama

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: voltarParaPentagrama03
   * Funcao: Responsavel pela movimentação do consumidor ate o pentagrama a partir
   * do buffer03
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void voltarParaPentagrama03() {

    Platform.runLater(() -> {
      this.imagem_consumidor.setScaleX(1); //vira o consumidor para direita
      this.imagem_consumidor.setImage(imagem_consumidor_andando); //muda a imagem do consumidor para andando
    
    });

    while (this.imagem_consumidor.getLayoutX() < 620) { //Horizontal

      Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5));
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    while ((this.imagem_consumidor.getLayoutX() <= 690) && (this.imagem_consumidor.getLayoutY() >= -20)) { //Diagonal

      Platform.runLater(() -> {

        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);
        this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 5);

      });
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

    controller.atualizarPentagrama(); //atualiza o pentagrama

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: voltarParaPentagrama04
   * Funcao: Responsavel pela movimentação do consumidor ate o pentagrama a partir
   * do buffer04
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void voltarParaPentagrama04() {

    Platform.runLater(() -> {
      this.imagem_consumidor.setScaleX(1); //vira o consumidor para a direita
      this.imagem_consumidor.setImage(imagem_consumidor_andando); //muda a imagem do consumidor para andando
    
    });

    while (this.imagem_consumidor.getLayoutX() < 311) { //Horizontal

      Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5));
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    while ((this.imagem_consumidor.getLayoutX() <= 690) && (this.imagem_consumidor.getLayoutY() >= -20)) { //Diagonal

      Platform.runLater(() -> {

        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);
        this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 5);

      });
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    while (this.imagem_consumidor.getLayoutX() < 680) { //Horizontal

      Platform.runLater(() -> {
        this.imagem_consumidor.setScaleX(1);
        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);

      });

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

    controller.atualizarPentagrama(); //atualiza o pentagrama

    repousoConsumidor(); //repousa o consumidor

  }

  /**************************************************************
   * Metodo: voltarParaPentagrama05
   * Funcao: Responsavel pela movimentação do consumidor ate o pentagrama a partir
   * do buffer05
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void voltarParaPentagrama05() {

    Platform.runLater(() -> {
      this.imagem_consumidor.setScaleX(1); //vira o consumidor para a direita
      this.imagem_consumidor.setImage(imagem_consumidor_andando); //muda a imagem do consumidor para andando
    
    });

    while (this.imagem_consumidor.getLayoutX() < 211) { //Horizontal

      Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5));
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    while ((this.imagem_consumidor.getLayoutX() <= 690) && (this.imagem_consumidor.getLayoutY() >= -20)) { //Diagonal

      Platform.runLater(() -> {

        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);
        this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 5);

      });
      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    while (this.imagem_consumidor.getLayoutX() < 680) { //Horizontal

      Platform.runLater(() -> {
        this.imagem_consumidor.setScaleX(1); //vira o consumidor para a direita
        this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 5);

      });

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }

    repousoConsumidor(); //repousa o consumidor

    controller.atualizarPentagrama(); //atualiza o pentagrama

    repousoConsumidor(); //repousa o consumidor

  }

    /**************************************************************
   * Metodo: retornarPosicaoEspera
   * Funcao: Responsavel pela animacao que movimenta o consumidor ate a posicao de espera pelo acesso ao Buffer
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void retornarPosicaoEspera(){

    while (this.imagem_consumidor.getLayoutX() > 611) {

      Platform.runLater(() -> {
        this.imagem_consumidor.setImage(imagem_consumidor_andando); //muda a imagem do consumidor para andando
        this.imagem_consumidor.setScaleX(-1); //vira o consumidor para a direita
      });

      if ((this.imagem_consumidor.getLayoutY() <= 50) && (this.imagem_consumidor.getLayoutX() > 611)) { //Vertical
        Platform.runLater(() -> this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() + 5));

      }else if ((this.imagem_consumidor.getLayoutY() == 51) && (this.imagem_consumidor.getLayoutX()) > 611) { //Horizontal
        Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      }

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }
  }


  //======================== METODOS RELACIONADOS AO RITUAL ========================


    /**************************************************************
   * Metodo: realizarRitual
   * Funcao: Responsavel pela animacao que da inicio ao ritual e morte do consumidor apos todos os itens do ritual forem colocados
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void realizarRitual(){

    entrarPentagrama(); //anda para o centro do pentagrama

    this.imagem_consumidor.setVisible(false); //deixa invisivel o ImageView que controla o consumidor andando
    this.consumidorMorrendo.setVisible(true); //deixa visivel o ImageView que controla o consumidor morrendo

    for(int i = 0; i < 18; i++){

      switch(i){ //animacao do consumidor morrendo

        case 0:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual01));
        break;
        case 1:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual02));
        break;
        case 2:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual03));
        break;
        case 3:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual04));
        break;
        case 4:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual05));
        break;
        case 5:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual06));
        break;
        case 6:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual07));
        break;
        case 7:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual08));
        break;
        case 8:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual09));
        break;
        case 9:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual10));
        break;
        case 10:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual11));
        break;
        case 11:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual12));
        break;
        case 12:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual13));
        break;
        case 13:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual14));
        break;
        case 14:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual15));
        break;
        case 15:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual16));
        break;
        case 16:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual17));
        break;
        case 17:
          Platform.runLater(() -> this.consumidorMorrendo.setImage(ritual18));
        break;
      }

      sleepThreadFixo(); //velocidade de animacao fixa
    }
    controller.resetarRitual();
    entrarNovoConsumidor(); //chama um novo consumidor

  }

    /**************************************************************
   * Metodo: entrarPentagrama
   * Funcao: Responsavel pela animacao que movimenta o consumidor ate o centro do pentagrama
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void entrarPentagrama(){

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_andando));
    while(this.imagem_consumidor.getLayoutX() < 859){ //Horizontal

      Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() + 2));

      sleepThreadFixo(); //velocidade de animacao fixa
    }

    while(this.imagem_consumidor.getLayoutY() > -89){ //Vertical

      Platform.runLater(() -> this.imagem_consumidor.setLayoutY(this.imagem_consumidor.getLayoutY() - 2));

      sleepThreadFixo(); //velocidade de animacao fixa
    }

  }

    /**************************************************************
   * Metodo: entrarNovoConsumidor
   * Funcao: Responsavel pela animacao de um novo consumidor entrando na tela
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void entrarNovoConsumidor(){

    Platform.runLater(() -> { //reseta a posicao do ImageView do consumidor

      this.consumidorMorrendo.setVisible(false);
      this.imagem_consumidor.setImage(imagem_consumidor_andando);
      this.imagem_consumidor.setScaleX(-1);
      this.imagem_consumidor.setLayoutY(51);
      this.imagem_consumidor.setLayoutX(1200);
      this.imagem_consumidor.setVisible(true);
    });

    while(this.imagem_consumidor.getLayoutX() > 611){ //Horizontal
      Platform.runLater(() -> this.imagem_consumidor.setLayoutX(this.imagem_consumidor.getLayoutX() - 5));

      sleepThread(this.sliderConsumidor.getValue()); //controle de velocidade
    }
  }
  

    /**************************************************************
   * Metodo: sleepThread
   * Funcao: Responsavel pelo controle de velocidade, coloca a thread para dormir por x tempo
   * Parametros: double velocidade(slider)
   * Retorno: void
   ***************************************************************/
  public void sleepThread(double velocidade) {

    try {
      Thread.sleep((long) (velocidade * 10));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

    /**************************************************************
   * Metodo: repousoConsumidor
   * Funcao: Responsavel pela animacao que deixa o consumidor parando fazendo alguma acao
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void repousoConsumidor() {

    Platform.runLater(() -> this.imagem_consumidor.setImage(imagem_consumidor_parado)); //muda a imagem do consumidor para parado

    sleepThread(sliderConsumidor.getValue()); //controle de velocidade

    for (int i = 0; i < 12; i++) { //busy wait proposital para deixar o consumidor parado

      sleepThread(sliderConsumidor.getValue()); //controle de velocidade
    }
  }

    /**************************************************************
   * Metodo: sleepThreadFixo
   * Funcao:  um controle de velocidade fixo, para animacoes que nao quero que o usuario controle sua velocidade
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  public void sleepThreadFixo(){

    try {
      Thread.sleep(60); //velocidade 60
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //getters e setters

   /****************************************************************
  * Metodo: getPausado
  * Funcao: retorna o estado de pausa da thread
  * Parametros: nulo
  * Retorno: boolean 
  *************************************************************** */
  public boolean getPausado(){
    return pausado;
  }

  /****************************************************************
  * Metodo: getReset
  * Funcao: retorna o flag de reset da thread
  * Parametros: nulo
  * Retorno: boolean 
  *************************************************************** */
  public boolean getReset() {
    return reset;
  }

    /* ***************************************************************
  * Metodo: setReset
  * Funcao: define o flag de reset da thread
  * Parametros: boolean 
  * Retorno: void
  *************************************************************** */
  public void setReset(boolean reset) {
    this.reset = reset;
}
}
