# [Best practices for REST API design](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/#h-maintain-good-security-practices)

## 1. Overview

REST APIs are one of the most common kinds of web interfaces available today. They allow various clients including browser 
apps to communicate with services via the REST API. Therefore, it’s very important to design REST APIs properly so that 
we won’t run into problems down the road. We have to take into account 
[security](https://stackoverflow.blog/2021/10/06/best-practices-for-authentication-and-authorization-for-rest-apis/), 
performance, and ease of use for API consumers.  

Otherwise, we create problems for clients that use our APIs, which isn’t pleasant and detracts people from using our API. 
If we don’t follow commonly accepted conventions, then we confuse the maintainers of the API and the clients that use 
them since it’s different from what everyone expects.  

In this article, we’ll look at how to design REST APIs to be easy to understand for anyone consuming them, 
[future-proof](https://stackoverflow.blog/2022/05/19/crystal-balls-and-clairvoyance-future-proofing-in-a-world-of-inevitable-change/),
and secure and fast since they serve data to clients that may be confidential.  

* Accept and respond with JSON
* Use nouns instead of verbs in endpoint paths
* Name collections with plural nouns
* Nesting resources for hierarchical objects
* Handle errors gracefully and return standard error codes
* Allow filtering, sorting, and pagination
* Maintain Good Security Practices
* Cache data to improve performance
* Versioning our APIs