package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.FollowService;

/**
 * フォローに関わる処理を行うActionクラス
 *
 */
public class FollowAction extends ActionBase {

    private FollowService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();

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

            //セッションからログイン中の従業員情報を取得
            EmployeeView loginEmployee = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

            //指定されたページ数の一覧画面に表示するデータを取得
            int page = getPage();
            List<FollowView> follows = service.getPerPage(loginEmployee,page);

            //ログイン中の従業員のフォロイーデータの件数を取得
            long follows_count = service.countAllMine(loginEmployee);

            putRequestScope(AttributeConst.FOLLOWEE, follows); //取得したフォロイーデータ
            putRequestScope(AttributeConst.FOL_COUNT, follows_count); //ログイン中の従業員のフォロイーデータの件数
            putRequestScope(AttributeConst.PAGE, page); //ページ数
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

            //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);


            }

            //一覧画面を表示
            forward(ForwardConst.FW_FOL_INDEX);

        } //追記

    /**
     * フォローの新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

            //フォロー情報のインスタンスを作成する
            FollowView fv = new FollowView();

            //フォロー情報登録
            fv = service.create(fv);

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.FW_PERREP_INDEX, ForwardConst.CMD_INDEX);
            }

/**
 * 物理削除を行う
 * @throws ServletException
 * @throws IOException
 */
public void destroy() throws ServletException, IOException {

        //idを条件にフォローデータを物理削除する
        service.destroy(toNumber(getRequestParam(AttributeConst.FOLLOWEE)));

        //一覧画面にリダイレクト
        redirect(ForwardConst.FW_PERREP_INDEX, ForwardConst.CMD_INDEX);
    }
}