docker build . -t mylibrary
docker stop mylibrary
docker rm mylibrary
docker run -d -p 8080:8080 --name mylibrary mylibrary