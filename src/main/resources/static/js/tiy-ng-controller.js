angular.module('TIYAngularApp', [])
   .controller('ToDoApp', function($scope, $http) {
        $scope.getAllToDo = function(){
        console.log("Retrieving existing ToDos");
        $http.get("http://localhost:8080/todos.json")
            .then(
            function successCallBack(response){
                console.log(response.data);
                console.log("Adding data...");
                $scope.todos = response.data;
            },
            function errorOnCallBack(response){
            console.log("Unable to get data");
            });
        }

        $scope.deletetodo = function(){
        $http.get("http://localhost:8080/deletetodo.json")
        .then(
        function successCallBack(response){
        console.log(response.data);
        console.log("Adding data to scope");
        $scope.todos = response.data;
        },
        function errorOnCallBack(response){
        console.log("Error retrieving data");
        });
        }

        $scope.addtodo = function(){
        $http.get("http://localhost:8080/addtodo.son")
        .then(
        function successCallBack(response)
        console.log(response.data);
        console.log("Adding data to scope");
        $scope.todos = response.data;
        },
        function errorOnCallBack(response){
        console.log("Error retrieving data")
        });
        }
   });
