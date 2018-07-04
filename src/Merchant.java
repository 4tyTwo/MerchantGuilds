public class Merchant {

  private final BehaviorModel behaviour;
  private int balance;

  Merchant(BehaviorModel behaviorModel){
    behaviour = behaviorModel;
    balance = 0;
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

}
