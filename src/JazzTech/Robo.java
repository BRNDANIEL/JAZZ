package JazzTech;

import java.io.IOException;
import java.util.Scanner;



public class Robo {
	int posicaoX, posicaoY, destinoX, destinoY;
    char direcao;
    StringBuilder percurso = new StringBuilder();
    Scanner scan = new Scanner(System.in);
    
    public Robo() {
    }

    public Robo inserirPosicaoInicial(Plantacao Plantacao) throws IOException {
        System.out.println("\nInforme a posição inicial no formato x,y");
        if (verificarPosicaoInicial(scan.next(), Plantacao.larguraX, Plantacao.alturaY)) ;
        else {
            System.out.println("\nFormato inválido ou fora dos limites da Plantacao.");
            inserirOrientacaoInicial(Plantacao);
        }
        return this;
    }
    
    public Robo inserirOrientacaoInicial(Plantacao Plantacao) throws IOException {
        System.out.println("\nInforme a orientação inicial:"
        		+ "\nN = Norte"
        		+ "\nS = Sul"
        		+ "\nL = Leste"
        		+ "\nO = Oeste");
        String str = scan.next();
        if (verificarOrientacaoInicial(Character.toUpperCase(str.charAt(0))))
            scan.close();
        else inserirOrientacaoInicial(Plantacao);
        return this;
    }
    
    public void inserirDestino(String destino) {
        String[] str = new String[2];
        str = destino.split("\\,");
        this.destinoX = Integer.parseInt(str[0]);
        destinoX = Integer.parseInt(str[0]);
        destinoY = Integer.parseInt(str[1]);
    }
    
    public String movimentoDestino() {
        char turnTo;
        if (posicaoX != destinoX) {
            if (posicaoX > destinoX) turnTo = 'O';
            else turnTo = 'L';
            percurso.append(ajustarOrientacao(direcao, turnTo));
            percurso.append(movimento(posicaoX, destinoX));
            posicaoX = destinoX;
        }
        if (posicaoY != destinoY) {
            if (posicaoY > destinoY) turnTo = 'S';
            else turnTo = 'N';
            percurso.append(ajustarOrientacao(direcao, turnTo));
            percurso.append(movimento(posicaoY, destinoY));
            posicaoY = destinoY;
        }
        return percurso.toString();
    }
    
    private String ajustarOrientacao(char direc, char irPara) {
        String turns = "";
        if (direc != irPara) {
            int dir = (direcaoPara(direc) - direcaoPara(irPara));
            dir = ((dir + 3) % 4);
            if (dir == 0) turns = "E ";
            else if (dir == 1) turns = "E E ";
            else turns = "D ";
            this.direcao = irPara;
        }
        return turns;
    }
    
    private boolean verificarOrientacaoInicial(char orientacao) {
        String validarOrientacao = "ONLS";
        if (orientacao == 'O' || orientacao == 'N' || orientacao == 'L' || orientacao == 'S') {
            this.direcao = orientacao;
            return true;
        } else return false;
    }
    
    
    private boolean verificarPosicaoInicial(String posicaoInicial, int larguraX, int alturaY) {
        String[] posicao = new String[2];
        posicao = posicaoInicial.split("\\,");
        if ((larguraX >= Integer.parseInt(posicao[0])) && alturaY >= Integer.parseInt(posicao[1])) {
            posicaoX = Integer.parseInt(posicao[0]);
            posicaoY = Integer.parseInt(posicao[1]);
            return true;
        } else
            return false;
    }
    
    private int direcaoPara(char d) {
        int instrucoes = 0;
        if (d == 'N') instrucoes = 0;
        if (d == 'L') instrucoes = 1;
        if (d == 'S') instrucoes = 2;
        if (d == 'O') instrucoes = 3;

        return instrucoes;
    }
    
    private String movimento(int pos, int dest) {
        StringBuilder movimentos = new StringBuilder("");
        while (pos != dest) {
            if (pos > dest) pos--;
            else pos++;
            movimentos.append("M ");
        }
        return movimentos.toString();
    }
    
    public void agua() {
        percurso.append("I ");
    }

    public StringBuilder pegarpercurso() {
        return percurso;
    }    
}
