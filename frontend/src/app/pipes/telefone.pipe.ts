import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'Telefone'
})
export class TelefonePipe implements PipeTransform {
  transform(value: string, ...args: any[]): any {
    if (value.length === 11) {
      return value.replace(/(\d{2})(\d{1})(\d{4})(\d{4})/g, '(\$1) \$2.\$3-\$4');
    }
    return 'error';
  }
}
