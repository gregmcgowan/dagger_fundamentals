# `@Inject` 

We want Dagger to create objects in our app for us as it will help us to scale our application and refactor as we do so. We do this by describing a graph of our application objects, how to create them and the relationships between them. 

The graph is a directed acyclic graph (DAG), which sounds fancy but is a relatively simple concept to understand. 

A DAG is a type of graph in which it's impossible to come back to the same node by traversing the edges. 
The nodes are objects in our application and the edges and their direction indicate a dependency. 

In the simple example we have, we can (and will) model the application as the following DAG. 
App has a dependency on HomeScreenPresenter which has a dependency on HomeScreen. 

DAG is where the library gets it's name, a creator of DAGs or a DAGger 
We'll start with the HomeScreen as it has no dependencies. 

In order for us to tell Dagger how to create a class of a type it we simply add the `@Inject` annotation on the constructor. 

```
class HomeScreen @Inject constructor() {
    fun show() {
        print("Showing main screen!")
    }
}
```

`@Inject` is not actually part of the Dagger API but part of [javax.inject](https://docs.oracle.com/javaee/6/api/javax/inject/package-summary.htmll) 
We next add the `@Inject` to the HomeScreenPresenter

```
class HomeScreenPresenter @Inject constructor(
    private val homeScreen: HomeScreen
) {
    fun present() {
        homeScreen.show()
    }
}
```


In order for us to put everything together we must define a starting point. 
This is done via the `@Component` annotation on an interface and a single parameter method (known as a Members-injection method) which takes the type we want dagger to inject to.  

```
@Component
interface AppComponent {
    fun inject(app: App)
}
```


We can think of a component as the graph of the dependencies in our application. 

Although we haven't explicitly linked our HomeScreen, HomeScreenPresenter and App Dagger is able to construct the graph by looking at the starting point (App) and then subsequently follow any dependancies from there (HomeScreenPresenter and then HomeScreen) 

Dagger will then generate code which we can use to inject those objects. All generated code will be prefixed with Dagger. We then call this generated code  and it will initialise our graph for us. 


```
class App {
    @Inject
    lateinit var homeScreenPresenter: HomeScreenPresenter

    fun start() {
        DaggerAppComponent
            .create()
            .inject(this)

        homeScreenPresenter.present()
    }
}
```
The app should build and run successfully! 

# Exercises 

1. What happens when we remove `@Inject` from `HomePresenter` or `HomeScreen`? 
2. Can you find where `App.homeScreenPresenter` is set? 

# Key concepts 

* `@Inject` on a class constructor tells Dagger how to create an object. If the constructor has parameters then Dagger must be able to know how to  create the parameters type as well. 

* `@Inject` on a class field type tells dagger that we want it to create an object for us. 

* In order for us to define what classes (and by extension types) we want dagger to create we must define a graph by creating an interface annotated by `@Component` with a Members-injection method 

* Members-injection methods have a single parameter and inject dependencies into each of the Inject-annotated fields and methods of the passed  instance.  

* When the project is complied, dagger will validate the graph to ensure all dependencies can be provided and there is no cyclic dependencies. If  successful it will create the generated code we can call to initialise our objects. 
