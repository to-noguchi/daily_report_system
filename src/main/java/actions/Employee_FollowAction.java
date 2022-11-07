//★追加クラス
package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.Employee_FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.Employee_FollowService;

/**
 * フォローに関する処理を行うActionクラス
 *
 */
public class Employee_FollowAction extends ActionBase {

    private Employee_FollowService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new Employee_FollowService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * フォロー先従業員一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するフォロー先従業員データを取得
        int page = getPage();
        List<Employee_FollowView> employee_follows = service.getAllPerPage(page);

        //フォロワーデータの件数を取得
        long emplloyee_followsCount = service.countAll();

        putRequestScope(AttributeConst.EMPFOLS, employee_follows); //取得したフォロー先従業員データ
        putRequestScope(AttributeConst.EMPFOL_COUNT, employee_follows_count); //全てのフォロー先従業員データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_EMPFOL_INDEX);
    }

}