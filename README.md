# Project Software Frameworks: KDA Profile Tracker for GAME APIs
https://github.com/LeonKattendick/software-frameworks

### Startup

1. Start docker
2. docker compose with profile: production
`docker compose --profile production up`

## Project:
Tracker auf Game APIs von Valve (Dota2) und Riot (League of Legends)
Anzeige von Informationen wie KDA Count und Profil.

### Kafka Config
--> docker-compose.yml

Replications: 1

Broker Count: 1

Partitions: auto

### Backend
- KafkaConsumerService
- JsonGameDataDeserializer
- Commons Module
  - Constants: Topic Names ("tracker" atm)
  - JsonGameData: DTO

### Crawler
- KafkaProducerService
- REST Controller (SampleData atm)

### Kafka Module
not used anymore (testing)



