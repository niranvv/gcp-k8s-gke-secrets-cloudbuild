*************JAVA**********************
+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation
create cluster
**Build and run the app using maven**
```bash
mvn package
java -jar target/employees-management-1.0.0.jar
```
Alternatively, you can run the app without packaging it using -
```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>.
cd docker/
docker image build -t employee-java-api:0.1 .
docker tag employee-java-api:0.1 gcr.io/efx-pocs-niran/employee-java-api:0.1
docker images
docker push gcr.io/efx-pocs-niran/employee-java-api:0.1
docker stop $(docker ps -q)
docker rm $(docker ps -aq)
gcloud config set compute/zone us-central1-a
gcloud container clusters get-credentials efx-poc-cluster
kubectl run employee-java-api-server --image=gcr.io/efx-pocs-niran/employee-java-api:0.1 --port 8080
kubectl expose deployment employee-java-api-server --type="LoadBalancer"

#To Test Optional - Remove Child images
docker rmi employee-java-api:0.1 gcr.io/efx-pocs-niran/employee-java-api employee-java-api:0.1
docker rmi $(docker images -aq) # remove remaining images
docker images
#Now we have pseudo env.. Test docker
docker pull gcr.io/efx-pocs-niran/employee-java-api:0.1
docker run -p 3000:8080 -d gcr.io/efx-pocs-niran/employee-java-api:0.1
curl http://localhost:8080

#Subsequent Runs
kubectl set image deployment employee-java-api-server employee-java-api-server=gcr.io/efx-pocs-niran/employee-java-api:0.1
kubectl rollout status deployment employee-java-api-server
kubectl get deployments

