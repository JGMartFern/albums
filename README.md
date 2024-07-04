<h1> Albums API </h1>

I am choosing to start from bottom to top, creating the model all the way to the top until the rest controller because
this way the code is easier to test. It is simpler to start writing tests when we start from the bottom, and keep adding
until we end up with the whole service and its corresponding tests. I went from simple to complex and not the other way
around.

I am also opting for a Data Source Layer in order to have an application that is more scalable, with its functions a
bit more separated between them. This will also be in our favor when we are testing the application. I am also trying
to make an application that follows the Single Responsibility Principle, which is why I am choosing to separate the
service and the data source layers.