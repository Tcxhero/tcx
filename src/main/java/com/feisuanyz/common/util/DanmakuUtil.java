package com.feisuanyz.common.util;

import com.feisuanyz.common.entity.SensitiveWord;
import com.feisuanyz.common.repository.SensitiveWordRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *   弹幕工具类，用于敏感词检测
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Component
public class DanmakuUtil {

    @Autowired
    private SensitiveWordRepository sensitiveWordRepository;

    private Set<String> sensitiveWords;

    @PostConstruct
    public void init() {
        sensitiveWords = new HashSet<>();
        List<SensitiveWord> words = sensitiveWordRepository.findByStatus(1);
        for (SensitiveWord word : words) {
            sensitiveWords.add(word.getWordContent());
        }
    }

    /**
     * 检查内容是否包含敏感词
     * @param content 内容
     * @return 是否包含敏感词
     */
    public boolean containsSensitiveWord(String content) {
        for (String word : sensitiveWords) {
            if (content.contains(word)) {
                return true;
            }
        }
        return false;
    }
}