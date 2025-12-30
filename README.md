# SAE1.02

. Développement Java (Le Code)

Tu dois créer quatre classes Java distinctes (une par algorithme) en respectant scrupuleusement les noms et signatures imposés:

    Algorithme Naïf (NaiveAlgo.java) : Compare le motif avec chaque position du texte une par une.

Knuth-Morris-Pratt (KMPAlgo.java) : Utilise un tableau de "fonction préfixe" pour effectuer des décalages intelligents.

Rabin-Karp (RabinKarpAlgo.java) : Utilise une fonction de hachage "déroulante" pour comparer les empreintes numériques avant de vérifier les caractères.

Boyer-Moore (BoyerMooreAlgo.java) : Compare les caractères en partant de la fin du motif et utilise deux tableaux de sauts (sauts de caractères et bons suffixes).

Contraintes techniques du code :

    Structures de données : Le texte doit être une ArrayList<Character> et le motif une String.

Méthodes obligatoires : Chaque classe doit contenir une méthode principal(), des méthodes de test (testAlgo, testCasAlgo) et une méthode d'évaluation (testAlgoEfficiency).

Mesures : Tu dois intégrer un compteur long cpt dans la boucle la plus imbriquée de tes algorithmes pour compter les opérations élémentaires.

Documentation : Tout ton code doit être soigné et documenté en anglais via JavaDoc.

2. Analyse de l'Efficacité (L'Étude)

Pour chaque algorithme, tu dois réaliser des tests de performance sur de très grandes tailles de texte (ex: 1000 caractères et plus).

    Types de textes à tester : Textes aléatoires et textes très répétitifs (ex: "AAAAA...").

Données à collecter : Pour chaque taille de texte, note la valeur du compteur cpt et le temps d'exécution réel.

Analyse théorique : Pour l'algorithme naïf, tu dois calculer f(n) exacte et en déduire sa complexité O(n).

3. Le Rendu Final

Tu dois rendre une archive .zip au nom de ton binôme avant le vendredi 16 janvier 2026 à 23h55. Elle doit contenir :

    Tes fichiers sources Java (.java) complets et documentés.

Un rapport PDF (max 15 pages) comprenant:

    Une introduction avec les objectifs.

Les tableaux de mesures (cpt et temps).

Les graphiques illustrant ces résultats.

Des commentaires et analyses sur ces graphiques (obligatoire pour que les résultats soient validés).

Une conclusion comparative expliquant quel algorithme est le plus performant selon la nature du texte.