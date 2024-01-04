package umc.springumc.service.StoreService;

import umc.springumc.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
}
