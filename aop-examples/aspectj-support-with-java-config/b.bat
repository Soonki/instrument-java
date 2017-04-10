call mvn -q clean test
call mvn -q clean compile exec:java -Dexec.mainClass=com.demo.App -Dexec.args=""