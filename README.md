# Installing

## SDK 3.0

* Create a file `${user.name}.pom.properties` from [jenkins.pom.properties](jenkins.pom.properties) example in the project root folder.  Where `${user.name}` is your PC user name. Edit IP address and check the path to `ImageMagick`. [Installing ImageMagick Manual](http://docs.alfresco.com/5.2/tasks/imagemagick-config.html) 

* Run for SDK3.0 alfresco startup

```bash
mvn clean install alfresco:run
```

## AMPs modules

* for building AMPs modules: 

```bash
mvn clean package
```
You can find AMPs here `.CustomizeLoginBackground-platform/target/CustomizeLoginBackground-platform.amp` and `.CustomizeLoginBackground-share/target/CustomizeLoginBackground-share.amp`
