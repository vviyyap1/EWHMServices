# Project Notes

# Make sure dependencies are set properly
* In test mode, use Embedded mongodb and set port separately for every module
* In development mode, start mongodb outside of Intellij. 

### Current Status of Architecture
* CQRS - Command Query Services are Separated
* Gateway Service created for client requests
* Service Discovery implements

### Next set of Tasks
* Implement consistent logging
* Implement Security (Start with Gateway Service and then explore JWT)
* Implement proper CQRS pattern with Event Sourcing

