import java.util.ArrayList;


public class FenetreQuiz extends javax.swing.JFrame {

    private int indexQuestionCourante = 0;
    private int scoreValue = 0;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FenetreQuiz.class.getName());
    private ArrayList<question> ListeQuestions = new ArrayList<>();

    /**
     * Creates new form FenetreQuizz
     */
    public FenetreQuiz() {
        initComponents();

        ListeQuestions.add(new question(
                "Quelle est la capitale de la France ?",
                "Lyon",
                "Paris",
                "Marseille",
                "Bordeaux",
                2));
        ListeQuestions.add(new question(
                "Quelle est la capitale de l'Italie ?",
                "Rome",
                "Milan",
                "Naples",
                "Turin",
                1
        ));

        ListeQuestions.add(new question(
                "Quel est le symbole chimique de l’eau ?",
                "O2",
                "H2O",
                "CO2",
                "HO",
                2
        ));

        ListeQuestions.add(new question(
                "En quelle année a eu lieu la Révolution française ?",
                "1515",
                "1789",
                "1914",
                "1848",
                2
        ));

        ListeQuestions.add(new question(
                "Combien font 7 × 8 ?",
                "48",
                "54",
                "56",
                "64",
                3
        ));

        ListeQuestions.add(new question(
                "Quel est l’océan le plus vaste ?",
                "Atlantique",
                "Indien",
                "Pacifique",
                "Arctique",
                3
        ));

        ListeQuestions.add(new question(
                "Quel pays a remporté la Coupe du monde de football 2018 ?",
                "Allemagne",
                "France",
                "Brésil",
                "Argentine",
                2
        ));

        ListeQuestions.add(new question(
                "Quel est l’élément le plus léger du tableau périodique ?",
                "Hydrogène",
                "Hélium",
                "Lithium",
                "Carbone",
                1
        ));

        ListeQuestions.add(new question(
                "Qui a peint la Joconde ?",
                "Picasso",
                "Van Gogh",
                "Monet",
                "Leonard de Vinci",
                4
        ));

        ListeQuestions.add(new question(
                "Quelle est la planète la plus proche du Soleil ?",
                "Vénus",
                "Mercure",
                "Terre",
                "Mars",
                2
        ));
java.util.Collections.shuffle(ListeQuestions);

        afficherQuestionCourante();

    }

    private void afficherQuestionCourante() {
        question q = ListeQuestions.get(indexQuestionCourante);
q.melangerReponses();

        // Construire un énoncé complet
        String texte
                = "<html>"
                + q.getIntitule() + "<br><br>"
                + "A) " + q.getProposition1() + "<br>"
                + "B) " + q.getProposition2() + "<br>"
                + "C) " + q.getProposition3() + "<br>"
                + "D) " + q.getProposition4()
                + "</html>";

        enonce.setText(texte);

        // Les boutons restent A, B, C, D → aucun changement
        // Réactiver les boutons
        CHOIX1.setEnabled(true);
        CHOIX2.setEnabled(true);
        CHOIX3.setEnabled(true);
        CHOIX4.setEnabled(true);

        // Effacer l’ancienne réponse affichée
        feedback.setText("");
    }

    private void verifierReponse(int choixUtilisateur) {
 
        question q = ListeQuestions.get(indexQuestionCourante);

        // Vérifier si le choix correspond à la bonne réponse
        if (choixUtilisateur == q.getIndexBonneReponse()) {
            scoreValue++;  // On incrémente
            Score.setText(String.valueOf(scoreValue)); // Met à jour l’affichage du score
            feedback.setText("Bonne réponse !");


        } else {
            feedback.setText(
    "Mauvaise réponse ! La bonne réponse était : "
    + getLettreBonneReponse(q.getIndexBonneReponse())
);
 
        }

       
        // Désactiver les boutons après la réponse
        CHOIX1.setEnabled(false);
        CHOIX2.setEnabled(false);
        CHOIX3.setEnabled(false);
        CHOIX4.setEnabled(false);
       
   
    }
    private String getLettreBonneReponse(int index) {
    switch (index) {
        case 1: return "A";
        case 2: return "B";
        case 3: return "C";
        case 4: return "D";
        default: return "?";
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        question = new javax.swing.JLabel();
        CHOIX1 = new javax.swing.JButton();
        CHOIX2 = new javax.swing.JButton();
        CHOIX3 = new javax.swing.JButton();
        CHOIX4 = new javax.swing.JButton();
        feedback = new javax.swing.JLabel();
        Question_suivante = new javax.swing.JButton();
        Score = new javax.swing.JLabel();
        enonce = new javax.swing.JLabel();
        textscore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        question.setText("Question");

        CHOIX1.setText("A");
        CHOIX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHOIX1ActionPerformed(evt);
            }
        });

        CHOIX2.setText("B");
        CHOIX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHOIX2ActionPerformed(evt);
            }
        });

        CHOIX3.setText("C");

        CHOIX4.setText("D");
        CHOIX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHOIX4ActionPerformed(evt);
            }
        });

        feedback.setText("feedback");

        Question_suivante.setText("next");
        Question_suivante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Question_suivanteActionPerformed(evt);
            }
        });

        Score.setText("score:");

        enonce.setText("jLabel1");

        textscore.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Question_suivante)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enonce)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CHOIX1)
                            .addComponent(CHOIX2)
                            .addComponent(CHOIX3))
                        .addContainerGap(187, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CHOIX4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Score, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(textscore, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Question_suivante)
                    .addComponent(question))
                .addGap(9, 9, 9)
                .addComponent(enonce)
                .addGap(36, 36, 36)
                .addComponent(CHOIX1)
                .addGap(18, 18, 18)
                .addComponent(CHOIX2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(CHOIX3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CHOIX4)
                            .addComponent(Score)
                            .addComponent(textscore))
                        .addContainerGap(44, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(feedback)
                        .addGap(17, 17, 17))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CHOIX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHOIX1ActionPerformed
verifierReponse(1);
    }//GEN-LAST:event_CHOIX1ActionPerformed

    private void Question_suivanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Question_suivanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Question_suivanteActionPerformed

    private void CHOIX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHOIX4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CHOIX4ActionPerformed

    private void CHOIX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHOIX2ActionPerformed
VerifierReponse(2);        // TODO add your handling code here:
    }//GEN-LAST:event_CHOIX2ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FenetreQuiz().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CHOIX1;
    private javax.swing.JButton CHOIX2;
    private javax.swing.JButton CHOIX3;
    private javax.swing.JButton CHOIX4;
    private javax.swing.JButton Question_suivante;
    private javax.swing.JLabel Score;
    private javax.swing.JLabel enonce;
    private javax.swing.JLabel feedback;
    private javax.swing.JLabel question;
    private javax.swing.JLabel textscore;
    // End of variables declaration//GEN-END:variables
}
