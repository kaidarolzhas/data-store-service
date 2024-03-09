package kz.olzhas.kafka.datastoremicroservice.repository;

import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;
import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryType;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {
    Optional<Summary> findBySensorId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );
}
