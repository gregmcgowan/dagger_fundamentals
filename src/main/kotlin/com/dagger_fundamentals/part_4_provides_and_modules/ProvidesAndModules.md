# @Provides and @Module

Our app is expanding, we create a `UserRepo` which will make a network call. We heard about a great http library called OkHttp and we include that in our user repo 

```
class RemoteUserRepo @Inject constructor( 
 private val okHttpClient: OkHttpClient 
) : UserRepo { 
 override fun getUser(userId: String): User { 
 // Pretend we have some okhttp code here 
 return User("User") 
 } 
}
```


We now have a problem. As `OkHttpClient` is not code we can change, we cannot add the `@Inject` annotation so Dagger will know how to create it. In order to get round this we can user the Dagger annotation `@Provides`  

Similar to `@Binds` we define these in a dagger module. As we need to provide some implementation it should be a class or object rather than interface. 

```
@Module 
class NetworkModule { 
 @Provides 
 fun provideOkHttp() = OkHttpClient() 
}
```

Now dagger can create it if it is used as a parameter of `@Inject` annotated constructor like `RemoteUserRepo`. 

We also need to add this new `NetworkModule` to `AppComponent` similar to previous modules. 

## Exercises 
1. Change HomeScreenModule to use @Provides instead of @Binds. Compare the two approaches, when would you use @Binds and @Provides ? 

## Key concepts 

* If we can't tell dagger to create an object using @Inject on the constructor. We can use @Provides. 

* This could be because we don't own the class (e.g it is a third party library)  
