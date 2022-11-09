//★追加クラス

package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Follow;

/**
 * フォローテーブルの操作に関わる処理を行うクラス
 */
public class FollowService extends ServiceBase {

    /**
     * フォローしている従業員データを、指定されたページ数の一覧画面に表示する分取得しEmployee_FollowViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<FollowView> getMinePerPage(FollowView employee, int page) {

        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return FollowConverter.toViewList(follows);
    }

    /**
     * フォローしている従業員の件数を取得し、返却する
     * @param employee_follow
     * @return フォローしている従業員データの件数
     */
    public long countAllMine(FollowView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示するフォロー先従業員データを取得し、Employee_FollowViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<FollowView> getAllPerPage(int page) {

        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOL_GET_ALL, Follow.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return FollowConverter.toViewList(follows);
    }

    /**
     * フォローテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long follows_count = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT, Long.class)
                .getSingleResult();
        return follows_count;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public FollowView findOne(int id) {
        return FollowConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Follow findOneInternal(int id) {
        return em.find(Follow.class, id);
    }

    /**
     * フォロー先従業員を1件登録する
     * @param efv フォロー先従業員データ
     */
    private void createInternal(FollowView fv) {

        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(fv));
        em.getTransaction().commit();

    }

    /**
     * フォロー先従業員を更新する
     * @param efv フォロー先従業員データ
     */
    private void updateInternal(FollowView fv) {

        em.getTransaction().begin();
        Follow f = findOneInternal(fv.getId());
        FollowConverter.copyViewToModel(f, fv);
        em.getTransaction().commit();

    }

}