import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;
import java.text.*;
import  java.net.*;

public class serverr  implements ActionListener {
    JTextField text;
    JPanel a1;
    static DataOutputStream dout;

    static JFrame f = new JFrame();

    static Box vertical = Box.createVerticalBox();

    serverr() {
        f.setLayout(null);


        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(7, 94, 84));
        jPanel.setBounds(0, 0, 450, 70);
        jPanel.setLayout(null);
        f.add(jPanel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/icons/3.png"));
        Image i2 = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel jLabel = new JLabel(i3);
        jLabel.setBounds(5, 20, 25, 25);
        jPanel.add(jLabel);

        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);


        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/1.png"));
        Image i4 = imageIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JLabel jLabel1 = new JLabel(i5);
        jLabel1.setBounds(40, 10, 50, 50);
        jPanel.add(jLabel1);
        f.setUndecorated(true);


        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 110, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setForeground(Color.WHITE);

        send.addActionListener(this);
      f.add(send);


        JLabel jLabel3 = new JLabel();
        jLabel3.setText("Server");
        JLabel jLabel20 = new JLabel("Online");
        jLabel20.setBounds(100, 44, 70, 15);
        jLabel20.setFont(new Font("Serif", Font.PLAIN, 15));
        jLabel20.setForeground(Color.WHITE);

//        jLabel3.setText(String.valueOf(Color.WHITE));
        jLabel3.setBounds(100, 20, 70, 20);
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setFont(new Font("Serif", Font.PLAIN, 20));
        jPanel.add(jLabel3);
        jPanel.add(jLabel20);


        ImageIcon imageIcon3 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/video.png"));
        Image i6 = imageIcon3.getImage().getScaledInstance(18, 30, Image.SCALE_DEFAULT);
        ImageIcon i7 = new ImageIcon(i6);
        JLabel jLabel2 = new JLabel(i7);
        jLabel2.setBounds(330, 20, 30, 20);
        jPanel.add(jLabel2);

        ImageIcon imageIcon4 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/phone.png"));
        Image i8 = imageIcon4.getImage().getScaledInstance(18, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel jLabel4 = new JLabel(i9);
        jLabel4.setBounds(369, 20, 30, 20);
        jPanel.add(jLabel4);

        ImageIcon imageIcon41 = new ImageIcon(ClassLoader.getSystemResource("icons/icons/3icon.png"));
        Image i81 = imageIcon41.getImage().getScaledInstance(8, 30, Image.SCALE_DEFAULT);
        ImageIcon i91 = new ImageIcon(i81);
        JLabel jLabel41 = new JLabel(i91);
        jLabel41.setBounds(400, 20, 30, 20);
        jPanel.add(jLabel41);


        f.setSize(450, 700);
        f.  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);

    }

//    public void actionPerformed(ActiveEvent ae){

//        String out = text.getText();
//        // is used to get text from the textField
//
//        JLabel output =  new JLabel(out);
//
//        JPanel p2 =  new JPanel();
//
//
//        p2.add(output);
//
//
//        a1.setLayout(new BorderLayout());
//
//        JPanel right = new JPanel(new BorderLayout());
//        right.add(p2, BorderLayout.LINE_END);
//
//        vertical.add(Box.createVerticalStrut(15));
//
//
//        a1.add(vertical,BorderLayout.PAGE_START);
//
//        repaint();
//        invalidate();
//        validate();

    //    }
    public static void main(String[] args) {
        new serverr();

        try{

            ServerSocket skt = new ServerSocket(6001);
            while(true)
            {
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout= new DataOutputStream(s.getOutputStream());

                while(true)
                {
                    String msg = din.readUTF();
                    JPanel panel = fromatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);

                    vertical.add(left);
                    f.validate();
                }
            }

        }catch(Exception e)
        {
                e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
try{
        String out = text.getText();
        // is used to get text from the textField

        JLabel output = new JLabel(out);

        JPanel p2 = fromatLabel(out);


        a1.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);  ///  whre  line is end
        vertical.add(right);

        vertical.add(Box.createVerticalStrut(15));    // space between between two vertical


        a1.add(vertical, BorderLayout.PAGE_START);

        dout.writeUTF(out);

        text.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
    }catch(Exception ae)
{
    ae.printStackTrace();
}
    }

    public static JPanel fromatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);


        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


        JLabel time = new JLabel();
        time.setText((sdf.format(cal.getTime())));

        panel.add(time);

        return panel;
    }
}






