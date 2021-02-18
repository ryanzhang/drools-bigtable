# How to run


```bash
cd rules 
mvn clean install
cd ../myapp
mvn clean compile exec:java
#Output
Initial Kie Session elapsed time: 12469 
false
fired rules: 1 elapsed time: 524 
Is Object Pass:false

#As you can see, the performance is not very promising.
#Let's switch to precompile-rule-solution branch
```
