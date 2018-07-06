import java.util.Random;

public abstract class BehaviorModel {
  protected String behaviorName;
  public static int CHEAT = -1;
  public static int FAIR = 1;

  public final String getBehaviorName() {
    return behaviorName;
  }

  public abstract int getDecision();

  public abstract void update(int partnerDecision);//Определяет правильно

  protected boolean mistake(){
    //определяет произойдет ли ошибка при сделке, стандартная вероятность - 5%
    return new Random().nextInt(20) == 0;
  }

  protected int reverseDesicion(int currentDesicion){
    //Домножение на -1 - это инверсия;
    return currentDesicion * -1;
  }

}
