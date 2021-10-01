# spring-cloud-circuitbreaker
sample for spring cloud circuit breaker using resilience4j

Order Service calls Payment Service.<br>
Circuit Breaker configuration added as Java Bean.<br>
@CircuitBreaker annotation from Resilience4j. Fallback method defined.<br>
<code>@CircuitBreaker(name="paymentCB", fallbackMethod = "fallbackOrder")</code>
