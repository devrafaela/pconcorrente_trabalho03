
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 21/04/2023
* Nome.............: LinhaDoTrem
* Funcao...........: Define os trilhos que o Trem ira percorrer
*************************************************************** */
public class LinhaDoTrem {

  /* *****************************************************************
  * - 'nomeLinha' :  String, representao nome da Linha do Trem
  * - 'comprimento' :  int, representa o comprimento da Linha do Trem
  * - 'proxima' :  Do tipo 'LinhaDoTrem', representa a PROXIMA Linha
  * - 'anterior' :  Do Tipo 'LinhaDoTrem', representa a Linha ANTERIOR
  ****************************************************************** */
  private String nomeLinha;     
  private int comprimento;      
  private LinhaDoTrem proxima;  
  private LinhaDoTrem anterior; 


  /* *********************************************************************
  * Nome do Constutor: LinhaDoTrem
  * Funcao: Cria um novo objeto LinhaDoTrem e inicializa suas variaveis
  * Parametros: 'nome' (nome da linha do trem) e 'comp' (comprimento da
  * linha do trem)
  * Retorno: void
  * Explicacao: O objetivo deste construtor eh inicializar duas variaveis 
  * de instancia da classe: 'nomeLinha' e 'comprimento'. O parametro 
  * 'nome'eh usado para atribuir um valor a variavel de instancia 'nomeLinha' 
  * e o paraetro "comp" eh usado para atribuir um valor a variavel de 
  * instancia "comprimento"
  ************************************************************************ */
  public LinhaDoTrem (String nome, int comp) {
    this.nomeLinha = nome;
    this.comprimento = comp;
  } // fim de LinhaDoTrem

  /* ***************************************************************
  * Metodo: conexao
  * Funcao: Tem como funcao  estabelecer a conexao entre as Linhas
  * do Trem e os Tuneis
  * Parametros: 'proxima' e 'anterior', ambas representam as Linhas
  * do Trem, que estao conectador a um Tunel x
  * Retorno:void
  *************************************************************** */
  public void conexao (LinhaDoTrem proxima, LinhaDoTrem anterior) {
    this.proxima = proxima;
    this.anterior = anterior;
  } // fim de conexao

  /* ***************************************************************
  * Metodo: proximaLinha
  * Funcao: Retorna a Proxima Linha de Trem ou a Linha Anterior, de-
  * pendendo do valor do parametro booleano 'direcao'
  * Parametros: 'direcao' indica se o metodo deve retornar a proxima
  * Linha do Trem ou a Linha Anterior, dependendo do valor. Se esse
  * valor for 'True' o metodo retorna a Proxima, se nao, a Anterior
  * Retorno: 'LinhaDoTrem' uma instancia da propria classe
  *************************************************************** */
  public LinhaDoTrem proximaLinha (boolean direcao) {
    if (direcao)
      return proxima;
    else
      return anterior;
  } // fim de proximaLinha

  public int getComprimento() {
    return comprimento;
  } // fim de getComprimento

  public String getNomeLinha() {
    return nomeLinha;
  } // fim de getNomeLinha

  public void setNomeLinha(String nomel) {
    this.nomeLinha = nomel;
  } // fim de setNomeLinha

} // fim da classe
