package aplicacao;

import java.util.ArrayList;

public class Robo extends Personagens{
    private int vidas = 3;
    private Robo r1;
    private ArrayList<Inimigos> listaInimigos;
    private Tesouro tesouroP;

    public Robo(int novX, int novY){
        super(novX, novY);
    }

    public int getVidas() {
        return vidas;
    }
    
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String verificarVida(Robo robo, ArrayList<Inimigos> listaInimigosRec) {
        r1 = robo;
        this.listaInimigos = listaInimigosRec;
        boolean colidiu = false;
        
        for (int index = 0; index < listaInimigos.size(); index++) {
            if (r1.getPosX() == listaInimigos.get(index).getPosX() && r1.getPosY() == listaInimigos.get(index).getPosY()) {
                colidiu = true;
                break; // Encontrou uma colisão, não é necessário verificar com os outros inimigos
            }
        }
        
        if (colidiu) {
            r1.setVidas(r1.getVidas() - 1);
            return "\n\nOPS, VOCE PERDEU UMA VIDA!!!\n\n";
        }
        
        return "";
    }

    public boolean tesouroVerif(Mapa m1){
        tesouroP = m1.getTesouro();
        if (r1.getPosX() == tesouroP.getPosX() ) {
            return true;
        }
        return false;
    }
}