# Simple use of scopes

Our app is growing at an incredible rate. 

We add a new feature called `FeatureScreen` and we realise creation of our `OkHttpClient` is expensive. Therefore we want to  ensure that there is only one instance of `OkHttpClient` and it is shared throughout the app. 

We can do this by adding a scope annotation to our `OkHttpClient` provides. The `@Singleton` annotation is also part of `javax.inject` as well so it seems appropriate to use that.

```
@Module 
class NetworkModule { 
 @Singleton 
 @Provides 
 fun provideOkHttp() = OkHttpClient() 
}
```


If we add this and try and compile we will get the following error  

```

[ksp] [Dagger/IncompatiblyScopedBindings] com.dagger_fundamentals.part_5_simple_use_of_scopes.AppComponent (unscoped) may not reference scoped bindings:
    @Singleton @Provides okhttp3.OkHttpClient com.dagger_fundamentals.part_5_simple_use_of_scopes.NetworkModule.provideOkHttp()

```

This tells us another important part of understanding scopes. It is not enough to use the scoped annotation on the provides method, we must also  annotation the component we wish to use it in.  

The two things combined give us the ability to control the creation of different objects.  

```
@Singleton
@Component(
    modules = [
        NetworkModule::class,
        UserRepoModule::class,
        FeatureRepoModule::class,
        HomeScreenModule::class,
        FeatureScreenModule::class,
    ],
)
interface AppComponent {
    fun inject(app: App)
}

```

It is possible to create our own custom scopes to signify some kind of meaning to the use of it. We will look at this later. 

## Exercises 

1. Remove the `@Singleton` annotation from `provideOkHttp` and run the application. Can you explain the output? What is the difference in the generated  code for both scenarios? 

2. Is there any other build in scope annotations? When should they be used? 


## Key concepts 

* Adding a `@Scope` annotation on a `@Provides` ensures that the same instance of the object is used in the `@Component` with the same annotation. 

* `@Components` with a `@Scope` annotation may only reference un-scoped dependencies or dependencies with the same scope. 
