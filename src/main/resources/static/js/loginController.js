app.controller("loginController", function($scope, $http, $location){
	$scope.urlServer = "http://comps-comps.a3c1.starter-us-west-1.openshiftapps.com";
	$scope.token = "";
//	$scope.urlServer = "http://localhost:8080";

	
	$scope.autenticar = function(email, senha) {
		Usuario = {email:"", senha:"", composicoes:[]};
		Usuario.email = email;
		Usuario.senha = senha;
		

		
		if(Usuario.email == "" || Usuario.email == undefined || Usuario.senha == "" || Usuario.senha == undefined) {
			  M.toast({html: 'Preencha os campos corretamente para logar-se!', classes: 'rounded'});
		} else {
			$http.post($scope.urlServer + "/autenticar", Usuario)
			.then(function (resposta){
				$scope.token = resposta.data.token;
				localStorage.setItem("userToken", resposta.data.token);
				$scope.armazenarUsuario(email);
				M.toast({html: 'Logado com sucesso <br/> Sua sessão acaba em 15 minutos!', classes: 'rounded'});
				$location.path("/");

				
			}, function(resposta){
				  M.toast({html: 'Algum dado está incorreto, tente novamente!', classes: 'rounded'});
				
			});
		}
	}
	
	
	$scope.armazenarUsuario = function(email){
		$http.get($scope.urlServer + "/usuario/" + email)
		.then(function (resposta){
			localStorage.setItem("userInfo", JSON.stringify(resposta.data));
		}, function(resposta){
			console.log("Erro ao recuperar usuario");
		});
	}
});