//★追加クラス

package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Employee_Follow;

/**
 * フォローしている従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class Employee_FollowConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param efv EmployeeViewのインスタンス
     * @return Employee_Followのインスタンス
     */
    public static Employee_Follow toModel(Employee_FollowView efv) {

        return new Employee_Follow(
                efv.getId(),
                efv.getFollower_name(),
                efv.getFollower_report());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param ef Employee_Followのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static Employee_FollowView toView(Employee_Follow ef) {

        if(ef == null) {
            return null;
        }

        return new Employee_FollowView(
                ef.getId(),
                ef.getEmployee_id(),
                ef.getFollower_id());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<Employee_FollowView> toViewList(List<Employee_Follow> list) {
        List<Employee_FollowView> efvs = new ArrayList<>();

        for (Employee_Follow ef : list) {
            efvs.add(toView(ef));
        }

        return efvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Employee_Follow ef, Employee_FollowView efv) {
        ef.setId(efv.getId());
        ef.setEmployee_id(efv.getFollower_name());
        ef.setFollower_id(efv.getFollower_report());

    }

}