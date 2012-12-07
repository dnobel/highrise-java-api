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
    
## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.    