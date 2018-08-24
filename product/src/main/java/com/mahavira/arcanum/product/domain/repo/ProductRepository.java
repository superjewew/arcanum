package com.mahavira.arcanum.product.domain.repo;

import com.mahavira.arcanum.product.domain.entity.Boardgame;
import io.reactivex.Single;

/**
 * Created by norman on 24/08/18.
 *
 */

public interface ProductRepository {

    Single<Boardgame> getProductByName(String param);
}
