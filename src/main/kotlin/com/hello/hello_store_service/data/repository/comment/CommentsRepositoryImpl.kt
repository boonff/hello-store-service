package com.hello.hello_store_service.data.repository.comment

import com.hello.hello_store_service.data.model.entity.CommentEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class CommentsRepositoryImpl : CommentsRepositoryCustom {
    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    override fun findByRange(spuId: String, pageIndex: Int, pageSize: Int): List<CommentEntity> {
        val query = Query()
        query.addCriteria(Criteria.where("spuId").`is`(spuId))
        query.skip((pageIndex * pageSize).toLong())
        query.limit(pageSize)
        return mongoTemplate.find(query, CommentEntity::class.java)
    }

    override fun findDetail(
        spuId: String,
        pageIndex: Int,
        pageSize: Int,
        hasImage: Boolean,
        commentLevel: Int
    ): List<CommentEntity> {
        val matchStage = Aggregation.match(Criteria.where("spuId").`is`(spuId))
        // 可选筛选：有图
        val hasImageStage = if (hasImage) {
            Aggregation.match(Criteria.where("commentResources")
                .elemMatch(Criteria.where("src").exists(true)))
        } else null

        val commentLevelStage = when (commentLevel) {
            1 -> Aggregation.match(Criteria.where("commentScore").`in`(1, 2))  // 差评
            2 -> Aggregation.match(Criteria.where("commentScore").`is`(3))      // 中评
            3 -> Aggregation.match(Criteria.where("commentScore").`in`(4, 5))  // 好评
            else -> null
        }

        // 分页：skip + limit
        val skipStage = Aggregation.skip((pageIndex * pageSize).toLong())
        val limitStage = Aggregation.limit(pageSize.toLong())

        // 组合管道
        val stages = listOfNotNull(matchStage, hasImageStage, commentLevelStage, skipStage, limitStage)
        val aggregation = Aggregation.newAggregation(stages)

        return mongoTemplate.aggregate(aggregation, "comments", CommentEntity::class.java).mappedResults

    }

    override fun findRandomTop(
        spuId: String,
        randomSize: Int,
        selectSize: Int
    ): List<CommentEntity> {
        // 随机选取 randomSize 条 spuId 匹配的评论
        val matchStage = Aggregation.match(Criteria.where("spuId").`is`(spuId))
        val sampleStage = Aggregation.sample(randomSize.toLong())

        // 按 commentScore 降序排序
        val sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "commentScore"))

        // 限制返回 selectSize 条
        val limitStage = Aggregation.limit(selectSize.toLong())

        val aggregation = Aggregation.newAggregation(matchStage, sampleStage, sortStage, limitStage)

        return mongoTemplate.aggregate(aggregation, "comments", CommentEntity::class.java).mappedResults
    }
}