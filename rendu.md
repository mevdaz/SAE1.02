# SAE S1.02 – Comparaison d’approches algorithmiques

## Recherche d’une sous-chaîne dans un texte

**Auteurs :** Nom Prénom – Nom Prénom  
**Groupe :** TD …  
**Année universitaire :** 2025–2026

---

## Introduction

L’objectif de cette SAE est de comparer plusieurs approches algorithmiques permettant de résoudre le problème de la recherche d’une sous-chaîne (motif) dans un texte. Ce problème est central en informatique et intervient dans de nombreux domaines tels que les éditeurs de texte ou l’analyse de séquences.

Dans ce travail, nous avons implémenté et étudié quatre algorithmes :
- l’algorithme naïf,
- l’algorithme de Knuth-Morris-Pratt (KMP),
- l’algorithme de Rabin-Karp,
- l’algorithme de Boyer-Moore.

Leur efficacité est comparée à l’aide de mesures empiriques basées sur le nombre d’opérations élémentaires et le temps d’exécution.

---

## 1. Présentation du problème

Le problème consiste à trouver toutes les occurrences d’un motif (chaîne de caractères) dans un texte donné. Le texte est représenté sous la forme d’un `ArrayList<Character>` et le motif sous la forme d’une chaîne `String`.

Toutes les positions où le motif apparaît dans le texte doivent être détectées.

---

## 2. Méthodologie d’évaluation

Pour chaque algorithme, nous avons :
- vérifié le bon fonctionnement sur des exemples simples,
- mesuré le nombre d’opérations élémentaires à l’aide d’un compteur `cpt`,
- mesuré le temps d’exécution,
- réalisé des tests sur différents types de textes :
  - textes aléatoires,
  - textes répétitifs.

Les résultats sont présentés sous forme de tableaux et de graphiques.

---

## 3. Algorithme naïf

### 3.1 Principe

L’algorithme naïf compare le motif avec chaque sous-chaîne possible du texte, caractère par caractère, en décalant le motif d’une position à chaque tentative.

Le nombre total d’opérations élémentaires est donc :
\[
f(n) = (n - m + 1) \times m
\]

Dans le pire des cas, on a :
\[
f(n) \in \Theta(n \times m)
\]

Si $m$ est considéré comme une constante, la complexité devient :
\[
\Theta(n)
\]


### 3.2 Résultats expérimentaux

**Tableau des mesures (texte aléatoire)**

[INSÉRER ICI LE TABLEAU DES MESURES – NAÏF / TEXTE ALÉATOIRE]

**Graphique – nombre d’opérations en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – NAÏF / TEXTE ALÉATOIRE]

**Graphique – temps d’exécution en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – NAÏF / TEXTE ALÉATOIRE]

### 3.3 Commentaires

[COMMENTAIRES ET INTERPRÉTATION DES RÉSULTATS]

---

## 4. Algorithme de Knuth-Morris-Pratt (KMP)

### 4.1 Principe

L’algorithme KMP améliore la recherche en évitant les comparaisons inutiles grâce à l’utilisation d’un tableau de préfixes.


La construction du tableau des préfixes nécessite au plus $m$ comparaisons :
\[
\Theta(m)
\]

La phase de recherche parcourt le texte sans jamais revenir en arrière.
Chaque caractère du texte est examiné au plus une fois :
\[
\Theta(n)
\]

La complexité totale est donc :
\[
\Theta(n + m)
\]

Cette complexité est valable aussi bien dans le pire que dans le meilleur des cas.

### 4.2 Résultats expérimentaux

**Tableau des mesures (texte aléatoire)**

[INSÉRER ICI LE TABLEAU DES MESURES – KMP / TEXTE ALÉATOIRE]

**Graphique – nombre d’opérations en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – KMP / TEXTE ALÉATOIRE]

**Graphique – temps d’exécution en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – KMP / TEXTE ALÉATOIRE]

### 4.3 Commentaires

[COMMENTAIRES ET INTERPRÉTATION DES RÉSULTATS]

---

## 5. Algorithme de Rabin-Karp

### 5.1 Principe

L’algorithme de Rabin-Karp utilise une fonction de hachage déroulante pour comparer rapidement le motif avec des portions du texte.

L’algorithme de Rabin-Karp utilise une fonction de hachage déroulante.
Le calcul initial de l’empreinte du motif et de la première portion du texte
nécessite :
\[
\Theta(m)
\]

Ensuite, pour chaque décalage du motif, la mise à jour de l’empreinte est effectuée
en temps constant :
\[
\Theta(1)
\]

Sur l’ensemble du texte, cela donne :
\[
\Theta(n)
\]

Cependant, en cas de collision de hachage, une comparaison caractère par caractère
est nécessaire, coûtant $\Theta(m)$.

### 5.2 Résultats expérimentaux

**Tableau des mesures (texte aléatoire)**

[INSÉRER ICI LE TABLEAU DES MESURES – RABIN-KARP / TEXTE ALÉATOIRE]

**Graphique – nombre d’opérations en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – RABIN-KARP / TEXTE ALÉATOIRE]

**Graphique – temps d’exécution en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – RABIN-KARP / TEXTE ALÉATOIRE]

### 5.3 Commentaires

[COMMENTAIRES ET INTERPRÉTATION DES RÉSULTATS]

---

## 6. Algorithme de Boyer-Moore

### 6.1 Principe

L’algorithme de Boyer-Moore compare le motif en partant de la fin et utilise des règles de décalage avancées pour accélérer la recherche.

Dans le meilleur des cas, le motif est décalé de plusieurs positions à chaque
comparaison, ce qui permet de parcourir le texte très rapidement :
\[
\Theta\left(\frac{n}{m}\right)
\]

En moyenne, l’algorithme est sous-linéaire et s’exécute en :
\[
\Theta(n)
\]

Dans le pire des cas (textes très répétitifs et motifs défavorables),
les décalages sont faibles et la complexité devient :
\[
\Theta(n \times m)
\]

### 6.2 Résultats expérimentaux

**Tableau des mesures (texte aléatoire)**

[INSÉRER ICI LE TABLEAU DES MESURES – BOYER-MOORE / TEXTE ALÉATOIRE]

**Graphique – nombre d’opérations en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – BOYER-MOORE / TEXTE ALÉATOIRE]

**Graphique – temps d’exécution en fonction de la taille du texte**

[INSÉRER ICI LE GRAPHIQUE – BOYER-MOORE / TEXTE ALÉATOIRE]

### 6.3 Commentaires

[COMMENTAIRES ET INTERPRÉTATION DES RÉSULTATS]

---

## 7. Comparaison globale des algorithmes

### 7.1 Textes aléatoires

[INSÉRER ICI UN OU PLUSIEURS GRAPHIQUES COMPARATIFS]

### 7.2 Textes répétitifs

[INSÉRER ICI UN OU PLUSIEURS GRAPHIQUES COMPARATIFS]

### 7.3 Analyse comparative

[ANALYSE COMPARATIVE ENTRE LES 4 ALGORITHMES]

---

## Conclusion

Ce travail a permis de mettre en évidence les différences d’efficacité entre plusieurs algorithmes de recherche de sous-chaîne. Les résultats montrent que les algorithmes optimisés (KMP, Rabin-Karp, Boyer-Moore) sont globalement plus performants que l’algorithme naïf, en particulier pour les grands textes et selon la nature des données.

Une analyse plus fine montre que le choix de l’algorithme dépend fortement du type de texte et du motif recherché.

---

## Annexes (optionnel)

- Détails des jeux de tests
- Extraits de code
- Résultats supplémentaires

