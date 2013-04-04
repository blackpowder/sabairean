'use strict'

@AppController = ($scope, $http, localize, $routeParams) ->
  $scope.setLanguage = (language) ->
    localize.setLanguage(language)

  $http.get('chapters/config.json').success (data) ->
    $scope.topics = data.topics

  $scope.get_topic = ($routeParams) ->
    return false unless angular.isDefined($scope.topics)
    for topic in $scope.topics
      if topic.key == $routeParams.topic_key
        $scope.topic = topic
        $scope.chapters = $scope.topic.chapters

  $scope.get_chapter = ($routeParams) ->
    $scope.get_topic($routeParams)
    return false unless $scope.topic_exists()
    console.log('chapter')
    for chapter in $scope.chapters
      $scope.chapter = chapter if chapter.key == $routeParams.chapter_key

  $scope.topic_exists = () ->
    return false unless angular.isDefined($routeParams.topic_key)
    angular.isDefined($scope.topic)

  $scope.chapter_exists = () ->
    return false unless angular.isDefined($routeParams.chapter_key)
    angular.isDefined($scope.chapter)

  $scope.get_step = ($routeParams) ->

@TopicController = () ->

@ChapterController = ($scope, $routeParams) ->
  $scope.get_topic($routeParams)
  $scope.chapters = $scope.topic.chapters

@StepController = ($scope, $http, $routeParams, localize) ->
  $scope.get_chapter($routeParams)
  $scope.path = "chapters/#{$scope.chapter.key}"

  set_step_path = () ->
    $scope.step_path = "#{$scope.path}/#{$scope.step}"
    localize.additional_paths = ["chapters/#{$scope.chapter.key}/i18n"]
    localize.initAllLocalizedResources()

  $scope.step = $routeParams.step
  set_step_path()

  $http.get("chapters/#{$scope.chapter.key}/config.json").success (data) ->
    $scope.config = data
    $scope.step = data.steps[0] if typeof($scope.step) == 'undefined'
    set_step_path()

  $scope.scope_image = (image) ->
    "#{$scope.path}/images/#{image}"


