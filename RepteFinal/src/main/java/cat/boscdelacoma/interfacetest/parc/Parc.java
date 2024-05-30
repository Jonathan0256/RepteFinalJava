package cat.boscdelacoma.interfacetest.parc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parc {
    private String nom;
    private List<Dinosaure> dinosaures;

    public Parc(String nom) {
        this.nom = nom;
        this.dinosaures = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Dinosaure> getDinosaures() {
        return dinosaures;
    }

    public void afegirDinosaure(Dinosaure dinosaure) {
        this.dinosaures.add(dinosaure);
    }

    public void eliminarDinosaure(String nom) {
        dinosaures.removeIf(dinosaure -> dinosaure.getNom().equals(nom));
    }

    public void llistarDinosaures() {
        dinosaures.forEach(dinosaure -> System.out.println(dinosaure.getNom()));
    }

    public boolean canviarNomDinosaure(String currentName, String newName) {
        Optional<Dinosaure> dinosaureOpt = dinosaures.stream()
                .filter(dinosaure -> dinosaure.getNom().equals(currentName))
                .findFirst();

        if (dinosaureOpt.isPresent()) {
            Dinosaure dinosaure = dinosaureOpt.get();
            dinosaure.setNom(newName);
            return true;
        }
        return false;
    }
}
