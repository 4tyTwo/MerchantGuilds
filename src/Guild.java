import java.util.ArrayList;
import java.util.Random;

public class Guild {
  public static  ArrayList<Merchant> merchants;

  public Guild(){
    merchants = new ArrayList<>(60);
    for (int i = 0; i < 60; i++){
      if (i < 10)
        merchants.add(new Merchant(new AltruistModel()));
      if (i >= 10 && i < 20)
        merchants.add(new Merchant(new CheaterModel()));
      if (i >= 20 && i < 30)
        merchants.add(new Merchant(new CunningModel()));
      if (i >= 30 && i < 40)
        merchants.add(new Merchant(new UnpredictableModel()));
      if (i >= 40 && i < 50)
        merchants.add(new Merchant(new RevengefulModel()));
      if (i >= 50 && i < 60)
        merchants.add(new Merchant(new QuirkyModel()));
    }
  }

  public void tradeYear(){
    int tradesPerYear = 5 + new Random().nextInt(3); //[5,7]
    for (int i = 0; i < tradesPerYear; ++i){
      for (int j = 0; j < merchants.size(); ++j){
        for (int curr = j + 1; curr < merchants.size(); ++curr){
          merchants.get(j).makeDeal(merchants.get(curr));
        }
      }
    }
  }

  public void printYearResults(){
    for (int i = 0; i < merchants.size(); ++i) {
      System.out.println("Торговец №" + String.valueOf(i+1) + ", Стратегия: " + merchants.get(i).getBehaviour().getBehaviorName() + ", заработок: " +
          String.valueOf(merchants.get(i).getBalance()));
    }
  }

  public static void main(String[] args){
    Guild guild = new Guild();
    guild.tradeYear();
    guild.printYearResults();
  }
}
