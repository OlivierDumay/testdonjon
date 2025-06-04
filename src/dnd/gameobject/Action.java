package dnd.gameobject;

import dnd.Asset;
import dnd.Type;
import dnd.gameobject.personnage.EquipementPersonnage;
import dnd.gameobject.personnage.Inventaire;
import dnd.gameobject.personnage.Personnage;
import dnd.objet.Arme;
import dnd.objet.Armure;
import dnd.objet.Item;

public class Action
{
    public static void equiper(Asset personnage, int n_equipement)
    {
        Personnage personnage_cast = (Personnage) personnage;
        Inventaire inventaire_perso = personnage_cast.getInventaire();

        if (n_equipement < 0 || n_equipement >= inventaire_perso.size())
            throw new IllegalArgumentException("Erreur : item inexistant dans l'inventaire !");

        Item item = inventaire_perso.getInventaire().get(n_equipement);

        try {
            switch (item.getType())
            {
                case ARME:
                    personnage_cast.getEquipement().equiperArme((Arme) item);
                    break;
                case ARMURE:
                    personnage_cast.getEquipement().equiperArmure((Armure) item);
                    break;
                default:
                    throw new IllegalArgumentException("Erreur : cet item n'est ni une arme ni une armure !");
            }

            inventaire_perso.removeItem(n_equipement);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static void desequiper(Asset personnage, Type type)
    {
        Personnage personnage_cast = (Personnage)personnage;
        Inventaire inventaire_perso = personnage_cast.getInventaire();
        EquipementPersonnage equipement_perso = personnage_cast.getEquipement();

        switch (type)
        {
            case ARME:
                Arme arme = equipement_perso.getArme();
                inventaire_perso.addItem(arme);
                equipement_perso.retirerArme();
                break;
            case ARMURE:
                Armure armure = equipement_perso.getArmure();
                inventaire_perso.addItem(armure);
                equipement_perso.retirerArmure();
                break;
            default:
                // should never occur
                throw new IllegalArgumentException("Erreur : le type d'équipement est invalide !");
        }
    }

    public static void deplacer(Asset personnage)
    {
        Personnage personnage_cast = (Personnage)personnage;
    }
}
