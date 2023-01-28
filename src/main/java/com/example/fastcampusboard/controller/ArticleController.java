package com.example.fastcampusboard.controller;

import com.example.fastcampusboard.domain.ArticleRequest;
import com.example.fastcampusboard.domain.constant.FormStatus;
import com.example.fastcampusboard.dto.response.ArticleResponse;
import com.example.fastcampusboard.dto.response.ArticleWithCommentsResponse;
import com.example.fastcampusboard.dto.security.BoardPrincipal;
import com.example.fastcampusboard.service.ArticleService;
import com.example.fastcampusboard.service.PaginationService;
import com.example.fastcampusboard.type.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articles")
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;
    @GetMapping
    public String articles( @RequestParam(required = false) SearchType searchType,
                            @RequestParam(required = false) String searchValue,
                            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                            ModelMap map){
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());
        map.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "articles/detail";
    }
    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);

        return "articles/form";
    }

    @PostMapping("/form")
    public String postNewArticle(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleRequest articleRequest
    ) {
        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles";
    }

}
