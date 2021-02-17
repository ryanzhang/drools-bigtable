package com.myspace;

import com.myspace.spreadsheet_decisiontable.ClientObject;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {

    private KieContainer kieContainer; 

    public static void main(String[] args) throws InterruptedException {
        App run = new App();
        run.evaluateRules();
    }

    

    private void evaluateRules() throws InterruptedException {
        // get kie container
        kieContainer = getContainer();

        // start the stopwatch
        long start = System.currentTimeMillis();
        
        KieSession ksession = kieContainer.newKieSession("mykiesession");
        
        long end = System.currentTimeMillis();
        
        System.out.format("Initialize Kie Session elapsed time: %d \n", (end - start));
        
        start = System.currentTimeMillis();
        ClientObject o1 = new ClientObject();
        // ClientObject o2 = new ClientObject();
        // ClientObject o3 = new ClientObject();
        o1.setDescr("DangerObject9999");
        // o2.setDescr("DangerObject99");
        // o3.setDescr("DangerObject999");

        ksession.insert(o1);
        // ksession.insert(o2);
        // ksession.insert(o3);
        
        int fired = ksession.fireAllRules();
        
        // stop watch
        end = System.currentTimeMillis();

        // print the result
        System.out.format("fired rules: %d elapsed time: %d \n", fired, (end - start));
        System.out.println("Is Object Pass:" + o1.isPass());


        ksession.halt();
        ksession.dispose();
        
    }

    private static KieContainer getContainer() {
        KieServices kieServices = KieServices.Factory.get();

        // Retrieve the decision project (kjar) from classpath
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        // Dynamically pull the decision project from Maven repo
        // ReleaseId releaseId = kieServices.newReleaseId("com.myspace","spreadsheet-decisiontable", "1.0.0-SNAPSHOT");
        // KieContainer kieContainer = kieServices.newKieContainer(releaseId);
        return kieContainer;
    }
}
