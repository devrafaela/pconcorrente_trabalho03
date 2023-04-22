import solucao.Solucao;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 22/04/2023
* Nome.............: Tunel
* Funcao...........: Representa um Tunel em um Sistema de Trem
*************************************************************** */
public class Tunel extends LinhaDoTrem {
  
  /* ***************************************************************
  * 'solucao' :  atributo privado do tipo Solucao
  *************************************************************** */
  private Solucao solucao;


  /* ***************************************************************
  * - Nome do Constutor: Tunel
  * - Funcao: Apenas inicializa os atributos da classe
  * - Parametros: 'nome' (Nome da Linha do Trem), 'comprimento' 
  * tamanho da Linha do Trem) e 'solucao' (que vai ser o tipo de so-
  * lucao aplicado durante a execucao da interface)
  * - Retorno: Nao retorna nenhum valor explicitamente
  * - Explicacao: Quando uma instancia da classe 'Tunel' eh criada 
  * usando esse construtor, o objeto eh inicializado com os valores
  * dos parametros passados e os atribuitos sao definidos
  *************************************************************** */
  public Tunel (String nome, int comprimento, Solucao solucao) {
    super(nome, comprimento);
    this.solucao = solucao;
  } // fim do Tunel


  /* ***************************************************************
  * - Metodo: getSolucao
  * - Funcao: Obtem o valor do atributo 'solucao' da classe 'Tunel'
  * - Parametros: nao recebe nenhum parametro
  * - Retorno: retorna 'Solucao'
  *************************************************************** */
  public Solucao getSolucao() {
    return solucao;
  } // fim do getSolucao

} // fim da classe