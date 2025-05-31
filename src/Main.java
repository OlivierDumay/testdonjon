import dnd.*;
import dnd.gameobject.personnage.Personnage;
import dnd.gameobject.personnage.classe.EnumClasse;
import dnd.gameobject.personnage.race.EnumRace;
import dnd.objet.arme.ArmeCourante;
import dnd.partie.donjon.Carte;
import dnd.affichage.Affichage;

public class Main
{
    public static void main(String args[])
    {
        System.out.println("Bienvenue dans DOOnjon et Dragons\n");
        Carte carte =new Carte(15,15);

        Personnage perso = new Personnage("Alber Venturier", EnumClasse.CLERC, EnumRace.HUMAIN);

        //System.out.println("Alber.getString() : \n");
        //perso.getString();
        System.out.println("alber.getEtiquette = " + perso.getEtiquette());


        carte.ajouterGameObject(perso, 2, 2);

        System.out.println("carte.getEtiquettedelaCase = " + carte.getEtiquetteDeLaCase(2,2));
        System.out.println("carte.getCase(2,2).getGameObject().getEtiquette()= " + carte.getCase(2,2).getGameObject().getEtiquette());


        Affichage.afficherCarte(carte);




    }
}