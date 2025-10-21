

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class tic extends JFrame implements ActionListener{
    private JButton[] buttons=new JButton[9];
    private boolean playerX =true;
    private ImageIcon xIcon,oIcon;

    public tic(){
        setTitle("Tic Tack Toe");
        setSize(400,400);
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //here add some icons
      //  xIcon=new ImageIcon(getClass().getResource("/games/asset/xii.png"));
        //oIcon=new ImageIcon(getClass().getResource("/games/asset/Oreva.png"));

        for (int i = 0; i < 9; i++) {
            buttons[i]=new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));

            buttons[i].setFocusPainted(false);
buttons[i].setBackground(Color.WHITE);//new design aadded
buttons[i].setContentAreaFilled(true);
buttons[i].setOpaque(true);

//effects
buttons[i].addMouseListener(new java.awt.event.MouseAdapter(){
public void mouseEntered(java.awt.event.MouseEvent evt){
    JButton b=(JButton)evt.getSource();
if(b.getText().equals(""))b.setBackground(Color.LIGHT_GRAY);
}
public void mouseExited(java.awt.event.MouseEvent evt) {
JButton b=(JButton)
evt.getSource();

if(b.getText().equals(""))b.setBackground(Color.WHITE);

}
});
//hover effect is over here

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
// changing here
    if (!clicked.getText().equals(""))return; 
      clicked.setText(playerX ? "X":"O");
      clicked.setForeground(playerX?Color.RED:Color.BLUE);
   // clicked.setEnabled(false);
    
   // if (clicked.getIcon() != null)return;
    clicked.setIcon(playerX ? xIcon:oIcon);


    if (checkWinner()) {
        JOptionPane.showMessageDialog(this, (playerX ? "X":"O")+ " Wins!");
        highLightwinner();
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
       //if( buttons[c[0]].getIcon() != null && buttons[c[0]].getIcon()==buttons[c[1]].getIcon() && buttons[c[1]].getIcon() == buttons[c[2]].getIcon()){
            WinningCombo=c;
        return true;

        }
    }
    return false;
}

//winning combo
private int[] WinningCombo =null;
private void highLightwinner(){
    if(WinningCombo!=null ){
        for(int i :WinningCombo){
            buttons[i].setBackground(Color.GREEN);
            buttons[i].setOpaque(true);
            buttons[i].setContentAreaFilled(true);
        }
    }
} 

private boolean isBoardFull(){
    for (JButton b : buttons) {
        if(b.getText().equals(""))return false;
    }
    return true;
}
private void resetGame(){
    
    /*   for (JButton b : buttons) {
          b.setText("");
          b.setEnabled(true);
      }

    playerX=true;
    */
    Timer t=new Timer(1000, e ->{
        for(JButton b : buttons){
          //  b.setIcon(null);
          b.setText("");
            b.setBackground(Color.WHITE);
            b.setOpaque(true);
b.setContentAreaFilled(true);
        }
        playerX=true;
        WinningCombo=null;
    });
    t.setRepeats(false);
    t.start();
}
}



