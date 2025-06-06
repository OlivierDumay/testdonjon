package dnd.gameobject.personnage.classe;

public class Magicien extends Classe
{
    @Override
    public int bonusCreation()
    {
        return 12;
    }

    @Override
    public String toString() {
        return "Magicien";
    }
}