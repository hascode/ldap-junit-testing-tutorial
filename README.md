[![build-status](https://pipelines-badges-service.useast.staging.atlassian.io/badge/hascode/ldap-junit-testing-tutorial.svg)](https://bitbucket.org/hascode/ldap-junit-testing-tutorial/addon/pipelines/home)

# LDAP JUnit Testing Tutorial

A demonstration how to initialize an embedded LDAP server, import test data from a given LDIF file and run tests using either the [ApacheDS] Testing Library or the [embedded-ldap-junit] library with [JUnit].

Please feel free to have a look at my blog at [www.hascode.com] for the full tutorial.

## Running the Tests

Running the tests in the command-line is easy using [Maven]

```
mvn test
```

---------

**2016 Micha Kops / hasCode.com**

   [www.hascode.com]:http://www.hascode.com/
   [ApacheDS]:https://github.com/andsel/moquette
   [embedded-ldap-junit]:https://eclipse.org/paho/clients/java/
   [JUnit]:http://junit.org/
   [Maven]:http://maven.apache.org/
   
