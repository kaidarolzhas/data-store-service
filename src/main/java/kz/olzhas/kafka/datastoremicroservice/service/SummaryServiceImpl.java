package kz.olzhas.kafka.datastoremicroservice.service;


import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;
import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryType;
import kz.olzhas.kafka.datastoremicroservice.model.exception.SensorNotFoundException;
import kz.olzhas.kafka.datastoremicroservice.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes) {
        return summaryRepository.findBySensorId(
                sensorId,
                measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes
        ).orElseThrow(SensorNotFoundException::new);
    }
}
