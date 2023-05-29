public class MancheHumain {
    private Plateau p;

    public MancheHumain() {
        this.p = new Plateau();
    }

    // fonctions sur les codes pour la manche Humain
    public static Code propositionCodeHumain() {
        String codeHumain;
        do {
            System.out.println("Veuillez saisir le code sous forme de chaîne de caractères et de longueur lgCode == "+Code.getLgCode());
            Ut.afficher("Couleurs disponibles dans tabCouleur : ");
            System.out.println(UtMM.listElem(Couleur.getCouleurs())); 
            codeHumain = Ut.saisirChaine();
        } while (!Code.codeCorrect(codeHumain));
        return Couleur.motVersEntiers(codeHumain);
    }

    public int mancheHumain() {
        Code codeOrdi = Code.codeAleat();
        p.setCoups(1);
        while (p.getNbCoups() <= Plateau.getNbEssaisMax()) {
            Code currentCode = propositionCodeHumain();
            p.jouerCoup(currentCode);
            if (Code.sontEgaux(codeOrdi, currentCode)) {
                System.out.println("Code trouvé.");
                return p.getNbCoups();
            } else {
                int[] tabPlaces = Code.nbBienMalPlaces(codeOrdi, currentCode);
                Ut.afficherSL("Nombre de pions bien placés : " + tabPlaces[0]);
                Ut.afficherSL("Nombre de pions mal placés : " + tabPlaces[1]);
                p.jouerReponse(tabPlaces[0], tabPlaces[1]);
                if (p.getNbCoups() == Plateau.getNbEssaisMax()) { // Si on a atteint le nb max d'essais
                    return (tabPlaces[1] + 2 * (Code.getLgCode() - (tabPlaces[0] + tabPlaces[1])));
                }
            }
            p.incrementerCoup();
        }
        Ut.afficher(Couleur.entiersVersMot(codeOrdi));
        return p.getNbCoups();
    }
}
