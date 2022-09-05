import { IonBackButton, IonButton, IonButtons, IonContent, IonHeader, IonIcon, IonImg, IonInput, IonItem, IonLabel, IonList, IonPage, IonSelect, IonSelectOption, IonThumbnail, IonTitle, IonToolbar } from '@ionic/react';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { getCustomerById } from '../databaseHandler';
import { Customer } from '../models/Customer';
import './Home.css';

import {trash} from 'ionicons/icons'

interface IdParam {
  id: string
}

const Detail: React.FC = () => {
  const [name, setName] = useState('')
  const [languages, setLanguages] = useState<string[]>([])
  const [picture, setPicture] = useState('')
  const [allCustomers, setAllCustomers] = useState<Customer[]>([]);
  const { id } = useParams<IdParam>()

  const fetchDataFromDB = async()=>{
    const cus = await getCustomerById(Number.parseInt(id)) as Customer
    setName(cus.name)
    setLanguages(cus.languages)
    setPicture(cus.picture)
  }
  useEffect(()=>{
    fetchDataFromDB()
  })
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="warning">
        <IonButtons slot="start">
            <IonBackButton />
          </IonButtons>
          <IonButton color="danger" slot="end">
            <IonIcon slot="icon-only" icon={trash}></IonIcon>
          </IonButton>
          <IonTitle>Customer Details</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
      <IonItem>
          <IonLabel position="floating">Name</IonLabel>
          <IonInput value={name}></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel position="floating">Languages can speak</IonLabel>
          <IonSelect value={languages} multiple>
            <IonSelectOption value="English">English</IonSelectOption>
            <IonSelectOption value="Vietnamese">Vietnamese</IonSelectOption>
            <IonSelectOption value="Spanish">Spanish</IonSelectOption>
          </IonSelect>
        </IonItem>
        <IonItem>
          <IonLabel>Picture</IonLabel>
          <IonThumbnail>
            <IonImg src={picture} />
          </IonThumbnail>
        </IonItem>
      </IonContent>
    </IonPage>
  );
};
export default Detail;
