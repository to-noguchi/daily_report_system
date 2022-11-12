//★追加クラス

package models;
//DTO
//テーブル名やカラム名をJpaConstクラスの定数で定義している

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォローに関わる従業員データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FOL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_FOL_GET_ALL,
            query = JpaConst.Q_FOL_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_COUNT,
            query = JpaConst.Q_FOL_COUNT_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Follow {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローする側の従業員のid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_WER, nullable = false)
    private Employee followerId;

    /**
     * フォロー先の従業員のid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_WEE, nullable = false)
    private Employee followeeId;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.FOL_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

}