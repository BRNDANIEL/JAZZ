package JazzTech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Plantacao {
	 //Listagem de variaveis globais 
	List ptsIrrigacao = new ArrayList<>();
	int larguraX, alturaY;
	String MsgErro = "";
	Scanner scan = new Scanner(System.in); 
	
	
	public Plantacao() {
		
	}
	
	public Plantacao inserirTamanho() throws IOException, InputMismatchException{
		//insercao de danos pelo usuario
		
		System.out.println("\nInsira a larguraX da horta:");
		this.larguraX = scan.nextInt();
		System.out.println("\nInsira a alturaY da horta:");
		this.alturaY = scan.nextInt();
		this.verificaTamanho(larguraX, alturaY);
		return this;
	}
	
	
	public List inserirPontos() throws IOException{
		//Laço de repeticao. utilizando "P" para encerrar o loop
		String pt = "";
		while (!pt.equalsIgnoreCase("P")) {
			System.out.println("\nInsira as coordenadas do canteiro a serem irrigadas." 
					+ "\nDigite 'P' para encerrar."
					+ "\n(Ex: 4,5)");
			pt = scan.next();
			if (this.verificarEntrada(pt)) {
				ptsIrrigacao.add(pt);
			} else {
				pt = "";
				System.out.println(MsgErro);
			}
		}
		return ptsIrrigacao;
	}
	
	public String pegarProximoPonto() {
		String ponto = ptsIrrigacao.get(0).toString();
		ptsIrrigacao.remove(0);
		return ponto;
	}
	

	public boolean maisPontos() {
		if(ptsIrrigacao.size() > 1) {
			return true;
		}else 
			return false;		
	}
	
	private boolean verificarEntrada(String pt) {
        if (pt.equalsIgnoreCase("P")) {
            if (ptsIrrigacao.size() < 1) {
                MsgErro = "\nPor favor, informe ao menos uma coordenada.";
                return false;
            }
            else return true;
        }
        
        else {
            String[] XY = new String[2];
            XY = pt.split("\\,");
            if ((this.larguraX < Integer.parseInt(XY[0])) || this.alturaY < Integer.parseInt(XY[1])) {
                MsgErro = "\nCoordenada ignorada por estar fora dos limites da horta";
                return false;
            }
        }
        return true;
    }
		
	private void verificaTamanho(int larguraX, int alturaY) throws IOException {
        if (larguraX <=0 || alturaY <=0) {
            System.out.println("\nInsira um valor maior que zero.");
            this.inserirTamanho();
        }
    }


	
	

}
