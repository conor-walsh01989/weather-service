# weather-service
The weather-service provides an API to retrieve weather information. 
It supports the retrieval of weather information using textual location data, lat-long coordinates or zip code data.
The application uses the publicly available OpenWeather API available at https://openweathermap.org/. 

## Running the Application
Use the following command to run the weather-service application
``` mvn spring-boot:run ```
## Tests
Use the following command to run the applications tests
``` mvn test ```
## Example Queries
### 1. Retrieve weather information using a City Name

**Request**
```GET localhost:8080/api/v1/weather/city?city=New York ```

**Response**
```{
    "cityName": "New York",
    "country": "US",
    "latitude": 40.7143,
    "longitude": -74.006,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Clear",
            "description": "clear sky"
        }
    ],
    "weatherDetail": {
        "temperature": 269.83,
        "temperatureMin": 269.15,
        "temperatureMax": 270.93,
        "pressure": 1019.0,
        "humidity": 68.0
    },
    "windDetail": {
        "speed": 3.09,
        "degree": 0.0
    }
}

```
### 2. Retrieve weather information using a City Name and State Code

**Request**
```GET localhost:8080/api/v1/weather/city?city=Birmingham&stateCode=UK ```

**Response**
```
{
    "cityName": "Birmingham",
    "country": "GB",
    "latitude": 52.4814,
    "longitude": -1.8998,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Rain",
            "description": "light rain"
        }
    ],
    "weatherDetail": {
        "temperature": 284.24,
        "temperatureMin": 283.71,
        "temperatureMax": 284.82,
        "pressure": 998.0,
        "humidity": 87.0
    },
    "windDetail": {
        "speed": 6.69,
        "degree": 160.0
    }
}

```
### 3. Retrieve weather information using a City Name, State Code and Country Code

**Request**
```GET localhost:8080/api/v1/weather/city?city=Newark&stateCode=NJ&countryCode=US ```

**Response**
```
{
    "cityName": "Newark",
    "country": "US",
    "latitude": 40.7357,
    "longitude": -74.1724,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Clouds",
            "description": "few clouds"
        }
    ],
    "weatherDetail": {
        "temperature": 269.51,
        "temperatureMin": 268.71,
        "temperatureMax": 270.15,
        "pressure": 1019.0,
        "humidity": 68.0
    },
    "windDetail": {
        "speed": 6.17,
        "degree": 290.0
    }
}

```
### 4. Retrieve weather information using coordinate data

**Request**
```GET localhost:8080/api/v1/weather/location?lat=35&long=139 ```

**Response**
```
{
    "cityName": "Shuzenji",
    "country": "JP",
    "latitude": 35.0,
    "longitude": 139.0,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Clear",
            "description": "clear sky"
        }
    ],
    "weatherDetail": {
        "temperature": 283.15,
        "temperatureMin": 283.15,
        "temperatureMax": 283.15,
        "pressure": 1006.0,
        "humidity": 52.0
    },
    "windDetail": {
        "speed": 1.34,
        "degree": 271.0
    }
}

```
### 5. Retrieve weather information using zip code data

**Request**
```GET localhost:8080/api/v1/weather/zipCode?zipCode=85001 ```

**Response**
```
{
    "cityName": "Phoenix",
    "country": "US",
    "latitude": 33.4484,
    "longitude": -112.074,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Clear",
            "description": "clear sky"
        }
    ],
    "weatherDetail": {
        "temperature": 281.28,
        "temperatureMin": 280.15,
        "temperatureMax": 282.15,
        "pressure": 1017.0,
        "humidity": 34.0
    },
    "windDetail": {
        "speed": 3.6,
        "degree": 90.0
    }
}

```

### 6. Retrieve weather information using zip code and country code data

**Request**
```GET localhost:8080/api/v1/weather/zipCode?zipCode=X91&countryCode=IE ```

**Response**
```
{
    "cityName": "Waterford",
    "country": "IE",
    "latitude": 52.2583,
    "longitude": -7.1119,
    "visibility": 10000,
    "weatherDescription": [
        {
            "main": "Rain",
            "description": "moderate rain"
        }
    ],
    "weatherDetail": {
        "temperature": 283.72,
        "temperatureMin": 283.15,
        "temperatureMax": 284.26,
        "pressure": 982.0,
        "humidity": 98.0
    },
    "windDetail": {
        "speed": 8.05,
        "degree": 201.0
    }
}

```
