/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Utkarsh Pankaj
 */
public class NineBoxPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setContentPane(new PlayArea());
        frame.setSize(300, 300);
        frame.setVisible(true);
        
    }
    
}
