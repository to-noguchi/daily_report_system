package actions;

import java.io.IOException;
import java.util.List; //追記

import javax.servlet.ServletException;

import actions.views.EmployeeView; //追記
import actions.views.ReportView; //追記
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;  //追記
import services.EmployeeService;  //追記
import services.ReportService;  //追記

/**
 * 指定の従業員の日報一覧に関する処理を行うActionクラス
 *
 */
public class PersonalReportAction extends ActionBase {

    private ReportService rs; //追記
    private EmployeeService es;

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        rs = new ReportService(); //追記
        es = new EmployeeService();

        //メソッドを実行
        invoke();

        rs.close();
        es.close();//追記

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        //idを条件に従業員データを取得する
        EmployeeView personalEmployee = es.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));

        //指定の従業員が作成した日報データを、指定されたページ数の一覧画面に表示する分取得する
        int page = getPage();
        List<ReportView> personalReports = rs.getMinePerPage(personalEmployee, page);

        //指定の従業員が作成した日報データの件数を取得
        long reportsCount = rs.countAllMine(personalEmployee);

        putRequestScope(AttributeConst.REPORTS, personalReports); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //指定の従業員が作成した日報の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //一覧画面を表示
        forward(ForwardConst.FW_PERREP_INDEX);
    }

}