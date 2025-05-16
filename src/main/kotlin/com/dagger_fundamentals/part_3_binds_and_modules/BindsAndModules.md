# `@Binds` and `@Module`
We read that interfaces were cool in a medium article on the internet so we want to use them in our app. 

So we created an interface for the `HomeScreen` and `HomeScreenPresenter`

```
interface HomeScreenContract {
    interface Presenter {
        fun present()
    }

    interface Screen {
        fun show()
    }
}

class HomeScreenPresenter @Inject constructor(
    private val mainScreen: HomeScreenContract.Screen
) : HomeScreenContract.Presenter {
    override fun present() {
        mainScreen.show()
    }
}

class HomeScreen @Inject constructor() : HomeScreenContract.Screen {
    override fun show() {
        print("Showing main screen")
    }
}
```


But now Dagger does not know which implementation of Presenter to use. 

It will look to create a type of `HomeScreenContract.Screen` and `HomeScreenContract.Presenter` but the `@Inject` is applied to HomeScreen and  HomeScreenPresenter. 

Fortunately, we can use the `@Binds` to associate an interface with an implementation. 

We define our `@Binds` association in a dagger module. 

Modules can be an interface or class annotated with `@Module` annotation. 

A module represents allows us to encapsulate contributions to the dependency graph. We can organise related dependencies in the same module and  potentially they can be reused in different components. 
As we have a couple of bindings for our HomeScreen. It follows that we can create a HomeScreenModule 

```
@Module
interface HomeScreenModule {
    @Binds
    fun bindPresenter(impl: HomeScreenPresenter): HomeScreenContract.Presenter

    @Binds
    fun bindScreen(impl: HomeScreen): HomeScreenContract.Screen
}
```


We can consider modules as something that helps the developer to organise the code. 

Dagger doesn't care particularly if the bindings are in  1 module or several, as long as it can validate the graph it will be happy. 

Creating the module by itself will not do anything, we need to add it to the Application Component. 

The @Component annotation allows us to specify the  modules it uses. 

```
@Component(modules = [HomeScreenModule::class])
interface AppComponent {
    fun inject(app: App)
}
```

This provides us with an overview of the dependencies in our application. 

## Exercises
1. HomeScreenModule is an interface. Where is the implementation of this? 

## Key concepts 
* We can associate an implementation with an interface using `@Binds` 

* We can encapsulate contributions to the application graph by using `@Module` 

* `@Components` can have several modules and they are included in the annotation deceleration @Component (modules = ....) 
