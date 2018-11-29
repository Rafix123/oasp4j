package io.oasp.gastronomy.restaurant.staffmanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.oasp.gastronomy.restaurant.general.common.api.UserProfile;
import io.oasp.gastronomy.restaurant.general.common.api.Usermanagement;
import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.staffmanagement.dataaccess.api.StaffMemberEntity;
import io.oasp.gastronomy.restaurant.staffmanagement.dataaccess.api.dao.StaffMemberDao;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.Staffmanagement;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.to.StaffMemberCto;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.to.StaffMemberEto;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.to.StaffMemberSearchCriteriaTo;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;
import io.oasp.module.jpa.common.api.to.PaginationResultTo;

/**
 * Implementation of {@link Staffmanagement}.
 */
@Named
@Component
@Transactional
public class StaffmanagementImpl extends AbstractComponentFacade implements Staffmanagement, Usermanagement {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(StaffmanagementImpl.class);

  /**
   * @see #getStaffMemberDao()
   */
  private StaffMemberDao staffMemberDao;

  /**
   * The constructor.
   */
  public StaffmanagementImpl() {

    super();
  }

  /**
   * Do not extract this method as a service, because of PermitAll. (only for login)
   */
  @Override
  @RolesAllowed(PermissionConstants.FIND_STAFF_MEMBER)
  public StaffMemberEto findStaffMemberByLogin(String login) {

    return privateFindStaffMemberByLogin(login);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_STAFF_MEMBER)
  public StaffMemberEto findStaffMember(Long id) {

    return getBeanMapper().map(getStaffMemberDao().find(id), StaffMemberEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_STAFF_MEMBER)
  public List<StaffMemberEto> findAllStaffMembers() {

    List<StaffMemberEntity> members = getStaffMemberDao().findAll();
    List<StaffMemberEto> membersBo = new ArrayList<>();

    for (StaffMemberEntity member : members) {
      membersBo.add(getBeanMapper().map(member, StaffMemberEto.class));
    }

    return membersBo;
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_STAFF_MEMBER)
  public PaginatedListTo<StaffMemberEto> findStaffMemberEtos(StaffMemberSearchCriteriaTo criteria) {

    // Uncomment next line in order to limit the maximum page size for the staff member search
    // criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);

    PaginatedListTo<StaffMemberEntity> offers = getStaffMemberDao().findStaffMembers(criteria);
    return mapPaginatedEntityList(offers, StaffMemberEto.class);
  }

  @Override
  public UserProfile findUserProfileByLogin(String login) {

    return privateFindStaffMemberByLogin(login);
  }

  /**
   * Do not extract this method as a service, because of PermitAll. (only for login)
   */
  private StaffMemberEto privateFindStaffMemberByLogin(String login) {

    return getBeanMapper().map(getStaffMemberDao().findByLogin(login), StaffMemberEto.class);
  }

  @Override
  @RolesAllowed(PermissionConstants.DELETE_STAFF_MEMBER)
  public void deleteStaffMemberByLogin(String login) {

    getStaffMemberDao().delete(getStaffMemberDao().findByLogin(login));
  }

  @Override
  @RolesAllowed(PermissionConstants.DELETE_STAFF_MEMBER)
  public void deleteStaffMember(Long staffMemberId) {

    getStaffMemberDao().delete(staffMemberId);
  }

  @Override
  @RolesAllowed(PermissionConstants.SAVE_STAFF_MEMBER)
  public StaffMemberEto saveStaffMember(StaffMemberEto staffMember) {

    Objects.requireNonNull(staffMember, "staffMember");

    Long id = staffMember.getId();
    StaffMemberEntity targetStaffMember = null;

    if (id != null) {
      targetStaffMember = getStaffMemberDao().find(id);
    }
    if (targetStaffMember == null) {
      // StaffMember is new: -> create
      LOG.debug("Saving StaffMember with id '{}' to the database.", id);
    } else {
      // StaffMember already exists: -> Update
      LOG.debug("Updating StaffMember with id '{}' in the database.", id);
      if (!Objects.equals(targetStaffMember.getName(), staffMember.getName())) {
        LOG.debug("Changing login of StaffMember with id '{}' from '{}' to '{}' in the database.", id,
            targetStaffMember.getName(), staffMember.getName());
      }
    }
    StaffMemberEntity persistedStaffMember = getStaffMemberDao()
        .save(getBeanMapper().map(staffMember, StaffMemberEntity.class));
    return getBeanMapper().map(persistedStaffMember, StaffMemberEto.class);
  }

  /**
   * @return the {@link StaffMemberDao} instance.
   */
  public StaffMemberDao getStaffMemberDao() {

    return this.staffMemberDao;
  }

  /**
   * Sets the field 'staffMemberDao'.
   *
   * @param staffMemberDao New value for staffMemberDao
   */
  @Inject
  public void setStaffMemberDao(StaffMemberDao staffMemberDao) {

    this.staffMemberDao = staffMemberDao;
  }

  @Override
  public StaffMemberCto findStaffMemberCto(Long id) {

    LOG.debug("Get StaffMemberCto with id {} from database.", id);
    StaffMemberEntity entity = getStaffMemberDao().findOne(id);
    StaffMemberCto cto = new StaffMemberCto();
    cto.setStaffMember(getBeanMapper().map(entity, StaffMemberEto.class));

    return cto;
  }

  @Override
  public PaginatedListTo<StaffMemberCto> findStaffMemberCtos(StaffMemberSearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<StaffMemberEntity> staffmembers = getStaffMemberDao().findStaffMembers(criteria);
    List<StaffMemberCto> ctos = new ArrayList<>();
    for (StaffMemberEntity entity : staffmembers.getResult()) {
      StaffMemberCto cto = new StaffMemberCto();
      cto.setStaffMember(getBeanMapper().map(entity, StaffMemberEto.class));
      ctos.add(cto);

    }
    PaginationResultTo pagResultTo = new PaginationResultTo(criteria.getPagination(), (long) ctos.size());
    PaginatedListTo<StaffMemberCto> pagListTo = new PaginatedListTo(ctos, pagResultTo);
    return pagListTo;
  }

}
