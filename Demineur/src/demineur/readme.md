### 1. Comment démmarrer le projet ?

Pour demmarer le projet "Super Démineur" il vous suffit de run le fichier 
Main.java situé dans le dossier demineur.

### 2. Spécifitées techniques 

"Super Démineur" a été codé entièrement en Java, en utilisant la librairie 
Swing  (disponible par défaut dans le SDK Java), à l'aide de graphics2D. 
En effet, contrairement a ce qui a été fait en classe, et puisque cela n'était 
pas demandé dans les consignes, je n'ai pas utilisé de composants préfaits de 
Swing, ni utilisé l'editeur, tout a été fait dans un JPanel en dessin image 
par image (de façon bas niveau).

### 3. Pourquoi ce choix ?

C'est surtout par souci de liberté, vous constaterez vous même que le jeu 
propose des effets graphiques assez uniques (screen shake, ecran pixelisé, ...) 
ainsi qu'un style retro qu'il serait difficile a répliquer en utilisant 
les éléments UI classiques.

### 4. Respect du cahiet des charges

Comme vous pourrez le constater, j'ai pris beacoups de libertées sur ce qui 
était demandé, bien que j'ai fait de mon mieux pour respecter les consignes.

Vous avez demander trois classes : 
- Partie
- Cellule
- GrilleDeJeu

Cellule et GrilleDeJeu sont complêtement implémentées a travers les classes
Cell et Grid, bien que Grid gère des aspects plus étendus du jeu.

Le rôle demandé dans la classe partie est en partie géré par SceneManager, 
Context et Runtime (dans le moteur donc).

J'ai fait ce choix architectural pour bien séparer les responsabilitées
entre le jeu et le moteur.

A part ces quelques divergences, les classes Grid et Cell font quasiment
exactement ce qui est demandé dans le cahiet des charges.

Concernant le choix de la taille de la grille, j'ai préféré eviter de donner
cette possibilité pour deux raisons :

## 1.
La difficulté repose déjà sur le nombre de bombes dans la grille,
pourquoi complexifier l'experience utilisateur en ajoutant un 
parametre en plus (la taille de la grille). 

## 2.
Le style retro du jeu inspiré de la NES, ainsi que la resolution réduite 
(256x240), empêchent d'avoir une grille plus grande que celle déjà 
présente (a moins d'ajouter un système de caméra ce qui est assez etrange
pour un jeu de démineur).

### 5. Mode avancé

Vous avez demandé d'ajouter une fonctionalité unique, il s'agit dans mon 
cas du mode "arcade".

Dans ce mode, vous devez résoudre une grille de 5x5 contenant 3 bombes en 
moins de 15 secondes.

Vous obtenez un point à chaque résolution.

### 6. Architecture générale du projet

Mon projet n'apporte rien de nouveau, il se base sur des systèmes connus
et recconus.

Le sytème de Scenes, de SceneManager ainsi que de GameObject pour gérer 
les objets du jeu.

- Le système de Context pour gérer l'API du moteur.

- Le système de Runtime pour gérer le backend.

- J'ai essayé de bien architecturer le projet et de créer un moteur réutilisable.

