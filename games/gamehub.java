package games;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class gamehub extends JFrame{
public gamehub() {
setTitle("Java Game Hub");
setSize(400,400);

setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new GridLayout(5,1,10,10));

JButton btn1 =new JButton("Tic Tack Toe");
JButton btn2 = new JButton("Snake Game");





btn1.addActionListener(e-> new tic().startGame());

add(btn1);
setVisible(true);

}

public static void main(String[] args) {
new gamehub();
}
}