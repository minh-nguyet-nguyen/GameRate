'use strict';

angular.module('myApp').controller('GameController', ['$scope', '$log' ,'GameService', function($scope, $log, GameService) {
    var self = this;
    self.game = { id: null, title: '', comment: '', rating: null};
    self.games = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllGames();

    function fetchAllGames(){
        GameService.fetchAllGames()
            .then(
            function(d) {
                self.games = d;
            },
            function(errResponse){
                $log.error('Error while fetching Games ', errResponse);
            }
        );
    }

    function createGame(game){
        GameService.createGame(game)
            .then(
            fetchAllGames,
            function(errResponse){
                $log.error('Error while creating Game ', errResponse);
            }
        );
    }

    function updateGame(game){
        GameService.updateGame(game)
            .then(
            fetchAllGames,
            function(errResponse){
                $log.error('Error while updating Game ', errResponse);
            }
        );
    }

    function deleteGame(game){
        GameService.deleteGame(game)
            .then(
            fetchAllGames,
            function(errResponse){
                $log.error('Error while deleting Game ', errResponse);
            }
        );
    }

    function submit() {
        if(self.game.id===null){
            $log.log('Saving New Game', self.game);
            createGame(self.game);
        }else{
            updateGame(self.game, self.game.id);
            $log.log('Game updated with id ', self.game.id);
        }
        reset();
    }

    function edit(game){
		var id = game.id;
        $log.log('id to be edited', id);
        for(var i = 0; i < self.games.length; i++){
            if(self.games[i].id === id) {
                self.game = angular.copy(self.games[i]);
                break;
            }
        }
    }

    function remove(game){
        $log.log('id to be deleted', game.id);
        //clean form after game is deleted
        if(self.game.id === game.id) { 
            reset();
        }
        deleteGame(game);
    }


    function reset(){
        self.game={id: null, title: '', comment: '', rating: null};
        $scope.myForm.$setPristine();
    }

}]);
