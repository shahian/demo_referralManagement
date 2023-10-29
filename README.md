# demo_referralManagement
* JPA Criteria API vs JPQL
A major advantage of using the criteria API is that errors can be detected earlier, during compilation rather than at runtime. On the other hand, for many developers string based JPQL queries, which are very similar to SQL queries, are easier to use and understand.
For simple static queries - string based JPQL queries (e.g. as named queries) may be preferred. For dynamic queries that are built at runtime - the criteria API may be preferred.
For example, building a dynamic query based on fields that a user fills at runtime in a form that contains many optional fields - is expected to be cleaner when using the JPA criteria API, because it eliminates the need for building the query using many string concatenation operations.
String based JPQL queries and JPA criteria based queries are equivalent in power and in efficiency. Therefore, choosing one method over the other is also a matter of personal preference. 
* Unit testing 
