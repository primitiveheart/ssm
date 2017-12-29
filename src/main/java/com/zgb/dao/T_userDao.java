package com.zgb.dao;

import com.zgb.entity.T_user;

import java.util.Set;

/**
 * Created by admin on 2017/12/28.
 */
public interface T_userDao {
    T_user findUserByUsername(String userName);

    Set<String> findRoles(String userName);

    Set<String> findPermissions(String userName);
}
