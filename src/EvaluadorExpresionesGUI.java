import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluadorExpresionesGUI {

    private JFrame frame;
    private JTextField inputField;
    private JLabel postfijaLabel;
    private JLabel resultadoLabel;

    public EvaluadorExpresionesGUI() {
        frame = new JFrame("Evaluador de Expresiones");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        inputField = new JTextField();
        JButton convertirButton = new JButton("Convertir a Postfija");
        JButton evaluarButton = new JButton("Evaluar");
        postfijaLabel = new JLabel("Notación postfija: ");
        resultadoLabel = new JLabel("Resultado: ");

        frame.add(inputField);
        frame.add(convertirButton);
        frame.add(evaluarButton);
        frame.add(postfijaLabel);
        frame.add(resultadoLabel);

        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expresion = inputField.getText();
                if (!ExpresionUtils.parentesisBalanceados(expresion)) {
                    JOptionPane.showMessageDialog(frame, "Los paréntesis no están balanceados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String postfija = ExpresionUtils.infijaAPostfija(expresion);
                postfijaLabel.setText("Notación postfija: " + postfija);
            }
        });

        evaluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postfija = postfijaLabel.getText().replace("Notación postfija: ", "");
                if (postfija.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Primero convierta la expresión a postfija.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int resultado = ExpresionUtils.evaluarPostfija(postfija);
                resultadoLabel.setText("Resultado: " + resultado);
            }
        });

        frame.setVisible(true);
    }
}
