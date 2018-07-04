import java.util.Random;

public class UnpredictableModel extends BehaviorModel{

  public UnpredictableModel(){
    behaviorName = "Непредсказуемый";
  }

  @Override
  public int getDecision() {
    int decision = CHEAT;
    if (new Random().nextBoolean())
      decision = FAIR;
    if (mistake())
      decision = reverseDesicion(decision);
    return decision;
  }

  @Override
  public void update(int partnerDecision) {
    //Do nothing
  }
}