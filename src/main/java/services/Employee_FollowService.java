//★追加クラス

package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.Employee_FollowConverter;
import actions.views.Employee_FollowView;
import constants.JpaConst;
import models.Employee_Follow;

/**
 * フォローテーブルの操作に関わる処理を行うクラス
 */
public class Employee_FollowService extends ServiceBase {

    /**
     * フォローしている従業員データを、指定されたページ数の一覧画面に表示する分取得しEmployee_FollowViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<Employee_FollowView> getMinePerPage(Employee_FollowView employee, int page) {

        List<Employee_Follow> employee_follows = em.createNamedQuery(JpaConst.Q_EMPFOL_GET_ALL, Employee_Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return Employee_FollowConverter.toViewList(employee_follows);
    }

    /**
     * フォローしている従業員の件数を取得し、返却する
     * @param employee_follow
     * @return フォローしている従業員データの件数
     */
    public long countAllMine(Employee_FollowView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_EMPFOL_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示するフォロー先従業員データを取得し、Employee_FollowViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<Employee_FollowView> getAllPerPage(int page) {

        List<Employee_Follow> employee_follows = em.createNamedQuery(JpaConst.Q_EMPFOL_GET_ALL, Employee_Follow.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return Employee_FollowConverter.toViewList(employee_follows);
    }

    /**
     * フォローテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long employee_follows_count = (long) em.createNamedQuery(JpaConst.Q_EMPFOL_COUNT, Long.class)
                .getSingleResult();
        return employee_follows_count;
    }

    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public Employee_FollowView findOne(int id) {
        return Employee_FollowConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Employee_Follow findOneInternal(int id) {
        return em.find(Employee_Follow.class, id);
    }

    /**
     * フォロー先従業員を1件登録する
     * @param efv フォロー先従業員データ
     */
    private void createInternal(Employee_FollowView efv) {

        em.getTransaction().begin();
        em.persist(Employee_FollowConverter.toModel(efv));
        em.getTransaction().commit();

    }

    /**
     * フォロー先従業員を更新する
     * @param efv フォロー先従業員データ
     */
    private void updateInternal(Employee_FollowView efv) {

        em.getTransaction().begin();
        Employee_Follow ef = findOneInternal(efv.getId());
        Employee_FollowConverter.copyViewToModel(ef, efv);
        em.getTransaction().commit();

    }

}