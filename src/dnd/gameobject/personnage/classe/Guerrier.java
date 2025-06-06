package dnd.gameobject.personnage.classe;

public class Guerrier extends Classe
{
    @Override
    public int bonusCreation()
    {
        return 20;
    }

    @Override
    public String toString() {
        return "Guerrier";
    }
}
