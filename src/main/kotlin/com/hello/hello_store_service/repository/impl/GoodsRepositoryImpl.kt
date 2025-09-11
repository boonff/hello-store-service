package com.hello.hello_store_service.repository.impl

import com.hello.hello_store_service.model.Goods
import com.hello.hello_store_service.repository.GoodsRepositoryCustom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class GoodsRepositoryCustomImpl() : GoodsRepositoryCustom {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    override fun updateGoods(
        spuId: String,
        goods: Goods
    ): Goods {
        val query = Query(Criteria.where("spuId").`is`(spuId))
        val update = Update()
            .set("title", goods.title)
            .set("maxLinePrice", goods.maxLinePrice)
        mongoTemplate.updateFirst(query, update, Goods::class.java)
        return mongoTemplate.findOne(query, Goods::class.java)!!
    }

}