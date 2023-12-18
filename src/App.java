import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
  static String pathComputer = "C:\\Users\\Anderson\\Desktop\\projects\\Git\\RTE\\";
  static String pathMovies = pathComputer + "MyApp\\files\\movies.csv";
  static String pathUsers = pathComputer + "MyApp\\files\\accounts.csv";
  static Format pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  static List<User> loadUsers(List<Movie> moviesList) throws ParseException {
    List<User> usersList = new ArrayList<User>();

    try (BufferedReader br = new BufferedReader(new FileReader(pathUsers))) {
      String line;
      int idx = 0;

      // ignore .csv title
      br.readLine();

      while ((line = br.readLine()) != null) {
        String sections[] = line.split(";");

        User newUser = new User();
        newUser.setId(idx);
        newUser.setEmail(sections[0]);
        newUser.setUserName(sections[1]);
        newUser.setPassword(sections[2]);

        Account newAccount = new Account();
        newAccount.setBudge(Float.parseFloat(sections[3]));

        String idsSt[] = {};
        if (sections[4].split(",").length > 0) {
          idsSt = sections[4].split(",");
        }
        for (int i = 0; i < idsSt.length; i++) {
          newAccount.setJustNewMovie(moviesList.get(Integer.parseInt(idsSt[i])));
        }
        String datesSt[] = {};
        if (sections[5].split(",").length > 0) {
          datesSt = sections[5].split(",");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate;
        for (int i = 0; i < datesSt.length; i++) {
          startDate = df.parse(datesSt[i]);
          newAccount.setLastStartDate(startDate);
        }

        newUser.setAccount(newAccount);
        usersList.add(newUser);

        idx++;
      }

    } catch (IOException e) {
      System.out.println("It was not possible to read the users file.");
    }

    return usersList;
  }

  static List<Movie> loadMovies() throws ParseException {
    List<Movie> moviesList = new ArrayList<Movie>();

    try (BufferedReader br = new BufferedReader(new FileReader(pathMovies))) {
      String line;
      int idx = 0;

      // ignore .csv title
      br.readLine();

      while ((line = br.readLine()) != null) {
        // ignore ';' case it is between '"'
        String sections[] = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        Movie newMovie = new Movie();
        newMovie.setId(idx);
        newMovie.setLanguage(sections[0]);
        newMovie.setOriginalTitle(sections[1]);
        newMovie.setOverview(sections[2]);
        newMovie.setPopularity(Float.parseFloat(sections[3].replaceAll(",", ".")));
        newMovie.setRealeseDate(new SimpleDateFormat("MM/dd/yyyy").parse(sections[4]));
        newMovie.setRuntime(Integer.parseInt(sections[5]));
        newMovie.setTagline(sections[6]);
        newMovie.setTitle(sections[7]);
        newMovie.setVoteAvarage(Float.parseFloat(sections[8].replaceAll(",", ".")));
        newMovie.setVoteCount(Integer.parseInt(sections[9]));
        newMovie.setPrice(Float.parseFloat(sections[10].replaceAll(",", ".")));
        moviesList.add(newMovie);

        idx++;
      }

    } catch (IOException e) {
      System.out.println("It was not possible to read the movies file.");
    }

    return moviesList;
  }

  public static String startMenu(Scanner input) {
    System.out.println("\n" +
        "To continue, choose one of the options:\n" +
        "[u] - Sign Up\n" +
        "[i] - Sign In\n" +
        "[c] - Change Password\n" +
        "[l] - Leave");

    String start;
    start = input.nextLine();
    return start;
  }

  public static String internMenu(Scanner input) {
    System.out.println("\n" +
        "Choose one of the options:\n" +
        "[a] - My Account\n" +
        "[m] - Movie List\n" +
        "[l] - Logout");

    String intern;
    intern = input.nextLine();
    return intern;
  }

  static public boolean emailValidate(String email) {
    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  static void signup(Scanner input, List<User> usersList) throws IOException {
    String email;
    String userName;
    String password;
    System.out.println("Do your Sign Up");
    Boolean confirm = false;
    System.out.print("Enter name: ");
    userName = input.nextLine();
    do {
      System.out.print("Enter email: ");
      email = input.nextLine();
      confirm = emailValidate(email);
      if (!confirm) {
        System.out.println("Enter with a valid email.");
      }
    } while (!confirm);
    System.out.print("Enter password: ");
    password = input.nextLine();
    FileWriter writerMovies = new FileWriter(pathUsers);
    writerMovies.append("email;name;password;account;moviesId;startDates");
    writerMovies.append("\n");

    for (int d = 0; d < usersList.size(); d++) {
      String userMovIds = "";
      for (int i = 0; i < usersList.get(d).getAccount().getListMovies().size(); i++) {
        userMovIds = userMovIds +
            Integer.toString(usersList.get(d).getAccount().getListMovies().get(i).getId())
            + (i < usersList.get(d).getAccount().getListMovies().size() - 1 ? "," : "");
      }
      String userStrDates = "";
      for (int i = 0; i < usersList.get(d).getAccount().getStartDates().size(); i++) {
        userStrDates = userStrDates +
            pattern.format(usersList.get(d).getAccount().getStartDates().get(i))
            + (i < usersList.get(d).getAccount().getListMovies().size() - 1 ? "," : "");
      }
      writerMovies.append(
          usersList.get(d).getEmail() + ";" + usersList.get(d).getUserName() + ";" + usersList.get(d).getPassword()
              + ";" + usersList.get(d).getAccount().getBudget() + ";" + userMovIds
              + ";" + userStrDates);
      writerMovies.append("\n");
    }
    writerMovies.append(
        email + ";" + userName + ";" + password + ";0;,;,");
    writerMovies.append("\n");
    writerMovies.flush();
    writerMovies.close();

  }

  static User login(Scanner input, List<User> usersList) {
    String email;
    String password;
    System.out.println("Do your Sign In");
    Boolean confirm = false;
    do {
      System.out.print("Enter email: ");
      email = input.nextLine();
      confirm = emailValidate(email);
      if (!confirm) {
        System.out.println("Enter with a valid email.");
      }
    } while (!confirm);
    System.out.print("Enter password: ");
    password = input.nextLine();

    for (int i = 0; i < usersList.size(); i++) {
      if (usersList.get(i).getEmail().equals(email)) {
        if (usersList.get(i).getPassword().equals(password)) {
          return usersList.get(i);
        }
      }
    }
    return null;
  }

  static void showMovies(List<Movie> moviesList) {
    System.out.println();
    System.out.printf("%-11s%-44s%-44s\n", "Id", "Title", "Price");
    for (int index = 0; index < 10; index++) { // moviesList.size()
      System.out.printf("%-11s%-44s%-44s\n", moviesList.get(index).getId(),
          moviesList.get(index).getTitle().length() > 35 ? moviesList.get(index).getTitle().substring(0, 35) + "..."
              : moviesList.get(index).getTitle(),
          moviesList.get(index).getPrice());
    }
  }

  static class MapValueKeyComparator<K extends Comparable<? super K>, V extends Comparable<? super V>>
      implements Comparator<Map.Entry<K, V>> {

    public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
      int cmp1 = b.getValue().compareTo(a.getValue());
      if (cmp1 != 0) {
        return cmp1;
      } else {
        return a.getKey().compareTo(b.getKey());
      }
    }
  }

  static void showLast5Movies(List<Movie> moviesList, List<User> usersList) {
    System.out.println("\nThe last 5 movies");
    System.out.printf("%-11s%-11s%-44s\n", "Times", "Id", "Title");
    class DataHelper {
      Integer id;
      Date date;

      DataHelper(Integer id, Date date) {
        this.id = id;
        this.date = date;
      }
    }

    List<Integer> ids = new ArrayList<Integer>();
    List<Date> dates = new ArrayList<Date>();
    List<DataHelper> combine = new ArrayList<DataHelper>();

    for (int i = 0; i < usersList.size(); i++) {
      for (int j = 0; j < usersList.get(i).getAccount().getListMovies().size(); j++) {
        combine.add(new DataHelper(usersList.get(i).getAccount().getListMovies().get(j).getId(),
            usersList.get(i).getAccount().getStartDates().get(j)));
      }
      for (int j = 0; j < usersList.get(i).getAccount().getListMovies().size(); j++) {
        ids.add(usersList.get(i).getAccount().getListMovies().get(j).getId());
      }
      for (int j = 0; j < usersList.get(i).getAccount().getStartDates().size(); j++) {
        dates.add(usersList.get(i).getAccount().getStartDates().get(j));
      }
    }

    Date now = new Date();
    Map<Integer, Integer> order = new HashMap<Integer, Integer>();
    for (int index = 0; index < combine.size(); index++) {
      if ((combine.get(index).date.getTime() - now.getTime()) > -(5 * 60 * 1000)) {
        order.put(combine.get(index).id, 0);
      }
    }
    for (int index = 0; index < combine.size(); index++) {
      if ((combine.get(index).date.getTime() - now.getTime()) > -(5 * 60 * 1000)) {
        order.put(combine.get(index).id, order.get(combine.get(index).id) + 1);
      }
    }
    List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(order.entrySet());
    Collections.sort(list, new MapValueKeyComparator<Integer, Integer>());

    int i = 0;
    for (Map.Entry<Integer, Integer> m : list) {
      if (i < 5) {
        System.out.printf("%-11s%-11s%-44s\n", m.getValue(),
            moviesList.get(m.getKey()).getId(),
            moviesList.get(m.getKey()).getTitle().length() > 35
                ? moviesList.get(m.getKey()).getTitle().substring(0, 35) +
                    "..."
                : moviesList.get(m.getKey()).getTitle());
        i++;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    List<Movie> moviesList = loadMovies();
    List<User> usersList = loadUsers(moviesList);

    Scanner input = new Scanner(System.in);

    System.out.println(
        "Welcome to the RTE Player \nyour EirVid stream service");
    String start;
    String intern;
    User currentUser = new User();
    do {
      start = startMenu(input);
      if (start.equals("u")) {
        signup(input, usersList);
      }
      if (start.equals("i")) {
        currentUser = login(input, usersList);
        if (currentUser != null) {
          System.out.println("\nWelcome " + currentUser.getUserName());
          intern = internMenu(input);
          do {
            if (intern.equals("a")) {
              System.out.println("\nMy Account");
              System.out.println("My Name: " + currentUser.getUserName());
              System.out.println("\nMy Budget: " + currentUser.getAccount().getBudget());
              System.out.println("\nMy History: ");
              System.out.printf("%-33s%-44s%-44s\n", "Rent Time", "Title", "Status");
              for (int index = 0; index < currentUser.getAccount().getListMovies().size(); index++) {
                System.out.printf("%-33s%-44s%-44s\n", currentUser.getAccount().getStartDates().get(index),
                    currentUser.getAccount().getListMovies().get(index).getTitle().length() > 35
                        ? currentUser.getAccount().getListMovies().get(index).getTitle().substring(0, 35) + "..."
                        : currentUser.getAccount().getListMovies().get(index).getTitle(),
                    currentUser.getAccount().getStatus().get(index));
              }
            }
            if (intern.equals("m")) {
              showLast5Movies(moviesList, usersList);
              showMovies(moviesList);
              int id;
              System.out.print("Choice the video you want to rent, write the id: ");
              id = Integer.parseInt(input.nextLine());
              currentUser.getAccount().setNewMovie(moviesList.get(id));
              for (int index = 0; index < moviesList.size(); index++) {
                if (id == moviesList.get(index).getId()) {
                  String choice;
                  System.out.println();
                  System.out.println("Title: " + moviesList.get(index).getTitle());
                  System.out.println("Overview: " + moviesList.get(index).getOverview());
                  System.out.println("Realese Date: " +
                      moviesList.get(index).getRealeseDate());
                  System.out.println("Votes: " + moviesList.get(index).getVoteAvarage());
                  System.out.println("Price: " + moviesList.get(index).getPrice());
                  System.out.println();
                  System.out.print("The video you choose is " +
                      moviesList.get(index).getTitle()
                      + ", and the price for rent the video for 1 min is " +
                      moviesList.get(index).getPrice()
                      + ". \nAre you sure that you want to rent this video? (y/n)\n");
                  choice = input.nextLine();
                  if (choice.equals("y")) {
                    FileWriter writerMovies = new FileWriter(pathUsers);
                    writerMovies.append("email;name;password;account;moviesId;startDates");
                    writerMovies.append("\n");
                    for (int d = 0; d < usersList.size(); d++) {
                      String userMovIds = "";
                      for (int i = 0; i < usersList.get(d).getAccount().getListMovies().size(); i++) {
                        userMovIds = userMovIds +
                            Integer.toString(usersList.get(d).getAccount().getListMovies().get(i).getId())
                            + (i < usersList.get(d).getAccount().getListMovies().size() - 1 ? "," : "");
                      }
                      String userStrDates = "";
                      for (int i = 0; i < usersList.get(d).getAccount().getStartDates().size(); i++) {
                        userStrDates = userStrDates +
                            pattern.format(usersList.get(d).getAccount().getStartDates().get(i))
                            + (i < usersList.get(d).getAccount().getListMovies().size() - 1 ? "," : "");
                      }
                      if (usersList.get(d).getId() == currentUser.getId()) {
                        writerMovies.append(
                            usersList.get(d).getEmail() + ";" + usersList.get(d).getUserName() + ";"
                                + usersList.get(d).getPassword() + ";"
                                + (new DecimalFormat("0.00").format(
                                    usersList.get(d).getAccount().getBudget() - moviesList.get(id).getPrice()))
                                    .replace(",", ".")
                                + ";"
                                + userMovIds + "," + ";" + userStrDates + ",");
                      } else {
                        writerMovies.append(
                            usersList.get(d).getEmail() + ";" + usersList.get(d).getUserName() + ";"
                                + usersList.get(d).getPassword() + ";"
                                + Float.toString(usersList.get(d).getAccount().getBudget())
                                + ";"
                                + userMovIds + "," + ";" + userStrDates + ",");
                      }
                      writerMovies.append("\n");
                    }
                    currentUser.getAccount()
                        .setBudge(Float.parseFloat(new DecimalFormat("#.##")
                            .format(currentUser.getAccount().getBudget() - moviesList.get(id).getPrice())
                            .replace(",", ".")));
                    writerMovies.flush();
                    writerMovies.close();
                  }
                  if (choice.equals("n")) {
                  }
                  break;
                }
              }

            }
            intern = internMenu(input);
          } while (!intern.equals("l"));
        } else {
          System.out.println("Inexistent User");
        }
      }
    } while (!start.equals("l"));
  }
}
