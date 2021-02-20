# Use rule template + Xls to create drools rules
By default we would test 10k row Table for performance check
### 10k row table scenario
```bash
cd rules
mvn clean package
# rule package is spreadsheet-decisiontable-1.1-SNAPSHOT.jar
cd ../myapp
mvn clean compile exec:java
Initial Kie Session elapsed time: 8755 
false
fired rules: 1 elapsed time: 387 
Is Object Pass:false
```
### 100k rows table scenario
### 1. Disable 10kTable.xls and enable 100kTable.xls
Rename rules/src/main/resources/com/myspace/spreadsheet_decisiontable/10kTable.xls -> 10kTable.xls.deactive

Rename rules/src/main/resources/com/myspace/spreadsheet_decisiontable/100kTable.xls.deactive -> 100kTable.xls

### 2. Modify kmodule.xml
```
Change
dtable="com/myspace/spreadsheet_decisiontable/10kTable.xls"
to
dtable="com/myspace/spreadsheet_decisiontable/100kTable.xls"
```
### 3.Redo the above step, package the rule kjar and run myapp application
```bash
cd rules
# It would take 1.5 mins to package the rule jar
mvn clean package

# rule package is spreadsheet-decisiontable-1.1-SNAPSHOT.jar
cd ../myapp
mvn clean compile exec:java
Initial Kie Session elapsed time: 99290 
false
fired rules: 1 elapsed time: 9206 
Is Object Pass:false
```

# Summary

For 10K rules, fire one rule cost 300~400 millseconds time;
For 100k rules, fire one rule costs about 9000+ millseconds time;

The compilation takes some time due to large amounts of rows data to check before package the kjar
for 10k rules, package the kjar takes about 15 s
For 100k rules, it takes about 1.5 minutes;

