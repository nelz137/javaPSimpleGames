
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class gamehub extends JFrame{
public gamehub() {
setTitle("Java Simple Games");
setSize(500,600);

setDefaultCloseOperation(EXIT_ON_CLOSE);
setLocationRelativeTo(null);
//setLayout(new GridLayout(5,1,10,10));
JPanel panel=new JPanel();
panel.setLayout(new GridBagLayout());
panel.setBackground(new Color(30,30,30));

GridBagConstraints gbc =new GridBagConstraints();

gbc.insets=new Insets(15, 0, 15, 0);
gbc.fill= GridBagConstraints.HORIZONTAL;

JLabel Titlle= new JLabel("Simple Games",SwingConstants.CENTER);
Titlle.setFont(new Font("Arial",Font.BOLD,32));
Titlle.setForeground(Color.WHITE);
gbc.gridx=0;
gbc.gridy=0;
panel.add(Titlle,gbc);



JButton btn1 =new JButton("Tic Tack Toe");
JButton btn2 = new JButton("Ping Pong Ball");
JButton btn3 = new JButton("Snake Game");
JButton btn4 = new JButton("Bro Game");


gbc.gridy=1;panel.add(btn1,gbc);
gbc.gridy=2;panel.add(btn2,gbc);
gbc.gridy=3;panel.add(btn3,gbc);
gbc.gridy=4;panel.add(btn4,gbc);
//gbc.gridy=5;panel.add(btn2,gbc);



btn1.addActionListener(e-> new tic().startGame());
btn2.addActionListener(e->new PongGame().startGame());
//btn2.addActionListener(e->new snake().startGame());

add(panel);
setVisible(true);

}
private JButton createMenuButton (String text){
JButton button =new JButton(text);
button.setFont(new Font("Arial",Font.BOLD,20));
button.setForeground(Color.WHITE);
button.setBackground(new Color(70,130,180));
button.setFocusPainted(false);
button.setBorderPainted(false);
setPreferredSize(new Dimension(250,50));
button.setCursor(new Cursor(Cursor.HAND_CURSOR));

button.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt){
        button.setBackground(new Color(100,149,237));

    }
    public void mouseExited(java.awt.event.MouseEvent evt){
        button.setBackground(new Color(70,130,180));
    }

});
return button;
}



public static void main(String[] args) {
new gamehub();
}
}