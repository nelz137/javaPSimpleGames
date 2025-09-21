package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class tic extends JFrame implements ActionListener{
    private JButton[] buttons=new JButton[9];
    private boolean playerX =true;

    public tic(){
        setTitle("Tic Tack Toe");
        setSize(400,400);
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        for (int i = 0; i < 9; i++) {
            buttons[i]=new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));

            buttons[i].addActionListener(this);
            add(buttons[i]);

        }

setVisible(true);
        

    }

public void startGame(){
    new tic();
}

public void actionPerformed(ActionEvent e){
    JButton clicked=(JButton)e.getSource();

    if (!clicked.getText().equals(""))return; 
       clicked.setText(playerX ? "X":"O");
    clicked.setEnabled(false);

    if (checkWinner()) {
        
JOptionPane.showMessageDialog(this, (playerX ? "X":"O")+ " Wins!");
resetGame();
    }
else if (isBoardFull()) {
    JOptionPane.showMessageDialog(this, "It's a Draw!");
resetGame();
}
else{
    playerX=!playerX;
}
    
}

private boolean checkWinner (){
    int [][] combos={
{0,1,2} , {3,4,5}, {6,7,8} ,

{0,3,6} , {1,4,7}, {2,5,8},

{0,4,8}, {2,4,6}



    };


    for (int[] c : combos) {
        if (!buttons[c[0]].getText().equals("")&& buttons[c[0]].getText().equals(buttons[c[1]].getText()) && buttons[c[1]].getText().equals(buttons[c[2]].getText())) {
            
            return true;

        }
    }
    return false;
}
private boolean isBoardFull(){
    for (JButton b : buttons) {
        if(b.getText().equals(""))return false;
    }
    return true;
}
private void resetGame(){
    for (JButton b : buttons) {
        b.setText("");
        b.setEnabled(true);
    }

    playerX=true;
}
}



