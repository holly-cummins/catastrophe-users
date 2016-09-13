# Cat-astrophe Microservices Sample
# Users Service


# About this sample

This sample is one microservice in a [larger sample 
microservices application](http://github.com/holly-cummins/catastrophe-microservices). I've written a [blog post](https://developer.ibm.com/wasdev/blog/2016/06/01/putting-micro-microservices/) describing the overall sample. 

This service tracks users and scores in a database. 

This application is designed to run on a [raspberry pi](http://www.linksprite.com/linksprite-pcduino/) (and optionally [Bluemix](http://bluemix.net)), and runs on [WebSphere Liberty](http://wasdev.net). 

**[License information](LICENSE.txt)** 

## Getting started 

### Eclipse integration 

To set up Eclipse projects, run 

    gradle clean
    gradle eclipse

### Running the server locally (from the command line) 

Run

    gradle runServer

The application should be available on http://localhost:9080.

### Deploying to a single board computer 

To create a zip with the application and all dependencies (including the server), run 

    gradle packageServer


### Deploying to Bluemix 

To create a war, run `gradle` from the catastrophe-scoring folder.

This can then be pushed to Bluemix with 

    cf push -p build/libs/catastrophe-users.war

# Dependencies 

This sample uses [WebSphere Liberty](http://wasdev.net), Java EE interfaces, and the [webjars] bundles of the [Bootstrap UI framework](http://getbootstrap.com). 

# More information 

* [A Blog post about microservices and this sample](https://developer.ibm.com/wasdev/blog/2016/06/01/putting-micro-microservices/)
* [The talk which accompanies this demo](http://www.slideshare.net/HollyCummins/microservices-from-dream-to-reality-in-an-hour")
* [Liberty and microservices](https://developer.ibm.com/wasdev/docs/microservices/)
