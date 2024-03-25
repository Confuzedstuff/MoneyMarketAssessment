# Money Market Assessment

## Getting Started
1. Clone the repo.
2. Run `docker-compose up` in the root directory of the repository to start the auth server and database.
3. Run the api project found in the `./Api` folder using `Intellij`.
4. Install `Insomnia` and import the `Insomnia` collection found at `./docs/api-local-insomnia.json`.
5. Retrieve auth token for user.
6. You can now use this as a `Bearer` token to make requests to the api using.

## Design
![overview](/docs/Overview.jpg)

The design assumes the api is deployed to AWS.

- The REST API is deployed as an auto scaling cluster that monitors cpu and memory usage to determine when spin up/down new instances of the api microservice.

- The REST API sits behind a hardware load balancer which will ensure that traffic is routed to the API cluster effeciently. The load balancer is the only component exposed to the internet. 

- CQRS is used to ensure high read performance even when there is a backlog of writes. The database is spilt up into a primary db and a secondary db. Commands are immediately written to the primary db and queued to eventually be written to the secondary db. Queries are only excuted on the secondary database.

## Local Dev environment
Deployed locally using `docker compose`.

## Security
- To ensure maximum security the cloud environment should be configured to have the smallest attack surface. This can be achieved by deploying the api in a private VPC that does not talk to the internet. The api in the private VPC can then be exposed to incoming traffic by routing that traffic via DMZ implemented as a public VPC that only exposes an application load balancer to the public internet.

### Authentication
#### Cloud based
##### Pros
- Much simpler setup.
- No scaling required.
- Data security handled by cloud provider.
##### Cons
- Costly (On AWS without Advanced features like MFA costs already reach 17415 USD per month)

#### Self Managed
##### Pros
- Much cheaper

#### Cons
- More complex to set up correctly.
- Extra work required to ensure scalibility.
- Responsibility to secure and manage user data lies with us.

The example uses  a Keycloak server hosted on port `8090`.

## Logging and monitoring
- Request metrics can be logged to Prometheus + Grafana for visualisation.

# Deployment to UAT/Production 
- Using Kubernetes the entire system can be defined as a single configuration and easily control the infrastructre used on AWS
- To allow for zero downtime deployment the api can be deployed using a blue/green deployment strategy.

# Addendum
-  Download Insomnia [here](https://insomnia.rest/download).

# Things not considered
- Setting up the cloud enviroment using a DMZ
