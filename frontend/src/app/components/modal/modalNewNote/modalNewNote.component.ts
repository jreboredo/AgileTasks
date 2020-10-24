import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: './modalNewNote.component.html'
})
export class NewNoteModal {
  public title: string;
  public text: string;
  public color: string;
  public notes: Object[];
  public addNewNotes: (notes) => void;


  constructor(public activeModal: NgbActiveModal) {}

  addNewNote(){
    const note = {id:this.notes.length + 1,title:this.title,text:this.text,color:this.color};
    this.notes.push(note);
    this.addNewNotes(this.notes)
  }

}
