var App = angular.module('app',['ui.router']);

App.config(['$stateProvider', '$urlRouterProvider', 
	function($stateProvider, 
		$urlRouterProvider){

	//if any url does not match with states, it will redirect to login
	$urlRouterProvider.otherwise("/")
	
	$stateProvider
	.state('home', { //state for Home -- DONE
		url: "/",
		templateUrl: "home.html",
		controller: "HomeController"
	})

	.state('dashboard', { //state for Home -- DONE
		url: "/dashboard",
		templateUrl: "dashboard.html",
		controller: "DashBoardController"
	})
	.state('categories', { //state for All Categories -- DONE
		url: "/categories",
		templateUrl: "categories.html",
		controller: "CategoryController"
	})
	.state('featured', { //state for dashboard
		url: "/featured",
		templateUrl: "featured.html",
		controller: "DashBoardController"
	})
	.state("/all/product",{	//state for showing all products in a category
		url: "/all/product/:cid",
		templateUrl: "product.html",
		controller: "CategoryProductController"
	})
	
	.state("/show/vendor",{	//state for showing vendor -- DONE
		url: "/show/vendor/:vid",
		templateUrl: "vendor.html",
		controller: "VendorController"
	})
	
	.state("/addtocart",{ //state for adding to cart -- DONE except TOTAL AMT
		url: "/addtocart/:pid",
		templateUrl: "cart.html",
		controller: "AddToCartController"
	})
	
	.state("/deletefromcart",{ //state for removing from cart -- DONE except TOTAL AMT
		url: "/deletefromcart/:pid",
		templateUrl: "cart.html",
		controller: "RemoveFromCartController"
	})


}])

.controller("LoginController", function($rootScope,$scope,$http,$location,$window) {
	
	$scope.validate=function(){
		
		//call the api and do the process
		//if success[201] - redirect to /dashboard.
		$http({
	        method : "GET",
	        url : "http://localhost:1234/rest/login",
	        headers : {
		        "username" : $scope.username,
		        "password" : $scope.password
		    }
	    }).then( _success, _error );
		
		function _success(response) {
	        $rootScope.user = response.data;
	        //console.log($rootScope.user);
	        $window.localStorage.setItem("user", angular.toJson($scope.user));
	        $window.location.href="#!/dashboard";
		}
	    function _error(response) {
	    	//console.log($scope.user);
	    	$scope.msg="Invalid Username/Password";
	    }		
	}		 
 })
 
.controller("HomeController", function($rootScope,$scope,$http,$location,$window) {
	$http({
		method : "GET",
		url : "http://localhost:8762/api/shop-service/product/get/prodofday",
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then(_successProdOfDay, _errorProdOfDay);
	
	function _successProdOfDay(response){
		$scope.pofd = response.data;	
	}
	
	function _errorProdOfDay(response){
		$scope.msg="Error";
	}
 })
 
 .controller("DashBoardController", function($rootScope,$scope,$http,$location,$window) {
	
	 $http({
	        method : "GET",
	        url : "http://localhost:8762/api/shop-service/product/get/prodofday",
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.productOfDay = response.data;
	        console.log("hello");
	    }
	    function _error(response) {
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
.controller("CategoryController", function($rootScope,$scope,$http,$location,$window) {
	
	 $http({
	        method : "GET",
	        url : "http://localhost:8762/api/shop-service/category/getall",
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.categories = response.data;	       
	    }
	    function _error(response) {	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
 .controller("ProductController", function($rootScope,$scope,$http,$location,$window,$stateParams) {
	 //$scope.p = $stateParams.pid;	
	 $http({
	        method : "GET",
	        url : "http://localhost:8762/api/shop-service/product/getone/"+$stateParams.pid,
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.product = response.data;
	    }
	    function _error(response) {
	    	
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
 .controller("VendorController", function($rootScope,$scope,$http,$location,$window,$stateParams) {
	 $http({
	        method : "GET",
	        url : "http://localhost:8762/api/shop-service/vendor/show/vendor/"+$stateParams.vid,
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.vendor = response.data;
	        console.log(response.data);
	        //console.log($scope.product);
	    }
	    function _error(response) {
	    	//console.log($scope.user);
	    	//console.log($scope.pid);
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
 .controller("AddToCartController", function($rootScope,$scope,$http,$location,$window,$stateParams) {
	 $http({
	        method : "POST",
	        url : "http://localhost:8762/api/shop-service/cart/addtocart/"+$stateParams.pid,
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
			$scope.total = 0;
	        $scope.cartItems = response.data;	     
	    }
	    function _error(response) {	    	
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
 .controller("RemoveFromCartController", function($rootScope,$scope,$http,$location,$window,$stateParams) {
	 $http({
	        method : "POST",
	        url : "http://localhost:8762/api/shop-service/cart/deletefromcart/"+$stateParams.pid,
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.cartItems = response.data;	     
	    }
	    function _error(response) {	    	
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
 .controller("CategoryProductController", function($rootScope,$scope,$http,$location,$window,$stateParams) {
	 $http({
	        method : "GET",
	        url : "http://localhost:8762/api/shop-service/product/get/categoryproduct/"+$stateParams.cid,
	        	headers : {
	    			'Content-Type' : 'application/json'
	    		}

	    }).then( _success, _error );
		
		function _success(response) {
	        $scope.product = response.data;	 
	        console.log(response.data);
	    }
	    function _error(response) {	    	
	    	$scope.msg="Unable to load Category Data";
	    }
 })
 
