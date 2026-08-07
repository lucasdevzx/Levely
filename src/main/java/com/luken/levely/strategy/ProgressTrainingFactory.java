package com.luken.levely.strategy;

import com.luken.levely.enums.ProgressTrainingType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProgressTrainingFactory {

    private final Map<ProgressTrainingType, ProgressTrainingStrategy> strategies;

    public ProgressTrainingFactory(List<ProgressTrainingStrategy> progressTrainingStrategies) {
        this.strategies = progressTrainingStrategies.stream()
                .collect(Collectors.toMap(ProgressTrainingStrategy::getProgressType, Function.identity()));
    }

    public ProgressTrainingStrategy getProgressType(ProgressTrainingType progressTrainingType) {
        return strategies.get(progressTrainingType);
    }

}
