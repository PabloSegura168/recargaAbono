import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Clase que representa una interfaz gráfica para la recarga de abonos de transporte.
 * Permite al usuario seleccionar un mes y año para realizar la recarga del abono de bus.
 * Incluye validaciones y funcionalidades extra para mejorar la experiencia de usuario.
 */
public class recargaBus extends JFrame { // Nombre de clase corregido

    // Array para convertir el índice del mes (0-11) a su nombre en español.
    private final String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    // Componentes de la interfaz
    private final JMonthChooser monthChooser;
    private final JButton recargarButton;

    public recargaBus() { // Constructor corregido para coincidir con el nombre de la clase
        // --- 1. Configuración de la ventana principal (JFrame) ---
        setTitle("Recarga de Abono de Transporte");
        setSize(450, 350); // Aumentamos la altura para el logo
        setLocationRelativeTo(null); // Centrar la ventana
        // Usamos DO_NOTHING_ON_CLOSE para manejar el cierre manualmente con un WindowListener
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // --- 2. Creación del panel principal y configuración del Layout ---
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda

        // --- 3. Creación y adición de componentes ---

        // Componente para el Logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        try {
            // 1. Carga la imagen original desde el archivo
            BufferedImage imagenOriginal = ImageIO.read(new File("recursos/logoBus.png"));

            // 2. Define la nueva altura y redimensiona la imagen manteniendo la proporción
            int nuevaAltura = 60; // <-- ¡Puedes cambiar este valor para hacerlo más grande o pequeño!
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(-1, nuevaAltura, Image.SCALE_SMOOTH);

            // 3. Crea el JLabel con la nueva imagen redimensionada
            JLabel logoLabel = new JLabel(new ImageIcon(imagenRedimensionada));
            panel.add(logoLabel, gbc);
        } catch (IOException e) {
            System.err.println("Error: No se pudo encontrar o cargar la imagen del logo en 'recursos/logoBus.png'");
        }

        // Etiqueta de instrucción
        gbc.gridx = 0;
        gbc.gridy = 1; // Bajamos una posición
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.anchor = GridBagConstraints.WEST; // Restaurar anclaje
        panel.add(new JLabel("Seleccione el mes para recargar su abono (año actual):"), gbc);

        // Selector de mes (JMonthChooser)
        monthChooser = new JMonthChooser();
        monthChooser.setLocale(new Locale("es", "ES"));
        gbc.gridx = 0;
        gbc.gridy = 2; // Bajamos una posición
        gbc.gridwidth = 2; // Ocupa el espacio de los dos componentes anteriores
        gbc.fill = GridBagConstraints.NONE; // Evita que el componente se estire
        gbc.anchor = GridBagConstraints.CENTER; // Centra el componente
        panel.add(monthChooser, gbc);

        // Botón de recarga
        recargarButton = new JButton("Recargar Abono");
        // Mejora 1: Tooltip en el botón
        recargarButton.setToolTipText("Haga clic para confirmar la recarga del abono seleccionado");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0; // Permite que la celda se expanda horizontalmente
        gbc.fill = GridBagConstraints.NONE;
        panel.add(recargarButton, gbc);
        gbc.weightx = 0.0; // Reiniciar para futuros componentes

        // --- 4. Lógica de los eventos (Action Listeners y Window Listeners) ---

        // Acción del botón "Recargar Abono"
        recargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos el mes del selector y el año actual del sistema
                int mesSeleccionado = monthChooser.getMonth(); // 0-11
                int anioActual = Calendar.getInstance().get(Calendar.YEAR);

                // Mejora 2: Validación para no recargar meses pasados
                Calendar hoy = Calendar.getInstance();
                int mesActual = hoy.get(Calendar.MONTH); // 0-11
                if (mesSeleccionado < mesActual) {
                    JOptionPane.showMessageDialog(recargaBus.this,
                            "No puede recargar un abono para un mes que ya ha pasado.",
                            "Mes no válido",
                            JOptionPane.WARNING_MESSAGE);
                    return; // Detiene la ejecución si la validación falla
                }

                // El tipo de transporte es fijo: Bus
                String tipoTransporte = "Bus";
                String nombreMes = meses[mesSeleccionado];
                String mensaje = String.format("Su abono de %s ha sido recargado para el mes de: %s de %d.",
                        tipoTransporte, nombreMes, anioActual);

                // Se muestra el diálogo de confirmación con un icono de información estándar.
                JOptionPane.showMessageDialog(recargaBus.this,
                        mensaje,
                        "Confirmación de Recarga",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Mejora 4: Confirmación al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(recargaBus.this,
                        "¿Está seguro de que desea salir de la aplicación?",
                        "Confirmar Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0); // Cierra la aplicación
                }
            }
        });

        // --- 5. Finalización de la configuración ---
        add(panel); // Añadir el panel al frame
    }

    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread (EDT) para seguridad en Swing
        SwingUtilities.invokeLater(() -> new recargaBus().setVisible(true));
    }
}
