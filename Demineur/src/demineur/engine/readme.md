### Package "engine"

Ce package est dédié a la partie "moteur" du projet.
Par moteur, je designe tout le code générique a la conception de jeux 2D.

## Context.java

Le Contexte est l'objet central du jeu, il permet la communication entre tous
les acteurs de celui-ci, il centralise également l'API (dessin, ajout d'objet, 
...) 

Il hérite logiquement de javax.swing.JFrame.

## Rect.java

Implémentation d'un rectangle basique supportant les collisions avec

- autres rectangles
- points 

## Renderer.java

Le Renderer est l'objet gérant le rendu (et l'actualisation des objets).
Le rendu se fait en receptionnant des instructions générées par le contexte, 
ces instructions contiennent les informations utiles au rendu d'un objet.
- type 
- position
- couleur
- ... 
Il hérite logiquement de javax.swing.JPanel

## GameObject.java

GameObject est la classe abstraite définissant la structure d'un objet dans 
le jeu, les sous classes doivent réimplémenter "update". 

## Instruction.java

Dataclass contenant les informations utiles a une instruction d'affichage.
