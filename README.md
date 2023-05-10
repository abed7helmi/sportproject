# sportproject Microservice
spring/rabbitMQ/mango/redis/JPA/Hibernate/MQTT/Docker/SpringCloud/ClusterMysql/.....

# lancement projet 

sudo docker-compose up


# Config-service
. Le service Config-service a été mis en place afin de centraliser les proprietes partagées et pour qu'il attribue à chaque service sa configuration à son exécution 
. ce service permet également de modifier la config de chaque service à chaud ( sans arreter le service concerné) grace à un systéme de versionning et un repo soit en local soit distant sur git
. on a desactivé l'utilisation de ce service derniérement 

# Architecture
![SalleDeSport_Archi1](SalleDeSport_Archi1.bmp)


