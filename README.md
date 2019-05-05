
# Lab 11 ARSW

To the work done with the Cine application that worked through WebSockets, a cache will be added using the NoSQL database, REDIS. This in order to acquire quick access to the state of the seats in each of the rooms.

## Part l
In this section, the relevant machine configurations are made, in addition to adding the dependencies and copying the classes to manage a pool of connections to the REDIS.

## Part ll

For the current version of the application the following functionalities were managed: to receive the seat to buy, to carry out the purchase in the respective function and to publish the purchase of the seat in the corresponding topic. The above, taking into account the respective race conditions.

Use will be made of the REDIS Key-Value database, and a client for Java Jedis.

We implement the `saveToREDIS` method and test its correct operation, initially from the terminal using `get` of REDIS and then with the `getFromREDIS` method from the application.

Then the `buyTicketRedis` and `getSeatsRedis` methods are created, which return an array of boolean associated with the key that represents the function. Then the `buyTicket` method to use `buyTicektRedis` created in the previous point.

Finally, another virtual machine was configured to point to the same REDIS server and verify its functionality.

