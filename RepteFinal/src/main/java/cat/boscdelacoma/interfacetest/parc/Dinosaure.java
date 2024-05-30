package cat.boscdelacoma.interfacetest.parc;

public class Dinosaure {
    private int id;
    private String nom;
    private int edat;
    private String tipus;
    private double pes;
    private int parcId;
    public Dinosaure(int id, String nom, int edat, String tipus, double pes, int parcId) {
        this.id = id;
        this.nom = nom;
        this.edat = edat;
        this.tipus = tipus;
        this.pes = pes;
        this.parcId = parcId;
    }
    
    public Dinosaure(String nom, int edat, String tipus, double pes) {
        this.nom = nom;
        this.edat = edat;
        this.tipus = tipus;
        this.pes = pes;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public int getParcId() {
        return parcId;
    }

    public void setParcId(int parcId) {
        this.parcId = parcId;
    }

   
}
