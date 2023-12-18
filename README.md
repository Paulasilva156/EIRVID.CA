## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static Boolean startMenu(String userName, Scanner myObj) throws FileNotFoundException {
        System.out.println(
                "Welcome to the RTE Player \nyour EirVid stream service\nTo start, choose one of the options:\n[u] - Sign Up\n[i] - Sign In\n[c] - Change Password\n[l] - Leave ");
        // Scanner myObj = new Scanner(System.in);
        String start;
        start = myObj.nextLine();
        Boolean confirm = false;
        if (start.equals("i")) {
            // String userName;
            String password;
            System.out.println("Do your sign in");
            System.out.print("Enter username: ");
            userName = myObj.nextLine();
            System.out.print("Enter password: ");
            password = myObj.nextLine();

            File accountCsvFile = new File("C:\\\\Users\\\\Anderson\\\\Desktop\\\\accountsDemo.csv");
            Scanner accounts = new Scanner(accountCsvFile);
            List<String> values = new ArrayList<String>();
            while (accounts.hasNext()) {
                String data = accounts.next();
                values.add(data.split(",")[0]);
            }
            for (int i = 0; i < values.size(); i += 3) {
                if (values.get(i).equals(userName)) {
                    if (values.get(i + 1).equals(password)) {
                        confirm = true;
                    }
                }
            }
            accounts.close();
        }
        return confirm;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(
        // "Welcome to the RTE Player \nyour EirVid stream service\nTo start, choose one
        // of the options:\n[u] - Sign Up\n[i] - Sign In\n[c] - Change Password\n[l] -
        // Leave ");
        Scanner myObj = new Scanner(System.in);
        // String start;
        // start = myObj.nextLine();
        // if (start.equals("i")) {
        String userName = "";
        // String password;
        // System.out.println("Do your sign in");
        // System.out.print("Enter username: ");
        // userName = myObj.nextLine();
        // System.out.print("Enter password: ");
        // password = myObj.nextLine();
        // Boolean confirm = false;
        // File accountCsvFile = new
        // File("C:\\\\Users\\\\Anderson\\\\Desktop\\\\accountsDemo.csv");
        // Scanner accounts = new Scanner(accountCsvFile);
        // List<String> values = new ArrayList<String>();
        // while (accounts.hasNext()) {
        // String data = accounts.next();
        // values.add(data.split(",")[0]);
        // }
        // for (int i = 0; i < values.size(); i += 3) {
        // if (values.get(i).equals(userName)) {
        // if (values.get(i + 1).equals(password)) {
        // confirm = true;
        // }
        // }
        // }
        // accounts.close();
        Boolean confirm = startMenu(userName, myObj);
        if (confirm) {

            String choice;
            System.out.println("Welcome " + userName);
            Scanner sc = new Scanner(new File("C:\\Users\\Anderson\\Desktop\\csvDemo.csv"));
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                System.out.print(sc.next());
            }
            System.out.println();
            System.out.println("Our videos list");
            System.out.print("Choice the video you want to rent: ");
            int number = Integer.parseInt(myObj.nextLine());

            startMenu(userName, myObj);

            List<String> list = new ArrayList<String>();
            List<String> ids = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<String> prices = new ArrayList<String>();
            List<String> counts = new ArrayList<String>();

            File csvFile = new File("C:\\\\Users\\\\Anderson\\\\Desktop\\\\csvDemo.csv");
            Scanner sc2 = new Scanner(csvFile);
            while (sc2.hasNext()) {
                String data = sc2.next();
                list.add(data.split(",")[0]);
            }
            for (int i = 0; i < list.size(); i += 4) {
                ids.add(list.get(i));
            }
            for (int i = 1; i < list.size(); i += 4) {
                names.add(list.get(i));
            }
            for (int i = 2; i < list.size(); i += 4) {
                prices.add(list.get(i));
            }
            for (int i = 3; i < list.size(); i += 4) {
                counts.add(list.get(i));
            }
            // for (int i = 0; i < ids.size(); i++) {
            // if (ids.get(i).equals(Integer.toString(number))) {
            // System.out.print("The video you choose is " + names.get(i)
            // + ", and the price for rent the video for 1 min is " + prices.get(i)
            // + ". \nAre you sure that you want to rent this video? (y/n)\n");
            // choice = myObj.nextLine();

            // if (choice.equals("y")) {
            // System.out.println("Thanks for choising us! We going to descount " +
            // prices.get(i)
            // + " from your account, and you have 1 min to watch the video.");
            // FileWriter fileWriter = new FileWriter(csvFile);
            // for (int d = 0; d < ids.size(); d++) {
            // fileWriter.append(
            // ids.get(d) + ", " + names.get(d) + ", " + prices.get(d) + ", "
            // + ((d + 1 == number)
            // ? Integer.toString(Integer.parseInt(counts.get(d)) + 1)
            // : counts.get(d)));
            // fileWriter.append("\n");
            // }
            // fileWriter.flush();
            // fileWriter.close();

            // FileWriter fileWriter2 = new FileWriter(accountCsvFile);
            // List<String> usersNames = new ArrayList<String>();
            // List<String> passwords = new ArrayList<String>();
            // List<String> usersBankAccounts = new ArrayList<String>();
            // for (int f = 0; f < values.size(); f += 3) {
            // usersNames.add(values.get(f));
            // }
            // for (int f = 1; f < values.size(); f += 3) {
            // passwords.add(values.get(f));
            // }
            // for (int f = 2; f < values.size(); f += 3) {
            // usersBankAccounts.add(values.get(f));
            // }
            // for (int f = 0; f < usersNames.size(); f++) {
            // fileWriter2.append(usersNames.get(f) + ", " + passwords.get(f) + ", "
            // + ((number == f + 1) ? Double.toString(
            // Double.parseDouble(usersBankAccounts.get(f))
            // - Double.parseDouble(prices.get(number - 1)))
            // : usersBankAccounts.get(f)));
            // fileWriter2.append("\n");
            // }
            // fileWriter2.flush();
            // fileWriter2.close();
            // }
            // }
            // }

            sc2.close();
            sc.close();
        } else {
            System.out.println("Incorrect Information");
        }
        // } else if (start.equals("l")) {

        // }

        myObj.close();
    }

}
