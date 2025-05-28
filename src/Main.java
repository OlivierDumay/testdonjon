import dnd.*;
import dnd.gameobject.personnage.Personnage;
import dnd.gameobject.personnage.classe.EnumClasse;
import dnd.gameobject.personnage.race.EnumRace;
import dnd.objet.arme.ArmeCourante;

public class Main
{
    public static void main(String args[])
    {
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        Personnage muhammed = new Personnage("Muhammed", EnumClasse.CLERC, EnumRace.HUMAIN);

        System.out.println("Étiquette de Muhammed : " + muhammed.getEtiquette());

        muhammed.equiper(0);

        System.out.println("Muhammed : " + muhammed.toString());


    }
}