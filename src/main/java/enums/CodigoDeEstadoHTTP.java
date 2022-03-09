package enums;

public enum CodigoDeEstadoHTTP {
	
	Status100("Continuar"), Status101("Mudando protocolos"), Status102("Processamento (WebDAV) (RFC 2518)"),
	Status122(" Pedido-URI muito longo"), Status200(" OK"), Status201(" Criado"), Status202(" Aceito"),
	Status203(" nao-autorizado (desde HTTP/1.1)"), Status204(" Nenhum conteudo"), Status205(" Reset"),
	Status206(" Conteudo parcial"), Status207("Status Multi (WebDAV) (RFC 4918)"), Status300("Multipla escolha"),
	Status301(" Movido"), Status302("Encontrado"), Status303("Consulte Outros"), Status304("Nao modificado"),
	Status305("Use Proxy (desde HTTP/1.1)"), Status306("Proxy Switch"),
	Status307("Redirecionamento temporario (desde HTTP/1.1)"), Status308("Redirecionamento permanente (RFC 7538[2])"),
	Status400("Requisicao invalida"), Status401("Nao autorizado"), Status402("Pagamento necessario"),
	Status403("Proibido"), Status404("Nao encontrado"), Status405("Metodo nao permitido"), Status406("Nao Aceitavel"),
	Status407("Autenticacao de proxy necessaria"), Status408("Tempo de requisicao esgotou (Timeout)"),
	Status409("Conflito"), Status410("Gone"), Status411("comprimento necessario"), Status412("Pre-condicao falhou"),
	Status413("Entidade de solicitacao muito grande"), Status414("Pedido-URI Too Long"),
	Status415("Tipo de midia nao suportado"), Status416("Solicitada de Faixa Nao Satisfatoria"),
	Status417("Falha na expectativa"), Status418("Eu sou um bule de cha"),
	Status422("Entidade improcessavel (WebDAV) (RFC 4918)"), Status423("Fechado (WebDAV) (RFC 491"),
	Status424("Falha de Dependencia (WebDAV) (RFC 4918)"), Status425("colecao nao ordenada (RFC 3648)"),
	Status426("Upgrade Obrigatorio (RFC 2817)"), Status450("bloqueados pelo Controle de Pais do Windows"),
	Status499("cliente fechou Pedido (utilizado em ERPs/VPSA)"),
	Status500("Erro interno do servidor (Internal Server Error)"), Status501("Nao implementado (Not implemented)"),
	Status502("Bad Gateway"), Status503("Servico indisponivel (Service Unavailable)"), Status504("Gateway Time-Out"),
	Status505("HTTP Version not supported");

	private final String valor;

	private CodigoDeEstadoHTTP(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}