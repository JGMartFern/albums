<h1> Albums API </h1>

How to run the app:
`./gradlew bootRun`

Once the app is running, you can go to:
http://localhost:8080/api/albums

There you will get a list of all the albums that JSONPlaceholder provides.

Alternatively, you can use this link:
http://localhost:8080/api/photos?albumId=3

Here, you will get the details for all the photos that are associated with the album 3. You can freely change the number
after the symbol "=" as shown below: 
http://localhost:8080/api/photos?albumId=14

This way you can search through different albums and see what photos they contain.

I am choosing to start from bottom to top, creating the model all the way to the top until the rest controller because
this way the code is easier to test. It is simpler to start writing tests when we start from the bottom, and keep adding
until we end up with the whole service and its corresponding tests. I went from simple to complex and not the other way
around.

I am also opting for a Data Source Layer in order to have an application that is more scalable, with its functions a
bit more separated between them. This will also be in our favor when we are testing the application. I am also trying
to make an application that follows the Single Responsibility Principle, which is why I am choosing to separate the
service and the data source layers.