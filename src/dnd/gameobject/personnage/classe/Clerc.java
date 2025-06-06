package dnd.gameobject.personnage.classe;

public class Clerc extends Classe
{
    @Override
    public int bonusCreation()
    {
        return 16;
    }

    @Override
    public String toString() {
        return "Clerc";
    }
}