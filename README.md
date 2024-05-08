# Spring Autowired instance-rebuilder using Spring AOP proxies
An example project to show how to build AOP proxies for managed beans in Spring Java projects


## Introduction

In the context of a Java Spring application, once the managed bean singletons are created (typically using `@Autowired`), the instances of those singletons do not change in all time of  the execution of the application.

This is how Java Spring applications are supposed to be and how they should work in all occasions.

But sometimes the developer has to face scenarios that require tricks like changing in *execution time* the actual instance injected by Spring.

For example

```
    @Autowired
    Connection connection;
```

where Connection class allows to communicate with an external component.

Let's assume that this Connection instance goes to an inconsistent state, and from that point it does no longer work and there is no way to reset it.

That is a problem so,  how can the application rebuild a new Connection from the scratch in a transparent way?

One convenient way to do this is using proxies for `@Autowired` instances. The idea is that the proxy actually points to a real instance of Connection, if for any reason this instance fails, then it can be replaced for a new one in a transparent way.

That is precisely the idea behind the utility class **InstanceRebuilder** that I've built. Basically this clas allows to create proxies as explained here in an easy way, using AOP Spring proxies.

Please follow the example in this project as it is easy to follow and use it.
