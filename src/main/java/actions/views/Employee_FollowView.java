//★追加クラス

package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォローについて画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class Employee_FollowView {

    /**
     * id
     */
    private Integer id;

    /**
     * フォローする側の従業員Viewとの関連付け
     */
    private EmployeeView employee;

    /**
     * フォローする側の従業員のid
     */
    private Employee_FollowView employee_id;

    /**
     * フォロー先の従業員のid
     */
    private Employee_FollowView follower_id;

}