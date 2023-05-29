public class MasterMindBase {

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
        for (int i = t.length - 1; i >= 0; i--) {
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
            System.out.println(
                    "Veuillez saisir le code sous forme de chaîne de caractère et de longeur lgCode == " + lgCode);
            Ut.afficher("Couleur disponnible dans tabCouleur : ");
            Ut.afficherSL(listElem(tabCouleurs));
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
        int[] codIndexI;
        int[] repCodIndexI;
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
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups,
            int nbCouleurs) {
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
        int [][] cod = new int[nbEssaisMax][lgCode];
        int [][] rep = new int[nbEssaisMax][2];
        int [] cod1 = initTab(lgCode,0);
        int [] repHumain;
        int nbCoups = 0;
        boolean trouve = false;

        System.out.println("Manche N°"+numManche);

        do {
            System.out.println("********************************************************");
            System.out.println("Coup N°"+(nbCoups+1)+"\nCode proposé par l'ordinateur :\n"+entiersVersMot(cod1, tabCouleurs));
            repHumain = reponseHumain(lgCode);
            cod[nbCoups] = copieTab(cod1);
            rep[nbCoups] = copieTab(repHumain);
            if (rep[nbCoups][0] == lgCode) {
                trouve = true;
            }
            nbCoups++;
            affichePlateau(cod, rep, nbCoups, tabCouleurs);
            passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, nbCoups);
            System.out.println("********************************************************");
        } while (nbCoups < nbEssaisMax && !trouve);

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
        // Ut.afficherSL(tabCouleur);
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

    /**
     * Fonction complémentaire pour afficher un tableaux d'entier
     */

    public static void afficheTabInt(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + "  ");
        }
        Ut.sautLigne();
    }

    // ___________________________________________________________________

    // .........................................................................
    // PROGRAMME PRINCIPAL
    // .........................................................................

    /**
     * CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
     ******************************************************************************
     * action : demande à l'utilisateur de saisir les paramètres de la partie
     * (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum
     * doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {
        /* Saisit de lgCode,tabCouleur, nbManches et nbEssaisMax par l'humain */
        Ut.afficher("*** Début de la saisit de lgCode (Prérequis : lgCode > 0) :");
        int lgCode = saisirEntierPositif();
        Ut.afficher("*** Début de la saisit de tabCouleur : ");
        char[] tabCouleur = saisirCouleurs();
        Ut.afficher("*** Début de la saisit de nbManche (Prérequis : nbManche > 0 et pair) : ");
        int nbManches = saisirEntierPairPositif();
        Ut.afficher("*** Début de la saisit de nbEsssaieMax (Prérequis : nbEssaisMax > 0) : ");
        int nbEssaisMax = saisirEntierPositif();

        int scoreHumain = 0;
        int scoreOrdinateur = 0;
        int i = 0;
        while (i < nbManches) {
            scoreOrdinateur += mancheHumain(lgCode, tabCouleur, i + 1, nbEssaisMax);
            i++;
            scoreHumain += mancheOrdinateur(lgCode, tabCouleur, i + 1, nbEssaisMax);
            i++;
        }
        Ut.afficherSL("Score Humain : " + scoreHumain);
        Ut.sautLigne();
        Ut.afficherSL("Score Ordinateur : " + scoreOrdinateur);
        Ut.sautLigne();

        if (scoreHumain > scoreOrdinateur) {
            Ut.afficherSL("L'humain à gagné avec un total de " + scoreHumain);
        } else if (scoreOrdinateur > scoreHumain) {
            Ut.afficherSL("L'ordinateur a gagné avec un total de " + scoreOrdinateur);
        } else {
            Ut.afficherSL("Egalité entre l'humain et l'ordinateur " + scoreHumain + " à " + scoreOrdinateur);
        }
        Ut.sautLigne();
        Ut.afficher("Fin du mastermind, Extinction des feux");
    } // fin main

    // ___________________________________________________________________

    // Extensions affichage du plateaux

    /**
     * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
     * 0 <= nbCoups < cod.length, nbCoups < rep.length et
     * les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
     * action : affiche les nbCoups premières lignes de cod (sous forme
     * de mots en utilisant le tableau tabCouleurs) et de rep
     */

    public static void affichePlateau(int[][] cod, int[][] rep, int nbCoups, char[] tabCouleur) {
        String codMot = "";
        int[] codLigne = initTab(cod[0].length, 0);
        for (int i = 0; i < nbCoups; i++) {
            for (int j = 0; j < cod[i].length; j++) {
                for (int j2 = 0; j2 < cod[i].length; j2++) {
                    codLigne[j2] = cod[i][j2];
                }
                codMot = entiersVersMot(codLigne, tabCouleur);
                Ut.afficher(codMot.charAt(j) + " ");
                if (!(j + 1 < cod[i].length)) {
                    Ut.afficher(" " + rep[i][0] + " " + rep[i][1]);
                }
            }
            Ut.sautLigne();
        }
    }

    /**
     * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
     * 6
     * 0 <= nbCoups < cod.length, nbCoups < rep.length,
     * les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
     * et codMot est incorrect ou incompatible avec les nbCoups
     * premières lignes de cod et de rep
     * action : affiche les erreurs d’incorrection ou d’incompatibilité
     */
    public static void afficheErreurs(String codMot, int[][] cod, int[][] rep, int nbCoups, int lgCode,
            char[] tabCouleurs) {
        int[] repCodMot = new int[2];
        int[] codRecup = initTab(cod[0].length, 0);
        for (int i = 0; i < cod.length; i++) {

        }
    }

} // fin MasterMindBase