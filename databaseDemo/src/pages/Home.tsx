import { IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonPage, IonSelect, IonSelectOption, IonTitle, IonToolbar } from '@ionic/react';
import { useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import { insertCustomer } from '../databaseHandler';
import { Customer } from '../models/Customer';
import './Home.css';

const Home: React.FC = () => {
  const [name, setName] = useState('')
  const [languages, setLanguages] = useState<string[]>([])
  const [picture, setPicture] = useState('')
  const [allCustomers, setAllCustomers] = useState<Customer[]>([]);

  const saveHandler = async () =>{
    const newCus : Customer = {'name':name,'languages':languages,'picture':picture}
     await insertCustomer(newCus)
     alert('Insert done!')
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="warning">
          <IonTitle>Manage Customer</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel position='floating'>Name</IonLabel>
          <IonInput onIonChange={e=>setName(e.detail.value!)}></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel position='floating'>Country</IonLabel>
          <IonSelect multiple onIonChange={e=>setLanguages(e.detail.value)}>
            <IonSelectOption value="The UK">The UK</IonSelectOption>
            <IonSelectOption value="Vietnam">Vietnam</IonSelectOption>
            <IonSelectOption value="Spain">Spain</IonSelectOption>
          </IonSelect>
        </IonItem>
        <IonItem>
          <IonLabel position='floating'>Picture</IonLabel>
          <IonInput onIonChange={e=>setPicture(e.detail.value!)}></IonInput>
        </IonItem>
        <IonButton onClick={saveHandler} expand='block' class='ion-margin'>Save</IonButton>
      
      </IonContent>
    </IonPage>
  );
};

export default Home;
