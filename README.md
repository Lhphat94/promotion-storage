**Step 1: Build Project**

`./mvnw install -Dmaven.test.skip=true -Djacoco.skip=true`

**Step 2: Build Docker image**

`docker build -t promotion-storage .`

**Step 3: Create new container**

`docker network create promotion-net`

`docker container run --name promotion-storage --network=promotion-net -p 8081:8080 promotion-storage`

**Step 4: Access URL**

`http://127.0.0.1:8081/promotion-storage/promotions`