
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
          controller: 'loginController',
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
         
         
         var logadoState = {
                 name: 'logado',
                 url: '/logado',
                 templateUrl: 'paginas/logado.html',
                 controller: 'usuarioController',
               };


        $stateProvider.state(homeState);
        $stateProvider.state(loginState);
        $stateProvider.state(registerState);
         $stateProvider.state(compsState);


    });

app.config(function($httpProvider){
    $httpProvider.interceptors.push("tokenInterceptor");
});

