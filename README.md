# News Whip URL Manager
This is a shell application helps to manage URLS and can run analytics on the available URLs. This application offers 
the below functionalities:
* Add URL and its score
* List all URLs and their scores.
* Delete a URL
* Export URL and generate report in csv

## Architecture
This application is a shell application which uses Spring shell framework for shell functionalities and uses Spring JPA 
data package to connect to H2 database and store the URLs. This uses an embedded H2 database to store the URL data.

## How to use
### Start the services
The solution is available as docker. Start the docker containers using the below commands:

Note: This requires docker installed on the host.
i) Refer to https://docs.docker.com/get-docker/ to install docker

1. Clone the repo:  https://github.com/shivasr/zinkworks-atm-machine.git
```shell
 git clone https://github.com/shivasr/newswhip-url-manager.git
 cd newswhip-url-manager
```
2. Start the docker container
```shell
 docker run -it \
        -v $PWD:$PWD \
        -w $PWD \
        shivasr/news-whip-shell:1.0.0
```

### Hit the REST APIs
1. Add spring.io URL and score
```shell
add --url http://spring.io --score 10
```
Sample Output
```shell
Adding the URL: http://spring.io with score: 10
Done
```

2. Add gmail.com URL and score
```shell
add --url http://gmail.com --score 20
```
Sample Output
```shell
Adding the URL: http://gmail.com with score: 20
Done
```

3. Add gmail.com URL and score
```shell
add --url http://gmail.com/host --score 20
```
Sample Output
```shell
Adding the URL: http://gmail.com/host with score: 20
Done

```
4. Export URLs to generate a statistics
```shell
export
```

Sample Output
```shell
Exporting to the file: exported_url.csv
Done.
```

Contents of exported_url.csv
```shell
% cat exported_url.csv 
spring.io;1;10
gmail.com;2;40
```
5. Delete a URL
```shell
delete --url http://spring.io
```
Sample Output
```shell
Delete the URL: http://spring.io.
Done.
```