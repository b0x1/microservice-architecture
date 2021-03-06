# Maven Archetype for Microservice Architecture

A Maven archetype to bootstrap a microservice based on [Thorntail](https://thorntail.io/).

## Usage

### 1. Install the archetype into local Maven repository
```bash
# In the archetype folder
mvn clean install
```

### 2. Use archetype to generate a project
```bash
# In the folder where you want to generate your new project
mvn archetype:generate -DarchetypeGroupId=com.gepardec \
                       -DarchetypeArtifactId=microservice-archetype \
                       -DarchetypeVersion=1.0.0-SNAPSHOT \
                       -DgroupId=com.example \
                       -DartifactId=my-example-app \
                       -Dversion=1.0.0-SNAPSHOT \
                       -Dproject-name="An Example Project" \
                       -DinteractiveMode=false
```

### 3. Run the tests
```bash
# The tests run with swarm.project.stage=test
mvn clean test
```

### 4. Run the Thorntail project
```bash
mvn thorntail:run -Dswarm.project.stage=dev -Dmaven.test.skip=true
```

### 5. Run in OpenShift

```bash
mvn fabric8:run -Pfabric8 -Dmaven.test.skip=true
```


