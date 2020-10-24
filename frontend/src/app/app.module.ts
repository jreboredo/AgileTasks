import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NoteComponent } from './components/note/note.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {Modal } from './components/modal/modalEditNote/modalEditNote.component';

@NgModule({
  declarations: [
    AppComponent,
    NoteComponent,
    Modal
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
