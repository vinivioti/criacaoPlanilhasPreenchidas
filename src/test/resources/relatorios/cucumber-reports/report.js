$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("34104_orcamento.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#enconding: utf-8"
    },
    {
      "line": 2,
      "value": "#Auto generated Octane revision tag"
    }
  ],
  "line": 5,
  "name": "COL - Novo Cotador Online",
  "description": "Como um analista ,\nQuero que as cotações sejam efetuadas no novo cotador\nPara que tenhamos os processos efetuados no COL e gravados no Salesforce",
  "id": "col---novo-cotador-online",
  "keyword": "Feature",
  "tags": [
    {
      "line": 3,
      "name": "@TID34104REV0.6.0"
    },
    {
      "line": 4,
      "name": "@COL"
    },
    {
      "line": 4,
      "name": "@Orcamento"
    }
  ]
});
formatter.scenarioOutline({
  "line": 33,
  "name": "Elaboração de Cotação – Validação de Dados com Sucesso – PME Saúde - Front",
  "description": "",
  "id": "col---novo-cotador-online;elaboração-de-cotação-–-validação-de-dados-com-sucesso-–-pme-saúde---front",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 32,
      "name": "@CotacaoVariosPlanosSaoPauloRJ"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Que o usuário acesse o front COL com credenciais válidas",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "apresentar a tela inicial",
  "keyword": "And "
});
formatter.step({
  "line": 36,
  "name": "selecionar os menus \"Cálculos\" - \"Saude\" - \"Novo Cotador Saude TST/HML\"",
  "keyword": "When "
});
formatter.step({
  "line": 37,
  "name": "iniciar Criar uma nova cotação",
  "keyword": "And "
});
formatter.step({
  "line": 38,
  "name": "preenche as Informações do estipulante",
  "keyword": "And "
});
formatter.step({
  "line": 39,
  "name": "optar por preencher a quantidade de vidas \"\u003cqtdVidas\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 40,
  "name": "informo os dados para distribuir vidas \"\u003cdistribuirVidas\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 41,
  "name": "seleciono a comissão desejada \"\u003ccomissao\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 42,
  "name": "seleciono o checkbox Li e estou de acordo com as Regras de Agenciamento.",
  "keyword": "And "
});
formatter.step({
  "line": 43,
  "name": "clicar no botão Cotar",
  "keyword": "And "
});
formatter.step({
  "line": 44,
  "name": "o sistema deve apresentar a tela de confirmação com o numero da cotação",
  "keyword": "Then "
});
formatter.examples({
  "line": 46,
  "name": "",
  "description": "",
  "id": "col---novo-cotador-online;elaboração-de-cotação-–-validação-de-dados-com-sucesso-–-pme-saúde---front;",
  "rows": [
    {
      "comments": [
        {
          "line": 47,
          "value": "#c| minimo 3 vd | Plano - modalidade - porcentagem  - tipoacomodacao - modalidadeOuro - distrib vidas               | K175 K079 K154 K130 K001 |"
        }
      ],
      "cells": [
        "qtdVidas",
        "distribuirVidas",
        "comissao"
      ],
      "line": 48,
      "id": "col---novo-cotador-online;elaboração-de-cotação-–-validação-de-dados-com-sucesso-–-pme-saúde---front;;1"
    },
    {
      "comments": [
        {
          "line": 49,
          "value": "#   |   5       | Saúde Mais - Pulsar - NT - NT - NT - 00 a 18:5,                                                        |   K130    |"
        },
        {
          "line": 50,
          "value": "#   |   3       | Saúde Mais - Vivaz - NT - NT - NT - 00 a 18:3,                                                         |   K154    |"
        },
        {
          "line": 51,
          "value": "#   |   3       | Saúde Mais - Sublime - NT - NT - NT - 00 a 18:3,                                                       |   K130    |"
        }
      ],
      "cells": [
        "3",
        "Cristal - NT - Sem Copar  - NT  - NT - 00 a 18:3,",
        "H175"
      ],
      "line": 52,
      "id": "col---novo-cotador-online;elaboração-de-cotação-–-validação-de-dados-com-sucesso-–-pme-saúde---front;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "comments": [
    {
      "line": 49,
      "value": "#   |   5       | Saúde Mais - Pulsar - NT - NT - NT - 00 a 18:5,                                                        |   K130    |"
    },
    {
      "line": 50,
      "value": "#   |   3       | Saúde Mais - Vivaz - NT - NT - NT - 00 a 18:3,                                                         |   K154    |"
    },
    {
      "line": 51,
      "value": "#   |   3       | Saúde Mais - Sublime - NT - NT - NT - 00 a 18:3,                                                       |   K130    |"
    }
  ],
  "line": 52,
  "name": "Elaboração de Cotação – Validação de Dados com Sucesso – PME Saúde - Front",
  "description": "",
  "id": "col---novo-cotador-online;elaboração-de-cotação-–-validação-de-dados-com-sucesso-–-pme-saúde---front;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@TID34104REV0.6.0"
    },
    {
      "line": 32,
      "name": "@CotacaoVariosPlanosSaoPauloRJ"
    },
    {
      "line": 4,
      "name": "@COL"
    },
    {
      "line": 4,
      "name": "@Orcamento"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Que o usuário acesse o front COL com credenciais válidas",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "apresentar a tela inicial",
  "keyword": "And "
});
formatter.step({
  "line": 36,
  "name": "selecionar os menus \"Cálculos\" - \"Saude\" - \"Novo Cotador Saude TST/HML\"",
  "keyword": "When "
});
formatter.step({
  "line": 37,
  "name": "iniciar Criar uma nova cotação",
  "keyword": "And "
});
formatter.step({
  "line": 38,
  "name": "preenche as Informações do estipulante",
  "keyword": "And "
});
formatter.step({
  "line": 39,
  "name": "optar por preencher a quantidade de vidas \"3\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 40,
  "name": "informo os dados para distribuir vidas \"Cristal - NT - Sem Copar  - NT  - NT - 00 a 18:3,\"",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 41,
  "name": "seleciono a comissão desejada \"H175\"",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 42,
  "name": "seleciono o checkbox Li e estou de acordo com as Regras de Agenciamento.",
  "keyword": "And "
});
formatter.step({
  "line": 43,
  "name": "clicar no botão Cotar",
  "keyword": "And "
});
formatter.step({
  "line": 44,
  "name": "o sistema deve apresentar a tela de confirmação com o numero da cotação",
  "keyword": "Then "
});
formatter.match({
  "location": "TesteCol.acessarCOL()"
});
formatter.result({
  "duration": 4617426823,
  "status": "passed"
});
formatter.match({
  "location": "TesteCol.telaInicial()"
});
formatter.result({
  "duration": 12156,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Cálculos",
      "offset": 21
    },
    {
      "val": "Saude",
      "offset": 34
    },
    {
      "val": "Novo Cotador Saude TST/HML",
      "offset": 44
    }
  ],
  "location": "TesteCol.selecionar_os_menus(String,String,String)"
});
formatter.result({
  "duration": 1481192,
  "status": "passed"
});
formatter.match({
  "location": "TesteCol.iniciar_criar_uma_nova_cotação()"
});
formatter.result({
  "duration": 101347804,
  "status": "passed"
});
formatter.match({
  "location": "TesteCol.preenche_as_informações_do_estipulante()"
});
formatter.result({
  "duration": 47806538335,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 43
    }
  ],
  "location": "TesteCol.optar_por_preencher_a_quantidade_de_vidas(String)"
});
formatter.result({
  "duration": 7487809032,
  "error_message": "java.lang.AssertionError: 2022-02-16T15:12:18.901 Tempo excedido para encontrar o elemento: \u0027By.xpath: //button[contains(@class,\u0027new-quotation\u0027)]\u0027 em tela.\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat interfaces.web.IEspera.esperarSerClicavel(IEspera.java:39)\n\tat paginas.CotacaoPage.btnDistVidas(CotacaoPage.java:96)\n\tat steps.TesteCol.optar_por_preencher_a_quantidade_de_vidas(TesteCol.java:50)\n\tat ✽.And optar por preencher a quantidade de vidas \"3\"(34104_orcamento.feature:39)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "Cristal - NT - Sem Copar  - NT  - NT - 00 a 18:3,",
      "offset": 40
    }
  ],
  "location": "TesteCol.informoDistribuirVidas(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "H175",
      "offset": 31
    }
  ],
  "location": "TesteCol.seleciono_a_comissão_desejada(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "TesteCol.seleciono_o_checkbox_li_e_estou_de_acordo_com_as_regras_de_agenciamento()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "TesteCol.clicar_no_botão_cotar()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "TesteCol.apresentaTelaConfirmacao()"
});
formatter.result({
  "status": "skipped"
});
});