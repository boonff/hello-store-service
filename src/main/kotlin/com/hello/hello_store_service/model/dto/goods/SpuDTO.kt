package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.*

/**
 * 商品详情 DTO（以 SPU 为主体，包含 SKU 和规格信息）
 */
data class SpuDTO(
    val spuId: String,                       // 商品 ID
    val saasId: String,
    val storeId: String,

    val title: String,
    val etitle: String,
    val primaryImage: String,
    val images: List<String>,
    val video: String? = null,
    val desc: List<String>,                  // 图文详情

    val categoryIds: List<String>?,
    val groupIdList: List<String>?,
    val spuTagList: List<SpuTag>,

    val available: Int?,                     // 是否可售
    val minSalePrice: Int?,                  // 最低价
    val maxSalePrice: Int?,                  // 最高价
    val maxLinePrice: Int?,                  // 划线价

    val specList: List<Spec>,
    val skuList: List<Sku>
) {
    companion object {
        fun from(spu: Spu, skus: List<Sku>, specs: List<Spec>): SpuDTO {
            return SpuDTO(
                spuId = spu.spuId,
                saasId = spu.saasId,
                storeId = spu.storeId,
                title = spu.title,
                etitle = spu.etitle,
                primaryImage = spu.primaryImage,
                images = spu.images,
                video = spu.video,
                desc = spu.desc,
                categoryIds = spu.categoryIds,
                groupIdList = spu.groupIdList,
                spuTagList = spu.spuTagList,
                available = spu.available,
                minSalePrice = spu.minSalePrice,
                maxSalePrice = spu.maxSalePrice,
                maxLinePrice = spu.maxLinePrice,

                specList = specs,
                skuList = skus
            )
        }
    }
}


