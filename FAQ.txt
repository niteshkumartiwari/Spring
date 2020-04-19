What is a Spring Bean?

A "Spring Bean" is simply a Java object.

When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

Spring Beans are created from normal Java classes .... just like Java objects.

In summary, whenever you see "Spring Bean", just think Java object. :-)	

=================================================================

Why do we specify the Coach interface in getBean()?

For example:

Coach theCoach = context.getBean("myCoach", Coach.class); 

---

Answer

When we pass the interface to the method, behind the scenes Spring will cast the object for you.

context.getBean("myCoach", Coach.class)  

However, there are some slight differences than normal casting.

From the Spring docs:

Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).


=============================================================

Special Note about init and destroy Method Signatures

When using XML configuration, I want to provide additional details regarding the method signatures of the init-method  and destroy-method .

Access modifier
The method can have any access modifier (public, protected, private)

Return type
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

Method name
The method can have any method name.

Arguments
The method can not accept any arguments. The method should be no-arg.

================================================================

Section 1.9.2: Autowired

As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. However, if several constructors are available, at least one must be annotated to teach the container which one to use.

==================================================================

Question:

During All Java Configuration, how does the @Bean annotation work in the background?



Answer

This is an advanced concept. But I'll walk through the code line-by-line.

For this code:

  @Bean 
  public Coach swimCoach() {   
   SwimCoach mySwimCoach = new SwimCoach();   
   return mySwimCoach; 
  }
At a high-level, Spring creates a bean component manually. By default the scope is singleton. So any request for a "swimCoach" bean, will get the same instance of the bean since singleton is the default scope.



However, let's break it down line-by-line

@Bean


The @Bean annotation tells Spring that we are creating a bean component manually. We didn't specify a scope so the default scope is singleton.

 public Coach swimCoach(){
This specifies that the bean will bean id of "swimCoach". The method name determines the bean id. The return type is the Coach interface. This is useful for dependency injection. This can help Spring find any dependencies that implement the Coach interface.

The @Bean annotation will intercept any requests for "swimCoach" bean. Since we didn't specify a scope, the bean scope is singleton. As a result, it will give the same instance of the bean for any requests.



 SwimCoach mySwimCoach = new SwimCoach();
This code will create a new instance of the SwimCoach.



 return mySwimCoach;
This code returns an instance of the swimCoach.

----



Now let's step back and look at the method in it's entirety.

 @Bean 
 public Coach swimCoach() {   
   SwimCoach mySwimCoach = new SwimCoach();   
   return mySwimCoach; 
 }


It is important to note that this method has the @Bean annotation. The annotation will intercept ALL calls to the method "swimCoach()". Since no scope is specified the @Bean annotation uses singleton scope. Behind the scenes, during the @Bean interception, it will check in memory of the Spring container (applicationContext) and see if this given bean has already been created.

If this is the first time the bean has been created then it will execute the method as normal. It will also register the bean in the application context. So that is knows that the bean has already been created before. Effectively setting a flag.

The next time this method is called, the @Bean annotation will check in memory of the Spring container (applicationContext) and see if this given bean has already been created. Since the bean has already been created (previous paragraph) then it will immediately return the instance from memory. It will not execute the code inside of the method. Hence this is a singleton bean.

The code for

 SwimCoach mySwimCoach = new SwimCoach(); 
 return mySwimCoach;
is not executed for subsequent requests to the method public Coach swimCoach() . This code is only executed once during the initial bean creation since it is singleton scope.



That explains how @Bean annotation works for the swimCoach example.

====

Now let's take it one step further.

Here's your other question

>> Please explain in detail whats happening behind the scene for this statement.

return new SwimCoach(sadFortuneService())


The code for this question is slightly different. It is injecting a dependency.

In this example, we are creating a SwimCoach and injecting the sadFortuneService().

         // define bean for our sad fortune service
        @Bean
        public FortuneService sadFortuneService() {
            return new SadFortuneService();
        }
        
        // define bean for our swim coach AND inject dependency
        @Bean
        public Coach swimCoach() {
            SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());
            
            return mySwimCoach;
        }


Using the same information presented earlier

The code

        // define bean for our sad fortune service
        @Bean
        public FortuneService sadFortuneService() {
            return new SadFortuneService();
        }


In the code above, we define a bean for the sad fortune service. Since the bean scope is not specified, it defaults to singleton.

Any calls for sadFortuneService, the @Bean annotation intercepts the call and checks to see if an instance has been created. First time through, no instance is created so the code executes as desired. For subsequent calls, the singleton has been created so @Bean will immediately return with the singleton instance.



Now to the main code based on your question.

return new SwimCoach(sadFortuneService())
This code creates an instance of SwimCoach. Note the call to the method sadFortuneService(). We are calling the annotated method above. The @Bean will intercept and return a singleton instance of sadFortuneService. The sadFortuneService is then injected into the swim coach instance.



This is effectively dependency injection. It is accomplished using all Java configuration (no xml).

---

This concludes the line-by-line discussion of the source code. All of the behind the scenes work.

I hope this clears your doubt. :-)

============================================================
