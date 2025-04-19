package com.xiaou.novel.controller.anthor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "AiController", description = "作家后台-AI模块")
@RequestMapping("/novel/author/ai")
public class AuthorAiController {

}
