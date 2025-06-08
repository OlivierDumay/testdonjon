package dnd.objet.arme;

import dnd.objet.Arme;

import static dnd.des.De.lancerDe;

public class ArmeCourante extends Arme
{
    public ArmeCourante(String name, int nb_de, int nb_face, int m_portee)
    {

        super(name, nb_de, nb_face, m_portee);

    }

    public void appliqueBonusArme()
    {
        // rien
    }
    public int jetDegat()
    {
        return lancerDe(super.getnbDe(), super.getnbFace(), " le jet de dégat au corps à corps"); // quand perso attaque : perso.attaque(case, this.armeEquipee)
    }

    public int getBonusAttaque(int force, int dex)
    {
        return force;
    }
}

