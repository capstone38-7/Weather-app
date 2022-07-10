import { Coordinate } from "../Coordinate";
import { Temperature } from "../Temperature";
import { Weather } from "../Weather";


export class CityWeather {

    cityId: number;
    name: string;
    coord: Coordinate;
    weather: Weather;
    temperature: Temperature;
    visibility:number;
    country:string

}
