import javax.swing.JLabel;
import java.util.Arrays;
import miniprojet_cadenas_serghini_marwane.cadenajuego;

// Assurez-vous que votre CadenasGame.java est bien compilé dans le projet !
public class fenetre extends javax.swing.JFrame {
   
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(fenetre.class.getName());

    private cadenajuego jeu; // Utilise la classe métier CadenasGame
   
    private JLabel[] chiffresLabels;
   
    public fenetre() {
        jeu = new cadenajuego();
        initComponents();
       
        chiffresLabels = new JLabel[]{texte_chiffre_0, texte_chiffre_1, texte_chiffre_2, texte_chiffre_3};
       
        addListeners(); // C'est ici que le bouton TESTER est connecté
       
        mettreAJourAffichage();
       
        gagner_perdu.setText("");
    }
   
    // ================== GESTIONNAIRES D'ÉVÉNEMENTS ==================

    private void addListeners() {
        // --- Boutons UP/DOWN (inchangés, ils devraient marcher) ---
        up_chiffre_1.addActionListener(e -> gererChangementChiffre(0, true));
        up_chiffre_2.addActionListener(e -> gererChangementChiffre(1, true));
        up_chiffre_3.addActionListener(e -> gererChangementChiffre(2, true));
        up_chiffre_4.addActionListener(e -> gererChangementChiffre(3, true));
       
        down_chiffre_1.addActionListener(e -> gererChangementChiffre(0, false));
        down_chiffre_2.addActionListener(e -> gererChangementChiffre(1, false));
        down_chiffre_3.addActionListener(e -> gererChangementChiffre(2, false));
        down_chiffre_4.addActionListener(e -> gererChangementChiffre(3, false));
       
        // --- Le Listener le plus important : TESTER ---
        boutton_tester.addActionListener(e -> gererTest());
       
        // --- Bouton RECOMMENCER (semble fonctionner pour vous) ---
        boutoun_recommencer.addActionListener(e -> gererRecommencer());
    }
   
    private void gererChangementChiffre(int position, boolean monter) {
        if (!jeu.estPartieTerminee() && boutton_tester.isEnabled()) {
            jeu.changerChiffre(position, monter);
            mettreAJourAffichage();
        }
    }
   
    // METHODE DÉSORMAIS FONCTIONNELLE POUR LE BOUTON TESTER
    private void gererTest() {
        if (jeu.estPartieTerminee() || !boutton_tester.isEnabled()) {
            return;
        }
       
        // 1. Tester la proposition
        ResultatAnalyse resultat = jeu.tester ResultatAnalyse();
       
        // 2. Mettre à jour les résultats
        texte_nb_chiffres_exacts.setText(String.valueOf(resultat.nombreChiffresExacts));
        texte_nb_chiffres_hauts.setText(String.valueOf(resultat.nombreChiffresTropHauts));
        texte_nb_chiffres_bas.setText(String.valueOf(resultat.nombreChiffresTropBas));
       
        // 3. Mettre à jour le score (tentatives restantes)
        mettreAJourAffichage();
       
        // 4. Vérifier la fin de partie
        if (resultat.estGagne()) {
            gagner_perdu.setText("🥳 GAGNÉ ! Code trouvé en " + jeu.getTentativesEffectuees() + " tentatives !");
            desactiverBoutonsJeu(false);
        } else if (jeu.estPartieTerminee()) {
            String codeSecret = Arrays.toString(jeu.getCodeSecret()).replaceAll("[\\[\\] ,]", "");
            gagner_perdu.setText("😭 PERDU ! Le code était : " + codeSecret);
            desactiverBoutonsJeu(false);
        }
    }
   
    private void gererRecommencer() {
        jeu.recommencer();
       
        // Réinitialiser les affichages des résultats
        texte_nb_chiffres_exacts.setText("0");
        texte_nb_chiffres_hauts.setText("0");
        texte_nb_chiffres_bas.setText("0");
        gagner_perdu.setText("");
       
        mettreAJourAffichage(); // Met à jour les chiffres et le score (0 sur 5)
        desactiverBoutonsJeu(true); // Réactive les boutons pour jouer
    }

    private void mettreAJourAffichage() {
        int[] code = jeu.getCodeActuel();
        for (int i = 0; i < code.length; i++) {
            chiffresLabels[i].setText("     " + code[i]);
        }
        texte_score.setText("     " + jeu.getScore());
    }
   
    private void desactiverBoutonsJeu(boolean actif) {
        bouton_tester.setEnabled(actif);
        up_chiffre_1.setEnabled(actif);
        up_chiffre_2.setEnabled(actif);
        up_chiffre_3.setEnabled(actif);
        up_chiffre_4.setEnabled(actif);
        down_chiffre_1.setEnabled(actif);
        down_chiffre_2.setEnabled(actif);
        down_chiffre_3.setEnabled(actif);
        down_chiffre_4.setEnabled(actif);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        down_chiffre_1 = new javax.swing.JButton();
        down_chiffre_2 = new javax.swing.JButton();
        down_chiffre_3 = new javax.swing.JButton();
        down_chiffre_4 = new javax.swing.JButton();
        boutton_tester = new javax.swing.JButton();
        boutoun_recommencer = new javax.swing.JButton();
        up_chiffre_1 = new javax.swing.JButton();
        up_chiffre_2 = new javax.swing.JButton();
        up_chiffre_3 = new javax.swing.JButton();
        up_chiffre_4 = new javax.swing.JButton();
        texte_intro = new javax.swing.JLabel();
        texte_chiffre_0 = new javax.swing.JLabel();
        texte_chiffre_1 = new javax.swing.JLabel();
        texte_chiffre_2 = new javax.swing.JLabel();
        texte_chiffre_3 = new javax.swing.JLabel();
        texte_lbl_nb_chiffres_exacts = new javax.swing.JLabel();
        texte_nb_chiffres_exacts = new javax.swing.JLabel();
        texte_lbl_nb_chiffres_haut = new javax.swing.JLabel();
        texte_nb_chiffres_haut = new javax.swing.JLabel();
        texte_lbl_nb_chiffres_bas = new javax.swing.JLabel();
        texte_nb_chiffres_bas = new javax.swing.JLabel();
        texte_tentatives = new javax.swing.JLabel();
        texte_score = new javax.swing.JLabel();
        gagner_perdu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        down_chiffre_1.setText("\\/");
        down_chiffre_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                down_chiffre_1ActionPerformed(evt);
            }
        });
        getContentPane().add(down_chiffre_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        down_chiffre_2.setText("\\/");
        getContentPane().add(down_chiffre_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        down_chiffre_3.setText("\\/");
        getContentPane().add(down_chiffre_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, 20));

        down_chiffre_4.setText("\\/");
        down_chiffre_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                down_chiffre_4ActionPerformed(evt);
            }
        });
        getContentPane().add(down_chiffre_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        boutton_tester.setText("tester");
        getContentPane().add(boutton_tester, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        boutoun_recommencer.setText("recommencer");
        getContentPane().add(boutoun_recommencer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, -1, -1));

        up_chiffre_1.setText("^");
        getContentPane().add(up_chiffre_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 30, 20));

        up_chiffre_2.setText("^");
        up_chiffre_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_chiffre_2ActionPerformed(evt);
            }
        });
        getContentPane().add(up_chiffre_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        up_chiffre_3.setText("^");
        getContentPane().add(up_chiffre_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        up_chiffre_4.setText("^");
        up_chiffre_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_chiffre_4ActionPerformed(evt);
            }
        });
        getContentPane().add(up_chiffre_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        texte_intro.setText("Trouvez le bon code en moins de cinq tentative");
        getContentPane().add(texte_intro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        texte_chiffre_0.setText("0");
        getContentPane().add(texte_chiffre_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        texte_chiffre_1.setText("0");
        getContentPane().add(texte_chiffre_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        texte_chiffre_2.setText("0");
        getContentPane().add(texte_chiffre_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        texte_chiffre_3.setText("0");
        getContentPane().add(texte_chiffre_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        texte_lbl_nb_chiffres_exacts.setText("Nombre de chiffres exact");
        getContentPane().add(texte_lbl_nb_chiffres_exacts, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        texte_nb_chiffres_exacts.setText("0");
        getContentPane().add(texte_nb_chiffres_exacts, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 10, -1));

        texte_lbl_nb_chiffres_haut.setText("Nombre de chiffres trop haut");
        getContentPane().add(texte_lbl_nb_chiffres_haut, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        texte_nb_chiffres_haut.setText("0");
        getContentPane().add(texte_nb_chiffres_haut, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 20, 20));

        texte_lbl_nb_chiffres_bas.setText("Nombre de chiffres trop bas");
        getContentPane().add(texte_lbl_nb_chiffres_bas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        texte_nb_chiffres_bas.setText("0");
        getContentPane().add(texte_nb_chiffres_bas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 10, -1));

        texte_tentatives.setText("tentatives");
        getContentPane().add(texte_tentatives, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        texte_score.setText("0 sur 5");
        getContentPane().add(texte_score, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, -1));

        gagner_perdu.setText("gagner\\perdu");
        getContentPane().add(gagner_perdu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void down_chiffre_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_down_chiffre_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_down_chiffre_1ActionPerformed

    private void up_chiffre_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_chiffre_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_up_chiffre_2ActionPerformed

    private void down_chiffre_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_down_chiffre_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_down_chiffre_4ActionPerformed

    private void up_chiffre_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_chiffre_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_up_chiffre_4ActionPerformed

    
    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> new Interface().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutoun_recommencer;
    private javax.swing.JButton boutton_tester;
    private javax.swing.JButton down_chiffre_1;
    private javax.swing.JButton down_chiffre_2;
    private javax.swing.JButton down_chiffre_3;
    private javax.swing.JButton down_chiffre_4;
    private javax.swing.JLabel gagner_perdu;
    private javax.swing.JLabel texte_chiffre_0;
    private javax.swing.JLabel texte_chiffre_1;
    private javax.swing.JLabel texte_chiffre_2;
    private javax.swing.JLabel texte_chiffre_3;
    private javax.swing.JLabel texte_intro;
    private javax.swing.JLabel texte_lbl_nb_chiffres_bas;
    private javax.swing.JLabel texte_lbl_nb_chiffres_exacts;
    private javax.swing.JLabel texte_lbl_nb_chiffres_haut;
    private javax.swing.JLabel texte_nb_chiffres_bas;
    private javax.swing.JLabel texte_nb_chiffres_exacts;
    private javax.swing.JLabel texte_nb_chiffres_haut;
    private javax.swing.JLabel texte_score;
    private javax.swing.JLabel texte_tentatives;
    private javax.swing.JButton up_chiffre_1;
    private javax.swing.JButton up_chiffre_2;
    private javax.swing.JButton up_chiffre_3;
    private javax.swing.JButton up_chiffre_4;
    // End of variables declaration//GEN-END:variables
