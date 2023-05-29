public class MM {
    // .........................................................................
    // OUTILS DE BASE
    // .........................................................................

    // fonctions classiques sur les tableaux

    /**
     * pré-requis : nb >= 0
     * résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = val;
        }
        return tab;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab) {
        int[] newtab = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            newtab[i] = tab[i];
        }
        return newtab;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la liste des éléments de t entre parenthèses et séparés par des
     * virgules
     */
    public static String listElem(char[] t) {
        String element = "(";
        for (int i = 0; i < t.length; i++) {
            element += t[i];
            if (i + 1 < t.length) {
                element += ",";
            }
        }
        return element + ")";
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : le plus grand indice d'une case de t contenant c s'il existe, -1
     * sinon
     */
    public static int plusGrandIndice(char[] t, char c) {
        for (int i = t.length-1; i >= 0; i--) {
            if (t[i] == c) {
                return i;
            }
        }
        return -1;
    }
    // ______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi c est un élément de t
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c) {
        return plusGrandIndice(t, c) != -1;
    }

    // ______________________________________________

    /**
     * pré-requis : aucun
     * action : affiche un doublon et 2 de ses indices dans t s'il en existe
     * résultat : vrai ssi les éléments de t sont différents
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t) {
        boolean res = true;
        for (int i = 0; i < t.length; i++) {
            if (estPresent(t, t[i]) && plusGrandIndice(t, t[i]) != i) {
                System.out.println("Doublon : " + t[i] + " aux indices " + i + " et "
                        + plusGrandIndice(t, t[i]));
                i++;
                res = false;
            }
        }
        return res;
    }

    // ______________________________________________

    /**
     * pré-requis : t1.length = t2.length
     * résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {
        for (int i = 0; i < t2.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;
    }

    // ______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les
    // paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de
    // tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /**
     * pré-requis : aucun
     * résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et
     * nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] tab = new int[lgCode];
        for (int i = 0; i < lgCode; i++) {
            tab[i] = Ut.randomMinMax(0, nbCouleurs - 1);
        }
        return tab;
    }

    // ____________________________________________________________

    /**
     * pré-requis : aucun
     * action : si codMot n'est pas correct, affiche pourquoi
     * résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne
     * contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        boolean res = true;
        if (codMot.length() != lgCode) {
            System.out.println("Le code doit être de longueur " + lgCode);
            res = false;
        }
        for (int i = 0; i < codMot.length(); i++) {
            if (!estPresent(tabCouleurs, codMot.charAt(i))) {
                System.out.println("Le code ne doit contenir que des éléments de " + listElem(tabCouleurs));
                res = false;
            }
        }
        return res;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant
     * chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] tab = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++) {
            tab[i] = plusGrandIndice(tabCouleurs, codMot.charAt(i));
        }
        return tab;
    }

    // ____________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition
     * de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     * qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     * résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        String codeHumain;
        do {
            System.out.println("Veuillez saisir le code sous forme de chaîne de caractère et de longeur lgCode == "+lgCode);
            Ut.afficher("Couleur disponnible dans tabCouleur : "); Ut.afficherSL(listElem(tabCouleurs));
            codeHumain = Ut.saisirChaine();
        } while (!codeCorrect(codeHumain, lgCode, tabCouleurs));
        return motVersEntiers(codeHumain, tabCouleurs);
    }

    // ____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même
     * indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1
     * (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int compteur = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                compteur++;
            }
        }
        return compteur;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le
     * nombre d'occurrences de i dans cod
     * Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne
     * (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] tabFrequence = new int[nbCouleurs];

        for (int i = 0; i < tabFrequence.length; i++) {
            for (int j = 0; j < cod.length; j++) {
                if (cod[j] == i) {
                    tabFrequence[i]++;
                }
            }
        }
        return tabFrequence;
    }

    // ____________________________________________________________

    /**
     * pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à
     * nbCouleurs-1
     * résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de
     * leur position
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3
     * (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int nbCommuns = 0;
        int [] tabFrequenceCod1 = tabFrequence(cod1, nbCouleurs);
        int [] tabFrequenceCod2 = tabFrequence(cod2, nbCouleurs);
        
        for (int i = 0; i < Math.min(tabFrequenceCod1.length, tabFrequenceCod2.length); i++) {
            nbCommuns += Math.min(tabFrequenceCod1[i], tabFrequenceCod2[i]);
        }

        return nbCommuns;
    }

    // ____________________________________________________________

    /**
     * pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont
     * des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre
     * d'éléments communs de cod1 et cod2
     * se trouvant (resp. ne se trouvant pas) au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne
     * (1,2) : 1 bien placé (le "0" à l'indice 3)
     * et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] tab = new int[2];

        // On vérifie le nombre d'élément bien placer

        tab[0] = nbBienPlaces(cod1, cod2);

        /* Nombre d'élément mal placé = nbCommun - nbBienPlace */

        tab[1] = nbCommuns(cod1, cod2, nbCouleurs) - tab[0];

        return tab;
    }

    // ____________________________________________________________

    // .........................................................................
    // MANCHEHUMAIN
    // .........................................................................

    /**
     * pré-requis : numMache >= 1
     * action : effectue la (numManche)ème manche où l'ordinateur est le codeur et
     * l'humain le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du
     * joueur humain (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int[] codeOrdi = codeAleat(lgCode, tabCouleurs.length);
        int currentEssai = 1;
        while (currentEssai <= nbEssaisMax) {
            int[] currentCode = propositionCodeHumain(currentEssai, lgCode, tabCouleurs);
            if (sontEgaux(codeOrdi, currentCode)) {
                System.out.println("Code trouvé.");
                return currentEssai;
            } else {
                int[] tabPlaces = nbBienMalPlaces(codeOrdi, currentCode, tabCouleurs.length);
                Ut.afficherSL("Nombre de pions bien placés : " + tabPlaces[0]);
                Ut.afficherSL("Nombre de pions mal placés : " + tabPlaces[1]);
                if (currentEssai == nbEssaisMax) { // Si on a atteint le nb max d'essais
                    return (tabPlaces[1] + 2 * (lgCode - (tabPlaces[0] + tabPlaces[1])));
                }
            }
            currentEssai++;
        }
        Ut.afficher(entiersVersMot(codeOrdi, tabCouleurs));
        return currentEssai;
    }

    // ____________________________________________________________

    // ...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    // ...................................................................

    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     * résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String codMot = "";
        for (int i = 0; i < cod.length; i++) {
            codMot += tabCouleurs[cod[i]];
        }
        return codMot.toUpperCase();
    }

    // ___________________________________________________________________

    /**
     * pré-requis : rep.length = 2
     * action : si rep n'est pas correcte, affiche pourquoi, sachant que rep[0] et
     * rep[1] sont
     * les nombres de bien et mal placés resp.
     * résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0
     * et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        return rep[0] >= 0 && rep[1] >= 0 && rep[0] + rep[1] <= lgCode;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir les nombres de bien et mal
     * placés,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode) {
        int[] reponseHumain = new int[2];
        do {
            System.out.println("Veuillez saisir le nombre d'élément bien placé : ");
            reponseHumain[0] = Ut.saisirEntier();
            System.out.println("Veuillez saisir le nombre d'élément mal placé : ");
            reponseHumain[1] = Ut.saisirEntier();            
        } while (!repCorrecte(reponseHumain, lgCode));
        return reponseHumain;
    }

    // ___________________________________________________________________

    /**
     * CHANGE : action si le code suivant n'existe pas
     *************************************************
     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * action/résultat : met dans cod1 le code qui le suit selon l'ordre
     * lexicographique (dans l'ensemble
     * des codes à valeurs de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        boolean passer = false;
        int i = cod1.length - 1;
        int x;

        while (i >= 0 && !passer) {
            if (cod1[i] < nbCouleurs - 1) {
                passer = true;
                cod1[i]++;
                x = i + 1;

                while (x < cod1.length) {
                    cod1[x] = 0;
                    x++;
                }
            }
            i--;
        }
        return passer;
    }

    // ___________________________________________________________________

    /**
     * CHANGE : ajout du paramètre cod1 et modification des spécifications
     ********************************************************************* 
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice
     * à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0
     * à nbCouleurs-1
     * résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de
     * cod et de rep,
     * c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups
     * premières
     * propositions de cod seraient les nbCoups premières réponses de rep resp.
     */
    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        int [] codIndexI; int [] repCodIndexI;
        for (int i = 0; i < nbCoups; i++) {
            codIndexI = copieTab(cod[i]);
            repCodIndexI = nbBienMalPlaces(cod1, codIndexI, nbCouleurs);
            if (repCodIndexI[0] != rep[i][0] || repCodIndexI[1] != rep[i][1]) {
                return false;
            }
        }
        return true;
    }
    // ___________________________________________________________________

    /**
     * CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     * ajout du paramètre cod1 et modification des spécifications
     ************************************************************************** 
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice
     * à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0
     * à nbCouleurs-1
     * action/résultat : met dans cod1 le plus petit code (selon l'ordre
     * lexicographique (dans l'ensemble
     * des codes à valeurs de 0 à nbCouleurs-1) qui est à la fois plus grand que
     * cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod
     * et rep si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups,int nbCouleurs) {
        do {
            if (!passeCodeSuivantLexico(cod1, nbCouleurs)) {
                cod1 = initTab(cod1.length, 0);
                return false;
            }
        } while (!estCompat(cod1, cod, rep, nbCoups, nbCouleurs));
        return true;
    }

    // ___________________________________________________________________

    // manche Ordinateur

    /**
     * pré-requis : numManche >= 2
     * action : effectue la (numManche)ème manche où l'humain est le codeur et
     * l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de
     * l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        boolean trouve = false;
        boolean passeCodeSuivant = false;
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        int[] codATester = initTab(lgCode, 0);
        String codMot; // codATester qui sera remplie de 0 si on ne peut passer ou pas compat
        int[] codeMemory = initTab(lgCode, 0); // Le code en mémoire du lexico courant
        int[] repMemory = initTab(2, 0);
        int nbCoups = 0;
        while (!trouve && nbCoups < nbEssaisMax) {
            Ut.afficherSL("Manche N°" + numManche + " coups N°" + (nbCoups + 1) + "\n\n");

            codMot = entiersVersMot(codATester, tabCouleurs);

            // Ajout du code compat proposé par l'ordinateur au plateaux
            for (int i = 0; i < cod[nbCoups].length; i++) {
                cod[nbCoups][i] = codATester[i];
            }

            // Affichage du code proposé par l'ordonateur
            Ut.afficher("Proposition de l'ordinateur : ");
            for (int i = 0; i < codMot.length(); i++) {
                Ut.afficher(codMot.charAt(i) + " ");
            }
            System.out.println("\n");

            // Validation des réponse de l'humain qui ne seront pas forcément valide

            repMemory = reponseHumain(lgCode);
            rep[nbCoups][0] = repMemory[0];
            rep[nbCoups][1] = repMemory[1];

            // Si le code proposé par l'ordinateur est le code secret

            Ut.sautLigne();
            nbCoups++;

            if (rep[nbCoups - 1][0] == lgCode) {
                trouve = true;
            } else {
                while (!passeCodeSuivant) {
                    // Si le cod est compatible et peut être passer au code lexico suivant
                    if (passeCodeSuivantLexicoCompat(codATester, cod, rep, nbCoups, tabCouleurs.length)) {
                        passeCodeSuivant = true;
                        codeMemory = copieTab(codATester);

                        Ut.sautLigne();
                    }

                    // Si passeCode lexico suivant, c'est que l'ancien cod n'était pas compatible
                    else if (passeCodeSuivantLexico(codeMemory, tabCouleurs.length)) {
                        codATester = copieTab(codeMemory);
                    }

                    // L'humain s'est trompé dans une de ses saisit
                    else {
                        return 0;
                    }
                }
            }
            passeCodeSuivant = false;
        }

        if (nbCoups == nbEssaisMax) {
            Ut.sautLigne();
            return rep[nbCoups - 1][1] + 2 * (lgCode - (rep[nbCoups - 1][0] + rep[nbCoups - 1][1]));
        }

        Ut.afficherSL("saisir le votre code secret");
        String codHumain = Ut.saisirChaine();
        return nbCoups;
    }

    // ___________________________________________________________________

    // .........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    // .........................................................................

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {
        int nbPositif = 0;
        do {
            Ut.afficherSL("\nVeuillez saisir un nombre strictement positif (cad nb > 0) : ");
            nbPositif = Ut.saisirEntier();
        } while (nbPositif <= 0);
        return nbPositif;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier pair strictement
     * positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {
        int nbPositifPair = 0;
        do {
            Ut.afficherSL("\nVeuillez saisir un nombre pair strictement positif (cad nb>0 et nb%2==0) : ");
            nbPositifPair = Ut.saisirEntier();
        } while (nbPositifPair <= 0 || nbPositifPair % 2 != 0);
        return nbPositifPair;
    }

    // ___________________________________________________________________

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir le nombre de couleurs (stricement
     * positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs() {
        int nbPositif = saisirEntierPositif();
        char[] tabCouleur = new char[nbPositif];
        //Ut.afficherSL(tabCouleur);
        char couleur;
        boolean couleurValide = false;
        boolean trouveDoublon = false;
        int j = 0;

        for (int i = 0; i < nbPositif; i++) {
            while (!couleurValide) {
                Ut.afficherSL("Veuillez saisir une couleur (Format : Première lettre d'une couleur " +
                        "\nex : bleu ==> b, orange ==> o, etc...)");
                Ut.sautLigne();
                Ut.afficherSL("Votre tab couleur actuellement");
                couleur = Ut.saisirCaractere();
                while (j < tabCouleur.length && !trouveDoublon) {
                    if (couleur == tabCouleur[j]) {
                        trouveDoublon = true;
                    }
                    j++;
                }

                j = 0;

                // Si on ne trouve pas de doublon on l'ajoute sinon on renvoie un message
                // d'erreur et on fait ressaisir

                if (!trouveDoublon) {
                    couleurValide = true;
                    tabCouleur[i] = Character.toUpperCase(couleur);
                } else {
                    trouveDoublon = false;
                    System.out.println("Couleur déjà présente !!!");
                }
            }

            // reset avant de passer à la saisit suivante

            couleurValide = false;
            trouveDoublon = false;
        }
        return tabCouleur;
    }
}