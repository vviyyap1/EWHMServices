# Project Notes

### Make sure dependencies are set properly
* In test mode, use Embedded mongodb and set port separately for every module
* In development mode, start mongodb outside of Intellij. 

### Order of microservices start up is important
* EurekaDiscoveryService
* All Microservices other than GatewayService
* Gateway Service

### Current Status of Architecture
* CQRS - Command Query Services are Separated
* Gateway Service created for client requests
* Service Discovery implements
* Unit and Integration Tests implemented
* Logging AOP implemented

### Next set of Tasks
* Implement Security (Start with Gateway Service and then explore JWT)
* Implement proper CQRS pattern with Event Sourcing

