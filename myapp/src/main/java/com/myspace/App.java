package com.myspace;

import com.myspace.spreadsheet_decisiontable.ClientObject;
import com.myspace.spreadsheet_decisiontable.Keyword;
import com.myspace.spreadsheet_decisiontable.KeywordReader;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {

    private KieContainer kieContainer;

    public static void main(String[] args) {
        App run = new App();
        run.evaluateRules();
    }

    private  void evaluateRules() {
        // get kie container
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

        // start the stopwatch
        // KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        long start = System.currentTimeMillis();
        KieSession ksession = kieContainer.newKieSession("mykiesession");
        long end = System.currentTimeMillis();
        System.out.format("Initial Kie Session elapsed time: %d \n",  (end - start));

        start = System.currentTimeMillis();
        ClientObject o1 = new ClientObject();
        KeywordReader kr = new KeywordReader("10kTable.xls");
        // KeywordReader kr = new KeywordReader("100kTable.xls");
        // ClientObject o2 = new ClientObject();
        // ClientObject o3 = new ClientObject();
        o1.setDescr("999");
        // o1.setDescr("50000");
        // o2.setDescr("DangerObject99");
        // o3.setDescr("DangerObject999");

        ksession.insert(o1);
        ksession.insert(kr);
        // ksession.insert(o2);
        // ksession.insert(o3);

        int fired = ksession.fireAllRules();
        // stop watch
        end = System.currentTimeMillis();
        // print the result
        System.out.format("fired rules: %d elapsed time: %d \n", fired, (end - start));
        System.out.println("Is Object Pass:" + o1.isPass());

        start = System.currentTimeMillis();
        ClientObject o2 = new ClientObject();

        o2.setDescr("DangerObject9999");
        // o2.setDescr("DangerObject50001");
        ksession.insert(o2);
        // ksession.insert(o2);
        // ksession.insert(o3);
        fired = ksession.fireAllRules();
        // stop watch
        end = System.currentTimeMillis();
        // print the result
        System.out.format("2second round fired rules: %d elapsed time: %d \n", fired, (end - start));
        System.out.println("Is Object Pass:" + o2.isPass());


    }
}
