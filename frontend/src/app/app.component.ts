import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NewNoteModal } from './components/modal/modalNewNote/modalNewNote.component';
import { Notes } from './notes_mock';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public notes: Object[] = Notes;
  public noteTitle: string;
  public description: string;
  public color: string;
  
  constructor(private modalService: NgbModal){}

  addNewNotes(notes): void {
    this.notes = notes;
  }

  openModal(){
    const modalRef = this.modalService.open(NewNoteModal)
    modalRef.componentInstance.notes = this.notes;
    modalRef.componentInstance.addNote = this.addNewNotes
  }
}

