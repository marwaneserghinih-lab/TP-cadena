import java.util.ArrayList;


public class question {

    private String intitule;
    private String proposition1;
    private String proposition2;
    private String proposition3;
    private String proposition4;
    private int indexBonneReponse;

   
    public question(String intitule, String proposition1, String proposition2, String proposition3, String proposition4, int indexBonneReponse) {
        this.intitule = intitule;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.indexBonneReponse = indexBonneReponse;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getProposition1() {
        return proposition1;
    }

    public String getProposition2() {
        return proposition2;
    }

    public String getProposition3() {
        return proposition3;
    }

    public String getProposition4() {
        return proposition4;
    }

    public int getIndexBonneReponse() {
        return indexBonneReponse;
    }


public void melangerReponses() {
    // On crée une liste temporaire
    ArrayList<String> propositions = new ArrayList<>();
    propositions.add(proposition1);
    propositions.add(proposition2);
    propositions.add(proposition3);
    propositions.add(proposition4);

    // On mémorise la bonne réponse AVANT mélange
    String bonne = propositions.get(indexBonneReponse - 1);

    // Mélange des propositions
    java.util.Collections.shuffle(propositions);

    // On réaffecte les propositions mélangées
    proposition1 = propositions.get(0);
    proposition2 = propositions.get(1);
    proposition3 = propositions.get(2);
    proposition4 = propositions.get(3);

    // On retrouve la nouvelle position de la bonne réponse
    indexBonneReponse = propositions.indexOf(bonne) + 1;
}

}
