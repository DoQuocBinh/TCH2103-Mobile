const btnCalculate = document.getElementById('btn-calculate')
const btnClear = document.getElementById('btn-clear')
const inputHeight = document.getElementById('input-height')
const inputWeight = document.getElementById('input-weight')
const resultElement = document.getElementById('result')

const calculateClickHandler = ()=>{
    //calculate the BMI : 
    const result = `
        <ion-card-content>
            <h2>Your BMI</h2>
            ${+inputWeight.value / (inputHeight.value * inputHeight.value)}
        </ion-card-content>
    `
    //Create the element ion-card and it's content
    const ionCard = document.createElement("ion-card")
    ionCard.innerHTML = result

    //append ion-card to the div result
    resultElement.innerHTML = ''
    resultElement.appendChild(ionCard)
}

btnCalculate.addEventListener("click",calculateClickHandler)