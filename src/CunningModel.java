public class CunningModel extends BehaviorModel {
  //Начинает с сотрудничества, потом повторяет ход оппонента.
  //Условие трактуется как то, что он повторяет действие последнего торговца, с которым торговал
  //Последовательность принятых решений: сотрудничество, решение торговца №1, решение торговца №2 и т.д.
  private int previousPartnerDecision; // 0 по умолчанию

  public CunningModel(){
    behaviorName = "Хитрец";
    previousPartnerDecision = 0;
  }

  @Override
  public int getDecision(){
    int decision = previousPartnerDecision;
    if (previousPartnerDecision == 0)
        decision = FAIR;
    if (mistake())
      decision = reverseDesicion(decision);
    return  decision;
  }

  @Override
  public void update(int partnerDecision) {
    //Запоминает выбор последнего партнера
    if (previousPartnerDecision != 0)
      previousPartnerDecision = partnerDecision;
  }
}
