# Highrise Java API

Highrise Java API is a java client library for the [Highrise CRM API](http://github.com/37signals/highrise-api/). It contains the main entities of Highrise as Java classes and provides a clean and simple to use API to access the entities from Highrise.

## Entities and Resources

### Supported entities

* Case
* Category
* Deal
* Note
* Person
* Company
* Task
* User

### Supported resources

* Category
* Deal
* Note
* Person
* Task
* User


## Usage

The HighriseClient class provides a facade for accessing all resources. At first a client must be instantiated for a specific URL. If you already have an API token, you can create the client with the token as second argument directly. Otherwise you must only provide the base URL of the Highrise REST API. In the next step you can authenticate the client against the API with a username and a password. The client ist stateful, which means that it stores the API token after the authentication and uses it for all following request.   

Here's an example how to to instantiate the client and authenticate afterwards:

    highriseClient = HighriseClient.create("https://example.highrisehq.com/");
    highriseClient.auth("username", "password"));