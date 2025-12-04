# SAE1.02


# A Faire pour BM :

# TO-DO LIST : Finalisation Algorithme Boyer-Moore

## 1. Implémentation de la Règle du "Bon Suffixe" (Tableau 2)
[cite_start]Le sujet exige l'utilisation de **deux** tableaux pour déterminer le décalage [cite: 524-526]. Vous devez créer une méthode pour le second.

- [ ] **Créer la méthode `int calculDecalageBonSuffixe(String pattern, int j)`**
    - **Entrée :** Le motif et l'indice `j` où l'erreur s'est produite (dans le motif).
    - **Sortie :** Un entier représentant le décalage D2.

- [ ] **Coder le Cas Particulier (Erreur en fin de motif)**
    - Si `j` est le dernier caractère (`|m|-1`), le suffixe $u$ est vide.
    - [cite_start]**Action :** Retourner **1** [cite: 511-517].

- [ ] **Coder la Règle 1 (Suffixe récurrent)**
    - Identifier le suffixe $u$ ($pattern[j+1 \dots fin]$).
    - Chercher une autre occurrence de $u$ dans le motif (vers la gauche).
    - [cite_start]**Condition :** Le caractère précédant cette occurrence doit être **différent** de $pattern[j]$[cite: 431].
    - **Action :** Si trouvé, retourner la distance entre les deux suffixes.

- [ ] **Coder la Règle 2 (Préfixe correspond au Suffixe)**
    - Si la Règle 1 échoue.
    - [cite_start]Chercher le plus long **préfixe** du motif qui est aussi un **suffixe** de $u$ [cite: 432-433].
    - **Action :** Si trouvé (longueur $z$), retourner $|m| - z$.
    - **Action :** Sinon, retourner $|m|$ (décalage complet).

---

## 2. Intégration dans la boucle principale (`boyerMooreAlgo`)
Vous devez combiner votre méthode actuelle (`decalage`) et la nouvelle méthode.

- [ ] **Calculer les deux décalages** dans le bloc `else` (cas de non-correspondance) :
    1. `d1` = résultat de votre méthode actuelle (Règle du Mauvais Caractère).
    2. `d2` = résultat de `calculDecalageBonSuffixe` (Règle du Bon Suffixe).

- [ ] **Appliquer le Maximum**
    - [cite_start]Modifier l'incrémentation de l'indice : `indPattern += Math.max(d1, d2);`[cite: 526].

---

## 3. Conformité et Rendu
Derniers points pour respecter les consignes du PDF.

- [ ] **Javadoc en Anglais**
    - [cite_start]Traduire ou rédiger les commentaires Javadoc de la classe et des méthodes en anglais[cite: 539].

- [ ] **Tests sur Textes Répétitifs**
    - [cite_start]Vérifier que `testBoyerMooreAlgoEfficiency` inclut bien des textes répétitifs (ex: "AAAAA...") car c'est là que le Bon Suffixe est le plus performant[cite: 48].

- [ ] **Nettoyage**
    - Supprimer tout code mort ou affichage de débogage inutile avant le rendu.