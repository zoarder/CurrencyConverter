# Android MVP Architecture Tutorials

## package io.left.core;

### MyApplication.jav

This class name will changed based on application name. e.g.: **FlareApplication.java**,  **RightServeApplication.java**

| Method | Details |
| ------ | ------ |
| getContext() |  When context not available in a class, then you can call |
| MyApplication.getContext(); | Call example |
| releaseLoader(sContext); | Load all class which are required for release build  |
| debugLoader(sContext); | Load all class which are required for debug build |

> You can remove unused library initialization if not required for your project
----------
----------

## package io.left.core.util.lib;

### GSonHelper.jav

Generic class for get string and model class using GSON Library.

| Method | Details |
| ------ | ------ |
|  public static <T> String toJson(T modelClassObject) |  Model to String |
| public static <T> String toJson(List<T> listOfModelClassObjects) | List Model to String |
| public static <T1, T2> String toJson(Map<T1, T2> keyValuePairsOfModelClassObjects) | From map to String  |
| public static <T> String toJson(T[] arrayOfModelClassObjects) | From Arry to String |
|public static <T> T fromJson(String json, Class<T> modelClass)|String to Model|
|public static JsonObject fromJson(String json)|String to JsonObject|
|public static <T> List<T> fromJson(String json, Type desiredType)|String to Model List|
