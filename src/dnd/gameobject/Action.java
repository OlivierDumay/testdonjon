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
    public static void equiper(Personnage personnage, int n_equipement)
    {
        Inventaire inventaire_perso = personnage.getInventaire();

        if (n_equipement < 0 || n_equipement >= inventaire_perso.size())
            throw new IllegalArgumentException("Erreur : item inexistant dans l'inventaire !");

        Item item = inventaire_perso.getInventaire().get(n_equipement);

        try {
            switch (item.getType())
            {
                case ARME:
                    personnage.getEquipement().equiperArme((Arme) item);
                    break;
                case ARMURE:
                    personnage.getEquipement().equiperArmure((Armure) item);
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


    public static void desequiper(Personnage personnage, Type type)
    {
        Inventaire inventaire_perso = personnage.getInventaire();
        EquipementPersonnage equipement_perso = personnage.getEquipement();

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
}
