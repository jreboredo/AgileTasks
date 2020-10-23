import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public notes: Object[] = [];
  public description: Text;
  public color: string;
  public showModalB: boolean = false;
  
  addNewNote(): void {
    this.notes.push({text: this.description, color:this.color});
    this.description = null;
    this.color = null;
  }

  showModal(): void{
    this.showModalB = true;
  }
}

