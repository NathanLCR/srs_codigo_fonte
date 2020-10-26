import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'RG'
})
export class RGPipe implements PipeTransform {
  transform(value: string, ...args: any[]): any {
    if (value.length === 7) {
      return value.replace(/(\d{1})(\d{3})(\d{3})/g, '\$1.\$2.\$3');
    }
    return 'error';
  }
}