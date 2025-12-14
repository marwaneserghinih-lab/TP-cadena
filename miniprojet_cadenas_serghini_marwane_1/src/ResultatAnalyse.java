/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */

public class ResultatAnalyse {
    private final int exacts;
    private final int superieurs;
    private final int inferieurs;

    public ResultatAnalyse(int exacts, int superieurs, int inferieurs) {
        this.exacts = exacts;
        this.superieurs = superieurs;
        this.inferieurs = inferieurs;
    }

    public int getExacts() { return exacts; }
    public int getSuperieurs() { return superieurs; }
    public int getInferieurs() { return inferieurs; }
}
