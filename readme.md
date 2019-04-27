# Test Project on Google Cloud

Hi! I'm **Niran**. This is a test project with Google Cloud

# Adding domain in GAE

 1. Modify "**dispatch.yaml**" in root with the new domain
 2. run `gcloud app deploy dispatch.yaml`

# Deploy Java API in GAE
Created based on [google code labs](https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/index.html#0)
 1. `cd gae-std-java\gae-standard-example`
 2. Add App Engine Plugin in **pom.xml** add the below:
     ```
     <plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>appengine-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
          <version>1</version>
        </configuration>
      </plugin>
      ```
 3. Add App Engine Descriptor at "**src/main/webapp/WEB-INF/appengine-web.xml**"
    ```
     <appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
      <threadsafe>true</threadsafe>
      <runtime>java8</runtime>
     </appengine-web-app>
    ```
 4. Run locally: `./mvnw -DskipTests spring-boot:run`
 5. `gcloud app create --region us-central`
 6. `./mvnw -DskipTests appengine:deploy`

 # Deploy Angular in GAE

 1. `npm install -g @angular/cli@latest`
 2. `npm install -g http-server`
 3. `cd InfiniteScroll`
 4. `ng build --prod`
 5. Test Prod Build `http-server ./dist`
 6. `gcloud app deploy`

 # Deploy to Kubernetes

 ## Create Kubernetes cluster
 1. `gcloud config set compute/zone us-central1-a`
 2. `gcloud container clusters create [CLUSTER-NAME]` 
  Ex: 
  ```bash
  gcloud beta container --project "$GOOGLE_CLOUD_PROJECT" clusters create "efx-poc-cluster" --zone "us-central1-a" --username "admin" --cluster-version "1.11.8-gke.6" --machine-type "n1-standard-1" --image-type "COS" --disk-type "pd-standard" --disk-size "20" --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "2" --enable-cloud-logging --enable-cloud-monitoring --no-enable-ip-alias --network "projects/$GOOGLE_CLOUD_PROJECT/global/networks/default" --subnetwork "projects/$GOOGLE_CLOUD_PROJECT/regions/us-central1/subnetworks/default" --enable-autoscaling --min-nodes "1" --max-nodes "3" --addons HorizontalPodAutoscaling,HttpLoadBalancing,KubernetesDashboard --enable-autoupgrade --enable-autorepair
  ```
 3. `gcloud container clusters get-credentials efx-poc-cluster`

 ## Deploy Java Application on the Container:
 1. `./mvnw -DskipTests spring-boot:run`
 ### Package the Java application as a Docker container [Option 1 - using Jib]
 2. Package the app: `./mvnw -DskipTests package`
 3. Use **Jib** to create the container image and push it to the Container Registry: 
    `./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build -Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:v1`
 4. Test the image locally with the following command which will run a Docker container 
    as a daemon on port 8080 from your newly-created container image 
    `docker run -ti --rm -p 8080:8080 gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:v1`
 5. Deploy your application to Kubernetes `kubectl run employee-java-api-server --image=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:v1 --port=8080`
 6. `kubectl get deployments`
 7. `kubectl get pods`
 8. `kubectl expose deployment employee-java-api-server --type=LoadBalancer`
### Package the Java application as a Docker container [Option 2]
 1. Package: `mvn package` & Run `java -jar target/employee-0.0.1-SNAPSHOT.war`
    Alternatively, you can run the app without packaging it using: `mvn spring-boot:run`
 2. `docker image build -t employee-java-api:0.1.0 .`
 3. `docker tag employee-java-api:0.1.0 gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0`
 4. `docker images`
 5. `docker push gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0`
 6. `docker stop $(docker ps -q)`
 7. `docker rm $(docker ps -aq)`
 8. `gcloud config set compute/zone us-central1-a`
 9. `gcloud container clusters get-credentials efx-poc-cluster`
 10. `kubectl run employee-java-api-server --image=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0 --port 8080`
 11. `kubectl expose deployment employee-java-api-server --type="LoadBalancer"`
 #### Update the image [Option 2]
 1. `kubectl set image deployment employee-java-api-server employee-java-api-server=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0`
 2. `kubectl rollout status deployment employee-java-api-server`
 3. `kubectl get deployments`
 #### [Optional] To Test  after removing local Child images
 1. `docker stop $(docker ps -q)`
 2. `docker rm $(docker ps -aq)`
 3. `docker rmi employee-java-api:0.1.0 gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api employee-java-api:0.1.0`
 4. `docker rmi openjdk:8-jre-alpine`
 5. `docker rmi $(docker images -aq) --force # remove remaining images`
 6. `docker images`
##### [Optional] Now we have pseudo env.. Test docker
 7. `docker pull gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0`
 8. `docker run -p 8080:8080 -d gcr.io/$GOOGLE_CLOUD_PROJECT/employee-java-api:0.1.0`
 9. `curl http://localhost:8080`


 ## Deploy Angular Application on the Container:
 1. `npm install -g @angular/cli@latest`
 2. `npm install -g http-server`
 3. `cd InfiniteScroll/`
 4. `ng build --prod`
 5. To Test Prod Build `http-server ./dist`
 6. `docker image build -t employee-angular-web:0.1 .`
 7. `docker image ls`
 8. `docker run -p 3000:80 --rm employee-angular-web:0.1` OR `docker run -d  -p 8080:8080 employee-angular-web:0.1`
 9. `docker ps`
 10. `docker history employee-angular-web:0.1`
 11. `docker stop [container_id]`
 12. `docker tag employee-angular-web:0.1 gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1`
 13. `docker images`
 14. `docker push gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1`
 15. `docker stop $(docker ps -q)`
 16. `docker rm $(docker ps -aq)`
 17. `kubectl run employee-angular-web-server --image=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1 --port 80`
 18. `kubectl expose deployment employee-angular-web-server --type="LoadBalancer"`
 ### Update the image
 19. `kubectl set image deployment employee-angular-web-server employee-angular-web-server=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1`
 20. `kubectl rollout status deployment employee-angular-web-server`
 21. `kubectl get deployments`

## Spinnacker for Kubernetes [Option 1]
 1. Enable Required APIs
      * [Pub/Sub API](https://console.cloud.google.com/apis/api/pubsub.googleapis.com)
      * [Cloud build API](https://console.cloud.google.com/apis/api/cloudbuild.googleapis.com)
      * [Kubernetes API](https://console.cloud.google.com/apis/api/container.googleapis.com)
 2. Deploy Spinnaker 
```bash
gsutil cp gs://gke-spinnaker-codelab/install.tgz . 
tar -xvzf install.tgz
 ```
 3. `./setup.sh`
 ## Spinnacker for Kubernetes [Option 2]
 Ref https://www.qwiklabs.com/focuses/552?parent=catalog
  1. `gcloud config set compute/zone us-central1-f`
  2. `gcloud container clusters create spinnaker-tutorial --machine-type=n1-standard-2 --enable-legacy-authorization`
 ##### Configure identity and access management
  3. `gcloud iam service-accounts create  spinnaker-storage-account --display-name spinnaker-storage-account`
  4. `export SA_EMAIL=$(gcloud iam service-accounts list     --filter="displayName:spinnaker-storage-account"     --format='value(email)')`
  5. `export PROJECT=$(gcloud info --format='value(config.project)')`
  6. Bind the storage.admin role to your service account: `gcloud projects add-iam-policy-binding $PROJECT --role roles/storage.admin --member serviceAccount:$SA_EMAIL`
  7. Download the service account key. In a later step, you will install Spinnaker and upload this key to Kubernetes Engine: `gcloud iam service-accounts keys create spinnaker-sa.json --iam-account $SA_EMAIL`
 ### Deploying Spinnaker using Helm
  8. `wget https://storage.googleapis.com/kubernetes-helm/helm-v2.5.0-linux-amd64.tar.gz`
  9. `tar zxfv helm-v2.5.0-linux-amd64.tar.gz`
  10. `cp linux-amd64/helm .`
  11. Initialize Helm to install Tiller, the server side of Helm, in your cluster: `./helm init`
  12. `./helm repo update`
  13. Ensure that Helm is properly installed by running the following command. If Helm is correctly installed, v2.5.0 appears for both client and server. `./helm version`
 ### Configure Spinnaker
  14. In Cloud Shell, create a bucket for Spinnaker to store its pipeline configuration:
  ```bash
  export PROJECT=$(gcloud info \
    --format='value(config.project)')
  export BUCKET=$PROJECT-spinnaker-config
  gsutil mb -c regional -l us-central1 gs://$BUCKET
  ```
  15. Create your configuration file by executing the following:
  ```
  export SA_JSON=$(cat spinnaker-sa.json)
  cat > spinnaker-config.yaml <<EOF
  storageBucket: $BUCKET
  gcs:
    enabled: true
    project: $PROJECT
    jsonKey: '$SA_JSON'
  
  # Disable minio the default
  minio:
    enabled: false

  # Configure your Docker registries here
  accounts:
  - name: gcr
    address: https://gcr.io
    username: _json_key
    password: '$SA_JSON'
    email: 1234@5678.com
  EOF
  ```
### Deploy the Spinnaker chart
  16. `./helm install -n cd stable/spinnaker -f spinnaker-config.yaml --timeout 600 --version 0.3.1 `
      > Note: You will see a warning here about a listener not getting created - you can ignore it. The installation will proceed after a few minutes.
  17. After the command completes, run the following command to set up port forwarding to Spinnaker from Cloud Shell:
  ```bash
  export DECK_POD=$(kubectl get pods --namespace default -l "component=deck" \
    -o jsonpath="{.items[0].metadata.name}")
  kubectl port-forward --namespace default $DECK_POD 8080:9000 >> /dev/null &
  ```
  19. To open the Spinnaker user interface, click the Web Preview icon at the top of the Cloud Shell window and select Preview on port 8080
### Configure your build triggers
  20. In the Cloud Platform Console, click Navigation menu > Cloud Build > Triggers. Click Create trigger.


 #### Troubleshooting [Common]
 ##### For permission issues
 * `chmod +x mvnw`

 