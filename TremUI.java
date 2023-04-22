import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 21/04/2023
* Ultima alteracao.: 22/04/2023
* Nome.............: TremUI
* Funcao...........: descricao do que eh o programa
*************************************************************** */
public class TremUI extends Trem {

  /* ***************************************************************
  * - 'trem' :  Eh do tipo 'ImageView', representa uma imagem na tela
  * que representa um trem
  * - 'rota' :  Eh do tipo 'Polyline', representa uma linha poligonal
  * na tela que representa a rota do trem
  *************************************************************** */
  public ImageView trem;
  public Polyline rota;
  

  /* ********************************************************************************************
  * - Nome do Constutor: TremUI
  * - Funcao: Eh utilizado para criar objetos dessa classe, extende a classe 'Trem' e adiciona a 
  * capacidade de senhar o trem e sua rota em uma GUI
  * - Parametros: 'piD' (int que representa o ID do trem), 'nome' (uma string que representa o 
  * nome do trem), 'slider' (um objeto do tipo 'Slider' que representa o controle de velocidade do
  * trem), 'direcao' (um booleano que indica a direcao do trem), 'rota' (um objeto do tipo 'LinhaDoTrem' 
  * que representa a rota de um trem),'Trem' (um objeto do tipo 'ImageView' que representa a imagem 
  * do trem na GUI), 'polyline' (objeto do tipo 'Polyline' que representa a linha da rota do trem 
  * na GUI)
  * - Retorno: Nao possui retorno
  * - Explicacao: Cria um objeto do tipo 'TremUI' que estende 'Trem'e adiciona a capacidade de 
  * desenhar o trem e sua rota em uma GUI.
 ******************************************************************************************** */
  public TremUI(int pID, String nome, Slider slider, boolean direcao, LinhaDoTrem rota, ImageView Trem, Polyline polyline) {
    super(pID, nome, slider, direcao, rota);
    this.trem = Trem;
    // -- Define a posicao horizontal da imagem na GUI como a posicao horizontal da linha da rota do trem -- //
    this.trem.setLayoutX(polyline.getLayoutX());
    // -- Define a posicao vertical da imagem na GUI como a posicao vertical da linha rota do trem menos um pixel -- //
    this.trem.setLayoutY(polyline.getLayoutY() -1);
    this.rota = polyline;
  } // fim de TremUI


  /* ***************************************************************
  * - Metodo: movimento
  * - Funcao: Eh usado para atualizar a posicao de uma imagem na GUI
  * - Parametros: 'direcao' do tipo booleano
  * - Retorno: Nao retorna nada
  *************************************************************** */
  @Override
  protected void movimento(boolean direcao) {
    Platform.runLater(() -> 
    updateImageViewPosition(rota, trem, porcentagem));
  } // fim de movimento


  /* **********************************************************************************
  * - Metodo: getPolylineLenght
  * - Funcao: Calcula o comprimento total de uma polyline, que eh uma linha composta por
  * varios segmentos de linha de retas conectadas
  * - Parametros: 'polyline' do tipo Polyline
  * - Retorno: 'lenght' do tipo double
  ********************************************************************************** */
  public static double getPolylineLength(Polyline polyline) {
    /* -----------------------------------------------------------------------------------------------------------
     * - O metodo começa inicializando 'lengt' com 0. Em seguida, obtem a lista de points da 'polyline',
     * que eh uma lista de coordenadas x e y dos points que compoem a linha;
     * - O metodo percorre a lista de points usando um loop 'for', começando do 0 e incrementando de 2 em 2,
     * pois cada ponto consiste em um paz de coordenadas;
     * - Dentro do loop, eh obtido as coordenadas x e y do ponto atual e do ponto seguinte;
     * - Usando essas coordenadas, eh calulado a distancia entre os dois pontos usando a formula de Distancia
     * Euclidianada. O metodo adiciona a distancia ao comprimento total da 'polyline', azamazenando-a em 'length'
     * - Quando o loop termina, o metodo retorna o comprimento total da 'polyline', que eh a soma das distancias 
     * entre cada par de pontos consecutivos
     * ---------------------------------------------------------------------------------------------------------- */
    double length = 0;
    List<Double> points = polyline.getPoints();
    for (int i = 0; i < points.size() - 3; i += 2) {
        double x1 = points.get(i);
        double y1 = points.get(i + 1);
        double x2 = points.get(i + 2);
        double y2 = points.get(i + 3);
        length += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    } // fim do for
    return length;
  } // fim do getPoylineLenght


  /* **********************************************************************************************
  * - Metodo: updtateImageViewPosition
  * - Funcao: Autaliza a posicao de 'ImageView' ao longo de uma 'polyline', com base em uma 'slider'
  * - Parametros: 'polyline' (do tipo 'Polyline', representa a linha onde a 'ImageView' deve se mo-
  * ver), 'imagreView' (instancia da classe 'ImageView', representa a imagem que sera movida ao lon-
  * go da 'polyline'), 'sliderValue' (double, valor numerico entre 0 e 1, representa a posicao atual
  * da 'ImagerView' ao longo da 'polyline')
  * - Retorno: Nao possui retorno
  *********************************************************************************************** */
  private static void updateImageViewPosition(Polyline polyline, ImageView imageView, double sliderValue) {

    // -- Obtem uma lista do tipo double de points da polyline, que representa as coordenadas x e y -- //
    List<Double> points = polyline.getPoints();
    // -- Calcula o comprimento total da polyline, usando o metodo anterior, e o valor eh armazenado em 'totalLenght' -- //
    double totalLength = getPolylineLength(polyline);
    // -- O valor de 'sliderValue' eh multiplicado por 'totalLenght', para obeter a posicao atual da 'ImageView' ao longo da linha, eh armazenado em 'targetLenght' -- //
    double targetLength = sliderValue * totalLength;
    // -- 'currentLenght' eh inicializado com 0 -- //
    double currentLength = 0;

    /* -----------------------------------------------------------------------------------------------------------------
     * - O loop percorre todos os segmentos de linha da polyline (a cada iteracao, i eh incrementado em 2 pq cada
     * segmento contem 2 pontos na lista de points). Para cada segmentos as coordenadas dos dois pontos, x1, y1, x2,
     * y2, sao extraidos da lista de points;
     * - Em seguida, o comprimento do segmento eh calculado usando o Teorema de Pitagoras, e eh armazenado na vari-
     * avel 'segmentLenght'. O comprimento total ate o final do segmento eh calculado e armazenado na variavel
     * 'nextLenght';
     * - A posicao atual da 'ImageView' eh determinada pela comparacao de 'targentLenght' com os comprimentos do seg-
     * mentos. Se estiver dentro do intervalo entre 'currentLenght' e 'nextLenght', significa que a 'ImageView' esta
     * localizada neste segmento;
     * - A variavel 'segmentProgress' eh usada para determinar a posicao da 'ImagreView' dentro do segmento. Esta va-
     * riavel eh calculada dividindo-se a distancia restante ate a posicao alvo pela distancia total do segmento;
     * - As coordenadas da 'ImageView' sao entao calculadas usando a formula para Interpolacao Linear entre dois pontos.
     * A posicao da 'ImageView' eh centralizada e definida com os metodos 'setX()' e 'setY()';
     * - Se a posicao da 'ImageView' foi encontrado, o loop eh interrompido com o 'break'. Se o 'targetLenght' nao 
     * estiver dentro do segmento atual, o valor de 'nextLenght' eh atualizado para o proximo segmento;
     * --------------------------------------------------------------------------------------------------------------- */
    for (int i = 0; i < points.size() - 3; i += 2) {
        double x1 = points.get(i);
        double y1 = points.get(i + 1);
        double x2 = points.get(i + 2);
        double y2 = points.get(i + 3);

        double segmentLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double nextLength = currentLength + segmentLength;

        if (targetLength >= currentLength && targetLength <= nextLength) {
            double segmentProgress = (targetLength - currentLength) / segmentLength;
            double imageViewX = x1 + segmentProgress * (x2 - x1);
            double imageViewY = y1 + segmentProgress * (y2 - y1);
            imageView.setX(imageViewX - imageView.getBoundsInLocal().getWidth() / 2);
            imageView.setY(imageViewY - imageView.getBoundsInLocal().getHeight() / 2);          
            break;
        } // fim do if
        currentLength = nextLength;
    } // fim do for
  } // fim do updateImageViewPosition

} // fim da classe
