package com.Controller;

import com.domain.POST.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    List<Post> posts = new ArrayList<>();  //클래스의 변수로 선언해서 모든 메소드에서 접근 가능해짐

    public HomeController() {
        // 테스트 게시물 리스트 생성
        posts.add(new Post(1L, "First Post", "This is the first post description"));
        posts.add(new Post(2L, "Second Post", "This is the second post description"));
        posts.add(new Post(3L, "Third Post", "This is the third post description"));

        // 데이터 확인용 출력
        System.out.println("Initial posts:");
        posts.forEach(post -> System.out.println("Title: " + post.getTitle() + ", Description: " + post.getDescription()));

    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", posts);
        return "index";
    }

    // 1. 새 게시물 추가 페이지로 이동하는 라우트 (GET 요청)
    @GetMapping("/posts/new")
    public String newPostForm() {
        return "posts/new";  // posts/new.html 템플릿으로 이동
    }

    // 2. 새 게시물 추가 처리 (POST 요청)
    @PostMapping("/posts/new")
    public String addNewPost(@RequestParam("title") String title, @RequestParam("description") String description) {
        Long newId = (long) (posts.size() + 1);  // 새로운 게시물의 ID
        Post newPost = new Post(newId, title, description);  // 새 게시물 생성
        posts.add(newPost);  // 게시물 목록에 추가
        return "redirect:/";  // 게시물 목록 페이지로 리다이렉트
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        posts.removeIf(post -> post.getId().equals(id));
        return "redirect:/";
    }
}
