# How to run 
```bash
cd compile-rules
mvn clean install -DskipTests
[INFO] Generating /wdc/github/ryanzhang/drools-bigtable/compile-rules/target/generated-sources/drools-model-compiler/main/java/./com/myspace/spreadsheet_decisiontable/PA5/LambdaPredicateA5C5AE29824CD9B328E26A8BE8BCC3CB.java
...
[INFO] DSL successfully generated
[INFO] 
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile-1) @ spreadsheet-decisiontable ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 10207 source files to /wdc/github/ryanzhang/drools-bigtable/compile-rules/target/classes
[INFO] 
[INFO] --- kie-maven-plugin:7.39.0.Final:build (default-build) @ spreadsheet-decisiontable ---
[INFO] Artifact not fetched from maven: org.drools:drools-model-compiler:7.39.0.Final. To enable the KieScanner you need kie-ci on the classpath
[INFO] kieMap not present
[INFO] KieModule successfully built!
[INFO] 
[INFO] --- kie-maven-plugin:7.39.0.Final:injectreactive (default-injectreactive) @ spreadsheet-decisiontable ---
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ spreadsheet-decisiontable ---
[INFO] Building jar: /wdc/github/ryanzhang/drools-bigtable/compile-rules/target/spreadsheet-decisiontable-1.0-SNAPSHOT.jar
[INFO] 
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:44 min
[INFO] Finished at: 2021-02-17T22:55:26+08:00
[INFO] -----------------------------------------------------------------------

#Rule Compile took *1.7 mins*

# Rule Running is faster
cd ../myapp
mvn clean compile exec:java
Initialize Kie Session elapsed time: 2429 
fired rules: 1 elapsed time: 196 
Is Object Pass:false
```
