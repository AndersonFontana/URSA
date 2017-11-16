import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

// Importa módulo Push para a página
import { Push, PushObject, PushOptions } from '@ionic-native/push';

import { DetailsPage } from '../details/details';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

	constructor(public navCtrl: NavController, private push: Push) {

	  	// Checar se tem permissão para enviar push
		this.push.hasPermission().then((res: any) => {

		    if (res.isEnabled) {
		    	console.log('Tem permissão');

		    	// Inicializar push notifications

				const options: PushOptions = {
					android: {
						topics: ['ursapp']
					},
					ios: {
				    	alert: 'true',
				    	badge: true,
				    	sound: 'false',
				    	topics: ['ursapp']
					},
					windows: {},
					browser: {
				    	pushServiceURL: 'http://push.api.phonegap.com/v1/push'
					}
				};

				const pushObject: PushObject = this.push.init(options);

				// Este vai receber a mensagem da notificação
				pushObject.on('notification').subscribe((notification: any) =>
				{
					alert(notification.message);

					let additionalData = JSON.parse(JSON.stringify(notification.additionalData));

					this.navCtrl.push(DetailsPage, {
					    'title': notification.title,
					    'message': notification.message,
					    'content': additionalData.content
					});
				});

				// Este vai registrar no sistema de notificação e exibe o token do dispositivo
				pushObject.on('registration').subscribe((registration: any) =>
				{
					console.log('Token dispositivo: ', registration.registrationId);
				});

				// Este vai verificar se existe algum erro na mensagem
				pushObject.on('error').subscribe(error => console.error('Error with Push plugin', error));

		    } else {
		    	console.log('Não tem permissão');
		    }

	  	});

	}

}
