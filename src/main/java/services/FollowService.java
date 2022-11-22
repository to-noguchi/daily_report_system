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
     * 指定されたページ数の一覧画面に表示するデータを取得し、FollowViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<FollowView> getPerPage(EmployeeView follow,int page) {
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, EmployeeConverter.toModel(follow))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return FollowConverter.toViewList(follows);
    }

    /**
     * 指定した従業員がフォローしているフォロイーデータの件数を取得し、返却する
     * @return フォローテーブルのデータの件数
     */
    public long countAllMine(EmployeeView follow) {
        long folCount = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, EmployeeConverter.toModel(follow))
                .getSingleResult();

        return folCount;
    }
}