ng build --prod
docker image build -t employee-angular-web:0.3 .
docker tag employee-angular-web:0.3 gcr.io/efx-pocs-niran/employee-angular-web:0.3
docker images
docker push gcr.io/efx-pocs-niran/employee-angular-web:0.3
docker stop $(docker ps -q)
docker rm $(docker ps -aq)
kubectl set image deployment employee-angular-web-server employee-angular-web-server=gcr.io/efx-pocs-niran/employee-angular-web:0.3
kubectl rollout status deployment employee-angular-web-server
kubectl get deployments