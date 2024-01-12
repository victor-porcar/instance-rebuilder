package com.github.victormpcmun.ir.example;

import com.github.victormpcmun.ir.instancerebuilder.InstanceRebuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleRunner implements CommandLineRunner {
    @Autowired
    Connection connection;  // this "connection" is actually a Proxy, but this is transparent

    @Autowired
    InstanceRebuilder<Connection> instanceRebuilderConnection; // this is the instance rebuilder of connection


    @Override
    public void run(String...args)  {
        runLogic();
    }


    public void runLogic() {
        for (int i=0; i<10000; i++) {

            if (i % 4 ==0) {

                System.out.println("Launching rebuildInstance()");

                // in this example every 4 iterations a new real instance of Connection is created and used subsequently by the proxy
                instanceRebuilderConnection.rebuildInstance();
            }

            connection.communicate(); // this connection is the proxy but as pointed before, it is fully transparent for the framework

        }
    }
}