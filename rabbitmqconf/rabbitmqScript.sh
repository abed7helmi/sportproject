#!/bin/sh


#create network for the two rabbits instances
sudo docker network create rabbits 

#create two instance

sudo docker run -d --rm --net rabbits --hostname rabbit-1 --name rabbit-1 -p 5672:5672 -p 15672:15672 -e RABBITMQ_ERLANG_COOKIE=WAZIZIKZHSJPMASZAY rabbitmq:3.8-management

sudo docker run -d --rm --net rabbits --hostname rabbit-1 --name rabbit-1 -p 5673:5672 -p 15673:15672 -e RABBITMQ_ERLANG_COOKIE=WAZIZIKZHSJPMASZAY rabbitmq:3.8-management

#join node 2 to the cluster
docker exec -it rabbit-2 rabbitmqctl stop_app
docker exec -it rabbit-2 rabbitmqctl reset
docker exec -it rabbit-2 rabbitmqctl join_cluster rabbit@rabbit-1
docker exec -it rabbit-2 rabbitmqctl start_app
docker exec -it rabbit-2 rabbitmqctl cluster_status

#### queue mirroring

# enable federation plugin
docker exec -it rabbit-1 rabbitmq-plugins enable rabbitmq_federation 
docker exec -it rabbit-2 rabbitmq-plugins enable rabbitmq_federation

#into rabbit 1
docker exec -it rabbit-1 bash

rabbitmqctl set_policy ha-fed \
    ".*" '{"federation-upstream-set":"all", "ha-mode":"nodes", "ha-params":["rabbit@rabbit-1","rabbit@rabbit-2"]}' \
    --priority 1 \
    --apply-to queues








