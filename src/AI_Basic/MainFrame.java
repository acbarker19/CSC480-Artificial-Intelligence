/**
 * MainFrame.
 * A GUI frame class that a user can interact with to set the problem for the
 * agent.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        String[] sampleProblems = new String[] {
            "M&C 1", "M&C 2", "Invalid M&C 1", "Invalid M&C 2",
            "8 Puzzle 1", "8 Puzzle 2", "Invalid 8 Puzzle 1",
            "Invalid 8 Puzzle 2", "Route Finder 1", "Route Finder 2",
            "Invalid Route Finder 1", "Invalid Route Finder 2"
        };
        sampleProblemsComboBox.removeAllItems();
        for(String sample : sampleProblems){
            sampleProblemsComboBox.addItem(sample);
        }
    }
    
    private void displayError(String error){
        JOptionPane.showMessageDialog(null,
                "<html><body><p style='width: 200px;'>" + error.replace("\n",
                        "<br>") + "</p></body></html>",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        enterButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        sampleProblemsComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        sampleProblemsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sampleProblemsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleProblemsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sampleProblemsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterButton)
                    .addComponent(clearButton)
                    .addComponent(sampleProblemsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        String input = textArea.getText();
        
        String errorMessage = ValidityChecker.runChecks(input);
        
        // If the input is incorrect, display error message.
        // If input returns null, it is correct and runs program.
        if(errorMessage != null){
            displayError(errorMessage);
        }else{
            textArea.setText(input + "\r\n\r\n===================");
            
            Agent agent = new Agent(input);
            
            Sequence solution = agent.getSolution();
            
            String output = textArea.getText() +
                    "\r\n\r\nNumber of Steps to Get to Solution: " +
                    (solution.size() - 1) + "\r\n\r\nSteps to Get to Solution:";
            
            State currentState = agent.getProblem().getInitialState();
            for(int i = 0; i < solution.size(); i++){
                if(solution.get(i) != null){
                    output += "\r\n" + currentState.toString() +
                            " + " + solution.get(i).toString() +
                            " = " + agent.getProblem().getResult(currentState, 
                                    solution.get(i));
                    if(agent.getProblemName().equals("Route Finder")){
                        output += "\tDistance: " +
                                agent.getProblem().getPathCost(
                                        currentState, solution.get(i));
                    }
                    currentState = agent.getProblem().getResult(currentState, 
                                    solution.get(i));
                }
            }
            textArea.setText(output);
        }
    }//GEN-LAST:event_enterButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        textArea.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void sampleProblemsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleProblemsComboBoxActionPerformed
        String input = (String)sampleProblemsComboBox.getSelectedItem();
        
        if(input != null){
            switch(input){
                case "M&C 1":
                    textArea.setText("M&C#331000#000133");
                    break;
                case "M&C 2":
                    textArea.setText("M&C#321001#000133");
                    break;
                case "Invalid M&C 1":
                    textArea.setText("M&C#201013#000133");
                    break;
                case "Invalid M&C 2":
                    textArea.setText("M&C#330100#000133");
                    break;
                case "8 Puzzle 1":
                    textArea.setText("8puzzle#012345678#102345678");
                    break;
                case "8 Puzzle 2":
                    textArea.setText("8puzzle#012345678#125348607");
                    break;
                case "Invalid 8 Puzzle 1":
                    textArea.setText("8puzzle#012345678#0one2345678");
                    break;
                case "Invalid 8 Puzzle 2":
                    textArea.setText("8puzzle#123456789#192345678");
                    break;
                case "Route Finder 1":
                    textArea.setText("Route#Arad#Oradea#Oradea 71 Zerind\n"
                            + "Oradea 151 Sibiu\n"
                            + "Zerind 75 Arad\n"
                            + "Arad 140 Sibiu\n"
                            + "Arad 118 Timisoara\n"
                            + "Timisoara 111 Lugoj\n"
                            + "Lugoj 70 Mehadia\n"
                            + "Mehadia 75 Drobeta\n"
                            + "Drobeta 120 Craiova\n"
                            + "Craiova 146 Rimnicu_Vilcea\n"
                            + "Rimnicu_Vilcea 80 Sibiu\n"
                            + "Craiova 138 Pitesti\n"
                            + "Pitesti 97 Rimnicu_Vilcea\n"
                            + "Pitesti 101 Bucharest\n"
                            + "Sibiu 99 Fagaras\n"
                            + "Fagaras 211 Bucharest\n"
                            + "Bucharest 90 Giurgiu\n"
                            + "Bucharest 85 Urziceni\n"
                            + "Urziceni 98 Hirsova\n"
                            + "Hirsova 86 Eforie\n"
                            + "Urziceni 142 Vaslui\n"
                            + "Vaslui 92 Iasi\n"
                            + "Iasi 87 Neamt");
                    break;
                case "Route Finder 2":
                    textArea.setText("Route#A#Z#A 1 B\n"
                            + "B 1 C\n"
                            + "C 1 Z\n"
                            + "C 1 D\n"
                            + "A 100 Z");
                    break;
                case "Invalid Route Finder 1":
                    textArea.setText("Route#Cleveland#Oradea#Oradea 71 Zerind\n"
                            + "Oradea 151 Sibiu\n"
                            + "Zerind 75 Arad\n"
                            + "Arad 140 Sibiu\n"
                            + "Arad 118 Timisoara\n"
                            + "Timisoara 111 Lugoj\n"
                            + "Lugoj 70 Mehadia\n"
                            + "Mehadia 75 Drobeta\n"
                            + "Drobeta 120 Craiova\n"
                            + "Craiova 146 Rimnicu_Vilcea\n"
                            + "Rimnicu_Vilcea 80 Sibiu\n"
                            + "Craiova 138 Pitesti\n"
                            + "Pitesti 97 Rimnicu_Vilcea\n"
                            + "Pitesti 101 Bucharest\n"
                            + "Sibiu 99 Fagaras\n"
                            + "Fagaras 211 Bucharest\n"
                            + "Bucharest 90 Giurgiu\n"
                            + "Bucharest 85 Urziceni\n"
                            + "Urziceni 98 Hirsova\n"
                            + "Hirsova 86 Eforie\n"
                            + "Urziceni 142 Vaslui\n"
                            + "Vaslui 92 Iasi\n"
                            + "Iasi 87 Neamt");
                    break;
                case "Invalid Route Finder 2":
                    textArea.setText("Route#A#Z#A 1 B\n"
                            + "B 1 C Z\n"
                            + "C 1 Z\n"
                            + "C 1 D\n"
                            + "A 100 Z");
                    break;
            }
        }
    }//GEN-LAST:event_sampleProblemsComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> sampleProblemsComboBox;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
