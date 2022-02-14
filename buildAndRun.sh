DOCKER_REPO_AUTHOR="kirieshki"
EXTERNAL_SERVICE_STORAGE_PORT=8080
mvn clean package
docker build -t $DOCKER_REPO_AUTHOR/service-storage .
docker run -d -p 8080:$EXTERNAL_SERVICE_STORAGE_PORT $DOCKER_REPO_AUTHOR/service-storage