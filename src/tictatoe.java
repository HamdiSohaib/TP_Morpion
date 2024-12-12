import java.util.Scanner;

    class tictactoe {

        static char[][] plateau = new char[3][3];
        static String joueur1, joueur2;
        static char joueurActuel;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);


            initialiserPlateau();


            System.out.print("Entrez le nom du joueur 1 (X) : ");
            joueur1 = scanner.nextLine();
            System.out.print("Entrez le nom du joueur 2 (O) : ");
            joueur2 = scanner.nextLine();


            joueurActuel = 'X';

            boolean jeuTermine = false;
            while (!jeuTermine) {
                afficherPlateau();
                System.out.println("C'est au tour de " + (joueurActuel == 'X' ? joueur1 : joueur2));
                faireCoup(scanner);
                jeuTermine = verifierFinDePartie();

                joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            }

            afficherPlateau();
            if (joueurActuel == 'X') {
                System.out.println("Le gagnant est : " + joueur2 + " (O) !");
            } else {
                System.out.println("Le gagnant est : " + joueur1 + " (X) !");
            }

            scanner.close();
        }

        public static void initialiserPlateau() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    plateau[i][j] = ' ';
                }
            }
        }

        public static void afficherPlateau() {
            System.out.println("\n");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(plateau[i][j]);
                    if (j < 2) System.out.print(" | ");
                }
                System.out.println();
                if (i < 2) System.out.println("---------");
            }
            System.out.println("\n");
        }

        public static void faireCoup(Scanner scanner) {
            int ligne, colonne;
            while (true) {
                System.out.print("Entrez la ligne (1-3) : ");
                ligne = scanner.nextInt() - 1;
                System.out.print("Entrez la colonne (1-3) : ");
                colonne = scanner.nextInt() - 1;

                if (ligne >= 0 && ligne < 3 && colonne >= 0 && colonne < 3 && plateau[ligne][colonne] == ' ') {
                    plateau[ligne][colonne] = joueurActuel;
                    break;
                } else {
                    System.out.println("Case invalide ou déjà occupée. Essayez encore.");
                }
            }
        }

        public static boolean verifierFinDePartie() {
            if (verifierVictoire()) {
                return true;
            }
            if (verifierMatchNul()) {
                System.out.println("C'est un match nul !");
                return true;
            }
            return false;
        }

        public static boolean verifierVictoire() {

            for (int i = 0; i < 3; i++) {
                if ((plateau[i][0] == joueurActuel && plateau[i][1] == joueurActuel && plateau[i][2] == joueurActuel) || // Lignes
                        (plateau[0][i] == joueurActuel && plateau[1][i] == joueurActuel && plateau[2][i] == joueurActuel)) { // Colonnes
                    return true;
                }
            }

            if ((plateau[0][0] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][2] == joueurActuel) || // Diagonale principale
                    (plateau[0][2] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][0] == joueurActuel)) { // Diagonale inverse
                return true;
            }

            return false;
        }

        public static boolean verifierMatchNul() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (plateau[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
    }
}
