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
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するフォロワーデータを取得
        int page = getPage();
        List<Employee_FollowView> follower_name = service.getAllPerPage(page);

        //フォロワーデータの件数を取得
        long followersCount = service.countAll();

        putRequestScope(AttributeConst.EMPFOLS, employee_follows); //取得したフォロワーデータ
        putRequestScope(AttributeConst.EMPFOL_COUNT, employee_follows_count); //全てのフォロワーデータの件数
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

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件にフォロワーデータを取得する
        Employee_FollowView efv = service.findOne(toNumber(getRequestParam(AttributeConst.EMPFOL_ID)));

        if (efv == null) {
            //フォロワーが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.EMPFOL, efv); //取得したフォロワーデータ

            //詳細画面を表示
            forward(ForwardConst.FW_EMPFOL_SHOW);
        }
    }
}