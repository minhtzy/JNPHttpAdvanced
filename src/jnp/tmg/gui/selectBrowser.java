/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnp.tmg.gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiamo
 */
public class selectBrowser extends javax.swing.JFrame {

    Browser browser;

    /**
     * Creates new form selectBrowser
     */

    public selectBrowser(Browser browser) {
        this.browser = browser;
        initComponents();
        this.setVisible(true);
    }

    private selectBrowser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputTxt = new javax.swing.JTextField();
        viewBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaResult = new javax.swing.JTextArea();
        choseCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nhập từ khóa");

        viewBtn.setText("Xem");
        viewBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewBtnMouseClicked(evt);
            }
        });

        AreaResult.setColumns(20);
        AreaResult.setRows(5);
        jScrollPane1.setViewportView(AreaResult);

        choseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "tagName", "class", "name", "xpath" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(inputTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(choseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewBtn)
                .addContainerGap(127, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewBtn)
                            .addComponent(choseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Trích xuất dữ liệu", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMouseClicked
        // TODO add your handling code here:
        DOMDocument document = browser.getDocument();

        String incombo = choseCombo.getSelectedItem().toString();
        String inTxt = inputTxt.getText();
        if (incombo == "id") {
          DOMElement element = document.findElement(By.id(inTxt));
          AreaResult.setText("");
          Map<String, String> attributes = element.getAttributes();
                AreaResult.append("--------------------\n");
                AreaResult.append(element.getTextContent() + "\n");
                for (String attrName : attributes.keySet()) {
                    AreaResult.append(attrName + " = " + attributes.get(attrName) + "\n");
                }
                
        } else if (incombo == "tagName") {
            
            List<DOMElement> elements = document.findElements(By.tagName(inTxt));
            AreaResult.setText("");
            for (int i = 0; i < elements.size(); i++) {
                Map<String, String> attributes = elements.get(i).getAttributes();
                AreaResult.append("--------------------\n");
                AreaResult.append(elements.get(i).getTextContent() + "\n");
                for (String attrName : attributes.keySet()) {
                    AreaResult.append(attrName + " = " + attributes.get(attrName) + "\n");
                }
            }
        } else if (incombo == "class"){
            List<DOMElement> elements = document.findElements(By.className(inTxt));
            AreaResult.setText("");
            for (int i = 0; i < elements.size(); i++) {
                Map<String, String> attributes = elements.get(i).getAttributes();
                AreaResult.append("--------------------\n");
                AreaResult.append(elements.get(i).getTextContent() + "\n");
                for (String attrName : attributes.keySet()) {
                    AreaResult.append(attrName + " = " + attributes.get(attrName) + "\n");
                }
            }
        } else if (incombo == "name"){
            List<DOMElement> elements = document.findElements(By.name(inTxt));
            AreaResult.setText("");
            for (int i = 0; i < elements.size(); i++) {
                Map<String, String> attributes = elements.get(i).getAttributes();
                AreaResult.append("--------------------\n");
                AreaResult.append(elements.get(i).getTextContent() + "\n");
                for (String attrName : attributes.keySet()) {
                    AreaResult.append(attrName + " = " + attributes.get(attrName) + "\n");
                }
            }
        } else if (incombo == "xpath"){
            List<DOMElement> elements = document.findElements(By.xpath(inTxt));
            AreaResult.setText("");
            for (int i = 0; i < elements.size(); i++) {
                Map<String, String> attributes = elements.get(i).getAttributes();
                AreaResult.append("--------------------\n");
                AreaResult.append(elements.get(i).getTextContent() + "\n");
                for (String attrName : attributes.keySet()) {
                    AreaResult.append(attrName + " = " + attributes.get(attrName) + "\n");
                }
            }
        }


    }//GEN-LAST:event_viewBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(selectBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectBrowser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectBrowser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaResult;
    private javax.swing.JComboBox<String> choseCombo;
    private javax.swing.JTextField inputTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}