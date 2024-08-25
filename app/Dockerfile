# Étape 1 : Utiliser une image Maven pour construire l'application
FROM maven:3.8.5-openjdk-11 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le reste du code source et compiler l'application
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image de Tomcat pour déployer l'application
FROM tomcat:9.0-jdk11-openjdk

# Copier le fichier WAR généré dans le dossier webapps de Tomcat
COPY --from=build /app/target/app.war /usr/local/tomcat/webapps/

# Exposer le port 8080
EXPOSE 8080

# Commande pour démarrer Tomcat
CMD ["catalina.sh", "run"]