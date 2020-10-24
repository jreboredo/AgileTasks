import { Component, Input } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Modal } from '../modal/modalEditNote/modalEditNote.component';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})

export class NoteComponent{
  @Input() note: Object;
  @Input() public id;
  @Input() public title;
  @Input() public text: string
  @Input() public color: string;

  constructor(private modalService: NgbModal) {};

  open(){
    const modalRef = this.modalService.open(Modal)
    modalRef.componentInstance.note = this
  }

  update(text: string,title:string) {
    this.title = title;
    this.text = text;
    
  }
}

