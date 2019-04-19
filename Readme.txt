#Setup APplication and Build
$ npm install -g npm
$ npm install -g @angular/cli@latest
$ npm install -g http-server
$ cd AngularJavaAPICloudSQL/InfiniteScroll/
In angular.json make sure options should be
	"outputPath": "dist",
app.yaml:
	runtime: nodejs8
	env: standard
	service: angpoc
	handlers:
	- url: /
	  static_files: dist/index.html
	  upload: dist/index.html
	- url: /
	  static_dir: dist
$ ng build --prod

#To Test Prod Build
$ http-server ./dist

$ gcloud app deploy

#Build & Deploy the Docker file
    FROM nginx:alpine
    COPY nginx.conf /etc/nginx/nginx.conf
    WORKDIR /usr/share/nginx/html
    COPY dist/ .
nginx.conf
    worker_processes  1;
    events {
        worker_connections  1024;
    }
    http {
        server {
            listen 80;
            server_name  localhost;

            root   /usr/share/nginx/html;
            index  index.html index.htm;
            include /etc/nginx/mime.types;

            gzip on;
            gzip_min_length 1000;
            gzip_proxied expired no-cache no-store private auth;
            gzip_types text/plain text/css application/json application/javascript application/x-javascript text/xml application/xml application/xml+rss text/javascript;

            location / {
                try_files $uri $uri/ /index.html;
            }
        }
    }

ng build --prod
ng build
docker image build -t employee-angular-web:0.1 .
# docker image ls
# docker run -p 3000:80 --rm employee-angular-web:0.1

$ docker run -d  -p 8080:8080 employee-angular-web:0.1
$ docker ps
$ docker history employee-angular-web:0.1
$ docker stop [container_id]

docker tag employee-angular-web:0.1 gcr.io/efx-pocs-niran/employee-angular-web:0.1
docker images
docker push gcr.io/efx-pocs-niran/employee-angular-web:0.1
docker stop $(docker ps -q)
docker rm $(docker ps -aq)

#To Test Optional - Remove Child images
docker rmi employee-angular-web:0.1 gcr.io/efx-pocs-niran/employee-angular-web employee-angular-web:0.1
docker rmi node:6
docker rmi $(docker images -aq) # remove remaining images
docker images
#Now we have pseudo env.. Test docker
docker pull gcr.io/efx-pocs-niran/employee-angular-web:0.1
docker run -p 3000:80 -d gcr.io/efx-pocs-niran/employee-angular-web:0.1
curl http://localhost:8080


#==========Kubernetes==============
gcloud config set compute/zone us-central1-a
gcloud container clusters create [CLUSTER-NAME]
gcloud container clusters get-credentials [CLUSTER-NAME]
kubectl run employee-angular-web-server --image=gcr.io/efx-pocs-niran/employee-angular-web:0.1 --port 80
kubectl expose deployment employee-angular-web-server --type="LoadBalancer"
kubectl get service employee-angular-web-server
curl http://[EXTERNAL-IP]:80

kubectl set image deployment employee-angular-web-server employee-angular-web-server=gcr.io/efx-pocs-niran/employee-angular-web:0.1
kubectl rollout status deployment employee-angular-web-server
kubectl get deployments

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

#Subsequent Runs
kubectl set image deployment employee-java-api-server employee-java-api-server=gcr.io/efx-pocs-niran/employee-java-api:0.1
kubectl rollout status deployment employee-java-api-server
kubectl get deployments

