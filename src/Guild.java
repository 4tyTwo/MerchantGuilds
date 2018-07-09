import java.util.*;

public class Guild {
  private static  ArrayList<Merchant> merchants;

  public Guild(){
    //Инициализация массива, на 70 торговцев т.к. я добавил 10 торговцев своей стратегии поведения
    merchants = new ArrayList<>(70);
    for (int i = 0; i < 70; i++){
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
      if (i >= 60 && i < 70)
        merchants.add(new Merchant(new StatisticianModel()));
    }
    randomizePositioning();
  }

  private void randomizePositioning(){
    //Производит случайную перестановку всех элементов массива, для чистоты симуляции
    for (int i = 59; i >= 0; --i){
      int j = new Random().nextInt(60);
      Collections.swap(merchants,i,j);
    }
  }


  public void tradeYear(){
    //Проводит "год" торгов, в рамках которого каждый торговец заключает 5-7 сделок с каждым другим
    int tradesPerYear = 5 + new Random().nextInt(3); //[5,7]
    for (int i = 0; i < tradesPerYear; ++i){
      for (int j = 0; j < merchants.size(); ++j){
        for (int curr = j + 1; curr < merchants.size(); ++curr){
          merchants.get(j).makeDeal(merchants.get(curr));
        }
      }
    }
    Collections.sort(merchants); //Сортировка по доходу за прошедший год
  }

  public void printYearResults(){
    //Вывод результатов за год
    for (int i = 0; i < merchants.size(); ++i) {
      System.out.println("Торговец №" + String.valueOf(i+1) + ", Стратегия: " + merchants.get(i).getBehaviour().getBehaviorName() + ", заработок: " +
          String.valueOf(merchants.get(i).getBalance()));
    }
  }

  public static void main(String[] args){
    Guild guild = new Guild();
    Scanner reader = new Scanner(System.in);
    System.out.print("Введите кол-во лет для симуляции: ");
    int years = 0;
    try {
      years = reader.nextInt();
    }
    catch (InputMismatchException e){
      System.out.println("Некорректное значение года, нажмите enter для завершения программы");
      reader.nextLine();
      reader.nextLine();
      System.exit(1);
    }
    for(int i = 0; i < years; ++i) {
      guild.tradeYear();
      System.out.println("Год №" + String.valueOf(i+1));
      guild.printYearResults();
      guild.reshuffle(merchants.size()/5); // 20% гильдии
    }
  }

  public void reshuffle(int num){
    //Удаляет num худших торговцев и нбирает новых, повторяющих num лучших торговцев
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
