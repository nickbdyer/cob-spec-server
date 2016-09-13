#HTTP Server in Java

[![Coverage Status](https://coveralls.io/repos/github/nickbdyer/cob-spec-server/badge.svg?branch=master)](https://coveralls.io/github/nickbdyer/cob-spec-server?branch=master) [![Build Status](https://travis-ci.org/nickbdyer/cob-spec-server.svg?branch=master)](https://travis-ci.org/nickbdyer/cob-spec-server)

###Clone

```shell
$ cd <folder where you want to store the project>

$ git clone https://github.com/nickbdyer/cob-spec-server.git

$ cd cob-spec-server/
```

This project has a Gradle Wrapper embedded, so you can run the project and tests without having Gradle on your path.

###Compile
```shell
$ ./gradlew build
```

###Run Program
```shell
$ java -jar build/libs/cob-spec-server.jar
```
Or
```shell
$ ./gradlew --console plain run
```

###Test Program
To see the results in the command line:
```shell
$ ./gradlew cleanTest test
```
Or in your browser:
```shell
$ open reports/tests/index.html
```



