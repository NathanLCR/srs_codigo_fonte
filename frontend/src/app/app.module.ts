import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from "./app-routing.module";
import { NgModule } from "@angular/core";
import { SharedModule } from "./shared/shared.module";
import { LocationStrategy, HashLocationStrategy, CommonModule,} from "@angular/common";
import { HttpClientModule } from "@angular/common/http";

import { EquipamentoComponent } from "./equipamento/equipamento.component";
import { ReservasComponent } from "./reserva/reservas.component";
import { ClienteComponent } from "./cliente/cliente.component";
import { SalaComponent } from "./sala/sala.component";
import { AppComponent } from "./app.component";
import { AppTopbarComponent } from "./components/topbar/app.topbar.component";
import { AppFooterComponent } from "./components/footer/app.footer.component";
import { environment } from "../environments/environment";
import {
    PageNotificationModule,
    BreadcrumbModule,
    MenuModule,
    ErrorStackModule,
} from "@nuvem/primeng-components";
import { SecurityModule, VersionTagModule } from "@nuvem/angular-base";
import { DiarioErrosComponent } from "./components/diario-erros/diario-erros.component";
import { BlockUIModule } from "ng-block-ui";
import { ToolbarComponent } from "./components/toolbar/toolbar.component";
import { ButtonModule } from "primeng";
import { CPFPipe } from './pipes/cpf.pipe';
import { RGPipe } from './pipes/rg.pipe';
import { TelefonePipe } from './pipes/telefone.pipe';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
    declarations: [
        AppComponent,
        AppTopbarComponent,
        AppFooterComponent,
        DiarioErrosComponent,
        ClienteComponent,
        EquipamentoComponent,
        ClienteComponent,
        SalaComponent,
        ReservasComponent,
        ToolbarComponent,
        CPFPipe,
        RGPipe,
        TelefonePipe,
        DashboardComponent,
    ],
    imports: [
        BlockUIModule.forRoot({
            message: "Carregando...",
        }),
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        SharedModule,
        HttpClientModule,
        PageNotificationModule,
        BreadcrumbModule,
        ErrorStackModule,
        VersionTagModule,
        SecurityModule.forRoot(environment.auth),
        MenuModule,
        FormsModule,
        ReactiveFormsModule,
        ButtonModule,
        CommonModule,
    ],
    providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }],
    bootstrap: [AppComponent],
})
export class AppModule { }
