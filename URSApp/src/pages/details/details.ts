import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-details',
  templateUrl: 'details.html',
})
export class DetailsPage {

  title: string;
  message: string;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.title = this.navParams.get('title');
    this.message = this.navParams.get('message');
  }

}
