public class CheaterModel extends BehaviorModel {

  public CheaterModel(){
    behaviorName = "Кидала";
  }

  @Override
  public int getDecision() {
    int decision = CHEAT;
    if (mistake())
      decision = inverseDecision(decision);
    return decision;
  }

  @Override
  public void update(int partnerDecision){
    //Do nothing
  }
}