package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Follow;

/**
 * フォロー関係のDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class FollowConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param fv FollowViewのインスタンス
     * @return Followのインスタンス
     */
    public static Follow toModel(FollowView fv) {
        return new Follow(
                fv.getId(),
                FollowConverter.toModel(fv.getEmployee()),
                fv.getFollower_id(),
                fv.getFollowee_id());

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param ef Employee_Followのインスタンス
     * @return Employee_FollowViewのインスタンス
     */
    public static FollowView toView(Follow f) {

        if (f == null) {
            return null;
        }

        return new FollowView(
                f.getId(),
                FollowConverter.toView(f.getEmployee()),
                f.getFollower_id(),
                f.getFollowee_id());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<FollowView> toViewList(List<Follow> list) {
        List<FollowView> fvs = new ArrayList<>();

        for (Follow f : list) {
            fvs.add(toView(f));
        }

        return fvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param f DTOモデル(コピー先)
     * @param fv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Follow f, FollowView fv) {
        f.setId(fv.getId());
        f.setEmployee(EmployeeConverter.toModel(fv.getEmployee()));
        f.setFollower_id(fv.getFollower_id());
        f.setFollowee_id(fv.getFollowee_id());

    }

}