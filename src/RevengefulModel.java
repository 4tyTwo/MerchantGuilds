public class RevengefulModel extends BehaviorModel {

  private int behavior;

  public RevengefulModel(){
    behaviorName = "Мстительный";
    behavior = FAIR;
  }

  @Override
  public int getDecision() {
    if (mistake())
      return reverseDesicion(behavior);
    return behavior;
  }

  @Override
  public void update(int partnerDecision) {
    if (partnerDecision == CHEAT)
      behavior = CHEAT;
  }
}
