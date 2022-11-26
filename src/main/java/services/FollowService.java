package services;
//フォローテーブルの操作に関わるすべての処理をそれぞれメソッドとして定義

import java.time.LocalDateTime;
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
     * フォロワーがフォローしているフォロイーデータを取得し、FollowViewのリストで返却する
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
     * フォロワーがフォローしているフォロイーデータの件数を取得し、返却する
     * @return フォローテーブルのデータの件数
     */
    public long countAllMine(EmployeeView follower) {
        long folCount = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, EmployeeConverter.toModel(follower))
                .getSingleResult();

        return folCount;
    }

    /**
     * idを条件に取得したデータをFollowViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public FollowView findOne(int id) {
        Follow f = findOneInternal(id);
        return FollowConverter.toView(f);
    }

    /**
     * idを条件にフォローデータを物理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件にフォロー情報を取得する
        FollowView savedFol = findOne(id);

        //削除処理を行う
        destroy(savedFol);

    }

    /**
     * idを条件にデータを1件取得し、Followのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Follow findOneInternal(int id) {
        Follow f = em.find(Follow.class, id);

        return f;
    }

    /**
     * フォローデータを1件登録する
     * @param fv フォローデータ
     * @return
     */
    public FollowView create(FollowView fv) {

        FollowView fv = new FollowView();

        EmployeeView follower = (EmployeeView)follower;
        fv.setFollower(follower);

        EmployeeView followee = (EmployeeView)followee;
        fv.setFollowee(followee);

        //登録日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        fv.setCreatedAt(now);

        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(fv));
        em.getTransaction().commit();

        return fv;
    }

    /**
     * フォローデータを削除する
     * @param fv フォローの登録内容
     */
    private void destroy(FollowView fv) {

        em.getTransaction().begin();
        Follow f = findOneInternal(fv.getId());
        FollowConverter.copyViewToModel(f, fv);
        em.getTransaction().commit();

    }
}