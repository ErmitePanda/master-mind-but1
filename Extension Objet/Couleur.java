public class Couleur {
    private static char [] tabCouleurs;

    /**
     * Méthode qui initialise tabCouleur de taille passer en paramètre avec la fonction saisirCouleur
     */

    public static void initTabCoul() {
        tabCouleurs = UtMM.saisirCouleurs();
    }

    public static int nbCouleurs() {
        return tabCouleurs.length;
    }

    public static char[] getCouleurs() {
        return tabCouleurs;
    }

    /**
     * Convertit un string codMot en cod entier
     * @param codMot
     * @return
     */

    public static Code motVersEntiers(String codMot) {
        // Prend en paramètre une String représentant un code
        // Renvoie un objet Code contenant le code converti
        // d'un String vers un tableau d'entiers
        return new Code(UtMM.motVersEntiers(codMot.toUpperCase(), getCouleurs()));
    }

    /**
     * Convertie un entier en string
     * @param codEntier
     * @return
     */

    public static String entiersVersMot(Code c) {
        String codMot = "";
        int[] cod = c.getCode();
        for (int i = 0; i < cod.length; i++) {
            codMot += tabCouleurs[cod[i]];
        }
        return codMot.toUpperCase();
    }

    /**
     * Permet d'afficher les couleurs
     */

    public static void afficheCouleur() {
        for (int i = 0; i < tabCouleurs.length; i++) {
            Ut.afficher(tabCouleurs[i]+" ");
        }
    }
}