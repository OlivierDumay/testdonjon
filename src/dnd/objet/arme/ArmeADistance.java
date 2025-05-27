package dnd.objet.arme;

import dnd.objet.Arme;

import static dnd.des.De.lancerDe;

public class ArmeADistance extends Arme
{
    public ArmeADistance(String name, int m_deDegat, int m_nbDe, int m_portee)
    {

        super(name, m_deDegat, m_nbDe, m_portee);

    }

    public void appliqueBonusArme()
    {
        // rien
    }
    public int jetDegat()
    {
        return lancerDe(this.m_deDegat, this.m_nbDe); // quand perso attaque : perso.attaque(case, this.armeEquipee)
    }

}
