/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

/**
 *
 * @author MERRY
 */
public class VistaInfoPesaje extends javax.swing.JPanel {

    /**
     * Creates new form VistaInfoPesaje
     */
    public VistaInfoPesaje() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtInfoPesaje = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        txtInfoPesaje.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInfoPesaje.setForeground(new java.awt.Color(59, 123, 50));
        txtInfoPesaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtInfoPesaje.setText("<html>\n<body>\n    <table>\n        <tr>\n            <td style=\"text-align: right; font-weight: bold;\">Fecha de pesaje:</td>\n            <td></td>\n            <td style=\"text-align: right; font-weight: bold;\">Implante:</td>\n            <td></td>\n        </tr>\n        <tr>\n            <td style=\"text-align: right; font-weight: bold;\">Peso:</td>\n            <td></td>\n            <td style=\"text-align: right; font-weight: bold;\">Descornado:</td>\n            <td></td>\n        </tr>\n        <tr>\n            <td style=\"text-align: right; font-weight: bold;\">Destete:</td>\n            <td></td>\n            <td style=\"text-align: right; font-weight: bold;\">Hierro:</td>\n            <td></td>\n        </tr>\n        <tr>\n            <td style=\"text-align: right; font-weight: bold;\">Fecha de destete:</td>\n            <td></td>\n            <td style=\"text-align: right; font-weight: bold;\">Descripción:</td>\n            <td></td>\n        </tr>\n        <tr>\n            <td colspan=\"4\"><b>Notas:</b><br></td>\n        </tr>\n        <tr>\n            <td colspan=\"4\">\n                <table style=\"border-collapse: collapse;\">\n                    <tr style=\"background-color: #3B7B32; color: white;\">\n                        <td style=\"border: 1px solid #3B7B32;\">Medicamento</td>\n                        <td style=\"border: 1px solid #3B7B32;\">Cantidad</td>\n                        <td style=\"border: 1px solid #3B7B32;\">Unidad de medida</td>\n                    </tr>\n                    <tr style=\"background-color: #ededed;\">\n                        <td></td>\n                        <td></td>\n                        <td></td>\n                    </tr>\n                </table>\n            </td>\n        </tr>\n    </table>\n</body>\n</html>");
        txtInfoPesaje.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(txtInfoPesaje, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtInfoPesaje;
    // End of variables declaration//GEN-END:variables
}
