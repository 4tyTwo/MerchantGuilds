import java.util.Random;

public abstract class BehaviorModel {
  protected String behaviorName;
  public final static int CHEAT = -1;
  public final static int FAIR = 1;

  public final String getBehaviorName() {
    return behaviorName;
  }

  public abstract int getDecision();

  public abstract void update(int partnerDecision);//Обновляет модель поведения

  protected boolean mistake(){
    //определяет произойдет ли ошибка при сделке, стандартная вероятность - 5%
    return new Random().nextInt(20) == 0;
  }

  protected int inverseDecision(int currentDecision){
    //Инверсирует решение в случае ошибок
    //Домножение на -1 - это инверсия;
    return currentDecision * -1;
  }

}
