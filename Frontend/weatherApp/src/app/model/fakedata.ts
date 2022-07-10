import { CityWeather } from "./cityWeather/CityWeather"

export const result = {
    "coord": {
        "lon": 78.4744,
        "lat": 17.3753
    },
    "weather": [
        {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 309.38,
        "feels_like": 310.78,
        "temp_min": 309.38,
        "temp_max": 310.88,
        "pressure": 1006,
        "humidity": 34
    },
    "visibility": 6000,
    "wind": {
        "speed": 4.12,
        "deg": 260
    },
    "clouds": {
        "all": 40
    },
    "dt": 1654606866,
    "sys": {
        "type": 1,
        "id": 9214,
        "country": "IN",
        "sunrise": 1654560666,
        "sunset": 1654607938
    },
    "timezone": 19800,
    "id": 1269843,
    "name": "Hyderabad",
    "cod": 200
}

export const citiesWeather:CityWeather[] = [
    {"cityId":1269843,"name":"Pune","temperature":{"feelsLike":37,"humidity":34,"pressure":1006,"tempMax":37,"tempMin":36,"temp":36},"coord":{"longitude":78.4744,"latitude":17.3753},"visibility":6000,"weather":{"description":"scattered clouds","mainInfo":"Clouds"},"country":"IN"},
    {"cityId":1269843,"name":"Hyderabad","temperature":{"feelsLike":37,"humidity":34,"pressure":1006,"tempMax":37,"tempMin":36,"temp":36},"coord":{"longitude":78.4744,"latitude":17.3753},"visibility":6000,"weather":{"description":"scattered clouds","mainInfo":"Clouds"},"country":"IN"}

]

export const baseServerUrl = "http://localhost:8580";

