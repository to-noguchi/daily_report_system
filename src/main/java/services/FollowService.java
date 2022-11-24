package services;
//フォローテーブルの操作に関わるすべての処理をそれぞれメソッドとして定義

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Follow;

/**
 * フォローテーブルの操作に関わる処理を行うクラス
 */
public class FollowService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータ（指定した従業員がフォローしているフォロイーのデータ）を取得し、FollowViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<FollowView> getPerPage(EmployeeView follower,int page) {
        List<Follow> followee = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL_MINE, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, EmployeeConverter.toModel(follower))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return FollowConverter.toViewList(followee);
    }

    /**
     * 指定した従業員がフォローしているフォロイーデータの件数を取得し、返却する
     * @return フォローテーブルのデータの件数
     */
    public long countAllMine(EmployeeView followee) {
        long folCount = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, EmployeeConverter.toModel(followee))
                .getSingleResult();

        return folCount;
    }
}