import com.toedter.calendar.JMonthChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class recargaTren {
    private JPanel panelPrincipal;
    private JButton cancelarButton;
    private JButton recargarAbonoButton;
    private JPanel Header;
    private JPanel Botonera;
    private JPanel panelCentral;
    private JMonthChooser selectorMes;
    private JLabel LOGO;

    public recargaTren() {
        recargarAbonoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Recarga realizada con exito para el mes: " + (selectorMes.getMonth() + 1));
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opción = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la recarga?", "Confirmar cancelación", JOptionPane.YES_NO_OPTION);

                if (opción == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        selectorMes.setMonth(0);
        ImageIcon imagen = new ImageIcon("recursos/logo.jpg");
        LOGO.setIcon(imagen);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("recargaTren");
        frame.setContentPane(new recargaTren().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
