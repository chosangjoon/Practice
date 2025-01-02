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

    private List<Post> posts = new ArrayList<>();
    private Long nextId = 1L;  // ID 자동 생성을 위한 변수 추가

    public HomeController() {
        // 테스트 게시물에도 nextId 사용
        posts.add(new Post(nextId++, "First Post", "This is the first post description"));
        posts.add(new Post(nextId++, "Second Post", "This is the second post description"));
        posts.add(new Post(nextId++, "Third Post", "This is the third post description"));

        // 데이터 확인용 출력
        System.out.println("Initial posts:");
        posts.forEach(post -> System.out.println("Title: " + post.getTitle() + ", Description: " + post.getDescription()));

    }

    @GetMapping("/")
    public String home(Model model) {
        // 콘솔에 현재 게시물 목록과 ID 출력
        System.out.println("Current posts:");
        posts.forEach(post -> System.out.println("ID: " + post.getId() + ", Title: " + post.getTitle()));
        
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
        Post newPost = new Post(nextId++, title, description);  // nextId 사용 후 증가
        posts.add(newPost);
        return "redirect:/";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        posts.removeIf(post -> post.getId().equals(id));
        return "redirect:/";
    }

    // 게시물 수정 폼을 보여주는 메서드
    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable("id") Long id, Model model) {
        // ID로 게시물 찾기
        Post post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (post == null) {
            return "redirect:/";  // 게시물이 없으면 홈으로 리다이렉트
        }

        model.addAttribute("post", post);
        return "posts/edit";  // posts/edit.html 템플릿으로 이동
    }

    // 게시물 수정을 처리하는 메서드
    @PostMapping("/posts/{id}/edit")
    public String updatePost(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        
        // ID로 게시물 찾아서 수정
        posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(title);
                    post.setDescription(description);
                });

        return "redirect:/";
    }
}
