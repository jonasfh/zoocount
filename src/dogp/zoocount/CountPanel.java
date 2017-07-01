/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogp.zoocount;

import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jonas
 */
public class CountPanel extends javax.swing.JPanel {

    /**
     * Creates new form CountPanel
     * @param text
     * @param value
     */
    public CountPanel(String text, int value) {
        initComponents();
        charAndName.setText(text);
        character = text;
        count.setText(Integer.toString(value));
    }

    @Override
    public void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
        charAndName.addKeyListener(l);
        count.addKeyListener(l);
        settingsButton.addKeyListener(l);
    }
    public String getValue() {
        return count.getText();
    }
    public void setValue(String s) {
        count.setText(s);
    }
    public String getChar() {
        return character;
    }
    public String getCharName() {
        return name;
    }
    public String getCharShortName() {
        return shortname;
    }
    public void setChar(String s) {
        character = s;
    }
    public void setCharName(String s) {
        name = s;
    }
    public void setCharShortName(String s) {
        shortname = s;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        charAndName = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        settingsButton = new javax.swing.JButton();

        charAndName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charAndName.setText("A");

        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setText("0");

        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dogp/resources/settings.png"))); // NOI18N
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(count, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton))
                    .addComponent(charAndName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(charAndName)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(count)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settingsButton)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        JTextField name = new JTextField(getCharName());
        JTextField shortname = new JTextField(getCharShortName());
        JComponent[] input = new JComponent[] {
            new JLabel("Data name"),
            name,
            new JLabel("Display name (short)"),
            shortname
        };
        int result = JOptionPane.showConfirmDialog(this, input);
        if (result == JOptionPane.OK_OPTION) {
            setCharName(name.getText().trim());
            setCharShortName(shortname.getText().trim());
            String display = this.character;
            if (this.shortname.length()>0) {
                display += ": " + this.shortname;
            }
            this.charAndName.setText(display);
        }
    }//GEN-LAST:event_settingsButtonActionPerformed

    private String name = "";
    private String shortname = "";
    private String character = "";

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        String separator = Settings.getInstance().getOption(
            "csv.separator", ","
        );
        String quote = Settings.getInstance().getOption(
            "csv.quote", "\""
        );

        String[] strings= new String[] {
            quote,
            character,
            quote,
            separator,
            quote,
            shortname,
            quote,
            separator,
            quote,
            name,
            quote,
            separator,
            count.getText(),
        };
        for (String s : strings) {
            output.append(s);
        }
        return output.toString();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel charAndName;
    private javax.swing.JLabel count;
    private javax.swing.JButton settingsButton;
    // End of variables declaration//GEN-END:variables
}
