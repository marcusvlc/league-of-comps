
app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

        var homeState = {
          name: 'home',
          url: '/',
          templateUrl: 'paginas/slides.html',
          controller: 'usuarioController',
        };
        var loginState = {
          name: 'login',
          url: '/login',
          templateUrl: 'paginas/login.html',
          controller: 'usuarioController',
        };

       	var registerState = {
          name: 'registrar',
          url: '/registrar',
          templateUrl: 'paginas/registrar.html',
          controller: 'usuarioController',
        };

         var compsState = {
          name: 'comps',
          url: '/comps',
          templateUrl: 'paginas/comps.html',
          controller: 'compsController',
        };

        $stateProvider.state(homeState);
        $stateProvider.state(loginState);
        $stateProvider.state(registerState);
         $stateProvider.state(compsState);


    });

app.config(function($httpProvider){
    $httpProvider.interceptors.push("tokenInterceptor");
});

