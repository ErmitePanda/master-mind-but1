import java.util.Arrays;

public class Plateau {
    private static int nbEssaisMax; // >= 0
    private Code[] cod;
    private int[][] rep;
    private int nbCoups; // >= 0

    // Constructeur(s)
    public Plateau() {
        // Init un plateau vide avec le nb d'essais max
        // ( on considère que les essais commencent à 0 )
        // /!\ Avant de créer une instance, il faut lancer
        // la fonction initEssaisMax
        this.cod = new Code[nbEssaisMax+1];
        this.rep = new int[nbEssaisMax+1][2];
        this.nbCoups = 0;
    }

    public static void initEssaisMax(int n) {
        nbEssaisMax = n;
    }

    public void jouerCoup(Code code) {
        /* Insère un code passé en paramètre dans cod */
        cod[nbCoups] = code;
    }

    public void jouerReponse(int bienPlace, int malPlace) {
        /* Insère une réponse passée en paramètre dans rep */
        rep[nbCoups][0] = bienPlace;
        rep[nbCoups][1] = malPlace;
    }

    public void incrementerCoup() {
        nbCoups++;
    }

    public int getNbCoups() {
        return nbCoups;
    }

    public static int getNbEssaisMax() {
        return nbEssaisMax;
    }

    public void setCoups(int n) {
        this.nbCoups = n;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "cod=" + Arrays.toString(cod) +
                ", \n rep=" + UtMM.MatriceToString(rep) +
                ", \n nbCoups=" + nbCoups + " / " + nbEssaisMax +
                '}';
    }
}
