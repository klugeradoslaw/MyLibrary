docker build . -t mylibrary
docker stop mylibrary || true
docker rm mylibrary || true
docker run -d -p 8080:8080 --name=mylibrary -e SPRING_PROFILES_ACTIVE=prod --network mylibrary-network --restart unless-stopped mylibrary