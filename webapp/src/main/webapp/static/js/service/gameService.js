'use strict';

angular.module('myApp').factory('GameService', GameServiceFactory)

GameServiceFactory.$inject = ['$http', '$log']

function GameServiceFactory($http, $log) {
	var REST_SERVICE_URI = 'http://localhost:8080/GameRate/game/';

    var factory = {
        fetchAllGames: fetchAllGames,
        createGame: createGame,
        updateGame:updateGame,
        deleteGame:deleteGame
    };

    return factory;

    function fetchAllGames() {
        return $http.get(REST_SERVICE_URI).then(
			function (response) {
                return response.data;
            },
            function (errResponse) {
                $log.error('Error while fetching Games ', errResponse);
            }
        );
    }

    function createGame(game) {
        return $http.post(REST_SERVICE_URI, game).then(
            function (response) {
                return response.data;
            },
            function(errResponse){
                $log.error('Error while creating Game', errResponse);
            }
        );
    }


    function updateGame(game) {
        return $http.put(REST_SERVICE_URI + game.id, game).then(
            function (response) {
                return response.data
            },
            function(errResponse){
                $log.error('Error while updating Game', errResponse);
            }
        );
    }

    function deleteGame(game) {
        return $http.delete(REST_SERVICE_URI + game.id).then(
            function (response) {
                return response.data
            },
            function(errResponse){
                $log.error('Error while deleting Game', errResponse);
            }
        );
    }
}
