/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nineboxpuzzle;

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
            box.setEnabled(false);
            box.setText("");
            emptyBox = box;
            if (checkWinningStatus()) {
                System.out.println("You Win");
                playArea.showWinningDialog();
            }
        }
    }
    
    private Box[][] getRandomBoxes() {
        Random random = new Random();
        HashMap<Integer, Integer> integers = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            int temp = random.nextInt();
            integers.put(temp, i);
            list.add(temp);
        }
        
        Collections.sort(list);
        
        Box[][] boxes = new Box[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int boxValue = integers.get(list.get(i+j*3));
                boxes[i][j] = new Box(i, j, boxValue);
                if (boxValue == 0) {
                    boxes[i][j].setEnabled(false);
                    boxes[i][j].setText("");
                    emptyBox = boxes[i][j];
                }
            }
        }
        return boxes;
    }
}
