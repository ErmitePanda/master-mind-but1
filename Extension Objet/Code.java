import java.util.Arrays;

public class Code {
    private int [] code;
    private static int lgCode;

    // Constructeurs
    public Code() {
        // Init un code vide
        this.code = new int[lgCode];
    }

    public Code(int[] c) {
        // Init un code à l'aide d'un tableau pré existant
        this.code = UtMM.copieTab(c);
    }

    public Code(Code c) {
        // Init par recopie
        this.code = UtMM.copieTab(c.code);
    }

    public static void initLgCode(int n) {
        lgCode = n;
    }

    @Override
    public String toString() {
        return Arrays.toString(code);
    }

    public int[] getCode() {
        return code;
    }

    public static int getLgCode() {
        return lgCode;
    }

    //MancheHumain
    public static boolean sontEgaux(Code c1, Code c2) {
        int[] t1 = c1.getCode(), t2 = c2.getCode();
        for (int i = 0; i < t2.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Code codeAleat() {
        int[] tab = new int[Code.getLgCode()];
        for (int i = 0; i < Code.getLgCode(); i++) {
            tab[i] = Ut.randomMinMax(0, Couleur.nbCouleurs() - 1);
        }
        return new Code(tab);
    }

    public static boolean codeCorrect(String codMot) {
        boolean res = true;
        if (codMot.length() != Code.getLgCode()) {
            System.out.println("Le code doit être de longueur " + Code.getLgCode());
            res = false;
        }
        for (int i = 0; i < codMot.length(); i++) {
            if (!UtMM.estPresent(Couleur.getCouleurs(), codMot.charAt(i))) {
                System.out.println("Le code ne doit contenir que des éléments de " + UtMM.listElem(Couleur.getCouleurs()));
                res = false;
            }
        }
        return res;
    }

    public static int nbBienPlaces(Code c1, Code c2) {
        int compteur = 0;
        int[] cod1 = c1.getCode(), cod2 = c2.getCode();
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                compteur++;
            }
        }
        return compteur;
    }

    public static int[] codeFrequence(Code c) {
        int[] tabFrequence = new int[Couleur.nbCouleurs()];
        int[] cod = c.getCode();
        for (int i = 0; i < tabFrequence.length; i++) {
            for (int j = 0; j < cod.length; j++) {
                if (cod[j] == i) {
                    tabFrequence[i]++;
                }
            }
        }
        return tabFrequence;
    }

    public static int nbCommuns(Code c1, Code c2) {
        int val;
        int j;
        int compteur = 0;
        int[] cod1 = c1.getCode(), cod2 = c2.getCode();
        boolean nbTrouve;
        for (int i = 0; i < cod1.length; i++) {
            val = cod1[i];
            nbTrouve = false;
            j = 0;
            while (j < cod2.length && !nbTrouve) {
                if (cod2[j] == val) {
                    nbTrouve = true;
                    compteur++;
                }
                j++;
            }
        }
        return compteur;
    }

    public static int[] nbBienMalPlaces(Code c1, Code c2) {
        int[] tab = new int[2];

        // On vérifie le nombre d'élément bien placés

        tab[0] = nbBienPlaces(c1, c2);

        /* Nombre d'éléments mal placés = nbCommun - nbBienPlace */

        tab[1] = nbCommuns(c1, c2) - tab[0];

        return tab;
    }
}
