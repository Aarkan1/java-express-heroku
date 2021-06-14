# java-express-heroku
Deploy Java Express to Heroku

## Steps
With heroku cli:
1. Create a file at root called _Procfile_ containing: _web: java $JAVA_OPTS -jar <your-application>.jar --port $PORT_
2. Create a file at root called _system.properties_ containing: _java.runtime.version=11_ (set to same jdk version as your application)
3. Create new app at [Heroku](https://dashboard.heroku.com/apps)
4. in the terminal type:
  1. npm install -g heroku (if heroku cli isn't installed)
  2. heroku login
  3. git init (if not already connected with git)
  4. git add . && git commit -m "deploy to heroku"
  5. heroku buildpacks:set heroku/jvm
  6. git remote add heroku https://git.heroku.com/<heroku_app_name>.git
  7. git push heroku main

### Extract the port from arguments
```java
int port = 4000; // default port

// loop and set the port value from args
for(int i = 0; i < args.length; i++) {
  if(args[i].equals("--port")) {
    port = Integer.parseInt(args[i + 1]);
    break;
  }
}

app.listen(port);
```

### Set build options in pom.xml to include dependencies
```xml
<!-- run this command to build jar -->
<!-- mvn clean compile assembly:single -->
<build>
  <plugins>
    <plugin>
      <artifactId>maven-assembly-plugin</artifactId>
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <!-- change to your class containing the main()-method -->
            <mainClass>com.example.Main</mainClass>
          </manifest>
        </archive>
        <descriptorRefs>
          <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
      </configuration>
      <executions>
        <execution>
          <goals>
            <goal>single</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M5</version>
      <configuration>
        <skipTests>true</skipTests>
      </configuration>
    </plugin>
  </plugins>
</build>
```