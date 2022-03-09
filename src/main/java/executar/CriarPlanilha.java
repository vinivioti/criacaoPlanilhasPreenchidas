package executar;

import java.io.IOException;

import interacoes.InteracaoWeb;
import paginas.PlanilhaVidasTeste;


public class CriarPlanilha implements InteracaoWeb {
	
	public static void main(String[] args) throws IOException {
		
		//new PlanilhaVidasManual().GeraPlanilhaVidas();
		new PlanilhaVidasTeste().GeraPlanilhaVidasTeste();

       }
   }
