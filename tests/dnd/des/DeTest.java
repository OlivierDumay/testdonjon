package dnd.des;
import org.junit.jupiter.api.Test;
import static dnd.des.De.lancerDe;
import static org.junit.jupiter.api.Assertions.*;

public class DeTest
{
    @Test
    void LancerValide()
    {
        System.out.println("Résultat lancer : " + lancerDe(1, 4));
    }
}
