@echo off
REM Vérifier les prérequis
where mvnw >nul 2>&1 || (
    echo Maven non trouvé. Assurez-vous que Maven est installé et ajouté au PATH.
    pause
    exit /b 1
)

REM ======= MySQL Container =======
echo Lancement du conteneur MySQL...
docker run -dit -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 --name mysql-test mysql

echo Vérification du conteneur MySQL...
docker ps

REM ======= Nginx Container =======
echo Lancement du conteneur Nginx...
docker run -dit -p 8080:80 --name nginx-test nginx:alpine
echo Vérification de Nginx : http://localhost:8080

REM ======= Création des images (message) =======
echo Clonage du microservice message...
git clone https://gitlab.com/cda-a3/microservices-docker.git message
cd message
mvnw package -DskipTests
docker build -t message .

echo Lancement du conteneur message...
docker run -d -p 8081:8081 --name message-ms message
cd ..

REM ======= Registre Docker local =======
echo Création d'un registre Docker local...
docker run -d -p 5000:5000 --name registry registry:2
docker tag message localhost:5000/message
docker push localhost:5000/message

REM ======= Utilisation des volumes et réseau =======
echo Création réseau et volume...
docker network create name-net
docker volume create name-db-vol

echo Lancement conteneur MySQL pour 'name'...
docker run -d --name name-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test --mount type=volume,src=name-db-vol,dst=/var/lib/mysql --network name-net mysql

echo Clonage du microservice name...
cd ..
git clone https://gitlab.com/cda-a3/microservices-docker.git name
cd name
mvnw package -DskipTests
docker build -t name .

docker run -d --name name-ms -p 8082:8082 --network name-net -e spring.datasource.url=jdbc:mysql://name-db:3306/test -e spring.datasource.password=root name

cd ..

REM ======= Préparation microservice hello =======
echo Préparation microservice hello...
git clone https://gitlab.com/cda-a3/microservices-docker.git hello
cd hello
mvnw package -DskipTests
docker build -t hello .
cd ..

REM ======= docker-compose =======
echo Génération docker-compose.yml...
echo services: > docker-compose.yml
echo   message: >> docker-compose.yml
echo     image: message >> docker-compose.yml
echo     ports: >> docker-compose.yml
echo       - 8081:8081 >> docker-compose.yml

echo   name: >> docker-compose.yml
echo     image: name >> docker-compose.yml
echo     ports: >> docker-compose.yml
echo       - 8082:8082 >> docker-compose.yml
echo     depends_on: >> docker-compose.yml
echo       - name_db >> docker-compose.yml

echo   name_db: >> docker-compose.yml
echo     image: mysql >> docker-compose.yml
echo     environment: >> docker-compose.yml
echo       MYSQL_ROOT_PASSWORD: root >> docker-compose.yml
echo       MYSQL_DATABASE: test >> docker-compose.yml
echo     volumes: >> docker-compose.yml
echo       - name-db-vol:/var/lib/mysql >> docker-compose.yml

echo   hello: >> docker-compose.yml
echo     image: hello >> docker-compose.yml
echo     ports: >> docker-compose.yml
echo       - 8083:8083 >> docker-compose.yml
echo     depends_on: >> docker-compose.yml
echo       - name >> docker-compose.yml
echo       - message >> docker-compose.yml

echo volumes: >> docker-compose.yml
echo   name-db-vol: >> docker-compose.yml

echo Lancement docker-compose...
docker-compose up -d

docker-compose ps

pause"}

---

**À faire impérativement :**  
- Installe Maven depuis : [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)  
- Vérifie que la variable d’environnement `MAVEN_HOME/bin` est bien ajoutée au `PATH` Windows  
- Vérifie que Docker Desktop est démarré.
