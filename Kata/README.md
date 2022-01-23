# Objectware Kata

Test Technique Live Coding

Problématique :

Développer un service Web Rest qui permet d'accepter une liste de structure de données appelée Person telle que définie ci-dessous, avec un paramètre du type entier subListSize. a. Les objets Person sont considérés valides en fonction des critères suivants: i. LastName : obligatoire et différent de vide. ii. FirstName : obligatoire et différent de vide.
b. Le service doit pouvoir diviser (Split) la liste des Person en une seule liste de sous-listes dont la taille de chacune des sous-listes est égale au maximum à subListSize.

Filtrer et Sauvegarder tous les objets Person reçus dans une table (DB H2 par exemple) comme suit :
a. Filtrer la liste des Person lorsque Age > 40. b. Sauvegarder la liste dans la table. c. La liste filtrée doit apparaître dans un fichier texte avec un chemin paramétrable. i. On doit trier la liste avant qu'elle ne soit écrite en se basant sur le triplet suivant : (AgeLastName FirstName)

Un ensemble de tests unitaires pour tester le service développé.
Exemple du body de la requête : { "Persons": [ { "FirstName": "P1", "LastName": "P2", "Age": 10 }, { "FirstName": "P1", "LastName": "P2", "Age": 10 } ], "SubListSize": 2 }

# Remarques Yassin

Les services web sont disponibles avec l'url : http://localhost:8080

La base de données a été configuré avec les paramétres dans le fichier : application.properties

Le chemin paramétrable pour le fichier de text est alimenté avec le champ "path_file" dans le fichier : application.properties

Le fichier personsData.json est un exemple du body de requête pour les tests unitaires.

