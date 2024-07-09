<h1> Albums API </h1>

How to run the app:
`./gradlew bootRun`

Once the app is running, you can use Swagger OpenAPI to get a better understanding of it:
http://localhost:8080/swagger-ui/index.html

Alternatively, you can use directly its endpoints, such as this one for the albums:
http://localhost:8080/api/albums

There you will get a list of all the albums that JSONPlaceholder provides.

Alternatively, you can use this link:
http://localhost:8080/api/photos?albumId=3

Here, you will get the details for all the photos that are associated with the album 3. You can freely change the number
after the symbol "=" as shown below: 
http://localhost:8080/api/photos?albumId=14

This way you can search through different albums and see what photos they contain.

In order to run all tests, use this:
`./gradlew test`

If you want to pass a lint check, you can use this command:
`./gradlew ktlintCheck`

And if the check is raising some alarms, you can use this command for applying ktlint directly and formatting your code:
`./gradlew ktlintFormat`



<h2>Design choices</h2>

In order to make a more maintainable, scalable application, I chose to divide controllers, services and data sources
between Photo and Album, because they are two models that are not alike at all. Also, having them separated from each
other will allow us to basically work in any of them independently. We can still develop Photo service and controller,
and Album can stay as it is with no risk of getting in our way, since they are separated. Each one has its own
responsibilities, they are independent of each other, and can be worked on separately.

Following on this, each one has its own integration test for the purposes of scalability and maintainability. If we want
to expand this application in the future, it is very likely that we want to check that they work independently of each
other. Besides, having smaller, differentiated tests can be helpful to maintain and debug the application if it keeps
growing.

I am choosing to start from bottom to top, creating the model all the way to the top until the rest controller because
this way the code is easier to test. It is simpler to start writing tests when we start from the bottom, and keep adding
until we end up with the whole service and its corresponding tests. I went from simple to complex and not the other way
around.

I am also opting for a Data Source Layer in order to have an application that is more scalable, with its functions a
bit more separated between them. This will also be in our favor when we are testing the application. I am also trying
to make an application that follows the Single Responsibility Principle, which is why I am choosing to separate the
service and the data source layers.

I have made the decision of trying the application using mock data and not removing them from the code. The reasoning
behind this is to have something to start without depending on external services such as JSONPlaceholder. This way I
could create services, controllers and tests without hitting the JSONPlaceholder endpoints. Also, it made implementing
the final version with the actual calls to JSONPlaceholder much easier with minimal changes, since everything was 
already tested. I decided not to remove that part of the code so anyone could take a look at it, see similarities, opt
for a similar approach and so forth. In this context of a technical test, and in spirit of having this app stored in my
GitHub publicly, I chose to let the mock datasources be, so anyone could take them as examples for practicing.

I chose not to use a ControllerAdvice this time, because of the scale that the application has at this time. Being only
two exceptions, and both of them being part of the PhotoController, I see no need of creating it. However, it would be
very advisable in the case that we expand our app in the future and start having more controllers, each of them with 
their own exceptions. A centralized ControllerAdvice can do wonders for having those exceptions organised and easily 
located.

In the latest changes, I have tinkered a bit with the Album model, so it can include a collection of photos inside each
one if it's possible. The most efficient way of doing this changes is to make a single GET request and take all photos
at once, and then distribute them among their corresponding albums internally. This way, we do not have to do one GET
request for each album (that would amount to around 100 requests), but instead I am doing just one and taking all the
data we need (which coincidentally is all the data). Of course, this solution works for this case because of its 
simplicity, and because I already know the data I am going to get in advance. In the future, if someone is inclined to
integrate this with front-end, a possible and elegant solution would be the use of pagination, so we don't have to 
retrieve all the data at once. The sheer amount of albums is enough to think about some way of managing these data and 
displaying it by pieces (for example, 5 albums at once, with their photos, which is still a lot but more manageable than
all 100 of them at the same time).