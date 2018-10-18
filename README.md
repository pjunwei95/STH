# Skills Taxonomy Harmoniser (STH)
This is the GitHub repo for the Skills 
Taxonomy Harmoniser (STH) Proof-of-Concept (PoC).

It is a CLI application which harmonises Skills.

It is designed under the Capability Development Division (CDD) in Skills-Future Singapore (SSG) with the Skills Framework in mind. 

# Architecture:

* Standalone local CLI application
* Design: Model-View-Controller (MVC) Framework
* Written in: Java (>2K LoC)
* Database: MongoDB
* Utilising: 
    * VCS: Github
    * A.I.: ParallelDots API
    * Build: Gradle
* Libraries: 
    * Rest API: OkHttp3 
    * Parsing Response: SimpleJson
    * Java-Json Communication: Jackson
    * Java-MongoDB Communication: MongoJack (built on top of MongoJavaDriver)


# Manual Configurations:

You need to create an account in ParallelDots in order to call the API.
Currently the API-key is not provided in the program for security purposes.

You will also need to setup your own instance of MongoDB as a database for the application to access.

# Build:

* This app utilise Gradle as the build automation tool
* If you opt not to use Gradle, you will have to manage the dependencies below, or manually download the depending '.jar' files

# Dependencies:
* OkHttp3
    * com.squareup.okhttp3:okhttp:3.11.0
* Jackson
    * com.fasterxml.jackson.core:jackson-core:2.9.6
    * com.fasterxml.jackson.core:jackson-annotations:2.9.6
    * com.fasterxml.jackson.core:jackson-databind:2.9.6
* Simple-Json
    * org.json:json:20180813
* Mongo-Java Driver
    * org.mongodb:mongo-java-driver:2.12.3
* MongoJack
    * org.mongojack:mongojack:2.7.0

# To install:
1. Clone the repo to your destination directory

2. Configure the "api_key.txt" file.
    * Go to the directory:
        * (YOUR_OWN_DIRECTORY)\Programs\Git\STH
    * Create/Modify a new txt file labelling it as "api_key.txt" 
    * Insert the API key inside the txt file. (You need to create an account in ParallelDots in order to call the API)

3. Start the mongodb server by running `mongod` in CMD

4. Now to link the application to your server, start the application.

5. Start the application by running the Java class SkillsTaxonomyHarmoniser.java
    * This can be found in the directory of the repo:
        * (YOUR_OWN_DIRECTORY)\STH\app\src\main\java\com\main 