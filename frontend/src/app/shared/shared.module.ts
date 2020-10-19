import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { ReservaFormComponent } from './reserva/reserva-form/reserva-form.component';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
    ],
    declarations: [ReservaFormComponent]
})
export class SharedModule { }
