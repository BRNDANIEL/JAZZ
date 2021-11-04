package JazzTech;

import java.io.IOException;


public class Irrigacao {

	public static void main(String[] args) throws IOException{

		Plantacao Plantacao = new Plantacao();
		Plantacao.inserirTamanho();
		Plantacao.inserirPontos();
		Robo robo = new Robo();
		robo.inserirPosicaoInicial(Plantacao);
		robo.inserirOrientacaoInicial(Plantacao);
		
		while(Plantacao.maisPontos() == true) {
			robo.inserirDestino(Plantacao.pegarProximoPonto());
			robo.movimentoDestino();
			robo.agua();
		}
		System.out.println(robo.pegarpercurso());
	}
}
