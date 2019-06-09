h4. The Point Of It All

This is service forms part of a technology demonstration project. In order to demonstrate web service technology user actions are simulated (with Gatling) and various technology stacks provide resources consumed by the simulated users. The resources are not of concern and should be interpreted as an abstract collection of possibilities. Of concern to this project is data that can be collected about the user experience, in this case simulated. Contrived data to be collected are:
- a resource ID,
- a duration, and
- an interest metric.

 These metrics will be collected from a GET and POST request from unknown users. The GET method, GET /api?user=<UUID>&resource=<resourceId>, will provide a start time, a resource ID, and a Universal Unique ID (UUID).
The POST method, POST /api?user=<UUID>&interest=<doubleValue>, will be used to determine the duration that the user, identified by the UUID, spent engaged with the resource, and some double value that can be used to gauge the users interest in the resource.
A later project may provide data that can be used for AI/ML data processing but that is not of concern here.


Project Dependencies
- Java
- Eclipse
- JavaServlet
- Tomcat
