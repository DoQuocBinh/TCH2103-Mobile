import { IonContent, IonHeader , IonPage,  IonTitle, IonToolbar } from '@ionic/react';
const Settings: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Setting page</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
         <h2>Setting page!</h2>
      </IonContent>
    </IonPage>
  );
};
export default Settings;
