ng build --prod
docker image build -t employee-angular-web:0.1 .
docker tag employee-angular-web:0.1 gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1
docker images
docker push gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1
docker stop $(docker ps -q)
docker rm $(docker ps -aq)
kubectl run employee-angular-web-server --image=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1 --port 80
kubectl expose deployment employee-angular-web-server --type="LoadBalancer"
#kubectl set image deployment employee-angular-web-server employee-angular-web-server=gcr.io/$GOOGLE_CLOUD_PROJECT/employee-angular-web:0.1
#kubectl rollout status deployment employee-angular-web-server
#kubectl get deployments