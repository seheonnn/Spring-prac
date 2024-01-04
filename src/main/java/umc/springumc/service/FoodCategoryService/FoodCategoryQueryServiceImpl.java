package umc.springumc.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springumc.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService {
    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public boolean isExistedCategory(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
