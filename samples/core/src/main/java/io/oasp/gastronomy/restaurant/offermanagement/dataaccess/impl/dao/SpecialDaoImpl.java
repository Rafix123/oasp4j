package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import static com.querydsl.core.alias.Alias.$;

import java.time.DayOfWeek;
import java.util.List;

import javax.inject.Named;

import com.querydsl.core.alias.Alias;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;

/**
 * @author RCZAJA
 *
 */
@Named
public class SpecialDaoImpl extends ApplicationMasterDataDaoImpl<SpecialEntity> implements SpecialDao {

  @Override
  public List<SpecialEntity> findActiveSpecials(SpecialSearchCriteriaTo criteria) {

    DayOfWeek currentDayOfWeek = criteria.getDateOfCheckingOffers().getDayOfWeek();
    int currentHour = criteria.getDateOfCheckingOffers().getHour();

    SpecialEntity special = Alias.alias(SpecialEntity.class);
    EntityPathBase<SpecialEntity> alias = $(special);
    JPAQuery<SpecialEntity> query = new JPAQuery<SpecialEntity>(getEntityManager()).from(alias);

    query.where($(special.getActivePeriod().getStartingDay()).loe(currentDayOfWeek));
    query.where($(special.getActivePeriod().getEndingDay()).goe(currentDayOfWeek));
    query.where($(special.getActivePeriod().getStartingHour()).loe(currentHour));
    query.where($(special.getActivePeriod().getEndingHour()).goe(currentHour));
    return query.fetch();
  }

  @Override
  protected Class<SpecialEntity> getEntityClass() {

    return SpecialEntity.class;
  }

}
