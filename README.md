# lcs-project


## Build

```
./gradlew clean build
```

## Docker images

```
docker build -t lcs-api:0.0.1-local api
```

```
docker build -t lcs-ui:0.0.1-local ui
```

## Run

```
docker stack deploy -c stack.yml lcs
```
