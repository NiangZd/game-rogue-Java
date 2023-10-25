package visao;

import aplicacao.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int direcao, posX = 0, posY = 0;
        String receberMapa, atualizarMapa, verifVida;

        Mapa m1 = new Mapa();
        Robo r1 = new Robo(posX, posY);
        ArrayList<Inimigos> listaInimigos = m1.getListaInimigos();
        receberMapa = m1.desenharMapa(r1);
        System.out.println(receberMapa);

        do {

            //              TABELA DE COMANDOS

            System.out.println("DESEJA SE MOVER PARA QUAL DIRECAO?");
            System.out.println("1 - FRENTE");
            System.out.println("2 - TRAS");
            System.out.println("3 - DIREITA");
            System.out.println("4 - ESQUERDA");
            direcao = teclado.nextInt();
            teclado.nextLine();

            //              ATUALIZAR O MAPA COMPLETO
            atualizarMapa = m1.atualizarMapa(r1, direcao);
            System.out.println("VOCE TEM " + r1.getVidas() + " VIDAS");
            System.out.println(atualizarMapa);

            //EXIBIR POSIÇÕES INIMIGOS E PLAYER
            
            /*System.out.println("Nova posicao do robo: (" + r1.getPosX() + ", " + r1.getPosY() + ")");
            for (int index = 0; index < listaInimigos.size(); index++) {
                System.out.println("Nova posicao do inimigo " + (index + 1) + ": (" + listaInimigos.get(index).getPosX() + ", " + listaInimigos.get(index).getPosY() + ")");
            }*/

            //              MOSTRAR INIMIGOS E VERIFICAR VIDA

            System.out.println("Total de inimigos gerados: " + listaInimigos.size());
            verifVida = r1.verificarVida(r1, listaInimigos);
            System.out.println(verifVida);

            //              VERIFICAR CONDIÇÃO DE VITÓRIA | PASSAR FASE
            if (r1.tesouroVerif(m1)) {
                m1.setLevelMap((m1.getLevelMap()+1));
                r1.setPosX(0);
                r1.setPosY(0);
                r1.setVidas(3);
                m1.desenharMapa(r1);
            }

        } while (r1.getVidas() >= 1);
        System.out.println("GAME OVER!!!");
    }
}
