@echo off
REM Script de nettoyage complet de l'environnement Docker

REM Arrêter et supprimer les containers existants
echo Arrêt et suppression des conteneurs...
docker container stop mysql-test nginx-test message-ms registry name-db name-ms hello-ms
docker container rm mysql-test nginx-test message-ms registry name-db name-ms hello-ms

REM Supprimer les images créées lors de l'atelier
echo Suppression des images Docker...
docker image rm message name hello localhost:5000/message nginx:alpine registry:2

REM Suppression des volumes et des réseaux
echo Suppression du volume et du réseau...
docker volume rm name-db-vol
docker network rm name-net

REM Nettoyage via docker-compose si nécessaire
echo Arrêt et suppression des services docker-compose...
docker-compose down -v

REM Suppression des répertoires clonés lors de l'atelier
echo Suppression des dossiers clonés...
rmdir /s /q message
rmdir /s /q name
rmdir /s /q hello

echo Environnement nettoyé, vous pouvez relancer l'atelier depuis le début.

pause