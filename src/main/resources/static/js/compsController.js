app.controller("compsController", function($scope, $http, $location){
	
	$scope.urlServer = "http://comps-comps.a3c1.starter-us-west-1.openshiftapps.com";
	//$scope.urlServer = "http://localhost:8080";

	$scope.user = {};
	$scope.user = JSON.parse(localStorage.getItem("userInfo"));


	$scope.init = function() {
		$http.get($scope.urlServer + "/api/usuarios")
		.then(function (resposta){
			
		}, function(resposta){
			console.log("Init error");
		});
	}
	
	$scope.deletarComp = function(comp){
		
		$http.delete($scope.urlServer + "/api/removerComp/" + comp.titulo)
		.then(function (resposta){
			for(i = 0; i < $scope.user.composicoes.length; i++) {
				if($scope.user.composicoes[i].titulo == comp.titulo) {
					$scope.user.composicoes.splice(i,1);
					break;
				}
			}
			
		M.toast({html: 'Composição removida com sucesso!', classes: 'rounded'});
		localStorage.setItem("userInfo", $scope.user);
			
		}, function(resposta){
			  M.toast({html: 'Algo deu errado ao remover essa composição!', classes: 'rounded'});
			
		});
		

		
	}
	
	
	
	$scope.cadastrarComposicao = function(titulo, descricao, top, mid, jg, adc, sup) {
		if(atributoEhInvalido(titulo) || atributoEhInvalido(descricao) || atributoEhInvalido(top) || atributoEhInvalido(mid) || atributoEhInvalido(jg) || atributoEhInvalido(adc) || atributoEhInvalido(sup)) {
			  M.toast({html: 'Preencha todos os campos corretamente para cadastrar uma composição!', classes: 'rounded'});
		} else {
			$http.get($scope.urlServer + "/api/listarComps")
			.then(function (resposta){
				compExiste = false;
				
				for(i = 0; i < resposta.data.length; i++) {
					if(resposta.data[i].titulo == titulo)  {
						compExiste = true;
					}
				}
				
				
				if(compExiste == true) {
					  M.toast({html: 'O sistema já possui uma composição com esse título, tente outro!', classes: 'rounded'});

				} else {
					Composicao = {};
					Composicao.titulo = titulo;
					Composicao.descricao = descricao;
					Composicao.campeoes = [top, jg, mid, adc, sup];
					
					$http.post($scope.urlServer + "/api/" + $scope.user.id + "/addComp", Composicao)
					.then(function (resposta){
						Composicao.id = resposta.data.id;
						$scope.user.composicoes.push(resposta.data);
						localStorage.setItem("userInfo", JSON.stringify($scope.user));
						M.toast({html: 'Composição cadastrada com sucesso! :)', classes: 'rounded'});
						$('#modal1').modal('close');

						
					}, function(resposta){
						  M.toast({html: 'Algum dado está incorreto, tente novamente!', classes: 'rounded'});
						
					});
					
				}
			}, function(resposta){
				console.log("Init error");
			});
			
			
	}

}
	
	var atributoEhInvalido = function(atributo) {
		if(atributo == null || atributo == undefined || atributo == "") {
			return true;
		} else {
			return false;
		}
	}
	
	$scope.abrirModalComps = function () {
		$scope.titulo = "";
		$scope.descricao = "";
		$scope.top = "";
		$scope.mid = "";
		$scope.jg = "";
		$scope.adc = "";
		$scope.sup = "";
		$('#modal1').modal('open');
	}
	
	$scope.init();
	
});