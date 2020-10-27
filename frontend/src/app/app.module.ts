import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { EquipamentoComponent } from "./equipamento/equipamento.component";
import { ReservasComponent } from "./reserva/reservas.component";
import { ClienteComponent } from "./cliente/cliente.component";
import { SalaComponent } from "./sala/sala.component";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { SharedModule } from "./shared/shared.module";
import { AppTopbarComponent } from "./components/topbar/app.topbar.component";
import { AppFooterComponent } from "./components/footer/app.footer.component";
import {
    LocationStrategy,
    HashLocationStrategy,
    CommonModule,
} from "@angular/common";
import { environment } from "../environments/environment";
import { HttpClientModule } from "@angular/common/http";
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
