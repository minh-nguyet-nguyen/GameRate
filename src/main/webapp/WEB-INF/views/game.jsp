<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Home</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="GameController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading">
              	<span class="lead">Game Registration Form </span>
              	<a class="floatRight" style="font-size:18px" href="/GameRate/home" id="homeLink">Home</a>
              </div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.game.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Title</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.game.title" name="title" class="form-control input-sm" placeholder="Enter the game's title" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.title.$error.required">This is a required field</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Comment</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.game.comment" name="comment" class="form-control input-sm" placeholder="Enter your comments for the game. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Rating</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.game.rating" name="rating" class="form-control input-sm" placeholder="Enter your rating for the game 1-5" required min="1" max="5"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.rating.$error.required">This is a required field</span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.game.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Games </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID</th>
                              <th>Title</th>
                              <th>Comment</th>
                              <th>Rating</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="game in ctrl.games">
                              <td><span ng-bind="game.id"></span></td>
                              <td><span ng-bind="game.title"></span></td>
                              <td><span ng-bind="game.comment"></span></td>
                              <td><span ng-bind="game.rating"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(game.id)" class="btn btn-success custom-width">Edit</button>
                              <button type="button" ng-click="ctrl.remove(game.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/gameService.js' />"></script>
      <script src="<c:url value='/static/js/controller/gameController.js' />"></script>
  </body>
</html>