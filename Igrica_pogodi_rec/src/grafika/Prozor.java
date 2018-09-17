package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prozor extends JFrame {
    public String tajnaRec = "";
    public boolean pauz = true;
    private int duzinaReci = 4;
    private String rec = "";
    private int nivo = 1;
    private int poeni = 0;
    private int pauza = 1400;
    private JButton next;
    private JButton proba;
    private JButton start;
    private JLabel lblRec;
    private JLabel lblPoeni;
    private JLabel lblVreme;
    private JLabel opas;
    public JPanel donji=new JPanel();
    public JButton pauza3;
    public int vremen=12;
    public Prozor() {
        setSize(400, 400);
        setTitle("Vezba8");
        dodajKomponente();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void dodajKomponente() {
        getContentPane().setLayout(new BorderLayout());
        JPanel gornji = new JPanel();
        gornji.setLayout(new BorderLayout());
        lblVreme = new JLabel("Vreme:");
        opas = new JLabel("V");
        lblVreme.setFont(new Font("Serif", Font.BOLD, 17));
        lblVreme.setHorizontalAlignment(JLabel.LEFT);
        lblPoeni = new JLabel("Poeni:0");
        lblPoeni.setFont(new Font("Serif", Font.BOLD, 17));
        lblPoeni.setHorizontalAlignment(JLabel.RIGHT);
        JLabel level = new JLabel("Nivo:1");
        level.setFont(new Font("Serif", Font.BOLD, 17));
        level.setHorizontalAlignment(JLabel.CENTER);
        JPanel gg = new JPanel();
        gg.setLayout(new GridLayout(1, 3));
        gg.add(lblVreme);
        gg.add(level);
        gg.add(opas);
        gg.add(lblPoeni);
        gornji.add("North", gg);
        JLabel l3 = new JLabel("Tajna rec");
        l3.setFont(new Font("Serif", Font.BOLD, 17));
        l3.setHorizontalAlignment(JLabel.CENTER);
        gornji.add("Center", l3);
        lblRec = new JLabel("????");
        lblRec.setFont(new Font("Serif", Font.BOLD, 17));
        lblRec.setHorizontalAlignment(JLabel.CENTER);
        gornji.add("South", lblRec);
        getContentPane().add("North", gornji);
        JPanel centralni = new JPanel();
        centralni.setLayout(new GridLayout(4, 8));


        for (int i = 65; i < 91; i++) {
            JButton b = new JButton((char) i + "");
            centralni.add(b);

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    rec += e.getActionCommand();

                        if (rec.length() == duzinaReci) {
                            provera();
                        } else {
                            lblRec.setText(rec + "? \n Preostalo slova:" + (duzinaReci - rec.length()));
                        }
                    }

            });
        }
        getContentPane().add("Center", centralni);
        JPanel donji = new JPanel();
        donji.setLayout(new GridLayout(1, 2));

        start = new JButton("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level.setText("Nivo:" + nivo);
                lblPoeni.setText("Poeni:" + poeni);
                lblRec.setForeground(Color.BLACK);
                generisiRec(duzinaReci);
                lblRec.setText(tajnaRec);
                pokreniNit();
                start.setVisible(false);
            }
        });
        next = new JButton("NEXT");
        next.setVisible(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivo++;
                duzinaReci = nivo + 4;
                pauza += 200;
                level.setText("Nivo:" + nivo);
                lblRec.setText("");
                lblRec.setForeground(Color.BLACK);
                for (int i = 0; i < duzinaReci; i++) {
                    lblRec.setText(lblRec.getText() + "?");
                }
                start.setVisible(true);
                next.setVisible(false);
            }
        });
        donji.add(start);
        donji.add(next);
        getContentPane().add("South", donji);
    }

    private void pokreniNit() {
        new VremeNit(lblVreme, lblRec, pauza).start();
    }
    private void generisiRec(int br) {
        tajnaRec = "";
        for (int i = 0; i < br; i++) {
            tajnaRec += (char) ((int) (Math.random() * 26) + 63);
        }
    }

    public void provera() {
        lblRec.setText(rec);
        if (rec.equals(tajnaRec)) {
            lblRec.setForeground(Color.green);
            next.setVisible(true);
            poeni += tajnaRec.length() * 20;
            lblPoeni.setText("Poeni:" + poeni);
        } else {
            lblRec.setForeground(Color.red);
            lblRec.setText(rec + "  Izgubili ste, trazena rec:" + tajnaRec);
            nivo = 1;
            duzinaReci=4;
            poeni = 0;
            start.setVisible(true);
            next.setVisible(false);
        }
        VremeNit.setVreme(-1);

        rec = "";
    }


}
