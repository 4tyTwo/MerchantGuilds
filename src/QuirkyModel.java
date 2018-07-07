public class QuirkyModel extends BehaviorModel {
  private static final int[] firstDecisions = {FAIR,CHEAT,FAIR,FAIR};
  private boolean gotCheated;
  private int currentDealNum;
  private BehaviorModel newBehaviour;

  public  QuirkyModel(){
    behaviorName = "Ушлый";
    gotCheated = false;
    currentDealNum = 0;
  }

  @Override
  public int getDecision() {
    int decision;
    if (currentDealNum < firstDecisions.length)
      decision = firstDecisions[currentDealNum++];
    else
      return newBehaviour.getDecision(); //Ошибка уже заложена
    if (mistake())
      decision = inverseDecision(decision);
    return decision;
  }

  @Override
  public void update(int partnerDecision) {
    if (currentDealNum < firstDecisions.length) {
      if (partnerDecision == CHEAT)
        gotCheated = true;
    }
    else {
      if (currentDealNum == firstDecisions.length && newBehaviour == null)
        setNewDecisionModel(); //По завершении 4ой сделки надо выбрать новую модель поведения
      else
        newBehaviour.update(partnerDecision); //После 4ой сделки руководствуется уже новой моделью
    }
  }

  private void setNewDecisionModel(){
    if (gotCheated)
      newBehaviour = new CheaterModel();
    else
      newBehaviour = new CunningModel();
  }
}
