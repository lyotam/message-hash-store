# message-hash-store
A Web server app which hashes and stores messages

## Features
message-hash-store has the following features:
* Hash and store a message
* Retrieve a message previously stored by Hash

## How to run this app:
### Run using docker-compose:
```
$ cd message-hash-store
$ docker-compose up -d
```
### Interact with the app:
examples:
```
curl -X POST -H "Content-Type: application/json" -d '{"message": "foo"}' "http://localhost:8080/messages"
{"digest":"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae"}

curl http://localhost:8080/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae
{"message":"foo"}

curl http://localhost:8080/messages/aaa
{"err_msg":"Message not found"}
```

## Performance & Scaling:
The main bottleneck of the application is the DB (mongoDb here).
To handle this we scale mongoDB horizontally with sharding using the sha256 hash as the shard key. 
In addition, other improvements are usage dependant - an in memory cache is also a possible solution if the same messages are requested often.
Furthermore, we can also scale the message-hash-store app horizontally and use a load balancer to route the traffic evenly.