call mvn clean install dependency:copy-dependencies
java -cp "target/lib/*;target/classes" -javaagent:target/dependency/spring-instrument-3.1.0.RELEASE.jar com.demo.App
