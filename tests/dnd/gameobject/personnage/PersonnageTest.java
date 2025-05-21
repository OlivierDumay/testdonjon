package dnd.gameobject.personnage;

import dnd.Asset;
import dnd.gameobject.*;
import dnd.gameobject.personnage.Personnage;
import dnd.gameobject.personnage.classe.EnumClasse;
import dnd.gameobject.personnage.race.EnumRace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest
{
    @Test
    void PersonnageValide()
    {
        assertDoesNotThrow(()->{
            Asset p1 = new Personnage("test", EnumClasse.GUERRIER, EnumRace.HUMAIN);
        });
    }

    @Test
    void PersonnageNomVide()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            Asset p1 = new Personnage("", EnumClasse.GUERRIER, EnumRace.HUMAIN);
        });
    }

    @Test
    void PersonnageNomNull()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            Asset p1 = new Personnage(null, EnumClasse.GUERRIER, EnumRace.HUMAIN);
        });
    }

    /*

    // Test par définition impossible car impossible de passer autre chose qu'un membre de l'enum
    @Test
    void PersonnageClasseInexistante()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            Asset p1 = new Personnage("test", EnumClasse.ROUBLARD, EnumRace.HUMAIN);
        });
    }
    */

    
}
