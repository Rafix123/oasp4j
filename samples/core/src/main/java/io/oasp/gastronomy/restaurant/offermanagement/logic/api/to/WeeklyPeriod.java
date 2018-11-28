package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import java.time.DayOfWeek;

/**
 * @author RCZAJA
 *
 */
public class WeeklyPeriod {
  private DayOfWeek startingDay;

  private int startingHour;

  private DayOfWeek endingDay;

  private int endingHour;

  /**
   * @return startingDay
   */
  public DayOfWeek getStartingDay() {

    return this.startingDay;
  }

  /**
   * @param startingDay new value of {@link #getstartingDay}.
   */
  public void setStartingDay(DayOfWeek startingDay) {

    this.startingDay = startingDay;
  }

  /**
   * @return startingHour
   */
  public int getStartingHour() {

    return this.startingHour;
  }

  /**
   * @param startingHour new value of {@link #getstartingHour}.
   */
  public void setStartingHour(int startingHour) {

    this.startingHour = startingHour;
  }

  /**
   * @return endingDay
   */
  public DayOfWeek getEndingDay() {

    return this.endingDay;
  }

  /**
   * @param endingDay new value of {@link #getendingDay}.
   */
  public void setEndingDay(DayOfWeek endingDay) {

    this.endingDay = endingDay;
  }

  /**
   * @return endingHour
   */
  public int getEndingHour() {

    return this.endingHour;
  }

  /**
   * @param endingHour new value of {@link #getendingHour}.
   */
  public void setEndingHour(int endingHour) {

    this.endingHour = endingHour;
  }

}
