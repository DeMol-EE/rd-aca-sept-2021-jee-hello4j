# Hello world

## Description

* Step 1:
 
Make a service which is reachable over HTTP.
This service returns "Hello, world!" when receiving a GET request to the "/greeting" endpoint.

* Step 2:
 
Extend the service in such a way that it accepts a request parameter named "name".
The greeting should be adapted to return "Hello, <name>!".

* Step 3:

Add "business logic" which ensures that the name (if present) is automatically properly capitalized (first letter is made uppercase, others lowercase).
Extract this logic into a separate component.
Write a unit test to cover this logic.
Use the DI framework to inject an instance of the new component into the HTTP service.
Refactor the HTTP service, so it delegates to the new component.

* Step 3.5

Add a logging filter/interceptor to log all requests and responses.
Use it to log the requested URI on the way in and the response status code on the way out.
Do not use `System.out.println()`, use a logging framework.
The easiest to use is `java.util.logging` (JUL), since it is part of the JDK.

* Step 4:

Add a new endpoint: "/email".
When doing an HTTP POST to this endpoint, an email should be sent.
The recipient should be passed as parameter, the body of the email can be taken from the HTTP request body.
(Bonus: verify that the recipient is a valid email address, using bean validation).

Use MailHog as a fake SMTP server; see next section for more information.
Ensure that the email sending logic is executed asynchronously.
(Bonus: write a test with a mock for the mail sender to verify that calling the endpoint triggers sending an email.)

Add a security rule to protect this endpoint from anonymous access.
Try calling the endpoint and verify that it is not accessible.
Create a user in the JEE platform you are using and try to send an email now.
(Bonus: also secure the EJB.)

* Step 5 (optional):

We'll make one final extension to the greeting endpoint.
Whenever the endpoint is called with a name, that name should be broadcast to a JMS topic (when no name is provided, no broadcast is made).
Also set up a JMS queue and pass it as `JMSReplyTo` header to each message that is broadcast to the topic.
Set up a listener to the queue which logs the responses.

For generating responses, write a tiny, second application containing nothing more than just a JMS consumer.
Register a listener to the topic.
When receiving a broadcast with name "X", reply with "Hello, X!" to the queue from the `JMSReplyTo` header.
Deploy the client application and send some greetings.
Do you get multiple greetings when you deploy the companion application more than once?

## Mock SMTP (MailHog)

SMTP implementation for integration testing (does not really send emails).

### Docker

    docker run --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog

* 1025 is used as SMTP port
* 8025 is used for the web UI

### JEE

```java
@MailSessionDefinition(
        name = "java:app/mail/mailhog",
        from = "jee@realdolmen.com",
        properties = {"mail.smtp.port=1025"}
)
```

## Wildfly

### Config file

    -c standalone-full.xml

### Custom port

    -Djboss.http.port=8081