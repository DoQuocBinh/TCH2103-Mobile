import { IonButton, IonContent, IonDatetime, IonHeader, IonInput, IonItem, IonLabel, IonList, IonPage, IonPopover, IonRouterLink, IonTitle, IonToolbar } from '@ionic/react';
import { useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import './Home.css';

interface Product{
  name : string,
  productionDate: string | undefined
}

const Home: React.FC = () => {
  const [name,setName] = useState('')
  const [productionDate,setProductionDate] = useState<string>()
  const [productList,setProductList] = useState<Product[]>([])
  
  const setDateToInput = (e:any) =>{
    const mydate = new Date(e.detail.value)
    setProductionDate(mydate.toLocaleDateString("en-GB"))
  }
  const saveHandler = () =>{
    const newProduct: Product = {'name':name,'productionDate' : productionDate}
    //copy all elements from productList to copyList
    const copyList = [...productList]
    //append the copyList with newProduct
    copyList.push(newProduct)
    //replace the productList with copyList
    setProductList(copyList)
    console.log(productList)

  }
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Product Management</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
          <IonItem>
            <IonLabel position='floating'>Name</IonLabel>
            <IonInput onIonChange={e => setName(e.detail.value!)}></IonInput>
          </IonItem>
          <IonItem>
            <IonLabel position='floating'>Production date</IonLabel>
            <IonInput id='mydatepicker' value={productionDate}></IonInput>
            <IonPopover keepContentsMounted={true} trigger='mydatepicker' triggerAction='click'>
                <IonContent>
                  <IonDatetime onIonChange={e=>setDateToInput(e)}></IonDatetime>
                </IonContent>
            </IonPopover>
          </IonItem>
          <IonButton expand='block' onClick={saveHandler} className='ion-margin'>Save</IonButton>
      {productList &&
        <IonList>
          {productList.map(c =>
            <IonItem key={c.name}>{c.name} - {c.productionDate}</IonItem>
          )}
        </IonList>
      }
      <IonRouterLink routerLink='/settings'>Settings page</IonRouterLink>
      </IonContent>
    </IonPage>
  );
};

export default Home;
