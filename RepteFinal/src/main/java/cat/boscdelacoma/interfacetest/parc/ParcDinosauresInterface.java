/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.boscdelacoma.interfacetest.parc;

import cat.boscdelacoma.interfacetest.parc.Dinosaure;

/**
 *
 * @author jonar
 */

public interface ParcDinosauresInterface {
    void afegirDinosaure(Dinosaure dinosaure);
    void canviarNomDinosaure(String nomAntic, String nomNou);
    void eliminarDinosaure(String nom);
    Dinosaure obtenirDinosaure(String nom);
    void llistarDinosaures();
}

