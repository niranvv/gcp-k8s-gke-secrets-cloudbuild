gcloud components install app-engine-java
Set the Java compiler flags in your project's pom.xml to specify Java 8 bytecode.
	<properties>
	  <maven.compiler.source>1.8</maven.compiler.source>
	  <maven.compiler.target>1.8</maven.compiler.target>
	</properties>
mvn -v
pom.xml >>
	<profiles>
		<profile>
			<id>cloud-gcp</id>
			<dependencies>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-gcp-starter</artifactId>
					<version>1.0.0.RELEASE</version>
				</dependency>
			</dependencies>
			<build>
				<plugins>
					<plugin>
						<groupId>com.google.cloud.tools</groupId>
						<artifactId>appengine-maven-plugin</artifactId>
						<version>1.3.2</version>
					</plugin>
				 </plugins>
			</build>
		</profile>
	</profiles>
src/main/appengine/app.yaml	
	runtime: java
	env: flex
	runtime_config:
	  jdk: openjdk8
	env_variables:
	  SPRING_PROFILES_ACTIVE: "gcp,mysql"
	handlers:
	- url: /.*
	  script: this field is required, but ignored
	manual_scaling: 
	  instances: 1
  
mvn clean package appengine:deploy -P cloud-gcp
http://localhost:8080/