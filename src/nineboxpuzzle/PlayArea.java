/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Utkarsh Pankaj
 */
public class PlayArea extends JPanel {
    private GameEngine myGameEngine;
    
    PlayArea() {
        myGameEngine = new GameEngine(this);
        initializeComponents();
    }
    
    private void initializeComponents() {
        setLayout(new GridLayout(3, 3));
        Box[][] boxes = myGameEngine.getBoxes();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                add(boxes[i][j]);
                boxes[i][j].addActionListener(new EventHandler(myGameEngine, i, j));
            }
        }
        setVisible(true);
    }

    void showWinningDialog() {
        JOptionPane.showMessageDialog(this, "You Won!");
    }
}
