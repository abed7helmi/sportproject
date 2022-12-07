# sportproject

# install Consul 
 wget -O- https://apt.releases.hashicorp.com/gpg | gpg --dearmor | sudo tee /usr/share/keyrings/hashicorp-archive-keyring.gpg //////////////
 echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
 //////////////sudo apt update && sudo apt install consul
 
# start consul service 
ip a : to get MyIP//// sudo consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=MyIP
  
# Config-service
. Le service Config-service a été mis en place afin de centraliser les proprities partagés et pour qu'il attribue à chaque service sa configuration à son exécution 
. ce service permet également de modifier la config de chaque service à chaud ( sans arreter le service concerné) grace à un systéme de versionning soit en local soit distant sur git
. on a desactivé l'utilisation de ce service derniérement 
