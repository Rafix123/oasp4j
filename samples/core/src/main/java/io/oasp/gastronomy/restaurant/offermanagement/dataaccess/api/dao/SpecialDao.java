package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author RCZAJA
 *
 */
public interface SpecialDao extends MasterDataDao<SpecialEntity> {

  List<SpecialEntity> findActiveSpecials(SpecialSearchCriteriaTo criteria);

  Money findBestActiveSpecial(SpecialSearchCriteriaTo criteria);
}
