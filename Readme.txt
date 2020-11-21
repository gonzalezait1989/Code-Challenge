REQUIREMENTS
============
.- First of all, install 'docker' in your computer.
.- Rename files src/main/resources/static/*.js_s to *.js


COMMENTS
========
.- This is a Spring Boot Application with all needed dependencies included in the POM file.
.- You have a docker file to run the Application. The command to run your implementation is
docker-compose up.
.- Modify properties to change the host to connect to the local database (docker). It must be your local IP in your current network.
.- Modify properties to change . The host to connect to Rabbitmq (docker) must be your local IP in your current network.
.- If not connect using the local network ip you can discover the IP using this docker command
	docker network inspect bridge
.- The host to connect to Rabbitmq (docker) must be your local IP in your current network.
.- To remove all docker installation (including prior dockers) do (in terminal):
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi -f $(docker images -q)
docker volume prune

EXERCISES
=========
1.- Implement REST API to Create, Retrieve (all, byId, byEmail), Update & Delete Account from/to Database.
The account can have multiple addresses & they (the addresses) have to be showed in the data returned (only for retrieve methods).
Account entity should have (at least) the next attributes:
    name   	    (Alphanumeric)
    email	    (Alphanumeric)
    age		    (Number)
    addresses   (Collection of account address)

Fields of Address entity:
    address (Alphanumeric)

2.- Create a simple REST API endpoint using Spring Integration DSL (use MessagingGatewaySupport to create the endpoint). The funcionality is retrieve a random Toy with the "id" provided as parameter.
The endpoint is    GET /toy/{id}
and the data model to return is a toy with this fields:
String id;
String name;

3.- Create REST service to retrieve Pokemons (the name of the endpoint must be '/pokemons') that its name starts with {parameter}. The result is a JSON containing any field from the pokemon.  You have to discover the external API to retrieve the pokemons. IOt is included in the project !!.
4.- Implement any Basic Security only for the Pokemons REST service. The endpoint for accounts (CRUD) remains without security.
5.- We want to execute 3 free processes in parallel & we want to execute a 4th process when the others 3 has finished.
Could you implement a sample REST API (only GET method with endpoint "/thread") to demostrate this behaviour ?
Could you implement a Basic Security for the prior REST method ?

6.- Create Unit Testing for any class of the Implemented Service Layer.
7.- Create the WEB page to manage (CRUD) the accounts.
8.- This exercise is based on Request / Response (synchronous process) using queues.
Create the implementation of a new Pokemons REST API to retrieve pokemons that name starts with concrete string. 
The business logic (retrieve the pokemons) must be executed in a Consumer of Rabbitmq queue (free name). 
The name of the endpoint must be '/pokemons/queue'.  it means that the logic part should be executed in a consumer of queue. 
The queue must be created dynamically (without human intervention).
The consumer & the producer can be in the same maven module. Remember that the host to connect to Rabbitmq (docker) must be your local IP in your current network.
Dont use cache strategy (save pokemons in cache) to solve the excercise. 

The signature of the consumer process should be similar to.

	@StreamListener(????)
	@Other Annotations??????
	public Message<PokemonVO> processPutAccount(Message<String> name)
