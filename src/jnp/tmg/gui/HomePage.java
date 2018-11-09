/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnp.tmg.gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jnp.tmg.client.HttpRequester;
import jnp.tmg.modules.http.ContentType;
import jnp.tmg.modules.http.Cookie;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.utils.BodyBuilder;
import jnp.tmg.utils.JsonUtils;
import jnp.tmg.utils.XMLUtils;

/**
 *
 * @author minht
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        loadDefaultConfig();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jMenu1 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        method_combobox = new javax.swing.JComboBox<>();
        url_string = new javax.swing.JTextField();
        SubmitButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        RequestPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        HeaderTable = new javax.swing.JTable();
        AddRowHeaderButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        radioUrlencoded = new javax.swing.JRadioButton();
        radioRaw = new javax.swing.JRadioButton();
        requestContentType = new javax.swing.JComboBox<>();
        urlencodePane = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        rawPane = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        BodyTable = new javax.swing.JTable();
        AddRowBodyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblResponseStatus = new javax.swing.JLabel();
        ResponsePane = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HeaderResponse = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        BodyResponse = new javax.swing.JTextArea();
        ResponeContentType = new javax.swing.JComboBox<>();
        btnPreviewHtml = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CookiesTable = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        method_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GET", "POST", "PUT", "DELETE", "HEAD" }));

        url_string.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                url_stringActionPerformed(evt);
            }
        });

        SubmitButton.setText("Send");
        SubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SubmitButtonMouseClicked(evt);
            }
        });
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Params");

        RequestPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Type");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Auth", "Basic Auth", "OAuth 1.0", "OAuth 2.0" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(98, 98, 98)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(728, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(239, Short.MAX_VALUE))
        );

        RequestPane.addTab("Authorization", jPanel1);

        HeaderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Boolean(true), null, null}
            },
            new String [] {
                "", "Key", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        HeaderTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        HeaderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(HeaderTable);
        if (HeaderTable.getColumnModel().getColumnCount() > 0) {
            HeaderTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            HeaderTable.getColumnModel().getColumn(1).setPreferredWidth(405);
            HeaderTable.getColumnModel().getColumn(2).setPreferredWidth(405);
        }

        AddRowHeaderButton.setText("+");
        AddRowHeaderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddRowHeaderButtonMouseClicked(evt);
            }
        });
        AddRowHeaderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRowHeaderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddRowHeaderButton)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(AddRowHeaderButton)
                .addContainerGap(226, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        RequestPane.addTab("Header", jPanel2);

        buttonGroup1.add(radioUrlencoded);
        radioUrlencoded.setSelected(true);
        radioUrlencoded.setText("x-www-form-urlencoded");

        buttonGroup1.add(radioRaw);
        radioRaw.setText("raw");

        requestContentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JSON(application/json)", "XML(text/xml)", "XML(application/xml)", "HTML(text/html)", "JAVASCRIPT(application/javascript)", "TEXT(text/plain)" }));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, radioRaw, org.jdesktop.beansbinding.ELProperty.create("${selected}"), requestContentType, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, radioUrlencoded, org.jdesktop.beansbinding.ELProperty.create("${selected}"), urlencodePane, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        urlencodePane.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout urlencodePaneLayout = new javax.swing.GroupLayout(urlencodePane);
        urlencodePane.setLayout(urlencodePaneLayout);
        urlencodePaneLayout.setHorizontalGroup(
            urlencodePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(urlencodePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
        );
        urlencodePaneLayout.setVerticalGroup(
            urlencodePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(urlencodePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, radioRaw, org.jdesktop.beansbinding.ELProperty.create("${selected}"), rawPane, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        BodyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Boolean(true), null, null}
            },
            new String [] {
                "", "Key", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        BodyTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        BodyTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BodyTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(BodyTable);
        if (BodyTable.getColumnModel().getColumnCount() > 0) {
            BodyTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            BodyTable.getColumnModel().getColumn(1).setPreferredWidth(405);
            BodyTable.getColumnModel().getColumn(2).setPreferredWidth(405);
        }

        AddRowBodyButton.setText("+");
        AddRowBodyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddRowBodyButtonMouseClicked(evt);
            }
        });
        AddRowBodyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRowBodyButtonActionPerformed(evt);
            }
        });

        rawPane.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rawPane.setLayer(AddRowBodyButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rawPaneLayout = new javax.swing.GroupLayout(rawPane);
        rawPane.setLayout(rawPaneLayout);
        rawPaneLayout.setHorizontalGroup(
            rawPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rawPaneLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddRowBodyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        rawPaneLayout.setVerticalGroup(
            rawPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rawPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(rawPaneLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(AddRowBodyButton)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(radioUrlencoded)
                .addGap(18, 18, 18)
                .addComponent(radioRaw)
                .addGap(18, 18, 18)
                .addComponent(requestContentType, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rawPane)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(urlencodePane)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioUrlencoded)
                    .addComponent(radioRaw)
                    .addComponent(requestContentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rawPane)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(urlencodePane)
                    .addContainerGap()))
        );

        RequestPane.addTab("Body", jPanel3);

        jLabel1.setText("Response");

        jLabel2.setText("Status:");

        HeaderResponse.setColumns(20);
        HeaderResponse.setRows(5);
        jScrollPane3.setViewportView(HeaderResponse);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        ResponsePane.addTab("Header", jPanel5);

        BodyResponse.setColumns(20);
        BodyResponse.setRows(5);
        jScrollPane4.setViewportView(BodyResponse);

        ResponeContentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "XML", "HTML", "JSON", "RAW" }));
        ResponeContentType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ResponeContentTypeItemStateChanged(evt);
            }
        });
        ResponeContentType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ResponeContentTypePropertyChange(evt);
            }
        });

        btnPreviewHtml.setText("Preview");
        btnPreviewHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewHtmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResponeContentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPreviewHtml)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResponeContentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreviewHtml))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ResponsePane.addTab("Body", jPanel6);

        CookiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Key", "Value", "Domain", "Path", "Expires", "HttpOnly", "Sercure"
            }
        ));
        CookiesTable.setRowHeight(40);
        CookiesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(CookiesTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ResponsePane.addTab("Cookies", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RequestPane)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(method_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(url_string)
                        .addGap(18, 18, 18)
                        .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ResponsePane, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(lblResponseStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(method_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(url_string, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RequestPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblResponseStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResponsePane, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblResponseStatus.getAccessibleContext().setAccessibleName("lblResponseStatus");

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void url_stringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_url_stringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_url_stringActionPerformed

    private void SubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubmitButtonMouseClicked
        // TODO add your handling code here:

        resetRespones();
        String url = url_string.getText();
        String method = method_combobox.getSelectedItem().toString();
        if (null == method) {
            JOptionPane.showMessageDialog(null, "Select Method", "Method", ERROR);
            return;
        }
        System.out.println(url + method);
//         String[] key = new String[10];
//         String[] value = new String[10];

        try {
            requester = new HttpRequester(new URI(url));
            requester.setRequestHeaders(this.getRequestHeaders());
            requester.setBody(getRequestBody());

            switch (method) {
                case "GET":
                    requester.sendGet();
                    break;
                case "POST":
                    requester.sendPost();
                    break;
                case "PUSH":
                    requester.sendPut();
                    break;
                case "DELETE":
                    requester.sendDelete();
                    break;
                case "HEAD":
                    requester.sendHead();
                    break;
                default:
                    break;
            }

            loadResponseBody();
            loadResponseHeader();
            loadResponseCookies();

        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);

        } catch (URISyntaxException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "URL syntax not correct!", "Error", ERROR);
        } catch (NullPointerException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_SubmitButtonMouseClicked

    private void AddRowBodyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRowBodyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRowBodyButtonActionPerformed

    private void AddRowBodyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddRowBodyButtonMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) BodyTable.getModel();
        model.addRow(new Object[]{true, "", ""});
    }//GEN-LAST:event_AddRowBodyButtonMouseClicked

    private void AddRowHeaderButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddRowHeaderButtonMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) HeaderTable.getModel();
        model.addRow(new Object[]{true, "", ""});
    }//GEN-LAST:event_AddRowHeaderButtonMouseClicked

    private void AddRowHeaderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRowHeaderButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRowHeaderButtonActionPerformed

    private void ResponeContentTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ResponeContentTypePropertyChange
        // TODO add your handling code here:


    }//GEN-LAST:event_ResponeContentTypePropertyChange

    private void ResponeContentTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ResponeContentTypeItemStateChanged
        // TODO add your handling code here:
        
        String bodyResponse = requester.getResponseBody();
        String contentType = ResponeContentType.getSelectedItem().toString();
        if (null == contentType) {
            ResponeContentType.setSelectedItem("RAW");
            //BodyResponse.setText(bodyResponse);

        } else {
            String parseData;
            switch (contentType) {
                case "HTML":
                case "XML": {
                    parseData = XMLUtils.prettyXMLJsoup(bodyResponse);
                }
                break;
                case "JSON": {
                    parseData = JsonUtils.prettyJson(bodyResponse);
                }
                break;
                case "Text":
                case "RAW":
                default:
                    parseData = bodyResponse;
                    break;
            }
            BodyResponse.setText("");
            BodyResponse.append(parseData);
        }

    }//GEN-LAST:event_ResponeContentTypeItemStateChanged

    private void btnPreviewHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewHtmlActionPerformed
        // TODO add your handling code here:
        previewBrowser();
    }//GEN-LAST:event_btnPreviewHtmlActionPerformed

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddRowBodyButton;
    private javax.swing.JButton AddRowHeaderButton;
    private javax.swing.JTextArea BodyResponse;
    private javax.swing.JTable BodyTable;
    private javax.swing.JTable CookiesTable;
    private javax.swing.JTextArea HeaderResponse;
    private javax.swing.JTable HeaderTable;
    private javax.swing.JTabbedPane RequestPane;
    private javax.swing.JComboBox<String> ResponeContentType;
    private javax.swing.JTabbedPane ResponsePane;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JButton btnPreviewHtml;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblResponseStatus;
    private javax.swing.JComboBox<String> method_combobox;
    private javax.swing.JRadioButton radioRaw;
    private javax.swing.JRadioButton radioUrlencoded;
    private javax.swing.JLayeredPane rawPane;
    private javax.swing.JComboBox<String> requestContentType;
    private javax.swing.JTextField url_string;
    private javax.swing.JLayeredPane urlencodePane;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    // 
    protected HttpRequester requester = null;

    protected void resetRespones() {
        lblResponseStatus.setText("");
        BodyResponse.setText("");
        HeaderResponse.setText("");
    }

    protected Headers getRequestHeaders() {
        Headers requestHeaders = new Headers();
        requestHeaders.addEntity(new Header("Content-Type", ContentType.URLENC.toString()));
        for (int i = 0; i < HeaderTable.getRowCount(); ++i) {
            boolean isAdd = Boolean.valueOf(BodyTable.getValueAt(i, 0).toString());
            if (isAdd && HeaderTable.getValueAt(i, 0) != null && HeaderTable.getValueAt(i, 1) != null) {
                String key = HeaderTable.getValueAt(i, 1).toString();
                String value = HeaderTable.getValueAt(i, 2).toString();
                requestHeaders.addEntity(new Header(key, value));
            }
        }
        return requestHeaders;
    }

    protected String getRequestBody() {

        Headers bodyKeysValues = new Headers();
        for (int i = 0; i < BodyTable.getRowCount(); ++i) {
            boolean isAdd = Boolean.valueOf(BodyTable.getValueAt(i, 0).toString());
            if (isAdd && BodyTable.getValueAt(i, 0) != null && BodyTable.getValueAt(i, 1) != null) {
                String key = BodyTable.getValueAt(i, 1).toString();
                String value = BodyTable.getValueAt(i, 2).toString();
                bodyKeysValues.addEntity(new Header(key, value));
            }
        }

        String body = "";
        try {
            body = new BodyBuilder()
                    .setContentType(ContentType.URLENC.toString())
                    .setKeysAndValues(bodyKeysValues)
                    .build();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return body;
    }

    protected void loadResponseHeader() {
        System.out.println("Load Response Headers...");

        HeaderResponse.setText(requester.getResponeHeaders().toString());
        lblResponseStatus.setText(requester.getStatusCode() + " - " + requester.getStatusMessage());
        System.out.println("Load Response Headers Completed.");
    }

    protected void loadResponseBody() {
        btnPreviewHtml.setEnabled(false);
        System.out.println("Load Response Body...");

        String ct = ContentType.ANY.toString();
        if (requester.getResponeHeaders().hasEntity("Content-Type")) {
            ct = requester.getResponeHeaders().getValue("Content-Type");
        }
        switch (ContentType.fromContentType(ct)) {
            case HTML:
                btnPreviewHtml.setEnabled(true);
                ResponeContentType.setSelectedItem("HTML");
                break;
            case XML:
                ResponeContentType.setSelectedItem("XML");
                break;
            case JSON:
                ResponeContentType.setSelectedItem("JSON");
                break;
            default:
                ResponeContentType.setSelectedItem("RAW");
        }
        System.out.println("Load Response Body Completed.");

    }

    protected void loadResponseCookies() {
        System.out.println("Load Response Cookies...");

        DefaultTableModel ctModel = (DefaultTableModel) CookiesTable.getModel();
        ctModel.getDataVector().removeAllElements();

        List<Cookie> listCookie = requester.getCookies().getEntities();

        for (Cookie c : listCookie) {
            Object[] col = new Object[7];
            col[0] = c.getKey();
            col[1] = c.getValue();
            col[2] = c.getDomain();
            col[3] = c.getPath();
            col[4] = c.getExpiryDate();
            col[5] = c.isHttpOnly();
            col[6] = c.isSecured();
            ctModel.addRow(col);
        }
        ctModel.fireTableDataChanged();
        System.out.println("Load Response Cookies Completed.");

    }

    protected void previewBrowser() {
        String bodyResponse = requester.getResponseBody();
        Browser browser = new Browser();
        BrowserView browswerView = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.add(browswerView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);
        browser.loadHTML(bodyResponse);
    }
}
