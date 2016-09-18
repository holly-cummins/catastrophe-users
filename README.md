# Cat-astrophe Microservices Sample
# Users Service


# About this sample

This sample is one microservice in a [larger sample 
microservices application](http://github.com/holly-cummins/catastrophe-microservices). If you’re wondering about the names – all good internet content involves cats. This sample was written
to be used as a live demo, and I have some experience of live demos, so I called it – of course – cat-astrophe.

This service tracks users and scores in a database. 

This application is designed to run on a [raspberry pi](http://www.linksprite.com/linksprite-pcduino/) (and optionally [Bluemix](http://bluemix.net)), and runs on [WebSphere Liberty](http://wasdev.net). 

**[License information](LICENSE.txt)** 

## Getting started 

Press this button to get your own copy of the sample running in Bluemix. 

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https://github.com/holly-cummins/catastrophe-scoring)

### Eclipse integration 

To set up Eclipse projects, run 

    gradle clean
    gradle eclipse

### Running the server locally (from the command line) 

Copy `usr/servers/catastrophe-users/local-config/jpa.xml` to `usr/servers/catastrophe-users/`. This sets up 
configuration for an in-memory Derby datasource, which isn't required in 
Bluemix. 

Run

    gradle runServer

The application should be available on http://localhost:9080.

### Deploying to a single board computer 

Copy `usr/servers/catastrophe-users/local-config/jpa.xml` to `usr/servers/catastrophe-users/`. This sets up 
configuration for an in-memory Derby datasource, which isn't required in 
Bluemix. 

To create a zip with the application and all dependencies (including the server), run 

    gradle packageServer


### Deploying to Bluemix 


If you used the deploy to Bluemix button, a pipeline will have been set up which automatically deploys to Bluemix. 

To create a war, run `gradle` from the catastrophe-scoring folder.

This can then be pushed to Bluemix with 

    cf push -p build/libs/catastrophe-users.war

# Dependencies 

This sample uses [WebSphere Liberty](http://wasdev.net), Java EE interfaces, and the [webjars] bundles of the [Bootstrap UI framework](http://getbootstrap.com). 

# More information 

* [A Blog post about microservices and this sample](https://developer.ibm.com/wasdev/blog/2016/06/01/putting-micro-microservices/)
* [The talk which accompanies this demo](http://www.slideshare.net/HollyCummins/microservices-from-dream-to-reality-in-an-hour")
* [Liberty and microservices](https://developer.ibm.com/wasdev/docs/microservices/)
