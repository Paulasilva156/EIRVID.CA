import java.util.Date;

public class Movie {
  private int id;
  private String language;
  private String originalTitle;
  private String overview;
  private float popularity;
  private Date realeseDate;
  private int runtime;
  private String tagline;
  private String title;
  private float voteAvarage;
  private int voteCount;
  private float price;

  public int getId() {
    return id;
  }

  public void setId(int newId) {
    this.id = newId;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String newLanguage) {
    this.language = newLanguage;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String newOriginalTitle) {
    this.originalTitle = newOriginalTitle;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String newOverview) {
    this.overview = newOverview;
  }

  public float getPopularity() {
    return popularity;
  }

  public void setPopularity(float newPopularity) {
    this.popularity = newPopularity;
  }

  public Date getRealeseDate() {
    return realeseDate;
  }

  public void setRealeseDate(Date newRealeseDate) {
    this.realeseDate = newRealeseDate;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(int newRuntime) {
    this.runtime = newRuntime;
  }

  public String getTagline() {
    return tagline;
  }

  public void setTagline(String newTagline) {
    this.tagline = newTagline;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String newTitle) {
    this.title = newTitle;
  }

  public float getVoteAvarage() {
    return voteAvarage;
  }

  public void setVoteAvarage(float newVoteAvarage) {
    this.voteAvarage = newVoteAvarage;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int newVoteCount) {
    this.voteCount = newVoteCount;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float newPrice) {
    this.price = newPrice;
  }
}
