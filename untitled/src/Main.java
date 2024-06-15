import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField pesoField;
    private JTextField alturaField;
    private JLabel resultadoLabel;

    public Main() {
        // ventanana
        setTitle("Calculadora de IMC");
        setSize(600, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(Color.LIGHT_GRAY);


        JLabel pesoLabel = new JLabel("Ingrese su peso kg:");
        pesoLabel.setForeground(Color.BLUE);
        panel.add(pesoLabel);

        pesoField = new JTextField();
        panel.add(pesoField);

        JLabel alturaLabel = new JLabel("Ingrese su altura:");
        alturaLabel.setForeground(Color.black);
        panel.add(alturaLabel);

        alturaField = new JTextField();
        panel.add(alturaField);

        // btn calcular
        JButton calcularButton = new JButton("Calcular IMC");
        calcularButton.setBackground(Color.pink);
        calcularButton.setForeground(Color.BLACK);
        calcularButton.addActionListener(new CalcularButtonListener());
        panel.add(calcularButton);

        // Crear etiqueta de resultado
        resultadoLabel = new JLabel("", JLabel.CENTER);
        resultadoLabel.setForeground(Color.RED);
        panel.add(resultadoLabel);

        // Añadir el panel a la ventana
        add(panel);
    }

    private class CalcularButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener los valores de los campos de texto
                double peso = Double.parseDouble(pesoField.getText());
                double altura = Double.parseDouble(alturaField.getText());

                double imc = calcularIMC(peso, altura);


                String clasificacion = clasificacionIMC(imc);

                // Mostrar el resultado
                resultadoLabel.setText(String.format("Su IMC es de: %.2f (%s)" , imc, clasificacion));
            } catch (NumberFormatException ex) {
                // Manejar errores de formato de número
                resultadoLabel.setText("Por favor, ingrese valores válidos.");
            }
        }
    }


    public static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    // operaciones para calcunar el peso
    public static String clasificacionIMC(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
