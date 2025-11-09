# Atelier5_JAX_RS (Backend)

Ce projet est une API RESTful conçue pour gérer des stations-service, les types de carburants et l'historique de leurs prix. Elle est développée en Java avec JAX-RS et JPA (Hibernate).

## Technologies utilisées

* **Java 21**
* **Jakarta EE 10**
    * **JAX-RS (Jakarta RESTful Web Services)** : Pour la création des endpoints REST.
    * **CDI (Contexts and Dependency Injection)** : Pour l'injection de dépendances (ex: `StationService`).
    * **JPA (Jakarta Persistence API)** : Pour l'ORM (Object-Relational Mapping).
* **Hibernate 7** : Implémentation de JPA.
* **Maven** : Gestion des dépendances et du build.
* **MySQL** : La base de données cible (configurée via `persistence.xml`).
* **Lombok** : Pour réduire le code "boilerplate" dans les entités.

## Prérequis

1.  **JDK 21** ou supérieur.
2.  **Maven 3.9+**.
3.  **Un serveur d'applications Jakarta EE 10** (ex: WildFly 30+ ou JBoss EAP).
4.  **Une base de données MySQL** accessible.

## Configuration

1.  **Base de données** :
    * Assurez-vous que votre serveur MySQL est en cours d'exécution.
    * Le fichier `persistence.xml` est configuré pour utiliser la source de données (datasource) JTA `java:jboss/datasources/JaxRrsDB`.
    * La propriété `hibernate.hbm2ddl.auto` est définie sur `update`, ce qui signifie qu'Hibernate tentera de mettre à jour le schéma de la base de données au démarrage.

2.  **Serveur d'applications (WildFly/JBoss)** :
    * Vous devez configurer la datasource `java:jboss/datasources/JaxRrsDB` dans votre serveur pour qu'elle pointe vers votre base de données MySQL avec les bons identifiants.

## Lancement

1.  **Compiler le projet** :
    ```bash
    # Utiliser le wrapper Maven inclus
    ./mvnw clean package
    ```
    ou (si vous avez Maven installé globalement)
    ```bash
    mvn clean package
    ```

2.  **Déploiement** :
    * Cette commande génère un fichier `.war` dans le répertoire `target/` (ex: `Atelier5_JAX_RS-1.0-SNAPSHOT.war`).
    * Déployez ce fichier `.war` sur votre serveur d'applications (WildFly, JBoss, etc.).

## Points de terminaison (Endpoints)

L'application est servie sous le chemin de base `/api` (défini dans `HelloApplication.java`).

### Stations (`/api/stations`)

* `GET /` : Récupère la liste de toutes les stations.
* `GET /{id}` : Récupère une station par son ID.
* `POST /` : Crée une nouvelle station.
* `PUT /{id}` : Met à jour une station existante.
* `DELETE /{id}` : Supprime une station.

### Carburants (`/api/carburants`)

* `GET /` : Récupère la liste de tous les carburants.
* `GET /{id}` : Récupère un carburant par son ID.
* `POST /` : Crée un nouveau carburant.
* `PUT /{id}` : Met à jour un carburant.
* `DELETE /{id}` : Supprime un carburant.

### Prix (`/api/stations/{id}/prices`)

* `GET /api/stations/{stationId}/prices` : Récupère l'historique des prix pour une station donnée.
* `POST /api/stations/{stationId}/prices/{carburantId}` : Ajoute un nouveau prix pour un carburant dans une station.
    * **Body attendu** : `{"date": "YYYY-MM-DD", "prix": 1.99}`

---
