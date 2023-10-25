package aplicacao;
import java.util.ArrayList;
import java.util.Random;

public class Mapa {
    private int levelMap = 75;
    private int totalInimigos;
    private int[][] campo = new int[20][40];
    private int posX;
    private int posY;
    private Robo r1;
    private boolean gerado = false;
    private ArrayList<Inimigos> listaInimigos = new ArrayList<Inimigos>();
    private Tesouro tesouro;

    public void setLevelMap(int levelMap) {
        this.levelMap = levelMap;
    }
    public int getLevelMap() {
        return levelMap;
    }

    public void setTesouro(Tesouro tesouro) {
        this.tesouro = tesouro;
    }

    public Tesouro getTesouro() {
        return tesouro;
    }

    public ArrayList<Inimigos> getListaInimigos() {
        return listaInimigos;
    }

    public void setGerado(boolean gerado) {
        this.gerado = gerado;
    }
    public boolean getGerado(){
        return gerado;
    }

    public int[][] getCampo() {
        return campo;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setCampo(int[][] campo) {
        this.campo = campo;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String desenharMapa(Robo robo) {
        Random rand = new Random();
        this.r1 = robo;
    
        if (levelMap >= 1 && levelMap <= 2) {
            totalInimigos = 4;
        } else if (levelMap >= 3 && levelMap <= 5) {
            totalInimigos = 7;
        } else if (levelMap >= 6) {
            totalInimigos = 12;
        }
    
        System.out.println("Total de inimigos a serem gerados: " + totalInimigos); // Depuração
    
        ArrayList<int[]> coordenadasDisponiveis = new ArrayList<int[]>();
        for (int i = 2; i < 18; i++) {
            for (int j = 5; j < 38; j++) {
                coordenadasDisponiveis.add(new int[] {i, j});
            }
        }
    
        if (!gerado) {
            for (int index = 0; index < totalInimigos; index++) {
                if (coordenadasDisponiveis.isEmpty()) {
                    break;
                }
        
                int randomIndex = rand.nextInt(coordenadasDisponiveis.size());
                int[] coordenada = coordenadasDisponiveis.remove(randomIndex);
        
                int x = coordenada[1]; // Coordenada X
                int y = coordenada[0]; // Coordenada Y
        
                Inimigos ini = new Inimigos(x, y);
                listaInimigos.add(ini);
            }
        
            tesouro = new Tesouro((rand.nextInt(19) + 20), (rand.nextInt(9)+10));
        
            this.gerado = true;
        }
        
    
        System.out.println("Total de inimigos gerados: " + listaInimigos.size());
        StringBuilder mapa = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                boolean inimigoPresente = false;
                for (Inimigos ini : listaInimigos) {
                    if (ini.getPosX() == j && ini.getPosY() == i) {
                        mapa.append(" \u001B[31mI\u001B[0m ");
                        inimigoPresente = true;
                        break;
                    }
                }
    
                if (!inimigoPresente) {
                    if (r1.getPosY() == i && r1.getPosX() == j) {
                        mapa.append(" \u001B[34mP\u001B[0m ");
                    } else if (tesouro.getPosX() == j && tesouro.getPosY() == i) {
                        mapa.append(" \u001B[33mT\u001B[0m ");
                    } else {
                        mapa.append(" \u001B[32m.\u001B[0m ");
                    }
                }
            }
            mapa.append("\n");
        }
    
        return mapa.toString();
    }
    

    public String atualizarMapa(Robo robo, int direcao){
        Random rand = new Random();
        this.r1 = robo;

        int moverIni, moverPlayer = direcao;

        switch (moverPlayer) {
            case 1:
                r1.moverFrente();
                break;
            case 2:
                r1.moverTras();
                break;
            case 3:
                r1.moverDireita();
                break;
            case 4:
                r1.moverEsquerda();
                break;
            default:
                break;
        }

        for (int index = 0; index < totalInimigos; index++) {
            moverIni = rand.nextInt(4) + 1;

            switch (moverIni) {
                case 1:
                    listaInimigos.get(index).moverDireita();
                    break;
                case 2:
                    listaInimigos.get(index).moverEsquerda();
                    break;
                case 3:
                    listaInimigos.get(index).moverFrente();
                    break;
                case 4:
                    listaInimigos.get(index).moverTras();
                    break;
                default:
                    break;
            }
        }

        System.out.println("Total de inimigos gerados: " + listaInimigos.size()); 
        StringBuilder mapa = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 40; j++) {
                boolean inimigoPresente = false;
                for (Inimigos ini : listaInimigos) {
                    if (ini.getPosX() == j && ini.getPosY() == i) { 
                        mapa.append(" \u001B[31mI\u001B[0m ");
                        inimigoPresente = true;
                        break;
                    }
                }
                
                if (!inimigoPresente) {
                    if (r1.getPosY() == i && r1.getPosX() == j) {
                        mapa.append(" \u001B[34mP\u001B[0m ");
                    } else if (tesouro.getPosX() == j && tesouro.getPosY() == i) {
                        mapa.append(" \u001B[33mT\u001B[0m ");
                    } else {
                        mapa.append(" \u001B[32m.\u001B[0m ");
                    }
                }
            }
            mapa.append("\n");
        }
        return mapa.toString();
    }
}