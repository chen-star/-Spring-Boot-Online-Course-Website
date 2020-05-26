package com.course.business.controller;

import com.course.server.domain.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-05-23 21:18
 */
@RestController
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> test() {
        return chapterService.list();
    }
}