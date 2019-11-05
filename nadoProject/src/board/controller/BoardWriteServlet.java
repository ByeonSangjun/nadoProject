package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;


/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/bwrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 등록 처리용 컨트롤러 : 파일 업로드 처리 기능 포함 (cos.jar 라이브러리 사용)
		
		//1. multipart 방식으로 전송왔는지 확인함 : 아니면 에러 페이지 내보냄
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form enctype 속성이 multipart 여야 합니다.");
			view.forward(request, response);
		}
		
		//2. 업로드할 파일의 용량 제한 설정 : 10Mbtype 로 제한한다면
		int maxSize = 1024 *1024 * 10;
		
		//3. 업로드한 파일이 저장될 폴더 지정
		//"c:\\work\\savefiles" 해도 됨
		//현재 서버 엔진(웹 컨테이너)에서 실행되고 있는 에플리케이션의 루트 안의 폴더일 때는
		String savePath = request.getSession().getServletContext().getRealPath(
									"/resources/groupware/board_upfiles");
		
		//4. 파일업로드 실행함
		//cos.jar 는 MultipartRequest 클래스를 사용해서 request 를 MultipartRequest 로 바꿈
		MultipartRequest mrequest = new MultipartRequest(request, savePath, 
					maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//객체 생성과 동시에 파일 업로드 됨.
		
		//5. 전송온 값 꺼내서 객체에 저장하기
		Board board = new Board();
		
		board.setBoardTitle(mrequest.getParameter("title"));
		board.setUserId(mrequest.getParameter("writer"));
		board.setBoardContent(mrequest.getParameter("ir1"));
		
		//업로드 저장된 파일이름만 추출해서 변수에 기록
		String originalFileName = mrequest.getFilesystemName("upfile");
		board.setbFileOriname(originalFileName);
		
		//System.out.println("board : " + board);
		//저장 폴더의 파일명 중복 해결 방법 : 파일명을 "yyyyMMddHHmmss.확장자" 로 바꾸기
		if(originalFileName != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
						+ "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			
			//파일명을 바꾸려면 File 객체의 renameTo() 사용함
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);
			
			//파일 이름바꾸기 실행함 >> 실패한 경우에는 직접 바꾸기함
			if(!originFile.renameTo(renameFile)) {
				//파일 입출력 스트림 생성하고, 원본을 읽어서 바꿀이름 파일에 기록함
				int read = -1;
				byte[] buf = new byte[1024];	//한 번에 읽어서 저장할 바이트 배열
				
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				while((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				
				fin.close();
				fout.close();
				originFile.delete();	//원본 파일 삭제함.
			}	//renameTo()
			
			board.setbFileRename(renameFileName);
		}	//파일 업로드시
		
		//6. 모델 서비스로 전달하고, 결과 받기
		
		HttpSession session = request.getSession(false);
		
		int result = new BoardService().insertBoard(session.getAttribute("comp"), board);
		
		//7. 받은 결과로 성공/실패 페이지 내보내기
		if(result > 0) {
			response.sendRedirect("/nado/blist");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 공지글 등록 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
