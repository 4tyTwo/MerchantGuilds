public class StatisticianModel extends BehaviorModel {

  /*
  Модель поведения статистик опирается на данные, собранные в процессе торговли
  Статистик запоминает результаты всех сделок и основываясь на них вычисляет долю сделок, в которых его не обманули
  Исходя из этой доли, он вычисляет ожидаемый доход в зависимости от того, будет ли он сотрудничать или обманывать и принимает свое решение в пользу наиболее выгодного варианта
  Также статистик не очень доверчивый, и поэтому после каждой сделки закладывает небольшое значение, что его обманут в следующий раз.
  Это помогает модели быстрее приспособится к переходу в ситуацию, когда честные торговцы отсеиваются и остаются преимущественно кидалы, хитрецы и ушлые.
   */


  private float timesCheated, timesCooperated;

  StatisticianModel(){
    behaviorName = "Статистик";
    timesCheated = 0;
    timesCooperated = 0;
  }

  @Override
  public int getDecision() {
    int decision;
    float valueOfCheating, valueOfCooperating, coopProbability = getCooperationProbability();
    valueOfCheating = (coopProbability * 5) + ((1-coopProbability) * 2);
    valueOfCooperating = (coopProbability * 4) + ((1-coopProbability) * 1);
    if (valueOfCheating > valueOfCooperating)
      decision =  -1;
    else
      decision = 1;
    if (mistake())
      decision = inverseDecision(decision);
    return decision;
  }

  @Override
  public void update(int partnerDecision) {
    if (partnerDecision == CHEAT)
      timesCheated++;
    else
      timesCooperated++;
    timesCheated +=0.04; // Поправка на то, что со временем будет меньше честных;
  }

  private float getCooperationProbability(){
    if (timesCheated !=0)
      return timesCooperated /(timesCooperated + timesCheated);
    return 0.0f;
  }
}
