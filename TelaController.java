import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import solucao.EstritaAlternancia;
import solucao.Solucao;
import solucao.VariavelDeTravamento;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 
* Nome.............: TelaController
* Funcao...........: 
*************************************************************** */
public class TelaController implements Initializable {
  
  @FXML
  private Pane painel; // -- Pane que representa o painel da interface -- //

  @FXML
  private CheckBox checkBoxMusic; // -- CheckBox da Musica -- //

  @FXML
  private ImageView imgButtonPlay; // -- ImageView do Botao Iniciar -- //

  @FXML
  private ChoiceBox <String> choiceBoxSolutions; // -- ChoicheBox das Solucoes de Tratamento Disponiveis -- //
  private String lista [] = {"Variavel de Travamento", "Estrita Alternancia"}; // -- Lista com os nomes das Solucoes -- //

  @FXML
  private Slider sliderTrem_1; // -- Slider de Velocidade do Trem 1 -- //

  @FXML
  private Slider sliderTrem_2; // -- Slider de Velocidade do Trem 2 -- //

  @FXML
  private CheckBox checkBox1; // -- CheckBox1  de escolha de direcao dos Trens -- //
  @FXML
  private CheckBox checkBox2; // -- CheckBox2  de escolha de direcao dos Trens -- //
  @FXML
  private CheckBox checkBox3; // -- CheckBox3  de escolha de direcao dos Trens -- //
  @FXML
  private CheckBox checkBox4; // -- CheckBox4  de escolha de direcao dos Trens -- //

  @FXML
  private ImageView imgTrainPosition11; // -- ImageView do Trem na Linha 1 Coluna 1 -- //
  @FXML
  private ImageView imgTrainPosition12; // -- ImageView do Trem na Linha 1 Coluna 2 -- //
  @FXML
  private ImageView imgTrainPosition21; // -- ImageView do Trem na Linha 2 Coluna 1 -- //
  @FXML
  private ImageView imgTrainPosition22; // -- ImageView do Trem na Linha 2 Coluna 2 -- //
  
  public static Trem[] processosTrem; // -- Array de Objetos da classe 'Trem' -- //

  @FXML
  private Label labelVelocidade_1; // -- Label que contem o valor da Velocidade do Trem 1 -- //
  @FXML
  private Label labelVelocidade_2; // -- Label que contem o valor da Velocidade do Trem 2 -- //

  @FXML
  private Polyline polyline_1; // -- Polyline1 que representao o Caminho 1 do Trem -- //
  @FXML
  private Polyline polyline_2; // -- Polyline2 que representao o Caminho 2 do Trem -- //
  @FXML
  private Polyline polyline_3; // -- Polyline3 que representao o Caminho 3 do Trem -- //
  @FXML
  private Polyline polyline_4; // -- Polyline4 que representao o Caminho 4 do Trem -- //


  /* *****************************************************************
  * - Metodo: initialize
  * - Funcao: Define as acoes a serem executados ao Iniciar o Programa
  * - Parametros: 'url' do tipo URL e 'rb' do tipo ResourceBundle
  * - Retorno: Nao possui retorno
  ***************************************************************** */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    // -- Adiciona a Lista de solucoes dentro do ChoiceBox -- //
    choiceBoxSolutions.getItems().addAll(lista);
    // -- Ja deixa pre-selecionado o primeiro item do ChoiceBox -- //
    choiceBoxSolutions.getSelectionModel().selectFirst();
   
    // -- Define o 'checkBox3' como selecionado -- //
    checkBox3.setSelected(true);

    // -- Se o 'checkBox3' estiver selecionado, todos os outros ficarao desativados -- //
    if (checkBox3.isSelected()) {
      checkBox2.setDisable(true);
      checkBox1.setDisable(true);
      checkBox4.setDisable(true);
    } // fim do if

    // -- Define o VALOR MAXIMO do 'sliderTrem_1' como 50 -- //
    sliderTrem_1.setMax(50);
    // -- Define o VALOR MINIMO do 'sliderTrem_1' como 1 -- //
    sliderTrem_1.setMin(1);
    // -- Define o VALOR ATUAL do 'sliderTrem_1' como 10 -- //
    sliderTrem_1.setValue(10);

    // -- Define o VALOR MAXIMO do 'sliderTrem_2' como 50 -- //
    sliderTrem_2.setMax(50);
    // -- Define o VALOR MINIMO do 'sliderTrem_2' como 1 -- //
    sliderTrem_2.setMin(1);
    // -- Define o VALOR ATUAL do 'sliderTrem_2' como 10 -- //
    sliderTrem_2.setValue(10);


    /* ***************************************************************
    * - Metodo: Platform.runLater()
    * - Funcao: Labda, ajuda a atualizar as Labels de Velocidade de 
    * cada Trem
    * - Parametros: Nao recebe parametro
    * - Retorno: Nao retorna valor
    *************************************************************** */
    Platform.runLater(() -> {
      // -- Atualiza 'labelVelocidade_1' do Trem 1, utilizando o metodo 'setText()' que recebe o valor inteiro de 'sliderTrem_1' -- //
      labelVelocidade_1.setText("Velocidade: " + (int)sliderTrem_1.getValue() + " px/s"); // -- (px/s: 1 pixel por segundo) -- //
      // -- Atualiza 'labelVelocidade_2' do Trem 2, utilizando o metodo 'setText()' que recebe o valor inteiro de 'sliderTrem_2' -- //
      labelVelocidade_2.setText("Velocidade: " + (int)sliderTrem_2.getValue() + " px/s"); // -- (px/s: 1 pixel por segundo) -- //
    }); // fim do Platform

  } // fim do initialize


  /* ***************************************************************
  * - Metodo: controleDeVelocidade
  * - Funcao: Atualiza as Labels de Velocidade correspondentes com
  * base nos sliders de velocidade sempre que o usuario interage com 
  * um desses sliders na GUI
  * - Parametros: 'e', objeto MouseEvent que eh gerado quando o usuario 
  * interage com um dos sliders de velocidade da GUI
  * - Retorno: Nao retorna nada
  *************************************************************** */
  public void controleDeVelocidade (MouseEvent e) {
    // -- Se o usuario interagir com o 'sliderTrem_1', entao a 'labelVelocidade_1' eh atualizada para o valor correto -- //
    if (e.getSource().equals(sliderTrem_1)) {
      labelVelocidade_1.setText("Velocidade: " + (int)sliderTrem_1.getValue() + " px/s");
    } // fim do if
    // -- Se o usuario interagir com o 'sliderTrem_2', entao a 'labelVelocidade_2' eh atualizada para o valor correto -- //
    if (e.getSource().equals(sliderTrem_2)) {
      labelVelocidade_2.setText("Velocidade: " + (int)sliderTrem_2.getValue() + " px/s");
    } // fim do if
  } // fim de controleDeVelocidade


  /* ***************************************************************
  * - Metodo: buttonIniciar
  * - Funcao: Assim que clicado, inicia os eventos da interface
  * - Parametros: 'me', objeto MouseEvent que eh gerado quando o usu-
  * ario interage com a GUI
  * - Retorno: 
  *************************************************************** */
  @FXML
  public void buttonIniciar(MouseEvent me) {
  // -- Se o evento for o usuario clicando no botao de iniciar entao : -- //
  if (me.getSource().equals(imgButtonPlay)) {
    // -- Mensagem de aviso para o usuario -- //
    System.out.println("[Botao Iniciar foi clicado. Espere o encerramento da Simulacao!]");
    // -- Musica eh iniciada -- //
    Jukebox.play("trilha.wav");

    // -- CRIACAO DE LINHAS E TUNEIS -- //

    LinhaDoTrem Linha_U1 = new LinhaDoTrem("U01", 45);
    LinhaDoTrem Linha_U2 = new LinhaDoTrem("U02", 45);
    LinhaDoTrem Linha_U3 = new LinhaDoTrem("U03", 45);
    LinhaDoTrem Linha_D1 = new LinhaDoTrem("D01", 45);
    LinhaDoTrem Linha_D2 = new LinhaDoTrem("D02", 45);
    LinhaDoTrem Linha_D3 = new LinhaDoTrem("D03", 45);
    Tunel Tunel_1 = new Tunel("T-01", 90, getSolucao(choiceBoxSolutions));
    Tunel Tunel_2 = new Tunel("T-02", 90, getSolucao(choiceBoxSolutions));

    // -- CONEXOES ENTRE AS LINHAS E OS TUNEIS -- //
    Linha_U1.conexao(Tunel_1, null);
    Linha_U2.conexao(Tunel_2, Tunel_1);
    Linha_U3.conexao(null, Tunel_2);
    Tunel_1.conexao(Linha_U2, Linha_D1);
    Tunel_2.conexao(Linha_U3, Linha_D2);
    Linha_D1.conexao(Tunel_1, null);
    Linha_D2.conexao(Tunel_2, Tunel_1);
    Linha_D3.conexao(null, Tunel_2);  

    // -- Se a 'checkBox3' estiver selecionada, entao as imagens 'imgTrainPosition12' e 'imgTrainPosition21' nao ficaram visiveis -- //
    if (checkBox3.isSelected()) {
    imgTrainPosition11.setVisible(true);
    imgTrainPosition22.setVisible(true);
    imgTrainPosition12.setVisible(false);
    imgTrainPosition21.setVisible(false);
    // -- Se a 'checkBox1' estiver selecionada, entao as imagens 'imgTrainPosition11' e 'imgTrainPosition12' nao ficaram visiveis -- //
    } else if (checkBox1.isSelected()) {
      imgTrainPosition11.setVisible(true);
      imgTrainPosition22.setVisible(false);
      imgTrainPosition12.setVisible(true);
      imgTrainPosition21.setVisible(false);
    // -- Se a 'checkBox2' estiver selecionada, entao as imagens 'imgTrainPosition22' e 'imgTrainPosition21' nao ficaram visiveis -- //
    } else if (checkBox2.isSelected()) {
      imgTrainPosition11.setVisible(false);
      imgTrainPosition22.setVisible(true);
      imgTrainPosition12.setVisible(false);
      imgTrainPosition21.setVisible(true);
    // -- Se a 'checkBox4' estiver selecionada, entao as imagens 'imgTrainPosition12' e 'imgTrainPosition21' nao ficaram visiveis -- //
    } else if (checkBox4.isSelected()) {
      imgTrainPosition11.setVisible(false);
      imgTrainPosition22.setVisible(false);
      imgTrainPosition12.setVisible(true);
      imgTrainPosition21.setVisible(true);
    } // fim do else if
    
  // -- CRIANDO OS TRENS COM OS PARAMETROS NECESSARIOS -- //
  Trem greenTrain_1 = new TremUI(0, "Trem Verde", sliderTrem_1, true, Linha_U1, imgTrainPosition11, polyline_1);
  Trem purpleTrain_1 = new TremUI(1, "Trem Roxo", sliderTrem_2, false, Linha_D3, imgTrainPosition22, polyline_2);

  // -- SALVANDO OS TRENS EM UM VETOR -- //
  processosTrem = new Trem [2];
  processosTrem[0] = greenTrain_1;
  processosTrem[1] = purpleTrain_1;

  // -- INICIA A EXECUCAO DAS THREADS DOS TRENS -- //
  greenTrain_1.start();
  purpleTrain_1.start();

  // -- Eh desativado temporariamente o botao de Iniciar -- //
  imgButtonPlay.setDisable(true);
  // -- Eh desabilitado temporariamente a 'choiceBoxSolutions' que contem as Solucoes disponiveis -- //
  choiceBoxSolutions.setDisable(true);
    
  // -- THREAD PARA VERIFICACAO DE EXECUCAO DOS TRENS -- //
    // -- Eh criado um novo objeto Thread que executara o codigo no Lambda passado como parametro -- //
    Thread thread = new Thread(() -> {
        // -- Loop infinito iniciado -- /
        do {
          // -- Verifica se 'greenTrain_1' e 'purplerTrain_1' nao estao mais em execucao, ou seja, se suas Threads foram finalizadas -- //
          // -- Se foram finalizadas, entao : -- //
          if (!greenTrain_1.isAlive() && !purpleTrain_1.isAlive()) {
            // -- Executao o codigo dentro de Lambda na Thread principal da GUI -- //
            Platform.runLater(() -> {
              // -- Reabilida o botao Iniciar -- //
              imgButtonPlay.setDisable(false);
              // -- Altera a imagem do botao Iniciar para um botao Reiniciar -- //
              imgButtonPlay.setImage(new Image("view/botao-reinciar.png"));
              // -- Reabilita o ChoiceBox contendo as Solucoes -- //
              choiceBoxSolutions.setDisable(false);  
              // -- Reabilita as CheckBox 1, 4 e 2 -- //
              checkBox1.setDisable(false);      
              checkBox4.setDisable(false);         
              checkBox2.setDisable(false);         
            }); // fim do Platform.runlater
            // -- Sai do loop infinito -- //
            break;
          } // fim do if
          // -- Continua no loop enquanto for True -- //
        } while (true);
      }); // fim da thread
      // -- Inicia a Thread -- //
      thread.start();
    } // fim do primeiro if
  } // fim de buttonIniciar


  /* ***************************************************************
  * - Metodo: getSolucao
  * - Funcao: Cria e retorna um objeto do tipo 'Solucao', com base na
  * opcao selecionado em um objeto 'ChoiceBox'
  * - Parametros: recebe 'ChoiceBox' como parametro
  * - Retorno: 'solucaoEscolhida' retorna qual solucao foi escolhida
  *************************************************************** */
  public Solucao getSolucao(ChoiceBox choiceBox) {
    // -- 'solucaoEscolhida' do tipo Solucao eh criada -- //
    Solucao solucaoEscolhida;
    // -- Switch recebe como argumento a poscaio selecionda na 'ChoiceBox', 0 - Variavel de Travamento, 1 - Estrita Alternancia -- //
    switch (choiceBox.getSelectionModel().getSelectedIndex()) {
      // -- Se for 0, significa que o primeiro item foi selecionado, e a variavel 'solucaoEscolhida' recebe um novo objeto da classe 'VariavelDeTravamento()' -- //
      case 0:
      solucaoEscolhida = new VariavelDeTravamento();
      break;
      // -- Se for 1, significa que o segundo item foi selecionado, e a variavel 'solucaoEscolhida' recebe um novo objeto da classe 'EstritaAlternancia' -- //
      case 1:
      solucaoEscolhida = new EstritaAlternancia();
      break;
      // -- Se nao for selecionado nem 0 e 1, significa que o primeiro item foi selecionado, e a variavel 'solucaoEscolhida' recebe um novo objeto da classe 'VariavelDeTravamento()' -- //
      default:
      solucaoEscolhida = new VariavelDeTravamento();
    } // fim do switch
    // -- Eh retornado a solucao escolhida de acordo a posicao selecionada na 'ChoiceBox' -- //
    return solucaoEscolhida;
  } // fim de getSolucao


  /* ***************************************************************
  * - Metodo: checkBoxMusic
  * - Funcao: Verifica se 'checkBoxMusic' esta selecionada, se sim,
  * a trilha começa a tocar, se nao, a musica eh 'pausada'
  * - Parametros: 'event' do tipo 'ActionEvent'
  * - Retorno: nao retorna nada
  *************************************************************** */
  @FXML
  public void checkBoxMusic (ActionEvent event) {
    // -- Se a CheckBox estiver selecionada, entao: -- //
   if (checkBoxMusic.isSelected()) {
    // -- Eh iniciado a reproduxao da musica 'trilha.wav' -- //
    Jukebox.play("trilha.wav");
    // -- Caso nao: -- /
   } else {
    // -- A musica eh pausada -- //
    Jukebox.pause();
   } // fim do if-else
  } // fim do CheckBoxMusic


  /* ***************************************************************
  * - Metodo: buttonPause
  * - Funcao: Pausa a musica que esta sendo reproduzida pelo objeto
  * 'Jukebox' quando a 'CheckBox' for desmarcada
  * - Parametros: 'event' do tipo 'ActionEvent'
  * - Retorno: Nao possui retorno
  *************************************************************** */
  @FXML
  public void buttonPause(ActionEvent event) {
    // -- Verifica se a fonte do evento eh a caixa de selecao da 'CheckBox', se sim: -- //
    if (event.getSource().equals(checkBoxMusic)) {
      // -- A reproducao da musica eh pausada -- //
      Jukebox.pause();
    } // fim do if
  } // fim de buttonPause

} // fim da classe
