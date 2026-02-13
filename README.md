# Docker

### Prerequisites
* Docker
###### For windows
* WSL
* Git Bash


### Tips
* Make sure the Docker Engine is running, for windows the Docker app HAS to be open.
* Only ever run docker or .sh scripts in a unix-esque terminal shell
    * (windows) Use git bash. Do NOT run in a WSL, powershell, or command prompt shell!!!!! It won't work!
    * (Linux/MacOS) Any shell works. e.g. bash, zsh, fish ...
* To run a script use `./<script_name>.sh` e.g. `./run.sh` 
    * Note - You can also add the directory path to the script infront of the name to skip having to cd to where the script is
    * `./<dir_to_script>/<script_name>.sh` e.g. `./scripts/run.sh`

### Commands
* `docker compose up` - start the application
* `docker compose down` - stop the application
* `docker compose logs` - show terminal output for all services (add the name of a container to the end to get that services logs)
* `docker compose ps` - show the running containers within your compose project
* `docker ps` - show all running docker containers
#### Flags
* `-f` - add a .yml file for compose to use
* `-d` - run your containers in detached mode, lets you use the same terminal instance instead of having to switch to a new one
* `--build` - rebuild your docker images

### Compose Files

Here is the compose.local.yml for this repo since half of yall arn't using Spring-Boot, and docker is already hard enough to download.
```
services:
  db:
    image: mysql:8.0
    restart: unless-stopped
    environment:
      MYSQL_DATABASE: ${MYSQL_DATABASE}
      MYSQL_USER: ${MYSQL_USER}
      MYSQL_PASSWORD: ${MYSQL_PASSWORD}
      MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
    volumes:
      - mysql_data:/var/lib/mysql
      - ./backups:/backups    
    ports:
      - "127.0.0.1:3306:3306"

  backend:
    ports:
      - "8080:8080"
    env_file:
      - .env
    environment:
      SPRING_PROFILES_ACTIVE: dev
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/${MYSQL_DATABASE}
      DB_HOST: db            
      DB_PORT: 3306
    depends_on:
      - db

volumes: 
  mysql_data:
```

### Scripts
Here is the script to bring the app up. To make the others, the only line you should change is the last one.
```
#!/usr/bin/env bash
set -euo pipefail

docker compose version >/dev/null 2>&1 || {
  echo "Docker Compose v2 required (docker compose)"
  exit 1
}

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
cd "$REPO_ROOT"

docker compose \
  -f compose.yml \
  -f compose.local.yml \
  up -d --build    # <-- change this one 
```
