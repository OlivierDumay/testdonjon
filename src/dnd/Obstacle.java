package dnd;

public class Obstacle implements Asset{

    private String m_etiquette;

    public Obstacle()
    {
        this.m_etiquette = "[ ]";
    }

    @Override
    public String getEtiquette()
    {
        return m_etiquette;
    }


}
