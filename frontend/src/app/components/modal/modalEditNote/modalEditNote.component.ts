import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NoteComponent } from '../../note/note.component';

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: './modalEditNote.component.html'
})
export class Modal {
  @Input() note: NoteComponent;

  constructor(public activeModal: NgbActiveModal) {}

  updateNote(text:string,title:string){
    this.note.update(text,title)
  }

}
