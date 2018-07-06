public class Merchant implements Comparable<Merchant> {

  private BehaviorModel behaviour;
  private int balance;

  Merchant(BehaviorModel behaviorModel){
    behaviour = behaviorModel;
    balance = 0;
  }

  Merchant(Merchant copied){
    balance = copied.balance;
    switch (copied.behaviour.behaviorName){
      case "Альтруист": behaviour = new AltruistModel();
        return;
      case "Кидала": behaviour = new CheaterModel();
        return;
      case "Хитрец": behaviour = new CunningModel();
        return;
      case "Непредсказуемый": behaviour = new UnpredictableModel();
        return;
      case "Мстительный": behaviour = new RevengefulModel();
        return;
      case "Ушлый": behaviour = new CunningModel();
        return;
    }
  }

  public BehaviorModel getBehaviour() {
    return behaviour;
  }

  public void makeDeal(Merchant partner){
    int firstDecision = this.getBehaviour().getDecision() , secondDecision = partner.getBehaviour().getDecision() ;
    if (firstDecision ==secondDecision){
      //Либо оба смошенничали, либо оба честны
      if (firstDecision == BehaviorModel.FAIR){
          this.addProfit(4);
          partner.addProfit(4);
      }
      else{
        this.addProfit(2);
        partner.addProfit(2);
      }
    }
    else{
      if (firstDecision == BehaviorModel.FAIR){
        this.addProfit(1);
        partner.addProfit(5);
      }
      else{
        this.addProfit(5);
        partner.addProfit(1);
      }
    }
    this.getBehaviour().update(secondDecision);
    partner.getBehaviour().update(firstDecision);
  }

  public int getBalance() {
    return balance;
  }

  public void addProfit(int profit){
    balance += profit;
  }

  public void setBalance(int balance){
    this.balance = balance;
  }

  public int compareTo(Merchant b){
    return b.getBalance() - this.balance;
  }

}
