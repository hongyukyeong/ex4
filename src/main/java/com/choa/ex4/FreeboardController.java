package com.choa.ex4;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.freeboard.FreeboardDTO;
import com.choa.freeboard.FreeboardServiceImpl;


@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {
	
	@Autowired
	private FreeboardServiceImpl freeboardServiceImpl;
	
	//list
	@RequestMapping(value="freeboardList", method= RequestMethod.GET)
	public String freeboardList(Model model, @RequestParam(defaultValue="1") Integer curPage)throws Exception{
		
		model.addAttribute("board", "freeboard");
		model.addAttribute("list", freeboardServiceImpl.boardList(curPage));
		
		return "board/boardList";
	}
	
	
	
	//view
		@RequestMapping(value="freeboardView", method=RequestMethod.GET)
		public String freeboardView(Integer num, Model model) throws Exception{
			
			BoardDTO boardDTO=freeboardServiceImpl.boardView(num);
			model.addAttribute("board", "freeboard");
			model.addAttribute("view", boardDTO);
			
			return "board/boardView";
		}
	
	
		//writeForm
		@RequestMapping(value="freeboardWrite", method=RequestMethod.GET)
		public String freeboardWrite(Model model){
			model.addAttribute("path", "Write");
			model.addAttribute("board", "freeboard");
			
			return "board/boardWrite";
			
		}
		//write
		@RequestMapping(value="freeboardWrite", method=RequestMethod.POST)
		public String freeboardWrite(FreeboardDTO freeboardDTO, Model model, RedirectAttributes rd) throws Exception{
			
			int result=freeboardServiceImpl.boardWrite(freeboardDTO);
			String message ="FAIL";
			
			if(result>0){
				message="SUCCESS";
			}
			
			
			/*redirect로 보낼 때 message도 보내고 싶고*/
			/**/
			rd.addFlashAttribute("message", message);
			
			return "board/result";
			
			/*redirect로 보내는 방법*/
			/*return "redirect:/"; 바로 홈으로 가는 주소 */
			
			
		}
		
		//updateForm
		@RequestMapping(value="freeboardUpdate", method=RequestMethod.GET)
		public String freeboardUpdate(Integer num, Model model) throws Exception{
			
				BoardDTO boardDTO = freeboardServiceImpl.boardView(num);
				model.addAttribute("dto", boardDTO);
				model.addAttribute("path", "Update");
				model.addAttribute("board", "freeboard");
				
				return "freeboard/freeboardWrite";
		}
		
		//update
		@RequestMapping(value="freeboardUpdate" , method=RequestMethod.POST)
		public String freeboardUpdate(BoardDTO boardDTO, RedirectAttributes rd, Model model) throws Exception{
			int result= freeboardServiceImpl.boardUpdate(boardDTO);
			String message ="UPDATE FAIL";
			
			if(result>0){
				message="UPDATE SUCCESS";			
			}
			
			rd.addFlashAttribute("message", message);
			return "redirect:freeboardList";
			
			//if문 성공 실패
		}
			
		//delete
		@RequestMapping(value="freeboardDelete", method=RequestMethod.GET)
		public String freeboardDelete(Integer num, RedirectAttributes rd) throws Exception{
			int result=freeboardServiceImpl.boardDelete(num);
			String message ="DELETE FAIL";
			
			if(result>0){
				message="DELETE SUCCESS";
			}
			
			
			
			rd.addFlashAttribute("message", message);
			return "redirect:freeboardList";
			//if문 성공 실패
		}
		
			
		
		
		
		
		
		
		
		
}
