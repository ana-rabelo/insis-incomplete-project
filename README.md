<div align="center" style="margin-top: 1em; margin-bottom: 3em;">
  <a><img alt="microservices" src=https://solace.com/wp-content/uploads/2020/05/microservices.png" alt="ethereum.org" width="125"></a>
  <h1>Project ACME INSIS</h1>

  [![Contributors][contributors]][contributors-url]
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#non-functional-requirements">Non-functional Requirements</a></li>
        <li><a href="#functional-requirements">Functional Requirements</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>
<p>

<!-- ABOUT THE PROJECT -->
## About The Project

The goal of this project is to reengineer the <a href="https://github.com/ana-rabelo/insis-monolithic-project" target="_blank">ACME application</a> by adopting a **decentralized/distributed** approach.

- Two decentralization/distribution dimensions should be addressed:

    - <span style="color:#009639"><b>Business domain segregation</b></span>: the monolithic application must be segregated in three distinct but collaborating applications:
    
        (i) Products; 
        
        (ii) Reviews;
        
        (iii) Votes.

    - <span style="color:#009639"><b>Cloning</b></span>: Multiple instances of each of the previous application must be deployed in Virtual Machines or containers

### **Non-functional Requirements**
<hr />

- The following patterns must be adopted:

    - (Strangler fig)
    - Command-Query Responsibility Segregation (CQRS)
    - Database-per-Service
    - Polyglot persistence
    - Messaging
    - The Domain Events
    - Event Sourcing
    - Saga  <p>


- AMQP Message Broker (e.g. RabbitMQ) must be adopted for communication between services. 
NB: External application endpoints should remain HTTP REST.

- Two or more frameworks/programming languages must be adopted in implementing the services.
- Deployment must be automated through CI/CD.
- Adoption of service component test pattern.
- Adoption of end-to-end test pattern. 
<p>

### **Functional Requirements**
<hr />

- Develop an endpoint in service Votes that allows <span style="color:#009639"><b>creating a vote for a non-existing review.</b></span> The review must be created for the specified product, and the vote is eventually associated with the review.

- Develop a <span style="color:#009639"><b>bootstrap process for the starting services.</b></span> I.e. at any time, a new service can start and its data must be bootstrapped.

### **Built With**
<hr/>

- [![Docker][docker]][Docker-url]

- [![RabbitMQ][rabbitmq]][RabbitMQ-url]

- [![Spring][spring]][Spring-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
[contributors]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/ana-rabelo/insis-project/graphs/contributors
[docker]: https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com/
[RabbitMQ]: https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white
[RabbitMQ-url]: https://www.rabbitmq.com/
[Spring]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/ 