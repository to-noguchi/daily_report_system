package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Employee_Follow;

/**
 * フォロー関係のDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class Employee_FollowConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param efv Employee_FollowViewのインスタンス
     * @return Employee_Followのインスタンス
     */
    public static Employee_Follow toModel(Employee_FollowView efv) {
        return new Employee_Follow(
                efv.getId(),
                Employee_FollowConverter.toModel(efv.getEmployee()),
                efv.getEmployee_id(),
                efv.getFollower_id());

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param ef Employee_Followのインスタンス
     * @return Employee_FollowViewのインスタンス
     */
    public static Employee_FollowView toView(Employee_Follow ef) {

        if (ef == null) {
            return null;
        }

        return new Employee_FollowView(
                ef.getId(),
                Employee_FollowConverter.toView(ef.getEmployee()),
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
     * @param ef DTOモデル(コピー先)
     * @param efv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Employee_Follow ef, Employee_FollowView efv) {
        ef.setId(efv.getId());
        ef.setEmployee(EmployeeConverter.toModel(efv.getEmployee()));
        ef.setEmployee_id(efv.getEmployee_id());
        ef.setFollower_id(efv.getFollower_id());

    }

}