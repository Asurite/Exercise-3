
// reponses
import java.util.Scanner;

class Banque {
    public static final int nbMaxCompte = 1000;
    public static Compte T[] = new Compte[nbMaxCompte];
    public static int N = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            menu();
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ajouterCompte();
                    break;
                case 2:
                    supprimerCompte();
                    break;
                case 3:
                    ListedesComptes();
                    break;
                case 4:
                    Depot();
                    break;
                case 5:
                    Retrait();
                    break;
                case 6:
                    Virement();
                    break;
                case 7:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez entrer un nombre entre 1 et 7.");
            }

        } while (choix != 7);
    }

    public static void menu() {
        System.out.println("\t\t\tMenu principale");
        System.out.println("\t\t\t---------------");
        System.out.println("\tAjouter un compte==========>1");
        System.out.println("\tSupprimer un compte=========>2");
        System.out.println("\tListe des comptes===========>3");
        System.out.println("\tDépot=======================>4");
        System.out.println("\tRetrait=====================>5");
        System.out.println("\tVirement====================>6");
        System.out.println("\tQuitter=====================>73");
    }

    public static void ajouterCompte() {
        Scanner scanner = new Scanner(System.in);

        if (N < nbMaxCompte) {
            System.out.print("Entrez la propriété du compte: ");
            String propriete = scanner.next();
            System.out.print("Entrez le solde initial du compte: ");
            float solde = scanner.nextFloat();

            T[N] = new Compte(propriete, solde);
            N++;

            System.out.println("Compte ajouté avec succès!");
        } else {
            System.out.println("Nombre maximal de comptes atteint. Impossible d'ajouter un nouveau compte.");
        }
    }

    private static void supprimerCompte() {
        Scanner scanner = new Scanner(System.in);

        if (N == 0) {
            System.out.println("Aucun compte à supprimer. La liste des comptes est vide.");
            return;
        }

        System.out.print("Entrez le numéro du compte à supprimer: ");
        int numCompte = scanner.nextInt();

        int index = filtrerIndexCompte(numCompte);

        if (index != -1) {6
            for (int i = index; i < N - 1; i++) {
                T[i] = T[i + 1];
            }

            N--;
            System.out.println("Compte supprimé avec succès.");
        } else {
            System.out.println("Compte non trouvé. La suppression a échoué.");
        }
    }

    private static int filtrerIndexCompte(int numCompte) {
        for (int i = 0; i < N; i++) {
            if (T[i].getNum() == numCompte) {
                return i;
            }
        }
        return -1;
    }

    public static void ListedesComptes() {
        if (N == 0) {
            System.out.println("Aucun compte n'a été ajouté.");
        } else {
            System.out.println("Liste des comptes:");
            for (int i = 0; i < N; i++) {
                System.out.println(T[i]);
            }
        }
    }

    public static void Depot() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro du compte pour le dépôt: ");
        int numCompte = scanner.nextInt();

        Compte compte = filtrerCompte(numCompte);
        if (compte != null) {
            System.out.print("Entrez le montant à déposer: ");
            float montant = scanner.nextFloat();
            compte.deposer(montant);
            System.out.println("Dépôt effectué avec succès.");
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    public static void Retrait() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro du compte pour le retrait: ");
        int numCompte = scanner.nextInt();

        Compte compte = filtrerCompte(numCompte);
        if (compte != null) {
            System.out.print("Entrez le montant à retirer: ");
            float montant = scanner.nextFloat();
            compte.retirer(montant);
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    public static void Virement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le numéro du compte source pour le virement: ");
        int numCompteSource = scanner.nextInt();

        Compte compteSource = filtrerCompte(numCompteSource);

        System.out.print("Entrez le numéro du compte destinataire pour le virement: ");
        int numCompteDestinataire = scanner.nextInt();

        Compte compteDestinataire = filtrerCompte(numCompteDestinataire);

        if (compteSource != null && compteDestinataire != null) {
            System.out.print("Entrez le montant à virer: ");
            float montant = scanner.nextFloat();
            compteSource.virerVers(montant, compteDestinataire);
            System.out.println("Virement effectué avec succès.");
        } else {
            System.out.println("Compte source ou compte destinataire non trouvé.");
        }
    }

    public static Compte filtrerCompte(int numCompte) {
        for (int i = 0; i < N; i++) {
            if (T[i].num == numCompte) {
                return T[i];
            }
        }
        return null;
    }
}