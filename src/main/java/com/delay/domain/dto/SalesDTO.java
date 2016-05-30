package com.delay.domain.dto;

import java.util.UUID;

/**
 * Created by LucidMinds on 20.05.16.
 * package com.delay.domain.dto;
 */
public class SalesDTO {

    public UUID productId;
    public long count;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
