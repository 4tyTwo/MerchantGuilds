public class MyModel extends BehaviorModel {

  private float cheat, fair;

  MyModel(){
    behaviorName = "Мое поведение";
    cheat = 0;
    fair = 0;
  }

  @Override
  public int getDecision() {
    int decision;
    float valueOfCheating, valueOfCooperating, fairProb = fairChance();

    valueOfCheating = (fairProb * 5) + ((1-fairProb) * 2)/2;
    valueOfCooperating = (fairProb * 4) + ((1-fairProb) *1 )/2;

    if (valueOfCheating > valueOfCooperating)
      decision =  -1;
    else
      decision = 1;
    if (mistake())
      decision = reverseDesicion(decision);
    return decision;
  }

  @Override
  public void update(int partnerDecision) {
    if (partnerDecision == CHEAT)
      cheat++;
    else
      fair++;
    cheat+=0.04; // Коэффициент того, что дальше будет меньше честных;
  }

  private float fairChance(){
    if (cheat!=0)
      return fair/(fair+cheat);
    return 0.0f;
  }
}
