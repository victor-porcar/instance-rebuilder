# instance-rebuilder
An example project to show how to build AOP proxies for managed beans


InstanceRebuilder

In the context of a Java Spring application, once the managed bean singletons are created (typically using @Autowired), the instances of those singletons do not change in all time of  the execution of the application.

This is how Java Spring applications are designed and how they should work in all occasions

But sometimes the developer has to face scenarios that require tricks like changing in execution time the actual instance injected by Spring.

For example

    @Autowired
    Connection connection;


Connection is some component that allows to communicate with an external component.

Let's assume that this Connection instance may go to an inconsistent state, and from that point it does no longer work and there is no way to reset it.

That is a problem,so, how can the application rebuild a new Connection from the scratch in a transparent way?

One convenient way to do this is using proxies for @Autowired instances. The idea is that the proxy points to a real instance of Connection, if for any reason this instance fails, then the real instance pointed by the proxy may be created


Spring offers AOP proxies that makes this easy.

InstanceRebuilder class is a utility class that allows to create proxies as explained here in an easy way. Please follow the example package as it is straightforward example of how to use it.





This can be achieved using Proxies

for example

Let's suppose a Connection object that allows to communicate with an external system

    @Autowired
    Connection connection;

let's assume that Connection cls 


In order to make this very easy to use I've created
class InstanceRebuilder, which can be copy and used directly in any Spring boot project

This demo projects shows how to do it





