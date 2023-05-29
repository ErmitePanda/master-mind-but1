public class testMMObjet {
    /* Classe utilisée pour tester les fonctions de la version objet individuellement */
    public static void main(String[] args) {
        Code.initLgCode(3);
        Code c1 = new Code();
        System.out.println(c1);
        Code c2 = new Code(new int[] {1,1,3,2});
        System.out.println(c2);
        Code c3 = new Code(c2);
        System.out.println(c3);

        Couleur.initTabCoul();
        Code c4 = Couleur.motVersEntiers("RVBR");
        System.out.println(c4);

        String m1 = Couleur.entiersVersMot(c2);
        System.out.println(m1);

        Couleur.afficheCouleur();

        Plateau.initEssaisMax(10);
        Plateau p1 = new Plateau();
        // p1.jouerCoup(c3);
        // p1.jouerReponse(2,1);
        // p1.incrementerCoup();
        // p1.jouerCoup(c1);
        // p1.jouerReponse(1,2);
        // System.out.println(p1);
        MancheHumain manche1 = new MancheHumain();
        manche1.mancheHumain();
    }
}
