import { IonButton, IonContent, IonHeader, IonIcon, IonInput, IonItem, IonLabel, IonPage, IonTitle, IonToolbar } from '@ionic/react';

import {calculator,refreshCircleOutline} from 'ionicons/icons'

import './Home.css';

const Home: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color='primary'> 
          <IonTitle>BMI Calculator</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel position='floating'>Weight</IonLabel>
          <IonInput></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel position='floating'>Height</IonLabel>
          <IonInput></IonInput>
        </IonItem>
        <IonButton className='ion-margin'>
          <IonIcon icon= {calculator} slot="start"></IonIcon>
          Calculate</IonButton>
      </IonContent>
    </IonPage>
  );
};

export default Home;
