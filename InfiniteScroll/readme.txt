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

$ ng build --prod
$ docker image build -t employee-angular-web:latest .
$ docker image ls
$ docker run -p 3000:80 --rm employee-angular-web:latest

$ docker run -d  -p 8080:8080 employee-angular-web:latest
$ docker ps
$ docker history employee-angular-web:latest
$ docker stop [container_id]

docker tag employee-angular-web:latest gcr.io/learngcpniran/employee-angular-web:latest
docker images
docker push gcr.io/learngcpniran/employee-angular-web:latest
docker stop $(docker ps -q)
docker rm $(docker ps -aq)

#To Test Optional - Remove Child images
docker rmi employee-angular-web:latest gcr.io/learngcpniran/employee-angular-web employee-angular-web:latest
docker rmi node:6
docker rmi $(docker images -aq) # remove remaining images
docker images
#Now we have pseudo env.. Test docker
docker pull gcr.io/learngcpniran/employee-angular-web:latest
docker run -p 3000:80 -d gcr.io/learngcpniran/employee-angular-web:latest
curl http://localhost:8080


#==========Kubernetes==============
gcloud config set compute/zone us-central1-a
gcloud container clusters create [CLUSTER-NAME]
gcloud container clusters get-credentials [CLUSTER-NAME]
kubectl run employee-angular-web-server --image=gcr.io/learngcpniran/employee-angular-web:latest --port 80
kubectl expose deployment employee-angular-web-server --type="LoadBalancer"
kubectl get service employee-angular-web-server
curl http://[EXTERNAL-IP]:80
