/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

import java.awt.Color;
import java.util.*;

/**
 *
 * @author Utkarsh Pankaj
 */
public class GameEngine {
    
    private Box[][] boxes;
    private Box emptyBox;
    private PlayArea playArea;

    GameEngine(PlayArea playArea) {
        this.playArea = playArea;
        boxes = getRandomBoxes();
    }
    
    public Box[][] getBoxes(){
        return boxes;
    }
    
    private boolean canMakeMove(int positionX, int positionY) {
        int differenceX = Math.abs(emptyBox.getPositionX() - positionX);
        int differenceY = Math.abs(emptyBox.getPositionY() - positionY);
        if (differenceX <= 1 && differenceY <= 1 && !(differenceX == 1 && differenceY == 1))
            return true;
        return false;
    }
    
    private boolean checkWinningStatus() {
        boolean hasWon = true;
        
        int num = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("" + num + "  " +boxes[i][j].getText().trim());
                try {
                    if (Integer.parseInt(boxes[i][j].getText().trim()) != num) {
                        hasWon = false;
                    }
                } catch (NumberFormatException e) {
                    hasWon  = false;
                    System.out.println("Empty String at " + i + " " + j);
                }
                num++;
                if(num==9) return hasWon;
            }
        }
        
        return hasWon;
    }
    
    public void makeMove(int positionX, int positionY) {
        if (canMakeMove(positionX, positionY)) {
            Box box = boxes[positionX][positionY];
            emptyBox.setText(box.getText());
            System.out.println("Clicked on : " + box.getText());
            emptyBox.setEnabled(true);
            emptyBox.setBackground(Color.white);
            box.setEnabled(false);
            box.setBackground(new Color(150, 150, 180));
            box.setText("");
            emptyBox = box;
            if (checkWinningStatus()) {
                System.out.println("You Win");
                playArea.showWinningDialog();
            }
        }
    }    

    private ArrayList<Box> getValidMoves(Box boxes[][]) {
        ArrayList<Box> validMoves = new ArrayList<Box>();
        int emptyBoxX = emptyBox.getPositionX();
        int emptyBoxY = emptyBox.getPositionY();
        for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                int x = emptyBoxX + i;
                int y = emptyBoxY + j;
                if(x < 3 && x >= 0 && y < 3 && y >= 0) {
                    validMoves.add(boxes[x][y]);
                }
            }
        }

        return validMoves;
    }
    
    private Box[][] getRandomBoxes() {
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(i);
        }
        
        Box[][] boxes = new Box[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int boxValue = list.get(3*i+j);
                boxes[i][j] = new Box(i, j, boxValue);
                if (boxValue == 0) {
                    boxes[i][j].setEnabled(false);
                    boxes[i][j].setText("");
                    emptyBox = boxes[i][j];
                    emptyBox.setBackground(new Color(150, 150, 180));
                }
            }
        }
        
        //time to randomize the boxes by making a random number of moves
        Random random = new Random();
        int minMovesCount = 200;
        int movesCount = minMovesCount + random.nextInt(1000);
        for (int i = 0; i < movesCount; i++) {
            ArrayList<Box> validMoves = getValidMoves(boxes);
            Box randomBox = validMoves.get(random.nextInt(validMoves.size()));
            makeMove(randomBox.getPositionX(), randomBox.getPositionY());
        }
        return boxes;
    }
}
