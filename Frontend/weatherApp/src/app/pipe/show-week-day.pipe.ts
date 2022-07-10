import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'showWeekDay'
})
export class ShowWeekDayPipe implements PipeTransform {

  days:string[] = ['SUNDAY','MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FIRDAY','SATURDAY']

  transform(value : number): string {

    return this.days[value];
  }
}
