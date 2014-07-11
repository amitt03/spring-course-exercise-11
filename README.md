
Exercise 11
===========


Business Requirements:
======================

Create an application that will act as an **Aggregator of external Instant Messaging Vendors** :-)<br/>
Meaning, we must be able to connect to **external IM Aggregator vendors** in order to send **IM Messages**.<br/>
We will make a profit by selling Instant Messaging ourselves AND redirecting the request to the cheapest IM vendor at the time of the request.<br/>

Product Requirements:
=====================

1. Enable to connect to various external **IM vendors**.

2. Expose API for sending **SMS Messages**<br/>
   The API will receive the following:<br/>
    - *user id*     (mandatory)
    - *from number* (only digits, 10 digits long)
    - *to number*   (only digits, 10 digits long)
    - the *message* itself (no constraint on the length, may be empty)
    - *bid in cents* representing how much the caller is willing to pay for this api (notice, bid must be a natural number)<br/>
    The application will lookup all **IM Vendors that support SMS messages** and request a quote.<br/>
    The application objective is to make the **highest** profit (quotes vs caller bid),<br/>
    meaning that the **SMS Message** will be directed to the **cheapest IM vendor at the time of the request**.<br/>
    If the application cannot make a profit then an error is sent back to the caller.<br/>

3. Expose an administrator API for viewing the **total profit** of the application

4. Add **throttling** mechanism in order to protect the system.<br/>
   Do not allow more then **[X] Send SMS Messages per minute** (throttling is general and NOT per user)<br/>
   "X" above should be configurable via **configuration file**, if it is not configured then the default value is 5.<br/>


Internal Requirements:
======================

1. **Throttling** mechanism should be written in such a way that we can disable it in development and enable it in QA / Production.

2. Expose to **JMX** how many **"Send SMS"** APIs were called per minute (elevate the throttling data)

3. Write **unit test** (including mocking) to the service logic<br/>
   (make sure **IM vendor** is chosen correctly in correlation to request IM and request bid)

4. Write **integration test** to the facade API
    - Test happy flow, send sms that succeeds (provide a high bid > 500)
    - Test flow where bid is insufficient
    - Test validation, i.e. bid is negative or from number is not a 10 digit number

5. Write **remote (system) test**:
    - Test happy flow, send sms that succeeds (provide a high bid)
    - Test flow where too many APIs are called in one minute (throttling)

6. Understand internal service **exceptions** and adjust the facade exception handler

7. Make sure **swagger** is fully enabled for the new APIs.<br/>
   Meaning that you can execute all of the APIs via swagger, and all APIs are well documented and understood.


Where to start?
===============

**Product Requirement 1 is already implemented.**<br/>
View the following modules:<br/>
    - **im-aggregator-vendor-api**          --> Understand the APIs<br/>
    - **im-aggregator-vendor-joker-impl**   --> (NO NEED TO READ THIS PROJECT, all you need to know is that it implements the above api.<br/>
    - **im-aggregator-service-model**       --> Look at the data structure<br/>

