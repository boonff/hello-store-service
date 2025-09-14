package com.hello.hello_store_service.repository.impl

import com.hello.hello_store_service.model.entity.Comments
import com.hello.hello_store_service.repository.CommentsRepositoryCustom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class CommentsRepositoryImpl: CommentsRepositoryCustom {
    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    override fun findByRange(spuId:String, pageIndex: Int, pageSize: Int):List<Comments> {
        val query = Query()
        query.addCriteria(Criteria.where("spuId").`is`(spuId))
        query.skip((pageIndex * pageSize).toLong())
        query.limit(pageSize)
        return mongoTemplate.find(query, Comments::class.java)
    }

    override fun findRandomTopComments(
        spuId: String,
        randomSize: Int,
        selectSize: Int
    ): List<Comments> {
        // 随机选取 randomSize 条 spuId 匹配的评论
        val matchStage = Aggregation.match(Criteria.where("spuId").`is`(spuId))
        val sampleStage = Aggregation.sample(randomSize.toLong())

        // 按 commentScore 降序排序
        val sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "commentScore"))

        // 限制返回 selectSize 条
        val limitStage = Aggregation.limit(selectSize.toLong())

        val aggregation = Aggregation.newAggregation(matchStage, sampleStage, sortStage, limitStage)

        return mongoTemplate.aggregate(aggregation, "comments", Comments::class.java).mappedResults
    }
}