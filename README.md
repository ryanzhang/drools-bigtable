# Convert row data to fact instead of rules
In this solution, we would convert the decision table rows into drools fact instead rules. 

```java
package com.myspace.spreadsheet_decisiontable
import java.util.*
import com.myspace.spreadsheet_decisiontable.KeywordReader

rule "Load keyword"
    when
        $kr:KeywordReader(excelFile matches ".*.xls")
    then
        System.out.println("Load keyword rule fired");
        List<Map<String, String>> list = $kr.getKwList();
        for ( Map<String, String> m : list){
            Keyword k = new Keyword(m.get("1"), Boolean.valueOf(m.get("2")));
            insert(k);
        }
end
```
KeywrodReader is an utilities to read xls file and transfer data into a HashMap List.

So in my drl, the HashMap was converted into Keyword Fact and insert into kiesession.

This is my second rule, match the description from Fact which is very efficient;
```java
rule "Check Client Object"
    when
        $k:Keyword($v : value)
        $c:ClientObject( descr matches $v )
    then
        $c.setPass($k.isResult());
        System.out.println("Check Client Object fired");
end
```

## How to Run 
```
cd rules
mvn clean install 
# rule kjar is spreadsheet-decisiontable-1.3-SNAPSHOT.jar
cd ../myapp
mvn clean compile exec:java 
```
## Try 100k Decision Table
Edit App.java, uncomment the following line
```
        //KeywordReader kr = new KeywordReader("10kTable.xls");
         KeywordReader kr = new KeywordReader("100kTable.xls");
        
        //o1.setDescr("999");
         o1.setDescr("50000");
        
        //o2.setDescr("DangerObject9999");
         o2.setDescr("DangerObject50001");
```
```java
mvn clean compile exec:java 
Initial Kie Session elapsed time: 4943 
Load keyword rule fired
fired rules: 1 elapsed time: 2761 
Is Object Pass:true
Check Client Object fired
2second round fired rules: 1 elapsed time: 70 
```

The first time Load keyword file would take extra time to load xls file in kiesession;

Afterwards, each rule execution is very fast;

# Summary
By convert large row data to fact instead of handling as rules as usual, would dramatic increase performance;


