package com.Samkarro.CMSapi.common.DTO;

import java.util.Set;

public record ArticleResponse (
        int articleId,
        String title,
        String body,
        Set<String> categories
){}
