import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Guild {
  private static  ArrayList<Merchant> merchants;

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
    randomizePositioning();
  }

  private void randomizePositioning(){
    for (int i = 59; i >= 0; --i){
      int j = new Random().nextInt(60);
      Collections.swap(merchants,i,j);
    }
  }


  public void tradeYear(){
    int tradesPerYear = 5 ;//+ new Random().nextInt(3); //[5,7]
    for (int i = 0; i < tradesPerYear; ++i){
      for (int j = 0; j < merchants.size(); ++j){
        for (int curr = j + 1; curr < merchants.size(); ++curr){
          merchants.get(j).makeDeal(merchants.get(curr));
        }
      }
    }
    Collections.sort(merchants);
  }

  public void printYearResults(){
    for (int i = 0; i < merchants.size(); ++i) {
      System.out.println("Торговец №" + String.valueOf(i+1) + ", Стратегия: " + merchants.get(i).getBehaviour().getBehaviorName() + ", заработок: " +
          String.valueOf(merchants.get(i).getBalance()));
    }
  }

  public static void main(String[] args){
    Guild guild = new Guild();
    for(int i = 0; i < 5; ++i) {
      guild.tradeYear();
      System.out.println("Год №" + String.valueOf(i+1));
      guild.printYearResults();
      guild.reshuffle(12);
    }
  }

  public void reshuffle(int num){
    kickWorstMerchants(num);
    resetBalances();
    hireMerchants(num);
    randomizePositioning();
  }

  private void kickWorstMerchants(int num){
    for (int i = 0; i < num; ++i){
      merchants.remove(merchants.size()-1);
    }
  }

  private void hireMerchants(int num){
    for (int i = 0; i < num; ++i){
      merchants.add(new Merchant(merchants.get(i)));
    }
  }

  private void resetBalances(){
    for (int i = 0; i < merchants.size(); ++i)
      merchants.get(i).setBalance(0);
  }
}
