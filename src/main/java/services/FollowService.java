package services;
//フォローテーブルの操作に関わるすべての処理をそれぞれメソッドとして定義

import java.util.List;

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
    public List<FollowView> getPerPage(int page) {
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL, Follow.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return FollowConverter.toViewList(follows);
    }

    /**
     * フォローテーブルのデータの件数を取得し、返却する
     * @return フォローテーブルのデータの件数
     */
    public long countAll() {
        long folCount = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT, Long.class)
                .getSingleResult();

        return folCount;
    }
}