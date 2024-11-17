# Kafka PoC 🛠

At the moment, we will have 1 action to stream : 
* transfer

Web-gateway represents the unique entry point of the mobile and web app, if user make money transfer through their UI, it will produce a transfer message in 'transfer' topic  
Funds-processor maintains the business logic around money transfer, transactions... It's listening messages from transfer topic to adjust customer bank accounts (persisted in MSSQL DB)


## Technical aspects  🐳

Requirement : Docker, docker-compose

Go to root folder of the project and execute the following commands, 
it will run zookeeper, kafka broker 1, kafka ui, mssql servers, schema-registry (avro) :

```
docker-compose -f ./env/docker-compose.yml --env-file ./env/.env --project-name kafka-poc-infra up -d
```

## Useful links 🔗
One docker-compose ended successfully, you can use following links to facilitate use of different apps, 
visualize contracts and kafka messages :  

* Funds processors contract : http://localhost:8084/funds-processor/swagger-ui.html (useful to check balance on account)
* Web-gateway contract : http://localhost:8085/web-gateway/swagger-ui.html (useful to send transfer money action, that will produce message in kafka)
* Kafka UI : http://localhost:9090 (monitor kafka cluster)
* Kafka UI : http://localhost:9090/ui/clusters/kafka-1/all-topics/transfer/messages (list messages sent on transfer topic )


## Tips :💡
To generate TransferMoneyRequestDto that is used to produce and consume message on Transfer topic (through Avro), use following command on both web-gateway and funds-processor :
```
mvn clean install
```


## Todo list  📝
* Configure multiple broker (configuration issue when I tried it)
