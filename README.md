# OKE2018-CHALLENGE-Task-2
An application that solves OKE2018 challenge task 2 - Broader Named Entity Identification and Linking
## Broader Named Entity Identification and Linking
The task comprises the identification of named entities in sentences and the disambiguation of the identified entities to the DBpedia knowledge base. A competing system is expected to identify elements in a given text by its start and end index, further to generate an RDF formalizing the linking of the identified entities to the DBpedia knowledge base.
- more about task: https://project-hobbit.eu/challenges/oke2018-challenge-eswc-2018/tasks/
- test data: http://hobbitdata.informatik.uni-leipzig.de/oke2018-challenge/Task2/A/training.tar.gz

## Running app
1. Get java 8 jdk and set JAVA_HOME environment variable.
2. Download maven and add maven to PATH environment variable.
3. Download code and run following commands:
```
mvn clean install
java -jar target/OKEChallenge-x.x.x-SNAPSHOT.jar
```
Your app should now be running on [localhost:8080](http://localhost:8080/)

Application is also available on heroku: [Open app](https://oke2018-challenge-task-2.herokuapp.com/)
