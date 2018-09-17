package grafika;
import javax.swing.*;
import java.awt.*;

public class VremeNit extends Thread {
    public  JLabel lblVreme;
    private JLabel lblRec;
    public static int vreme;



    public VremeNit(JLabel l, JLabel l2, int pauza) {
        this.lblVreme = l;
        this.lblRec = l2;
        vreme = 25;

    }


    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lblRec.setText("Unesite trazenu rec");

        while (vreme > 0  ) {
            lblVreme.setText("Vreme:" + vreme--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (vreme == 0) {

            lblVreme.setText("Vreme isteklo!!");
            lblVreme.setForeground(Color.red);


        } else {
            lblVreme.setText("Vreme:"+ vreme);
        }

    }


    public static void setVreme(int brS) {
        vreme = brS;
    }

}
