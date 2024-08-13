# Springdoc: Unable to run a war-packaged Spring app

# Outline
We recently migrated from Swagger to Springdoc/OpenAPI, but we are having trouble getting our application to start with a working Springdoc configuration.

We build and run our application on Tomcat 10 from the IDE (IntelliJ), however the war can be built using `mvn package`.

When we start the application as is configured in this project, we receive the following error:

```
14:34:47.570 [RMI TCP Connection(2)-127.0.0.1] WARN  o.s.w.c.s.XmlWebApplicationContext - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'openApiWebMvcResource' defined in URL [jar:file:.../springdoc-webmvc-app/target/springdoc-webmvc-war-0.0/WEB-INF/lib/springdoc-openapi-starter-webmvc-api-2.2.0.jar!/org/springdoc/webmvc/api/OpenApiWebMvcResource.class]: Unsatisfied dependency expressed through constructor parameter 5: Error creating bean with name 'springDocProviders' defined in class path resource [org/springdoc/core/configuration/SpringDocConfiguration.class]: Unsatisfied dependency expressed through method 'springDocProviders' parameter 3: Error creating bean with name 'springRepositoryRestResourceProvider' defined in class path resource [org/springdoc/core/configuration/SpringDocDataRestConfiguration$SpringRepositoryRestResourceProviderConfiguration.class]: Unsatisfied dependency expressed through method 'springRepositoryRestResourceProvider' parameter 0: No qualifying bean of type 'org.springframework.data.rest.core.mapping.ResourceMappings' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
14:34:47.574 [RMI TCP Connection(2)-127.0.0.1] ERROR o.s.web.context.ContextLoader - Context initialization failed
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'openApiWebMvcResource' defined in URL [jar:file:.../springdoc-webmvc-app/target/springdoc-webmvc-war-0.0/WEB-INF/lib/springdoc-openapi-starter-webmvc-api-2.2.0.jar!/org/springdoc/webmvc/api/OpenApiWebMvcResource.class]: Unsatisfied dependency expressed through constructor parameter 5: Error creating bean with name 'springDocProviders' defined in class path resource [org/springdoc/core/configuration/SpringDocConfiguration.class]: Unsatisfied dependency expressed through method 'springDocProviders' parameter 3: Error creating bean with name 'springRepositoryRestResourceProvider' defined in class path resource [org/springdoc/core/configuration/SpringDocDataRestConfiguration$SpringRepositoryRestResourceProviderConfiguration.class]: Unsatisfied dependency expressed through method 'springRepositoryRestResourceProvider' parameter 0: No qualifying bean of type 'org.springframework.data.rest.core.mapping.ResourceMappings' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:800)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:245)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1352)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1189)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
	...
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'springDocProviders' defined in class path resource [org/springdoc/core/configuration/SpringDocConfiguration.class]: Unsatisfied dependency expressed through method 'springDocProviders' parameter 3: Error creating bean with name 'springRepositoryRestResourceProvider' defined in class path resource [org/springdoc/core/configuration/SpringDocDataRestConfiguration$SpringRepositoryRestResourceProviderConfiguration.class]: Unsatisfied dependency expressed through method 'springRepositoryRestResourceProvider' parameter 0: No qualifying bean of type 'org.springframework.data.rest.core.mapping.ResourceMappings' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:800)
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:550)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1332)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1162)
	...
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'springRepositoryRestResourceProvider' defined in class path resource [org/springdoc/core/configuration/SpringDocDataRestConfiguration$SpringRepositoryRestResourceProviderConfiguration.class]: Unsatisfied dependency expressed through method 'springRepositoryRestResourceProvider' parameter 0: No qualifying bean of type 'org.springframework.data.rest.core.mapping.ResourceMappings' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:800)
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:550)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1332)
	...
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.data.rest.core.mapping.ResourceMappings' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1824)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1383)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1337)
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:888)
```

## History
I have logged this in two places already:
- https://github.com/springdoc/springdoc-openapi/issues/2343 - this issue was marked as completed as it was missing a sample application.
- https://stackoverflow.com/questions/76861574/using-springdoc-openapi-in-spring-mvc-application-not-spring-boot - in case anyone on SO had seen this issue (so far no answers)

