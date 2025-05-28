# ALTEN SHOP back

Prérequis
Java 17+ (ou version compatible avec ton projet)
Maven 3+
Base de données configurée (H2) 

Construire le projet avec Maven
mvn clean install

Configurer la base de données dans src/main/resources/application.properties

Lancer l’application
mvn spring-boot:run	

Utilisation
L’API est accessible à l’adresse : http://localhost:8080/api
Swagger UI pour la documentation et test : http://localhost:8080/swagger-ui/index.html
la BD H2 est accessible à l’adresse : http://localhost:8080/h2-console

Endpoints principaux
POST /account : création d’un compte utilisateur
POST /token : connexion et récupération d’un token JWT

GET /api/products : liste des produits (authentification requise)
POST /api/products : création d’un produit (seulement admin@admin.com)
PATCH /api/products/{id} : modification partielle (admin uniquement)
DELETE /api/products/{id} : suppression (admin uniquement)
