# Test Project on Google Cloud

Hi! I'm **Niran**. This is a test project with Google Cloud

# Adding domain in GAE

 1. Modify "**dispatch.yaml**" in root with the new domain
 2. run `gcloud app deploy dispatch.yaml`

# Deploy Java API in GAE
Created based on [google code labs](https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/index.html#0)
 1. `cd gae-std-java\gae-standard-example`
 2. Add App Engine Plugin in **pom.xml** add the below:
     `<plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>appengine-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
          <version>1</version>
        </configuration>
      </plugin>`
 3. Add App Engine Descriptor at "**src/main/webapp/WEB-INF/appengine-web.xml**"
   `<appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
     <threadsafe>true</threadsafe>
     <runtime>java8</runtime>
    </appengine-web-app>`
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
    Ex: `gcloud beta container --project "test-ci-cd-spin3" clusters create "efx-poc-cluster" --zone "us-central1-a" --username "admin" --cluster-version "1.11.8-gke.6" --machine-type "n1-standard-1" --image-type "COS" --disk-type "pd-standard" --disk-size "20" --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "2" --enable-cloud-logging --enable-cloud-monitoring --no-enable-ip-alias --network "projects/test-ci-cd-spin3/global/networks/default" --subnetwork "projects/test-ci-cd-spin3/regions/us-central1/subnetworks/default" --enable-autoscaling --min-nodes "1" --max-nodes "3" --addons HorizontalPodAutoscaling,HttpLoadBalancing,KubernetesDashboard --enable-autoupgrade --enable-autorepair`
 3. `gcloud container clusters get-credentials test-ci-cd-spin3`
 