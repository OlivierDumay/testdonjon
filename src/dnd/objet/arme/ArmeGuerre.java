package dnd.objet.arme;

import dnd.objet.Arme;

import static dnd.des.De.lancerDe;

public class ArmeGuerre extends Arme
{
    public ArmeGuerre(String name, int m_deDegat, int m_nbDe, int m_portee)
    {

        super(name, m_deDegat, m_nbDe, m_portee);

    }

    public void appliqueBonusArme()
    {
        // -2 vitesse + 4 force
    }
    public int jetDegat()
    {
        return lancerDe(this.m_deDegat, this.m_nbDe); // quand perso attaque : perso.attaque(case, this.armeEquipee)
    }


}
