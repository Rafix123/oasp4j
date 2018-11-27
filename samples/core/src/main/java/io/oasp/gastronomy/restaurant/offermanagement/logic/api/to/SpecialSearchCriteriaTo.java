package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import java.time.LocalDateTime;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

/**
 * @author RCZAJA
 *
 */
public class SpecialSearchCriteriaTo extends SearchCriteriaTo {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String name;

  private Long offerNumber;

  private Money specialPrice;

  private OfferEntity offer;

  private LocalDateTime dateOfCheckingOffers;

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return offerNumber
   */
  public Long getOfferNumber() {

    return this.offerNumber;
  }

  /**
   * @param offerNumber new value of {@link #getofferNumber}.
   */
  public void setOfferNumber(Long offerNumber) {

    this.offerNumber = offerNumber;
  }

  /**
   * @return specialPrice
   */
  public Money getSpecialPrice() {

    return this.specialPrice;
  }

  /**
   * @param specialPrice new value of {@link #getspecialPrice}.
   */
  public void setSpecialPrice(Money specialPrice) {

    this.specialPrice = specialPrice;
  }

  /**
   * @return offer
   */
  public OfferEntity getOffer() {

    return this.offer;
  }

  /**
   * @param offer new value of {@link #getoffer}.
   */
  public void setOffer(OfferEntity offer) {

    this.offer = offer;
  }

  /**
   * @return dateOfCheckingOffer
   */
  public LocalDateTime getDateOfCheckingOffers() {

    return this.dateOfCheckingOffers;
  }

  /**
   * @param dateOfCheckingOffer new value of {@link #getdateOfCheckingOffer}.
   */
  public void setDateOfCheckingOffers(LocalDateTime dateOfCheckingOffers) {

    this.dateOfCheckingOffers = dateOfCheckingOffers;
  }

  /**
   * @return serialversionuid
   */
  public static long getSerialversionuid() {

    return serialVersionUID;
  }

}
