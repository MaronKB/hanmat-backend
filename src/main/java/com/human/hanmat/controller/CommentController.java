package com.human.hanmat.controller;

import com.human.hanmat.dto.CommentDTO;
import com.human.hanmat.dto.Page;
import com.human.hanmat.dto.PostDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;



    // 댓글 저장 API
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<?> addComment(@RequestBody CommentDTO commentDTO) {
        try {
            System.out.println("Received commentDTO: " + commentDTO); //
            // 댓글 저장
            System.out.println("Received regBy: " + commentDTO.getRegBy()); // 여기서 NULL인지 확인
            CommentDTO savedComment = commentService.save(commentDTO);

            // 저장된 댓글 데이터를 JSON 응답으로 반환
            return new Response<>(savedComment, "Comment successfully saved", true, null);
        } catch (Exception e) {
            // 실패 시 오류 메시지를 포함한 JSON 반환
            return new Response<>(null, "Comment saving failed", false, e.getMessage());
        }
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        List<CommentDTO> commentPage = commentService.getPage(page, size, sort);
        int total = commentService.getTotal();

        Page<CommentDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(commentPage);
        pageData.setTotalItems(total);

        return new Response<>(pageData, "Success", true, null);
    }
}


