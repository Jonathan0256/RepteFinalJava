export default class Card{
  constructor(firstNum, secondNum){
    this.firstNum = firstNum;
    this.secondNum = secondNum;
  }

  constructor(numCaptura){
    this.numCaptura = numCaptura;
  }

  getFirstNum(){
    return firstNum;
  }

  setFirstNum(firstNum){
    this.firstNum = firstNum;
  }

  getSecondNum(){
    return secondNum;
  }

  setSecondNum(secondNum){
    this.secondNum = secondNum;
  }

  getNumCaptura(){
    return numCaptura;
  }

  setNumCaptura(numCaptura){
    this.numCaptura = numCaptura;
  }
}