import dnd.*;
import dnd.gameobject.GameObject;
import dnd.gameobject.personnage.Personnage;
import dnd.gameobject.personnage.classe.EnumClasse;
import dnd.gameobject.personnage.race.EnumRace;
import dnd.objet.Item;
import dnd.objet.arme.ArmeCourante;
import dnd.partie.donjon.Carte;
import dnd.affichage.Affichage;

public class Main
{
    public static void main(String args[])
    {
        System.out.println("Bienvenue dans DOOnjon et Dragons\n");

        Personnage perso1 = new Personnage("Albert", EnumClasse.CLERC, EnumRace.HUMAIN);
        Carte carte = new Carte(15,20);
        carte.ajouterGameObject(perso1, 2, 2);
        carte.ajouterObstacle(2, 3);
        carte.ajouterObstacle(4, 6);
        carte.ajouterObstacle(8, 3);

        Item item = new Item("epee");
        try
        {
            carte.ajouterItem(item, 1,21);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        Affichage.afficherCarte(carte);
        carte.retirerItem( item, 1, 2);
        System.out.println();
        Affichage.afficherCarte(carte);

    }
}