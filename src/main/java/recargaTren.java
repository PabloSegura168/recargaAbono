import com.toedter.calendar.JMonthChooser; // Componente para seleccionar meses del calendario
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase recargaTren.
 * Esta clase implementa una interfaz gráfica en Java Swing para simular la recarga
 * de un abono de transporte mensual. Incluye un selector de mes, botones de acción
 * y un logotipo visual.
 *
 * @PabloSegura
 */
public class recargaTren {
    // Paneles principales de la interfaz
    private JPanel panelPrincipal;
    private JPanel Header;
    private JPanel Botonera;
    private JPanel panelCentral;

    // Componentes gráficos
    private JButton cancelarButton;
    private JButton recargarAbonoButton;
    private JMonthChooser selectorMes; // Selector de mes del componente JCalendar
    private JLabel LOGO; // Etiqueta donde se muestra el logotipo

    /**
     * Constructor de la clase recargaTren.
     * Inicializa los componentes y define la lógica de los botones.
     */
    public recargaTren() {

        // Acción del botón "Recargar abono"
        recargarAbonoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra un mensaje confirmando la recarga para el mes seleccionado
                JOptionPane.showMessageDialog(null,
                        "Recarga realizada con éxito para el mes: " + (selectorMes.getMonth() + 1));
            }
        });

        // Acción del botón "Cancelar"
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Diálogo de confirmación para salir del programa
                int opción = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea cancelar la recarga?",
                        "Confirmar cancelación",
                        JOptionPane.YES_NO_OPTION
                );

                // Si el usuario selecciona "Sí", el programa se cierra completamente
                if (opción == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Se inicializa el selector de mes en enero (posición 0)
        selectorMes.setMonth(0);

        // Carga del logotipo desde la carpeta 'recursos'
        ImageIcon imagen = new ImageIcon("recursos/logoTren.jpg");
        LOGO.setIcon(imagen);
    }

    /**
     * Método principal que lanza la aplicación.
     * Crea la ventana principal y establece su contenido.
     *
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // Creación del marco principal de la aplicación
        JFrame frame = new JFrame("recargaTren");

        // Se asigna el panel principal al contenido de la ventana
        frame.setContentPane(new recargaTren().panelPrincipal);

        // Se define la acción al cerrar la ventana (finalizar el programa)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajusta el tamaño de la ventana según sus componentes
        frame.pack();

        // Hace visible la ventana
        frame.setVisible(true);
    }
}
