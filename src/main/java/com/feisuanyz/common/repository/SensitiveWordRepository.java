package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.SensitiveWord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.feisuanyz.common.entity.SensitiveWordDO;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

/**
 * <p>
 *   敏感词数据访问接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface SensitiveWordRepository extends JpaRepository<SensitiveWord, Long> {

    List<SensitiveWordDO> findByStatus(Integer status);

    /**
     * 根据敏感词内容和类型查找敏感词
     * @param wordContent 敏感词内容
     * @param wordType 词类型
     * @return Optional<SensitiveWord>
     */
    @Query("SELECT s FROM SensitiveWord s WHERE s.wordContent = :wordContent AND s.wordType = :wordType")
    Optional<SensitiveWord> findByWordContentAndWordType(@Param("wordContent") String wordContent, @Param("wordType") Integer wordType);
}
