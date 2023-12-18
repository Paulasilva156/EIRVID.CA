import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
  private float budget;
  private List<Movie> listMovies;
  private List<Date> startDates;
  private List<String> status;

  Account() {
    budget = 0;
    listMovies = new ArrayList<Movie>();
    startDates = new ArrayList<Date>();
    status = new ArrayList<String>();
  }

  public float getBudget() {
    return budget;
  }

  public void setBudge(float newBudget) {
    this.budget = newBudget;
  }

  public List<Movie> getListMovies() {
    return listMovies;
  }

  public void setJustNewMovie(Movie newMovie) {
    this.listMovies.add(newMovie);
    setLastStatus("Active");
  }

  public void setNewMovie(Movie newMovie) {
    this.listMovies.add(newMovie);
    setLastStartDate(new Date());
    setLastStatus("Active");
  }

  public Date getLastStartDate() {
    return startDates.get(startDates.size() - 1);
  }

  public List<Date> getStartDates() {
    return startDates;
  }

  public void setLastStartDate(Date newStartDate) {
    this.startDates.add(newStartDate);
  }

  public List<String> getStatus() {
    List<Date> currentDates = getStartDates();
    for (int index = 0; index < currentDates.size(); index++) {
      Date now = new Date();
      if (currentDates.get(index).getTime() - now.getTime() < -(1 * 60 * 1000)) {
        setPosStatus(index, "Expired");
      }
    }
    return status;
  }

  public void setPosStatus(int index, String newStatus) {
    this.status.set(index, newStatus);
  }

  public void setLastStatus(String newStatus) {
    this.status.add(newStatus);
  }
}