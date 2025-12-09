Voici les consignes de l'atelier formatées en Markdown, prêtes à être intégrées dans un fichier `README.md` ou un sujet de TP.

---

# Atelier TDD : Gestion de Laboratoire

Cet atelier individuel a pour but d'élaborer une classe généralisant l'objet créé lors du précédent atelier, en adoptant une approche **Test Driven Development (TDD)** stricte.

> **Règle d'Or :** Chacun des cas suivants **SANS EXCEPTION** doit être implémenté avec TDD.
>
> **L'absence de commit git pour chacune des 3 étapes (Red, Green, Refactor)** de la majorité des itérations TDD de l'atelier rendra l'atelier **hors-sujet** (seuls des oublis ponctuels seront tolérés).
>
> *Conseil : Utilisez des préfixes explicites dans vos messages de commit, ex : `[RED] Test init laboratory`, `[GREEN] Impl init laboratory`, `[REFACTOR] Clean code`.*

---

## Étape 1 - Démarrage du projet

1.  **Configuration :**
    * Dans le langage de votre choix (parmi la liste de l'atelier précédent), créez un projet console accompagné de la bibliothèque de test unitaire adéquate.
2.  **Création de la classe :**
    * Créez une classe `Laboratory`.
    * Elle doit être initialisée avec une **liste de chaînes de caractères** représentant des substances connues.
3.  **Méthode de consultation :**
    * Cette classe possède une méthode `getQuantity` retournant la quantité décimale en stock d'une substance donnée.
4.  **TDD :**
    * Implémentez chaque cas d'initialisation, y compris les cas d'erreur.

## Étape 2 - Ajout en stock

1.  **Méthode d'ajout :**
    * Codez la méthode `add` qui ajoute en stock une quantité décimale donnée d'une substance (donnée sous forme de chaîne).
2.  **Gestion d'erreurs :**
    * Assurez-vous de traiter correctement les cas d'erreur (ex: substance inconnue, quantité négative, etc.).

## Étape 3 - Notions de réaction et produit

1.  **Mise à jour de l'initialisation :**
    * Ajoutez à l'initialisation une liste de réactions sous la forme d'un dictionnaire.
    * Ce dictionnaire associe un **nom de produit** à une **liste de couples quantité/substance** (la recette).
2.  **Maintenance des tests :**
    * Adaptez les tests existants (Refactor) et vérifiez qu'ils passent toujours (Green).
    * Ajoutez des tests pour les nouveaux cas d'initialisation.
3.  **Mise à jour de l'ajout :**
    * Adaptez la méthode `add` pour permettre l'ajout direct de produits finis en stock.

## Étape 4 - Réalisation de réactions

1.  **Méthode de fabrication :**
    * Ajoutez une méthode `make`.
    * **Entrée :** Un produit et une quantité cible.
    * **Comportement :** Utilise les substances en stock pour obtenir autant de produit que possible.
    * **Sortie :** Retourne la quantité réellement obtenue.
    * **Effet de bord :** Les stocks des substances utilisées diminuent et le stock du produit obtenu augmente en conséquence.
2.  **Réactions complexes :**
    * Gérez enfin des réactions utilisant des produits comme ingrédients, et plus seulement des substances de base.

---

## Bonus (Optionnel)

Prendre en charge le cas de **références circulaires**.

*Exemple :*
* Connaissant les réactions :
    * $A = B + C$
    * $C = 0.2A + D$
* On peut obtenir $A$ avec : $1B$, $0.5C$, $0.1A$ et $0.5D$.

---

## Remise

* **Format :** Lien vers un dépôt public (GitHub / GitLab).
* **Vérification :** L'historique des commits sera audité pour valider la méthode TDD.

---

### Souhaitez-vous que je génère le premier test unitaire (l'étape RED) pour l'initialisation de la classe Laboratory dans un langage spécifique (Python, Java, C#...) ?