/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadulp24;

import accesoadatos.Conexion;
import vistas.MenuPrincipal;

/**
 *
 * @author Usuario
 */
public class UniversidadULP24 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion.getConexion();
        MenuPrincipal vistas=new MenuPrincipal();
        vistas.show();
    }
    
}
