package aplicacao;

public abstract class Personagens {
    private int posX;
    private int posY;

    public Personagens(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void moverDireita(){
        setPosX(getPosX() + 1);
        if (getPosX() > 40) {
            setPosX(39);
        }
    }

    public void moverEsquerda(){
        setPosX(getPosX() - 1);
        if (getPosX() < 0) {
            setPosX(0);
        }
    }

    public void moverFrente(){
        setPosY(getPosY() + 1);
        if (getPosY() >= 20) {
            setPosY(19);
        }
    }

    public void moverTras(){
        setPosY(getPosY() - 1);
        if (getPosY() < 0) {
            setPosY(0);
        }
    }
}
