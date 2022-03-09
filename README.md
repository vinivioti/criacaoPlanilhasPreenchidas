# **Documentação de utilização do Framework de automação de testes - BRQ | Porto Seguro **

Bibliotecas utilizadas:

[![Appium](https://img.shields.io/badge/appium-v.%207.0.0-blueviolet.svg)](http://appium.io/)  [![junit](https://img.shields.io/badge/junit-4.12-red.svg)](https://junit.org/junit4/) [![gherkin](https://img.shields.io/badge/gherkin-2.12.2-brightgreen.svg)](https://cucumber.io/docs/gherkin/) [![restassured](https://img.shields.io/badge/restassured-2.9.0-brightgreen.svg)](https://github.com/rest-assured/rest-assured/wiki/ReleaseNotes29) [![selenium](https://img.shields.io/badge/selenium-3.141.59-blue.svg)](https://www.seleniumhq.org/)

Para manuseio da planilha, foi utilizada a biblioteca: [![poiji](https://img.shields.io/badge/poiji-1.11-red.svg)](https://github.com/ozlerhakan/poiji)

# Como escrever seus testes utilizando o framework:


1. Primeiro é necessário ter o [git](https://git-scm.com/) e o [NodeJs](https://nodejs.org/en/download/) instalado e ter realizado o clone do repositório da branch [master](https://gitportoprd.portoseguro.brasil/saude/saude-java/Automacao_Saude.git).

  Para clonar o repositório:
* git clone https://github.com/vinivioti/testeSubidaCodigo.git

2.  Após clonar o repositório, entre na pasta que você acabou de clonar, verifique se você está na mesma pasta onde contém o arquivo "pom.xml" e execute o seguinte comando:
  * mvn install -Dmaven.test.skip=true  (Este comando será o responsável por instalar as dependências do framework).

3. Após os passos 1 e 2, é hora de criarmos as classes. 

Primeiro, entenda nossa estrutura:

# Estrutura do Framework:

```
-- brq.qaa
    |-- main/java
	|	|
	|	|-- Contém os métodos referentes a selenium, appium e restAssured. Todos os métodos são encapsulados e com tryCath para cada exceção que a ação pode apresentar.
	|	|
	|-- test/java
	|	|
	|	|-- Deve conter os scripts dos testes automatizados.
	|	|
	|-- test/resources
	|	|
	|	|-- Contém informações para configuração de arquivos, logs e drivers.
	|	|
	|-- Configuração
	|	|
	|	|-- Arquivo de configuração do plugin do ExtendsReport
	|	|
	|-- Evidencias
	|	|
	|	|-- Contém duas pastas (Prints e PrintsDeErros) na qual os teste executado são arquivados e evidenciados em um relatório.
	|	|
	|-- massaDeDadosExcel
	|	|
	|	|-- Armazena os arquivos em excel no qual devem ser usado como massa de dados para os testes
	|	|
	|-- properties
	|	|
	|	|-- Arquivo de propriedades onde se defini variaveis globais nos testes como url, usuários, conexão de banco de dados, caminho de pastas
	|	|
	|-- dockerFile
	|	|
	|	|-- Contâiner docker do ambiente da porto seguro
 
```


```
-- src
    |-- main
          |-- java
                |
                |-- package acessibility (contém classes responsáveis pelos testes de acessibilidade**)
                |   
                |-- package annotations (contém classes responsáveis pela manipulação da execução do cucumber, assim como pela implementação de duas anotações extras)
                |                  
                |-- package drivers
                |              |
                |              |-- package mobile (contém classe responsável pelas configurações do driver mobile Android e Ios)
                |              |             |
                |              |             |-- package appium (contém a classe responsável pela manipulação e inicialização dinâmica do appium)
                |              |
                |              |-- package web (contém classe responsável pelas configurações do driver web Linux, Windows e IOS**)
                |
                |-- package enums (contém todos os enumeradores utilizados pelas classes do framework)
                |
                |-- package geradoresdemassa (contém as classes responsáveis pelas gerações de massas de dados)
                |                  
                |-- package interacoes (contém as interfaces que serão chamadas pelos steps para interação com sistemas Web, Mobile, Desktop e API. As interfaces deste pacote não implementam nada, pois quem contém as implementações são as interfaces dentro do pacote "interfaces") do pacote )
                |
                |-- package interfaces 
                |              |    
                |              | -- package api (contém as implementações dos métodos das API's)
                |              |    
                |              | -- package log (contém as implementações dos LOGS referentes aos testes)
                |              | 
                |              | -- package mobile 
                |              |      |
                |              |      |-- package android (contém as implementações dos testes android)
                |              |      |
                |              |      |-- package ios (contém as implementações dos testes ios)
                |              |
                |              | -- package web (contém as implementações dos testes web)
                |
                |-- package models
                |              |
                |              |-- package geradoresdemassa (contém a classe modelo das gerações de massa de dados)
                |              |
                |              |-- package planilhadetestes (contém a classe modelo da planilha de testes)
                | 
                |-- package planilha (contém as classes responsáveis pela obtenção dos dados da planilha de testes)
                |
                |
                |-- package util (contém métodos utilitários para os testes, tais como "obter data atual", "converter pdf em texto" e etc...)

```

```
-- src
    |-- test
          |-- java
          |     |
          |     |-- package Data
          |     |     |
          |     |     |-- conexaoBdOracle.java (Classes que contém a configuração de conexão com uma base de dados)
          |     |		  
		  |     |-- package pageElements
          |     |     |
          |     |     |-- pageElements.java (Classe que deve conter os elementos da página **aconselhável utilizar uma classe para cada interface do sistema)
          |     |		  
          |     |-- package executar
          |     |     |
          |     |     |-- CucumberExecuteTest.java (classe que inicializará os testes)
          |     |
          |     |-- package steps ou funcionalidades
          |     |     |
          |     |     |-- steps.java (Classes que implementarão os passos das features. **aconselhável manter uma classe por funcionalidade**)  
          |     |                  
          |     |-- package pages
          |     |     |
          |     |     |-- page.java (Classes que conterão a ação da página, como clicar, selecionarCombo.  **aconselhável utilizar uma classe para cada interface do sistema**)
          |     |
		  |     |-- package massaDeDados
		  |     |	  |	
		  |     |	  |-- leitorDeArquivo (Classe responsável por ler arquivo de excel para serem utilizado como massa de dados nos testes **aconselhável criar um POJO para ter controle dos objetos a ser chamados nos testes**)
		  |     |
          |     |-- package utils
          |           |
          |           |-- utils.java(classe que conterá os métodos que são necessários ao projeto e que não estejam no framework. Ou seja, qualquer método extra referente ao projeto, não deverá ficar nas classes do framework, mas sim nessa classe específica)
          |
          |-- resources
                |
                |-- pasta armazenador (deverá ficar a planilha de testes e as apks referente aos testes mobile. Não modificar o nome da planilha de testes. Caso seja modificao, a classe "Planilha" deverá ser alterada para identificar o novo nome)
                |
                |-- pasta conflogs (arquivos de configuração dos logs)
                |
                |-- pasta drivers (deverá conter os drivers referentes ao selenium. **Importante não modificar os nomes. Caso sejam modificados os nomes, a classe "DriverWeb" deverá ser alterada para que sejam identificados os novos nomes)
				|
				|-- pasta features (deve conter os arquivos com os cenários em gherkin)
```

* Crie o pacote executar:

No pacote main, deverá ter a seguinte classe: 

```java
@RunWith(ExtendedCucumberRunner.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {"json:src/test/resources/json/Resultado.json" }, 
glue = { "" }, monochrome = true, dryRun = false, strict = false)
public class CucumberExecuteTest {}
```

Será por meio dela que você iniciará as execuções dos seus testes.


* Crie o pacote pageElements (observação: este pacote só é necessário para testes Mobile e Web, portanto não há necessidade de sua criação em testes de serviços):

No pacote pageElements estarão localizadas as classes que contém os elementos das nossas interfaces. Elas deverão ter a estrutura parecida com o seguinte modelo:

```java
import org.openqa.selenium.By;

public class AgendaDaSalaArena {

	public By username = By.id("com.brq.pontobrq:id/txtDataAgenda");
	public By senha = By.id("android:id/next");
	public By botaoLogin = By.xpath("com.brq.pontobrq:/logar/buttonLogar");
}

```

* Crie o pacotes steps:

No pacote steps estarão localizadas as implementações dos nossos casos de testes:

Veja exemplos para cada tipo de teste:

#### Web:

Os testes web seguem o mesmo padrão do mobile, o que mudam são os nomes das interfaces e métodos chamados.


 ```java
 package mobile.steps;

import java.net.MalformedURLException;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import drivers.mobile.DriverMobile;
import drivers.web.DriverWeb;
import enums.Browser;
import interacoes.InteracaoAndroid;
import interacoes.InteracaoWeb;

public class Step implements InteracaoWeb{

	private LoginModel loginModel = new LoginModel();
	
	@Quando("^implementacaoDoSeuPassoQuando$")
	public void implementacaoDoSeuPassoQuando() {
		loginModel.efetuarLogin();
	}
}

 ```

#Crie o pacotes de pages:


 ```java
 import Elementos.SistemaApollo.Login.ElementosLogin;
 import interacoes.InteracaoWeb;
 import utils.Iteracao;

 public class LoginPage extends ElementosLogin implements InteracaoWeb{
	
 	public void efetuarLogin(String login, String senha) {
 		preencherUsuario(login);
 		preencherSenha(senha);
 		clicarEntrar();
 	}
	
 	protected void preencherUsuario(String login) {
 		esperarElementoSerVisivel(getTxtUsuario(), 60, "Aguarda o campo usuário ser visivel");
 		escrever(getTxtUsuario(), login, "Preenche o campo Usuario");
 	}
	
 	protected void preencherSenha(String senha) {
 		esperarElementoSerVisivel(getTxtSenha(), 60, "Aguarda o campo senha ser visivel");
 		escreverNoCampo(getTxtSenha(), senha, "Preenche o campo Senha");
 	}
	
 	protected void clicarEntrar() {
 		esperarSerClicavel(getBtnAcessar(), 60, "Aguarda o botão acessar ser clicavel");
 		superClick(getBtnAcessar(), "Clicar no Botão Acessar");
 	}
	
 }

 ```
### Usando o framework para testes de API

```java
package api.steps;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import interacoes.InteracaoRequisicao;

public class API implements InteracaoRequisicao{
	Response resposta;
	String url = "https://jsonplaceholder.typicode.com/posts/1";
	
	@Dado("^que eu faço uma requisição$")
	public void que_eu_faço_uma_requisição() {
		usarHttps();	    
		resposta = get(url,"", ContentType.JSON);
		System.out.println(resposta.asString());
	}
	    
	@Então("^eu obtenho um código de sucesso$")
	public void eu_obtenho_um_código_de_sucesso()  {
	    validarStatusCode(resposta, 200);
		 
	}	

	@Então("^eu obtenho uma resposta em formato válido$")
	public void eu_obtenho_uma_resposta_em_formato_válido() {
	 validarEstruturaJson(resposta.asString());
	}
	
	@Então("^valido se a chave name retorna o valor correto$")
	public void valido_se_a_chave_name_retorna_o_valor_correto() {
		validarValorDeElementoJsonPorChave("id", 1, resposta.asString());
		logGet("Validando content type", url, resposta.contentType());
	}
}


```

Para realizar os testes de API, é necessário implementar a interface InteracaoRequisicao.
No exemplo foi utilizado o "get", mas poderia ser chamado o "post", "put", "delete" e "patch".

Para usar os logs, basta escolher o determinado para qual o tipo de requisição está realizando, há os seguintes métodos de log:

* logGet(String step, Object url, Object response)
* logPut(String step, Object url, Object response)
* logDelete(String step, Object url, Object response)
* logPatch(String step, Object url, Object response)
* logPost(String step, Object url, Object response)



#####   Autenticação no cabeçalho:

No nome dos métodos, você consegue identificar, pelo nome do método, qual é responsável por enviar autenticação via cabeçalho, pois eles contém o nome "AutenticacaoNoCabecalho":

**POST**
``` postComAutenticacaoNoCabecalho("www.google.com", "Authorization", "Basic ixosiaPOoekmqJhsn", "", ContentType.XML);```

**GET**
``` getComAutenticacaoNoCabecalho("www.google.com", "Authorization", "Basic ixosiaPOoekmqJhsn", "", ContentType.XML);```

**PUT**
``` putComAutenticacaoNoCabecalho("www.google.com", "Authorization", "Basic ixosiaPOoekmqJhsn", "", ContentType.XML);```

**PATCH**
``` patchComAutenticacaoNoCabecalho("www.google.com", "Authorization", "Basic ixosiaPOoekmqJhsn", "", ContentType.XML);```

**DELETE**
``` deleteComAutenticacaoNoCabecalho("www.google.com", "Authorization", "Basic ixosiaPOoekmqJhsn", "", ContentType.XML);```

**DICA:**
Caso use "HTTPS", e receba alguma mensagem de erro, como por exemplo:  PKIX path building failed, considero utilizar o método `usarHttps();` antes da requisição.



#####  Outros tipos de autenticação:

Você pode fazer uso dos seguintes métodos:

`definirAutenticacaoBasica(username, password);`

`definirAutenticacaoOauth(oauthConsumerKey, oauthConsumerSecret, oauthAccessToken, oauthSecretToken);`

`definirAutenticacaoOauth2(oauth2AccessToken, oauth2Signature);`

Após fazer uso do método que lhe atenda, qualquer requisição que você fizer, usará o tipo de autenticação definido. 
Por exemplo, se você definir à autenticação: `definirAutenticacaoBasica(username, password);` e depois fizer um `get(url, xmlOuJson, contentType);`,
essa requisição fará um GET utilizando à autenticação básica.

Todas as autenticações são estáticas, ou seja, uma vez que for definido qualquer tipo de autenticação, todas as requisições posteriores serão feitas com autenticação.
Então é importante que você se atente para o caso de não querer utilizar mais autenticação em requisições posteriores. Para isso você pode fazer uso do método:

`resetarConfiguracoesExtras();`

Ao utilizar esse método, qualquer configuração estática que tiver sido realizada, será apagada. Ou seja, se você definiu autenticação, usou à autenticação e logo em seguida, retirou a forma de autenticação.
Sendo assim, todas as requisições posteriores serão realizadas sem autenticação.


##### Validação de respostas das requisições:

Todas as requisições te retornam uma resposta. Cada resposta retornada é do tipo `Response`.

Ex.: 
**GET**
`Response resposta;`
`resposta = get("www.google.com/", "", ContentType.JSON);`

**POST**
`Response resposta;`
`resposta = post("www.google.com/", "", ContentType.JSON);`

**PUT**
`Response resposta;`
`resposta = put("www.google.com/", "", ContentType.JSON);`

**PATCH**
`Response resposta;`
`resposta = patch("www.google.com/", "", ContentType.JSON);`

**DELETE**
`Response resposta;`
`resposta = delete("www.google.com/", "", ContentType.JSON);`

Após receber essas respostas, você pode utilizar os métodos de validação. Por exemplo, se você quiser validar os status de resposta, poderá fazer o seguinte:

`validarStatusCode(resposta, 200);`

repare que o método recebe a resposta da requisição e verificar se o status code foi 200. Caso contrário, o teste falhará.


### BDD - Boas Práticas:


1. Não repetir o mesmo passo dentro da mesma funcionalidade (Recomendado).
	Quais os problemas disso? O problema se dá principalmente nos casos de validações, ou seja, no step 'Entao'.
	Pensando no teste de API, digamos que será validado o status de erro 401 para um cenário e o status 403 para outro cenário.
	Ex.:

 ```
 Cenario: cenario 1 
      Dado que nao tenho credenciais validas
      Quando eu realizar uma requisicao para o website da alelo refeicao 
      Entao eu devo obter o status de erro

 Cenario: cenario 2 
     Dado que eu tenho credenciais validas 
     Quando eu realizar uma requisicao para o website da alelo refeicao sem informar o token 
     Entao eu devo obter o status de erro 
 ```
Qual o problema? Pois bem, repare que os passos são iguais mas as validações são diferentes. Isso deixa o código de automação pouco reutilizável, 	porque obrigará o automatizador a escrever um script de validação específico para tratar qual validação de erro executar no momento da execução do teste.

O ideal é diferenciá-los, pois isso mantém o código limpo e com um nível maior de reutilização. 

*Recomendamos que os passos sejam sempre diferentes, pois evitarão problemas futuros.*

Exemplo correto:
 ```
 Cenario: cenario 1 
      Dado que nao tenho credenciais validas
      Quando eu realizar uma requisicao para o website da alelo refeicao 
      Entao eu devo obter o status de erro 401

 Cenario: cenario 2 
     Dado que eu tenho credenciais validas 
     Quando eu realizar uma requisicao para o website da alelo refeicao sem informar o token 
     Entao eu devo obter o status de erro 403
 ```

2. Contexto só deve ser utilizado para 'Dado' e/ou 'Entao'.

3. Tomar cuidado ao utilizar palavras reservadas e caracteres reservados.
	
*Exemplo de palavras reservadas: Dado, Quando, E, Entao.*

Exemplo de erro: 
`Quando eu informar nome E informar senha. ` (Repare que o 'E' está em maiúsculo, constituindo-se como palavra reservada).

*Nesses casos de uso, o ideal é utilizar em letra minúscula, diferindo-se da palavra reservada).*

Exemplo certo: 
 `Quando eu informar nome e informar senha. ` (Perceba agora que o 'E' está em minúsculo).
	

*Exemplo de caracteres reservados:*
Sobre caracteres reservados, o indicado é primeiro verificar a versão do Cucumber e depois, pois cada versão possui tipos diferentes de carácteres especiais.

[link para verificar as palavras reservadas da última versão do cucumber](https://docs.cucumber.io/gherkin/reference/#keywords )


# Dicas codificação:
Saiba que aplicar boas práticas durante a codificação resulta em um código bem estruturado, limpo, conciso e de fácil compreensão, 
e esta melhoria da qualidade do código viabiliza o aumento da produtividade do time em todas as fases de qualquer processo de software.

Manutenção do código:

1° Métodos genéricos:

* Um método deve ter somente uma responsabilidade e se adequar a todas as chamadas.

2° Responsabilidade de classes:

* Métodos de interação não devem misturar-se com métodos de validação. Por isso foi criada uma interface chamada IValidacoes que é específica para isso. Cada classe deve ter somente métodos para os quais ela foi programada para ter. Por exemplo, o screenshot não deve ser misturado com a classe que contém métodos de interação com a página. São coisas distintas. 
* Cada classe deve comportar métodos do tipo dela. Se a classe é de Driver, devem permanecer somente métodos referente ao driver. Se a classe é de interação com a página, devem ter somente métodos de interação.

3° Pasta "target":
 A pasta target é dinâmica de acordo com o maven. Se você der um comando do maven, a primeira coisa que ele vai fazer é limpar toda a pasta "target".
    Se o desejo for apagar todos os "screenshots", recomendo a seguinte solução para o caso dos prints:
	* Portanto, lembre-se que os arquivos da pasta target são temporários.

4° 

* Manter uma classe por funcionalidade  
* Aconselhável utilizar uma classe pageObjects para cada interface do sistema
* Utilizar uma classe separada para conter os métodos que são necessários ao projeto e que não estejam no framework. Ou seja, qualquer método extra referente ao projeto, não deverá ficar nas classes do framework, mas sim nessa classe específica

5° Evitar a utilização de nomenclatura inadequada no repositório ou nas branches. Elas não causam grandes impactos negativos no cronograma do projeto, mas dificultam a identificação do código a ser evoluído. 

6° Outra atitude a ser evitada é a adição de arquivos desnecessários no sistema de controle de versão. Estes arquivos podem deixar o repositório grande e confuso e tornar o processo de atualização do código demorado.

7° A falta de critérios pré-estabelecidos de formatação do código, o desleixo, a falta de atenção ou a ausência do sentido de coletividade do código e do empenho por parte de membros do time são alguns dos fatores que prejudicam o trabalho.

8° A indentação é um dos aspectos visuais mais importantes para facilitar ou dificultar a leitura e o entendimento do código. Primeiramente é importante decidir se serão empregados tabulações ou espaços, e depois, definir o tamanho da indentação. Normalmente, utiliza-se quatro espaços.

9° Outra boa prática é criar nomes consistentes para as classes, variáveis e constantes. Apenas pelo nome destes elementos deve ser possível entender a razão de existirem ou o que fazem. Não há um limite sugerido para o tamanho dos nomes.

10° A utilização de poucos comentários também é primordial para a manutenção de um código limpo e conciso. Pode parecer contraditório, mas comentários demais poluem o código. Além disso, se for necessário adicionar comentários para explicar o objetivo de uma classe, método, variável ou qualquer outro módulo, é porque o nome deste elemento ou o código aninhado nele pode ser melhorado.

#### Qualquer dúvida sobre a utilização do framework, entre em contato:

Leonardo Ananias do Nascimento 	- leonardo.ananias@portoseguro.com.br

Mateus Quiterio 				- mateus.quiterio@portoseguro.com.br

