app.controller("usuarioController", function($scope, $http, $location){
	$scope.titulo = "League of Comps";
	$scope.urlServer = "https://leagueofcomps.herokuapp.com";
//	$scope.urlServer = "http://localhost:8080";

		
	$scope.cadastrarUsuario = function(email, senha, resenha) {
		Usuario = {email:"", senha:""};
		Usuario.email = email;
		Usuario.senha = senha;

		if(Usuario.email == "" || Usuario.senha == "" || Usuario.email == undefined || Usuario.senha == undefined || resenha == "" || resenha == undefined) {
			  M.toast({html: 'Preencha os campos corretamente para se cadastrar!', classes: 'rounded'});
		} else {
			if(senha == resenha) {
				$http.post($scope.urlServer + "/cadastrar", Usuario)
				.then(function (resposta){
					  M.toast({html: 'Você se cadastrou com sucesso! Faça login e aproveite!', classes: 'rounded'});
					  $location.path("/");
					
				}, function(resposta){
					  M.toast({html: 'Já existe um usuário com esse e-mail, tente outro!', classes: 'rounded'});
					
				});
			} else {
				  M.toast({html: 'As senhas não condizem.', classes: 'rounded'});
			}
		}
	
	}
	
	


});