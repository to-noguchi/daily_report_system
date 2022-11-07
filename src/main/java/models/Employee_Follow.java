//★追加クラス

package models;
//DTO
//テーブル名やカラム名をJpaConstクラスの定数で定義している

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォローしている従業員データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_EMPFOL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_EMPFOL_GET_ALL,
            query = JpaConst.Q_EMPFOL_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMPFOL_COUNT,
            query = JpaConst.Q_EMPFOL_COUNT_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Employee_Follow {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.EMPFOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローする従業員のid
     */
    @Column(name = JpaConst.EMPFOL_COL_EMP, nullable = false)
    private String employee_id;

    /**
     * フォロー先の従業員のid
     */
    @Column(name = JpaConst.EMPFOL_COL_FOL, nullable = false)
    private String follower_id;


}