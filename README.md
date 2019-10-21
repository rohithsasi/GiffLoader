A brief summary of the architecture and some explanations

-The architecture followed here is MVVM using the android architecture components.

-The ui layer talk to the presentation layer(view model) asking for the data it needs. The viewmodel makes the api call to
the apps data repository. The only reference ViewModel keeps is that of the  data repo. VM is completely de coupled from the ui.

-The data repo layer is responsible for calling into the api layer which in turn makes the network requests using retrofit returning a deffered result object.

-The network req is made via suspend functions and is launched in a coroutine in the viewmodel in a viewmodel scope.

- Api call is executed in a suspend fashion with appropriate threading done by with coroutine and suspend functions.

-The repo layer also maps the data from the api into a result wrapper before updating the livedata.

-Finally the ui layer is observing on the livedata which gets updated everytime we make a network request by the presentation layer.


Activities/Frag ---->ViewModel---->Repository----->Api----->Retrofit

Components Used :
1)Glide -> Image loading
2)Rerofit -> Network Client
3)Coroutines + Livedata -> Threading and Data Updates
4)I have also used the android jetback navigation components for quick implementation of fragment transition.


How the app work?
The home screen fetches "trending" gif images on load if there in network connectivity. Users can search for any gif they like from the home screen
with the help of the search bar and Ui is updated with top 25 search results or the appropriate error message if nothing is found.

Some Additional features I did:
-A bottom navigation is implemented to give it a full feel of an app and proper navigation between screens
-Additional screen in a fragment with a webview that lauched the giphy login page.
-Network connection error dialogs.





