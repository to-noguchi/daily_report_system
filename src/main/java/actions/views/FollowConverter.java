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
                EmployeeConverter.toModel(fv.getFollower()),
                EmployeeConverter.toModel(fv.getFollowee()),
                fv.getCreatedAt());

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
                EmployeeConverter.toView(f.getFollower()),
                EmployeeConverter.toView(f.getFollowee()),
                f.getCreatedAt());
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
        f.setFollower(EmployeeConverter.toModel(fv.getFollower()));
        f.setFollower(EmployeeConverter.toModel(fv.getFollowee()));
        f.setCreatedAt(fv.getCreatedAt());

    }

}