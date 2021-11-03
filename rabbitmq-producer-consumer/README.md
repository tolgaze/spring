Steps to run RabbitMQ server and Spring Boot apps on local:

1- Install Docker Desktop

2- On Command Prompt, run docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management

3- You can sign in RabbitMQ UI on http://localhost:15672 username:guest password:guest

4- Import and run Spring Boot producer and consumer apps

5- Send post request to producer api. Sample request is included in rabbitmq-producer.postman_collection.json file in rabbitmq-producer directory
