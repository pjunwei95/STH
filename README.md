#Skills Taxonomy Harmoniser (STH)
This is the GitHub repo for the Skills 
Taxonomy Harmoniser (STH) Proof-of-Concept (PoC).

It is a CLI application which harmonises Skills.

It is designed under the Capability Development Division (CDD) in Skills-Future Singapore (SSG) with the Skills Framework in mind. 

#Architecture:

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

Currently the API-key is not provided in the program for security purposes.

You will also need to setup your own instance of MongoDB as a database for the application to access.
# To install:
1. Clone the repo to your destination directory

2. Configure the "api_key.txt" file.
    * Create/Modify a new txt file labelling it as "api_key.txt" 
    * Insert the API key inside the txt file. (You need to create an account in ParallelDots in order to call the API)

3. Start the mongodb server by running `mongod` in CMD

4. Now to link the application to your server, start the application.

5. Start the application by running the Java class SkillsTaxonomyHarmoniser.java 