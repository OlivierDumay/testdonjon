package dnd.objet.arme;

import dnd.objet.Arme;

import static dnd.des.De.lancerDe;

public class ArmeGuerre extends Arme
{
    public ArmeGuerre(String name, int nb_de, int nb_face, int m_portee)
    {

        super(name, nb_de, nb_face, m_portee);

    }

    public void appliqueBonusArme()
    {
        // -2 vitesse + 4 force
    }
    public int jetDegat()
    {
        return lancerDe(super.getnbDe(), super.getnbFace()); // quand perso attaque : perso.attaque(case, this.armeEquipee)
    }


}
